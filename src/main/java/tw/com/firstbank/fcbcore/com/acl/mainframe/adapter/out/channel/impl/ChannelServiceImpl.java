package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.channel.impl;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.channel.api.ChannelFeignClient;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.channel.api.E001ARequest;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.channel.api.E001AResponse;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.channel.api.MessageRequestWrapper;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.channel.api.MessageResponseWrapper;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.channel.mapper.ChannelMapper;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api.E001AOuterRequest;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api.E001AOuterResponse;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api.ChannelServiceApi;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api.TxRqWrapperOuterRequest;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api.TxRsWrapperOuterResponse;
import tw.com.firstbank.fcbcore.fcbframework.core.application.CoreHeaderLegacy.ReqKey;
import tw.com.firstbank.fcbcore.fcbframework.core.spring.config.ChannelSource;

@Component
@AllArgsConstructor
public class ChannelServiceImpl implements ChannelServiceApi {

	private static final Logger log = LoggerFactory.getLogger(ChannelServiceImpl.class);
	private static final ChannelMapper mapper = Mappers.getMapper(ChannelMapper.class);

	private ChannelFeignClient outbound;

	@Override
	public TxRsWrapperOuterResponse<E001AOuterResponse> e001a(
			TxRqWrapperOuterRequest<E001AOuterRequest> outerRequest, String correlationId,
			String messageId) {

		MessageRequestWrapper<E001ARequest> request =
				mapper.toEBankRequestWrapper(outerRequest, correlationId, messageId);

		// 組合Channel 所需Http Header資訊
		Map<String, Object> headers = new HashMap<>();
		headers.put(ReqKey.X_CORE_CHANNEL.getName(), ChannelSource.E_BANK.getCode());
		headers.put(ReqKey.ACCEPT_LANGUAGE.getName(), "en");
		headers.put(ReqKey.X_CORE_UID.getName(), "uid");
		headers.put(ReqKey.X_CORE_TXID.getName(), request.getTxId());
		headers.put(ReqKey.X_CORE_TIMESTAMP.getName(), request.getCltTimeStamp());

		// 組合Channel 所需Client Header必填資訊
		request.setCorrelationId(correlationId == null ? "correlationId" : correlationId);
		request.setSourceId(ChannelSource.E_BANK.getCode());

		MessageResponseWrapper<E001AResponse> response = outbound.e001a(headers, request);
		return mapper.toTxRsWrapper(response);
	}

}
