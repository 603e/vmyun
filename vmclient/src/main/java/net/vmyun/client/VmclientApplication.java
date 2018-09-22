package net.vmyun.client;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.vmyun.client.dao")
public class VmclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(VmclientApplication.class, args);
	}
}
