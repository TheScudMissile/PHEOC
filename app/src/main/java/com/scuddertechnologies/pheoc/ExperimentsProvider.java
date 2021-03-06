package com.scuddertechnologies.pheoc;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Handles inserts, updates, and deletions of database records
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

    //constants to identify the operation requested (1 is all data, 2 is single record)
    private static final int EXPERIMENTS = 1;
    private static final int EXPERIMENTS_ID = 2;

    //identify which operation is being requested (1 or 2)
    private static final UriMatcher uriMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);

    //used to indicate updating an existing experiment
    public static final String CONTENT_ITEM_TYPE = "Experiment";

    static {

        //op to get all data
        uriMatcher.addURI(AUTHORITY, BASE_PATH, EXPERIMENTS);

        //"/#" means wild card (looking for single experiment vs all)
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", EXPERIMENTS_ID);

    }

    private SQLiteDatabase database;

    //creates the database defined by the DataBaseHelper class
    @Override
    public boolean onCreate() {

        DataBaseHelper helper = new DataBaseHelper(getContext());
        database = helper.getWritableDatabase();
        return false;
    }

    //return the specified record in database from a query
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        if (uriMatcher.match(uri) == EXPERIMENTS_ID) {

            //gets value after slash seen above so only single experiment is selected
            selection = DataBaseHelper.EXPERIMENT_ID + "=" + uri.getLastPathSegment();
        }

        return database.query(DataBaseHelper.TABLE_EXPERIMENTS, DataBaseHelper.ALL_COLUMNS,
                selection, null, null, null, DataBaseHelper.EXPERIMENT_CREATED + " DESC");
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        long id = database.insert(DataBaseHelper.TABLE_EXPERIMENTS, null, contentValues);
        return  Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        return database.delete(DataBaseHelper.TABLE_EXPERIMENTS, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[]
            selectionArgs) {

        return database.update(DataBaseHelper.TABLE_EXPERIMENTS, contentValues, selection,
                selectionArgs);
    }
}