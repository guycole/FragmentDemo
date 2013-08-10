package com.digiburo.fragdemo.content;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Models act as containers, bridge between database and code
 * 
 * Children should implement equals()/hashCode() without comparing row ID
 *
 * @author gsc
 */
public interface DataBaseModelIf {

  /**
   * set reasonable model defaults
   */
  public void setDefault();
  
  /**
   * load content from model
   * 
   * @return populated values
   */
  public ContentValues toContentValues();
  
  /**
   * convert from cursor to model
   * @param cursor points to model datum
   */
  public void fromCursor(Cursor cursor);

  /**
   * return associated table name
   * @return associated table name
   */
  public String getTableName();
  
  //for BaseColumns
  public Long getId();
  public void setId(Long id);
}
/**
 * Created by guycole on 8/6/13.
 */