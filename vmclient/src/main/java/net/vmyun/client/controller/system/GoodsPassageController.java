package net.vmyun.client.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import net.vmyun.annotation.SysLog;
import net.vmyun.client.base.BaseController;
import net.vmyun.client.config.VmclientConfig;
import net.vmyun.client.entity.Goods;
import net.vmyun.client.entity.GoodsPassage;
import net.vmyun.client.service.GoodsPassageService;

import net.vmyun.util.LayerData;
import net.vmyun.util.ResultMsg;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
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

    @GetMapping("list")
    @SysLog("跳转商品管理页")
    public String list(ModelMap map){
        return "admin/goods/goodsPassage";
    }

    @RequiresPermissions("sys:role:list")
    @PostMapping("list")
    @ResponseBody
    public LayerData<GoodsPassage> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                        @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                        ServletRequest request){
        LayerData<GoodsPassage> GoodsLayerData = new LayerData<>();
        EntityWrapper<GoodsPassage> GoodsEntityWrapper = new EntityWrapper<>();
        Integer id = vmclientConfig.getId();
        GoodsEntityWrapper.eq("vm_id",id);
        GoodsEntityWrapper.eq("del_flag",0);
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
        ResultMsg resultMsg=null;
        if (b){
            resultMsg=new ResultMsg("000000","保存更新成功");
        }
      return resultMsg;
    }

}
