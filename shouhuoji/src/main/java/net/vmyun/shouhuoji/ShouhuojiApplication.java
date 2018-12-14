package net.vmyun.shouhuoji;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.vmyun.shouhuoji.dao")
public class ShouhuojiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShouhuojiApplication.class, args);
	}
}
