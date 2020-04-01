package com.fc.psi.util;

import java.util.HashMap;
import java.util.Map;

public class PsiUtil {

    /**
     * 获取请求返回的map
     * @param flag 标识
     * @param data 数据
     * @return
     */
    public static Map<String,Object> getMap (Boolean flag,Object data){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("flag",flag);
        map.put("data",data);
        return map;
    }

    /**
     * 获取请求返回的map
     * @param flag 标识
     * @return
     */
    public static Map<String,Object> getMap (Boolean flag){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("flag",flag);
        return map;
    }


}
