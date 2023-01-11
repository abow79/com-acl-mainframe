package tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tw.com.firstbank.fcbcore.fcbframework.core.application.in.RequestCommand;

@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName("XML")
@Getter
public class TxRqWrapperRequestCommand<T extends RequestCommand> {

	@JsonProperty("Header")
	private TxRqHeaderRequestCommand header;

	@JsonProperty("TxRq")
	private T txRq;
}
