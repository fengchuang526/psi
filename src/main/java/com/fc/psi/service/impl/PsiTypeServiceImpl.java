package com.fc.psi.service.impl;

import com.fc.psi.entity.PsiTypeEntity;
import com.fc.psi.repository.PsiTypeRepository;
import com.fc.psi.service.PsiTypeService;
import com.google.gson.Gson;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PsiTypeServiceImpl implements PsiTypeService {

    @Autowired
    PsiTypeRepository psiTypeRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<PsiTypeEntity> findType(String pId) throws Exception {
        List<PsiTypeEntity> parentList = findTypeByCondition(pId);
        return parentList;
    }

    @Override
    public List<PsiTypeEntity> findTypeByCondition(String pId) throws Exception {
        String sql = null;
        if(StringUtils.isBlank(pId)){
            sql = "SELECT T.* FROM T_PSI_TYPE T WHERE T.P_ID=''";
        }else{
            sql = "SELECT T.* FROM T_PSI_TYPE T WHERE T.P_ID='"+pId+"'";
        }
        List<PsiTypeEntity> parentList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<PsiTypeEntity>(PsiTypeEntity.class));
        for(PsiTypeEntity type : parentList){
            sql = "SELECT T.* FROM T_PSI_TYPE T WHERE T.P_ID='"+type.getId()+"'";
            List<PsiTypeEntity> childList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<PsiTypeEntity>(PsiTypeEntity.class));
            if(CollectionUtils.isNotEmpty(childList)){
                type.setChildren(childList);
            }
        }
        return parentList;
    }

    @Override
    public void deleteTypeById(String id) throws Exception {
        if(StringUtils.isBlank(id)){
            return;
        }
        List<PsiTypeEntity> childTypeList = findType(id);
        if(CollectionUtils.isNotEmpty(childTypeList)){
            jdbcTemplate.execute("DELETE FROM T_PSI_TYPE WHERE P_ID='"+id+"'");
        }
        jdbcTemplate.execute("DELETE FROM T_PSI_TYPE WHERE ID='"+id+"'");
    }

    @Override
    public List<PsiTypeEntity> findTypeByPCode(String pTypeCode) throws Exception {
        if(StringUtils.isBlank(pTypeCode)){
            return null;
        }
        String sql = "SELECT CT.* FROM T_PSI_TYPE PT INNER JOIN T_PSI_TYPE CT ON PT.ID=CT.P_ID WHERE PT.TYPE_CODE='"+pTypeCode+"'";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<PsiTypeEntity>(PsiTypeEntity.class));
    }

    @Override
    public PsiTypeEntity editType(String data) throws Exception {
        PsiTypeEntity type = new Gson().fromJson(data,PsiTypeEntity.class);
        Date nowDate = new Date();
        if(StringUtils.isBlank(type.getId())){//新增
            type.setCreateDate(nowDate);
            type.setUpdateDate(nowDate);
            return psiTypeRepository.save(type);
        }else{
            PsiTypeEntity dbEntity = psiTypeRepository.getOne(type.getId());
            dbEntity.setTypeName(type.getTypeName());
            dbEntity.setTypeDesc(type.getTypeDesc());
            dbEntity.setUpdateDate(nowDate);
            return psiTypeRepository.save(dbEntity);
        }
    }
}