package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.MainframeMessageRequestCommand;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.MainframeworkMessageResponseCommand;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.MessageHeaderResponseCommand;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.TxRsWrapperResponseCommand;
import tw.com.firstbank.fcbcore.fcbframework.core.adapter.in.rest.StatusCode;
import tw.com.firstbank.fcbcore.fcbframework.core.adapter.in.rest.api.RequestBody;
import tw.com.firstbank.fcbcore.fcbframework.core.adapter.in.rest.api.RequestWrapper;
import tw.com.firstbank.fcbcore.fcbframework.core.adapter.in.rest.impl.BaseController;
import tw.com.firstbank.fcbcore.fcbframework.core.application.exception.SystemException;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.CommandBus;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.RequestCommand;

@AllArgsConstructor
public class MainframeBaseController extends BaseController {

	public <T extends RequestCommand, R extends MessageHeaderResponseCommand> TxRsWrapperResponseCommand<R> handleMessage(
			CommandBus commandBus, RequestWrapper<? extends RequestBody> requestWrapper, String txId,
			Class<T> commandType, TypeReference<?> typeReference) {

		// Request 物件轉成OuterRequest(XML格式物件)
		MainframeMessageRequestCommand requestCommand =
				this.getMainframeMessageRequestCommand(requestWrapper, txId, commandType);

		// 轉成String xml，調用CommandBus呼叫共用發電文的application
		MainframeworkMessageResponseCommand responseCommand = commandBus.handle(requestCommand);

		// 發送電文，取得回傳字串結果，將字串轉成OuterResponse
		TxRsWrapperResponseCommand<R> getFxRateResponseCommand =
				this.getTxRsWrapper(responseCommand.getMessage(), typeReference);

		// 補上XML回傳<Header>資訊
		this.setStatusCode(getFxRateResponseCommand);

		return getFxRateResponseCommand;
	}

	public <T extends RequestCommand> MainframeMessageRequestCommand getMainframeMessageRequestCommand(
			RequestWrapper<? extends RequestBody> requestWrapper, String txId, Class<T> commandType) {

		// Request 物件轉成OuterRequest(XML格式物件)
		RequestCommand getFxRateRequestCommand = commandType.cast(requestWrapper.toCommand());
		MainframeMessageRequestCommand requestCommand = new MainframeMessageRequestCommand();
		requestCommand.setTxId("FCB4A" + txId);
		requestCommand.setTxRq(getFxRateRequestCommand);
		return requestCommand;
	}

	public <T extends MessageHeaderResponseCommand> TxRsWrapperResponseCommand<T> getTxRsWrapper(
			String message, TypeReference<?> typeReference) {

		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			Object txRsWrapperResponseCommand = xmlMapper.readValue(message, typeReference);
			return (TxRsWrapperResponseCommand<T>) txRsWrapperResponseCommand;
		} catch (JsonProcessingException e) {
			throw new SystemException(StatusCode.RETURN_CODE_0011.getCode(), "XML轉換發生錯誤");
		}
	}

	public void setStatusCode(
			TxRsWrapperResponseCommand<? extends MessageHeaderResponseCommand> getFxRateResponseCommand) {

		getFxRateResponseCommand.getTxRs()
				.setStatusCode(getFxRateResponseCommand.getHeader().getStatusCode());
		getFxRateResponseCommand.getTxRs()
				.setStatusDesc(getFxRateResponseCommand.getHeader().getStatusDesc());
	}
}
