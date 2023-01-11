package tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.mainframe.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.com.firstbank.fcbcore.fcbframework.core.application.out.OuterResponse;

@ToString
@Getter
@Setter
public class SendMessageOuterResponse implements OuterResponse {

	private String correlationId;
	private String message;
}
