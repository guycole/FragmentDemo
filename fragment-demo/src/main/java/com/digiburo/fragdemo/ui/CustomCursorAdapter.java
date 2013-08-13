package com.digiburo.fragdemo.ui;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.digiburo.fragdemo.R;
import com.digiburo.fragdemo.content.DummyModel;
import com.digiburo.fragdemo.content.DummyTable;
import com.digiburo.fragdemo.utility.LegalRowType;
import com.digiburo.fragdemo.utility.LogFacade;

import java.sql.SQLException;

/**
 * cursor adapter for FourFragment
 * demonstrate handling of RadioGroup inside a ListView
 * demonstrate handling of multiple row types
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
  public int getViewTypeCount() {
    LogFacade.entry(LOG_TAG, "getViewTypeCount");
    return(2);
  }

  @Override
  public int getItemViewType(int position) {
    LogFacade.entry(LOG_TAG, "getItemViewType:" + position);

    int result = 0;

    DummyModel model = readFromCursor(position);
    if (model != null) {
      result = getItemViewType(model);
    }

    return(result);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LogFacade.entry(LOG_TAG, "getView:" + position);

    final DummyModel currentModel = readFromCursor(position);
    if (currentModel == null) {
      LogFacade.error(LOG_TAG, "readFromCursor:" + position + ":failure noted");
    }

    View view = null;
    ViewHolder holder = null;

    if (convertView == null) {
      LogFacade.debug(LOG_TAG, "getView w/null convertView");

      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      view = inflater.inflate(R.layout.row_four, null);

      holder = new ViewHolder(view);
      view.setTag(holder);
    } else {
      LogFacade.debug(LOG_TAG, "getView w/non-null convertView");

      view = convertView;
      holder = (ViewHolder) view.getTag();
    }

    holder.radioGroup.setTag(new Integer(position));
    holder.tvName.setText(currentModel.getName());
    holder.radioGroup.clearCheck();

    if (holder.radioGroup != null) {
      LogFacade.debug(LOG_TAG, "radio group not null:" + holder.radioGroup.getTag() + ":" + currentModel.getRadioButton());

      switch(currentModel.getRadioButton()) {
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
    }

    LogFacade.exit(LOG_TAG, "getView");
    return(view);
  }

  private void updateContentProvider(DummyModel model) {
    LogFacade.entry(LOG_TAG, "updateContentProvider:" + model.getId());

    String selection = DummyTable.Columns._ID + "=?";
    String[] selectionArgs = new String[] {model.getId().toString()};

    int count = 0;
    if (model.getId() > 0) {
//      count = context.getContentResolver().update(DummyTable.CONTENT_URI, model.toContentValues(), selection, selectionArgs);
//      LogFacade.debug(LOG_TAG, "update result:" + count + ":" + model.getName() + ":" + model.getId());
    }
  }

  /**
   * Read field values from cursor.
   * @param position
   * @return populated model or null if fail
   */
  private DummyModel readFromCursor(int position) {
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

  /**
   * Map row type to layout
   * @param model
   * @return
   */
  private int getItemViewType(DummyModel model) {
    int result = 0;

    switch(model.getRowType()) {
      case NAME_AND_RADIO:
        result = 1;
      case NAME_ONLY:
        result = 0;
      default:
        result = 0;
    }

    return(result);
  }

  class ViewHolder {
    ViewHolder(View view) {
      tvName = (TextView) view.findViewById(R.id.textName01);

      radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup01);
      radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
          Integer position = (Integer) radioGroup.getTag();
          DummyModel model = readFromCursor(position);

          switch(checkedId) {
            case R.id.radioButton01:
              LogFacade.debug(LOG_TAG, "button1:" + position);
              model.setRadioButton(1);
              updateContentProvider(model);
              break;
            case R.id.radioButton02:
              LogFacade.debug(LOG_TAG, "button2:" + position);
              model.setRadioButton(2);
              updateContentProvider(model);
              break;
            case R.id.radioButton03:
              LogFacade.debug(LOG_TAG, "button3:" + position);
              model.setRadioButton(3);
              updateContentProvider(model);
              break;
            default:
//              LogFacade.error(LOG_TAG, "unsupported option for:" + currentModel.getId() + ":" + currentModel.getName());
              break;
          }
        }
      });
    }

    //
    private TextView tvName;
    private RadioGroup radioGroup;

  }

  //
  private Context context;

  //
  public static final String LOG_TAG = CustomCursorAdapter.class.getName();
}
/**
 * Created by guycole on 8/11/13.
 */