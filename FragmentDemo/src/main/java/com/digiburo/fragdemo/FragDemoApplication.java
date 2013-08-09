package com.digiburo.fragdemo;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import com.digiburo.fragdemo.utility.LogFacade;

/**
 * perform application startup chores
 */
public class FragDemoApplication extends Application {

  @Override
  public void onCreate() {
    if (Constants.DEBUG_APPLICATION_MODE) {
      Log.i(LOG_TAG, "----application start w/debug mode true");
    } else {
      Log.i(LOG_TAG, "----application start w/debug mode false");
    }
  }

  @Override
  public void onConfigurationChanged(Configuration configuration) {
    super.onConfigurationChanged(configuration);
    LogFacade.entry(LOG_TAG, "onConfigurationChanged");
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
    LogFacade.error(LOG_TAG, "low memory");
  }

  @Override
  public void onTerminate() {
    super.onTerminate();
    LogFacade.info(LOG_TAG, "terminate");
  }

  //
  public static final String LOG_TAG = FragDemoApplication.class.getName();
}
/**
 * Created by guycole on 8/6/13.
 */
