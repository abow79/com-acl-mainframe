package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.api.CallTfxrR15Request;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.api.CallTfxrR15Response;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.api.GetFxRateRequest;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.api.GetFxRateResponse;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.CallTfxrR15RequestCommand;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.CallTfxrR15ResponseCommand;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.GetFxRateRequestCommand;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.GetFxRateResponseCommand;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MainframeControllerMapper {

	GetFxRateRequestCommand toGetFxRateRequestCommand(GetFxRateRequest source);

	GetFxRateResponse toGetFxRateResponse(GetFxRateResponseCommand source);

	CallTfxrR15RequestCommand toCallTfxrR15RequestCommand(CallTfxrR15Request request);

	CallTfxrR15Response toCallTfxrR15Response(CallTfxrR15ResponseCommand responseCommand);
}
