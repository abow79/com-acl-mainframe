package tw.com.firstbank.fcbcore.com.acl.mainframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "tw.com.firstbank.fcbcore")
@EnableFeignClients
@EnableJms
public class MainframeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainframeServiceApplication.class, args);
	}
}
