package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.mainframe.api;


import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.factory.Mappers;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.channel.mapper.ChannelMapper;
import tw.com.firstbank.fcbcore.fcbframework.adapters.rest.adapter.out.rest.api.RestInternalRequestBody;

@Setter
@Getter
@ToString
public class SendMessageRequest implements RestInternalRequestBody {

	@Serial
	private static final long serialVersionUID = 1094321765954112679L;
	private static final ChannelMapper converter = Mappers.getMapper(ChannelMapper.class);

	@Schema(description = "CorrelationId", example = "FCB112022100717111300001")
	@NotBlank(message = "此欄位為必填不可空白")
	private String correlationId;

	@Schema(description = "電文",
			example = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE FCB91103G><XML><Header><TxID>FCB91103G</TxID><SystemKey>FCB38</SystemKey><TxSeqNo>20040408000021</TxSeqNo><MsgSeqNo>00001</MsgSeqNo><CltTimeStamp>2004-04-0809:25:00.000</CltTimeStamp><CustID>00050523220</CustID><UserID>0001</UserID><AcctNo>04810345676</AcctNo><MsgDirection>RQ</MsgDirection></Header><TxRq>….</TxRq></XML>")
	@NotBlank(message = "此欄位為必填不可空白")
	private String message;

}
