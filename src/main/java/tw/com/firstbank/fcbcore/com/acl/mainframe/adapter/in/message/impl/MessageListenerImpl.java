package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.message.impl;

import javax.jms.Session;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.JmsMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.message.api.MessageListenerRequest;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.message.api.MessageListenerResponse;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.message.mapper.MessageListenerMapper;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.MessageListenerResponseCommand;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.message.api.MessageListenerApi;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.CommandBus;


@Component
@AllArgsConstructor
public class MessageListenerImpl implements MessageListenerApi {

	private static final Logger log = LoggerFactory.getLogger(MessageListenerImpl.class);

	private static final MessageListenerMapper mapper =
			Mappers.getMapper(MessageListenerMapper.class);
	private final CommandBus commandBus;

//	@SensitiveApi
//	@JmsListener(destination = "${ibm.mq.queue.downloadTxRq}")
//	@SendTo("${ibm.mq.queue.downloadTxRs}")
	public String receiveMessage(String message, JmsMessageHeaderAccessor jmsMessageHeaderAccessor,
			Session session) {

		MessageListenerRequest request = new MessageListenerRequest();
		request.setMessage(message);
		request.setCorrelationId(jmsMessageHeaderAccessor.getCorrelationId());
		request.setMessageId(jmsMessageHeaderAccessor.getMessageId());

		MessageListenerResponseCommand responseCommand = commandBus.handle(request.toCommand());
		MessageListenerResponse response = mapper.toEBankMessageResponse(responseCommand);
		return response.getMessage();
	}
}
