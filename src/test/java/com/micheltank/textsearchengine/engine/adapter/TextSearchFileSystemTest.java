package com.micheltank.textsearchengine.engine.adapter;

import com.micheltank.textsearchengine.adapter.filesystem.TextSearchFileSystem;
import com.micheltank.textsearchengine.engine.reader.DocumentNotFoundException;
import com.micheltank.textsearchengine.engine.reader.TextSearchIterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TextSearchFileSystemTest {

  @Test
  void IterationFiles() throws DocumentNotFoundException {
    String path = "src/test/resources";

    File file = new File(path);
    String absolutePath = file.getAbsolutePath();
    TextSearchIterator iterator = new TextSearchFileSystem(absolutePath);

    Assertions.assertEquals(true, iterator.hasNext());
    while (iterator.hasNext()) {
      Assertions.assertNotNull(iterator.getNext());
    }
    Assertions.assertEquals(false, iterator.hasNext());
  }

  @Test
  void HasNextEmptyDirectory() throws DocumentNotFoundException {
    String path = "src/test/resources/empty";

    File file = new File(path);
    String absolutePath = file.getAbsolutePath();
    TextSearchIterator iterator = new TextSearchFileSystem(absolutePath);

    Assertions.assertEquals(false, iterator.hasNext());
  }

  @Test
  void NextEmptyCollection() {
    String path = "src/test/resources/empty";

    File file = new File(path);
    String absolutePath = file.getAbsolutePath();
    TextSearchIterator iterator = new TextSearchFileSystem(absolutePath);

    DocumentNotFoundException thrown =
        assertThrows(DocumentNotFoundException.class, () -> iterator.getNext());

    Assertions.assertTrue(thrown.getMessage().contains("Document not found"));
  }
}
