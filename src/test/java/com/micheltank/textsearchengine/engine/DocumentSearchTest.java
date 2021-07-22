package com.micheltank.textsearchengine.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

class DocumentSearchTest {

  @ParameterizedTest
  @CsvSource({
    "0, 0", "10, 100", "5, 50",
  })
  void Rank(int occurrences, int expected) {
    var document = new Document(1, "/file1.txt");
    var documentSearch = new DocumentSearch(document, 10, occurrences);

    Assertions.assertEquals(expected, documentSearch.getRank());
  }
}
