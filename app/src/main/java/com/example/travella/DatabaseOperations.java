package com.example.travella;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int database_version = 1;
    public String CREATE_QUERY_TICKETS = "CREATE TABLE IF NOT EXISTS tokens(id TEXT,token TEXT,status TEXT,journey TEXT,vehicle TEXT)";

    Context context;

    public DatabaseOperations(Context context) {
        super(context, "travella", null, database_version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {

        sdb.execSQL(CREATE_QUERY_TICKETS);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion) {
            db.execSQL(CREATE_QUERY_TICKETS);
        }
    }

    public void putTokens(DatabaseOperations dop, String id, String token, String vehicle, String journey, String status) {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("token", token);
        cv.put("vehicle", vehicle);
        cv.put("journey", journey);
        cv.put("status", status);
        long k = SQ.insert("tokens", null, cv);
        System.out.println(journey);
    }

    public Cursor getTokens(DatabaseOperations dop) {
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] coloumns = {"id", "token", "vehicle", "journey", "status"};
        Cursor cr = SQ.query("tokens", coloumns, null, null, null, null, null);
        return cr;
    }

    public void clearDatabase(DatabaseOperations dop) {
        SQLiteDatabase SQ = dop.getWritableDatabase();
        String clearDBQuery = "DELETE FROM tokens";
        SQ.execSQL(clearDBQuery);
    }

    public Cursor selectOne(DatabaseOperations dop, String find) {
        SQLiteDatabase SQ = dop.getReadableDatabase();
        Log.d(find, find);
        String query = "SELECT * FROM tokens WHERE token=" + find + ";";
        Cursor cr = SQ.rawQuery(query, null);
        return cr;
    }
}
