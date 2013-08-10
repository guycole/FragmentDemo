package com.digiburo.fragdemo.content;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.digiburo.fragdemo.utility.LogFacade;

/**
 * database helper
 *
 * @author gsc
 */
public class DataBaseHelper extends SQLiteOpenHelper {
  
  //
  public DataBaseHelper(Context context) {
    super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
  }

  /* (non-Javadoc)
   * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
   */
  @Override
  public void onCreate(SQLiteDatabase db) {
    LogFacade.entry(LOG_TAG, "onCreate");

    /*
    db.execSQL(AccountTable.CREATE_TABLE);
    db.execSQL(HomeFragmentTable.CREATE_TABLE);
    db.execSQL(ImageTable.CREATE_TABLE);
    db.execSQL(NewsMeTable.CREATE_TABLE);
    db.execSQL(OpportunityStageTable.CREATE_TABLE);
    db.execSQL(OpportunityTable.CREATE_TABLE);
    db.execSQL(PeopleTable.CREATE_TABLE);
    db.execSQL(PhenomenonGroupTable.CREATE_TABLE);
    db.execSQL(ScaleObservationTable.CREATE_TABLE);
    db.execSQL(ScalePhenomenonTable.CREATE_TABLE);
    db.execSQL(SessionTable.CREATE_TABLE);
    */
  }

  /* (non-Javadoc)
   * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
   */
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    LogFacade.entry(LOG_TAG, "onUpgrade");
  }
  
  public static final String DATABASE_FILE_NAME = "fragdemo.db";
  public static final int DATABASE_VERSION = 1;
  
  //
  public static final String LOG_TAG = DataBaseHelper.class.getName();
}
/**
 * Created by guycole on 8/6/13.
 */
