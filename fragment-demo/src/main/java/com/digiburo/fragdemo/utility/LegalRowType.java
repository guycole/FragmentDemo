package com.digiburo.fragdemo.utility;

/**
 * Created by gsc on 8/12/13.
 */
public enum LegalRowType {
  UNKNOWN("Unknown"),
  NAME_AND_RADIO("NameAndRadio"),
  NAME_ONLY("NameOnly");

  LegalRowType(String name) {
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
  public static LegalRowType discoverMatchingEnum(String arg) {
    LegalRowType result = UNKNOWN;

    if (arg == null) {
      return(result);
    }

    for (LegalRowType token:LegalRowType.values()) {
      if (token.getName().equals(arg)) {
        result = token;
      }
    }

    return(result);
  }
}
