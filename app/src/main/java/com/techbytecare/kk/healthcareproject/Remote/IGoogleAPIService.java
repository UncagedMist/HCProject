package com.techbytecare.kk.healthcareproject.Remote;

import com.techbytecare.kk.healthcareproject.DataModel.DataTemp;
import com.techbytecare.kk.healthcareproject.Model.MyPlaces;
import com.techbytecare.kk.healthcareproject.Model.PlaceDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by kundan on 2/28/2018.
 */

public interface IGoogleAPIService {
    @GET
    Call<MyPlaces> getNearByPlaces(@Url String url);

    @GET
    Call<PlaceDetail> getDetailPlaces(@Url String url);
}
