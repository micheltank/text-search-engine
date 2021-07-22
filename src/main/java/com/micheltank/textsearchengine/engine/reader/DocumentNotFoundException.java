package com.micheltank.textsearchengine.engine.reader;

import java.io.IOException;

public class DocumentNotFoundException extends IOException {

  public DocumentNotFoundException() {
    super("Document not found");
  }

  public DocumentNotFoundException(String s) {
    super(s);
  }
}
