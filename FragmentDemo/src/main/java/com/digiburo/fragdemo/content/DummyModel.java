package com.digiburo.fragdemo.content;

import android.content.ContentValues;
import android.database.Cursor;

import com.digiburo.fragdemo.Constants;

/**
 * convenience wrapper for DummyTable
 *
 * @author guycole
 */
public class DummyModel implements DataBaseModelIf {

  @Override
  public void setDefault() {
    growId = 0L;
  //  name = Constants.DB_DEFAULT_ACCOUNT_NAME;
  }

  @Override
  public ContentValues toContentValues() {
    ContentValues cv = new ContentValues();
    
  //  cv.put(AccountTable.Columns.GROW_ID, growId);
  //  cv.put(AccountTable.Columns.NAME, Utility.eclecticString(name, Constants.DB_DEFAULT_ACCOUNT_NAME));

    return(cv);
  }

  @Override
  public void fromCursor(Cursor cursor) {
///    id = cursor.getLong(cursor.getColumnIndex(AccountTable.Columns._ID));
//
//    growId = cursor.getLong(cursor.getColumnIndex(AccountTable.Columns.GROW_ID));
//    name = cursor.getString(cursor.getColumnIndex(AccountTable.Columns.NAME));
  }

  @Override
  public String getTableName() {
    return(DummyTable.TABLE_NAME);
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return(name);
  }

  public void setName(String arg) {
    name = arg;
  }

  public Long getGrowId() {
    return(growId);
  }
  
  public void setGrowId(Long arg) {
    growId = arg;
  }
  
  private Long id = 0L;
  private Long growId = 0L;
  private String name;
}
/**
 * Created by guycole on 8/6/13.
 */