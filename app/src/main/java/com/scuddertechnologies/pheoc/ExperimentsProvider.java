package com.scuddertechnologies.pheoc;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by BScudder on 7/13/16.
 */
public class ExperimentsProvider extends ContentProvider {

    //identifies content provider to app framework
    private static final String AUTHORITY =
            "com.scuddertechnologies.pheoc.experimentsprovider";

    //represents data set
    private static final String BASE_PATH = "experiments";

    //identifies content provider
    public static final Uri Content_Uri =
            Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    //Constants to identify the operation requested (1 is all data, 2 is single record)
    private static final int EXPERIMENTS = 1;
    private static final int EXPERIMENTS_ID = 2;

    //Identify which op is being requested (1 or 2)
    private static final UriMatcher uriMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);

    static {

        //op to get all data
        uriMatcher.addURI(AUTHORITY, BASE_PATH, EXPERIMENTS);

        //"/#" means wild card (looking for single experiment vs all)
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", EXPERIMENTS_ID);

    }

    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {

        DBOpenHelper helper = new DBOpenHelper(getContext());
        database = helper.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}