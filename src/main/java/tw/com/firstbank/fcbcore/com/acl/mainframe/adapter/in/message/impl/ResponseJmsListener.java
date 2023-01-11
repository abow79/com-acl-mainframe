package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.message.impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.JmsMessageHeaderAccessor;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Component
@AllArgsConstructor
@ConditionalOnProperty(name = "ibm.mq.mockListener", havingValue = "true")
public class ResponseJmsListener {

	private static final Logger log = LoggerFactory.getLogger(ResponseJmsListener.class);

	// TODO: 此Listener僅為模擬舊核心接收到資料，並負責模擬回傳舊核心訊息
	@JmsListener(destination = "${ibm.mq.queue.uploadTxRq}")
	@SendTo("${ibm.mq.queue.uploadTxRs}")
	public String responseMessage(String message, MessageHeaders messageHeaders,
			JmsMessageHeaderAccessor jmsMessageHeaderAccessor) throws IOException {

		log.debug("JMS Header(CorrelationId): {}", jmsMessageHeaderAccessor.getCorrelationId());
		// 取得doctype(txId)
		String txId = this.getTxId(message);
		// 讀取取resource/mock/{doctype} 檔案內容，並回傳內容XML字串
		return getMockData(txId);
	}

	private String getTxId(String message) throws IOException {
		XmlMapper xmlMapper = new XmlMapper();
		Map<String, Object> map = xmlMapper.readValue(message, Map.class);
		Map<String, Object> header = (Map<String, Object>) map.get("Header");
		return (String) header.get("TxID");
	}

	private String getMockData(String mock) {
		Resource resource = new ClassPathResource("/mock/" + mock);
		InputStream inputStream = null;
		String result = "";
		try {
			inputStream = resource.getInputStream();
			byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
			result = new String(bytes, StandardCharsets.UTF_8);
		} catch (IOException e) {
			return String.format("Not found resource/mock/%s file", mock);
		}
		return result.replace("\t", "").replace("\r", "").replace("\n", "");
	}
}
