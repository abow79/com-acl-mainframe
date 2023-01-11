package tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.BaseRequestCommand;

@ToString
@Setter
@Getter
public class MessageListenerRequestCommand extends BaseRequestCommand {

	private String correlationId;
	private String messageId;
	private String message;

}
