package encryptdecrypt;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException {
    runner(args);
  }

  private static void runner(String[] args) throws IOException {
    RunParameters runParameters = parseParameters(args);

    String message = prepareMessage(runParameters).toString();

    if (runParameters.getMode().equals("enc")) {
      runParameters.setData(encode(message, runParameters.getKey()));
    } else {
      runParameters.setData(decode(message, runParameters.getKey()));
    }

    writeMessage(runParameters);
  }

  private static void writeMessage(RunParameters runParameters) throws IOException {
    if (runParameters.getOut().isBlank()) {
      System.out.println(runParameters.getData());
    } else {
      Path path = Paths.get(runParameters.getOut());
      File file = path.toFile();
      file.createNewFile();
      try (FileWriter printWriter = new FileWriter(file)) {
        printWriter.write(runParameters.getData());
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }
    }
  }

  private static StringBuilder prepareMessage(RunParameters runParameters) {
    StringBuilder message = new StringBuilder();

    if (!runParameters.getIn().isBlank()) {
      Path path = Paths.get(runParameters.getIn());
      File file = path.toFile();
      try (Scanner scanner = new Scanner(file)) {
        while (scanner.hasNext()) {
          message.append(scanner.nextLine());
        }
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }
    }

    if (!runParameters.getData().isBlank()) {
      message = new StringBuilder(runParameters.getData());
    }
    return message;
  }

  private static String encode(String message, int offset) {
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < message.length(); i++) {
      int numValue = message.charAt(i) + offset;
      stringBuilder.append((char) numValue);
    }

    return stringBuilder.toString();
  }

  private static String decode(String message, int offset) {
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < message.length(); i++) {
      int numValue = message.charAt(i) - offset;
      stringBuilder.append((char) (numValue));
    }

    return stringBuilder.toString();
  }

  private static RunParameters parseParameters(String[] args) {
    Map<String, String> arguments = new HashMap<>();
    for (int i = 0; i < args.length - 1; i += 2) {
      arguments.put(args[i], args[i + 1]);
    }
    RunParameters runParameters = new RunParameters();

    if (arguments.containsKey("-mode")) {
      runParameters.setMode(arguments.get("-mode"));
    }
    if (arguments.containsKey("-key")) {
      runParameters.setKey(Integer.parseInt(arguments.get("-key")));
    }
    if (arguments.containsKey("-data")) {
      runParameters.setData(arguments.get("-data"));
    }
    if (arguments.containsKey("-in")) {
      runParameters.setIn(arguments.get("-in"));
    }
    if (arguments.containsKey("-out")) {
      runParameters.setOut(arguments.get("-out"));
    }

    return runParameters;
  }
}
