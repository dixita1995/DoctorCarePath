package com.doctorcarepath.Util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import androidx.browser.customtabs.CustomTabsIntent;


import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Utils {

    public static String OPEN_AD_BANNER_NAME = "openAdBanner.png";
    public static String INTERSTITIAL_AD_BANNER_NAME = "interstitialAdBanner.png";


    public static String BASE_URL = "base_url";

    public static Boolean IS_FAILED_DIALOG_SHOW = false;

    public static String ENABLE_INTERSTIAL = "enable_interstial";
    public static String ENABLE_BACK_INTERSTIAL = "enable_back_interstial";

    public static Boolean IS_BACK_FAILED_DIALOG_SHOW = false;

    public static String ENABLE_LOCK_GLTEAM = "enable_lock_glteam";

    public static String ENABLE_SPIN_WIN_DIALOG = "enable_spin_win_dialog";

    public static String ENABLE_IN_APP_REVIEW = "enable_in_app_review";
    public static String IN_APP_REVIEW = "in_app_review";

    public static String CUSTOM_TAB_URL = "custom_tab_url";

    public static String ENABLE_2_NATIVE_AD = "enable_2_native_ad";

    public static String ENABLE_AD_LOADING_DIALOG = "enable_ad_loading_dialog";

    public static String PRIVAY_POLICY_URL = "privacy_policy_url";

    public static String NATIVE_AD_BANNER_CALLBACK = "native_ad_banner_callback";

    public static String NATIVE_APP_INSTALL_ICON = "native_app_install_icon";
    public static String NATIVE_AD_HEADLINE = "native_ad_headline";
    public static String NATIVE_AD_BODY = "native_ad_body";
    public static String NATIVE_AD_STORE = "native_ad_store";
    public static String NATIVE_AD_STORE_ICON = "native_ad_store_icon";
    public static String NATIVE_AD_CALL_TO_ACTION = "native_ad_call_to_action";
    public static String NATIVE_AD_CALL_TO_ACTION_NAME = "native_ad_call_to_action_name";
    public static String NATIVE_AD_STAR = "native_ad_star";


    public static String MORE_APPS_URL = "more_apps_url";
    public static String PLAY_WIN = "play_win";

    //ads
    public static String ADMOB_NATIVE_ID1 = "admob_native_id1";
    public static String ADMOB_NATIVE_ID2 = "admob_native_id2";
    public static String ADMOB_NATIVE_ID3 = "admob_native_id3";
    public static String ADMOB_NATIVE_ID4 = "admob_native_id4";

    public static String ADMOB_OPEN_ID_ID1 = "admob_open_id1";
    public static String ADMOB_OPEN_ID_ID2 = "admob_open_id2";

    public static String ADMOB_REWARD_ID1 = "admob_reward_id1";
    public static String ADMOB_REWARD_ID2 = "admob_reward_id2";

    public static String ADMOB_INTERSTITIAL_ID1 = "admob_interstitial_id1";
    public static String ADMOB_INTERSTITIAL_ID2 = "admob_interstitial_id2";
    public static String ADMOB_INTERSTITIAL_ID3 = "admob_interstitial_id3";
    public static String ADMOB_INTERSTITIAL_ID4 = "admob_interstitial_id4";
    public static String ADMOB_INTERSTITIAL_ID5 = "admob_interstitial_id5";

    public static String ADMOB_INTERSTITIAL_BACK_ID1 = "admob_interstitial_back_id1";
    public static String ADMOB_INTERSTITIAL_BACK_ID2 = "admob_interstitial_back_id2";
    public static String ADMOB_INTERSTITIAL_BACK_ID3 = "admob_interstitial_back_id3";
    public static String ADMOB_INTERSTITIAL_BACK_ID4 = "admob_interstitial_back_id4";

    public static String ADMOB_BANNER_ID1 = "admob_banner_id1";
    public static String ADMOB_BANNER_ID2 = "admob_banner_id2";
    public static String ADMOB_BANNER_ID3 = "admob_banner_id3";
    public static String ADMOB_BANNER_ID4 = "admob_banner_id4";
    public static String PREF_RATE = "RATE";
    public static String PREF_INAPPREVIEW = "PREF_INAPPREVIEW";

    public static String INTERSTITIAL_AD_BANNER = "interstitial_ads_banner";
    public static String INTERSTITIAL_AD_BANNER_CALLBACK = "interstitial_ads_banner_callback";

    public static String NATIVE_AD_BANNER = "native_ad_banner";

    public static String OPEN_AD_BANNER = "open_ad_banner";
    public static String OPEN_AD_BANNER_CALLBACK = "open_ad_banner_callback";


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static int getRate(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);
        return preferences.getInt(PREF_RATE, 0);
    }

    public static void setRate(Context context,int rate) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(PREF_RATE, rate);
        edit.apply();
    }

    public static Boolean getInappReviews(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);
        return preferences.getBoolean(PREF_INAPPREVIEW, false);
    }

    public static void setInappReviews(Context context,Boolean rate) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(PREF_INAPPREVIEW, rate);
        edit.apply();
    }

    public static String parseDateToddMMyyyy(String time) {
        String inputPattern = "dd-MM-yyyy hh:mm";
        String outputPattern = "MMM dd | hh:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void openChromeCustomTabUrl(Context context, String webUrl) {
        try {
            if (isAppInstalled(context, "com.android.chrome")) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                int coolorInt = Color.parseColor("#0086fe");
                builder.setToolbarColor(coolorInt);
                //       builder.setStartAnimations(getApplicationContext(), R.anim.slide_in_right, R.anim.slide_out_left);
                //        builder.setExitAnimations(getApplicationContext(), R.anim.slide_in_left, R.anim.slide_out_right);
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.intent.setPackage("com.android.chrome");
                customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                customTabsIntent.launchUrl(context, Uri.parse(webUrl));
            } else {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                int coolorInt = Color.parseColor("#0086fe");
                builder.setToolbarColor(coolorInt);
                //   builder.setStartAnimations(getApplicationContext(), R.anim.slide_in_right, R.anim.slide_out_left);
                //     builder.setExitAnimations(getApplicationContext(), R.anim.slide_in_left, R.anim.slide_out_right);
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                customTabsIntent.launchUrl(context, Uri.parse(webUrl));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isAppInstalled(Context context, String uri) {
        PackageManager pmm = context.getPackageManager();
        boolean installed = false;
        try {
            pmm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    public static void storeIds(Context context,Boolean enable_spin_win_dialog,
                                Boolean enable_ad_loading_dialog, Boolean enable_in_app_review,
                                Boolean enable_2_native_ad, String custom_tab_url, String base_url,
                                String admob_native_id1,
                                String admob_native_id2, String admob_native_id3, String admob_native_id4,
                                String admob_open_id1, String admob_open_id2,
                                String admob_interstitial_id1, String admob_interstitial_id2,
                                String admob_interstitial_id3, String admob_interstitial_id4,
                                String admob_interstitial_id5, String admob_interstitial_back_id1,
                                String admob_interstitial_back_id2, String admob_interstitial_back_id3,
                                String admob_interstitial_back_id4, String admob_banner_id1,
                                String admob_banner_id2, String admob_banner_id3, String admob_banner_id4,
                                Boolean enable_interstial, Boolean enable_back_interstial,
                                String more_apps_url, String play_win, String interstial_ad_banner,
                                String interstial_ad_banner_callback, String native_ad_banner, String open_ad_banner, String open_ad_banner_callback) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        SharedPreferences.Editor myEdit = preferences.edit();





        myEdit.putBoolean(ENABLE_SPIN_WIN_DIALOG, enable_spin_win_dialog);

        myEdit.putBoolean(ENABLE_AD_LOADING_DIALOG, enable_ad_loading_dialog);

        myEdit.putBoolean(ENABLE_IN_APP_REVIEW, enable_in_app_review);

        myEdit.putBoolean(ENABLE_2_NATIVE_AD, enable_2_native_ad);

        myEdit.putString(CUSTOM_TAB_URL, custom_tab_url);

        myEdit.putString(BASE_URL, base_url);


        myEdit.putString(ADMOB_NATIVE_ID1, admob_native_id1);
        myEdit.putString(ADMOB_NATIVE_ID2, admob_native_id2);
        myEdit.putString(ADMOB_NATIVE_ID3, admob_native_id3);
        myEdit.putString(ADMOB_NATIVE_ID4, admob_native_id4);

        myEdit.putString(ADMOB_OPEN_ID_ID1, admob_open_id1);
        myEdit.putString(ADMOB_OPEN_ID_ID2, admob_open_id2);

        myEdit.putString(ADMOB_INTERSTITIAL_ID1, admob_interstitial_id1);
        myEdit.putString(ADMOB_INTERSTITIAL_ID2, admob_interstitial_id2);
        myEdit.putString(ADMOB_INTERSTITIAL_ID3, admob_interstitial_id3);
        myEdit.putString(ADMOB_INTERSTITIAL_ID4, admob_interstitial_id4);
        myEdit.putString(ADMOB_INTERSTITIAL_ID5, admob_interstitial_id5);

        myEdit.putString(ADMOB_INTERSTITIAL_BACK_ID1, admob_interstitial_back_id1);
        myEdit.putString(ADMOB_INTERSTITIAL_BACK_ID2, admob_interstitial_back_id2);
        myEdit.putString(ADMOB_INTERSTITIAL_BACK_ID3, admob_interstitial_back_id3);
        myEdit.putString(ADMOB_INTERSTITIAL_BACK_ID4, admob_interstitial_back_id4);

        myEdit.putString(ADMOB_BANNER_ID1, admob_banner_id1);
        myEdit.putString(ADMOB_BANNER_ID2, admob_banner_id2);
        myEdit.putString(ADMOB_BANNER_ID3, admob_banner_id3);
        myEdit.putString(ADMOB_BANNER_ID4, admob_banner_id4);

        myEdit.putBoolean(ENABLE_INTERSTIAL, enable_interstial);
        myEdit.putBoolean(ENABLE_BACK_INTERSTIAL, enable_back_interstial);

        myEdit.putString(MORE_APPS_URL, more_apps_url);
        myEdit.putString(PLAY_WIN, play_win);


        myEdit.putString(INTERSTITIAL_AD_BANNER, interstial_ad_banner);
        myEdit.putString(INTERSTITIAL_AD_BANNER_CALLBACK, interstial_ad_banner_callback);
        myEdit.putString(NATIVE_AD_BANNER, native_ad_banner);
        myEdit.putString(OPEN_AD_BANNER, open_ad_banner);
        myEdit.putString(OPEN_AD_BANNER_CALLBACK, open_ad_banner_callback);


        myEdit.apply();
    }

    public static void storeInAppReview(Context context, Boolean inAppReview) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        SharedPreferences.Editor myEdit = preferences.edit();

        myEdit.putBoolean(IN_APP_REVIEW, inAppReview);

        myEdit.apply();
    }

    public static void storeLockBlog(Context context, String blogId, Boolean lock) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        SharedPreferences.Editor myEdit = preferences.edit();

        myEdit.putBoolean(blogId, lock);

        myEdit.apply();
    }

    public static Boolean getEnableLockGLTeam(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getBoolean(ENABLE_LOCK_GLTEAM, true);
    }


    public static Boolean getEnableSpinToWinDialog(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getBoolean(ENABLE_SPIN_WIN_DIALOG, true);
    }

    public static Boolean getLockBlog(Context context, String blogId) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getBoolean(blogId, true);
    }

    public static Boolean getEnableAdLoadingDialog(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getBoolean(ENABLE_AD_LOADING_DIALOG, false);
    }

    public static Boolean getInAppReview(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getBoolean(IN_APP_REVIEW, false);
    }

    public static Boolean getEnable2NativeAd(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getBoolean(ENABLE_2_NATIVE_AD, false);
    }

    public static Boolean getEnableInAppReview(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getBoolean(ENABLE_IN_APP_REVIEW, true);
    }

    public static String getPrivayPolicyUrl(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(PRIVAY_POLICY_URL, "");
    }

    public static String getCustomTabUrl(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(CUSTOM_TAB_URL, "");
    }

    public static String getAdmobRewardId1(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_REWARD_ID1, "");
    }

    public static String getAdmobRewardId2(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_REWARD_ID2, "");
    }

    public static String getAdmobNativeId1(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_NATIVE_ID1, "");
    }

    public static String getAdmobNativeId2(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_NATIVE_ID2, "");
    }

    public static String getAdmobOpenIdId1(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_OPEN_ID_ID1, "");
    }

    public static String getAdmobOpenIdId2(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_OPEN_ID_ID2, "");
    }

    public static String getAdmobInterstitialId1(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_INTERSTITIAL_ID1, "");
    }

    public static String getAdmobInterstitialId2(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_INTERSTITIAL_ID2, "");
    }

    public static String getAdmobInterstitialId3(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_INTERSTITIAL_ID3, "");
    }

    public static String getAdmobInterstitialId4(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_INTERSTITIAL_ID4, "");
    }

    public static String getAdmobInterstitialBackId1(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_INTERSTITIAL_BACK_ID1, "");
    }

    public static String getAdmobInterstitialBackId2(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_INTERSTITIAL_BACK_ID2, "");
    }

    public static String getAdmobInterstitialBackId3(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_INTERSTITIAL_BACK_ID3, "");
    }

    public static String getAdmobInterstitialBackId4(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_INTERSTITIAL_BACK_ID4, "");
    }

    public static String getAdmobBannerId1(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_BANNER_ID1, "");
    }

    public static String getAdmobBannerId2(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_BANNER_ID2, "");
    }

    public static String getAdmobBannerId3(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_BANNER_ID3, "");
    }

    public static String getAdmobBannerId4(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_BANNER_ID4, "");
    }

    public static String getBaseUrl(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(BASE_URL, "");
    }

    public static Boolean getInterstial(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getBoolean(ENABLE_INTERSTIAL, true);
    }

    public static Boolean getBackInterstial(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getBoolean(ENABLE_BACK_INTERSTIAL, true);
    }

    public static String getMoreAppsUrl(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(MORE_APPS_URL, "");
    }

    public static String getPlayWin(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(PLAY_WIN, "");
    }


    public static String getNativeAdBannerCallback(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(NATIVE_AD_BANNER_CALLBACK, "");
    }


    public static String getAdmobNativeId3(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_NATIVE_ID3, "");
    }

    public static String getAdmobNativeId4(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_NATIVE_ID4, "");
    }


    public static String getAdmobInterstitialId5(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(ADMOB_INTERSTITIAL_ID5, "");
    }

    public static String getInterstitialAdBanner(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(INTERSTITIAL_AD_BANNER, "");
    }

    public static String getInterstitialAdBannerCallback(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(INTERSTITIAL_AD_BANNER_CALLBACK, "");
    }

    public static String getNativeAdBanner(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(NATIVE_AD_BANNER, "");
    }

    public static String getOpenAdBanner(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(OPEN_AD_BANNER, "");
    }

    public static String getOpenAdBannerCallback(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Minigame", Context.MODE_PRIVATE);

        return preferences.getString(OPEN_AD_BANNER_CALLBACK, "");
    }

    public static void downloadOpenAdBanner(Context context, String openAdBanner) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(openAdBanner);

                    File file = new File(context.getCacheDir().getAbsolutePath() + "/" + Utils.OPEN_AD_BANNER_NAME);


                    Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());

                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(file));


                } catch (Exception e) {
                    Log.d("DownloadImage", "...." + e.toString());
                    e.printStackTrace();
                }
            }
        });
    }

    public static void downloadInterstitialAdBanner(Context context, String interstitialAdBanner) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(interstitialAdBanner);

                    File file = new File(context.getCacheDir().getAbsolutePath() + "/" + Utils.INTERSTITIAL_AD_BANNER_NAME);


                    Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());

                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(file));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
