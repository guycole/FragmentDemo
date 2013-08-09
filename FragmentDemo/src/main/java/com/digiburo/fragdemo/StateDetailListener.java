package com.digiburo.fragdemo;

/**
 * navigate from list to detail
 */
public interface StateDetailListener {

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
}
/**
 * Created by guycole on 8/8/13.
 */