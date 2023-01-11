package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.message.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.message.api.MessageListenerRequest;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.message.api.MessageListenerResponse;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.MessageListenerRequestCommand;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.MessageListenerResponseCommand;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MessageListenerMapper {

	MessageListenerRequestCommand toEBankMessageRequestCommand(MessageListenerRequest source);

	MessageListenerResponse toEBankMessageResponse(MessageListenerResponseCommand source);
}
