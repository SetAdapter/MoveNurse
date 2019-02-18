package com.fy.retrofit;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * dagger2 注入的目标类
 * Created by fangs on 2017/5/16.
 */
public class RequestConn {

    @Inject
    protected ApiService mConnService;

    public RequestConn() {
        RequestComponent component = DaggerRequestComponent.builder().build();
        component.inJect(this);
    }

    /**
     * requestDate(Map<String, String> params, RequestMode mode, final String requestID)
     * 关联函数 在此函数，调用mConnevice 网络请求接口
     *
     * @return
     */
    protected Observable getObservable(Map<String, Object> params, String requestID) {

        switch (requestID) {
            case Api.getZyPatient:
                return mConnService
                        .getZyPatient(params)
                        .compose(RxHelper.handleResult());
            case Api.LogOut:
                return mConnService
                        .LogOut(params)
                        .compose(RxHelper.handleResult());
            case Api.GetFirstNursing:
            return mConnService
                        .GetFirstNursingRecord(params)
                        .compose(RxHelper.handleResult());
            case Api.FirstNursingRecord:
            return mConnService
                        .FirstNursingRecord(params)
                        .compose(RxHelper.handleResult());
            case Api.FirstChildNursingRecord2:
                return mConnService
                        .ChildFirstNursingRecord(params)
                        .compose(RxHelper.handleResult());
            case Api.AnesthesiaVisit1:
                return mConnService
                        .AnesthesiaVisit1(params)
                        .compose(RxHelper.handleResult());
            case Api.AnesthesiaVisit2:
                return mConnService
                        .AnesthesiaVisit2(params)
                        .compose(RxHelper.handleResult());
            case Api.NursingRecodes1:
                return mConnService
                        .NursingRecodes(params)
                        .compose(RxHelper.handleResult());
            case Api.NursingRecodes3:
                return mConnService
                        .NursingRecodes3(params)
                        .compose(RxHelper.handleResult());
            case Api.PSEvaluation:
                return mConnService
                        .PSEvaluation(params)
                        .compose(RxHelper.handleResult());
            case Api.ObstetricsRecodes2:
                return mConnService
                        .ObstetricsRecodes2(params)
                        .compose(RxHelper.handleResult());
            case Api.ObstetricsRecodes3:
                return mConnService
                        .ObstetricalPostpartum(params)
                        .compose(RxHelper.handleResult());
            case Api.ObstetricsRecodes4:
                return mConnService
                        .ObstetricalPostpartum4(params)
                        .compose(RxHelper.handleResult());
            case Api.NeonatalCareRecodes1:
                return mConnService
                        .NeonatalCareRecodes1(params)
                        .compose(RxHelper.handleResult());
            case Api.NeonatalCareRecodes2:
                return mConnService
                        .NeonatalCareRecodes2(params)
                        .compose(RxHelper.handleResult());
            case Api.NCAssistanceRecodes:
                return mConnService
                        .NCAssistanceRecodes(params)
                        .compose(RxHelper.handleResult());
            case Api.GetBeds:
                return mConnService
                        .GetBeds(params)
                        .compose(RxHelper.handleResult());
            case Api.getDicts:
                return mConnService
                        .getDicts(params)
                        .compose(RxHelper.handleResult());
            case Api.getDicts1:
                return mConnService
                        .getDicts1(params)
                        .compose(RxHelper.handleResult());
            case Api.getDicts10001:
                return mConnService
                        .getDicts10001(params)
                        .compose(RxHelper.handleResult());
            case Api.getDicts10002:
                return mConnService
                        .getDicts10002(params)
                        .compose(RxHelper.handleResult());
            case Api.getDicts10003:
                return mConnService
                        .getDicts10003(params)
                        .compose(RxHelper.handleResult());
            case Api.getDicts10004:
                return mConnService
                        .getDicts10004(params)
                        .compose(RxHelper.handleResult());
            case Api.getDicts10005:
                return mConnService
                        .getDicts10005(params)
                        .compose(RxHelper.handleResult());
            case Api.getDicts10044:
                return mConnService
                        .getDicts10044(params)
                        .compose(RxHelper.handleResult());
            case Api.getDicts10045:
                return mConnService
                        .getDicts10045(params)
                        .compose(RxHelper.handleResult());
            case Api.getDicts10046:
                return mConnService
                        .getDicts10046(params)
                        .compose(RxHelper.handleResult());
            case Api.getDicts10047:
                return mConnService
                        .getDicts10047(params)
                        .compose(RxHelper.handleResult());
            case Api.getDicts10048:
                return mConnService
                        .getDicts10048(params)
                        .compose(RxHelper.handleResult());
            case Api.getDicts10049:
                return mConnService
                        .getDicts10049(params)
                        .compose(RxHelper.handleResult());
            case Api.getDicts10050:
                return mConnService
                        .getDicts10050(params)
                        .compose(RxHelper.handleResult());
            case Api.getDicts10051:
                return mConnService
                        .getDicts10051(params)
                        .compose(RxHelper.handleResult());
            case Api.getDicts10053:
                return mConnService
                        .getDicts10053(params)
                        .compose(RxHelper.handleResult());
            case Api.ChangeBed:
                return mConnService
                        .ChangeBed(params)
                        .compose(RxHelper.handleResult());
            case Api.GetPatOrders:
                return mConnService
                        .GetPatOrders(params)
                        .compose(RxHelper.handleResult());
            case Api.GetPatOrders2:
                return mConnService
                        .GetPatOrders2(params)
                        .compose(RxHelper.handleResult());
            case Api.ExcutePatOrder:
                return mConnService
                        .ExcutePatOrder(params)
                        .compose(RxHelper.handleResult());
            case Api.GetPainbBillDetails:
                return mConnService
                        .GetPainbBillDetails(params)
                        .compose(RxHelper.handleResult());
            case Api.GetPainbBillDetails_Ex:
                return mConnService
                        .GetPainbBillDetails_Ex(params)
                        .compose(RxHelper.handleResult());
            case Api.GetPainbBillDetails_Da:
                return mConnService
                        .GetPainbBillDetails_Da(params)
                        .compose(RxHelper.handleResult());
            case Api.login:
                return mConnService
                        .login(params)
                        .compose(RxHelper.handleResult());
            case Api.ExcutePainbBillDetail:
                return mConnService
                        .ExcutePainbBillDetail(params)
                        .compose(RxHelper.handleResult());
            case Api.SignInformedConsent:
                return mConnService
                        .SignInformedConsent(params)
                        .compose(RxHelper.handleResult());
            case Api.uploadPostFile:
                String token = (String) params.get("Token");
                RequestBody Token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
                RequestBody PatID = RequestBody.create(MediaType.parse("multipart/form-data"), (String) params.get("PatID"));
                RequestBody UserID = RequestBody.create(MediaType.parse("multipart/form-data"), (String) params.get("UserID"));
                RequestBody UploadType = RequestBody.create(MediaType.parse("multipart/form-data"), (String) params.get("UploadType"));

                List<MultipartBody.Part> maps = (List<MultipartBody.Part>) params.get("fileList");

                return mConnService
                        .uploadPostFile(Token, PatID, UserID, UploadType, maps)
                        .compose(RxHelper.handleResult());

        }


//        else if (Api.uploadImg.equals(requestID)){
//            File file = new File(params.get("file"));
//            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//
//            return mConnService.upload(requestFile);
//        }

        return null;
    }
}
