package com.digiburo.fragdemo;

import android.app.Application;
import android.content.res.Configuration;
import android.net.Uri;
import android.util.Log;

import com.digiburo.fragdemo.content.DummyModel;
import com.digiburo.fragdemo.content.DummyTable;
import com.digiburo.fragdemo.utility.LegalRowType;
import com.digiburo.fragdemo.utility.LogFacade;

import java.util.Random;

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

    // generate some random database items
    Random random = new Random();

    for (int ii = 0; ii < 10; ii++) {
      DummyModel model = new DummyModel();
      model.setDefault();
      model.setName(Long.toHexString(random.nextLong()));

      switch(ii%2) {
        case 0:
          model.setRowType(LegalRowType.NAME_ONLY);
          break;
        case 1:
          model.setRowType(LegalRowType.NAME_AND_RADIO);
          int radioButton = 1+(ii%3);
          model.setRadioButton(radioButton);
          break;
      }

      Uri result = getContentResolver().insert(DummyTable.CONTENT_URI, model.toContentValues());
      System.out.println(model.getId() + ":" + model.getName() + ":" + result);
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
