package com.example.java_coding;

import java.util.Scanner;

import static java.lang.Math.abs;

public class sample {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int XCount = 0;
        int OCount = 0;
        int spaceCount = 9;

        String strBegin = "| ";
        String strEnd = "|";

        char[][] chMatrix = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '},
        };

        drawGrid(strBegin, strEnd, chMatrix); //Draw Empty Grid
        int playerCount = 0;

        while (true) {

            int rowPos = 0;
            int colPos = 0;

            rowPos = scanner.nextInt();
            colPos = scanner.nextInt();


            char chrInput = ' ';
            playerCount++;

            while (true) {

                if (playerCount % 2 == 0)
                    chrInput = 'O';
                else
                    chrInput = 'X';

                boolean blnInputError = false;
                if (rowPos < 1 || rowPos > 3 || colPos < 1 || colPos > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    blnInputError = true;
                } else if (chMatrix[rowPos - 1][colPos - 1] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                    blnInputError = true;
                }

                if (blnInputError) {
                    rowPos = scanner.nextInt();
                    colPos = scanner.nextInt();
                } else
                    break;
            }

            chMatrix[rowPos - 1][colPos - 1] = chrInput;
            spaceCount--;

            drawGrid(strBegin, strEnd, chMatrix);


            boolean XWin = false;
            boolean OWin = false;

            XWin = isWin(chMatrix, 'X', XWin);
            OWin = isWin(chMatrix, 'O', OWin);


            boolean blnImpossible = false;
            boolean blnDraw = false;
            boolean blnGameNotOver = false;

            if (!XWin && !OWin && spaceCount == 0) {
                blnDraw = true;
                OWin = false;
                XWin = false;
            }

            if (!XWin && !OWin && spaceCount > 0) {
                blnGameNotOver = true;
                OWin = false;
                XWin = false;
            }

            if ((XWin && OWin) || (abs(XCount - OCount) >= 2)) {
                blnImpossible = true;
                OWin = false;
                XWin = false;
                blnGameNotOver = false;
            }

            if (XWin) {
                System.out.println("X wins");
                break;
            }
            if (OWin) {
                System.out.println("O wins");
                break;
            }
            if (blnImpossible) {
                System.out.println("Impossible");
                break;
            }
            if (blnDraw) {
                System.out.println("Draw");
                break;
            }
            if (blnGameNotOver) ;
            //System.out.println("Game not finished");

        }
    }

    private static void drawGrid(String strBegin, String strEnd, char[][] chMatrix) {
        System.out.println("---------");

        for (int i = 0; i < 3; i++) {
            System.out.print(strBegin);
            for (int j = 0; j < 3; j++)
                System.out.print(chMatrix[i][j] + " ");
            System.out.println(strEnd);
        }

        System.out.println("---------");
    }

    private static boolean isWin(char[][] chMatrix, char A, boolean AWin) {
        if ((chMatrix[0][0] == A) && (chMatrix[0][0] == chMatrix[0][1]) && (chMatrix[0][1] == chMatrix[0][2]))
            AWin = true;

        if ((chMatrix[1][0] == A) && (chMatrix[1][0] == chMatrix[1][1]) && (chMatrix[1][1] == chMatrix[1][2]))
            AWin = true;

        if ((chMatrix[2][0] == A) && (chMatrix[2][0] == chMatrix[2][1]) && (chMatrix[2][1] == chMatrix[2][2]))
            AWin = true;

        if ((chMatrix[0][0] == A) && (chMatrix[0][0] == chMatrix[1][0]) && (chMatrix[1][0] == chMatrix[2][0]))
            AWin = true;

        if ((chMatrix[0][1] == A) && (chMatrix[0][1] == chMatrix[1][1]) && (chMatrix[1][1] == chMatrix[2][1]))
            AWin = true;

        if ((chMatrix[0][2] == A) && (chMatrix[0][2] == chMatrix[1][2]) && (chMatrix[1][2] == chMatrix[2][2]))
            AWin = true;

        if ((chMatrix[0][0] == A) && (chMatrix[0][0] == chMatrix[1][1]) && (chMatrix[1][1] == chMatrix[2][2]))
            AWin = true;

        if ((chMatrix[0][2] == A) && (chMatrix[0][2] == chMatrix[1][1]) && (chMatrix[1][1] == chMatrix[2][0]))
            AWin = true;
        return AWin;
    }
}
