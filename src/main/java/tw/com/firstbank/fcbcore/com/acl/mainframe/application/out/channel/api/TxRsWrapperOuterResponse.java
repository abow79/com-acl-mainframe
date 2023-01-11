package tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.com.firstbank.fcbcore.fcbframework.core.application.out.OuterResponse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("XML")
public class TxRsWrapperOuterResponse<T extends OuterResponse> {

	@JsonProperty("Header")
	private TxRsHeaderOuterResponse header;

	@JsonProperty("TxRs")
	private T txRs;
}
