package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.factory.Mappers;
import tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.in.rest.mapper.MainframeControllerMapper;
import tw.com.firstbank.fcbcore.com.acl.mainframe.application.in.message.api.MainframeMessageRequestCommand;
import tw.com.firstbank.fcbcore.fcbframework.core.adapter.in.rest.api.RequestBody;


@Getter
@Setter
@ToString
public class MessageRequest implements RequestBody {

	private static final MainframeControllerMapper mapper =
			Mappers.getMapper(MainframeControllerMapper.class);

	@Schema(description = "client request body", example = "")
	private RequestBody txRq;

	@Override
	public MainframeMessageRequestCommand toCommand() {
		return null;
	}

}
