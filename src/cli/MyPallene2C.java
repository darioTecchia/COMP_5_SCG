package cli;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class MyPallene2C {

  private static MyPallene compiler;
  private static String finalFileName;
  private static ProcessBuilder builder = new ProcessBuilder();

  private static boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");

  private static String workingDir = System.getProperty("user.dir");

  public static void main(String[] args) throws Exception {

    StringBuilder startMessage = new StringBuilder();
    startMessage.append("Choose a file:\n");
    startMessage.append("0.\tsummer.mp\n");
    startMessage.append("\t\tSum between two numbers.\n");

    startMessage.append("1.\tproduct.mp\n");
    startMessage.append("\t\tProduct between two numbers using the sum.\n");

    startMessage.append("2.\tdivision.mp\n");
    startMessage.append("\t\tInteger division between two positive numbers.\n");

    startMessage.append("3.\tpower.mp\n");
    startMessage.append("\t\tPower elevation of a number.\n");

    startMessage.append("4.\tfibonacci.mp\n");
    startMessage.append("\t\tFibonacci sequence.\n");

    if (args.length == 0) {
      System.out.println(startMessage);
      String filePath = manageChoice(new Scanner(System.in).nextInt());
      // String filePath = manageChoice(0);
      compiler = new MyPallene(filePath, true);
      compiler.compile();
      finalFileName = filePath.replace(".mp", ".c");
    } else {
      compiler = new MyPallene(args[0], true);
      compiler.compile();
      finalFileName = args[0].replace(".mp", ".c");
    }

    if (isWindows) {
      //      builder.command("cmd.exe", "/c", "wsl", "clang-format", "-style=google", finalFileName.replace("\\", "/"), "-i");
      builder.command("cmd.exe", "/c", "wsl", "./launch.sh", finalFileName.replace("\\", "/"));
    } else {
//      builder.command("sh", "-c", "clang-format", "-style=google", finalFileName.replace("\\", "/"), "-i");
      builder.command("sh", "-c", "./launch.sh", finalFileName.replace("\\", "/"));
    }

    builder.directory(new File(workingDir));
    Process process = builder.start();
    StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
    Executors.newSingleThreadExecutor().submit(streamGobbler);
    int exitCode = process.waitFor();
    assert exitCode == 0;
    System.exit(0);

  }

  private static String manageChoice(int choice) {
    String[] choices = {"summer.mp", "product.mp", "division.mp", "power.mp", "fibonacci.mp"};
    return "test_files\\"  + choices[choice];
  }

}
