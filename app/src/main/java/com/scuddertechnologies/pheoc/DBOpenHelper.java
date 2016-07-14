package com.scuddertechnologies.pheoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BScudder on 7/9/16.
 *
 * Defines the database for PHEOC
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "experiments.db";
    private static final int DATABASE_VERSION = 1;

    //Constants for table and columns
    public static final String TABLE_EXPERIMENTS = "experiments";
    public static final String EXPERIMENT_ID = "_id";
    public static final String P = "problem";
    public static final String H = "hypothesis";
    public static final String E = "data";
    public static final String O = "observations";
    public static final String C = "conclusion";
    public static final String EXPERIMENT_CREATED = "experimentCreated";

    //SQL needed for the table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_EXPERIMENTS + " (" +
                    EXPERIMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    P + " TEXT, " +
                    H + " TEXT, " +
                    E + " TEXT, " +
                    O + " TEXT, " +
                    C + " TEXT, " +
                    EXPERIMENT_CREATED + " TEXT default CURRENT_TIMESTAMP" +
                    ")";




    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPERIMENTS);
        onCreate(db);
    }

    public boolean addExperiment(String problem, String hypothesis,
                                 String experiment, String observations, String conclusion) {

        //get ref to created database to write to
        SQLiteDatabase db = getWritableDatabase();

        //add all pieces of data for database
        ContentValues contentValues = new ContentValues();
        contentValues.put(P, problem);
        contentValues.put(H, hypothesis);
        contentValues.put(E, experiment);
        contentValues.put(O, observations);
        contentValues.put(C, conclusion);

        //add to database
        db.insert(TABLE_EXPERIMENTS, null, contentValues);
        return true;
    }
}