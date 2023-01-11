package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.channel.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MessageResponseWrapper<T> {

	private String txId;

	private String systemKey;

	private String txSeqNo;

	private String msgSeqNo;

	private String cltTimeStamp;

	private String msgDirection;

	private String system;

	private String statusCode;

	private String statusDesc;

	private String sourceId;

	private T clientResponse;

}
