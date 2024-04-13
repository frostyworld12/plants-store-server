package com.example.plantsstore.utility;

public class Utility {
  public static Boolean isStringEmpty(String stringToCheck) {
    return stringToCheck == null || stringToCheck.isBlank() || stringToCheck.isEmpty();
  }
}
