package com.micheltank.textsearchengine.engine;

/** Represents the document searched returned by the engine */
public class DocumentSearch {

  private Document document;
  private Integer tokensLength;
  private Integer occurrences;

  public DocumentSearch(Document document, int tokensLength, Integer occurrences) {
    this.document = document;
    this.tokensLength = tokensLength;
    this.occurrences = occurrences;
  }

  public float getRank() {
    return ((float) occurrences / tokensLength) * 100;
  }

  @Override
  public String toString() {
    return document.getPath() + ": " + this.getRank() + "%";
  }
}
