package com.micheltank.textsearchengine.engine;

public class Document {

  private Integer fileId;
  private String path;

  public Document(Integer fileId, String path) {
    this.fileId = fileId;
    this.path = path;
  }

  public Integer getFileId() {
    return fileId;
  }

  public String getPath() {
    return path;
  }
}
