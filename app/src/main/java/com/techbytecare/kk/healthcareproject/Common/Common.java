package com.techbytecare.kk.healthcareproject.Common;

import com.techbytecare.kk.healthcareproject.Model.Results;
import com.techbytecare.kk.healthcareproject.Model.UserDoctor;
import com.techbytecare.kk.healthcareproject.Model.UserPatient;
import com.techbytecare.kk.healthcareproject.Remote.IGoogleAPIService;
import com.techbytecare.kk.healthcareproject.Remote.RetrofitClient;

/**
 * Created by kundan on 2/25/2018.
 */

public class Common {

    public static UserPatient currentPatient;
    public static UserDoctor currentDoctor;

    public static final String USER_KEY_PAT = "UserPhone";
    public static final String PWD_KEY_PAT = "Password";

    public static final String USER_KEY_DOC = "UserPhone";
    public static final String PWD_KEY_DOC = "Password";

    public static final String USER_TEMP = "27.6";
    public static final String USER_ID = "52";

    public static Results currentResults;

    private static final String GOOGLE_API_URL = "https://maps.googleapis.com/";

    public static IGoogleAPIService getGoogleAPIService()   {
        return RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleAPIService.class);
    }
}
