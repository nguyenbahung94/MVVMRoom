package com.example.hungnb.demomvvm.api;

import com.example.hungnb.demomvvm.ui.model.request.LoginBody;
import com.example.hungnb.demomvvm.ui.model.response.GetListNotiResponse;
import com.example.hungnb.demomvvm.ui.model.response.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    @Headers("Ocp-Apim-Subscription-Key: 8421a295f02e44eaa395c739731164d2")
    @GET("notification/v1/getnotification?")
    Observable<GetListNotiResponse> getListNoti(@Query("customerId") String customerId,
                                                @Query("language") String language,
                                                @Query("count") int count,
                                                @Query("firstNotificationId") int firstNotificationId,
                                                @Query("lastNotificationId") int lastNotificationId);

    @Headers("Ocp-Apim-Subscription-Key: 8421a295f02e44eaa395c739731164d2")
    @POST("v1/login")
    Observable<LoginResponse> login(@Body LoginBody loginBody);
}
