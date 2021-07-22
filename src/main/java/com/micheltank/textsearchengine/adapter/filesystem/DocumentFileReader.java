package com.micheltank.textsearchengine.adapter.filesystem;

import com.micheltank.textsearchengine.engine.reader.DocumentReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** Implements the file system reader */
public class DocumentFileReader extends DocumentReader {

  private final File file;
  private final FileReader fileReader;

  public DocumentFileReader(File file) throws FileNotFoundException {
    this.fileReader = new FileReader(file);
    this.file = file;
  }

  @Override
  public String getPath() {
    return this.file.getName();
  }

  @Override
  public int read(char[] cbuf, int off, int len) throws IOException {
    return this.fileReader.read(cbuf, off, len);
  }

  @Override
  public void close() throws IOException {
    this.fileReader.close();
  }
}
