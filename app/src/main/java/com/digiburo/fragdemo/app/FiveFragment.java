package com.digiburo.fragdemo.app;

import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.digiburo.fragdemo.R;
import com.digiburo.fragdemo.content.DataBaseTableIf;
import com.digiburo.fragdemo.content.DummyTable;
import com.digiburo.fragdemo.utility.LogFacade;

/**
 * Service the "five" tab - scrolling list w/loader and custom cursor adapter
 * Short press creates BusyCancelDialog
 * BusyCancelDialog communicates via Broadcast Intent
 */
public class FiveFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>  {

  /**
   * LoaderCallback
   */
  public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    LogFacade.entry(LOG_TAG, "onCreateLoader:" + id);

    DataBaseTableIf table = new DummyTable();

    String[] projection = table.getDefaultProjection();
    String orderBy = table.getDefaultSortOrder();

    String selection = null;
    String[] selectionArgs = null;

    CursorLoader loader = new CursorLoader(getActivity(), DummyTable.CONTENT_URI, projection, selection, selectionArgs, orderBy);
    return(loader);
  }

  /**
   * LoaderCallback
   */
  public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    LogFacade.entry(LOG_TAG, "onLoadFinished:" + loader.toString());
    adapter.swapCursor(data);
    adapter.notifyDataSetChanged();

    if (data == null) {
      LogFacade.debug(LOG_TAG, "load finished w/null cursor");
    } else {
      LogFacade.debug(LOG_TAG, "load finished w/cursor size:" + data.getCount() + ":column size:" + data.getColumnCount());
    }
  }

  /**
   * LoaderCallback
   */
  public void onLoaderReset(Loader<Cursor> loader) {
    LogFacade.entry(LOG_TAG, "onLoaderReset");
    adapter.swapCursor(null);
  }

  /**
   * row selection
   */
  @Override
  public void onListItemClick(ListView listView, View view, int position, long id) {
    LogFacade.debug(LOG_TAG, "click:" + position + ":" + id);

    Intent intent = new Intent(getActivity(), FiveActivity.class);
    startActivityForResult(intent, REQUEST_CODE);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    LogFacade.debug(LOG_TAG, "onActivityResult:" + requestCode + ":" + resultCode);
    super.onActivityResult(requestCode, resultCode, intent);

    if (resultCode == Activity.RESULT_CANCELED) {
      LogFacade.debug(LOG_TAG, "cancel noted");
    } else if (resultCode == Activity.RESULT_OK) {
      LogFacade.debug(LOG_TAG, "ok noted");
    }
  }

  /**
   * mandatory empty ctor
   */
  public FiveFragment() {
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

    View view = inflater.inflate(R.layout.fragment_five, container, false);
    return(view);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    LogFacade.entry(LOG_TAG, "onActivityCreated");

    //
    setHasOptionsMenu(false);

    //
    adapter = new CustomCursorAdapter(getActivity());
    setListAdapter(adapter);

    //
    getLoaderManager().initLoader(LOADER_ID, null, this);
  }

  @Override
  public void onStart() {
    super.onStart();
    LogFacade.entry(LOG_TAG, "onStart");
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
    setListAdapter(null);
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
  private CustomCursorAdapter adapter;

  //
  private String cursorFilter;

  //
  public static final int LOADER_ID = 271828;

  //
  public static final int REQUEST_CODE = 314156;

  //
  public static final String LOG_TAG = FiveFragment.class.getName();
}
/**
 * Created by guycole on 9/1/13.
 */
