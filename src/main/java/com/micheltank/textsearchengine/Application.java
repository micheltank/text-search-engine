package com.micheltank.textsearchengine;

import com.micheltank.textsearchengine.adapter.filesystem.TextSearchFileSystem;
import com.micheltank.textsearchengine.engine.DocumentSearch;
import com.micheltank.textsearchengine.engine.TextSearchEngine;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Application {
  public static void main(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException("No directory given to index");
    }
    final String directory = args[0];
    try {
      TextSearchEngine textSearchEngine = new TextSearchEngine();
      textSearchEngine.load(new TextSearchFileSystem(directory));

      try (Scanner keyboard = new Scanner(System.in)) {
        while (true) {
          System.out.println("search> ");
          final String command = keyboard.nextLine();
          if (command.equals(":quit")) {
            break;
          }
          List<DocumentSearch> result = textSearchEngine.search(command);

          if (result.isEmpty()) {
            System.err.println("no matches found");
          } else {
            result.forEach(System.out::println);
          }
        }
      }
    } catch (IOException e) {
      System.err.println("Failure to run the application: " + e.getMessage());
    }
  }
}
