package com.digiburo.fragdemo.ui;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.digiburo.fragdemo.content.ContentFacade;
import com.digiburo.fragdemo.content.DummyModel;
import com.digiburo.fragdemo.utility.LogFacade;
import com.digiburo.fragdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Service the "three" tab - scrolling list w/no loader
 * Supports short and long press on row
 * Header and footer on list view
 * Custom row adapter w/radio buttons
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
    LogFacade.entry(LOG_TAG, "click:" + position + ":" + id);
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
    LogFacade.entry(LOG_TAG, "on context item select:" + item + ":" + info.id + ":" + customArrayAdapter.getItem(info.position));

    return super.onContextItemSelected(item);
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    LogFacade.entry(LOG_TAG, "onAttach");
    stateDetailListener = (StateDetailListener) activity;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LogFacade.entry(LOG_TAG, "onCreate");

    // read from content provider
    ContentFacade contentFacade = new ContentFacade();
    List<DummyModel> list = contentFacade.getDummyList(getActivity());

    customArrayAdapter = new CustomArrayAdapter(getActivity(), list);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    LogFacade.entry(LOG_TAG, "onCreateView");

    footerView = inflater.inflate(R.layout.footer_three, null);
    headerView = inflater.inflate(R.layout.header_three, null);

    View view = inflater.inflate(R.layout.fragment_three, container, false);
    return(view);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    LogFacade.entry(LOG_TAG, "onActivityCreated");

    getListView().addFooterView(footerView);
    getListView().addHeaderView(headerView);

    setListAdapter(customArrayAdapter);

    registerForContextMenu(getListView());
  }

  @Override
  public void onStart() {
    super.onStart();
    LogFacade.entry(LOG_TAG, "onStart");

    footerComment = (EditText) getActivity().findViewById(R.id.editFooter01);

    ImageButton imageButton = (ImageButton) getActivity().findViewById(R.id.buttonSkull01);
    imageButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
      LogFacade.entry(LOG_TAG, "onClick:imageButton");
      }
    });

    Button saveButton = (Button) getActivity().findViewById(R.id.buttonSave01);
    saveButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
      LogFacade.entry(LOG_TAG, "onClick:saveButton:" + footerComment.getText());
      }
    });
  }

  @Override
  public void onResume() {
    super.onResume();
    LogFacade.entry(LOG_TAG, "onResume");

    if (returnFromDetailFlag) {
      returnFromDetailFlag = false;
      stateDetailListener.onStateDeselect(TabHelper.TAG_THREE);
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
  private View footerView;
  private View headerView;

  //
  private EditText footerComment;

  //
  private CustomArrayAdapter customArrayAdapter;

  // handle transition events between selected item and detail
  private StateDetailListener stateDetailListener;

  // true, returning from detail fragment
  private boolean returnFromDetailFlag = false;

  //context menu
  public static final int CONTEXT_ITEM_1 = Menu.FIRST;

  //
  public static final String LOG_TAG = ThreeFragment.class.getName();
}
/**
 * Created by guycole on 8/6/13.
 */
