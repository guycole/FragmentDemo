package com.digiburo.fragdemo.app;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.digiburo.fragdemo.R;
import com.digiburo.fragdemo.utility.LogFacade;

/**
 * Service the "two" tab - simple scrolling list w/no loader
 * Supports short and long press on row
 */
public class TwoFragment extends ListFragment {

  /**
   * mandatory empty ctor
   */
  public TwoFragment() {
    //empty
  }

  /**
   * short press
   * @param listView
   * @param view
   * @param position
   * @param id
   */
  @Override
  public void onListItemClick(ListView listView, View view, int position, long id) {
    LogFacade.entry(LOG_TAG, "click:" + position + ":" + id);
    twoListener.onStateSelect((String) arrayAdapter.getItem(position), TabHelper.TAG_TWO);
    returnFromDetailFlag = true;
  }

  /**
   * delete row (long click)
   */
  @Override
  public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
    menu.add(0, CONTEXT_ITEM_1, 0, R.string.menu_context_delete);
  }

  /**
   * service context menu
   * @param item
   * @return
   */
  @Override
  public boolean onContextItemSelected(MenuItem item) {
    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
    LogFacade.entry(LOG_TAG, "on context item select:" + item + ":" + info.id + ":" + arrayAdapter.getItem(info.position));
    twoListener.createStateDeleteDialog(R.string.alert_delete_title, R.string.alert_delete_message);
    return super.onContextItemSelected(item);
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    LogFacade.entry(LOG_TAG, "onAttach");
    twoListener = (TwoListener) activity;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LogFacade.entry(LOG_TAG, "onCreate");

    //
    arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.us_states, android.R.layout.simple_list_item_1);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    LogFacade.entry(LOG_TAG, "onCreateView");

    View view = inflater.inflate(R.layout.fragment_two, container, false);
    return(view);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    LogFacade.entry(LOG_TAG, "onActivityCreated");

    setListAdapter(arrayAdapter);

    registerForContextMenu(getListView());
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

    if (returnFromDetailFlag) {
      returnFromDetailFlag = false;
      twoListener.onStateDeselect(TabHelper.TAG_TWO);
    }
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
  private ArrayAdapter<CharSequence> arrayAdapter;

  // transition events between selected item and detail
  private TwoListener twoListener;

  // true, returning from detail fragment
  private boolean returnFromDetailFlag = false;

  //context menu
  public static final int CONTEXT_ITEM_1 = Menu.FIRST;

  //
  public static final String LOG_TAG = TwoFragment.class.getName();
}
/**
 * Created by guycole on 8/6/13.
 */
