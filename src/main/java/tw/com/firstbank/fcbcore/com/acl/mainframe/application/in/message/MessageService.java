package tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api.ChannelServiceApi;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api.E001AOuterRequest;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api.TxRqWrapperOuterRequest;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api.TxRsWrapperOuterResponse;
import tw.com.firstbank.fcbcore.fcbframework.core.adapter.in.rest.StatusCode;
import tw.com.firstbank.fcbcore.fcbframework.core.application.exception.SystemException;
import tw.com.firstbank.fcbcore.fcbframework.core.application.out.OuterResponse;

@Component
@AllArgsConstructor
public class MessageService {

	private static final Logger log = LoggerFactory.getLogger(MessageService.class);

	private final XmlMapper xmlMapper = new XmlMapper();

	private final ChannelServiceApi channelService;

	public String process(String message, String txId, String correlationId, String messageId)
			throws JsonProcessingException {
		TxRsWrapperOuterResponse<? extends OuterResponse> outerResponse = null;
		switch (txId) {
			case "FCB92E001A":
				TxRqWrapperOuterRequest<E001AOuterRequest> outerRequest =
						xmlMapper.readValue(message, new TypeReference<>() {});
				outerResponse = channelService.e001a(outerRequest, correlationId, messageId);
				break;
			default:
				throw new SystemException(StatusCode.RETURN_CODE_0011.getCode(), "找不到對應的TxID處理方法");
		}
		return xmlMapper.writeValueAsString(outerResponse);
	}
}
