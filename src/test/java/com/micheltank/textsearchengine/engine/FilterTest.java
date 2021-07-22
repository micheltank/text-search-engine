package com.micheltank.textsearchengine.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FilterTest {

  @Test
  void toLowerCase() throws IOException {
    String[] input = {"Lorem", "Ipsum", "IS", "simplY"};
    String[] expect = {"lorem", "ipsum", "is", "simply"};

    var result = new Filter().toLowerCase(input);

    Assertions.assertArrayEquals(expect, result);
  }

  @Test
  void removeStopWord() throws IOException {
    String[] input = {
      "it",
      "has",
      "survived",
      "not",
      "only",
      "five",
      "centuries",
      "but",
      "also",
      "the",
      "leap",
      "into",
      "electronic",
      "typesetting",
      "remaining",
      "essentially",
      "unchanged"
    };
    String[] expect = {
      "survived",
      "five",
      "centuries",
      "also",
      "leap",
      "electronic",
      "typesetting",
      "remaining",
      "essentially",
      "unchanged"
    };

    var result = new Filter().removeStopWord(input);

    Assertions.assertArrayEquals(expect, result);
  }

  @Test
  void removeSpecialCharacters() throws IOException {
    String[] input = {"lorem", "ipsum, ", "is:", "simply."};
    String[] expect = {"lorem", "ipsum", "is", "simply"};

    var result = new Filter().removeSpecialCharacters(input);

    Assertions.assertArrayEquals(expect, result);
  }
}
