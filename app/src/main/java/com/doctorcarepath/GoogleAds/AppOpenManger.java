package com.doctorcarepath.GoogleAds;

import static androidx.lifecycle.Lifecycle.Event.ON_START;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.doctorcarepath.R;
import com.doctorcarepath.SplashScreen;
import com.doctorcarepath.Util.MyApplication;
import com.doctorcarepath.Util.Utils;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import java.io.File;
import java.util.Date;

public class AppOpenManger implements Application.ActivityLifecycleCallbacks, LifecycleObserver {

    private static final String LOG_TAG = "AppOpenManager";
    static GoogleAdListener listener;

    private static boolean isShowingAd = false;
    private static Context context;
    private final Application myApplication;
    private AppOpenAd appOpenAd = null;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;
    private Activity currentActivity;
    private long loadTime = 0;
    private String ADMOB_OPEN_ID, bannerurl;
    private Boolean ads = true;

    public AppOpenManger(Application application, Context contxet, String open_ad_id, String bannerurl) {
        Log.d("TAG", "AppOpenManger: " + open_ad_id);

        this.myApplication = application;

        this.ADMOB_OPEN_ID = open_ad_id;//open_ad_id;

        this.bannerurl = bannerurl;

        this.context = contxet;

        listener = (GoogleAdListener) contxet;

        this.myApplication.registerActivityLifecycleCallbacks(this);

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);

    }

    public void fetchAd() {
        Log.d("TAG", "fetchAd: " + ADMOB_OPEN_ID);

        if (isAdAvailable()) {
            return;
        }

//        loadOpenBanner();


        loadCallback =
                new AppOpenAd.AppOpenAdLoadCallback() {
                    @Override
                    public void onAdLoaded(AppOpenAd ad) {
                        Log.d("TAG", "onAdLoaded: " + ADMOB_OPEN_ID);
                        appOpenAd = ad;
                        Log.d(LOG_TAG, "open ad loaded");
                        loadTime = (new Date()).getTime();
                    }


                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        loadOpenBanner();
                        Log.d("TAG", "onAdFailedToLoad: " + ADMOB_OPEN_ID);
                        appOpenAd = null;
                        Log.d(LOG_TAG, "open ad failed to load");
                    }

                };

        AdRequest request = getAdRequest();
        AppOpenAd.load(
                myApplication, ADMOB_OPEN_ID, request,
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
    }

    private void loadOpenBanner() {
        Log.d("TAG", "loadOpenBanner: " + ADMOB_OPEN_ID);
//        if (failed_ad_dialog != null)
//            return;

        View openAdView;
        Dialog failed_ad_dialog;
        ConstraintLayout headerView;
        CardView bannerView;
        ImageView bannerImg;

        openAdView = LayoutInflater.from(MyApplication.context).inflate(R.layout.open_ad_banner_layout, null);

        headerView = openAdView.findViewById(R.id.headerView);
        bannerView = openAdView.findViewById(R.id.bannerView);
        bannerImg = openAdView.findViewById(R.id.bannerImg);

        Log.d(LOG_TAG, "loadOpenBanner" + currentActivity);

        failed_ad_dialog = new Dialog(currentActivity, android.R.style.Theme_Black_NoTitleBar_Fullscreen);

        if (openAdView.getParent() != null)
            ((ViewGroup) openAdView.getParent()).removeView(openAdView);

        failed_ad_dialog.setContentView(openAdView);
        failed_ad_dialog.setCancelable(false);

        failed_ad_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        File file = new File(MyApplication.context.getCacheDir().getAbsolutePath() + "/" + Utils.OPEN_AD_BANNER_NAME);
        if (!file.exists()) {
            Utils.downloadOpenAdBanner(MyApplication.context, Utils.getOpenAdBanner(MyApplication.context));
            Glide.with(MyApplication.context).load(bannerurl).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(bannerImg);
        } else
            Glide.with(MyApplication.context).load(file.getAbsoluteFile()).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(bannerImg);

        Log.d(LOG_TAG, "Dialog created.");

        bannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                failed_ad_dialog.dismiss();
                listener.OnAdClose();
                listener.OnOpenAdClick();
            }
        });

        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Close btn clicked.");
                listener.OnAdClose();
                failed_ad_dialog.dismiss();
            }
        });

        failed_ad_dialog.show();
    }

    public void showAdIfAvailable() {
        Log.d("TAG", "showAdIfAvailable: " + ADMOB_OPEN_ID);
        // context.stopService(new Intent(context, BackgroundSoundService.class));


        if (isShowingAd || !isAdAvailable()) {
            Log.d(LOG_TAG, "Can not show ad.");
            Log.d("TAG", "Can not show ad: " + ADMOB_OPEN_ID);
            fetchAd();
            //loadOpenBanner();
            if (!Utils.IS_FAILED_DIALOG_SHOW && !Utils.IS_BACK_FAILED_DIALOG_SHOW) {
                //  loadOpenBanner();
            }


            return;
        } else {
            Log.d(LOG_TAG, "Will show ad.");
            Log.d("TAG", "Will show ad: " + ADMOB_OPEN_ID);
            if (!(currentActivity instanceof SplashScreen)) {
                appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        Log.d("APPLICATION", "onAdDismissedFullScreenContent:......................................... ");
                        appOpenAd = null;
                        isShowingAd = false;
                        fetchAd();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        isShowingAd = true;
                    }
                });
                appOpenAd.show(currentActivity);
            }
        }
    }

    private boolean wasLoadTimeLessThanNHoursAgo(long numHours) {
        long dateDifference = (new Date()).getTime() - this.loadTime;
        long numMilliSecondsPerHour = 3600000;
        return (dateDifference < (numMilliSecondsPerHour * numHours));
    }

    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    public boolean isAdAvailable() {
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4);
    }


    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
       /* if (currentActivity instanceof SplashActivity)
            Log.d("APPLICATION", "SplashActivity: ......s......."+activity.getLocalClassName());
        currentActivity = activity;
        if (currentActivity instanceof FirstActivity)
            Log.d("APPLICATION", "FirstActivity: .....s........"+activity.getLocalClassName());

        currentActivity = activity;
        if (currentActivity instanceof MainActivity)
            Log.d("APPLICATION", "MainActivity: ......s......."+activity.getLocalClassName());

        currentActivity = activity;
        if (currentActivity instanceof Category)
            Log.d("APPLICATION", "Category: ........s....."+activity.getLocalClassName());

        currentActivity = activity;
        if (currentActivity instanceof CategoryActivity )
            Log.d("APPLICATION", "CategoryActivity: ....s........."+activity.getLocalClassName());
*/
        currentActivity = activity;
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
       /* if (currentActivity instanceof SplashActivity)
            Log.d("APPLICATION", "SplashActivity: ......r......."+activity.getLocalClassName());
        currentActivity = activity;
        if (currentActivity instanceof FirstActivity)
            Log.d("APPLICATION", "FirstActivity: ........r....."+activity.getLocalClassName());

        currentActivity = activity;
        if (currentActivity instanceof MainActivity)
            showAdIfAvailable();
            Log.d("APPLICATION", "MainActivity: ......r......."+activity.getLocalClassName());

        currentActivity = activity;
        if (currentActivity instanceof Category)
            Log.d("APPLICATION", "Category: .......r......"+activity.getLocalClassName());

        currentActivity = activity;
        if (currentActivity instanceof CategoryActivity )
            Log.d("APPLICATION", "CategoryActivity: ......r......."+activity.getLocalClassName());
*/
        currentActivity = activity;

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        currentActivity = null;
    }

    @OnLifecycleEvent(ON_START)
    public void onStart()
    {
        if (!(currentActivity instanceof SplashScreen))
            showAdIfAvailable();
             Log.d(LOG_TAG, "onStart");
    }


}
