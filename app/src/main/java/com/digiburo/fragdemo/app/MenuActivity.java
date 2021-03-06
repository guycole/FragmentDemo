package com.digiburo.fragdemo.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.digiburo.fragdemo.R;
import com.digiburo.fragdemo.utility.LegalOptionMenuType;
import com.digiburo.fragdemo.utility.LogFacade;

/**
 * support for menu selection
 */
public class MenuActivity extends Activity {

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    LogFacade.entry(LOG_TAG, "onOptionsItemSelected:" + item.getItemId());

    switch (item.getItemId()) {
      case android.R.id.home:
        LogFacade.debug(LOG_TAG, "home selected");
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        break;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    LogFacade.entry(LOG_TAG, "onCreate");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu);

    ActionBar actionBar = getActionBar();
    actionBar.setHomeButtonEnabled(true);

    Intent intent = getIntent();
    if (intent == null) {
      LogFacade.error(LOG_TAG, "null intent");
      finish();
    } else {
      Fragment fragment = null;

      String action = intent.getAction();
      LegalOptionMenuType menuType = LegalOptionMenuType.discoverMatchingEnum(action);
      switch(menuType) {
        case ABOUT:
          LogFacade.debug(LOG_TAG, "about noted");
          fragment = new AboutFragment();
          break;
        case SETTINGS:
          LogFacade.debug(LOG_TAG, "settings noted");
          fragment = new SettingsFragment();
          break;
        default:
          LogFacade.error(LOG_TAG, "unsupported option menu type:" + action);
          finish();
      }

      if (fragment != null) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layoutFragment01, fragment);
        fragmentTransaction.commit();
      }
    }
  }

  //
  public static final String LOG_TAG = MenuActivity.class.getName();
}
/**
 * Created by gsc on 8/18/13.
 */