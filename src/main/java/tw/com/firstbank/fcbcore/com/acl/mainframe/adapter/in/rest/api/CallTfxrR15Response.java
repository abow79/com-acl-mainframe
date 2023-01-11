package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Setter;
import lombok.ToString;
import tw.com.firstbank.fcbcore.fcbframework.core.adapter.in.rest.api.ResponseBody;

/**
 * The outward response object of CallTfxrR15.
 */
@ToString
@Setter
public class CallTfxrR15Response implements ResponseBody {

	@Schema(description = "即期買匯匯率", example = "0000")
	@JsonProperty("cpotBoughRate1")
	private BigDecimal cpotBoughRate1;


	@Schema(description = "即期大額買匯匯率2", example = "0000")
	@JsonProperty("cpotBoughRate2")
	private BigDecimal cpotBoughRate2;

	@Schema(description = "即期買匯匯率成本", example = "0000")
	@JsonProperty("costRate")
	private BigDecimal costRate;

	@Schema(description = "irCostRateLarge", example = "0000")
	@JsonProperty("costRateLarge")
	private BigDecimal costRateLarge;


}
