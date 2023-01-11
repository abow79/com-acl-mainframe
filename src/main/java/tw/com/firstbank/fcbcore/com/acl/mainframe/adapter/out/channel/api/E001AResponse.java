package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.channel.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The type S001A json output.
 */
@Getter
@Setter
@ToString
public class E001AResponse {

	@Schema(description = "金額", example = "100")
	@JsonProperty(value = "amount")
	private BigDecimal amount;
	@Schema(description = "手續費", example = "20")
	@JsonProperty(value = "fee")
	private BigDecimal fee;

}
