package tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.ResponseCommand;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("XML")
public class TxRsWrapperResponseCommand<T extends ResponseCommand> {

	@JsonProperty("Header")
	private TxRsHeaderResponseCommand header;

	@JsonProperty("TxRs")
	private T txRs;
}
