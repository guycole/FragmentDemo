package com.digiburo.fragdemo;

import android.app.ActionBar;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 *
 */
public class MainActivity extends Activity {

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
    super.onCreate(savedInstanceState);
    LogFacade.entry(LOG_TAG, "onCreate");
    setContentView(R.layout.activity_main);

    ActionBar actionBar = getActionBar();
    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

    tabDispatch = new TabDispatch(this);
    tabDispatch.initialize();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  //
  private TabDispatch tabDispatch;

  //
  public static final String LOG_TAG = MainActivity.class.getName();
}
/**
 * Created by guycole on 8/6/13.
 */
