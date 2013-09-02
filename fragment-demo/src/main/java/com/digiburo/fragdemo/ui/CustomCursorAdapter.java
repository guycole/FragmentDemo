package com.digiburo.fragdemo.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.digiburo.fragdemo.R;
import com.digiburo.fragdemo.content.DummyTable;
import com.digiburo.fragdemo.utility.LogFacade;

/**
 * cursor adapter for FiveFragment
 *
 * @author gsc
 */
public class CustomCursorAdapter extends CursorAdapter {

  /**
   *
   * @param context
   */
  public CustomCursorAdapter(Context context) {
    super(context, null, true);
    LogFacade.entry(LOG_TAG, "ctor");
    this.context = context;
  }

  @Override
  public void bindView(View view, Context context, Cursor cursor) {
    LogFacade.entry(LOG_TAG, "bindView");

    TextView tvName  = (TextView) view.findViewById(R.id.textName01);
    tvName.setText(cursor.getString(cursor.getColumnIndex(DummyTable.Columns.NAME)));

    LogFacade.exit(LOG_TAG, "bindView");
  }

  @Override
  public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
    LogFacade.entry(LOG_TAG, "newView");

    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.row_name, viewGroup, false);
    bindView(view, context, cursor);

    LogFacade.exit(LOG_TAG, "newView");
    return(view);
  }

  //
  private Context context;

  //
  public static final String LOG_TAG = CustomCursorAdapter.class.getName();
}
/**
 * Created by guycole on 8/11/13.
 */