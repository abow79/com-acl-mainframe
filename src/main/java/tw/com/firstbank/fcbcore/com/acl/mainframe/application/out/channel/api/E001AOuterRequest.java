package tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.RequestCommand;

@ToString
@Getter
@Setter
public class E001AOuterRequest implements RequestCommand {

	private String account;

	private BigDecimal amount;
}
