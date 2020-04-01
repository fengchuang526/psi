package com.fc.psi.controller;

import com.fc.psi.entity.PsiInvenInfoEntity;
import com.fc.psi.enums.PsiEnum;
import com.fc.psi.service.PsiInvenInfoService;
import com.fc.psi.util.PsiUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/psi/invenInfo/")
public class PsiInvenInfoController {

    @Autowired
    private PsiInvenInfoService psiInvenInfoService;

    /**
     * 流水主界面数据初始化
     * @param runFlag 流水标识
     * @return
     */
    @RequestMapping("/invenInfoInit.action")
    public Map<String,Object> invenInfoInit(String runFlag){
        try{
            return psiInvenInfoService.inventInfoInit(runFlag);
        }catch(Exception e){
            e.printStackTrace();
            return PsiUtil.getMap(false,e.getMessage());
        }
    }

    /**
     * 根据条件查询数据
     * @param data 查询条件
     * @return
     */
    @RequestMapping("/findInvenInfoCondition.action")
    public Map<String,Object> findInvenInfoByCondition(String data){
        try{
            JSONObject json = JSONObject.fromObject(data);
            String typeId = null;
            String goodsCode = null;
            String goodsName = null;
            String runFlag = null;
            String merchantCode = null;
            String merchantName = null;
            String[] dataRange = new String[2];
            if(json.has("typeId")){
                typeId = json.getString("typeId");
            }
            if(json.has("goodsCode")){
                goodsCode = json.getString("goodsCode");
            }
            if(json.has("goodsName")){
                goodsName = json.getString("goodsName");
            }
            if(json.has("runFlag")){
                runFlag = json.getString("runFlag");
            }
            if(json.has("merchantCode")){
                merchantCode = json.getString("merchantCode");
            }
            if(json.has("merchantName")){
                merchantName = json.getString("merchantName");
            }
            if(json.has("dataRange")){
                Object obj = json.get("dataRange");
                if(obj != null){
                    JSONArray arr = (JSONArray)obj;
                    dataRange[0] = arr.getString(0);
                    dataRange[1] = arr.getString(1);
                }

            }
            Map<String,Object> map = PsiUtil.getMap(true);
            map.put(PsiEnum.INVEN_INIT_DATA.getValue(),psiInvenInfoService.findInvenInfoByCondition(typeId,goodsCode,
                    goodsName,merchantCode,merchantName,runFlag,dataRange[0],dataRange[1]));
            return map;
        }catch(Exception e){
            e.printStackTrace();
            return PsiUtil.getMap(false,e.getMessage());
        }
    }

    /**
     * 保存流水
     * @param data 信息
     * @return
     */
    @RequestMapping("/saveInvenInfo.action")
    public Map<String,Object> saveInvenInfo(String data){
        try{
            return psiInvenInfoService.saveInvenInfo(data);
        }catch(Exception e){
            e.printStackTrace();
            return PsiUtil.getMap(false,e.getMessage());
        }
    }

    /**
     * 删除流水
     * @param id 流水ID
     * @return
     */
    @RequestMapping("/deleteInvenInfo.action")
    public Map<String,Object> deleteInvenInfo(String id){
        try{
            return psiInvenInfoService.deleteInvenInfoById(id);
        }catch(Exception e){
            e.printStackTrace();
            return PsiUtil.getMap(false,e.getMessage());
        }
    }


}
