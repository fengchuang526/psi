package com.fc.psi.service.impl;

import com.fc.psi.entity.PsiGoodsEntity;
import com.fc.psi.service.PsiGoodsService;
import com.fc.psi.util.PsiUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PsiGoodsServiceImpl implements PsiGoodsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> goodsIndexSearch(String data) throws Exception {
        PsiGoodsEntity goods = (PsiGoodsEntity) JSONObject.toBean(JSONObject.fromObject(data),PsiGoodsEntity.class);
        StringBuffer sql = new StringBuffer("SELECT * FROM t_psi_goods G WHERE 1=1 ");
        if(StringUtils.isNotBlank(goods.getTypeId())){
            sql.append("AND G.TYPE_ID='"+goods.getTypeId()+"' ");
        }
        if(StringUtils.isNotBlank(goods.getGoodsCode())){
            sql.append("AND G.GOODS_CODE LIKE '%"+goods.getGoodsCode()+"%' ");
        }
        if(StringUtils.isNotBlank(goods.getGoodsName())){
            sql.append("AND G.GOODS_NAME LIKE '%"+goods.getGoodsName()+"%'");
        }
        List<PsiGoodsEntity> goodsList = jdbcTemplate.query(sql.toString(),new BeanPropertyRowMapper<PsiGoodsEntity>(PsiGoodsEntity.class));
        return PsiUtil.getMap(true,goodsList);
    }
}
