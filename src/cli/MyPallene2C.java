package cli;

import java.util.Scanner;

public class MyPallene2C {

  private static MyPallene compiler;

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
//      String filePath = manageChoice(new Scanner(System.in).nextInt());
      String filePath = manageChoice(4);
      compiler = new MyPallene(filePath, true);
      compiler.compile();
    }

  }

  private static String manageChoice(int choice) {
    String[] choices = {"summer.mp", "product.mp", "division.mp", "power.mp", "fibonacci.mp"};
    return "test_files\\"  + choices[choice];
  }

}
