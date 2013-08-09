package com.digiburo.fragdemo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

/**
 * Menu option:About, shameless self promotion
 * Implemented as a WebView w/local HTML content
 */
public class AboutActivity extends Activity {
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LogFacade.entry(LOG_TAG, "onCreate");
    setContentView(R.layout.activity_about);

    ActionBar actionBar = getActionBar();
    actionBar.setHomeButtonEnabled(true);

    WebView webView = (WebView) findViewById(R.id.webView01);
    webView.loadUrl("file:///android_asset/html/about.html");
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    LogFacade.entry(LOG_TAG, "onOptionsItemSelected:" + item.getItemId());

    switch (item.getItemId()) {
      case android.R.id.home:
        LogFacade.debug(LOG_TAG, "menu home");
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  //
  public static final String LOG_TAG = AboutActivity.class.getName();
}
/**
 * Created by guycole on 8/8/13.
 */