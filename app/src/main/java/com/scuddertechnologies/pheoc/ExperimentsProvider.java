package com.scuddertechnologies.pheoc;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.net.URI;

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
    public static final Uri CONTENT_URI =
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
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        return database.query(DBOpenHelper.TABLE_EXPERIMENTS, DBOpenHelper.ALL_COLUMNS,
                selection, null, null, null, DBOpenHelper.EXPERIMENT_CREATED + " DESC");
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        long id = database.insert(DBOpenHelper.TABLE_EXPERIMENTS, null, contentValues);
        return  Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        return database.delete(DBOpenHelper.TABLE_EXPERIMENTS, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[]
            selectionArgs) {

        return database.update(DBOpenHelper.TABLE_EXPERIMENTS, contentValues, selection,
                selectionArgs);
    }
}