package com.testproject;

public class CommandScanner {

    private CommandScanner() {
        throw new IllegalStateException("utility class");
    }

    public static void executeCommand(String userInput, Painter painter) {
        char command = validateCommand(userInput);
        int[] param;
        try {
            switch (command) {
                case 'C':
                    param = validateParam(userInput, 2);
                    painter.prepareCanvas(param[0], param[1]);
                    painter.printCanvasToConsole();
                    break;
                case 'L':
                    param = validateParam(userInput, 4);
                    painter.drawLineOnCanvas(param[0], param[1], param[2], param[3]);
                    painter.printCanvasToConsole();
                    break;
                case 'R':
                    param = validateParam(userInput, 4);
                    painter.drawRectangleOnCanvas(param[0], param[1], param[2], param[3]);
                    painter.printCanvasToConsole();
                    break;
                case 'Q':
                    System.exit(0); //Quit the program silently as instructed
                    break;
                default:
                    throw new IllegalArgumentException("command not implemented");
                    //in case more commands are required in future but is forgotten to be implemented here
            }
        } catch (ArrayIndexOutOfBoundsException ae) {
            System.out.println("required pixel is not found on the current canvas");
        } catch (NullPointerException ne) {
            System.out.println("Canvas does not exist, use \'C (int)width (int)height\' to create a canvas first");
        }
    }

    /**
     * Validate user input, only accepts capital letter as the first character in the command.
     *
     * @param userInput
     * @return command character
     */
    private static char validateCommand(String userInput) {
        char command = userInput.charAt(0);
        if (Character.isLetter(command) && Character.isUpperCase(command)) {
            return command;
        } else {
            throw new IllegalArgumentException("Commands must start with a capital letter");
        }
    }

    /**
     * Validate user input, only accepts expected number of parameters
     * that can be parsed to positive integer.
     * If get negative integer input, turn it into its absolute value
     *
     * @param userInput
     * @param expectedParamNumber
     * @return an array of positive integer parameters
     */
    private static int[] validateParam(String userInput, int expectedParamNumber) {
        String[] param = userInput.split(" ");
        int[] transformedParam = new int[expectedParamNumber];
        try {
            for (int i = 1; i < param.length; i++) {
                transformedParam[i - 1] = Math.abs(Integer.parseInt(param[i]));
            }
            return transformedParam;
        } catch (ArrayIndexOutOfBoundsException ae) {
            System.out.println("user input parameters are more than expected, exceeded parameters are ignored");
            return transformedParam;
        } catch (NumberFormatException ne) {
            System.out.println("user input parameter cannot be parsed to integer");
            throw new IllegalArgumentException("cannot parse input to integer");
        }
    }


}
