package com.example.rasik.firebasedemo;

import android.nfc.Tag;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by rasik on 15/9/17.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "FirebaseIDService";
    @Override
    public void onTokenRefresh() {
        String refresherToken= FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Refreshed Token: "+ refresherToken);
        sendRegistrationToServer(refresherToken);
    }

    public void sendRegistrationToServer(String token){

    }
}
