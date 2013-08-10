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
    String[] result = (String[]) keySet.toArray(new String[keySet.size()]);
    return(result);
  }

  //
  public static final class Columns implements BaseColumns {
 
    // column names
    public static final String GROW_ID = "grow_id";
    public static final String NAME = "name";
  }
  
  //
  public static final String TABLE_NAME = "dummy";
  
  // select all stations
  public static final Uri CONTENT_URI = Uri.parse("content://" + Constants.AUTHORITY + "/" + TABLE_NAME);
  
  //
  public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.factorlab." + TABLE_NAME;

  //
  public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.factorlab." + TABLE_NAME;
  
  //
  public static final String DEFAULT_SORT_ORDER = Columns.NAME + " ASC";
  
  //
  public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
    + Columns._ID + " INTEGER PRIMARY KEY,"
    + Columns.GROW_ID + " INTEGER NOT NULL,"
    + Columns.NAME + " TEXT NOT NULL"
    + ");";

  //
  public static HashMap<String, String> PROJECTION_MAP = new HashMap<String, String>();
  
  static {
    PROJECTION_MAP.put(DummyTable.Columns._ID, DummyTable.Columns._ID);
    PROJECTION_MAP.put(DummyTable.Columns.GROW_ID, DummyTable.Columns.GROW_ID);
    PROJECTION_MAP.put(DummyTable.Columns.NAME, DummyTable.Columns.NAME);
  }
}
/**
 * Created by guycole on 8/6/13.
 */