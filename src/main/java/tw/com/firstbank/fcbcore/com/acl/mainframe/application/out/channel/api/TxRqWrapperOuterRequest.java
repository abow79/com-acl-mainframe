package tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.RequestCommand;

@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("XML")
@Getter
public class TxRqWrapperOuterRequest<T extends RequestCommand> {

	@JsonProperty("Header")
	private TxRqHeaderOuterRequest header;

	@JsonProperty("TxRq")
	private T txRq;
}
