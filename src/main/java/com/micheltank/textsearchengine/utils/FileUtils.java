package com.micheltank.textsearchengine.utils;

public class FileUtils {
  private FileUtils() {}

  /**
   * Gets file extension by provided filename
   *
   * @param filename
   * @return
   */
  public static String getExtension(String filename) {
    int index = filename.lastIndexOf('.');
    if (index > 0) {
      return filename.substring(index + 1);
    }
    return "";
  }
}
