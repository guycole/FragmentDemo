package com.digiburo.fragdemo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.digiburo.fragdemo.R;
import com.digiburo.fragdemo.content.DummyModel;
import com.digiburo.fragdemo.utility.LogFacade;

import java.util.List;

/**
 * support Listview of DummyModel items
 * there are two row views, name only and name w/radio button
 *
 * @author gsc
 */
public class CustomArrayAdapter extends ArrayAdapter<DummyModel> {

  /**
   * ctor
   * @param context
   * @param list
   */
  public CustomArrayAdapter(Context context, List<DummyModel> list) {
    super(context, R.layout.row_radio, list);
    LogFacade.entry(LOG_TAG, "ctor w/list size:" + list.size());
    this.context = context;
  }

  @Override
  public int getViewTypeCount() {
    LogFacade.entry(LOG_TAG, "getViewTypeCount");
    return(2);
  }

  @Override
  public int getItemViewType(int position) {
    LogFacade.entry(LOG_TAG, "getItemViewType:" + position);

    int result = 0;

    DummyModel model = getItem(position);
    switch(model.getRowType()) {
      case NAME_AND_RADIO:
        result = 0;
        break;
      case NAME_ONLY:
        result = 1;
        break;
    }

    return(result);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    DummyModel item = getItem(position);
    LogFacade.entry(LOG_TAG, "getView:" + position + ":" + item.getName() + ":" + item.getRowType() + ":" + item.getRadioButton());

    View rowView;
    ViewHolder holder;

    if (convertView == null) {
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

      switch(item.getRowType()) {
        case NAME_AND_RADIO:
          rowView = inflater.inflate(R.layout.row_radio, null);
          break;
        case NAME_ONLY:
          rowView = inflater.inflate(R.layout.row_name, null);
          break;
        default:
          throw new IllegalArgumentException("unsupported row type");
      }

      holder = new ViewHolder(rowView);
      rowView.setTag(holder);
    } else {
      rowView = convertView;
      holder = (ViewHolder) rowView.getTag();
    }

    // common to both row views
    holder.tvName.setText(item.getName());

    switch(item.getRowType()) {
      case NAME_ONLY:
        break;
      case NAME_AND_RADIO:
        holder.radioGroup.setTag(position);

        switch(item.getRadioButton()) {
          case 1:
            holder.radioGroup.check(R.id.radioButton01);
            break;
          case 2:
            holder.radioGroup.check(R.id.radioButton02);
            break;
          case 3:
            holder.radioGroup.check(R.id.radioButton03);
            break;
          default:
            holder.radioGroup.clearCheck();
        }

        break;
    }

    return(rowView);
  }

  class ViewHolder {
    ViewHolder(View view) {
      tvName = (TextView) view.findViewById(R.id.textName01);
      radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup01);
      if (radioGroup != null) {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            LogFacade.entry(LOG_TAG, "onCheckedChange:" + checkedId + ":" + radioGroup.getTag());
            int position = (Integer) radioGroup.getTag();
            DummyModel model = getItem(position);

            switch(checkedId) {
              case R.id.radioButton01:
                LogFacade.debug(LOG_TAG, "button1:" + position);
                model.setRadioButton(1);
                break;
              case R.id.radioButton02:
                LogFacade.debug(LOG_TAG, "button2:" + position);
                model.setRadioButton(2);
                break;
              case R.id.radioButton03:
                LogFacade.debug(LOG_TAG, "button3:" + position);
                model.setRadioButton(3);
                break;
              default:
                LogFacade.debug(LOG_TAG, "default radio");
                radioGroup.clearCheck();
                break;
            }
          }
        });
      }
    }

    //
    private RadioGroup radioGroup;
    private TextView tvName;
  }

  //
  private final Context context;

  //
  public static final String LOG_TAG = CustomArrayAdapter.class.getName();
}
/**
 * Created by guycole on 8/6/13.
 */