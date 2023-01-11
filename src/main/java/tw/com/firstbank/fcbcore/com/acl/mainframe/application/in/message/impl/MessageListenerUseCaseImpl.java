package tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.MessageService;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.MessageListenerRequestCommand;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.MessageListenerResponseCommand;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.MessageListenerUseCaseApi;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.mapper.MessageUseCaseMapper;
import tw.com.firstbank.fcbcore.fcbframework.core.adapter.in.rest.StatusCode;
import tw.com.firstbank.fcbcore.fcbframework.core.application.exception.SystemException;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.CommandHandler;
import tw.com.firstbank.fcbcore.fcbframework.core.application.util.ApLogHelper;
import tw.com.firstbank.fcbcore.fcbframework.core.spring.config.ApLog;

@Service
@AllArgsConstructor
public class MessageListenerUseCaseImpl extends MessageListenerUseCaseApi
		implements CommandHandler {

	private static final Logger log = LoggerFactory.getLogger(MessageListenerUseCaseImpl.class);

	private static final MessageUseCaseMapper mapper = Mappers.getMapper(MessageUseCaseMapper.class);

	private MessageService messageService;

	@Override
	@ApLog
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000,
			rollbackFor = Exception.class)
	public MessageListenerResponseCommand execute(MessageListenerRequestCommand requestCommand) {
		try {
			// 取得TxID
			XmlMapper xmlMapper = new XmlMapper();
			Map<String, Object> messageMap = xmlMapper.readValue(requestCommand.getMessage(), Map.class);
			Map<String, Object> header = (Map<String, Object>) messageMap.get("Header");
			String txId = (String) header.get("TxID");

			// Call Domain Service，調用Outbound方法
			String result = messageService.process(requestCommand.getMessage(), txId,
					requestCommand.getCorrelationId(), requestCommand.getMessageId());

			// 準備回傳結果
			MessageListenerResponseCommand responseCommand = new MessageListenerResponseCommand();
			responseCommand.setCorrelationId(requestCommand.getCorrelationId());
			responseCommand.setMessageId(requestCommand.getMessageId());
			responseCommand.setMessage(result);
			return responseCommand;
		} catch (JsonProcessingException e) {
			ApLogHelper.error(log, e.getMessage());
			throw new SystemException(StatusCode.RETURN_CODE_0011.getCode(), "XML轉換發生錯誤");
		}
	}
}
