package com.digiburo.fragdemo.app;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.digiburo.fragdemo.R;
import com.digiburo.fragdemo.utility.LogFacade;

/**
 * State form detail
 */
public class StateDetailFragment extends Fragment {

  /**
   * Define detail candidate
   * @param arg
   */
  public void setStateName(String arg) {
    stateName = arg;
  }

  /**
   * mandatory empty ctor
   */
  public StateDetailFragment() {
    //empty
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    LogFacade.entry(LOG_TAG, "onAttach");
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LogFacade.entry(LOG_TAG, "onCreate");
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    LogFacade.entry(LOG_TAG, "onCreateView");

    View view = inflater.inflate(R.layout.fragment_state_detail, container, false);
    return(view);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    LogFacade.entry(LOG_TAG, "onActivityCreated");

    tvName = (TextView) getActivity().findViewById(R.id.detail_name01);

    Button cancelButton = (Button) getActivity().findViewById(R.id.button_cancel01);
    cancelButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        LogFacade.debug(LOG_TAG, "cancel button");
        // do some work
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        fragmentManager.popBackStack();
      }
    });

    Button saveButton = (Button) getActivity().findViewById(R.id.button_save01);
    saveButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        LogFacade.debug(LOG_TAG, "save button");
        // do some work
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        fragmentManager.popBackStack();
      }
    });
  }

  @Override
  public void onStart() {
    super.onStart();
    LogFacade.entry(LOG_TAG, "onStart");
  }

  @Override
  public void onResume() {
    super.onResume();
    LogFacade.entry(LOG_TAG, "onResume");

    if (stateName != null) {
      tvName.setText(stateName);
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    LogFacade.entry(LOG_TAG, "onPause");
  }

  @Override
  public void onStop() {
    super.onStop();
    LogFacade.entry(LOG_TAG, "onStop");
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    LogFacade.entry(LOG_TAG, "onDestroyView");
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    LogFacade.entry(LOG_TAG, "onDestroy");
  }

  @Override
  public void onDetach() {
    super.onDetach();
    LogFacade.entry(LOG_TAG, "onDetach");
  }

  //
  private TextView tvName;

  //
  private String stateName;

  //
  public static final String LOG_TAG = StateDetailFragment.class.getName();
}
/**
 * Created by guycole on 8/8/13.
 */
