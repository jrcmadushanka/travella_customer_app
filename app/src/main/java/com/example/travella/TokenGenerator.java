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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TokenGenerator extends AppCompatActivity {

    ImageView tokenPreview;
    DatabaseOperations DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_generator);

        DB = new DatabaseOperations(TokenGenerator.this);

        tokenPreview = findViewById(R.id.tokenView);

        generateToken("12", "2");

    }

    private void generateToken(String id, String journey){

        Map<String, String> params = new HashMap<String, String>();

        params.put("journey_id", journey);
        params.put("user_id", id);

        String json_url = "https://powerful-plateau-81192.herokuapp.com/api/token/";

        JSONObject jsonObject = new JSONObject(params);

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, json_url, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject array) {

                String[] id = new String[array.length()];
                String[] token = new String[array.length()];
                String[] journey = new String[array.length()];
                String[] vehicle = new String[array.length()];
                String[] status = new String[array.length()];

                try {
                    id[0] = (array.get("id")).toString();
                    token[0] = (array.get("token")).toString();
                    journey[0] = (array.get("journey_id")).toString();
                    vehicle[0] = (array.get("vehicle_id")).toString();
                    status[0] = (array.get("status")).toString();

                    DB.putTokens(DB, id[0], token[0], journey[0], vehicle[0], status[0]);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                generateQr(token[0]);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        Volley.newRequestQueue(TokenGenerator.this).add(jsonObjectRequest);
    }

    private void generateQr(String text){
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,400,400);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            tokenPreview.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
