package tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MessageUseCaseMapper {


}
