package net.vmyun.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/9/24.
 */
@Component
@ConfigurationProperties(prefix="vmclient")
@PropertySource("classpath:vmclient.properties")
public class VmclientConfig {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
