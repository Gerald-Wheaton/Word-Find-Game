//Gerald Wheaton

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WordFind {
    public static void main(String[] args) throws IOException {

        String word, wordToFind;
        char character;
        char[][] wordGrid;
        int colLength = 0; //assumes that grid is square
        int rowLength = 0, r = 0, c = 0;

        Scanner fileLengthScan = new Scanner (new File("cashiers.txt"));
        //counters for row and column lengths
        while(fileLengthScan.hasNext()) {
            word = fileLengthScan.nextLine();
            if(word.charAt(0) != '-') {
                colLength++;
                rowLength = (word.length() - 1) / 2;
            }
        }
 
        fileLengthScan.close();
        Scanner characterScan = new Scanner (new File("cashiers.txt"));

        wordGrid = new char[rowLength][colLength];
        //loop to read in each line to a string
        while(characterScan.hasNext()) { 
            word = characterScan.nextLine();
            //exclude lines and characters with a '-'
            if(word.charAt(0) != '-') {
                //exclude lines and characters with a '|'
                for(int i = 0; i < word.length(); i++) {
                    character = word.charAt(i);
                    if (character != '|') {
                        wordGrid[r][c] = character;
                        c++;
                    }
                }
                //reset columns and increment the row
                //allows loop to iterate through entire file while filling up the 2D array
                c = 0;
                r++;
            }
        }

        for(int rowIndex = 0; rowIndex < rowLength; rowIndex++) {
            for (int columnIndex = 0; columnIndex < colLength; columnIndex++) {
                System.out.print(wordGrid[rowIndex][columnIndex]);
            }
            System.out.println();
        }
        characterScan.close();

        System.out.println("Square matrix size: " + colLength + " x " + rowLength);

        //read in list of words to search for from a file
        //Search for words in word grid
        Scanner wordScan = new Scanner (new File("cashwords.txt"));
        while(wordScan.hasNext()) { 
            wordToFind = wordScan.nextLine();
            wordLocation(wordToFind, wordGrid, rowLength, colLength);
        }
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
