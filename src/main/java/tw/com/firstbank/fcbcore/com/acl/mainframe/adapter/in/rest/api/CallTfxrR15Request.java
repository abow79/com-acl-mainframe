package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.factory.Mappers;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.mapper.MainframeControllerMapper;
import tw.com.firstbank.fcbcore.fcbframework.core.adapter.in.rest.api.RequestBody;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.RequestCommand;

/**
 * The inward request object of CallTfxrR15.
 */
@Getter
@Setter
@ToString
public class CallTfxrR15Request implements RequestBody {


	private static final MainframeControllerMapper mapper = Mappers.getMapper(
		MainframeControllerMapper.class);

	@Schema(description = "承作日", example = "20221129")
	@JsonProperty("txDate")
	private Integer txDate;


	@Schema(description = "求償幣別", example = "11")
	@JsonProperty("claimCurrencyNo")
	private Integer claimCurrencyNo;


	@Override
	public RequestCommand toCommand() {
		return mapper.toCallTfxrR15RequestCommand(this);
	}
}
