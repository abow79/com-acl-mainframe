package tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.RequestCommand;


@ToString
@Setter
@Getter
@JsonRootName("TxRq")
public class MainframeMessageRequestCommand implements RequestCommand {

	private String txId;

	private RequestCommand txRq;

}
