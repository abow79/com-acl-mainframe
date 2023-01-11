package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.channel.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The type S001A json input.
 */
@Getter
@Setter
@ToString
public class E001ARequest {

	@Schema(description = "Account", example = "10-212-12345")
	@NotBlank(message = "account")
	@JsonProperty("account")
	private String account;

	@Schema(description = "金額", example = "100")
	@Digits(fraction = 2, integer = 9)
	@JsonProperty("amount")
	private BigDecimal amount;
}
