package com.digiburo.fragdemo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * support listview of String items
 *
 * @author gsc
 */
public class CustomArrayAdapter extends ArrayAdapter<String> {

  /**
   * ctor
   * @param context
   * @param list
   */
  public CustomArrayAdapter(Context context, List<String> list) {
    super(context, R.layout.row_three);

    this.context = context;
    this.list = list;

    addAll(list);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LogFacade.entry(LOG_TAG, "getView:" + position + ":" + getItem(position));

    String item = getItem(position);

    View rowView = null;

    if (convertView == null) {
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      rowView = inflater.inflate(R.layout.row_three, null);
    } else {
      rowView = convertView;
    }

    TextView textView = (TextView) rowView.findViewById(R.id.textName01);
    textView.setText(getItem(position));

    return(rowView);
  }

  //
  private final List<String> list;

  //
  private final Context context;

  //
  public static final String LOG_TAG = CustomArrayAdapter.class.getName();
}
/**
 * Created by guycole on 8/6/13.
 */