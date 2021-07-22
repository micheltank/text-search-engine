package com.micheltank.textsearchengine.engine;

import java.io.IOException;

public class Tokenizer {

  private final Filter filter;

  public Tokenizer() throws IOException {
    this.filter = new Filter();
  }

  public String[] tokenize(String text) {
    var words = text.split(" ");
    words = this.filter.removeSpecialCharacters(words);
    words = this.filter.toLowerCase(words);
    words = this.filter.removeStopWord(words);

    return words;
  }
}
