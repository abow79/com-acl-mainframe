package tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("Header")
public class TxRsHeaderOuterResponse implements Serializable {

	@JsonProperty("TxID")
	private String txID;

	@JsonProperty("SystemKey")
	private String systemKey;

	@JsonProperty("TxSeqNo")
	private String txSeqNo;

	@JsonProperty("MsgSeqNo")
	private String msgSeqNo;

	@JsonProperty("CltTimeStamp")
	private String cltTimeStamp;


	@JsonProperty("CustID")
	private String custID;

	@JsonProperty("UserID")
	private String userID;

	@JsonProperty("AcctNo")
	private String acctNo;

	@JsonProperty("MsgDirection")
	private String msgDirection;

	@JsonProperty("System")
	private String system;

	@JsonProperty("StatusCode")
	private String statusCode;

	@JsonProperty("StatusDesc")
	private String statusDesc;

}
