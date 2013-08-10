package com.digiburo.fragdemo.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.digiburo.fragdemo.content.DataBaseTableIf;
import com.digiburo.fragdemo.content.DummyTable;
import com.digiburo.fragdemo.utility.LogFacade;
import com.digiburo.fragdemo.R;

/**
 *
 */
public class FourFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>  {

  /**
   * LoaderCallback
   */
  public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    LogFacade.entry(LOG_TAG, "onCreateLoader");

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
  }

    /**
     * mandatory empty ctor
     */
  public FourFragment() {
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

    // pacifiers
    String[] columnz = {DummyTable.Columns.NAME};
    int[] rowAttrz = {R.id.textName01};

    adapter = new SimpleCursorAdapter(getActivity(), R.layout.row_four, null, columnz, rowAttrz, 0);

    getLoaderManager().initLoader(0,  null, this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    LogFacade.entry(LOG_TAG, "onCreateView");

    View view = inflater.inflate(R.layout.fragment_four, container, false);
    return(view);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    LogFacade.entry(LOG_TAG, "onActivityCreated");

    //
    setListAdapter(adapter);
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
  private SimpleCursorAdapter adapter;

  //
  public static final String LOG_TAG = FourFragment.class.getName();
}
/**
 * Created by guycole on 8/6/13.
 */
