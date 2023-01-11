package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.api.CallTfxrR15Request;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.api.CallTfxrR15Response;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.api.GetFxRateRequest;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.api.GetFxRateResponse;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.api.MainframeControllerApi;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.mapper.MainframeControllerMapper;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.CallTfxrR15ResponseCommand;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.GetFxRateRequestCommand;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.GetFxRateResponseCommand;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.TxRsWrapperResponseCommand;
import tw.com.firstbank.fcbcore.fcbframework.core.adapter.in.rest.api.RequestWrapper;
import tw.com.firstbank.fcbcore.fcbframework.core.adapter.in.rest.api.ResponseWrapper;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.CommandBus;

@AllArgsConstructor
@RestController
public class MainframeControllerImpl extends MainframeBaseController
		implements MainframeControllerApi {

	private static final Logger log = LoggerFactory.getLogger(MainframeControllerImpl.class);

	private static final MainframeControllerMapper mapper =
			Mappers.getMapper(MainframeControllerMapper.class);

	private final CommandBus commandBus;

	@Override
	@PostMapping("/getFxRate/v1")
	public ResponseEntity<ResponseWrapper<GetFxRateResponse>> getFxRate(
			RequestWrapper<GetFxRateRequest> requestWrapper) {
		String txId = "WFXR";

		TxRsWrapperResponseCommand<GetFxRateResponseCommand> getFxRateResponseCommand =
				this.handleMessage(commandBus, requestWrapper, txId, GetFxRateRequestCommand.class,
						new TypeReference<TxRsWrapperResponseCommand<GetFxRateResponseCommand>>() {});

		// 將OuterResponse轉成Response回傳
		return this.makeResponseEntity(getFxRateResponseCommand.getTxRs(), mapper::toGetFxRateResponse);
	}

	@Override
	@PostMapping("/call-tfxr-r15/v1")
	public ResponseEntity<ResponseWrapper<CallTfxrR15Response>> callTfxrR15(
		RequestWrapper<CallTfxrR15Request> requestWrapper) {
		String txId = "CallTfxrR15";

		TxRsWrapperResponseCommand<CallTfxrR15ResponseCommand> responseCommand =
			this.handleMessage(commandBus, requestWrapper, txId, GetFxRateRequestCommand.class,
				new TypeReference<TxRsWrapperResponseCommand<CallTfxrR15ResponseCommand>>() {});

		// 將OuterResponse轉成Response回傳
		return this.makeResponseEntity(responseCommand.getTxRs(), mapper::toCallTfxrR15Response);
	}


}
