package net.vmyun.client.controller.goodspassage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import net.vmyun.annotation.SysLog;
import net.vmyun.client.base.BaseController;
import net.vmyun.client.config.VmclientConfig;
import net.vmyun.client.entity.Goods;
import net.vmyun.client.entity.GoodsPassage;
import net.vmyun.client.service.GoodsPassageService;

import net.vmyun.client.service.impl.GoodsPassageServiceImpl;
import net.vmyun.client.slaver.service.SerialService;
import net.vmyun.util.LayerData;
import net.vmyun.util.ResultMsg;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangl on 2017/12/2.
 * todo:
 */
@Controller
@RequestMapping("admin/goods")
public class GoodsPassageController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsPassageController.class);
    @Autowired
    protected GoodsPassageService goodsPassageService;
    @Autowired
    protected VmclientConfig vmclientConfig;
    @Autowired
    protected SerialService serialService;
    ResultMsg resultMsg=null;
    @GetMapping("list")
    @SysLog("跳转商品管理页")
    public String list(ModelMap map){
        Subject user = SecurityUtils.getSubject();
        Object o=user.getPrincipal();
        JSONObject josnObject=(JSONObject)JSONObject.toJSON(o);
        String loginName= (String) josnObject.get("loginName");
        map.put("user",loginName);
        return "admin/goods/goodsPassage";
    }

    @GetMapping("goodsPassageForTesters")
    @SysLog("跳转商品管理页")
    public String goodsPassageForTesters(ModelMap map){
        Subject user = SecurityUtils.getSubject();
        Object o=user.getPrincipal();
        JSONObject josnObject=(JSONObject)JSONObject.toJSON(o);
        String loginName= (String) josnObject.get("loginName");
        map.put("user",loginName);
        return "admin/goodsForTester/goodsPassage";
    }

//    @RequiresPermissions("sys:role:list")
    @PostMapping("list")
    @ResponseBody
    public LayerData<GoodsPassage> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                        @RequestParam(value = "limit",defaultValue = "10")Integer limit){
        LayerData<GoodsPassage> GoodsLayerData = new LayerData<>();
        EntityWrapper<GoodsPassage> GoodsEntityWrapper = new EntityWrapper<>();
        Integer id = vmclientConfig.getId();
        GoodsEntityWrapper.eq("vm_id",id);
        GoodsEntityWrapper.eq("del_flag",0);
        GoodsEntityWrapper.orderBy("number");
        Page<GoodsPassage> goodsPassagePage = goodsPassageService.selectPage(new Page<>(page,limit),GoodsEntityWrapper);
        GoodsLayerData.setCount(goodsPassagePage.getTotal());
        GoodsLayerData.setData(setGoods(goodsPassagePage.getRecords()));
        return GoodsLayerData;
    }

    private List<GoodsPassage> setGoods(List<GoodsPassage> GoodsPassages){
        for(GoodsPassage r : GoodsPassages){
            if(r.getGoodsId() != null && !r.getGoodsId().equals("")){
                Goods goods= goodsPassageService.findGoodsByid(Long.parseLong(r.getGoodsId()));
                if(!StringUtils.isBlank(goods.getName())){
                   r.setGoods(goods);
                }
            }
        }
        return GoodsPassages;
    }
    @PostMapping("queryGoods")
    @ResponseBody
    public List<Goods> queryGoods(){
       List<Goods> GoodsList= goodsPassageService.selectGoodsList();
        return GoodsList;
    }

    @PostMapping("addGoodsPassage")
    @ResponseBody
    public ResultMsg addGoodsPassage( @RequestParam Map<String,Object> reqMap){
        JSONObject josnObject=(JSONObject)JSONObject.toJSON(reqMap);
        Boolean b=goodsPassageService.addGoodsPassage(josnObject,vmclientConfig.getId());
        if (b){
            resultMsg=new ResultMsg("000000","保存更新成功");
        }
      return resultMsg;
    }

    @PostMapping("serialOperation")
    @ResponseBody
    public ResultMsg serialOperation( @RequestParam Map<String,Object> reqMap){
        JSONObject josnObject=(JSONObject)JSONObject.toJSON(reqMap);
        List<GoodsPassage>  list=new ArrayList<>();
        boolean isToArray=isJsonArray((String) josnObject.get("rows"));
        String type=(String) josnObject.get("type");
        GoodsPassage goodsPassage=null;
        if(isToArray){
            JSONArray jsonArray=JSONArray.parseArray((String) josnObject.get("rows"));
            for (int i=0;i<jsonArray.size();i++){
                JSONObject goodsPassageJson=(JSONObject)jsonArray.get(i);
                goodsPassage  =GoodsPassageServiceImpl.setValue(goodsPassageJson);
                goodsPassage.setVmId((String)goodsPassageJson.get("vmId"));
                list.add(goodsPassage);
            }
        }else{
            String  a=(String)josnObject.get("rows");
            JSONObject jsonStr = JSONObject.parseObject(a);
            goodsPassage  =GoodsPassageServiceImpl.setValue(jsonStr);
            if(type!=null&&type.equals("1")){
                goodsPassage.setHeatFlag(true);
            }else if(type!=null&&type.equals("2")){
                goodsPassage.setHeatFlag(false);
            }
            goodsPassage.setVmId((String)josnObject.get("vmId"));
            list.add(goodsPassage);
        }
        String result=serialService.deliverGoodsCmd(list);
        if(result.equals("出货")){
            resultMsg=new ResultMsg("000000","出货成功");
        }
        return resultMsg;
    }

    /**
     * 判断字符串是否可以转化为JSON数组
     * @param content
     * @return
     */
    public static boolean isJsonArray(String content) {
        if(StringUtils.isBlank(content))
            return false;
        StringUtils.isEmpty(content);
        try {
            JSONArray jsonStr = JSONArray.parseArray(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * 判断字符串是否可以转化为json对象
     * @param content
     * @return
     */
    public static boolean isJsonObject(String content) {
        // 此处应该注意，不要使用StringUtils.isEmpty(),因为当content为"  "空格字符串时，JSONObject.parseObject可以解析成功，
        // 实际上，这是没有什么意义的。所以content应该是非空白字符串且不为空，判断是否是JSON数组也是相同的情况。
        if(StringUtils.isBlank(content))
            return false;
        try {
            JSONObject jsonStr = JSONObject.parseObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
