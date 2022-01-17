package com.doctorcarepath.GoogleAds;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;


import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.doctorcarepath.R;
import com.doctorcarepath.Util.Utils;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

public class AdManager {

    public static AdManagerInterstitialAd interstitialAd;
    public static LottieAnimationView animationView;
    public static Context context;

    public static AdManagerInterstitialAd back_interstitialAd;
    public static LottieAnimationView back_animationView;
    public static Context back_context;
    public static AdLoader adLoader;
    public static AdLoader.Builder builder;
    static Dialog back_ad_dialog;
    static GoogleAdListener listener;
    static Dialog ad_dialog;
    static String interstitialAdBanner;
    static String backinterstitialAdBanner;

    public static void loadGoogleInterstitialAds(Context mContext, String interstitial_id, String banner_url) {

        context = mContext;

        listener = (GoogleAdListener) context;

        animationView = new LottieAnimationView(context);

        animationView.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter(ResourcesCompat.getColor(context.getResources(), R.color.white, (Resources.Theme) null), PorterDuff.Mode.SRC_ATOP);
                    }
                }
        );

        animationView.setAnimation(R.raw.loader_splash);
        animationView.loop(true);

        ad_dialog = new Dialog(context);
        ad_dialog.requestWindowFeature(1);
        ad_dialog.setContentView(animationView);
        ad_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        ad_dialog.setCancelable(false);

        if (ad_dialog.isShowing()) {
            ad_dialog.dismiss();
        }

        interstitialAdBanner = banner_url;


//        loadInterstitialBanner(mContext, banner_url);

        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();
        AdManagerInterstitialAd.load(
                context,
                interstitial_id,
                adRequest,
                new AdManagerInterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull AdManagerInterstitialAd ad) {
                        interstitialAd = ad;
                        ad.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {

                                        if (ad_dialog.isShowing()) {
                                            ad_dialog.cancel();
                                        }

                                        listener.OnAdClose();
                                        interstitialAd = null;
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        Log.d("TAG", "The ad failed to show.");
                                        interstitialAd = null;

                                        if (ad_dialog.isShowing()) {
                                            ad_dialog.cancel();
                                        }
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                        Log.d("TAG", "The ad was shown.");
                                    }
                                });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.d("ADS_LOGS", "The ad was failed.");
                        interstitialAd = null;

                        if (ad_dialog.isShowing()) {
                            ad_dialog.cancel();
                        }
                    }
                });
    }

    public static void loadGoogleInterstitialAds(Context mContext, String interstitial_id, String banner_url,Boolean enable) {
        context = mContext;

        listener = (GoogleAdListener) context;

        if (!enable.booleanValue()){


        }
        else {


            animationView = new LottieAnimationView(context);

            animationView.addValueCallback(
                    new KeyPath("**"),
                    LottieProperty.COLOR_FILTER,
                    new SimpleLottieValueCallback<ColorFilter>() {
                        @Override
                        public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                            return new PorterDuffColorFilter(ResourcesCompat.getColor(context.getResources(), R.color.white, (Resources.Theme) null), PorterDuff.Mode.SRC_ATOP);
                        }
                    }
            );

            animationView.setAnimation(R.raw.loader_splash);
            animationView.loop(true);

            ad_dialog = new Dialog(context);
            ad_dialog.requestWindowFeature(1);
            ad_dialog.setContentView(animationView);
            ad_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            ad_dialog.setCancelable(false);

            if (ad_dialog.isShowing()) {
                ad_dialog.dismiss();
            }

            interstitialAdBanner = banner_url;

//        loadInterstitialBanner(mContext, banner_url);

            AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();
            AdManagerInterstitialAd.load(
                    context,
                    interstitial_id,
                    adRequest,
                    new AdManagerInterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull AdManagerInterstitialAd ad) {
                            Log.d("TAG", "onAdLoaded.....");
                            interstitialAd = ad;
                            ad.setFullScreenContentCallback(
                                    new FullScreenContentCallback() {
                                        @Override
                                        public void onAdDismissedFullScreenContent() {

                                            if (ad_dialog.isShowing()) {
                                                ad_dialog.cancel();
                                            }

                                            listener.OnAdClose();
                                            interstitialAd = null;
                                        }

                                        @Override
                                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                                            Log.d("TAG", "The ad failed to show.");
                                            interstitialAd = null;

                                            if (ad_dialog.isShowing()) {
                                                ad_dialog.cancel();
                                            }
                                        }

                                        @Override
                                        public void onAdShowedFullScreenContent() {
                                            Log.d("TAG", "The ad was shown.");
                                        }
                                    });
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            Log.d("ADS_LOGS", "The ad was failed.");
                            interstitialAd = null;

                            if (ad_dialog.isShowing()) {
                                ad_dialog.cancel();
                            }
                        }
                    });

        }

    }
    private static void loadInterstitialBanner(Context c, String bannerurl) {

//        if(failed_ad_dialog != null)
//            return;

        View interstitialView;
        Dialog failed_ad_dialog;

        interstitialView = LayoutInflater.from(c).inflate(R.layout.interstitial_banner_layout, null);

        ImageView interstitial_banner, btnClosebanner;

        interstitial_banner = interstitialView.findViewById(R.id.interstitial_banner);
        btnClosebanner = interstitialView.findViewById(R.id.btnClosebanner);

        failed_ad_dialog = new Dialog(c, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        if (interstitialView.getParent() != null)
            ((ViewGroup) interstitialView.getParent()).removeView(interstitialView);
        failed_ad_dialog.setContentView(interstitialView);
        failed_ad_dialog.setCancelable(false);

        File file = new File(c.getCacheDir().getAbsolutePath() + "/" + Utils.INTERSTITIAL_AD_BANNER_NAME);

        Log.d("InterBanner", file.exists() + "  " + file.getAbsolutePath());

        if (!file.exists()) {
            Utils.downloadOpenAdBanner(c, Utils.getInterstitialAdBanner(c));
            Glide.with(c).load(bannerurl).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(interstitial_banner);
        } else
            Glide.with(c).load(file.getAbsolutePath()).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(interstitial_banner);

        interstitial_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.IS_FAILED_DIALOG_SHOW = false;
                failed_ad_dialog.dismiss();
                listener.OnAdClose();
                listener.OnInterstitialAdClick();
            }
        });

        btnClosebanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.IS_FAILED_DIALOG_SHOW = false;
                failed_ad_dialog.dismiss();
                listener.OnAdClose();
            }
        });

        failed_ad_dialog.show();

    }

    public static void showInterstitialAd()
    {
        try {
            //context.stopService(new Intent(context, BackgroundSoundService.class));
            animationView.playAnimation();
            if (!((Activity) context).isFinishing()) {
                if (Utils.getEnableAdLoadingDialog(context))
                    ad_dialog.show();
            }

            if (interstitialAd != null) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (ad_dialog.isShowing()) {
                            ad_dialog.cancel();
                        }
                        if (interstitialAd != null) {
                            interstitialAd.show((Activity) context);
                        }
                    }
                }, 2000);
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (ad_dialog.isShowing())
                        {
                            ad_dialog.cancel();
                        }
                        if (!((Activity) context).isFinishing()) {
                            Utils.IS_FAILED_DIALOG_SHOW = true;
                            loadInterstitialBanner(context, interstitialAdBanner);
                        }
                    }
                }, 2000);
            }
        } catch (Exception e) {
            Log.d("ADS_LOGS", "----------exception ------" + e);
        }
    }

/*
    public static void backLoadGoogleInterstitialAds(Context c, String backInterstitial_id, String banner_url) {

        back_context = c;

        listener = (GoogleAdListener) back_context;

        back_animationView = new LottieAnimationView(back_context);

        back_animationView.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter(ResourcesCompat.getColor(back_context.getResources(), R.color.white, (Resources.Theme) null), PorterDuff.Mode.SRC_ATOP);
                    }
                }
        );

        back_animationView.setAnimation(R.raw.loader_splash);
        back_animationView.loop(true);
        back_ad_dialog = new Dialog(back_context);
        back_ad_dialog.requestWindowFeature(1);
        back_ad_dialog.setContentView(back_animationView);
        back_ad_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        back_ad_dialog.setCancelable(false);
//
        if (back_ad_dialog.isShowing()) {
            back_ad_dialog.dismiss();
        }

        backinterstitialAdBanner = banner_url;

//        loadBackInterstitialBanner(back_context, banner_url);

        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();
        AdManagerInterstitialAd.load(
                back_context,
                backInterstitial_id,
                adRequest,
                new AdManagerInterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull AdManagerInterstitialAd ad) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.

                        back_interstitialAd = ad;
                        //                        Log.i(TAG, "onAdLoaded");
                        ad.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {

                                        if (back_ad_dialog.isShowing()) {
                                            back_ad_dialog.cancel();
                                        }

                                        listener.OnAdClose();

                                        back_interstitialAd = null;
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        back_interstitialAd = null;

                                        if (back_ad_dialog.isShowing()) {
                                            back_ad_dialog.cancel();
                                        }
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                    }
                                });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        back_interstitialAd = null;
                        if (back_ad_dialog.isShowing()) {
                            back_ad_dialog.cancel();
                        }
                    }
                });
    }
*/
   /* public static void loadGoogleNativeAd100dp(Context context) {

        builder = new AdLoader.Builder(context, Utils.getAdmobNativeId2(context))
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd)
                    {
                        // Assumes you have a placeholder Linearlauout in your View layout
                        // (with id fl_adplaceholder) where the ad is to be placed.
                       LinearLayout linearLayout =((Activity) context).findViewById(R.id.googleNativeAd);
                        // Assumes that your ad layout is in a file call native_ad_layout.xml
                        // in the res/layout folder
                        Log.d("ADS_LOGS", "------------native ad loaded called---------------");
                        NativeAdView adView = (NativeAdView) ((Activity) context).getLayoutInflater()
                                .inflate(R.layout.ad_google_layout_100_dp, null);
                        // This method sets the text, images and the native ad, etc into the ad
                        // view.
                        populateNativeAdView100dp(nativeAd, adView);
                        linearLayout.removeAllViews();
                        linearLayout.addView(adView);
                    }
                });

        adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);

                Log.d("ADS_LOGS", "------------ERROR--------------" + loadAdError.getMessage());

            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.d("ADS_LOGS", "------------Ad is loaded, ready to display--------------");

            }
        }).build();

        adLoader.loadAd(new AdRequest.Builder().build());

    }*/


    public static void populateNativeAdView100dp(NativeAd nativeAd, NativeAdView adView) {

//        adView.setMediaView((MediaView) adView.findViewById(R.id.ad_media));
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
//        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
//        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
//        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

//        if (nativeAd.getBody() == null) {
//            adView.getBodyView().setVisibility(View.INVISIBLE);
//
//        } else {
//            adView.getBodyView().setVisibility(View.VISIBLE);
//            Log.d("ADS_LOGS", "------------native ad loaded called---------------" + nativeAd.getCallToAction());
//
//            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
//        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }
        adView.setNativeAd(nativeAd);

    }



    private static void loadBackInterstitialBanner(Context c, String bannerurl) {

//        if(back_failed_ad_dialog != null)
//            return;

        Dialog back_failed_ad_dialog;
        View backinterstitialView;

        backinterstitialView = LayoutInflater.from(c).inflate(R.layout.interstitial_banner_layout, null);

        ImageView interstitial_banner, btnClosebanner;

        interstitial_banner = backinterstitialView.findViewById(R.id.interstitial_banner);
        btnClosebanner = backinterstitialView.findViewById(R.id.btnClosebanner);

        back_failed_ad_dialog = new Dialog(c, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        if (backinterstitialView.getParent() != null)
            ((ViewGroup) backinterstitialView.getParent()).removeView(backinterstitialView);
        back_failed_ad_dialog.setContentView(backinterstitialView);
        back_failed_ad_dialog.setCancelable(false);

        File file = new File(c.getCacheDir().getAbsolutePath() + "/" + Utils.INTERSTITIAL_AD_BANNER_NAME);

        Log.d("InterBanner", file.exists() + "  " + file.getAbsolutePath());

        if (!file.exists()) {
            Utils.downloadOpenAdBanner(c, Utils.getInterstitialAdBanner(c));
            Glide.with(c).load(bannerurl).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(interstitial_banner);
        } else
            Glide.with(c).load(file.getAbsolutePath()).into(interstitial_banner);

        Glide.with(c).load(bannerurl).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(interstitial_banner);

        interstitial_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.IS_BACK_FAILED_DIALOG_SHOW = false;
                back_failed_ad_dialog.dismiss();
                listener.OnAdClose();
                listener.OnInterstitialAdClick();
            }
        });

        btnClosebanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.IS_BACK_FAILED_DIALOG_SHOW = false;
                back_failed_ad_dialog.dismiss();
                listener.OnAdClose();
            }
        });

        back_failed_ad_dialog.show();

    }
    public static void showInterstitialAd( Context mContext,Boolean enable) {
        context = mContext;

        listener = (GoogleAdListener) context;

        Log.d("ADS_LOGS", "value"+enable);
        if (!enable.booleanValue()){

            listener.OnAdClose();
        }
        else {
            try {
                //context.stopService(new Intent(context, BackgroundSoundService.class));
                animationView.playAnimation();
                if (!((Activity) context).isFinishing()) {
                    if (Utils.getEnableAdLoadingDialog(context))
                        ad_dialog.show();
                }

                if (interstitialAd != null) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (ad_dialog.isShowing()) {
                                ad_dialog.cancel();
                            }
                            if (interstitialAd != null) {
                                interstitialAd.show((Activity) context);
                            }
                        }
                    }, 2000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (ad_dialog.isShowing()) {
                                ad_dialog.cancel();
                            }
                            if (!((Activity) context).isFinishing()) {
                                Utils.IS_FAILED_DIALOG_SHOW = true;
                                loadInterstitialBanner(context, interstitialAdBanner);
                            }
                        }
                    }, 2000);
                }
            } catch (Exception e) {
                Log.d("ADS_LOGS", "----------exception ------" + e);
            }
        }

    }
    public static void backShowInterstitialAd() {

        try {
            back_animationView.playAnimation();
            if (!((Activity) back_context).isFinishing()) {
                if (Utils.getEnableAdLoadingDialog(context))
                    back_ad_dialog.show();
            }
            if (back_interstitialAd != null) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (back_ad_dialog.isShowing()) {
                            back_ad_dialog.cancel();
                        }
                        if (back_interstitialAd != null) {

                            back_interstitialAd.show((Activity) back_context);
                        }
                    }
                }, 2000);
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (back_ad_dialog.isShowing()) {
                            back_ad_dialog.cancel();
                        }

                        if (!((Activity) back_context).isFinishing()) {
                            Utils.IS_BACK_FAILED_DIALOG_SHOW = true;

                            loadBackInterstitialBanner(back_context, backinterstitialAdBanner);
                        }
                    }
                }, 2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void backLoadGoogleInterstitialAds(Context c, String backInterstitial_id, String banner_url,Boolean enable) {

        back_context = c;

        listener = (GoogleAdListener) back_context;

        back_animationView = new LottieAnimationView(back_context);



        if (!enable.booleanValue()){

        }
        else {

            back_animationView.addValueCallback(
                    new KeyPath("**"),
                    LottieProperty.COLOR_FILTER,
                    new SimpleLottieValueCallback<ColorFilter>() {
                        @Override
                        public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                            return new PorterDuffColorFilter(ResourcesCompat.getColor(back_context.getResources(), R.color.white, (Resources.Theme) null), PorterDuff.Mode.SRC_ATOP);
                        }
                    }
            );

            back_animationView.setAnimation(R.raw.loader_splash);
            back_animationView.loop(true);

            back_ad_dialog = new Dialog(back_context);
            back_ad_dialog.requestWindowFeature(1);
            back_ad_dialog.setContentView(back_animationView);
            back_ad_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            back_ad_dialog.setCancelable(false);
//
            if (back_ad_dialog.isShowing()) {
                back_ad_dialog.dismiss();
            }

            backinterstitialAdBanner = banner_url;

//        loadBackInterstitialBanner(back_context, banner_url);

            AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();
            AdManagerInterstitialAd.load(
                    back_context,
                    backInterstitial_id,
                    adRequest,
                    new AdManagerInterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull AdManagerInterstitialAd ad) {
                            // The mInterstitialAd reference will be null until
                            // an ad is loaded.

                            back_interstitialAd = ad;
                            //                        Log.i(TAG, "onAdLoaded");
                            ad.setFullScreenContentCallback(
                                    new FullScreenContentCallback() {
                                        @Override
                                        public void onAdDismissedFullScreenContent() {

                                            if (back_ad_dialog.isShowing()) {
                                                back_ad_dialog.cancel();
                                            }

                                            listener.OnAdClose();

                                            back_interstitialAd = null;
                                        }

                                        @Override
                                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                                            back_interstitialAd = null;

                                            if (back_ad_dialog.isShowing()) {
                                                back_ad_dialog.cancel();
                                            }
                                        }

                                        @Override
                                        public void onAdShowedFullScreenContent() {
                                        }
                                    });
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            back_interstitialAd = null;
                            if (back_ad_dialog.isShowing()) {
                                back_ad_dialog.cancel();
                            }
                        }
                    });
        }

    }



    public static void backShowInterstitialAd(Context mContext,Boolean enable) {

        context = mContext;

        listener = (GoogleAdListener) context;


        if (!enable.booleanValue()){
            listener.OnAdClose();
        }
        else {
            try {

                //context.stopService(new Intent(context, BackgroundSoundService.class));

                back_animationView.playAnimation();
                if (!((Activity) back_context).isFinishing()) {
                    if (Utils.getEnableAdLoadingDialog(context))
                        back_ad_dialog.show();
                }
                if (back_interstitialAd != null) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (back_ad_dialog.isShowing()) {
                                back_ad_dialog.cancel();
                            }
                            if (back_interstitialAd != null) {

                                back_interstitialAd.show((Activity) back_context);
                            }
                        }
                    }, 2000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (back_ad_dialog.isShowing()) {
                                back_ad_dialog.cancel();
                            }

                            if (!((Activity) back_context).isFinishing()) {
                                Utils.IS_BACK_FAILED_DIALOG_SHOW = true;

                                loadBackInterstitialBanner(back_context, backinterstitialAdBanner);
                            }
                        }
                    }, 2000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public static void loadGoogleNativeAd(Context mContext, String native_id, String bannerurl, LinearLayout linearLayout, Boolean is200) {

        builder = new AdLoader.Builder(mContext, native_id)
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        LinearLayout linearLayoutCompat = linearLayout;
                        Log.d("ADS_LOGS", "------------native ad loaded called---------------");
                        NativeAdView adView;
                        if (is200) {
                            adView = (NativeAdView) ((Activity) mContext).getLayoutInflater()
                                    .inflate(R.layout.native_ads_200dp, null);
                            populateNativeAdView200dp(nativeAd, adView);
                        } else {
                            adView = (NativeAdView) ((Activity) mContext).getLayoutInflater()
                                    .inflate(R.layout.native_ads_300dp, null);
                            populateNativeAdView300dp(nativeAd, adView);
                        }
                        linearLayoutCompat.removeAllViews();
                        linearLayoutCompat.addView(adView);
                    }
                });

        adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);

                loadNativeAdBanner(mContext, bannerurl, linearLayout, is200);
                Log.d("ADS_LOGS", "------------ERROR--------------" + loadAdError.toString());

            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.d("ADS_LOGS", "------------Ad is loaded, ready to display--------------");

            }
        }).build();

        adLoader.loadAd(new AdRequest.Builder().build());

    }

    private static void loadNativeAdBanner(Context mContext, String nativeBanner, LinearLayout linearLayout, Boolean is200) {
        try {
            View view;

            if (is200)
                view = LayoutInflater.from(mContext).inflate(R.layout.native_ads_200dp, null, false);
            else
                view = LayoutInflater.from(mContext).inflate(R.layout.native_ads_300dp, null, false);

            ImageView appinstall_image, imgAdStore;
            TextView ad_headline, ad_body, ad_store, ad_call_to_action;
            RatingBar ad_stars;

            MediaView mediaView = view.findViewById(R.id.ad_media);
            mediaView.setVisibility(View.GONE);

            appinstall_image = view.findViewById(R.id.appinstall_image);
            ad_headline = view.findViewById(R.id.ad_headline);
            ad_body = view.findViewById(R.id.ad_body);
            ad_store = view.findViewById(R.id.ad_store);
            imgAdStore = view.findViewById(R.id.imgAdStore);
            ad_call_to_action = view.findViewById(R.id.ad_call_to_action);
            ad_stars = view.findViewById(R.id.ad_stars);

            JSONArray array = new JSONArray(Utils.getNativeAdBanner(mContext));

            JSONObject object = array.getJSONObject(0);

            Glide.with(mContext).load(object.get(Utils.NATIVE_APP_INSTALL_ICON).toString()).into(appinstall_image);

            Glide.with(mContext).load(object.get(Utils.NATIVE_AD_STORE_ICON).toString()).into(imgAdStore);

            ad_headline.setText(object.get(Utils.NATIVE_AD_HEADLINE).toString());
            ad_body.setText(object.get(Utils.NATIVE_AD_BODY).toString());
            ad_store.setText(object.get(Utils.NATIVE_AD_STORE).toString());

            ad_call_to_action.setText(object.get(Utils.NATIVE_AD_CALL_TO_ACTION_NAME).toString());

            ad_stars.setRating(Float.parseFloat(object.get(Utils.NATIVE_AD_STAR).toString()));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        listener.OnNativeAdClick();
                        Utils.openChromeCustomTabUrl(mContext, object.get(Utils.NATIVE_AD_CALL_TO_ACTION).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            linearLayout.removeAllViews();

            linearLayout.addView(view);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void populateNativeAdView300dp(NativeAd nativeAd, NativeAdView adView) {

        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.appinstall_image));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setMediaView(adView.findViewById(R.id.ad_media));
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());

        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);

        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            Log.d("ADS_LOGS", "------------native ad loaded called---------------" + nativeAd.getCallToAction());

            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getMediaContent() == null)
            adView.getMediaView().setVisibility(View.GONE);
        else {
            ((MediaView) adView.getMediaView()).setMediaContent(
                    nativeAd.getMediaContent());
            adView.getMediaView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }
        adView.setNativeAd(nativeAd);

    }

    public static void populateNativeAdView200dp(NativeAd nativeAd, NativeAdView adView) {

        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.appinstall_image));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());

        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);

        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            Log.d("ADS_LOGS", "------------native ad loaded called---------------" + nativeAd.getCallToAction());

            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }
        adView.setNativeAd(nativeAd);

    }

//    public static void loadGoogleBannerAd(Context mContext, String banner_id, String bannerurl,LinearLayout linearLayout) {
//        AdView adView = new AdView(mContext);
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId(banner_id);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);
//
//        adView.setAdListener(new AdListener() {
//            @Override
//            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                super.onAdFailedToLoad(loadAdError);
//
//                loadBannerAdBanner(mContext,bannerurl,linearLayout);
//            }
//        });
//
//        linearLayout.removeAllViews();
//
//        linearLayout.addView(adView);
//    }
//
//    private static void loadBannerAdBanner(Context mContext,String bannerurl,LinearLayout linearLayout) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.banner_layout,null,false);
//
//        Glide.with(mContext).load(bannerurl).into((ImageView) view);
//
//        linearLayout.removeAllViews();
//
//        linearLayout.addView(view);
//
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.OnBannerAdClick();
//            }
//        });
//    }

}
