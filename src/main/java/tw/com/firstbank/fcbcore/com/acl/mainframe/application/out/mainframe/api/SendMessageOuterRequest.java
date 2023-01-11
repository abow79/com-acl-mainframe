package tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.mainframe.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.com.firstbank.fcbcore.fcbframework.core.application.out.OuterRequest;

@ToString
@Getter
@Setter
public class SendMessageOuterRequest implements OuterRequest {

	private String correlationId;
	private String message;
}
