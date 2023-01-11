package tw.com.firstbank.fcbcore.com.acl.mainframe.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import tw.com.firstbank.fcbcore.fcbframework.adapters.jms.adapter.out.message.api.JmsOutbound;
import tw.com.firstbank.fcbcore.fcbframework.adapters.jms.adapter.out.message.impl.JmsOutboundAdapter;

@Configuration
public class MainframeConfig {

	public final String uploadTxRq;
	public final String uploadTxRs;

	public MainframeConfig(@Value("${ibm.mq.queue.uploadTxRq}") String uploadTxRq,
		@Value("${ibm.mq.queue.uploadTxRs}") String uploadTxRs) {
		this.uploadTxRq = uploadTxRq;
		this.uploadTxRs = uploadTxRs;
	}

	@Bean
	public JmsOutbound jmsOutboundAdapter(JmsTemplate jmsTemplate) {
		return new JmsOutboundAdapter(jmsTemplate);
	}
}
