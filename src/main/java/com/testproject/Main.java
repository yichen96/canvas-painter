package com.testproject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Painter painter = new Painter();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("enter command: ");
            String cmd = scanner.nextLine();
            try {
                //System.out.println(CommandScanner.validateParam(cmd,2)[1]);
                CommandScanner.executeCommand(cmd, painter);
            } catch (IllegalArgumentException ie) {
                System.out.println(String.format("command not supported, error: %s",ie.getMessage()));
            }
        }
    }
}
