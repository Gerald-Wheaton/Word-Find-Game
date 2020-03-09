//Gerald Wheaton

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WordFind {
    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new FileReader("cashiers.txt"));
  
        char[][] characterGrid = new char[4][4]; //contains the 2d character grid
        int g = 0, i = 0, j= 0, rowLength = 0, colLength = 0;
        Scanner scanner = new Scanner(System.in);
        String word;
        //String word = "MQD"; //test string

        //get grid dimensions
        while((g = br.read()) != -1) {         //Read char by Char
            char character = (char) g;          //converting integer to character
            if (character == '\n') { //once the end of line is reached this resets the column index variable and increments the row index
                rowLength = j;
                j = 0;
                i++;
                colLength = i;
            }
            if (character != '-' && character != '|' && character != '\n') {
                j++;
            }
        }

        //close the file
        br.close();

        //open the file so I can read in the grid
        BufferedReader br2=new BufferedReader(new FileReader("cashiers.txt"));

        //read in grid to 2d array
            characterGrid = new char[rowLength][colLength];
            //resetting the loop variables
            i = 0;
            j = 0;
            g = 0;
        while((g = br2.read()) != -1) {         //Read char by Char
            char character = (char) g;          //converting integer to character
            if (character == '\n') { //once the end of line is reached this resets the column index variable and increments the row index
                j = 0;
                i++;
            }
            if (character != '-' && character != '|' && character != '\n' && j < colLength && i < rowLength) {
                characterGrid[i][j] = character;
                j++;
            }
        }

        System.out.println("row length: " + rowLength + " column length: " + colLength + "\n");

        for (int r = 0; r < characterGrid.length; r++) {
            for (int c = 0; c < characterGrid[0].length; c++) {
                System.out.print(characterGrid[r][c]);
            }
            System.out.println();
        }

        
        //user inputs a word to search for
            System.out.print("Enter a word : ");
            word = scanner.nextLine();  // Read user input
            
            if(wordLocation(word, characterGrid, rowLength, colLength) == false) {
                System.out.println(word + " not found.");
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
