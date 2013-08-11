package com.digiburo.fragdemo.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.digiburo.fragdemo.R;
import com.digiburo.fragdemo.content.DummyTable;
import com.digiburo.fragdemo.utility.LogFacade;

/**
 * support Listview of String items
 *
 * @author gsc
 */
public class CustomCursorAdapter extends SimpleCursorAdapter {

  public CustomCursorAdapter(Context context, int layout, Cursor cursor, String[] from, int[] to) {
    super(context, layout, cursor, from, to, 0);
    LogFacade.entry(LOG_TAG, "ctor");
    this.context = context;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LogFacade.entry(LOG_TAG, "getView:" + position);

    View view = convertView;
    if (view == null) {
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      view = inflater.inflate(R.layout.row_four, null);
    }

    if (readFromCursor(position)) {
      decorateView(view);
    } else {
      LogFacade.debug(LOG_TAG, "readFromCursor failure noted:" + position);
    }

    LogFacade.exit(LOG_TAG, "getView");
    return(view);
  }

  /**
   *
   * @param view
   */
  private void decorateView(View view) {
    tvName = (TextView) view.findViewById(R.id.textName01);
    tvName.setText(name);
  }

  /**
   * Read field values from cursor.
   * @param position
   * @return true if success
   */
  private boolean readFromCursor(int position) {
    Cursor cursor = getCursor();
    if (!cursor.moveToPosition(position)) {
      LogFacade.error(LOG_TAG, "moveTo failure for:" + position);
      return(false);
    }

    try {
      rowId = cursor.getLong(cursor.getColumnIndex(DummyTable.Columns._ID));
      name = cursor.getString(cursor.getColumnIndex(DummyTable.Columns.NAME));
    } catch(Exception exception) {
      LogFacade.error(LOG_TAG, exception);
      return(false);
    }

    return(true);
  }

  //
  private String name;
  private Long rowId;

  //
  private TextView tvName;

  //
  private Context context;

  //
  public static final String LOG_TAG = CustomCursorAdapter.class.getName();
}
/**
 * Created by guycole on 8/11/13.
 */