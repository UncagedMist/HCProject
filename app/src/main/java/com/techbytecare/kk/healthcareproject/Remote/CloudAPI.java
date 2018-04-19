package com.techbytecare.kk.healthcareproject.Remote;

import com.techbytecare.kk.healthcareproject.DataModel.Feeds;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by kundan on 3/2/2018.
 */

public interface CloudAPI {

    public static final String BASE_URL = "https://api.thingspeak.com/channels/437591/feeds.json?api_key=C7J67TRBKGSABYW2&results/";

    @GET(".json")
    Call<Feeds> getData(String BASE_URL);
}
