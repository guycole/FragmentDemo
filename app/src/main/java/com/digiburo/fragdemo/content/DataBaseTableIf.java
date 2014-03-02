package com.digiburo.fragdemo.content;

/**
 * Common parent for tables
 *
 * @author gsc
 */
public interface DataBaseTableIf {
  
  /**
   * return associated table name
   * @return associated table name
   */
  public String getTableName();
  
  /**
   * return default sort order
   * @return default sort order
   */
  public String getDefaultSortOrder();
  
  /**
   * return default projection (column names)
   * @return return default projection (column names)
   */
  public String[] getDefaultProjection();
}
/**
 * Created by guycole on 8/6/13.
 */
