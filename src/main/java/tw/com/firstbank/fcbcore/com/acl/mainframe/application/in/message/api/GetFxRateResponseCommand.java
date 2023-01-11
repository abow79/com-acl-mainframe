package tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.ResponseCommand;

/**
 * The type E001A input.
 */
@ToString
@Setter
@Getter
@JsonRootName("TxRs")
public class GetFxRateResponseCommand extends MessageHeaderResponseCommand
		implements ResponseCommand {

	@JsonProperty("currencyCode")
	private String currencyCode;

	@JsonProperty("spotBuyRate")
	private String spotBuyRate;

	@JsonProperty("spotSellRate")
	private String spotSellRate;

	@JsonProperty("costBuyRate")
	private String costBuyRate;

	@JsonProperty("costSellRate")
	private String costSellRate;

	@JsonProperty("cashBuyRate")
	private String cashBuyRate;

	@JsonProperty("cashSellRate")
	private String cashSellRate;

	@JsonProperty("cashCostBuyRate")
	private String cashCostBuyRate;

	@JsonProperty("cashCostSellRate")
	private String cashCostSellRate;

	@JsonProperty("largeBuyRate")
	private String largeBuyRate;

	@JsonProperty("largeSellRate")
	private String largeSellRate;

	@JsonProperty("largeCostBuyRate")
	private String largeCostBuyRate;

	@JsonProperty("largeCostSellRate")
	private String largeCostSellRate;

	@JsonProperty("bidCurrencyCode")
	private String bidCurrencyCode;

}
