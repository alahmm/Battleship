package battelship;

import java.util.Scanner;

public class Main {

    public static void PositionProvider() {
        char[][] matrixOfPositions = new char[10][10];
        String line = "  1 2 3 4 5 6 7 8 9 10";
        System.out.println(line);
        for (int i = 0; i < matrixOfPositions.length ; i++) {
                char variable = (char) (i + 65);
                System.out.print(variable+ " ");
            for (int j = 0; j < matrixOfPositions[i].length; j++) {
                matrixOfPositions[i][j] = '~';
                System.out.print(matrixOfPositions[i][j]+ " ");
            }
            System.out.println();
        }
    }
    public static void PositionProvider(String start, String end, char[][] matrixOfPositions) {

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
        String line = "  1 2 3 4 5 6 7 8 9 10";
        System.out.println(line);
        for (int i = 0; i < matrixOfPositions.length ; i++) {
            char variable = (char) (i + 65);
            System.out.print(variable+ " ");
            for (int j = 0; j < matrixOfPositions[i].length; j++) {
                if (i >= indexIOfStart && i <= indexIOfEnd && j >= indexJOfStart && j <= indexJOfEnd) {
                    matrixOfPositions[i][j] = '0';
                } else if (matrixOfPositions[i][j] == '0') {
                    matrixOfPositions[i][j] = '0';
                } else {
                    matrixOfPositions[i][j] = '~';
                }

                System.out.print(matrixOfPositions[i][j]+ " ");
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
        for (int i = 0; i < matrixOfPositions.length ; i++) {
            for (int j = 0; j < matrixOfPositions[i].length; j++) {
                if (i >= indexIOfStart && i <= indexIOfEnd && j >= indexJOfStart && j <= indexJOfEnd) {
                    matrixOfPositions[i][j] = '0';
                } else if (matrixOfPositions[i][j] == '0') {
                    matrixOfPositions[i][j] = '0';
                }else {
                    matrixOfPositions[i][j] = '~';
                }
            }
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
        }else {
            return "the Destroyer";
        }
    }

    public static void printer (int counter) {
        System.out.println();
        System.out.printf("Enter the coordinates of %s (%d cells):%n", deciderName(counter), decider(counter) + 1);
        System.out.println();
    }

    public static void main(String[] args) {
        PositionProvider();
        printer(0);
        char[][] matrixOfPositions = new char[10][10];
        int counter = 0;
        try(Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String start = scanner.next();
                String end = scanner.next();
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
                            PositionProvider(start, end, PositionReturner(start, end, matrixOfPositions));
                            counter++;
                            try {
                                if (counter != 5) {
                                    printer(counter);
                                } else {
                                    return;
                                }
                            } catch (Exception e) {
                                System.out.printf("Error! Wrong length of %s! Try again:", deciderName(counter));
                            }
                        } else {
                            System.out.println("Error! Wrong length of the Aircraft Carrier! Try again:");
                        }

                        //if (PositionReturner(start, end, matrixOfPositions)[indexIOfStart][indexJOfStart] == )

                    } else {
                        System.out.println("Error! Wrong ship location! Try again:");
                    }
                } else {
                    System.out.println("Error! Enter a valid number");
                }
                } catch (Exception e) {
                        System.out.println("Error! Enter numbers");
                    }
                }

                }

            }
        }
