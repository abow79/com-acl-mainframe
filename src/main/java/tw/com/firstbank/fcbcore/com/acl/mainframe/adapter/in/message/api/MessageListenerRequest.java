package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.message.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.factory.Mappers;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.message.mapper.MessageListenerMapper;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.MessageListenerRequestCommand;
import tw.com.firstbank.fcbcore.fcbframework.core.adapter.in.rest.api.RequestBody;

@Getter
@Setter
@ToString
public class MessageListenerRequest implements RequestBody {

	private static final MessageListenerMapper mapper =
			Mappers.getMapper(MessageListenerMapper.class);

	@Schema(description = "correlationId", example = "FCB112022100717111300001")
	private String correlationId;

	@Schema(description = "messageId", example = "")
	private String messageId;

	@Schema(description = "電文",
			example = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE FCB91103G><XML><Header><TxID>FCB91103G</TxID><SystemKey>FCB38</SystemKey><TxSeqNo>20040408000021</TxSeqNo><MsgSeqNo>00001</MsgSeqNo><CltTimeStamp>2004-04-0809:25:00.000</CltTimeStamp><CustID>00050523220</CustID><UserID>0001</UserID><AcctNo>04810345676</AcctNo><MsgDirection>RQ</MsgDirection></Header><TxRq>….</TxRq></XML>")
	private String message;

	@Override
	public MessageListenerRequestCommand toCommand() {
		return mapper.toEBankMessageRequestCommand(this);
	}
}
