package tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.com.firstbank.fcbcore.fcbframework.core.application.out.OuterResponse;

@ToString
@Getter
@Setter
@JsonRootName("TxRs")
public class E001AOuterResponse implements OuterResponse {

	@JsonProperty("Amount")
	private BigDecimal amount;
	@JsonProperty("Fee")
	private BigDecimal fee;
}
