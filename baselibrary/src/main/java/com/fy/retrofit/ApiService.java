package com.fy.retrofit;

import com.fy.entity.AnesthesiaPostoperationBean;
import com.fy.entity.AnesthesiaPreoperativeBean;
import com.fy.entity.AntenatalExpectant;
import com.fy.entity.AntenatalRecordBean;
import com.fy.entity.BedBean;
import com.fy.entity.ChangeBedBean;
import com.fy.entity.ChildFirstNurseBean;
import com.fy.entity.DailyListBean;
import com.fy.entity.DepartBean;
import com.fy.entity.DictTypeBean;
import com.fy.entity.DoctorInfoBean;
import com.fy.entity.DoctorInfoOldBean;
import com.fy.entity.EmergencyRecordBean;
import com.fy.entity.ExpenseListBean;
import com.fy.entity.FirstNurseBean;
import com.fy.entity.HealthGuidancesBean;
import com.fy.entity.ImagerBean;
import com.fy.entity.LogOut;
import com.fy.entity.LoginBean;
import com.fy.entity.NewBornNurseRecordBean;
import com.fy.entity.NewBornRecordBean;
import com.fy.entity.NoDataBean;
import com.fy.entity.NursingRecordBean;
import com.fy.entity.ObstetricalPostpartum;
import com.fy.entity.PainbBillDetailsBean;
import com.fy.entity.PatientsBean;
import com.fy.entity.PostpartumBean;
import com.fy.entity.PressureSoreBean;
import com.fy.entity.HealthGuidanceBean;
import com.fy.entity.RespiratorAuxiliary;
import com.fy.entity.RestraintStrapBean;
import com.fy.entity.SignInformedBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

/**
 * 请求 服务
 * email：fy310518@163.com
 * Created by fangs on 2017/8/28.
 */
public interface ApiService {

    /**
     * 登录
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydys/DocAndNurse")
    Observable<BeanModule<ArrayList<LoginBean>>> login(@Body Map<String, Object> options);

    /**
     * 获取住院患者信息
     * @param options
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/getZyPatient")
    Observable<BeanModule<ArrayList<PatientsBean>>> getZyPatient(@QueryMap Map<String, Object> options);

    /**
     * 退出登录
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydys/LogOut")
    Observable<BeanModule<ArrayList<LogOut>>> LogOut(@Body Map<String, Object> options);
    /**
     * 查询患者首次护理记录单
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydys/GetFirstNursing")
    Observable<BeanModule<ArrayList<FirstNurseBean>>> GetFirstNursingRecord(@QueryMap Map<String, Object> options);
    /**
     * 保存患者首次护理记录单
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydhl/FirstNursingRecord")
    Observable<BeanModule<ArrayList<FirstNurseBean>>> FirstNursingRecord(@Body Map<String, Object> options);

    /**
     * 保存儿童首次护理记录单
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydhl/FirstNursingRecord")
    Observable<BeanModule<ArrayList<ChildFirstNurseBean>>> ChildFirstNursingRecord(@Body Map<String, Object> options);

    /**
     * 保存患者麻醉访视记录 1麻醉前
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydhl/AnesthesiaVisit")
    Observable<BeanModule<ArrayList<AnesthesiaPreoperativeBean>>> AnesthesiaVisit1(@Body Map<String, Object> options);

    /**
     * 保存患者麻醉访视记录  2麻醉后
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydhl/AnesthesiaVisit")
    Observable<BeanModule<ArrayList<AnesthesiaPostoperationBean>>> AnesthesiaVisit2(@Body Map<String, Object> options);

    /**
     * 保存及获取患者内外科护理记录列表
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydhl/NursingRecodes")
    Observable<BeanModule<ArrayList<NursingRecordBean>>> NursingRecodes(@Body Map<String, Object> options);

    /**
     * 保存及获取急救记录列表
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydhl/NursingRecodes")
    Observable<BeanModule<ArrayList<EmergencyRecordBean>>> NursingRecodes3(@Body Map<String, Object> options);

    /**
     * 保存患者压疮风险评估单
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydhl/PSEvaluation")
    Observable<BeanModule<ArrayList<PressureSoreBean>>> PSEvaluation(@Body Map<String, Object> options);


    /**
     * 产前待产记录[呼吸]
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydhl/ObstetricsRecodes")
    Observable<BeanModule<ArrayList<AntenatalRecordBean>>> ObstetricsRecodes2(@Body Map<String, Object> options);

    /**
     * 产科产后记录单
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydhl/ObstetricsRecodes")
    Observable<BeanModule<ArrayList<ObstetricalPostpartum>>> ObstetricalPostpartum(@Body Map<String, Object> options);

    /**
     * 产科产后记录单
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydhl/ObstetricsRecodes")
    Observable<BeanModule<ArrayList<PostpartumBean>>> ObstetricalPostpartum4(@Body Map<String, Object> options);

    /**
     * 一般护理记录单
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydhl/NeonatalCareRecodes")
    Observable<BeanModule<ArrayList<NewBornRecordBean>>> NeonatalCareRecodes1(@Body Map<String, Object> options);

    /**
     * 新生儿护理记录单
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydhl/NeonatalCareRecodes")
    Observable<BeanModule<ArrayList<NewBornNurseRecordBean>>> NeonatalCareRecodes2(@Body Map<String, Object> options);

    /**
     * 保存及获取新生儿科呼吸辅助护理记录列表
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydhl/NCAssistanceRecodes")
    Observable<BeanModule<ArrayList<RespiratorAuxiliary>>> NCAssistanceRecodes(@Body Map<String, Object> options);

    /**
     * 获取病房管理信息
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/GetBeds")
    Observable<BeanModule<ArrayList<BedBean>>> GetBeds(@QueryMap Map<String, Object> options);

    /**
     * 获取病房管理信息
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/getDicts")
    Observable<BeanModule<ArrayList<DepartBean>>> getDicts(@QueryMap Map<String, Object> options);

    /**
     * 获取病房管理信息 10007
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/getDicts")
    Observable<BeanModule<ArrayList<HealthGuidanceBean>>> getDicts1(@QueryMap Map<String, Object> options);

    /**
     * 获取病房管理信息 getDicts10001 出院健康指导
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/getDicts")
    Observable<BeanModule<ArrayList<HealthGuidancesBean>>> getDicts10001(@QueryMap Map<String, Object> options);
    /**
     * 获取病房管理信息 getDicts10002 使用热水袋注意安全告知书
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/getDicts")
    Observable<BeanModule<ArrayList<HealthGuidanceBean>>> getDicts10002(@QueryMap Map<String, Object> options);

    /**
     * 获取病房管理信息 getDicts10003 坠床
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/getDicts")
    Observable<BeanModule<ArrayList<HealthGuidanceBean>>> getDicts10003(@QueryMap Map<String, Object> options);

    /**
    /**
     * 获取病房管理信息 getDicts10004 约束带
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/getDicts")
    Observable<BeanModule<ArrayList<RestraintStrapBean>>> getDicts10004(@QueryMap Map<String, Object> options);

    /**
     * 获取病房管理信息 getDicts10005 住院患者（家属）拒绝翻身知情同意书
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/getDicts")
    Observable<BeanModule<ArrayList<HealthGuidanceBean>>> getDicts10005(@QueryMap Map<String, Object> options);
    /**
     * 获取产前待产记录 getDicts10044
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/getDicts")
    Observable<BeanModule<ArrayList<AntenatalExpectant>>> getDicts10044(@QueryMap Map<String, Object> options);
    /**
     * 获取产前待产记录 getDicts10045
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/getDicts")
    Observable<BeanModule<ArrayList<AntenatalExpectant>>> getDicts10045(@QueryMap Map<String, Object> options);
    /**
     * 获取产前待产记录 getDicts10046
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/getDicts")
    Observable<BeanModule<ArrayList<AntenatalExpectant>>> getDicts10046(@QueryMap Map<String, Object> options);
    /**
     * 获取产前待产记录 getDicts10047
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/getDicts")
    Observable<BeanModule<ArrayList<AntenatalExpectant>>> getDicts10047(@QueryMap Map<String, Object> options);
    /**
     * 获取产前待产记录 getDicts10048
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/getDicts")
    Observable<BeanModule<ArrayList<AntenatalExpectant>>> getDicts10048(@QueryMap Map<String, Object> options);
    /**
     * 获取产前待产记录 getDicts10049
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/getDicts")
    Observable<BeanModule<ArrayList<AntenatalExpectant>>> getDicts10049(@QueryMap Map<String, Object> options);
    /**
     * 获取产前待产记录 getDicts10050
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/getDicts")
    Observable<BeanModule<ArrayList<AntenatalExpectant>>> getDicts10050(@QueryMap Map<String, Object> options);
    /**
     * 获取产前待产记录 getDicts10051
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/getDicts")
    Observable<BeanModule<ArrayList<AntenatalExpectant>>> getDicts10051(@QueryMap Map<String, Object> options);
    /**
     * 住院费用分类 getDicts10053
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/getDicts")
    Observable<BeanModule<ArrayList<DictTypeBean>>> getDicts10053(@QueryMap Map<String, Object> options);

    /**
     * 获取病房管理信息
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydhl/ChangeBed")
    Observable<BeanModule<ArrayList<ChangeBedBean>>> ChangeBed(@Body Map<String, Object> options);

    /**
     * 2.13.	获取患者医嘱信息
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/GetPatOrders")
    Observable<BeanModule<ArrayList<DoctorInfoBean>>> GetPatOrders(@QueryMap Map<String, Object> options);

    /**
     * 2.13.	获取患者医嘱信息
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/GetPatOrders")
    Observable<BeanModule<ArrayList<DoctorInfoOldBean>>> GetPatOrders2(@QueryMap Map<String, Object> options);

    /**
     * 2.14.	执行患者医嘱
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydhl/ExcutePatOrder")
    Observable<BeanModule<ArrayList<NoDataBean>>> ExcutePatOrder(@Body Map<String, Object> options);

    /**
     * 2.15.	获取患者退药退费信息
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/GetPainbBillDetails")
    Observable<BeanModule<ArrayList<PainbBillDetailsBean.ResultDataBean>>> GetPainbBillDetails(@QueryMap Map<String, Object> options);

    /**
     * 2.15.	获取患者退药退费-费用清单信息
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/GetPainbBillDetails")
    Observable<BeanModule<ArrayList<ExpenseListBean.ResultDataBean>>> GetPainbBillDetails_Ex(@QueryMap Map<String, Object> options);
    /**
     * 2.15.	获取患者退药退费-日清单信息
     */
    @Streaming
    @Headers({"url_name:user"})
    @GET("/app/ydhl/GetPainbBillDetails")
    Observable<BeanModule<ArrayList<DailyListBean.ResultDataBean>>> GetPainbBillDetails_Da(@QueryMap Map<String, Object> options);

    /**
     * 2.16.	执行患者退药退费
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydhl/ExcutePainbBillDetail")
    Observable<BeanModule<ArrayList<NoDataBean>>> ExcutePainbBillDetail(@Body Map<String, Object> options);
    /**
     * 2.19.	知情同意书预览及保存
     */
    @Streaming
    @Headers({"url_name:user"})
    @POST("/app/ydhl/SignInformedConsent")
    Observable<BeanModule<ArrayList<SignInformedBean>>> SignInformedConsent(@Body Map<String, Object> options);


    /**
     * 多图片上传
     * @param token
     * @param parts
     * @return
     */
    @Multipart
    @Headers({"url_name:user"})
    @POST("/app/ydhl/UploadPostFile")
    Observable<BeanModule<ArrayList<ImagerBean>>> uploadPostFile(@Part("Token") RequestBody token,
                                                                 @Part("PatID") RequestBody PatID,
                                                                 @Part("UserID") RequestBody UserID,
                                                                 @Part("UploadType") RequestBody UploadType,
                                                                 @Part List<MultipartBody.Part> parts);

}
