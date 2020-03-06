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
        
        if(wordLocation(word, characterGrid, rowLength, colLength) < 0) {
            System.out.println(word + " not found.");
        }
    }


    //returns -1 if the word is not found
    public static int wordLocation(String word, char[][] wordGrid, int rowLength, int colLength) {
        for (int rowIndex = 0; rowIndex < rowLength; rowIndex++) {
            for (int columnIndex = 0; columnIndex < colLength; columnIndex++) {
                if (wordGrid[rowIndex][columnIndex] == word.charAt(0)) {

                    //checking word orientation
                    if (North(word, wordGrid, rowIndex, columnIndex) > 0) {
                        System.out.println(word + " was found starting at (" + rowIndex + ", " + columnIndex + ") and oriented North");
                        return 1;
                    }

                    if (South(word, wordGrid, rowIndex, columnIndex, colLength) > 0) {
                        System.out.println(word + " was found starting at (" + rowIndex + ", " + columnIndex + ") and oriented South");
                        return 1;
                    }

                    if (East(word, wordGrid, rowIndex, columnIndex, rowLength) > 0) {
                        System.out.println(word + " was found starting at (" + rowIndex + ", " + columnIndex + ") and oriented East");
                        return 1;
                    }

                    if (West(word, wordGrid, rowIndex, columnIndex) > 0) {
                        System.out.println(word + " was found starting at (" + rowIndex + ", " + columnIndex + ") and oriented West");
                        return 1;
                    }
                }
            }
        }
        return -1;
    }

    public static int North(String word, char[][] wordGrid, int rowIndex, int columnIndex) {
        int currentChar = 1; //starts at one since the first character was already checked in the wordLocation function
        for (int i = rowIndex; i > rowIndex - word.length(); i--) {
            //check next character as long as not out of bounds 
            if (i > -1) {
                //if current character in the grid != currnet character in the word exit the loop
                if (wordGrid[i][columnIndex] != word.charAt(currentChar)) {
                    return -1;
                }
                else {
                    currentChar++;
                }
            }
        }
        return 1;
    }

    public static int South(String word, char[][] wordGrid, int rowIndex, int columnIndex, int colLength) {
        int currentChar = 0; //starts at one since the first character was already checked in the wordLocation function
        for (int i = rowIndex; i < word.length(); i++) {
            //check next character as long as not out of bounds 
            if (i < colLength) {
                //if current character in the grid != currnet character in the word exit the loop
                if (wordGrid[i][columnIndex] != word.charAt(currentChar)) {
                    return -1;
                }
                else {
                    currentChar++;
                }
            }
        }
        return 1;
    }

    public static int East(String word, char[][] wordGrid, int rowIndex, int columnIndex, int rowLength) {
        int currentChar = 0; //starts at one since the first character was already checked in the wordLocation function
        for (int i = columnIndex; i < word.length(); i++) {
            //check next character as long as not out of bounds 
            if (i < rowLength) {
                //if current character in the grid != currnet character in the word exit the loop
                if (wordGrid[rowIndex][i] != word.charAt(currentChar)) {
                    return -1;
                }
                else {
                    currentChar++;
                }
            }
        }
        return 1;
    }

    public static int West(String word, char[][] wordGrid, int rowIndex, int columnIndex) {
        int currentChar = 0; //starts at one since the first character was already checked in the wordLocation function
        for (int i = columnIndex; i > columnIndex - word.length(); i--) {
            //check next character as long as not out of bounds 
            if (i > -1) {
                //if current character in the grid != currnet character in the word exit the loop
                if (wordGrid[rowIndex][i] != word.charAt(currentChar)) {
                    return -1;
                }
                else {
                    currentChar++;
                }
            }
        }
        return 1;
    }
}