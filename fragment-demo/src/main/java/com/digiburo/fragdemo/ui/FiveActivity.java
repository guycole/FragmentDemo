package com.digiburo.fragdemo.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.digiburo.fragdemo.R;
import com.digiburo.fragdemo.utility.LegalOptionMenuType;
import com.digiburo.fragdemo.utility.LogFacade;

/**
 * getActivityForResult from FiveFragment
 */
public class FiveActivity extends Activity {

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
    setContentView(R.layout.activity_five);

    ActionBar actionBar = getActionBar();
    actionBar.setHomeButtonEnabled(true);

    Button cancelButton = (Button) findViewById(R.id.button_cancel01);
    cancelButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        LogFacade.debug(LOG_TAG, "cancel button");
        setResult(RESULT_CANCELED);
        finish();
      }
    });

    Button saveButton = (Button) findViewById(R.id.button_save01);
    saveButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        LogFacade.debug(LOG_TAG, "save button");

        Intent intent = new Intent();
        intent.setAction("DEMO_ACTION");
        intent.putExtra("DEMO_KEY", 12345);

        setResult(RESULT_OK, intent);
        finish();
      }
    });
  }

  //
  public static final String LOG_TAG = FiveActivity.class.getName();
}
/**
 * Created by gsc on 9/7/13.
 */