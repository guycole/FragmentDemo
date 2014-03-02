package com.digiburo.fragdemo.app;

/**
 * bridge between MainActivity2 and TwoFragment
 */
public interface TwoListener {

  /**
   * Display state detail fragment
   * @param stateName state name
   * @param tabTag parent tab
   */
  void onStateSelect(String stateName, String tabTag);

  /**
   *
   * @param tabTag
   */
  void onStateDeselect(String tabTag);

  /**
   * display delete dialog
   * @param title
   * @param message
   */
  void createStateDeleteDialog(int title, int message);

  /**
   * perform delete
   */
  void onStateDeleteYes();

  /**
   * cancel delete
   */
  void onStateDeleteNo();
}
/**
 * Created by guycole on 8/8/13.
 */