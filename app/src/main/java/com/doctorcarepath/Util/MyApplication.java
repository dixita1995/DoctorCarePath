package com.doctorcarepath.Util;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.multidex.MultiDex;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.doctorcarepath.GoogleAds.AppOpenManger;
import com.doctorcarepath.GoogleAds.GoogleAdListener;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class MyApplication extends Application implements LifecycleObserver, GoogleAdListener {

    public static Context context;

    RequestQueue mRequestQueue;

    public static Context getAppContext() {
        Log.d("CONTEXT_APP", "---------------" + MyApplication.context);
        return MyApplication.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        mRequestQueue = Volley.newRequestQueue(this);
        loadDataFromJson();

        RequestConfiguration configuration = new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("0BD6075A8084AE49311CDECCD9768E58")).build();


    }

    private void loadDataFromJson() {
        /*https://jsonkeeper.com/b/393Y*/
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, "https://jsonkeeper.com/b/6C39", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Utils.storeIds(context,

                                    response.getBoolean(Utils.ENABLE_SPIN_WIN_DIALOG),
                                    response.getBoolean(Utils.ENABLE_AD_LOADING_DIALOG),
                                    response.getBoolean(Utils.ENABLE_IN_APP_REVIEW),
                                    response.getBoolean(Utils.ENABLE_2_NATIVE_AD),
                                    response.getString(Utils.CUSTOM_TAB_URL),
                                    response.getString(Utils.BASE_URL),
                                    response.getString(Utils.ADMOB_NATIVE_ID1),
                                    response.getString(Utils.ADMOB_NATIVE_ID2),
                                    response.getString(Utils.ADMOB_NATIVE_ID3),
                                    response.getString(Utils.ADMOB_NATIVE_ID4),
                                    response.getString(Utils.ADMOB_OPEN_ID_ID1),
                                    response.getString(Utils.ADMOB_OPEN_ID_ID2),
                                    response.getString(Utils.ADMOB_INTERSTITIAL_ID1),
                                    response.getString(Utils.ADMOB_INTERSTITIAL_ID2),
                                    response.getString(Utils.ADMOB_INTERSTITIAL_ID3),
                                    response.getString(Utils.ADMOB_INTERSTITIAL_ID4),
                                    response.getString(Utils.ADMOB_INTERSTITIAL_ID5),
                                    response.getString(Utils.ADMOB_INTERSTITIAL_BACK_ID1),
                                    response.getString(Utils.ADMOB_INTERSTITIAL_BACK_ID2),
                                    response.getString(Utils.ADMOB_INTERSTITIAL_BACK_ID3),
                                    response.getString(Utils.ADMOB_INTERSTITIAL_BACK_ID4),
                                    response.getString(Utils.ADMOB_BANNER_ID1),
                                    response.getString(Utils.ADMOB_BANNER_ID2),
                                    response.getString(Utils.ADMOB_BANNER_ID3),
                                    response.getString(Utils.ADMOB_BANNER_ID4),

                                    response.getBoolean(Utils.ENABLE_INTERSTIAL),
                                    response.getBoolean(Utils.ENABLE_BACK_INTERSTIAL),

                                    response.getString(Utils.MORE_APPS_URL),
                                    response.getString(Utils.PLAY_WIN),
                                    response.getString(Utils.INTERSTITIAL_AD_BANNER),
                                    response.getString(Utils.INTERSTITIAL_AD_BANNER_CALLBACK),
                                    response.getString(Utils.NATIVE_AD_BANNER),
                                    response.getString(Utils.OPEN_AD_BANNER),
                                    response.getString(Utils.OPEN_AD_BANNER_CALLBACK)
                            );

                            Utils.downloadOpenAdBanner(context, Utils.getOpenAdBanner(context));
                            Utils.downloadInterstitialAdBanner(context, Utils.getInterstitialAdBanner(context));


                            MobileAds.initialize(MyApplication.this);

                            new AppOpenManger(MyApplication.this, MyApplication.this, Utils.getAdmobOpenIdId1(MyApplication.this), Utils.getOpenAdBanner(MyApplication.this));


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("JSON_RESPONSE", "-------------EXCEPTIon-------" + e);

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());


            }
        });

        mRequestQueue.add(req);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void OnAdClose() {

    }

    @Override
    public void OnAdError(int i) {

    }

    @Override
    public void OnBannerAdClick() {

    }

    @Override
    public void OnNativeAdClick() {
        Utils.openChromeCustomTabUrl(this,Utils.getNativeAdBannerCallback(this));
    }

    @Override
    public void OnInterstitialAdClick() {
        Utils.openChromeCustomTabUrl(this,Utils.getInterstitialAdBannerCallback(this));
    }

    @Override
    public void OnOpenAdClick() {
        Utils.openChromeCustomTabUrl(this,Utils.getOpenAdBannerCallback(this));
    }
}