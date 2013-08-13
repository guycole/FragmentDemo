package com.digiburo.fragdemo.content;

import android.content.ContentValues;
import android.database.Cursor;

import com.digiburo.fragdemo.Constants;
import com.digiburo.fragdemo.utility.LegalRowType;
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
    radioButton = 0;
    rowType = LegalRowType.UNKNOWN;
  }

  @Override
  public ContentValues toContentValues() {
    ContentValues cv = new ContentValues();

    cv.put(DummyTable.Columns.NAME, Utility.eclecticString(name, Constants.DB_DEFAULT_NAME));
    cv.put(DummyTable.Columns.RADIO, radioButton);
    cv.put(DummyTable.Columns.TYPE, rowType.getName());

    return(cv);
  }

  @Override
  public void fromCursor(Cursor cursor) {
    id = cursor.getLong(cursor.getColumnIndex(DummyTable.Columns._ID));

    name = cursor.getString(cursor.getColumnIndex(DummyTable.Columns.NAME));
    radioButton = cursor.getInt(cursor.getColumnIndex(DummyTable.Columns.RADIO));

    String temp = cursor.getString(cursor.getColumnIndex(DummyTable.Columns.TYPE));
    rowType = LegalRowType.discoverMatchingEnum(temp);
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

  public Integer getRadioButton() {
    return radioButton;
  }
  public void setRadioButton(Integer radioButton) {
    this.radioButton = radioButton;
  }

  public LegalRowType getRowType() {
    return rowType;
  }
  public void setRowType(LegalRowType rowType) {
    this.rowType = rowType;
  }
  
  private Long id = 0L;
  private String name;
  private Integer radioButton = 0;
  private LegalRowType rowType = LegalRowType.UNKNOWN;
}
/**
 * Created by guycole on 8/6/13.
 */