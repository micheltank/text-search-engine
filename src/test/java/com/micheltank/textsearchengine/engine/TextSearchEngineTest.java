package com.micheltank.textsearchengine.engine;

import com.micheltank.textsearchengine.adapter.filesystem.TextSearchFileSystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class TextSearchEngineTest {

  @Test
  void loadDirectory() throws IOException {
    String path = "src/test/resources";

    File file = new File(path);
    String absolutePath = file.getAbsolutePath();

    var engine = new TextSearchEngine();
    engine.load(new TextSearchFileSystem(absolutePath));
    Assertions.assertEquals(647, engine.getIndexedWords().size());
  }

  @Test
  void searchByPhrases() throws IOException {
    String path = "src/test/resources";

    File file = new File(path);
    String absolutePath = file.getAbsolutePath();

    var engine = new TextSearchEngine();
    engine.load(new TextSearchFileSystem(absolutePath));

    var result = engine.search("vaccines and coronavirus");
    Assertions.assertEquals(2, result.size());

    result = engine.search("lorem ipsum");
    Assertions.assertEquals(0, result.size());
  }
}
