package com.doctorcarepath.GoogleAds;

public interface GoogleAdListener {

    void OnAdClose();
    void OnAdError(int i);
    void OnBannerAdClick();

    void OnNativeAdClick();

    void OnInterstitialAdClick();

    void OnOpenAdClick();
}
