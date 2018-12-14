package net.vmyun.shouhuoji.slaver.service.impl;

import net.vmyun.shouhuoji.config.VemConfig;
import net.vmyun.shouhuoji.entity.GoodsPassage;
import net.vmyun.shouhuoji.entity.Order;
import net.vmyun.shouhuoji.slaver.port.SerialPort;
import net.vmyun.shouhuoji.slaver.service.SerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/10/2.
 * 售货机与控制系统通讯协议：
 * 1 、 采用串口或者485通讯，通讯口配置为：9600，n，8,1
 * 数据均采用16进制发送
 * 2 、 售货机发送信号：
 * （1）客户点单完毕收到付款发送 “准备出货信号”，其信号格式如下：
 *     FF 0X（其中X为货层信息）0Y（其中Y为货道信息）01（准备出货,加热） EE。
 *     FF 0X（其中X为货层信息）0Y（其中Y为货道信息）02（准备出货,不加热） EE。
 * （2）售货机出货完毕后发送：：  FE  DD  C1  03  EE；
 * （3）售货机如果出货失败发送： FE  DD  C2  04  EE；
 * （4）我方控制系统收到FF 数据后，即开始进行加热准备，并向售货机发送FF指令。
 * （5）加热完成后，控制系统向售货机系统发送EE完成指令，最后完成出货。
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SerialServiceImpl implements SerialService {
    @Autowired
    protected VemConfig vmclientConfig;
    @Override
    public String deliverGoodsCmd(List<GoodsPassage> goodsPassages) {

        for(GoodsPassage goodsPassage :goodsPassages){
            //“准备出货信号”，其信号格式如下：
            //   FF 0X（其中X为货层信息）0Y（其中Y为货道信息）01（准备出货,加热） EE。
            //    FF 0X（其中X为货层信息）0Y（其中Y为货道信息）02（准备出货,不加热） EE。
            StringBuffer command = new StringBuffer("FF")
                    .append(" 0").append(String.format("%01x", goodsPassage.getVmRow()).toUpperCase())
                    .append(" 0").append(String.format("%01x", goodsPassage.getVmColumn()).toUpperCase());
            if(goodsPassage.getHeatFlag()){
                command.append(" 01");
            }else{
                command.append(" 02");
            }
            command.append(" EE");
            SerialPort goodPort = new SerialPort(false);
            String feelback = null;
            if(goodPort.open(vmclientConfig.getGoodsCom())){
                feelback = goodPort.getFeedbackAfterSendMessage(command.toString());
            }
            if("FE  DD  C2  04  EE".equals(feelback)){
                return "出货失败";
            }
        }
        return "出货";
    }

    @Override
    public String cashReceiptCmd(Order order){
        return "成功";

    };

    public static void main(String[] args) {
        System.out.println(String.format("%01x", 12).toUpperCase());
        System.out.println(String.format("%01x", 16).toUpperCase());
        System.out.println(String.format("%01x", 17).toUpperCase());
        System.out.println(String.format("%01x", 0).toUpperCase());
    }
}
