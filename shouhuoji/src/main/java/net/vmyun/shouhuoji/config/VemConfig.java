package net.vmyun.shouhuoji.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/9/24.
 */
@Component
@ConfigurationProperties(prefix="vemclient")
@PropertySource("classpath:vemclient.properties")
public class VemConfig {

    private Integer id;

    private String goodsCom;
    private String cashCom;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsCom() {
        return goodsCom;
    }

    public void setGoodsCom(String goodsCom) {
        this.goodsCom = goodsCom;
    }

    public String getCashCom() {
        return cashCom;
    }

    public void setCashCom(String cashCom) {
        this.cashCom = cashCom;
    }
}
