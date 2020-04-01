package com.fc.psi.service.impl;

import com.fc.psi.entity.PsiGoodsEntity;
import com.fc.psi.entity.PsiInvenInfoEntity;
import com.fc.psi.enums.PsiEnum;
import com.fc.psi.repository.PsiGoodsRepository;
import com.fc.psi.repository.PsiInvenInfoRepository;
import com.fc.psi.service.PsiInvenInfoService;
import com.fc.psi.service.PsiTypeService;
import com.fc.psi.util.PsiUtil;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PsiInvenInfoServiceImpl implements PsiInvenInfoService {

    @Autowired
    private PsiTypeService psiTypeService;
    @Autowired
    private PsiGoodsRepository psiGoodsRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PsiInvenInfoRepository psiInvenInfoRepository;

    @Override
    public Map<String,Object> goodsIndexInit() throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(PsiEnum.GOODS_TYPE_CODE.getValue(),psiTypeService.findTypeByPCode(PsiEnum.GOODS_TYPE_CODE.getValue()));
        jsonObject.put(PsiEnum.YN.getValue(),psiTypeService.findTypeByPCode(PsiEnum.YN.getValue()));
        jsonObject.put(PsiEnum.UNIT.getValue(),psiTypeService.findTypeByPCode(PsiEnum.UNIT.getValue()));
        jsonObject.put(PsiEnum.INVEN_INIT_DATA.getValue(),psiGoodsRepository.findAll());
        return PsiUtil.getMap(true,jsonObject);
    }

    @Override
    public Map<String, Object> inventInfoInit(String runFlag) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(PsiEnum.GOODS_TYPE_CODE.getValue(),psiTypeService.findTypeByPCode(PsiEnum.GOODS_TYPE_CODE.getValue()));
        jsonObject.put(PsiEnum.GOODS.getValue(),psiGoodsRepository.findByIsUse(PsiEnum.Y.getValue()));
        jsonObject.put(PsiEnum.UNIT.getValue(),psiTypeService.findTypeByPCode(PsiEnum.UNIT.getValue()));
        jsonObject.put(PsiEnum.BUYER.getValue(),psiTypeService.findTypeByPCode(PsiEnum.BUYER.getValue()));
        Map<String,Object> map = PsiUtil.getMap(true,jsonObject);
        map.put(PsiEnum.INVEN_INIT_DATA.getValue(),findInvenInfoByCondition(null,null,
                null,null,null,runFlag,null,null));
        map.put(PsiEnum.RUN_FLAG.getValue(),psiTypeService.findTypeByPCode(PsiEnum.RUN_FLAG.getValue()));
        return map;
    }

    @Override
    public Map<String, Object> editGoodsInfo(String data) throws Exception {
        PsiGoodsEntity goods = (PsiGoodsEntity)JSONObject.toBean(JSONObject.fromObject(data),PsiGoodsEntity.class);
        Date now = new Date();
        if(StringUtils.isBlank(goods.getId())){//新增
            goods.setCreateDate(now);
            goods.setUpdateDate(now);
            psiGoodsRepository.save(goods);
        }else{//修改
            goods.setUpdateDate(now);
            psiGoodsRepository.saveAndFlush(goods);
        }
        return PsiUtil.getMap(true,"编辑成功!");
    }

    @Override
    public Map<String,Object> deleteGoodsById(String id) throws Exception {
        psiGoodsRepository.deleteById(id);
        return PsiUtil.getMap(true,"OK");
    }

    @Override
    public List<PsiInvenInfoEntity> findInvenInfoByCondition(String typeId, String goodsCode, String goodsName,
           String merchantCode, String merchantName, String runFlag,String startDateStr,String endDateStr) throws Exception {
        StringBuffer sb = new StringBuffer("SELECT II.* FROM t_psi_inven_info II INNER JOIN t_psi_goods GOODS ON II.GOODS_ID=GOODS.ID\n" +
                "INNER JOIN t_psi_type TYPE ON II.MERCHANT_CODE=TYPE.TYPE_CODE WHERE 1=1 ");
        if(StringUtils.isNotBlank(runFlag)){
            sb.append("AND II.RUN_FLAG='"+runFlag+"' ");
        }
        if(StringUtils.isNotBlank(typeId)){
            sb.append("AND GOODS.TYPE_ID='"+typeId+"' ");
        }
        if(StringUtils.isNotBlank(goodsCode)){
            sb.append("AND GOODS.GOODS_CODE LIKE '%"+goodsCode+"%' ");
        }
        if(StringUtils.isNotBlank(goodsName)){
            sb.append("AND GOODS.GOODS_NAME LIKE '%"+goodsName+"%' ");
        }
        if(StringUtils.isNotBlank(merchantCode)){
            sb.append("AND II.MERCHANT_CODE LIKE '%"+merchantCode+"%' ");
        }
        if(StringUtils.isNotBlank(merchantName)){
            sb.append("AND TYPE.MERCHANT_NAME LIKE '%"+merchantName+"%' ");
        }
        if(StringUtils.isNotBlank(startDateStr)){
            sb.append("AND II.CREATE_DATE >= '"+startDateStr+"' ");
        }
        if(StringUtils.isNotBlank(endDateStr)){
            sb.append("AND II.CREATE_DATE <= '"+endDateStr+"' ");
        }
        return jdbcTemplate.query(sb.toString(),new BeanPropertyRowMapper<PsiInvenInfoEntity>(PsiInvenInfoEntity.class));
    }

    @Override
    public Map<String, Object> saveInvenInfo(String data) throws Exception {
        PsiInvenInfoEntity invenInfo = new PsiInvenInfoEntity();
        JSONObject json = JSONObject.fromObject(data);
        if(json.has("id")){
            invenInfo.setId(json.getString("id"));
        }
        if(json.has("goodsId")){
            invenInfo.setGoodsId(json.getString("goodsId"));
        }
        if(json.has("runFlag")){
            invenInfo.setRunFlag(json.getString("runFlag"));
        }
        if(json.has("runNum")){
            invenInfo.setRunNum(Double.valueOf(json.getString("runNum")));
        }
        if(json.has("merchantCode")){
            invenInfo.setMerchantCode(json.getString("merchantCode"));
        }
        if(json.has("goodsUnit")){
            invenInfo.setGoodsUnit(json.getString("goodsUnit"));
        }
        if(json.has("goodsPrice")){
            invenInfo.setGoodsPrice(Double.valueOf(json.getString("goodsPrice")));
        }
        if(json.has("totalAmt")){
            invenInfo.setTotalAmt(Double.valueOf(json.getString("totalAmt")));
        }
        if(json.has("createDate")){
            invenInfo.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").parse(json.getString("createDate")));
        }
        invenInfo.setUpdateDate(new Date());
        if(StringUtils.isNotBlank(invenInfo.getId())){
            //更新
            psiInvenInfoRepository.save(invenInfo);
        }else{
            //新增
            psiInvenInfoRepository.saveAndFlush(invenInfo);
        }
        return PsiUtil.getMap(true,"保存成功！");
    }

    @Override
    public Map<String, Object> deleteInvenInfoById(String id) throws Exception {
        psiInvenInfoRepository.deleteById(id);
        return PsiUtil.getMap(true);
    }


}
