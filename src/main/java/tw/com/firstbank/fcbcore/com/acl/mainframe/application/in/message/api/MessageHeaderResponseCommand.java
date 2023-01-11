package tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.ResponseCommand;

@ToString
@Getter
@Setter
public class MessageHeaderResponseCommand implements ResponseCommand {

	@JsonProperty("statusCode")
	private String statusCode;

	@JsonProperty("statusDesc")
	private String statusDesc;

}
