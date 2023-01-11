package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.factory.Mappers;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.mapper.MainframeControllerMapper;
import tw.com.firstbank.fcbcore.fcbframework.core.adapter.in.rest.api.ResponseBody;


@Getter
@Setter
@ToString
public class MessageResponse implements ResponseBody {

	private static final MainframeControllerMapper mapper =
			Mappers.getMapper(MainframeControllerMapper.class);

	@Schema(description = "CorrelationId", example = "FCB112022100717111300001")
	@NotBlank
	@JsonProperty("correlationId")
	private String correlationId;

	@Schema(description = "電文",
			example = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE FCB91103G><XML><Header><TxID>FCB91103G</TxID><SystemKey>FCB38</SystemKey><TxSeqNo>20040408000021</TxSeqNo><MsgSeqNo>00001</MsgSeqNo><CltTimeStamp>2004-04-0809:25:00.000</CltTimeStamp><CustID>00050523220</CustID><UserID>0001</UserID><AcctNo>04810345676</AcctNo><MsgDirection>RQ</MsgDirection></Header><TxRq>….</TxRq></XML>")
	@NotBlank(message = "此欄位為必填不可空白")
	@JsonProperty("message")
	private String message;

}
