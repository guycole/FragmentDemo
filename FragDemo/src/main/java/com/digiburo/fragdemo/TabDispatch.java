package com.digiburo.fragdemo;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 *
 */
public class TabDispatch implements ActionBar.TabListener {

  public TabDispatch(Activity activity) {
    mainActivity = activity;
    oneFragment = (OneFragment) Fragment.instantiate(mainActivity, OneFragment.class.getName());
    twoFragment = (TwoFragment) Fragment.instantiate(mainActivity, TwoFragment.class.getName());
    threeFragment = (ThreeFragment) Fragment.instantiate(mainActivity, ThreeFragment.class.getName());
    fourFragment = (FourFragment) Fragment.instantiate(mainActivity, FourFragment.class.getName());
  }

  public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
    LogFacade.entry(LOG_TAG, "onTabSelected:" + tab.getTag());
    LogFacade.exit(LOG_TAG, "onTabSelected:" + tab.getTag());
  }

  public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    LogFacade.entry(LOG_TAG, "onTabUnselected:" + tab.getTag());
    LogFacade.exit(LOG_TAG, "onTabUnselected:" + tab.getTag());
  }

  public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    LogFacade.entry(LOG_TAG, "onTabReselected:" + tab.getTag());
    LogFacade.exit(LOG_TAG, "onTabReselected:" + tab.getTag());
  }

  /**
   * populate ActionBar
   */
  public void initialize() {
    LogFacade.entry(LOG_TAG, "initialize");

    ActionBar actionBar = mainActivity.getActionBar();

    oneTab = actionBar.newTab();
    oneTab.setTabListener(this);
    oneTab.setTag(TAG_ONE);
    oneTab.setText(R.string.menu_application_bar_one);
    actionBar.addTab(oneTab);

    twoTab = actionBar.newTab();
    twoTab.setTabListener(this);
    twoTab.setTag(TAG_TWO);
    twoTab.setText(R.string.menu_application_bar_two);
    actionBar.addTab(twoTab);

    threeTab = actionBar.newTab();
    threeTab.setTabListener(this);
    threeTab.setTag(TAG_THREE);
    threeTab.setText(R.string.menu_application_bar_three);
    actionBar.addTab(threeTab);

    fourTab = actionBar.newTab();
    fourTab.setTabListener(this);
    fourTab.setTag(TAG_THREE);
    fourTab.setText(R.string.menu_application_bar_four);
    actionBar.addTab(fourTab);

    LogFacade.exit(LOG_TAG, "initialize");
  }

  /**
   * Map a tag to ActionBar.Tab
   * @param arg
   * @return related tab
   */
  public ActionBar.Tab tagToTab(String arg) {
    if (arg.equals(TAG_ONE)) {
      return(oneTab);
    } else if (arg.equals(TAG_TWO)) {
      return(twoTab);
    } else if (arg.equals(TAG_THREE)) {
      return(threeTab);
    } else if (arg.equals(TAG_FOUR)) {
      return(fourTab);
    }

    throw new IllegalArgumentException("unsupported tag:" + arg);
  }

  //
  private Activity mainActivity;

  //
  private ActionBar.Tab oneTab;
  private ActionBar.Tab twoTab;
  private ActionBar.Tab threeTab;
  private ActionBar.Tab fourTab;

  //
  private OneFragment oneFragment;
  private TwoFragment twoFragment;
  private ThreeFragment threeFragment;
  private FourFragment fourFragment;

  //
  public static final String TAG_ONE = "TAG_ONE";
  public static final String TAG_TWO = "TAG_TWO";
  public static final String TAG_THREE = "TAG_THREE";
  public static final String TAG_FOUR = "TAG_FOUR";

  //
  public static final String LOG_TAG = TabDispatch.class.getName();
}
/**
 * Created by guycole on 8/6/13.
 */
