package net.vmyun.cloud;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.vmyun.cloud.dao")
public class VmcloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(VmcloudApplication.class, args);
	}
}
