package battelship;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static char[][] Provider() {
        char[][] matrixOfPositions = new char[10][10];
        for (char[] matrixOfPosition : matrixOfPositions) {
            Arrays.fill(matrixOfPosition, '~');
        }
        return matrixOfPositions;
    }
    public static char[][] ProviderMissOrHit(int indexIOfStart, int indexJOfStart, char variable) {
        char[][] matrixOfPositions = new char[10][10];
        for (int i = 0; i < matrixOfPositions.length; i++) {
            for (int j = 0; j < matrixOfPositions[i].length; j++) {
                if (i == indexIOfStart && j == indexJOfStart) {
                    matrixOfPositions[i][j] = variable;
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
        } else {
            matrixOfPositions[indexIOfStart][indexJOfStart] = 'M';
        }
        return matrixOfPositions;
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

    public static void main(String[] args) {
        PositionProvider(Provider());
        printer(0);
        char[][] matrixOfPositions = new char[10][10];
        int counter = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String start = scanner.next();
                String end = scanner.next();
                System.out.println();
                        try {
                            String regexChar = "[A-J][1-9]|[A-J]10";
                            int indexIOfStart = (int) start.charAt(0) - 65;
                            int indexJOfStart = Integer.parseInt(start.substring(1)) - 1;
                            int indexIOfEnd = (int) end.charAt(0) - 65;
                            int indexJOfEnd = Integer.parseInt(end.substring(1)) - 1;

                                if (start.matches(regexChar) && end.matches(regexChar)) {

                                        if (indexIOfStart == indexIOfEnd || indexJOfStart == indexJOfEnd) {

                                                if ((Math.abs(indexIOfStart - indexIOfEnd) == 0 && Math.abs(indexJOfStart - indexJOfEnd) == decider(counter)) ||
                                                        (Math.abs(indexIOfStart - indexIOfEnd) == decider(counter))) {
                                                    if (detector(indexIOfStart, indexJOfStart, indexIOfEnd, indexJOfEnd, matrixOfPositions)) {
                                                        PositionProvider(PositionReturner(start, end, matrixOfPositions));
                                                    counter++;
                                                    try {
                                                        if (counter != 5) {
                                                            printer(counter);
                                                        } else {
                                                            System.out.println();
                                                            System.out.println("The game starts!");
                                                            System.out.println();
                                                            PositionProvider(Provider());
                                                            //PositionProvider(matrixOfPositions);
                                                            System.out.println();
                                                            System.out.println("Take a shot!");
                                                            System.out.println();
                                                            while (scanner.hasNext()) {
                                                                String start2 = scanner.next();
                                                                System.out.println();
                                                                if (start2.matches(regexChar)) {
                                                                    int indexIOfStart2= (int) start2.charAt(0) - 65;
                                                                    int indexJOfStart2 = Integer.parseInt(start2.substring(1)) - 1;
                                                                    Shoter(matrixOfPositions, start2);
                                                                    if (matrixOfPositions[indexIOfStart2][indexJOfStart2] == 'X') {
                                                                        PositionProvider(ProviderMissOrHit(indexIOfStart2, indexJOfStart2, 'X'));
                                                                    } else {
                                                                        PositionProvider(ProviderMissOrHit(indexIOfStart2, indexJOfStart2, 'M'));
                                                                    }
                                                                    System.out.println();
                                                                    if (matrixOfPositions[indexIOfStart2][indexJOfStart2] == 'X') {
                                                                        System.out.println("You hit a ship!");
                                                                    } else {
                                                                        System.out.println("You missed!");
                                                                    }
                                                                    System.out.println();
                                                                    PositionProvider(matrixOfPositions);
                                                                    return;
                                                                } else {
                                                                    System.out.println("Error! You entered the wrong coordinates! Try again:");
                                                                    System.out.println();
                                                                }
                                                            }
                                                        }
                                                    } catch (Exception e) {
                                                        System.out.println("Error");
                                                    }
                                                } else {
                                                        System.out.println("Error! You placed it too close to another one. Try again:");
                                                        System.out.println();
                                                    }
                                                } else {

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
                        } catch (Exception e) {
                            System.out.println("Error! Enter numbers");
                        }

            }
        }
    }
}
