package com.digiburo.fragdemo.content;

import android.net.Uri;
import android.provider.BaseColumns;

import com.digiburo.fragdemo.Constants;

import java.util.HashMap;
import java.util.Set;

/**
 * dummy table definitions
 *
 * @author gsc
 */
public class DummyTable implements DataBaseTableIf {

  @Override
  public String getTableName() {
    return(TABLE_NAME);
  }

  @Override
  public String getDefaultSortOrder() {
    return(DEFAULT_SORT_ORDER);    
  }

  @Override
  public String[] getDefaultProjection() {
    Set<String> keySet = DummyTable.PROJECTION_MAP.keySet();
    return(keySet.toArray(new String[keySet.size()]));
  }

  //
  public static final class Columns implements BaseColumns {
 
    // column names
    public static final String NAME = "name";
    public static final String RADIO = "radio";
    public static final String TYPE = "type";
  }
  
  //
  public static final String TABLE_NAME = "dummy";
  
  // select all stations
  public static final Uri CONTENT_URI = Uri.parse("content://" + Constants.AUTHORITY + "/" + TABLE_NAME);
  
  //
  public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.digiburo." + TABLE_NAME;

  //
  public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.digiburo." + TABLE_NAME;
  
  //
  public static final String DEFAULT_SORT_ORDER = Columns.NAME + " ASC";
  
  //
  public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
    + Columns._ID + " INTEGER PRIMARY KEY,"
    + Columns.NAME + " TEXT NOT NULL,"
    + Columns.RADIO + " INTEGER NOT NULL,"
    + Columns.TYPE + " TEXT NOT NULL"
    + ");";

  //
  public static HashMap<String, String> PROJECTION_MAP = new HashMap<String, String>();
  
  static {
    PROJECTION_MAP.put(DummyTable.Columns._ID, DummyTable.Columns._ID);
    PROJECTION_MAP.put(DummyTable.Columns.NAME, DummyTable.Columns.NAME);
    PROJECTION_MAP.put(DummyTable.Columns.RADIO, DummyTable.Columns.RADIO);
    PROJECTION_MAP.put(DummyTable.Columns.TYPE, DummyTable.Columns.TYPE);
  }
}
/**
 * Created by guycole on 8/6/13.
 */