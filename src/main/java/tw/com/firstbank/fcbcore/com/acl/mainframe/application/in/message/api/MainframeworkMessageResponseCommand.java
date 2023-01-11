package tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api;

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
public class MainframeworkMessageResponseCommand implements ResponseCommand {

	private String correlationId;
	private String message;

}
