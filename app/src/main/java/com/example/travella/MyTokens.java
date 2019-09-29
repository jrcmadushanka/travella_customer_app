package com.example.travella;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MyTokens extends AppCompatActivity {

    RecyclerView re;
    DatabaseOperations DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tokens);

        re = findViewById(R.id.score_recycler_view);

        ArrayList<String> j = new ArrayList<>();
        ArrayList<String> t = new ArrayList<>();


        DB = new DatabaseOperations(MyTokens.this);

        Cursor CR = DB.getTokens(DB);

        int count = CR.getCount();

        if (count > 0) {
            CR.moveToFirst();
            do {
                String token = (CR.getString(1));
                String id = (CR.getString(0));
                String journey = (CR.getString(3));
                System.out.println((CR.getString(3)));
                int status = Integer.parseInt(CR.getString(4));

                j.add(journey);
                t.add(token);
              /*  ListElementsArrayList.add("ID : " + id + "  journey : " + journey ) ;
                adapter.notifyDataSetChanged();*/

            } while (CR.moveToNext());
        } else {

        }

        ScoreAdapter score = new ScoreAdapter(this, j,t);
        re.setLayoutManager(new LinearLayoutManager(this));
        re.setAdapter( new ScoreAdapter(this, j,t));


    }
}
