package com.fc.psi.service;

import com.fc.psi.entity.PsiTypeEntity;
import net.sf.json.JSONArray;

import java.util.List;

public interface PsiTypeService {

    /**
     * 编辑类型
     * @param data 数据
     * @return
     * @throws Exception
     */
    public PsiTypeEntity editType(String data) throws Exception;

    /**
     * 查询所有的类型（json格式）
     * @return
     * @throws Exception
     */
    public List<PsiTypeEntity> findType(String pId) throws Exception;

    /**
     *
     * @param pId 父编码
     * @return
     * @throws Exception
     */
    public List<PsiTypeEntity> findTypeByCondition(String pId) throws Exception;

    /**
     * 删除类型
     * @param id 主键
     * @throws Exception
     */
    public void deleteTypeById(String id) throws Exception;

    /**
     * 通过父编码查询子类
     * @param pTypeCode 父编码
     * @return
     * @throws Exception
     */
    public List<PsiTypeEntity> findTypeByPCode(String pTypeCode) throws Exception;
}
