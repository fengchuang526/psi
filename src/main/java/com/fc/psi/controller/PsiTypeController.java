package com.fc.psi.controller;

import com.fc.psi.entity.PsiTypeEntity;
import com.fc.psi.service.PsiTypeService;
import com.fc.psi.util.PsiUtil;
import com.google.gson.JsonArray;
import net.sf.json.JSONArray;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/psi/type")
public class PsiTypeController {

    @Autowired
    PsiTypeService psiTypeService;

    /**
     * 编辑类型
     * @param data 数据
     * @return
     */
    @RequestMapping("/editType.action")
    public Map<String,Object> editType(String data){
        try{
            psiTypeService.editType(data);
        }catch (Exception e){
            e.printStackTrace();
            return PsiUtil.getMap(false,e.getMessage());
        }
        return PsiUtil.getMap(true,"编辑成功!");
    }

    /**
     * 查询所有的节点
     * @return
     */
    @RequestMapping("/findType.action")
    public List<PsiTypeEntity> findTypeByPId(String pId){
        try{
            return psiTypeService.findType(pId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除类型
     * @param id 主键
     */
    @RequestMapping("/deleteType.action")
    public void deleteById(String id){
        try{
            psiTypeService.deleteTypeById(id);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
