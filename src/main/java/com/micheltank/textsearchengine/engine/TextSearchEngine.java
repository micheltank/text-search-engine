package com.micheltank.textsearchengine.engine;

import com.micheltank.textsearchengine.engine.reader.DocumentReader;
import com.micheltank.textsearchengine.engine.reader.TextSearchIterator;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The engine for text search. Use inverted index data structure that supports very fast full-text
 * searches.
 */
public class TextSearchEngine {

  private HashMap<String, int[]> indexedWords;
  private HashMap<Integer, Document> documents;
  private Tokenizer tokenizer;

  public TextSearchEngine() throws IOException {
    this.tokenizer = new Tokenizer();
  }

  public Map<String, int[]> getIndexedWords() {
    return indexedWords;
  }

  /**
   * Loads and index the content provided
   *
   * @param textSearch
   * @throws IOException
   */
  public void load(TextSearchIterator textSearch) throws IOException {
    this.indexedWords = new HashMap<>();
    this.documents = new HashMap<>();

    int fileId = 1;

    if (!textSearch.hasNext()) {
      throw new IOException("No files in the informed directory");
    }
    while (textSearch.hasNext()) {
      DocumentReader reader = textSearch.getNext();
      this.documents.put(fileId, new Document(fileId, reader.getPath()));
      try (BufferedReader br = new BufferedReader(reader)) {
        String line;
        while ((line = br.readLine()) != null) {
          index(fileId, line);
        }
      }
      fileId++;
    }
  }

  private void index(int fileId, String text) {
    String[] words = this.tokenizer.tokenize(text);
    for (String word : words) {
      var docIdsFromIndexedWord = this.indexedWords.get(word);
      if (docIdsFromIndexedWord != null
          && docIdsFromIndexedWord[docIdsFromIndexedWord.length - 1] == fileId) {
        continue;
      }
      if (docIdsFromIndexedWord == null) {
        this.indexedWords.put(word, new int[] {fileId});
      } else {
        var newDocIds = Arrays.copyOf(docIdsFromIndexedWord, docIdsFromIndexedWord.length + 1);
        newDocIds[docIdsFromIndexedWord.length] = fileId;
        this.indexedWords.put(word, newDocIds);
      }
    }
  }

  /**
   * Search by a term or phrase. Stop words will be ignored
   *
   * @param textToSearch
   * @return
   */
  public List<DocumentSearch> search(String textToSearch) {
    if (this.indexedWords == null) {
      throw new IllegalStateException("There is no data indexed");
    }
    var resultDocIds = new HashMap<Integer, Integer>();
    var tokensForSearch = this.tokenizer.tokenize(textToSearch);
    for (String token : tokensForSearch) {
      var docIdsFromIndexedWord = this.indexedWords.get(token);
      if (docIdsFromIndexedWord == null) {
        continue;
      }
      for (int i : docIdsFromIndexedWord) {
        var rankedDocIds = resultDocIds.get(i);
        if (rankedDocIds == null) {
          resultDocIds.put(i, 1);
        } else {
          resultDocIds.put(i, ++rankedDocIds);
        }
      }
    }
    return resultDocIds.entrySet().stream()
        .map(
            entry ->
                new DocumentSearch(
                    this.documents.get(entry.getKey()), tokensForSearch.length, entry.getValue()))
        .sorted(Comparator.comparingDouble(DocumentSearch::getRank).reversed())
        .limit(10)
        .collect(Collectors.toList());
  }
}
