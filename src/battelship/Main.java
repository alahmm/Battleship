package battelship;

import javax.print.DocFlavor;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class Main {

    public static char[][] Provider() {
        char[][] matrixOfPositions = new char[10][10];
        for (char[] matrixOfPosition : matrixOfPositions) {
            Arrays.fill(matrixOfPosition, '~');
        }
        return matrixOfPositions;
    }
    public static char[][] ProviderMissOrHit(int indexIOfStart, int indexJOfStart, char variable, char[][] matrixOfPositions) {
        for (int i = 0; i < matrixOfPositions.length; i++) {
            for (int j = 0; j < matrixOfPositions[i].length; j++) {

                if (i == indexIOfStart && j == indexJOfStart) {
                    matrixOfPositions[i][j] = variable;
                } else if (matrixOfPositions[i][j] == 'X') {
                        matrixOfPositions[i][j] = 'X';
                } else if (matrixOfPositions[i][j] == 'M') {
                        matrixOfPositions[i][j] = 'M';
                } else {
                    matrixOfPositions[i][j] = '~';
                }
            }
        }
        return matrixOfPositions;
    }

    public static void PositionProvider(char[][] matrixOfPositions) {

        String line = "  1 2 3 4 5 6 7 8 9 10";
        System.out.println(line);
        for (int i = 0; i < matrixOfPositions.length; i++) {
            char variable = (char) (i + 65);
            System.out.print(variable + " ");
            for (int j = 0; j < matrixOfPositions[i].length; j++) {
                System.out.print(matrixOfPositions[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static char[][] PositionReturner(String start, String end, char[][] matrixOfPositions) {

        int indexIOfStart = (int) start.charAt(0) - 65;
        int indexJOfStart = Integer.parseInt(start.substring(1)) - 1;
        int indexIOfEnd = (int) end.charAt(0) - 65;
        int indexJOfEnd = Integer.parseInt(end.substring(1)) - 1;
        if (indexIOfStart > indexIOfEnd) {
            int save = indexIOfStart;
            indexIOfStart = indexIOfEnd;
            indexIOfEnd = save;
        }
        if (indexJOfStart > indexJOfEnd) {
            int save = indexJOfStart;
            indexJOfStart = indexJOfEnd;
            indexJOfEnd = save;
        }
        for (int i = 0; i < matrixOfPositions.length; i++) {
            for (int j = 0; j < matrixOfPositions[i].length; j++) {
                if (i >= indexIOfStart && i <= indexIOfEnd && j >= indexJOfStart && j <= indexJOfEnd) {
                    matrixOfPositions[i][j] = 'O';
                } else if (matrixOfPositions[i][j] == 'O') {
                    matrixOfPositions[i][j] = 'O';
                } else if (matrixOfPositions[i][j] == 'X') {
                    matrixOfPositions[i][j] = 'X';
                } else if (matrixOfPositions[i][j] == 'M') {
                    matrixOfPositions[i][j] = 'M';
                } else {
                    matrixOfPositions[i][j] = '~';
                }
            }
        }
        return matrixOfPositions;
    }
    public static char[][] Shoter(char[][] matrixOfPositions, String start) {

        int indexIOfStart = (int) start.charAt(0) - 65;
        int indexJOfStart = Integer.parseInt(start.substring(1)) - 1;
        if (matrixOfPositions[indexIOfStart][indexJOfStart] == 'O') {
            matrixOfPositions[indexIOfStart][indexJOfStart] = 'X';
        } else if (matrixOfPositions[indexIOfStart][indexJOfStart] == 'X') {
            matrixOfPositions[indexIOfStart][indexJOfStart] = 'X';
        }else {
            matrixOfPositions[indexIOfStart][indexJOfStart] = 'M';
        }
        return matrixOfPositions;
    }
    public static boolean Checker(char[][] matrixOfPositions, ArrayList<Ship> listOfCoordinates, int counterSank) {
        boolean isSank = false;
        int counter = 0;
        int counterTrue = 0;
        for (Ship variable : listOfCoordinates
             ) {
            for (int i = variable.getStartOfIndexI(); i <= variable.getEndOfIndexI(); i++) {
                for (int j = variable.getStartOfIndexJ(); j <= variable.getEndOfIndexJ() ; j++) {
                    if (matrixOfPositions[i][j] == 'X') {
                        counter++;
                    }
                }
            }
            if (counter == variable.getLength() + 1) {
                counterTrue ++;
            }
            counter = 0;
        }
        if (counterTrue == counterSank + 1) {
            isSank = true;
        }

        return isSank;
    }
    public static int decider(int counter) {
        if (counter == 0) {
            return 4;
        } else if (counter == 1) {
            return 3;
        } else if (counter == 2 || counter == 3) {
            return 2;
        } else {
            return 1;
        }
    }

    public static String deciderName(int counter) {
        if (counter == 0) {
            return "the Aircraft Carrier";
        } else if (counter == 1) {
            return "the Battleship";
        } else if (counter == 2) {
            return "the Submarine";
        } else if (counter == 3) {
            return "the Cruiser";
        } else {
            return "the Destroyer";
        }
    }

    public static void printer(int counter) {
        System.out.println();
        System.out.printf("Enter the coordinates of %s (%d cells):%n", deciderName(counter), decider(counter) + 1);
        System.out.println();
    }

    public static boolean detector (int indexIOfStart, int indexJOfStart, int indexIOfEnd, int indexJOfEnd, char[][] matrix) {
        boolean isSpace = true;
        if (indexIOfStart > indexIOfEnd) {
            int save = indexIOfStart;
            indexIOfStart = indexIOfEnd;
            indexIOfEnd = save;
        }
        if (indexJOfStart > indexJOfEnd) {
            int save = indexJOfStart;
            indexJOfStart = indexJOfEnd;
            indexJOfEnd = save;
        }
        /**
         * horizontal
         */
        if (indexIOfStart == indexIOfEnd) {
            if (indexIOfStart == 0) {
                if (indexJOfStart == 0) {
                    for (int i = indexJOfStart; i <= indexJOfEnd; i++) {
                        if (matrix[indexIOfStart + 1][i] == 'O' || matrix[indexIOfStart][indexJOfEnd + 1] == 'O') {
                            isSpace = false;
                        }
                    }
                } else {
                    if (indexJOfEnd == 9) {
                        for (int i = indexJOfStart; i <= indexJOfEnd; i++) {
                            if (matrix[indexIOfStart + 1][i] == 'O' || matrix[indexIOfStart][indexJOfStart - 1] == 'O') {
                                isSpace = false;
                            }
                        }
                    } else {
                        for (int i = indexJOfStart; i <= indexJOfEnd; i++) {
                            if (matrix[indexIOfStart + 1][i] == 'O' || matrix[indexIOfStart][indexJOfStart - 1] == 'O' ||
                                    matrix[indexIOfStart][indexJOfEnd + 1] == 'O') {
                                isSpace = false;
                            }
                        }
                    }
                }
            }else if (indexIOfEnd == 9) {
                if (indexJOfStart == 0) {
                    for (int i = indexJOfStart; i <= indexJOfEnd; i++) {
                        if (matrix[indexIOfStart - 1][i] == 'O' || matrix[indexIOfStart][indexJOfEnd + 1] == 'O') {
                            isSpace = false;
                        }
                    }
                } else {
                    if (indexJOfEnd == 9) {
                        for (int i = indexJOfStart; i <= indexJOfEnd; i++) {
                            if (matrix[indexIOfStart - 1][i] == 'O' || matrix[indexIOfStart][indexJOfStart - 1] == 'O') {
                                isSpace = false;
                            }
                        }
                    } else {
                        for (int i = indexJOfStart; i <= indexJOfEnd; i++) {
                            if (matrix[indexIOfStart - 1][i] == 'O' || matrix[indexIOfStart][indexJOfStart - 1] == 'O' ||
                                    matrix[indexIOfStart][indexJOfEnd + 1] == '0') {
                                isSpace = false;
                            }
                        }
                    }
                }
            } else {
                if (indexJOfStart == 0) {
                    for (int i = indexJOfStart; i <= indexJOfEnd; i++) {
                        if (matrix[indexIOfStart - 1][i] == 'O' || matrix[indexIOfStart + 1][i] == 'O' ||
                                matrix[indexIOfStart][indexJOfEnd + 1] == 'O') {
                            isSpace = false;
                        }
                    }
                } else {
                    if (indexJOfEnd == 9) {
                        for (int i = indexJOfStart; i <= indexJOfEnd; i++) {
                            if (matrix[indexIOfStart - 1][i] == 'O' || matrix[indexIOfStart + 1][i] == 'O' ||
                                    matrix[indexIOfStart][indexJOfStart - 1] == 'O') {
                                isSpace = false;
                            }
                        }
                    } else {
                        for (int i = indexJOfStart; i <= indexJOfEnd; i++) {
                            if (matrix[indexIOfStart - 1][i] == 'O' && matrix[indexIOfStart + 1][i] == 'O' ||
                                    matrix[indexIOfStart][indexJOfStart - 1] == 'O' ||
                                    matrix[indexIOfStart][indexJOfEnd + 1] == 'O') {
                                isSpace = false;
                            }
                        }
                    }
                }
            }
        } else {
            /**
             * vertical
             */
            if (indexIOfStart == 0) {
                if (indexJOfStart == 0) {
                    for (int i = indexIOfStart; i <= indexIOfEnd; i++) {
                        if (matrix[i][indexJOfStart + 1] == 'O' || matrix[indexIOfEnd + 1][indexJOfStart] == 'O') {
                            isSpace = false;
                        }
                    }
                } else {
                    if (indexJOfEnd == 9) {
                        for (int i = indexIOfStart; i <= indexIOfEnd; i++) {
                            if (matrix[i][indexJOfStart - 1] == 'O' || matrix[indexIOfEnd + 1][indexJOfStart] == 'O') {
                                isSpace = false;
                            }
                        }
                    } else {
                        for (int i = indexIOfStart; i <= indexIOfEnd; i++) {
                            if (matrix[i][indexJOfStart - 1] == 'O' || matrix[indexIOfEnd + 1][indexJOfStart] == 'O' ||
                                    matrix[i][indexJOfStart + 1] == 'O') {
                                isSpace = false;
                            }
                        }
                    }
                }
            }else if (indexIOfEnd == 9) {
                if (indexJOfStart == 0) {
                    for (int i = indexIOfStart; i <= indexIOfEnd; i++) {
                        if (matrix[i][indexJOfStart + 1] == 'O' || matrix[indexIOfStart - 1][indexJOfStart] == 'O') {
                            isSpace = false;
                        }
                    }
                } else {
                    if (indexJOfEnd == 9) {
                        for (int i = indexIOfStart; i <= indexIOfEnd; i++) {
                            if (matrix[i][indexJOfStart - 1] == 'O' || matrix[indexIOfStart - 1][indexJOfStart] == 'O') {
                                isSpace = false;
                            }
                        }
                    } else {
                        for (int i = indexIOfStart; i <= indexIOfEnd; i++) {
                            if (matrix[i][indexJOfStart - 1] == 'O' || matrix[indexIOfStart - 1][indexJOfStart] == 'O' ||
                                    matrix[i][indexJOfStart + 1] == 'O') {
                                isSpace = false;
                            }
                        }
                    }
                }
            } else {
                if (indexJOfStart == 0) {
                    for (int i = indexIOfStart; i <= indexIOfEnd; i++) {
                        if (matrix[i][indexJOfStart + 1] == 'O' || matrix[indexIOfEnd + 1][indexJOfStart] == 'O'
                                || matrix[indexIOfStart - 1][indexJOfStart] == 'O') {
                            isSpace = false;
                        }
                    }
                } else {
                    if (indexJOfEnd == 9) {
                        for (int i = indexIOfStart; i <= indexIOfEnd; i++) {
                            if (matrix[i][indexJOfStart - 1] == 'O' || matrix[indexIOfEnd + 1][indexJOfStart] == 'O'
                                    || matrix[indexIOfStart - 1][indexJOfStart] == 'O') {
                                isSpace = false;
                            }
                        }
                    } else {
                        for (int i = indexIOfStart; i <= indexIOfEnd; i++) {
                            if (matrix[i][indexJOfStart + 1] == 'O' || matrix[indexIOfEnd + 1][indexJOfStart] == 'O' ||
                                    matrix[indexIOfStart - 1][indexJOfStart] == 'O' || matrix[i][indexJOfStart - 1] == 'O') {
                                isSpace = false;
                            }
                        }
                    }
                }
            }
        }
        return isSpace;
    }

    public static boolean tester (String start, String end, char[][] matrixOfPositions, int counter) {
        boolean isTester = false;
        String regexChar = "[A-J][1-9]|[A-J]10";
        int indexIOfStart = (int) start.charAt(0) - 65;
        int indexJOfStart = Integer.parseInt(start.substring(1)) - 1;
        int indexIOfEnd = (int) end.charAt(0) - 65;
        int indexJOfEnd = Integer.parseInt(end.substring(1)) - 1;
        if (indexIOfStart > indexIOfEnd) {
            int save = indexIOfStart;
            indexIOfStart = indexIOfEnd;
            indexIOfEnd = save;
        }
        if (indexJOfStart > indexJOfEnd) {
            int save = indexJOfStart;
            indexJOfStart = indexJOfEnd;
            indexJOfEnd = save;
        }
        if (start.matches(regexChar) && end.matches(regexChar)) {

            if (indexIOfStart == indexIOfEnd || indexJOfStart == indexJOfEnd) {

                if ((Math.abs(indexIOfStart - indexIOfEnd) == 0 && Math.abs(indexJOfStart - indexJOfEnd) == decider(counter)) ||
                        (Math.abs(indexIOfStart - indexIOfEnd) == decider(counter))) {
                    if (detector(indexIOfStart, indexJOfStart, indexIOfEnd, indexJOfEnd, matrixOfPositions)) {
                        isTester = true;
                    } else {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    System.out.println();
            }} else {

                System.out.printf("Error! Wrong length of %s! Try again:%n", deciderName(counter));
                System.out.println();

            }
        } else {
            System.out.println("Error! Wrong ship location! Try again:");
            System.out.println();
        }

    } else {
        System.out.println("Error! You entered the wrong coordinates! Try again:");
        System.out.println();
    }
        return isTester;
    }
    public static boolean lastTester (char[][] matrixOfPositions, char[][] matrixOfPositions2, int counter2, String regexChar, int num, ArrayList<Ship> listOfCoordinates, Scanner scanner) {
        boolean isFinished = false;
        String readString2 = scanner.nextLine();
        if (scanner.hasNextLine()) {
            readString2 = scanner.nextLine();
            String start2 = scanner.next();
            System.out.println();
            if (start2.matches(regexChar)) {
                int indexIOfStart2= (int) start2.charAt(0) - 65;
                int indexJOfStart2 = Integer.parseInt(start2.substring(1)) - 1;
                Shoter(matrixOfPositions2, start2);
                System.out.println();
                if (matrixOfPositions2[indexIOfStart2][indexJOfStart2] == 'X') {
                    if (Checker(matrixOfPositions2, listOfCoordinates, counter2)) {
                        counter2 ++;
                        if (counter2 == 5) {
                            isFinished = true;
                        }
                    }
                }

            }
        }
        return  isFinished;
    }
    public static void lastPrinter (char[][] matrixOfPositions, char[][] matrixOfPositions2, int counter2, String regexChar, int num, ArrayList<Ship> listOfCoordinates2, Scanner scanner) {
        boolean isFinished = false;
        System.out.println();
        System.out.println("Press Enter and pass the move to another player");
        String readString2 = scanner.nextLine();
        if (scanner.hasNextLine()) {
            readString2 = scanner.nextLine();
            PositionProvider(Provider());
            System.out.println("---------------------");
            PositionProvider(matrixOfPositions);
            System.out.println();
            System.out.printf("Player %d, it's your turn:%n", num);
            String start2 = scanner.next();
            System.out.println();
            if (start2.matches(regexChar)) {
                int indexIOfStart2= (int) start2.charAt(0) - 65;
                int indexJOfStart2 = Integer.parseInt(start2.substring(1)) - 1;
                Shoter(matrixOfPositions2, start2);
                System.out.println();
                if (matrixOfPositions2[indexIOfStart2][indexJOfStart2] == 'X') {
                    if (Checker(matrixOfPositions2, listOfCoordinates2, counter2)) {
                        counter2 ++;
                        if (counter2 == 5) {
                            System.out.println("You sank the last ship. You won. Congratulations!");
                            return;
                        } else {
                            System.out.println("You sank a ship!");

                        }
                    } else {
                        System.out.println("You hit a ship!");
                    }
                } else {
                    System.out.println("You missed!");
                }

            } else {
                System.out.println("Error! You entered the wrong coordinates!");
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        String regexChar = "[A-J][1-9]|[A-J]10";
        System.out.println("Player 1, place your ships on the game field");
        System.out.println();
        PositionProvider(Provider());
        printer(0);
        ArrayList<Ship> listOfCoordinates = new ArrayList<>();
        ArrayList<Ship> listOfCoordinates2 = new ArrayList<>();

        char[][] matrixOfPositions = new char[10][10];
        char[][] matrixOfPositions2 = new char[10][10];
        int counter = 0;
        int counter2 = 0;
        int counterPlayer = 0;
        int counterPlayer2 = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String start = scanner.next();
                String end = scanner.next();
                System.out.println();
                        try {
                            int indexIOfStart = (int) start.charAt(0) - 65;
                            int indexJOfStart = Integer.parseInt(start.substring(1)) - 1;
                            int indexIOfEnd = (int) end.charAt(0) - 65;
                            int indexJOfEnd = Integer.parseInt(end.substring(1)) - 1;
                            if (indexIOfStart > indexIOfEnd) {
                                int save = indexIOfStart;
                                indexIOfStart = indexIOfEnd;
                                indexIOfEnd = save;
                            }
                            if (indexJOfStart > indexJOfEnd) {
                                int save = indexJOfStart;
                                indexJOfStart = indexJOfEnd;
                                indexJOfEnd = save;
                            }
                            if (counter != 5) {

                                if (tester(start, end, matrixOfPositions, counter)) {

                                    PositionProvider(PositionReturner(start, end, matrixOfPositions));
                                    listOfCoordinates.add(new Ship(indexIOfStart, indexJOfStart, indexIOfEnd, indexJOfEnd, decider(counter)));
                                    counter++;
                                    try {
                                        if (counter != 5) {
                                            printer(counter);
                                        } else {
                                            System.out.println();
                                            System.out.println("Press Enter and pass the move to another player");
                                            String readString = scanner.nextLine();
                                            if (scanner.hasNextLine()) {
                                                readString = scanner.nextLine();
                                                System.out.println();
                                                System.out.println("Player 2, place your ships to the game field");
                                                PositionProvider(Provider());
                                                printer(0);
                                            }
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Error! Enter numbers");
                                    }
                                }
                            }else {
                                        if (tester(start, end, matrixOfPositions2, counterPlayer)) {
                                            PositionProvider(PositionReturner(start, end, matrixOfPositions2));
                                            listOfCoordinates2.add(new Ship(indexIOfStart, indexJOfStart, indexIOfEnd, indexJOfEnd, decider(counterPlayer)));
                                            counterPlayer++;
                                            if (counterPlayer != 5) {
                                                printer(counter);
                                            } else {
                                                while (scanner.hasNextLine()) {
                                                   /* if (!lastTester(matrixOfPositions, matrixOfPositions2, counter2, regexChar, 1, listOfCoordinates, scanner) &&
                                                            !lastTester(matrixOfPositions2, matrixOfPositions, counterPlayer2, regexChar, 2, listOfCoordinates2, scanner)){*/
                                                        lastPrinter(matrixOfPositions, matrixOfPositions2, counter2, regexChar, 1, listOfCoordinates, scanner);
                                                        lastPrinter(matrixOfPositions2, matrixOfPositions, counterPlayer2, regexChar, 2, listOfCoordinates2, scanner);
                                                    /* else {
                                                        return;
                                                    }*/
                                                }
                                                }
                                            }
                                        }

                                } catch (Exception e) {
                                    System.out.println("Error");
                                }
                            }

                        } catch (Exception e) {
                            System.out.println("Error! Enter numbers");
                        }

            }
        }