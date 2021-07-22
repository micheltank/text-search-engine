package com.micheltank.textsearchengine.adapter.filesystem;

import com.micheltank.textsearchengine.engine.reader.DocumentNotFoundException;
import com.micheltank.textsearchengine.engine.reader.DocumentReader;
import com.micheltank.textsearchengine.engine.reader.TextSearchIterator;
import com.micheltank.textsearchengine.utils.FileUtils;

import java.io.*;

/** Implements the file system loader as a source type for the {@link com.micheltank.textsearchengine.engine.TextSearchEngine */
public class TextSearchFileSystem implements TextSearchIterator {
  private String directory;
  private int currentPosition = 0;
  private File[] files = new File[] {};

  public TextSearchFileSystem(String directory) {
    this.directory = directory;
  }

  private void lazyLoad() {
    if (this.files.length == 0) {
      var foundFiles = new File(this.directory).listFiles();
      if (foundFiles == null) {
        return;
      }
      this.files = new File[foundFiles.length];
      for (int i = 0; i < foundFiles.length; i++) {
        if (FileUtils.getExtension(foundFiles[i].getName()).equals("txt")) {
          this.files[i] = foundFiles[i];
        }
      }
    }
  }

  @Override
  public boolean hasNext() {
    lazyLoad();
    return currentPosition < this.files.length;
  }

  @Override
  public DocumentReader getNext() throws DocumentNotFoundException {
    if (!hasNext()) {
      throw new DocumentNotFoundException();
    }

    var currentFile = this.files[currentPosition];
    currentPosition++;
    try {
      return new DocumentFileReader(currentFile);
    } catch (FileNotFoundException e) {
      throw new DocumentNotFoundException(e.getMessage());
    }
  }

  @Override
  public void reset() {
    currentPosition = 0;
  }
}
