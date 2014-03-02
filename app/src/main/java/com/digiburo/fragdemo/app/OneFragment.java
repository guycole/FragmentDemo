package com.digiburo.fragdemo.app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digiburo.fragdemo.R;
import com.digiburo.fragdemo.utility.LogFacade;

/**
 * Service the "one" tab - display platform build information (simple form)
 */
public class OneFragment extends Fragment {

  /**
   * mandatory empty ctor
   */
  public OneFragment() {
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

    View view = inflater.inflate(R.layout.fragment_one, container, false);
    return(view);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    LogFacade.entry(LOG_TAG, "onActivityCreated");

    tvBoard = (TextView) getActivity().findViewById(R.id.about_board01);
    tvBrand = (TextView) getActivity().findViewById(R.id.about_brand01);
    tvManufacturer = (TextView) getActivity().findViewById(R.id.about_manufacturer01);
    tvModel = (TextView) getActivity().findViewById(R.id.about_model01);
    tvName = (TextView) getActivity().findViewById(R.id.about_name01);
    tvProduct = (TextView) getActivity().findViewById(R.id.about_product01);
  }

  @Override
  public void onStart() {
    super.onStart();
    LogFacade.entry(LOG_TAG, "onStart");

    tvBoard.setText(Build.BOARD);
    tvBrand.setText(Build.BRAND);
    tvManufacturer.setText(Build.MANUFACTURER);
    tvModel.setText(Build.MODEL);
    tvName.setText(Build.DEVICE);
    tvProduct.setText(Build.PRODUCT);
  }

  @Override
  public void onResume() {
    super.onResume();
    LogFacade.entry(LOG_TAG, "onResume");
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
  private TextView tvBoard;
  private TextView tvBrand;
  private TextView tvManufacturer;
  private TextView tvModel;
  private TextView tvName;
  private TextView tvProduct;

  //
  public static final String LOG_TAG = OneFragment.class.getName();
}
/**
 * Created by guycole on 8/6/13.
 */
