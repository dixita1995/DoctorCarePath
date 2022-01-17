package com.doctorcarepath;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import static com.doctorcarepath.R.*;
import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.doctorcarepath.GoogleAds.AdManager;
import com.doctorcarepath.GoogleAds.GoogleAdListener;
import com.doctorcarepath.Util.Utils;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;

import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.appopen.AppOpenAd;

import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class SplashScreen extends AppCompatActivity implements GoogleAdListener {
    RequestQueue mRequestQueue;
    AppOpenAd.AppOpenAdLoadCallback loadCallback;
   static UnifiedNativeAd unifiedNativeAd1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        try {
            mRequestQueue = Volley.newRequestQueue(this);

            if (!Utils.isNetworkAvailable(SplashScreen.this))
                showInternetDialog();
            else
                fetchJsonResponse();
          //AdRegistration.setAppKey("0123456789ABCDEF0123456789ABCDEF");
            /*AppOpenManager appOpenManager = new AppOpenManager(getApplication());
            AppOpenManager.AD_UNIT_ID = "ca-app-pub-5047541701842745/7932691060";
            admobInterstitialAd = new InterstitialAd(this);
            admobInterstitialAd.setAdUnitId(getString(R.string.instrtrialid));
            admobInterstitialAd.loadAd(new AdRequest.Builder().build());


            admobInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    //   Toast.makeText(SecretSplashActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAdFailedToLoad(LoadAdError errorCode) {
                    try {


                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getMessage();
                    }
                    //   Toast.makeText(SecretSplashActivity.this,                        "onAdFailedToLoad() with error code: " + errorCode,                        Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onAdClosed() {
                    Intent i = new Intent(SplashScreen.this, TypeOfDoctor.class);
                    startActivity(i);
                    finish();

                }
            });
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    try {

                        if (admobInterstitialAd.isLoaded()) {
                            admobInterstitialAd.show();
                        } else {
                            Intent i = new Intent(SplashScreen.this, TypeOfDoctor.class);
                            startActivity(i);
                            finish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, 3000);*/
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    private void showInternetDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.no_internet_dialog);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setCancelable(false);
        Button btnretry = dialog.findViewById(R.id.btnretry);

        btnretry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Utils.isNetworkAvailable(SplashScreen.this)) {
                    dialog.dismiss();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.show();
                        }
                    }, 300);
                } else {
                    dialog.dismiss();
                    fetchJsonResponse();

                }
            }
        });
        dialog.show();
    }

    private void fetchJsonResponse() {
        // Pass second argument as "null" for GET requests

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, "https://jsonkeeper.com/b/6C39", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Utils.storeIds(SplashScreen.this,
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

                            Utils.downloadOpenAdBanner(SplashScreen.this, Utils.getOpenAdBanner(SplashScreen.this));
                            Utils.downloadInterstitialAdBanner(SplashScreen.this, Utils.getInterstitialAdBanner(SplashScreen.this));
                            Adload();
                            OpenAppAds();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("JSON_RESPONSE", "-------------EXCEPTIon-------" + e);
                            LoadAds();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                startMain();

            }
        });

        mRequestQueue.add(req);
    }
    private void LoadAds() {
        hideSystemUI();
        new Handler().postDelayed(new Runnable() {

            public void run() {
                AdManager.showInterstitialAd(SplashScreen.this, Utils.getInterstial(SplashScreen.this));
            }
        }, 3000);

    }
    private void Adload() {
        AdManager.loadGoogleInterstitialAds(SplashScreen.this,
                Utils.getAdmobInterstitialId1(this),
                Utils.getInterstitialAdBanner(this), Utils.getInterstial(this));

    }
    public void OpenAppAds() {

        this.loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
            public void onAdLoaded(AppOpenAd appOpenAd) {
                Log.d("Appopenads12", "load ");
                appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    public void onAdDismissedFullScreenContent() {
                        SplashScreen.this.OnAdClose();

                    }

                    public void onAdFailedToShowFullScreenContent(AdError adError) {

                    }

                    public void onAdShowedFullScreenContent() {

                    }
                });
                appOpenAd.show(SplashScreen.this);
            }

            public void onAdFailedToLoad(LoadAdError loadAdError) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AdManager.showInterstitialAd(SplashScreen.this, Utils.getInterstial(SplashScreen.this));
                    }
                }, 7000);

                Log.d("Appopenads12", "failed " + loadAdError.getResponseInfo());

            }
        };
        AppOpenAd.load((Context) this, Utils.getAdmobOpenIdId2(SplashScreen.this), new AdRequest.Builder().build(), 1, this.loadCallback);
    }

    private void hideSystemUI() {
        getWindow().addFlags(128);
        getWindow().getDecorView().setSystemUiVisibility(3846);
    }

    public void startMain() {
        Intent i = new Intent(SplashScreen.this, TypeOfDoctor.class);
        startActivity(i);
        finish();
    }

    @Override
    public void OnAdClose() {
        startMain();
    }

    @Override
    public void OnAdError(int i) {

    }

    @Override
    public void OnBannerAdClick() {

    }

    @Override
    public void OnNativeAdClick() {

    }

    @Override
    public void OnInterstitialAdClick() {
        Utils.openChromeCustomTabUrl(this, Utils.getInterstitialAdBannerCallback(this));
    }

    @Override
    public void OnOpenAdClick() {

    }


  /*  public void Admob(AppCompatActivity activity, String AD_MANAGER_AD_UNIT_ID, FrameLayout frm) {
        AdLoader.Builder builder = new AdLoader.Builder(activity, AD_MANAGER_AD_UNIT_ID);

        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                // If this callback occurs after the activity is destroyed, you must call
                // destroy and return or you may get a memory leak.
                boolean isDestroyed = false;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    isDestroyed = isDestroyed();
                }
                if (isDestroyed || isFinishing() || isChangingConfigurations()) {
                    unifiedNativeAd.destroy();
                    return;
                }
                // You must call destroy on old ads when you are done with them,
                // otherwise you will have a memory leak.
                if (unifiedNativeAd1 != null) {
                    unifiedNativeAd1.destroy();
                }
                unifiedNativeAd1 = unifiedNativeAd;

                UnifiedNativeAdView adView =
                        (UnifiedNativeAdView) activity.getLayoutInflater()
                                .inflate(R.layout.ad_unified, null);
                SplashScreen.populateUnifiedNativeAdView(unifiedNativeAd, adView);
//                frameLayout.removeAllViews();
                frm.addView(adView);
            }
        });
        VideoOptions videoOptions =
                new VideoOptions.Builder().setStartMuted(true).build();

        NativeAdOptions adOptions =
                new NativeAdOptions.Builder().setVideoOptions(videoOptions).build();

        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader =
                builder
                        .withAdListener(
                                new AdListener() {
                                    @Override
                                    public void onAdFailedToLoad(LoadAdError loadAdError) {


                                    }
                                })
                        .build();

        adLoader.loadAd(new PublisherAdRequest.Builder().build());

    }*/

   /* public static void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {
        // Set the media view.
        adView.setMediaView((MediaView) adView.findViewById(R.id.ad_media));

        // Set other ad assets.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // The headline and mediaContent are guaranteed to be in every UnifiedNativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
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

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd);

        // Get the video controller for the ad. One will always be provided, even if the ad doesn't b
        // have a video asset.
        VideoController vc = nativeAd.getVideoController();

        // Updates the UI to say whether or not this ad has a video asset.
        if (vc.hasVideoContent()) {
            // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
            // VideoController will call methods on this object when events occur in the video
            // lifecycle.
            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                @Override
                public void onVideoEnd() {
                    // Publishers should allow native ads to complete video playback before
                    // refreshing or replacing them with another ad in the same UI location.
                    super.onVideoEnd();
                }
            });
        }


    }*/

}
