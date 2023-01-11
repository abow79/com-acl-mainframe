package tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.MainframeMessageRequestCommand;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.MainframeMessageUseCaseApi;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.MainframeworkMessageResponseCommand;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.TxRqHeaderRequestCommand;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.TxRqWrapperRequestCommand;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.mapper.MessageUseCaseMapper;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.mainframe.api.MainframeServiceApi;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.mainframe.api.SendMessageOuterRequest;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.mainframe.api.SendMessageOuterResponse;
import tw.com.firstbank.fcbcore.fcbframework.core.adapter.in.rest.StatusCode;
import tw.com.firstbank.fcbcore.fcbframework.core.application.exception.SystemException;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.CommandHandler;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.RequestCommand;
import tw.com.firstbank.fcbcore.fcbframework.core.application.util.ApLogHelper;
import tw.com.firstbank.fcbcore.fcbframework.core.spring.config.ApLog;

@Service
@AllArgsConstructor
public class MainframeMessageUseCaseImpl extends MainframeMessageUseCaseApi
		implements CommandHandler {

	private static final Logger log = LoggerFactory.getLogger(MainframeMessageUseCaseImpl.class);

	private static final MessageUseCaseMapper mapper = Mappers.getMapper(MessageUseCaseMapper.class);

	private MainframeServiceApi mainframeService;

	/**
	 * Executes EBank domain logic in order.
	 *
	 * @param requestCommand the EBank original data
	 * @return the EBank data with count fee
	 */
	@Override
	@ApLog
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000,
			rollbackFor = Exception.class)
	public MainframeworkMessageResponseCommand execute(
			MainframeMessageRequestCommand requestCommand) {

		XmlMapper xmlMapper = new XmlMapper();
		OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		// 組合XML Header
		String txId = requestCommand.getTxId();
		String txSeqNo = dateFormat.format(new Date());
		String msgSeqNo = "";
		String correlationId = txId + txSeqNo + msgSeqNo;

		TxRqHeaderRequestCommand header = new TxRqHeaderRequestCommand();
		header.setTxID(txId);
		header.setTxSeqNo(txSeqNo);
		header.setMsgSeqNo(msgSeqNo);
		header.setCltTimeStamp(now.toString());
		header.setCustID("");
		header.setAcctNo("");
		header.setUserID("");
		header.setSystemKey("FCB4A");
		header.setMsgDirection("RQ");

		try {
			// 將Header、TxRq轉換維XML String
			TxRqWrapperRequestCommand<? extends RequestCommand> txRqWrapperRequestCommand =
					new TxRqWrapperRequestCommand<>(header, requestCommand.getTxRq());
			String txRq = xmlMapper.writeValueAsString(txRqWrapperRequestCommand);
			SendMessageOuterRequest outerRequest = new SendMessageOuterRequest();
			outerRequest.setMessage(txRq);
			outerRequest.setCorrelationId(correlationId);

			// XML String Outbound
			SendMessageOuterResponse outerResponse = mainframeService.sendMessage(outerRequest);
			String txRs = outerResponse.getMessage();

			// 回傳Response XML String
			MainframeworkMessageResponseCommand responseCommand =
					new MainframeworkMessageResponseCommand();
			responseCommand.setMessage(txRs);
			responseCommand.setCorrelationId(correlationId);
			return responseCommand;
		} catch (JsonProcessingException e) {
			ApLogHelper.error(log, e.getMessage());
			throw new SystemException(StatusCode.RETURN_CODE_0011.getCode(), "XML轉換發生錯誤");
		}
	}
}
