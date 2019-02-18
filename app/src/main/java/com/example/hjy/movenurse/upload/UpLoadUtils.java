package com.example.hjy.movenurse.upload;

import com.fy.entity.LoginBean;
import com.fy.entity.PatientsBean;
import com.fy.entity.UploadBean;
import com.fy.retrofit.Api;
import com.fy.utils.L;
import com.fy.utils.TimeUtils;
import com.fy.utils.cache.ACache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 上传列表 工具类
 * <p/>Created by fangs on 2017/10/12.
 */
public class UpLoadUtils {

    /**
     * 护理记录 模块 的编辑 保存失败 缓存 方法
     * @param mCache
     * @param patin  病人实体类
     * @param api
     * @param params
     */
    public static void cacheRequest(ACache mCache, PatientsBean patin, String api, Map<String, Object> params){

        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        String time = System.currentTimeMillis() + "";
        String keyApi = null;
        if (api.equals("PSEvaluation")){//压疮 可以缓存多条
            keyApi = loginBean.getUserID() + patin.getPatID() + time + "&" + api;
        } else {
            keyApi = loginBean.getUserID() + patin.getPatID() + "&" + api;
            boolean sss = mCache.remove(keyApi);
            L.e("清楚对应的缓存", keyApi + "-----" + sss);
        }

        mCache.put(keyApi, new UploadBean(time, patin.getPatID(), patin.getPatName(), api, params));//缓存未上传的 请求

        UploadBean keyBean = (UploadBean) mCache.getAsObject(loginBean.getUserID() + "UP_LOAD_LIST_KEY");
        if (null != keyBean) {

            List<String> keyList = keyBean.getKeyList();

            Iterator<String> iterator = keyList.iterator();
            while(iterator.hasNext()){//此循环清除 重复的key
                String key = iterator.next();
                if (key.equals(keyApi)){
                    iterator.remove();   //注意这个地方
                }
            }

            keyList.add(keyApi);
            keyBean.setKeyList(keyList);
        } else {
            List<String> keyList = new ArrayList<>();
            keyList.add(keyApi);
            keyBean = new UploadBean(keyList);
        }

        mCache.put(loginBean.getUserID() + "UP_LOAD_LIST_KEY", keyBean);//缓存所有 未上传的请求的 key列表
    }

    //获取 缓存的 未上传的 请求
    public static List<UploadBean> getData(ACache mCache){
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");

        List<UploadBean> data = new ArrayList<>();

        UploadBean uploadBean = (UploadBean) mCache.getAsObject(loginBean.getUserID() + "UP_LOAD_LIST_KEY");
        if (null != uploadBean) {
            List<String> keyList = uploadBean.getKeyList();

            for (String key : keyList) {
                UploadBean uploadParam = (UploadBean) mCache.getAsObject(key);
                if (null != uploadParam)data.add(uploadParam);
            }
        }

        return data;
    }

    //清除 缓存的 未上传的 请求
    public static void clienUPload(ACache mCache){
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");

        List<UploadBean> uploadBeanList = UpLoadUtils.getData(mCache);
        for (UploadBean bean : uploadBeanList) {
            mCache.remove(loginBean.getUserID() + bean.getPatID() + "&" + bean.getApi());
        }

        mCache.remove(loginBean.getUserID() + "UP_LOAD_LIST_KEY");
    }

    /**
     * 获取 api的功能描述
     * @param api
     * @return
     */
    public static String getFunDesc(String api){
        String funDesc = "";
        switch (api){
            case Api.FirstNursingRecord:
                funDesc = "首次护理记录";
                break;
            case Api.FirstChildNursingRecord2:
                funDesc = "儿童首次护理";
                break;
            case Api.PSEvaluation:
                funDesc = "压疮风险评估";
                break;
            case Api.AnesthesiaVisit1:
                funDesc = "麻醉术前访视";
                break;
            case Api.AnesthesiaVisit2:
                funDesc = "麻醉术后访视";
                break;
            case Api.NursingRecodes3:
                funDesc = "急诊抢救记录";
                break;
            case Api.NursingRecodes1:
                funDesc = "护理记录单";
                break;
            case Api.ObstetricsRecodes2:
                funDesc = "产科产后记录";
                break;
            case Api.ObstetricsRecodes3:
                funDesc = "产后观察记录";
                break;
            case Api.ObstetricsRecodes4:
                funDesc = "产后观察记录";
                break;
            case Api.NeonatalCareRecodes1:
                funDesc = "一般新生儿护理";
                break;
            case Api.NeonatalCareRecodes2:
                funDesc = "新生儿护理记录";
                break;
            case Api.NCAssistanceRecodes:
                funDesc = "新生儿辅助呼吸";
                break;
        }

        return funDesc;
    }
}
