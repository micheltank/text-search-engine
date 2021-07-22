package com.micheltank.textsearchengine.engine.reader;

/** Interface used by {@link com.micheltank.textsearchengine.engine.TextSearchEngine, allowing multiple source to be implemented */
public interface TextSearchIterator {
  boolean hasNext();

  DocumentReader getNext() throws DocumentNotFoundException;

  void reset();
}
