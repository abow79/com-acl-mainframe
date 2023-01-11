package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.com.firstbank.fcbcore.fcbframework.core.adapter.in.rest.api.ResponseBody;


@Getter
@Setter
@ToString
public class GetFxRateResponse implements ResponseBody {

	private String currencyCode;
	private String spotBuyRate;
	private String spotSellRate;
	private String costBuyRate;
	private String costSellRate;
	private String cashBuyRate;
	private String cashSellRate;
	private String cashCostBuyRate;
	private String cashCostSellRate;
	private String largeBuyRate;
	private String largeSellRate;
	private String largeCostBuyRate;
	private String largeCostSellRate;
	private String bidCurrencyCode;
	private String statusCode;
	private String statusDesc;
}
