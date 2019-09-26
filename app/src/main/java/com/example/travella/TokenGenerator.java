package com.example.travella;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONArray;
import org.json.JSONException;

public class TokenGenerator extends AppCompatActivity {
    ImageView tokenPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_generator);

        tokenPreview = findViewById(R.id.tokenView);

        String text = getToken();
        System.out.println(getToken() + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        if (text == ""){
            text = "got nothing";
        }
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            System.out.println("on  gene ++++++++++++++++++++++++++++");
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,400,400);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            tokenPreview.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private String getToken (){

        final String[] retSt = {""};
        String json_url = "https://powerful-plateau-81192.herokuapp.com/api/vehicles";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, json_url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray array) {
                String[] id = new String[array.length()];
                String[] number = new String[array.length()];
                String[] facility = new String[array.length()];
                String[] route = new String[array.length()];

                    try {
                        array.getString(0);
                        id[0] = (array.getJSONObject(0).get("id")).toString();
                        number[0] = (array.getJSONObject(0).get("number")).toString();
                        facility[0] = (array.getJSONObject(0).get("facility")).toString();
                        route[0] = (array.getJSONObject(0).get("routId")).toString();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                retSt[0] = number[0];
                System.out.println(retSt[0] + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        Volley.newRequestQueue(TokenGenerator.this).add(jsonObjectRequest);

        return retSt[0];

/*      ret
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.google.com";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);*/

    }
}
