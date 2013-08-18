package com.digiburo.fragdemo.utility;

/**
 * Created by gsc on 8/12/13.
 */
public enum LegalOptionMenuType {
  UNKNOWN("Unknown"),
  ABOUT("About"),
  SETTINGS("Settings");

  LegalOptionMenuType(String name) {
    typeName = name;
  }

  private final String typeName;

  public String getName() {
    return(typeName);
  }

  /**
   *
   * @param arg
   * @return
   */
  public static LegalOptionMenuType discoverMatchingEnum(String arg) {
    LegalOptionMenuType result = UNKNOWN;

    if (arg == null) {
      return(result);
    }

    for (LegalOptionMenuType token: LegalOptionMenuType.values()) {
      if (token.getName().equals(arg)) {
        result = token;
      }
    }

    return(result);
  }
}
