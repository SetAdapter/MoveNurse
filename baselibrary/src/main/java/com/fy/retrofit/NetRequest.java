package com.fy.retrofit;

import com.fy.application.BaseApplication;
import com.fy.statuslayout.manager.RootFrameLayout;
import com.fy.utils.ConstantUtils;
import com.fy.utils.NetUtils;
import com.fy.utils.SpfUtils;
import com.fy.utils.T;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * email：fy310518@163.com
 * Created by fangs on 2017/8/28.
 */
public class NetRequest extends RequestConn{

    static final String TAG = "Net";

    private volatile static NetRequest netRequest;

    private NetRequest() {
        super();
        ConstantUtils.custom_Url = SpfUtils.getSpfSaveStr(BaseApplication.getApplication(), "ServiceAddress");
    }

    public static NetRequest getInstens(){
        if (null == netRequest) {
            synchronized (NetRequest.class) {
                if (null == netRequest) {
                    netRequest = new NetRequest();
                }
            }
        }

        return netRequest;
    }

    /**
     * 采用 dagger2 依赖注入 框架 + retorfit 请求 后台 数据
     * @param params 请求服务器 接口所需要的 参数信息
     * @param requestID 对应 某一个 特殊的http 请求，用于回调标识
     * @param isforceRefresh 是否 同时从缓存获取数据
     * @param callBack
     * @param <V>
     */
    @SuppressWarnings("unchecked")
    public <V> void requestDate(Map<String, Object> params, String requestID, boolean isforceRefresh, NetCallBack callBack) {

        Observable<V> fromNetwrok = getObservable(params, requestID);

        if (null == fromNetwrok) {
            throw new IllegalArgumentException("no Observable");
        }

        RxRetrofitCache.load(ConstantUtils.userId + requestID, 1000 * 60 * 10, fromNetwrok, isforceRefresh)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        callBack.updataLayout(RootFrameLayout.LAYOUT_CONTENT_ID);

                        if (!NetUtils.isConnected(BaseApplication.getApplication())){
//                            disposable.dispose();
//                            L.e("NetRequest", "取消请求");
                            T.showShort("似乎没有网络哦!!!");
                            callBack.updataLayout(RootFrameLayout.LAYOUT_NETWORK_ERROR_ID);
//                            callBack.onComplete();
                        }
                    }
                })
                .subscribe(callBack);
    }

}
