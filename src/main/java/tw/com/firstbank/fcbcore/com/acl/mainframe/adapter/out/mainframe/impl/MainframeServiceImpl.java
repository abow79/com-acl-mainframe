package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.mainframe.impl;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.mainframe.api.MainframeServiceApi;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.mainframe.api.SendMessageOuterRequest;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.mainframe.api.SendMessageOuterResponse;
import tw.com.firstbank.fcbcore.com.acl.mainframe.spring.config.MainframeConfig;
import tw.com.firstbank.fcbcore.fcbframework.adapters.jms.adapter.out.message.api.JmsOutbound;
import tw.com.firstbank.fcbcore.fcbframework.core.spring.config.SensitiveApi;

@Component
@AllArgsConstructor
public class MainframeServiceImpl implements MainframeServiceApi {

	private static final Logger log = LoggerFactory.getLogger(MainframeServiceImpl.class);

	private JmsOutbound jmsOutbound;

	private MainframeConfig mainframeConfig;

	@Override
	@SensitiveApi
	public SendMessageOuterResponse sendMessage(SendMessageOuterRequest outerRequest) {
		SendMessageOuterResponse response = new SendMessageOuterResponse();
		String result = jmsOutbound.sendAndReceive(mainframeConfig.uploadTxRq,
			mainframeConfig.uploadTxRs, outerRequest.getCorrelationId(), outerRequest.getMessage());
		response.setMessage(result);
		response.setCorrelationId(outerRequest.getCorrelationId());
		return response;
	}
}
