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
import kr.co.homein.homeinproject.Maps.CompanyMapInfo;
import kr.co.homein.homeinproject.Maps.CompanyMapResult;
import kr.co.homein.homeinproject.Maps.HomeInMapData;
import kr.co.homein.homeinproject.Maps.HomeInMapResult;
import kr.co.homein.homeinproject.MyApplication;
import kr.co.homein.homeinproject.data.CompanyDetailItemData;
import kr.co.homein.homeinproject.data.CompanyDetailItemDataResult;
import kr.co.homein.homeinproject.data.CompanyInfoData;
import kr.co.homein.homeinproject.data.CompanyInfoDataResult;
import kr.co.homein.homeinproject.data.CompanyItemData;
import kr.co.homein.homeinproject.data.CompanyItemDataResult;
import kr.co.homein.homeinproject.data.EstimateDetailData;
import kr.co.homein.homeinproject.data.EstimateDetailResult;
import kr.co.homein.homeinproject.data.EstimateListData;
import kr.co.homein.homeinproject.data.EstimateListDataResult;
import kr.co.homein.homeinproject.data.EstimateWriteResult;
import kr.co.homein.homeinproject.data.EventPageData;
import kr.co.homein.homeinproject.data.EventPageResult;
import kr.co.homein.homeinproject.data.InputCommentResult;
import kr.co.homein.homeinproject.data.MyInfoData;
import kr.co.homein.homeinproject.data.MyInfoDataResult;
import kr.co.homein.homeinproject.data.PeopleAddWishListResult;
import kr.co.homein.homeinproject.data.PeopleDetailItemData;
import kr.co.homein.homeinproject.data.PeopleItemData;
import kr.co.homein.homeinproject.data.PeopleItemDataResult;
import kr.co.homein.homeinproject.data.PeopleItemDetailResult;
import kr.co.homein.homeinproject.data.PeopleItemWriteData;
import kr.co.homein.homeinproject.data.PeopleItemWriteDataResult;
import kr.co.homein.homeinproject.data.SearchDetailListResult;
import kr.co.homein.homeinproject.data.SearchDetailListResults;
import kr.co.homein.homeinproject.data.SearchListDataResult;
import kr.co.homein.homeinproject.data.SearchResult;
import kr.co.homein.homeinproject.data.WishListData;
import kr.co.homein.homeinproject.data.WishListDataResult;
import kr.co.homein.homeinproject.data.WishListResult;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
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
//    private static final String HOMEIN_SERVER = "http://192.168.211.224:3000";
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



    //회사 아이템 상세 페이지
    private static final String COMPANY_DETAIL = HOMEIN_SERVER + "/construction_homein_info/";
    public Request getCompanyItemDetail(Object tag, String CH_number, OnResultListener<CompanyDetailItemData> listener) {
        String url = String.format(COMPANY_DETAIL);
        RequestBody body = new FormBody.Builder()
                .add("CH_number", CH_number)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        final NetworkResult<CompanyDetailItemData> result = new NetworkResult<>();
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
                    CompanyDetailItemDataResult data = gson.fromJson(response.body().charStream(), CompanyDetailItemDataResult.class);
                    result.result = data.companyDetailItemData;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    //업체 프로필 페이지
    private static final String COMPANY_INFO = HOMEIN_SERVER + "/office_info/";
    public Request getCompanyInfo(Object tag, String office_number, OnResultListener<CompanyInfoData> listener) {
        String url = String.format(COMPANY_INFO);
        RequestBody body = new FormBody.Builder()
                .add("office_number", office_number)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        final NetworkResult<CompanyInfoData> result = new NetworkResult<>();
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
                    CompanyInfoDataResult data = gson.fromJson(response.body().charStream(), CompanyInfoDataResult.class);
                    result.result = data.companyInfoData;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }


    //업체에 해당하는 시공 사례 페이지
    private static final String COMPANY_OWN_ITEM_ = HOMEIN_SERVER + "/construction_example_list/";
    public Request getCompanyOwnItemList(Object tag, String office_number, OnResultListener<List<CompanyItemData>> listener) {

        RequestBody body = new FormBody.Builder()
                .add("office_number", office_number)
                .build();


        Request request = new Request.Builder()
                .url(COMPANY_OWN_ITEM_)
                .header("Accept", "application/json")
                .header("appKey", "458a10f5-c07e-34b5-b2bd-4a891e024c2a")
                .post(body)
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




    //작성 페이지
    private static final String PEOPLE_WRITE = HOMEIN_SERVER + "/people_homein_write/";
    public Request sendPeopleWrite(Object tag, String general_number, String PH_content, List<String>PH_tag, File file, int category_number,
                                   OnResultListener<PeopleItemWriteData> listener) {
        String url = String.format(PEOPLE_WRITE);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("general_number", general_number)
                .addFormDataPart("PH_content", PH_content);
        for (int i = 0; i <PH_tag.size(); i++) {
            String t = PH_tag.get(i);
            builder.addFormDataPart("PH_tag["+i+"]", t);
        }

        builder.setType(MultipartBody.FORM)
                .addFormDataPart("PH_picture", file.getName(),
                        RequestBody.create(MediaType.parse("image/jpeg"), file));

        builder.addFormDataPart("category_number", category_number+"");

        RequestBody body = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        final NetworkResult<PeopleItemWriteData> result = new NetworkResult<>();
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
                    PeopleItemWriteDataResult data = gson.fromJson(response.body().charStream(), PeopleItemWriteDataResult.class);
                    result.result = data.peopleItemWriteData;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }



    //내 정보
    private static final String MY_INFO = HOMEIN_SERVER + "/my_info/";
    public Request getMyInfo(Object tag, String general_number, OnResultListener<MyInfoData> listener) {

        RequestBody body = new FormBody.Builder()
                .add("general_number", general_number)
                .build();


        Request request = new Request.Builder()
                .url(MY_INFO)
                .header("Accept", "application/json")
                .header("appKey", "458a10f5-c07e-34b5-b2bd-4a891e024c2a")
                .post(body)
                .build();


        final NetworkResult<MyInfoData> result = new NetworkResult<>();
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
                    MyInfoDataResult data = gson.fromJson(response.body().charStream(), MyInfoDataResult.class);
                    result.result = data.myInfoData;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }


    //피플 홈인 관심 목록 담기
    private static final String PICK_LIST = HOMEIN_SERVER + "/pick_list/";
    public Request getMyWishList(Object tag, String general_number, OnResultListener<List<WishListData>> listener) {

        RequestBody body = new FormBody.Builder()
                .add("general_number", general_number)
                .build();

        Request request = new Request.Builder()
                .url(PICK_LIST)
                .header("Accept", "application/json")
                .header("appKey", "458a10f5-c07e-34b5-b2bd-4a891e024c2a")
                .post(body)
                .build();


        final NetworkResult<List<WishListData>> result = new NetworkResult<>();
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
                    WishListDataResult data = gson.fromJson(response.body().charStream(), WishListDataResult.class);
                    result.result = data.wishListData;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    //피플 관심 목록 담기
    private static final String PEOPLE_ADD_WISHLIST = HOMEIN_SERVER + "/add_pick_list";
    public Request addPeopleWishlist(Object tag, String PH_number,String general_number, OnResultListener<PeopleAddWishListResult> listener) {
        String url = String.format(PEOPLE_ADD_WISHLIST);
        RequestBody body = new FormBody.Builder()
                .add("PH_number", PH_number)
                .add("general_number", general_number)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        final NetworkResult<PeopleAddWishListResult> result = new NetworkResult<>();
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
                    PeopleAddWishListResult data = gson.fromJson(response.body().charStream(), PeopleAddWishListResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }


    //검색 화면 결과 리스트
    private static final String SEARCH_LIST = HOMEIN_SERVER + "/search_count";
    public Request getSearchResult(Object tag, String tag_result, OnResultListener<SearchListDataResult> listener) {

        RequestBody body = new FormBody.Builder()
                .add("tag", tag_result)
                .build();


        Request request = new Request.Builder()
                .url(SEARCH_LIST)
                .header("Accept", "application/json")
                .header("appKey", "458a10f5-c07e-34b5-b2bd-4a891e024c2a")
                .post(body)
                .build();


        final NetworkResult<SearchListDataResult> result = new NetworkResult<>();
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
                    SearchResult data = gson.fromJson(response.body().charStream(), SearchResult.class);
                    result.result = data.searchListDataResult;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }



    //이벤트 페이지
    private static final String EVENTPAGE = HOMEIN_SERVER + "/event_list";
    public Request getEventPage(Object tag, OnResultListener<List<EventPageData>> listener) {
        Request request = new Request.Builder()
                .url(EVENTPAGE)
                .header("Accept", "application/json")
                .header("appKey", "458a10f5-c07e-34b5-b2bd-4a891e024c2a")
                .build();
        final NetworkResult<List<EventPageData>> result = new NetworkResult<>();
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
                    EventPageResult data = gson.fromJson(response.body().charStream(), EventPageResult.class);
                    result.result = data.eventPageData;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }

    //댓글 달기
    private static final String INPUT_COMMENT = HOMEIN_SERVER + "/people_homein_comment_write";
    public Request addComment(Object tag, String posting_number, String comment_content, String member_number, OnResultListener<InputCommentResult> listener) {

        RequestBody body = new FormBody.Builder()
                .add("posting_number", posting_number)
                .add("comment_content", comment_content)
                .add("member_number", member_number)
                .build();


        Request request = new Request.Builder()
                .url(INPUT_COMMENT)
                .header("Accept", "application/json")
                .header("appKey", "458a10f5-c07e-34b5-b2bd-4a891e024c2a")
                .post(body)
                .build();


        final NetworkResult<InputCommentResult> result = new NetworkResult<>();
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
                    InputCommentResult data = gson.fromJson(response.body().charStream(), InputCommentResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }


    //관심 목록 삭제
    private static final String WISHLIST_DELETE = HOMEIN_SERVER + "/pick_list_delete/";
    public Request deleteMyWishList(Object tag, String general_number, List<String> delete_posting_number,
                                   OnResultListener<WishListResult> listener) {
        String url = String.format(WISHLIST_DELETE);
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("general_number", general_number);

        for (int i = 0; i <delete_posting_number.size(); i++) {
            String t = delete_posting_number.get(i);
            builder.add("delete_posting_number["+i+"]", t);
        }
        RequestBody body = builder.build();


        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("appKey", "458a10f5-c07e-34b5-b2bd-4a891e024c2a")
                .post(body)
                .build();



        final NetworkResult<WishListResult> result = new NetworkResult<>();
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
                    WishListResult data = gson.fromJson(response.body().charStream(), WishListResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }


    //검색 결과 리스트
    private static final String SEARCH_RESULT = HOMEIN_SERVER + "/search_all";
    public Request getSearchResultList(Object tag, String search_tag, OnResultListener< SearchDetailListResult> listener) {


        RequestBody body = new FormBody.Builder()
                .add("tag", search_tag)
                .build();

        Request request = new Request.Builder()
                .url(SEARCH_RESULT)
                .header("Accept", "application/json")
                .header("appKey", "458a10f5-c07e-34b5-b2bd-4a891e024c2a")
                .post(body)
                .build();


        final NetworkResult<SearchDetailListResult> result = new NetworkResult<>();
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
                    SearchDetailListResults data = gson.fromJson(response.body().charStream(), SearchDetailListResults.class);
                    result.result = data.searchDetailListResult;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }


    //견적서 작성 페이지
    private static final String ESTIMATE_WRITE = HOMEIN_SERVER + "/write_general_estimate/";
    public Request sendEstimateWrite(Object tag, String general_number,
                                     String office_number,
                                     String estimate_space,
                                     String estimate_sub_space,
                                     String estimate_size,
                                     List<File> interior_picture,
                                     String general_real_name,
                                     String general_email,
                                     String general_tel,
                                     String interior_info_content,
                                   OnResultListener<EstimateWriteResult> listener) {


        String url = String.format(ESTIMATE_WRITE);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("general_number", general_number)
                .addFormDataPart("office_number", office_number)
                .addFormDataPart("estimate_space", estimate_space)
                .addFormDataPart("estimate_sub_space", estimate_sub_space)
                .addFormDataPart("estimate_size", estimate_size);
        builder.setType(MultipartBody.FORM);
        for (int i = 0; i <interior_picture.size(); i++) {
                    builder.addFormDataPart("interior_picture", interior_picture.get(i).getName(),
                            RequestBody.create(MediaType.parse("image/jpeg"), interior_picture.get(i)));
        }

        builder.addFormDataPart("general_real_name", general_real_name)
                .addFormDataPart("general_email", general_email)
                .addFormDataPart("general_tel", general_tel)
                .addFormDataPart("interior_info_content", interior_info_content);

        RequestBody body = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        final NetworkResult<EstimateWriteResult> result = new NetworkResult<>();
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
                    EstimateWriteResult data = gson.fromJson(response.body().charStream(), EstimateWriteResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }


    //내 견적서 상세 페이지

    private static final String ESTIMATE_DETAIL = HOMEIN_SERVER + "/general_estimate_info";
    public Request getEstimateDetailItem(Object tag, String general_estimate_number, String general_number, OnResultListener<EstimateDetailData> listener) {
        String url = String.format(ESTIMATE_DETAIL);
        RequestBody body = new FormBody.Builder()
                .add("general_estimate_number", general_estimate_number)
                .add("general_number", general_number)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        final NetworkResult<EstimateDetailData> result = new NetworkResult<>();
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
                    EstimateDetailResult data = gson.fromJson(response.body().charStream(), EstimateDetailResult.class);
                    result.result = data.estimateDetailData;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }


    //견적서  댓글 달기
    private static final String INPUT_ESTIMATE_COMMENT = HOMEIN_SERVER + "/write_estimate_comment";
    public Request addEstimateComment(Object tag, String posting_number, String comment_content, String member_number, OnResultListener<InputCommentResult> listener) {

        RequestBody body = new FormBody.Builder()
                .add("member_number", member_number)
                .add("posting_number", posting_number)
                .add("comment_content", comment_content)
                .build();


        Request request = new Request.Builder()
                .url(INPUT_ESTIMATE_COMMENT)
                .header("Accept", "application/json")
                .header("appKey", "458a10f5-c07e-34b5-b2bd-4a891e024c2a")
                .post(body)
                .build();


        final NetworkResult<InputCommentResult> result = new NetworkResult<>();
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
                    InputCommentResult data = gson.fromJson(response.body().charStream(), InputCommentResult.class);
                    result.result = data;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }


    //내 견적서 리스트
    private static final String MYESTIMATE_LIST = HOMEIN_SERVER + "/general_estimate_list";
    public Request getMyEstimateList(Object tag,String general_number, OnResultListener <List<EstimateListData>> listener) {


        RequestBody body = new FormBody.Builder()
                .add("general_number", general_number)
                .build();

        Request request = new Request.Builder()
                .url(MYESTIMATE_LIST)
                .header("Accept", "application/json")
                .header("appKey", "458a10f5-c07e-34b5-b2bd-4a891e024c2a")
                .post(body)
                .build();


        final NetworkResult<List<EstimateListData>> result = new NetworkResult<>();
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
                    EstimateListDataResult data = gson.fromJson(response.body().charStream(), EstimateListDataResult.class);
                    result.result = data.estimateListData;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }



    //업체 맵 정보
    private static final String COMPANY_MAP_INFO = HOMEIN_SERVER + "/map_office";
    public Request getCompanyMapInfo(Object tag, String office_number, OnResultListener<CompanyMapInfo> listener) {
        String url = String.format(COMPANY_MAP_INFO);
        RequestBody body = new FormBody.Builder()
                .add("office_number", office_number)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        final NetworkResult<CompanyMapInfo> result = new NetworkResult<>();
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
                    CompanyMapResult data = gson.fromJson(response.body().charStream(), CompanyMapResult.class);
                    result.result = data.companyMapInfo;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }


    //실시간 사용자 위치 맵 정보
    private static final String PEOPLE_MAP_INFO = HOMEIN_SERVER + "/map_current";
    public Request getPeopleMapData(Object tag, double general_latitude, double general_longitude, OnResultListener<HomeInMapData> listener) {
        String url = String.format(PEOPLE_MAP_INFO);
        RequestBody body = new FormBody.Builder()
                .add("general_latitude", general_latitude+"")
                .add("general_longitude", general_longitude+"")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        final NetworkResult<HomeInMapData> result = new NetworkResult<>();
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
                    HomeInMapResult data = gson.fromJson(response.body().charStream(), HomeInMapResult.class);
                    result.result = data.homeInMapData;
                    mHandler.sendMessage(mHandler.obtainMessage(MESSAGE_SUCCESS, result));
                } else {
                    throw new IOException(response.message());
                }
            }
        });
        return request;
    }


}
