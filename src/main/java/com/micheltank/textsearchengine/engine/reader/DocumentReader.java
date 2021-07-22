package com.micheltank.textsearchengine.engine.reader;

import java.io.Reader;

/**
 * A abstract document reader for {@link com.micheltank.textsearchengine.engine.TextSearchEngine}
 */
public abstract class DocumentReader extends Reader {

  public abstract String getPath();
}
