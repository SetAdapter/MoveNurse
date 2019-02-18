package com.fy.retrofit;

/**
 * api
 * Created by fangs on 2017/5/15.
 */
public interface Api {

//    String BASE_URL = "http://192.168.100.30:8099/";//服务器 的 根目录地址
    String BASE_URL = "http://10.2.21.123:8099/";//服务器 的 根目录地址
//    String BASE_URL = "http://172.16.0.82:8099/";//服务器 的 根目录地址

    String DES3_HOSCODE = "lK1cU9VQMNM=";// 配置名称

    String login = "DocAndNurse";//用户登录
    String getZyPatient = "getZyPatient";//获取住院患者信息
    String LogOut = "LogOut";//退出登录

    String GetFirstNursing = "GetFirstNursing";//保存患者首次护理记录单
    String FirstNursingRecord = "FirstNursingRecord";//查询患者首次护理记录单
    String FirstChildNursingRecord2 = "FirstNursingRecord2";//保存儿童首次护理记录单
    String PSEvaluation = "PSEvaluation";//保存患者压疮风险评估单
    String getDicts1 = "getDicts1";//2.3.压疮身体部位正面	获取字典信息
    String AnesthesiaVisit1 = "AnesthesiaVisit1";//保存患者麻醉访视记录 1.麻醉前
    String AnesthesiaVisit2 = "AnesthesiaVisit2";//保存患者麻醉访视记录 2.麻醉后
    String NursingRecodes1 = "NursingRecodes1";//保存及获取患者内外科护理记录列表
    String ObstetricsRecodes2 = "ObstetricsRecodes2";//产前待产记录(呼吸)
    String ObstetricsRecodes3 = "ObstetricsRecodes3";//产后记录
    String ObstetricsRecodes4 = "ObstetricsRecodes4";//产后观察记录
    String NursingRecodes3 = "NursingRecodes3";//急救记录单
    String NeonatalCareRecodes1 = "NeonatalCareRecodes1";//1一般护理
    String NeonatalCareRecodes2 = "NeonatalCareRecodes2";//2新生儿护理
    String NCAssistanceRecodes = "NCAssistanceRecodes";//保存及获取新生儿科呼吸辅助护理记录列表
    String getDicts10044 = "getDicts10044";//2.3.100044	获取 护理记录单
    String getDicts10045 = "getDicts10045";//2.3.100045	获取 产前待产记录
    String getDicts10046 = "getDicts10046";//2.3.100046	获取 产科产后记录
    String getDicts10047 = "getDicts10047";//2.3.100047	获取 产后观察记录
    String getDicts10048 = "getDicts10048";//2.3.100048	获取 急诊抢救记录
    String getDicts10049 = "getDicts10049";//2.3.100049	获取 新生儿辅助呼吸
    String getDicts10050 = "getDicts10050";//2.3.100050	一般新生儿护理
    String getDicts10051 = "getDicts10051";//2.3.100051	新生儿护理记录
    String getDicts10005 = "getDicts10005";//2.3.10005	获取住院患者（家属）拒绝翻身知情同意书
    String getDicts10004 = "getDicts10004";//2.3.10004	获取约束带使用知情同意书
    String getDicts10003 = "getDicts10003";//2.3.10003	获取住院患者防跌倒/坠床知情同意书
    String getDicts10002 = "getDicts10002";//2.3.10002	使用热水袋注意安全告知书
    String getDicts10001 = "getDicts10001";//2.3.10001	出院健康指导
    String getDicts10053 = "getDicts10053";//2.3.10001	住院费用分类
    String getDicts = "getDicts";//2.3.	获取字典信息

    String GetBeds = "GetBeds";//获取病房管理/空床列表信息
    String ChangeBed = "ChangeBed";//2.12.	修改患者科室/床位信息
    String GetPatOrders = "GetPatOrders";//2.13.	获取患者医嘱信息 (现行医嘱)
    String GetPatOrders2 = "GetPatOrders2";//2.13.	获取患者医嘱信息 (已执行医嘱)
    String ExcutePatOrder = "ExcutePatOrder";//2.14.	执行患者医嘱
    String GetPainbBillDetails = "GetPainbBillDetails";//2.15.	获取患者退药退费信息
    String GetPainbBillDetails_Ex = "GetPainbBillDetails_Ex";//2.15.	获取患者退药退费信息
    String GetPainbBillDetails_Da = "GetPainbBillDetails_Da";//2.15.	获取患者退药退费信息
    String ExcutePainbBillDetail = "ExcutePainbBillDetail";//2.16.	执行患者退药退费
    String uploadPostFile = "UploadPostFile";//多图片上传
    String SignInformedConsent = "SignInformedConsent";//知情同意书预览及保存


}
