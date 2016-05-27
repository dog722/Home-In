package kr.co.homein.homeinproject.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import java.util.concurrent.TimeUnit;

import kr.co.homein.homeinproject.Maps.AddressInfo;
import kr.co.homein.homeinproject.Maps.AddressInfoResult;
import kr.co.homein.homeinproject.MyApplication;
import kr.co.homein.homeinproject.data.CompanyItemData;
import kr.co.homein.homeinproject.data.CompanyItemDataResult;
import kr.co.homein.homeinproject.data.PeopleDetailItemData;
import kr.co.homein.homeinproject.data.PeopleItemData;
import kr.co.homein.homeinproject.data.PeopleItemDataResult;
import kr.co.homein.homeinproject.data.PeopleItemDetailResult;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dongja94 on 2016-05-09.
 */
public class NetworkManager {
    private static NetworkManager instance;
    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    private static final int DEFAULT_CACHE_SIZE = 50 * 1024 * 1024;
    private static final String DEFAULT_CACHE_DIR = "miniapp";
    OkHttpClient mClient;
    private NetworkManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Context context = MyApplication.getContext();
        CookieManager cookieManager = new CookieManager(new PersistentCookieStore(context), CookiePolicy.ACCEPT_ALL);
        builder.cookieJar(new JavaNetCookieJar(cookieManager));

        File dir = new File(context.getExternalCacheDir(), DEFAULT_CACHE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        builder.cache(new Cache(dir, DEFAULT_CACHE_SIZE));

        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);

        mClient = builder.build();
    }

    public interface OnResultListener<T> {
        public void onSuccess(Request request, T result);
        public void onFail(Request request, IOException exception);
    }

    private static final int MESSAGE_SUCCESS = 1;
    private static final int MESSAGE_FAIL = 2;

    class NetworkHandler extends Handler {
        public NetworkHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkResult result = (NetworkResult)msg.obj;
            switch (msg.what) {
                case MESSAGE_SUCCESS :
                    result.listener.onSuccess(result.request, result.result);
                    break;
                case MESSAGE_FAIL :
                    result.listener.onFail(result.request, result.excpetion);
                    break;
            }
        }
    }

    NetworkHandler mHandler = new NetworkHandler(Looper.getMainLooper());

    static class NetworkResult<T> {
        Request request;
        OnResultListener<T> listener;
        IOException excpetion;
        T result;
    }

    Gson gson = new Gson();

    private static final String TMAP_SERVER = "https://apis.skplanetx.com";
    private static final String TMAP_REVERSE_GEOCODING = TMAP_SERVER + "/tmap/geo/reversegeocoding?coordType=WGS84GEO&addressType=A02&lat=%s&lon=%s&version=1";
    public Request getTMapReverseGeocoding(Object tag, double lat, double lng, OnResultListener<AddressInfo> listener) {
        String url = String.format(TMAP_REVERSE_GEOCODING, "" + lat, ""+lng);
        Request request = new Request.Builder()
                .url(url)
                .header("Accept","application/json")
                .header("appKey","458a10f5-c07e-34b5-b2bd-4a891e024c2a")
                .build();
        final NetworkResult<AddressInfo> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    AddressInfoResult data = gson.fromJson(response.body().charStream(), AddressInfoResult.class);
                    result.result = data.addressInfo;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }


    //피플 아이템 리스트
    private static final String HOMEIN_SERVER = "http://52.79.170.110:80";
    private static final String PEOPLE_ITEM_LIST = HOMEIN_SERVER + "/people_homein_list";
    public Request getPeopleItemList(Object tag, OnResultListener<List<PeopleItemData>> listener) {
        Request request = new Request.Builder()
                .url(PEOPLE_ITEM_LIST)
                .header("Accept", "application/json")
                .header("appKey", "458a10f5-c07e-34b5-b2bd-4a891e024c2a")
                .build();
        final NetworkResult<List<PeopleItemData>> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    PeopleItemDataResult data = gson.fromJson(response.body().charStream(), PeopleItemDataResult.class);
                    result.result = data.peopleitemdata;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    //피플 아이템 상세 페이지

    private static final String PEOPLE_DETAIL = HOMEIN_SERVER + "/people_homein_info";
    public Request getPeopleItemDetail(Object tag, String PH_number, OnResultListener<PeopleDetailItemData> listener) {
        String url = String.format(PEOPLE_DETAIL);
        RequestBody body = new FormBody.Builder()
                .add("PH_number", PH_number)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        final NetworkResult<PeopleDetailItemData> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    PeopleItemDetailResult data = gson.fromJson(response.body().charStream(), PeopleItemDetailResult.class);
                    result.result = data.peopleDetailItemData;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    //회사 아이템 리스트
    private static final String COMPANY_ITEM = HOMEIN_SERVER + "/construction_homein_list";
    public Request getCompanyItemList(Object tag, OnResultListener<List<CompanyItemData>> listener) {
        Request request = new Request.Builder()
                .url(COMPANY_ITEM)
                .header("Accept", "application/json")
                .header("appKey", "458a10f5-c07e-34b5-b2bd-4a891e024c2a")
                .build();
        final NetworkResult<List<CompanyItemData>> result = new NetworkResult<>();
        result.request = request;
        result.listener = listener;
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.excpetion = e;
                mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_FAIL, result));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    CompanyItemDataResult data = gson.fromJson(response.body().charStream(), CompanyItemDataResult.class);
                    result.result = data.companyItemData;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

}
