package com.digiburo.fragdemo.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.digiburo.fragdemo.R;
import com.digiburo.fragdemo.content.DummyModel;
import com.digiburo.fragdemo.utility.LogFacade;

/**
 * simple cursor adapter for FourFragment
 *
 * @author gsc
 */
public class CustomSimpleCursorAdapter extends SimpleCursorAdapter {

  /**
   *
   * @param context
   * @param from
   * @param to
   */
  public CustomSimpleCursorAdapter(Context context, String[] from, int[] to) {
    super(context, R.layout.row_name, null, from, to, 0);
    LogFacade.entry(LOG_TAG, "ctor");
    this.context = context;
  }

  /**
   *
   * @param position
   * @param convertView
   * @param parent
   * @return
   */
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LogFacade.entry(LOG_TAG, "getView:" + position);

    final DummyModel currentModel = readFromCursor(position);
    if (currentModel == null) {
      throw new IllegalArgumentException("readFromCursor:" + position + ":failure noted");
    }

    View view;
    ViewHolder holder;

    if (convertView == null) {
      LogFacade.debug(LOG_TAG, "getView w/null convertView");

      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      view = inflater.inflate(R.layout.row_name, null);

      holder = new ViewHolder(view);
      view.setTag(holder);
    } else {
      LogFacade.debug(LOG_TAG, "getView w/non-null convertView");

      view = convertView;
      holder = (ViewHolder) view.getTag();
    }

    holder.tvName.setText(currentModel.getName());

    LogFacade.exit(LOG_TAG, "getView");
    return(view);
  }

  /**
   * Read field values from cursor.
   * @param position
   * @return populated model or null if fail
   */
  private DummyModel readFromCursor(int position) {
    LogFacade.entry(LOG_TAG, "readFromCursor:" + position);

    Cursor cursor = getCursor();
    if (!cursor.moveToPosition(position)) {
      LogFacade.error(LOG_TAG, "moveTo failure for:" + position);
      return(null);
    }

    DummyModel result = new DummyModel();

    try {
      result.setDefault();
      result.fromCursor(cursor);
    } catch(Exception exception) {
      LogFacade.error(LOG_TAG, exception);
      return(null);
    }

    return(result);
  }

  class ViewHolder {
    ViewHolder(View view) {
      tvName = (TextView) view.findViewById(R.id.textName01);
    }

    //
    private TextView tvName;
  }

  //
  private Context context;

  //
  public static final String LOG_TAG = CustomSimpleCursorAdapter.class.getName();
}
/**
 * Created by guycole on 8/11/13.
 */