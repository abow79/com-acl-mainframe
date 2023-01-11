package tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.ResponseCommand;

/**
 * The response command of CallTfxrR15.
 */
@ToString
@Setter
@Getter
@JsonRootName("TxRs")
public class CallTfxrR15ResponseCommand extends MessageHeaderResponseCommand implements ResponseCommand {

	private BigDecimal cpotBoughRate1;
	private BigDecimal cpotBoughRate2;
	private BigDecimal costRate;
	private BigDecimal costRateLarge;
}
