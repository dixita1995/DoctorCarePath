package com.doctorcarepath;


import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.doctorcarepath.GoogleAds.AdManager;
import com.doctorcarepath.GoogleAds.GoogleAdListener;
import com.doctorcarepath.Util.Utils;

import static com.doctorcarepath.R.*;


public class DoctorSpecialist extends AppCompatActivity implements GoogleAdListener {
    TextView txtspecialist;
    String specialist,Name="",Image="";
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b=getIntent().getExtras();

        if(b!=null)
        {
            specialist=b.getString("specialist").toString();
            Name=b.getString("Name").toString();
            Image=b.getString("image");
        }
        getSupportActionBar().setTitle(Name);
        setContentView(layout.activity_doctor_specialist);
        AdManager.loadGoogleInterstitialAds(this, Utils.getAdmobInterstitialId3(this), Utils.getAdmobBannerId3(this));
        AdManager.backLoadGoogleInterstitialAds(this, Utils.getAdmobInterstitialBackId2(this), Utils.getAdmobBannerId2(this), Utils.getBackInterstial(DoctorSpecialist.this));
        AppCompatActivity activity = this;
        LinearLayout adfrm1 = findViewById(R.id.adfrm1);
        AdManager.loadGoogleNativeAd(DoctorSpecialist.this, Utils.getAdmobNativeId1(this),Utils.getAdmobBannerId1(this),adfrm1,false);

        txtspecialist=(TextView)findViewById(id.specialist);
        image=(ImageView)findViewById(id.img);
        txtspecialist.setText(specialist);
        if(Image!=null)
        {
            Glide.with(DoctorSpecialist.this).load(Image).fitCenter().into(image);
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shareicon, menu);
        return super.onCreateOptionsMenu(menu);
    }

    boolean isBack=false;

    @Override
    public void onBackPressed() {
        AdManager.showInterstitialAd(this,Utils.getInterstial(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.share:
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<H1>"+Name+"</H1>")+"  \n"+specialist);
                startActivity(Intent.createChooser(intent, "Share Detail"));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnAdClose() {
        finish();
    }

    @Override
    public void OnAdError(int i) {
        finish();
    }

    @Override
    public void OnBannerAdClick() {

    }

    @Override
    public void OnNativeAdClick() {

    }

    @Override
    public void OnInterstitialAdClick() {

    }

    @Override
    public void OnOpenAdClick() {

    }
}
