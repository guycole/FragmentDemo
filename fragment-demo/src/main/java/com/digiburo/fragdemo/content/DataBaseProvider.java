package com.digiburo.fragdemo.content;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.digiburo.fragdemo.Constants;
import com.digiburo.fragdemo.utility.LogFacade;

/**
 * database provider
 *
 * @author gsc
 */
public class DataBaseProvider extends ContentProvider {

  /* (non-Javadoc)
   * @see android.content.ContentProvider#getType(android.net.Uri)
   */
  @Override
  public String getType(Uri uri) {
    LogFacade.entry(LOG_TAG, "getType:" + uri);
    
    switch (uriMatcher.match(uri)) {
    case URI_MATCH_DUMMY:
      return(DummyTable.CONTENT_TYPE);
    case URI_MATCH_DUMMY_ID:
      return(DummyTable.CONTENT_ITEM_TYPE);
    default:
      throw new IllegalArgumentException("Unknown URI " + uri);
    }
  }

  /* (non-Javadoc)
   * @see android.content.ContentProvider#delete(android.net.Uri, java.lang.String, java.lang.String[])
   */
  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    LogFacade.entry(LOG_TAG, "delete:" + uri);    
    
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    int count = 0;
    String id = "";

    switch (uriMatcher.match(uri)) {
    case URI_MATCH_DUMMY:
      count = db.delete(DummyTable.TABLE_NAME, selection, selectionArgs);
      break;
    case URI_MATCH_DUMMY_ID:
      id = uri.getPathSegments().get(1);
      count = db.delete(DummyTable.TABLE_NAME, DummyTable.Columns._ID + "=" + id + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
      break;
    default:
      throw new IllegalArgumentException("Unknown URI " + uri);
    }
 
    getContext().getContentResolver().notifyChange(uri, null);
    return(count);
  }

  /* (non-Javadoc)
   * @see android.content.ContentProvider#insert(android.net.Uri, android.content.ContentValues)
   */
  @Override
  public Uri insert(Uri uri, ContentValues values) {
    LogFacade.entry(LOG_TAG, "insert:" + uri);
    
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    long rowId = 0;
    Uri result = null;
    
    switch (uriMatcher.match(uri)) {
    case URI_MATCH_DUMMY:
      rowId = db.insert(DummyTable.TABLE_NAME, null, values);
      if (rowId > 0) {
        result = ContentUris.withAppendedId(DummyTable.CONTENT_URI, rowId);
        getContext().getContentResolver().notifyChange(DummyTable.CONTENT_URI, null);
      }
      break;
    default:
      throw new IllegalArgumentException("Unknown URI " + uri);
    }
    
    if (rowId < 1) {
      throw new SQLException("insert failure:" + uri);
    }
    
    return(result);
  }

  /* (non-Javadoc)
   * @see android.content.ContentProvider#onCreate()
   */
  @Override
  public boolean onCreate() {
    LogFacade.entry(LOG_TAG, "onCreate");
    dbHelper = new DataBaseHelper(getContext());
    return(true);
  }

  /* (non-Javadoc)
   * @see android.content.ContentProvider#query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String)
   */
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
    LogFacade.entry(LOG_TAG, "query:" + uri);

    SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
    String orderBy = sortOrder;
    
    switch (uriMatcher.match(uri)) {
    case URI_MATCH_DUMMY:
      LogFacade.debug(LOG_TAG, "match all rows");
      qb.setTables(DummyTable.TABLE_NAME);
      qb.setProjectionMap(DummyTable.PROJECTION_MAP);
      if (sortOrder == null) {
        orderBy = DummyTable.DEFAULT_SORT_ORDER;
      }
      break;
    case URI_MATCH_DUMMY_ID:
      LogFacade.debug(LOG_TAG, "match row singular");
      qb.setTables(DummyTable.TABLE_NAME);
      qb.setProjectionMap(DummyTable.PROJECTION_MAP);
      qb.appendWhere(DummyTable.Columns._ID + "=" + uri.getPathSegments().get(1));
      if (sortOrder == null) {
        orderBy = DummyTable.DEFAULT_SORT_ORDER;
      }
      break;
    default:
      throw new IllegalArgumentException("Unknown URI " + uri);
    }
    
    // Get the database and run the query
    SQLiteDatabase db = dbHelper.getReadableDatabase();
    Cursor cc = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
    LogFacade.debug(LOG_TAG, "query result size:" + cc.getCount() + ":column size:" + cc.getColumnCount());

    cc.setNotificationUri(getContext().getContentResolver(), uri);
    return(cc);
  }

  /* (non-Javadoc)
   * @see android.content.ContentProvider#update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[])
   */
  @Override
  public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
    LogFacade.entry(LOG_TAG, "update:" + uri);
    
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    int count = 0;
    String id = "";

    switch (uriMatcher.match(uri)) {
    case URI_MATCH_DUMMY:
      count = db.update(DummyTable.TABLE_NAME, values, selection, selectionArgs);
      break;
    case URI_MATCH_DUMMY_ID:
      id = uri.getPathSegments().get(1);
      count = db.update(DummyTable.TABLE_NAME, values, DummyTable.Columns._ID + "=" + id + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')': ""), selectionArgs);
      break;
    default:
      throw new IllegalArgumentException("Unknown URI " + uri);
    }

    getContext().getContentResolver().notifyChange(uri, null);
    return(count);
  }
  
  //
  private static final UriMatcher uriMatcher;
  
  //URI Matcher Targets
  private static final int URI_MATCH_DUMMY = 5;
  private static final int URI_MATCH_DUMMY_ID = 6;

  static {
    uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    uriMatcher.addURI(Constants.AUTHORITY, DummyTable.TABLE_NAME, URI_MATCH_DUMMY);
    uriMatcher.addURI(Constants.AUTHORITY, DummyTable.TABLE_NAME + "/#", URI_MATCH_DUMMY_ID);
  }
  
  //
  private DataBaseHelper dbHelper;
  
  //
  public static final String LOG_TAG = DataBaseProvider.class.getName();
}
/**
 * Created by guycole on 8/6/13.
 */
