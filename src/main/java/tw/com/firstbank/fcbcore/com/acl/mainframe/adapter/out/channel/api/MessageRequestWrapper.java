package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.channel.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ToString
@Getter
@Setter
public class MessageRequestWrapper<T> {

	private static final Logger log = LoggerFactory.getLogger(MessageRequestWrapper.class);

	@Schema(description = "交易代號", example = "FCB92FR12")
	@NotBlank
	@JsonProperty("txId")
	private String txId;

	@Schema(description = "系統代號", example = "FCB92")
	@NotBlank
	@JsonProperty("systemKey")
	private String systemKey;

	@Schema(description = "交易序號", example = "20221230101530")
	@NotBlank
	@JsonProperty("txSeqNo")
	private String txSeqNo;

	@Schema(description = "訊息序號", example = "00001")
	@NotBlank
	@JsonProperty("msgSeqNo")
	private String msgSeqNo;

	@Schema(description = "傳送時間", example = "2022-09-01T10:01:400480")
	@NotBlank
	@JsonProperty("cltTimeStamp")
	private String cltTimeStamp;

	@Schema(description = "統編或身分證字號", example = "00000000")
	@JsonProperty("custId")
	private String custId;

	@Schema(description = "使用者代號", example = "0000")
	@JsonProperty("userId")
	private String userId;

	@Schema(description = "帳號", example = "00000000000")
	@JsonProperty("acctNo")
	private String acctNo;

	@Schema(description = "訊息方向", example = "RQ")
	@JsonProperty("msgDirection")
	private String msgDirection;

	@Schema(description = "CorrelationId", example = "FCB4A2022123010153000001")
	@NotBlank
	@JsonProperty("correlationId")
	private String correlationId;

	@Schema(description = "MessageId", example = "")
	@NotBlank
	@JsonProperty("messageId")
	private String messageId;

	@Schema(description = "來源端系統ID", example = "ebank")
	@NotBlank
	@JsonProperty("sourceId")
	private String sourceId;

	@NotNull
	@Valid
	@JsonProperty("clientRequest")
	protected T clientRequest;

}
