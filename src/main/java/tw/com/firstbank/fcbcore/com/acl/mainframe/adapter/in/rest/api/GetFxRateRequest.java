package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.factory.Mappers;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.mapper.MainframeControllerMapper;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.GetFxRateRequestCommand;
import tw.com.firstbank.fcbcore.fcbframework.core.adapter.in.rest.api.RequestBody;

/**
 * The type S001A json input.
 */
@Getter
@Setter
@ToString
public class GetFxRateRequest implements RequestBody {

	private static final MainframeControllerMapper mapper =
			Mappers.getMapper(MainframeControllerMapper.class);

	@Schema(description = "交易日", example = "20230101")
	@NotBlank
	@JsonProperty("txDate")
	private String txDate;

	@Schema(description = "幣別", example = "99")
	@NotBlank
	@JsonProperty("currencyCode")
	private String currencyCode;

	@Schema(description = "DBU/OBU註記", example = "D")
	@NotBlank
	@JsonProperty("doMark")
	private String doMark;

	@Override
	public GetFxRateRequestCommand toCommand() {
		return mapper.toGetFxRateRequestCommand(this);
	}

}
