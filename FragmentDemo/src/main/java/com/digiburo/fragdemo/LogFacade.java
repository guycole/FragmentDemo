package com.digiburo.fragdemo;

import android.util.Log;

/**
 * wrapper for android logger
 */
public class LogFacade {

  /**
   * log entry into a method
   *
   * @param logTag log tag
   * @param name method name
   */
  public static void entry(String logTag, String name) {
    if (Constants.DEBUG_APPLICATION_MODE) {
      String message = "--entry:" + name;
      Log.d(logTag, message);
    }
  }

  /**
   *
   * @param logTag
   * @param name
   */
  public static void exit(String logTag, String name) {
    if (Constants.DEBUG_APPLICATION_MODE) {
      String message = "--exit:" + name;
      Log.d(logTag, message);
    }
  }

  /**
   *
   * @param logTag
   * @param message
   */
  public static void debug(String logTag, String message) {
    if (Constants.DEBUG_APPLICATION_MODE) {
      Log.d(logTag, message);
    }
  }

  /**
   *
   * @param logTag
   * @param message
   */
  public static void info(String logTag, String message) {
    Log.i(logTag, message);
  }

  /**
   *
   * @param logTag
   * @param message
   */
  public static void error(String logTag, String message) {
    Log.e(logTag, "=-= error begin =-=-=-=-=-=-=");
    Log.e(logTag, message);
    Log.e(logTag, "=-= error end =-=-=-=-=-=-=");
  }

  /**
   *
   * @param logTag
   * @param exception
   */
  public static void error(String logTag, Exception exception) {
    String message = "null exception message";

    if (exception != null) {
      message = exception.toString();
    }

    error(logTag, message);
  }
}
/**
 * Created by guycole on 8/6/13.
 */