package com.digiburo.fragdemo.ui;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import com.digiburo.fragdemo.utility.LegalOptionMenuType;
import com.digiburo.fragdemo.utility.LogFacade;
import com.digiburo.fragdemo.R;

/**
 *
 */
public class MainActivity extends Activity implements TwoListener {

  /**
   * Remove state detail fragment
   * @param tabTag
   */
  public void onStateDeselect(String tabTag) {
    LogFacade.entry(LOG_TAG, "onStateDeselect:" + tabTag);
    tabHelper.onStateDeselect(tabTag);
    LogFacade.exit(LOG_TAG, "onStateDeselect");
  }

  /**
   * Display state detail fragment
   * @param stateName state name
   * @param tabTag parent tab
   */
  public void onStateSelect(String stateName, String tabTag) {
    LogFacade.entry(LOG_TAG, "onStateSelect:" + stateName + ":" + tabTag);
    tabHelper.onStateSelect(stateName, tabTag);
    LogFacade.exit(LOG_TAG, "onStateSelect");
  }

  /**
   * display delete dialog
   * @param title
   * @param message
   */
  public void createStateDeleteDialog(int title, int message) {
    LogFacade.entry(LOG_TAG, "createStateDeleteDialog");

    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    Fragment oldFragment = fragmentManager.findFragmentByTag(DIALOG_DELETE);
    if (oldFragment != null) {
      fragmentTransaction.remove(oldFragment);
    }

    DeleteDialogFragment ddf = DeleteDialogFragment.newInstance(R.string.alert_delete_title, R.string.alert_delete_message);
    ddf.show(getFragmentManager(), "deleteDialog");

    LogFacade.exit(LOG_TAG, "createStateDeleteDialog");
  }

  /**
   * perform delete
   */
  public void onStateDeleteYes() {
    LogFacade.entry(LOG_TAG, "onStateDeleteYes");
    LogFacade.exit(LOG_TAG, "onStateDeleteYes");
  }

  /**
   * cancel delete
   */
  public void onStateDeleteNo() {
    LogFacade.entry(LOG_TAG, "onStateDeleteNo");
    LogFacade.exit(LOG_TAG, "onStateDeleteNo");
  }

  @Override
  protected void onStart() {
    super.onStart();
    LogFacade.entry(LOG_TAG, "onStart");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    LogFacade.entry(LOG_TAG, "onRestart");
  }

  @Override
  protected void onResume() {
    super.onResume();
    LogFacade.entry(LOG_TAG, "onResume");
  }

  @Override
  protected void onPause() {
    super.onPause();
    LogFacade.entry(LOG_TAG, "onPause");
  }

  @Override
  protected void onStop() {
    super.onStop();
    LogFacade.entry(LOG_TAG, "onStop");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    LogFacade.entry(LOG_TAG, "onDestroy");
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    LogFacade.entry(LOG_TAG, "onCreate");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ActionBar actionBar = getActionBar();
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

    tabHelper = new TabHelper(this);
    tabHelper.initialize();
  }

  @Override
  public void onRestoreInstanceState(Bundle savedInstanceState) {
    if (savedInstanceState.containsKey(SELECTED_TAB_NDX)) {
      getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(SELECTED_TAB_NDX));
    }
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    outState.putInt(SELECTED_TAB_NDX, getActionBar().getSelectedNavigationIndex());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    LogFacade.entry(LOG_TAG, "onOptionsItemSelected:" + item.getItemId());

    Intent intent = new Intent(this, MenuActivity.class);

    switch (item.getItemId()) {
      case R.id.menu_about:
        intent.setAction(LegalOptionMenuType.ABOUT.getName());
        break;
      case R.id.menu_settings:
        intent.setAction(LegalOptionMenuType.SETTINGS.getName());
        break;
      default:
        return super.onOptionsItemSelected(item);
    }

    startActivity(intent);
    return(true);
  }

  //
  private TabHelper tabHelper;

  //
  public static final String SELECTED_TAB_NDX = "SELECTED_TAB_NDX";

  //
  public static final String DIALOG_DELETE = "DIALOG_DELETE";

  //
  public static final String LOG_TAG = MainActivity.class.getName();
}
/**
 * Created by guycole on 8/6/13.
 */
