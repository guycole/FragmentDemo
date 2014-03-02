package com.digiburo.fragdemo.app;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.digiburo.fragdemo.Constants;
import com.digiburo.fragdemo.R;
import com.digiburo.fragdemo.utility.LogFacade;

/**
 * Eye candy to pacify users for long running operations.
 *
 * @author gsc
 */
public class BusyCancelDialog extends DialogFragment {

  /**
   * invoke me
   */
  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    this.activity = activity;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    LogFacade.entry(LOG_TAG, "onCreate");
    super.onCreate(savedInstanceState);
    
    handler.postDelayed(runnable, 2345L);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.dialog_busy_cancel, container, false);
    getDialog().setTitle(R.string.dialog_busy_cancel);

    button = (Button) view.findViewById(R.id.buttonCancel01);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        LogFacade.entry(LOG_TAG, "onClick:cancelButton");
        activity.sendBroadcast(new Intent(Constants.ACTION_CANCEL_SOMETHING));
        dismiss();
      }
    });
 
    return(view);
  }
  
  /**
   * 
   */
  private Runnable runnable = new Runnable() {

    @Override
    public void run() {
      try {
        if (button != null) {
          button.setVisibility(View.VISIBLE);
        }
      } catch(Exception exception) {
        //empty
      }
    }
  };
  
  //
  private Button button;

  //
  private Activity activity;
  
  //
  private Handler handler = new Handler();

  //
  public static final String LOG_TAG = BusyCancelDialog.class.getName();
}

/**
 * Created by guycole on 8/23/13.
 */