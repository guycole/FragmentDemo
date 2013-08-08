package com.digiburo.fragdemo;

import android.app.Activity;
import android.app.Fragment;
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

import java.util.ArrayList;

/**
 * Service the "three" tab - simple scrolling list w/no loader
 * Supports short and long press on row
 * Header and footer on list view
 * Custom row adapter
 */
public class ThreeFragment extends ListFragment {

  /**
   * mandatory empty ctor
   */
  public ThreeFragment() {
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
    LogFacade.debug(LOG_TAG, "click:" + position + ":" + id);
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
    LogFacade.entry(LOG_TAG, "on context item select:" + item + ":" + info.id);

    return super.onContextItemSelected(item);
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    LogFacade.debug(LOG_TAG, "onAttach");
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LogFacade.debug(LOG_TAG, "onCreate");

    //
    ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.us_states, android.R.layout.simple_list_item_1);

    ArrayList<String> arrayList = new ArrayList<String>();
    for (int ii = 0; ii < arrayAdapter.getCount(); ii++) {
      arrayList.add((String) arrayAdapter.getItem(ii));
    }

    customArrayAdapter = new CustomArrayAdapter(getActivity(), arrayList);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    LogFacade.debug(LOG_TAG, "onCreateView");

    footerView = inflater.inflate(R.layout.footer_three, null);
    headerView = inflater.inflate(R.layout.header_three, null);

    View view = inflater.inflate(R.layout.fragment_three, container, false);
    return(view);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    LogFacade.debug(LOG_TAG, "onActivityCreated");

    if (needDecorationFlag) {
      needDecorationFlag = false;
      getListView().addFooterView(footerView);
      getListView().addHeaderView(headerView);
    }

    setListAdapter(customArrayAdapter);

    registerForContextMenu(getListView());
  }

  @Override
  public void onStart() {
    super.onStart();
    LogFacade.debug(LOG_TAG, "onStart");
  }

  @Override
  public void onResume() {
    super.onResume();
    LogFacade.debug(LOG_TAG, "onResume");
  }

  @Override
  public void onPause() {
    super.onPause();
    LogFacade.debug(LOG_TAG, "onPause");
  }

  @Override
  public void onStop() {
    super.onStop();
    LogFacade.debug(LOG_TAG, "onStop");
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    LogFacade.debug(LOG_TAG, "onDestroyView");
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    LogFacade.debug(LOG_TAG, "onDestroy");
  }

  @Override
  public void onDetach() {
    super.onDetach();
    LogFacade.debug(LOG_TAG, "onDetach");
  }

  //
  private View footerView;
  private View headerView;

  // determine if header/footer decorations have been added
  private boolean needDecorationFlag = true;

  //
  private CustomArrayAdapter customArrayAdapter;

  //context menu
  public static final int CONTEXT_ITEM_1 = Menu.FIRST;

  //
  public static final String LOG_TAG = ThreeFragment.class.getName();
}
/**
 * Created by guycole on 8/6/13.
 */
