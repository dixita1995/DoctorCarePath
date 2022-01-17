package com.doctorcarepath;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.doctorcarepath.GoogleAds.AdManager;
import com.doctorcarepath.GoogleAds.GoogleAdListener;
import com.doctorcarepath.Util.Utils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.doctorcarepath.R.*;


public class TypeOfDoctor extends AppCompatActivity implements GoogleAdListener,onClickInterface {
    RecyclerView recyclerView;
    DataAdapter adapter;
    private ArrayList<AndroidDoctor> data;
    String JSON_URL = "";
    List<Tutorial> tutorialList;
    String Name, specialist, image;
    String t1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_doctor);
        ((AdView) findViewById(R.id.admob_adview1)).loadAd(new AdRequest.Builder().build());
        AdManager.loadGoogleInterstitialAds(this, Utils.getAdmobInterstitialId2(this), Utils.getAdmobBannerId2(this));
        AdManager.backLoadGoogleInterstitialAds(this, Utils.getAdmobInterstitialBackId2(this), Utils.getAdmobBannerId2(this), Utils.getBackInterstial(TypeOfDoctor.this));
        initViews();
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.rvitem);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        JSON_URL = "https://extendsclass.com/api/json-storage/bin/bfcbdfa";
        loadJSON(JSON_URL);

    }

    @Override
    protected void onResume() {
        super.onResume();
        AdManager.loadGoogleInterstitialAds(this, Utils.getAdmobInterstitialId2(this), Utils.getAdmobBannerId2(this));
        AdManager.backLoadGoogleInterstitialAds(this, Utils.getAdmobInterstitialBackId2(this), Utils.getAdmobBannerId2(this), Utils.getBackInterstial(TypeOfDoctor.this));
    }

    private void loadJSON(String JSON_URL) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("process....");
        pd.show();
        tutorialList = new ArrayList<>();
        tutorialList.clear();
        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {


                            JSONObject obj = new JSONObject(response);


                            JSONArray tutorialsArray = obj.getJSONArray("Doctor");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < tutorialsArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject tutorialsObject = tutorialsArray.getJSONObject(i);

                                //creating a tutorial object and giving them the values from json object
                                Tutorial tutorial = new Tutorial(tutorialsObject.getString("ID"), tutorialsObject.getString("Name"), tutorialsObject.getString("Specialists"), tutorialsObject.getString("link"));
                                tutorialList.add(tutorial);
                            }
                            pd.dismiss();
                            adapter = new DataAdapter(tutorialList, TypeOfDoctor.this, TypeOfDoctor.this);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occur
                        Toast.makeText(TypeOfDoctor.this, "Internet Connection Required", Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        Context context = this;
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optlag, menu);
        return super.onCreateOptionsMenu(menu);
    }

    boolean eng = false, guj = false, hin = false, click = false;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.eng:
                eng = true;

                AdManager.showInterstitialAd(this, Utils.getInterstial(this));
                break;
            case R.id.hin:
                hin = true;

                AdManager.showInterstitialAd(this, Utils.getInterstial(this));
                break;
            case R.id.guj:

                guj = true;
                AdManager.showInterstitialAd(this, Utils.getInterstial(this));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    ActivityType activityType;

    @Override
    public void OnAdClose() {
        if (eng) {
            JSON_URL = "https://extendsclass.com/api/json-storage/bin/bfcbdfa";
            loadJSON(JSON_URL);
            eng=false;

        } else if (hin) {

            JSON_URL = "https://extendsclass.com/api/json-storage/bin/cfdeefb";
            loadJSON(JSON_URL);
            hin=false;

        } else if (guj) {
            JSON_URL = "https://extendsclass.com/api/json-storage/bin/bdefafa";
            loadJSON(JSON_URL);
            guj=false;
        } else if (click) {
            Intent i = new Intent(TypeOfDoctor.this, DoctorSpecialist.class);
            i.putExtra("specialist", specialist);
            i.putExtra("Name", Name);
            i.putExtra("image", image);
            startActivity(i);
            click=false;

        }


    }

    @Override
    public void OnAdError(int i) {
        if (eng) {
            JSON_URL = "https://extendsclass.com/api/json-storage/bin/bfcbdfa";
            loadJSON(JSON_URL);
            eng=false;

        } else if (hin) {

            JSON_URL = "https://extendsclass.com/api/json-storage/bin/cfdeefb";
            loadJSON(JSON_URL);
            hin=false;

        } else if (guj) {
            JSON_URL = "https://extendsclass.com/api/json-storage/bin/bdefafa";
            loadJSON(JSON_URL);
            guj=false;
        } else if (click) {
            Intent i1 = new Intent(TypeOfDoctor.this, DoctorSpecialist.class);
            i1.putExtra("specialist", specialist);
            i1.putExtra("Name", Name);
            i1.putExtra("image", image);
            startActivity(i1);
            click=false;

        }

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

    @Override
    public void setClick(String Specialist1, String Name1, String image1) {
        specialist = Specialist1;
        Name = Name1;
        image = image1;
        click = true;
        AdManager.showInterstitialAd(TypeOfDoctor.this, Utils.getInterstial(TypeOfDoctor.this));


    }

    public enum ActivityType {
        diamond, d2, d3, d1;
    }


}
