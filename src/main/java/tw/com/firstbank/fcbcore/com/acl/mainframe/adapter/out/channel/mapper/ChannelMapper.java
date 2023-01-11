package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.channel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.channel.api.E001ARequest;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.channel.api.E001AResponse;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.channel.api.MessageRequestWrapper;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.channel.api.MessageResponseWrapper;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api.E001AOuterRequest;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api.E001AOuterResponse;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api.TxRqWrapperOuterRequest;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.out.channel.api.TxRsWrapperOuterResponse;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ChannelMapper {

	@Mapping(source = "source.header.txID", target = "txId")
	@Mapping(source = "source.header.systemKey", target = "systemKey")
	@Mapping(source = "source.header.txSeqNo", target = "txSeqNo")
	@Mapping(source = "source.header.msgSeqNo", target = "msgSeqNo")
	@Mapping(source = "source.header.cltTimeStamp", target = "cltTimeStamp")
	@Mapping(source = "source.header.custID", target = "custId")
	@Mapping(source = "source.header.userID", target = "userId")
	@Mapping(source = "source.header.acctNo", target = "acctNo")
	@Mapping(source = "source.header.msgDirection", target = "msgDirection")
	@Mapping(source = "source.txRq", target = "clientRequest")
	@Mapping(source = "correlationId", target = "correlationId")
	@Mapping(source = "messageId", target = "messageId")
	MessageRequestWrapper<E001ARequest> toEBankRequestWrapper(
			TxRqWrapperOuterRequest<E001AOuterRequest> source, String correlationId, String messageId);

	@Mapping(source = "source.txId", target = "header.txID")
	@Mapping(source = "source.systemKey", target = "header.systemKey")
	@Mapping(source = "source.txSeqNo", target = "header.txSeqNo")
	@Mapping(source = "source.msgSeqNo", target = "header.msgSeqNo")
	@Mapping(source = "source.cltTimeStamp", target = "header.cltTimeStamp")
	@Mapping(source = "source.msgDirection", target = "header.msgDirection")
	@Mapping(source = "source.clientResponse", target = "txRs")
	TxRsWrapperOuterResponse<E001AOuterResponse> toTxRsWrapper(
			MessageResponseWrapper<E001AResponse> source);
}
