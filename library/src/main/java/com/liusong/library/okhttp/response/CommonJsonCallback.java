package com.liusong.library.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.liusong.library.okhttp.exception.OkHttpException;
import com.liusong.library.okhttp.listener.DisposeDataHandle;
import com.liusong.library.okhttp.listener.DisposeDataListener;
import com.liusong.library.okhttp.listener.DisposeHandleCookieListener;
import com.liusong.library.utils.GsonUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

/**
 * @author liusong
 * @function 专门处理JSON的回调
 */
public class CommonJsonCallback implements Callback {

    //与服务器返回字段的一个对应关系(返回码需要和服务器商定好的)
    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "error msg";
    protected final String EMPTY_MSG = "empty msg";

    protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it

    //自定义异常类型
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error

    /**
     * 将其它线程的数据转发到UI线程
     */
    private Handler mDeliveryHandler; //进行消息的转发
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle handle) {
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    //请求失败处理
    @Override
    public void onFailure(final Call call, final IOException ioexception) {
        /**
         * 此时还在非UI线程，因此要用post转发运行在UI主线程中
         */
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, ioexception));
            }
        });
    }

    //请求成功回调处理
    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
        final String result = response.body().string();
        final ArrayList<String> cookieLists = handleCookie(response.headers());
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
                /**
                 * handle the cookie
                 */
                if (mListener instanceof DisposeHandleCookieListener) {
                    ((DisposeHandleCookieListener) mListener).onCookie(cookieLists);
                }
            }
        });
    }

    private ArrayList<String> handleCookie(Headers headers) {
        ArrayList<String> tempList = new ArrayList<String>();
        for (int i = 0; i < headers.size(); i++) {
            if (headers.name(i).equalsIgnoreCase(COOKIE_STORE)) {
                tempList.add(headers.value(i));
            }
        }
        return tempList;
    }

    /**
     * 处理服务器返回的响应数据
     * @param responseObj
     */
    private void handleResponse(Object responseObj) {
        //服务器没有正常返回给我们数据
        if (responseObj == null || responseObj.toString().trim().equals("")) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }

        //解析json数据
        try {
            JSONObject result = new JSONObject(responseObj.toString());
            //尝试解析json
            if(result.has(RESULT_CODE)){
                //从json中取出响应码，判断是否是正确的数据响应码
                if(result.getInt(RESULT_CODE)==RESULT_CODE_VALUE){
                    if (mClass == null) {
                        mListener.onSuccess(result);
                    } else {
                        //json转实体对象
                        Object obj = GsonUtil.json2Object(result.toString(),mClass);
                        if (obj != null) {
                            //正确的转换为实体对象
                            mListener.onSuccess(obj);
                        } else {
                            //返回的不是合法的json
                            mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                        }
                    }
                }
            }else{
                //将服务器返回给我们的异常回调到应用层
                //解析异常，抛到catch处理
                mListener.onFailure(new OkHttpException(OTHER_ERROR, result.get(RESULT_CODE)));
            }

        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
            e.printStackTrace();
        }
    }
}