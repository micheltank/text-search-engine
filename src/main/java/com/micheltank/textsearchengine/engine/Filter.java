package com.micheltank.textsearchengine.engine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {

  private final List<String> stopWords;

  public Filter() throws IOException {
    this.stopWords = this.loadStopwords();
  }

  private List<String> loadStopwords() throws IOException {
    try (InputStream inputStream = getClass().getResourceAsStream("/stop-words.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      return reader.lines().collect(Collectors.toList());
    }
  }

  public String[] toLowerCase(String[] text) {
    String[] result = new String[text.length];
    for (int i = 0; i < text.length; i++) {
      result[i] = text[i].toLowerCase();
    }
    return result;
  }

  public String[] removeStopWord(String[] text) {
    ArrayList<String> textCleaned = new ArrayList<>();
    for (int i = 0; i < text.length; i++) {
      if (!this.stopWords.contains(text[i])) {
        textCleaned.add(text[i]);
      }
    }
    String[] result = new String[textCleaned.size()];
    return textCleaned.toArray(result);
  }

  public String[] removeSpecialCharacters(String[] text) {
    String[] result = new String[text.length];
    for (int i = 0; i < text.length; i++) {
      result[i] = text[i].replace(".", "");
      result[i] = result[i].replace(",", "");
      result[i] = result[i].replace(":", "");
      result[i] = result[i].replace("”", "");
      result[i] = result[i].replace("“", "");
      result[i] = result[i].replace("—", "");
      result[i] = result[i].trim();
    }
    return result;
  }
}
