package tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api;

import tw.com.firstbank.fcbcore.fcbframework.core.application.out.OutboundServiceApi;

public interface ChannelServiceApi extends OutboundServiceApi {

	TxRsWrapperOuterResponse<E001AOuterResponse> e001a(
			TxRqWrapperOuterRequest<E001AOuterRequest> outerRequest, String correlationId,
			String messageId);

}
