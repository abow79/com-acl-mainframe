package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tw.com.firstbank.fcbcore.fcbframework.core.adapter.in.rest.api.RequestWrapper;
import tw.com.firstbank.fcbcore.fcbframework.core.adapter.in.rest.api.ResponseWrapper;

@RequestMapping("/na/com/am/acl")
@Validated
@Tag(name = "Mainframe", description = "Send Message to mainframe")
public interface MainframeControllerApi {

	@Operation(summary = "Call GetFxRate Message", description = "Call GetFxRate Message")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Validation Fail",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = RequestWrapper.class))}),
			@ApiResponse(responseCode = "500", description = "Internal Server Error",
					content = {@Content(mediaType = "application/json",
							schema = @Schema(implementation = RequestWrapper.class))})})
	ResponseEntity<ResponseWrapper<GetFxRateResponse>> getFxRate(
			@Valid @RequestBody RequestWrapper<GetFxRateRequest> requestWrapper);



	/**
	 * Calls the TFXR-R15 function of mainframe.
	 *
	 * @param requestWrapper Request wrapper of CallTfxrR15Request
	 * @return Standard format response of CallTfxrR15Response
	 */
	@Operation(summary = "透過承作日和求償幣別取得匯率", description = "call舊核TFXR-R15程序,獲取匯率")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "400", description = "Validation Fail", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = RequestWrapper.class))}),
		@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = RequestWrapper.class))})})
	ResponseEntity<ResponseWrapper<CallTfxrR15Response>> callTfxrR15(
		@Valid @RequestBody RequestWrapper<CallTfxrR15Request> requestWrapper);
}
