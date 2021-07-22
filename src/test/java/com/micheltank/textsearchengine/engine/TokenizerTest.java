package com.micheltank.textsearchengine.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class TokenizerTest {

  @Test
  void tokenize() throws IOException {
    String input =
        "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";
    String[] expect = {
      "lorem",
      "ipsum",
      "industry's",
      "standard",
      "dummy",
      "text",
      "ever",
      "since",
      "1500s",
      "unknown",
      "printer",
      "took",
      "galley",
      "type",
      "scrambled",
      "make",
      "type",
      "specimen",
      "book"
    };

    var tokenizationResult = new Tokenizer().tokenize(input);

    Assertions.assertArrayEquals(expect, tokenizationResult);
  }
}
