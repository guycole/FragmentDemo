package com.digiburo.fragdemo.content;

import android.content.ContentValues;
import android.database.Cursor;

import com.digiburo.fragdemo.Constants;
import com.digiburo.fragdemo.utility.Utility;

/**
 * convenience wrapper for DummyTable
 *
 * @author guycole
 */
public class DummyModel implements DataBaseModelIf {

  @Override
  public void setDefault() {
    name = Constants.DB_DEFAULT_NAME;
  }

  @Override
  public ContentValues toContentValues() {
    ContentValues cv = new ContentValues();
    cv.put(DummyTable.Columns.NAME, Utility.eclecticString(name, Constants.DB_DEFAULT_NAME));
    return(cv);
  }

  @Override
  public void fromCursor(Cursor cursor) {
    id = cursor.getLong(cursor.getColumnIndex(DummyTable.Columns._ID));
    name = cursor.getString(cursor.getColumnIndex(DummyTable.Columns.NAME));
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
  
  private Long id = 0L;
  private String name;
}
/**
 * Created by guycole on 8/6/13.
 */