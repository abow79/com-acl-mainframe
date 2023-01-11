package tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.RequestCommand;

/**
 * The type E001A input.
 */
@ToString
@Setter
@Getter
@JsonRootName("TxRq")
public class GetFxRateRequestCommand implements RequestCommand {

	@JsonProperty("txDate")
	private String txDate;

	@JsonProperty("currencyCode")
	private String currencyCode;

	@JsonProperty("doMark")
	private String doMark;

}
