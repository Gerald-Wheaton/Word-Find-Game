//Gerald Wheaton

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WordFind {
    public static void main(String[] args) throws IOException {

        String word;
        char word2;
        char[][] wordGrid;
        int matrixSize = 0; //assumes that grid is square
        Scanner fileScan = new Scanner (new File("cashiers.txt"));

        while(fileScan.hasNext()) {
            fileScan.nextLine();
            matrixSize++;
        }

        fileScan.close();
        Scanner fileScan2 = new Scanner (new File("cashiers.txt"));

        wordGrid = new char[15][15];
        while(fileScan2.hasNext()) {
            word = fileScan2.nextLine();
            for(int i = 0; i < word.length(); i++) {
                word2 = word.charAt(i);
                System.out.print(word2);
            }
            System.out.println();
        }
        System.out.println("Square matrix size: " + matrixSize + " x " + matrixSize);
        fileScan2.close();

        
    }

    //returns false if the word is not found
    public static boolean wordLocation(String word, char[][] wordGrid, int rowLength, int colLength) {
        for (int rowIndex = 0; rowIndex < rowLength; rowIndex++) {
            for (int columnIndex = 0; columnIndex < colLength; columnIndex++) {
                if (wordGrid[rowIndex][columnIndex] == word.charAt(0)) {

                    if (North(word, wordGrid, rowIndex, columnIndex) == true) {
                        System.out.println(word + " was found starting at (" + (rowIndex + 1) + ", " + (columnIndex + 1) + ") and oriented North");
                        return true;
                    }

                    if (South(word, wordGrid, rowIndex, columnIndex, colLength) == true) {
                        System.out.println(word + " was found starting at (" + (rowIndex + 1) + ", " + (columnIndex + 1) + ") and oriented South");
                        return true;
                    }

                    if (West(word, wordGrid, rowIndex, columnIndex) == true) {
                        System.out.println(word + " was found starting at (" + (rowIndex + 1) + ", " + (columnIndex + 1) + ") and oriented West");
                        return true;
                    }

                    if (East(word, wordGrid, rowIndex, columnIndex, rowLength) == true) {
                        System.out.println(word + " was found starting at (" + (rowIndex + 1) + ", " + (columnIndex + 1) + ") and oriented East");
                        return true;
                    }

                    if (NorthEast(word, wordGrid, rowIndex, columnIndex, rowLength) == true) {
                        System.out.println(word + " was found starting at (" + (rowIndex + 1) + ", " + (columnIndex + 1) + ") and oriented Northeast");
                        return true;
                    }

                    if (NorthWest(word, wordGrid, rowIndex, columnIndex) == true) {
                        System.out.println(word + " was found starting at (" + (rowIndex + 1) + ", " + (columnIndex + 1) + ") and oriented Northwest");
                        return true;
                    }

                    if (SouthWest(word, wordGrid, rowIndex, columnIndex, colLength) == true) {
                        System.out.println(word + " was found starting at (" + (rowIndex + 1) + ", " + (columnIndex + 1) + ") and oriented Southwest");
                        return true;
                    }

                    if (SouthEast(word, wordGrid, rowIndex, columnIndex, colLength, rowLength) == true) {
                        System.out.println(word + " was found starting at (" + (rowIndex + 1) + ", " + (columnIndex + 1) + ") and oriented Southeast");
                        return true;
                    }

                }
            }
        }
        return false;
    }


    //compass functions
    public static boolean North(String word, char[][] wordGrid, int rowIndex, int columnIndex) {
        if (rowIndex < word.length() - 1) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            //if current character in the grid != currnet character in the word exit the loop
            if (wordGrid[rowIndex - i][columnIndex] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean NorthEast(String word, char[][] wordGrid, int rowIndex, int columnIndex, int rowLength) {
        if (columnIndex + word.length() > rowLength || rowIndex < word.length() - 1) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            //if current character in the grid != currnet character in the word exit the loop
            if (wordGrid[rowIndex - i][columnIndex + i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean NorthWest(String word, char[][] wordGrid, int rowIndex, int columnIndex) {
        if (columnIndex < word.length() - 1 || rowIndex < word.length() - 1) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            //if current character in the grid != currnet character in the word exit the loop
            if (wordGrid[rowIndex - i][columnIndex - i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }


    public static boolean South(String word, char[][] wordGrid, int rowIndex, int columnIndex, int colLength) {
        if (rowIndex + word.length() > colLength) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            //if current character in the grid != currnet character in the word exit the loop
            if (wordGrid[rowIndex + i][columnIndex] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean SouthWest(String word, char[][] wordGrid, int rowIndex, int columnIndex, int colLength) {
        if (columnIndex < word.length() - 1 || rowIndex + word.length() > colLength) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            //if current character in the grid != currnet character in the word exit the loop
            if (wordGrid[rowIndex + i][columnIndex - i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean SouthEast(String word, char[][] wordGrid, int rowIndex, int columnIndex, int colLength, int rowLength) {
        if (columnIndex + word.length() > rowLength || rowIndex + word.length() > colLength) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            //if current character in the grid != currnet character in the word exit the loop
            if (wordGrid[rowIndex + i][columnIndex + i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }


    public static boolean West(String word, char[][] wordGrid, int rowIndex, int columnIndex) {
        if (columnIndex < word.length() - 1) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            //if current character in the grid != currnet character in the word exit the loop
            if (wordGrid[rowIndex][columnIndex - i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean East(String word, char[][] wordGrid, int rowIndex, int columnIndex, int rowLength) {
        if (columnIndex + word.length() > rowLength) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            //if current character in the grid != currnet character in the word exit the loop
            if (wordGrid[rowIndex][columnIndex + i] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
