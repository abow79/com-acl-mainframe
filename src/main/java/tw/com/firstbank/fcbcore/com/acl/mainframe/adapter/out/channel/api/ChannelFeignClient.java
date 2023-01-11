package tw.com.firstbank.fcbcore.com.acl.mainframe.adapter.out.channel.api;

import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import tw.com.firstbank.fcbcore.fcbframework.adapters.rest.adapter.out.rest.api.FeignHeaderInterceptor;
import tw.com.firstbank.fcbcore.fcbframework.adapters.rest.adapter.out.rest.api.IFeignClient;
import tw.com.firstbank.fcbcore.fcbframework.adapters.rest.spring.config.FeignExternalConfig;

@Component
@FeignClient(value = "channel", url = "${feign.client.config.channel.baseUrl}",
		configuration = {FeignExternalConfig.class, FeignHeaderInterceptor.class})
public interface ChannelFeignClient extends IFeignClient {

	@RequestMapping(value = "/api/fx/ir/e001/a/v1")
	MessageResponseWrapper<E001AResponse> e001a(@RequestHeader Map<String, Object> headers,
			@RequestBody MessageRequestWrapper<E001ARequest> json);
}
