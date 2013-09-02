package com.digiburo.fragdemo.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.digiburo.fragdemo.R;
import com.digiburo.fragdemo.utility.LogFacade;

/**
 * React to ActionBar tab events
 */
public class TabHelper implements ActionBar.TabListener, FragmentManager.OnBackStackChangedListener {

  public TabHelper(Activity activity) {
    mainActivity = activity;

    oneFragment = (OneFragment) Fragment.instantiate(mainActivity, OneFragment.class.getName());
    twoFragment = (TwoFragment) Fragment.instantiate(mainActivity, TwoFragment.class.getName());
    threeFragment = (ThreeFragment) Fragment.instantiate(mainActivity, ThreeFragment.class.getName());
    fourFragment = (FourFragment) Fragment.instantiate(mainActivity, FourFragment.class.getName());
    fiveFragment = (FiveFragment) Fragment.instantiate(mainActivity, FiveFragment.class.getName());

    stateDetailFragment = (StateDetailFragment) Fragment.instantiate(mainActivity, StateDetailFragment.class.getName());
  }

  /**
   * ActionBar.TabListener
   * @param tab
   * @param fragmentTransaction
   */
  @Override
  public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    LogFacade.entry(LOG_TAG, "onTabSelected:" + tab.getTag());

    if (ignoreMe) {
      //spurious event caused by changing navigation mode within onStateDeselect()
      ignoreMe = false;
      return;
    }

    if (tab.getTag().equals(TAG_ONE)) {
      fragmentTransaction.add(R.id.layoutFragment01, oneFragment, TAG_ONE);
    } else if (tab.getTag().equals(TAG_TWO)) {
      fragmentTransaction.add(R.id.layoutFragment01, twoFragment, TAG_TWO);
    } else if (tab.getTag().equals(TAG_THREE)) {
      fragmentTransaction.add(R.id.layoutFragment01, threeFragment, TAG_THREE);
    } else if (tab.getTag().equals(TAG_FOUR)) {
      fragmentTransaction.add(R.id.layoutFragment01, fourFragment, TAG_FOUR);
    } else if (tab.getTag().equals(TAG_FIVE)) {
      fragmentTransaction.add(R.id.layoutFragment01, fiveFragment, TAG_FIVE);
    } else {
      throw new IllegalArgumentException("unknown tab:" + tab.getTag());
    }

    LogFacade.exit(LOG_TAG, "onTabSelected:" + tab.getTag());
  }

  /**
   * ActionBar.TabListener
   * @param tab
   * @param fragmentTransaction
   */
  @Override
  public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    LogFacade.entry(LOG_TAG, "onTabUnselected:" + tab.getTag());

    if (tab.getTag().equals(TAG_ONE)) {
      fragmentTransaction.remove(oneFragment);
    } else if (tab.getTag().equals(TAG_TWO)) {
      fragmentTransaction.remove(twoFragment);
    } else if (tab.getTag().equals(TAG_THREE)) {
      fragmentTransaction.remove(threeFragment);
    } else if (tab.getTag().equals(TAG_FOUR)) {
      fragmentTransaction.remove(fourFragment);
    } else if (tab.getTag().equals(TAG_FIVE)) {
      fragmentTransaction.remove(fiveFragment);
    } else {
      throw new IllegalArgumentException("unknown tab:" + tab.getTag());
    }

    LogFacade.exit(LOG_TAG, "onTabUnselected:" + tab.getTag());
  }

  /**
   * ActionBar.TabListener
   * @param tab
   * @param fragmentTransaction
   */
  @Override
  public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    LogFacade.entry(LOG_TAG, "onTabReselected:" + tab.getTag());

    if (tab.getTag().equals(TAG_ONE)) {
      //empty
    } else if (tab.getTag().equals(TAG_TWO)) {
      //empty
    } else if (tab.getTag().equals(TAG_THREE)) {
      //empty
    } else if (tab.getTag().equals(TAG_FOUR)) {
      //empty
    } else if (tab.getTag().equals(TAG_FIVE)) {
      //empty
    } else {
      throw new IllegalArgumentException("unknown tab:" + tab.getTag());
    }

    LogFacade.exit(LOG_TAG, "onTabReselected:" + tab.getTag());
  }

  /**
   * FragmentManager.OnBackStackChangedListener
   */
  public void onBackStackChanged() {
    LogFacade.entry(LOG_TAG, "onBackStackChanged");
    LogFacade.exit(LOG_TAG, "onBackStackChanged");
  }

  /**
   * populate ActionBar
   */
  public void initialize() {
    LogFacade.entry(LOG_TAG, "initialize");

    FragmentManager fragmentManager = mainActivity.getFragmentManager();
    fragmentManager.addOnBackStackChangedListener(this);

    ActionBar actionBar = mainActivity.getActionBar();

    oneTab = actionBar.newTab();
    twoTab = actionBar.newTab();
    threeTab = actionBar.newTab();
    fourTab = actionBar.newTab();
    fiveTab = actionBar.newTab();

    oneTab.setTabListener(this);
    twoTab.setTabListener(this);
    threeTab.setTabListener(this);
    fourTab.setTabListener(this);
    fiveTab.setTabListener(this);

    oneTab.setTag(TAG_ONE);
    twoTab.setTag(TAG_TWO);
    threeTab.setTag(TAG_THREE);
    fourTab.setTag(TAG_FOUR);
    fiveTab.setTag(TAG_FIVE);

    oneTab.setText(R.string.menu_application_bar_one);
    twoTab.setText(R.string.menu_application_bar_two);
    threeTab.setText(R.string.menu_application_bar_three);
    fourTab.setText(R.string.menu_application_bar_four);
    fiveTab.setText(R.string.menu_application_bar_five);

    actionBar.addTab(oneTab);
    actionBar.addTab(twoTab);
    actionBar.addTab(threeTab);
    actionBar.addTab(fourTab);
    actionBar.addTab(fiveTab);

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
    } else if (arg.equals(TAG_FIVE)) {
      return(fiveTab);
    }

    throw new IllegalArgumentException("unsupported tag:" + arg);
  }

  /**
   * State detail fragment is leaving, restore tabs
   * @param tabTag
   */
  public void onStateDeselect(String tabTag) {
    // when changing navigation mode, the platform will invoke onTabSelected for last tab
    // this must be ignored because of attempt to add an already existing fragment
    ignoreMe = true;
    ActionBar actionBar = mainActivity.getActionBar();
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
  }

  /**
   * State detail fragment is being created, hide tabs and switch fragment
   * @param stateName
   * @param tabTag
   */
  public void onStateSelect(String stateName, String tabTag) {
    stateDetailFragment = new StateDetailFragment();
    stateDetailFragment.setStateName(stateName);

    FragmentManager fragmentManager = mainActivity.getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    if (tabTag.equals(TAG_ONE)) {
      fragmentTransaction.remove(oneFragment);
    } else if (tabTag.equals(TAG_TWO)) {
      fragmentTransaction.remove(twoFragment);
    } else if (tabTag.equals(TAG_THREE)) {
      fragmentTransaction.remove(threeFragment);
    } else if (tabTag.equals(TAG_FOUR)) {
      fragmentTransaction.remove(fourFragment);
    } else if (tabTag.equals(TAG_FIVE)) {
      fragmentTransaction.remove(fiveFragment);
    }

    fragmentTransaction.add(R.id.layoutFragment01, stateDetailFragment, TAG_STATE_DETAIL);

    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();

    ActionBar actionBar = mainActivity.getActionBar();
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
  }

  //
  private final Activity mainActivity;

  //
  private ActionBar.Tab oneTab;
  private ActionBar.Tab twoTab;
  private ActionBar.Tab threeTab;
  private ActionBar.Tab fourTab;
  private ActionBar.Tab fiveTab;

  //
  private final OneFragment oneFragment;
  private final TwoFragment twoFragment;
  private final ThreeFragment threeFragment;
  private final FourFragment fourFragment;
  private final FiveFragment fiveFragment;

  private StateDetailFragment stateDetailFragment;

  // kludge
  private boolean ignoreMe = false;

  //
  public static final String TAG_ONE = "TAG_ONE";
  public static final String TAG_TWO = "TAG_TWO";
  public static final String TAG_THREE = "TAG_THREE";
  public static final String TAG_FOUR = "TAG_FOUR";
  public static final String TAG_FIVE = "TAG_FIVE";

  public static final String TAG_STATE_DETAIL = "TAG_STATE_DETAIL";

  //
  public static final String LOG_TAG = TabHelper.class.getName();
}
/**
 * Created by guycole on 8/6/13.
 */
