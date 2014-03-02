package com.digiburo.fragdemo.content;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.digiburo.fragdemo.utility.LogFacade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gsc on 8/18/13.
 */
public class ContentFacade {

  /**
   *
   * @param context
   * @return
   */
  public List<DummyModel> getDummyList(Context context) {
    LogFacade.entry(LOG_TAG, "getDummyList");

    ArrayList<DummyModel> list = new ArrayList<DummyModel>();

    DummyTable table = new DummyTable();
    String[] projection = table.getDefaultProjection();
    String selection = null;
    String[] selectionArgs = null;
    String sortOrder = DummyTable.DEFAULT_SORT_ORDER;

    Cursor cursor = null;

    try {
      cursor = context.getContentResolver().query(DummyTable.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
      if (cursor.moveToFirst()) {
        do {
          DummyModel model = new DummyModel();
          model.setDefault();
          model.fromCursor(cursor);
          list.add(model);
        } while(cursor.moveToNext());
      } else {
        LogFacade.debug(LOG_TAG, "getDummyList:select failure");
      }
    } catch(SQLException exception) {
      LogFacade.error(LOG_TAG, exception);
    } finally {
      if (cursor != null) {
        cursor.close();
      }
    }

    return(list);
  }

  //
  public static final String LOG_TAG = ContentFacade.class.getName();
}
