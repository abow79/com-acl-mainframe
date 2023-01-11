package tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.RequestCommand;

/**
 * The request command of CallTfxrR15.
 */
@ToString
@Setter
@Getter
@JsonRootName("TxRq")
public class CallTfxrR15RequestCommand implements RequestCommand {

	private Integer txDate;
	private Integer claimCurrencyNo;

}
