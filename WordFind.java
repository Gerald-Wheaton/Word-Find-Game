//Gerald Wheaton

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordFind {
    public static void main(String[] args) throws IOException {

        File gridFile=new File("filename.txt");
            FileReader fr1=new FileReader(gridFile);
            BufferedReader br1=new BufferedReader(fr1);
        File wordList=new File("wordsList.txt");
            FileReader fr2=new FileReader(wordList);
            BufferedReader br2=new BufferedReader(fr2);

        char[][] characterGrid = new char[4][4]; //contains the 2d character grid
        int c = 0, i = 0, j= 0;    
        //String word = "MIGA"; //test string

        //while loop to read in grid of characters file and omit anything that is not a character
        while((c = br1.read()) != -1) {         //Read char by Char
            char character = (char) c;          //converting integer to character
            if (character == '\n') { //once the end of line is reached this resets the column index variable and increments the row index
                j = 0;
                i++;
            }
            if (character != '-' && character != '|' && character != '\n') {
                characterGrid[i][j] = character;
                j++;
            }
        }

        for (int q = 0; q < 4; q++) {
            for (int g = 0; g < 4; g++) {
                System.out.print(characterGrid[q][g]);
            }
            System.out.println();
        }

        /*while((c = br2.read()) != -1) {
            String word = Integer.toString(c);

            if (!wordLocation(word, characterGrid, 4, 4)) {
                //alert user which words have not been found
                System.out.println("Word: " + word + " was not found.");
            }
        }*/
        
    }

    //returns false if the word is not found
    public static boolean wordLocation(String word, char[][] wordGrid, int rowLength, int colLength) {
        for (int rowIndex = 0; rowIndex < rowLength; rowIndex ++) {
            for (int columnIndex = 0; columnIndex < colLength; columnIndex++) {
                /*if (stringSearch(word, wordGrid, rowIndex, columnIndex, rowLength, colLength, eastWest, northSouth) == 0) {
                    System.out.println("(" + rowIndex + " , " + columnIndex + ")");
                    break;
                }*/
                if (wordGrid[rowIndex][columnIndex] == word.charAt(0)) {

                    //if word length does not exceed array bounds check if word is North
                    if ((rowIndex - word.length()) >= -1 ) {
                        if (North(word, wordGrid, rowIndex, columnIndex)) {
                            System.out.println(word + " was found starting at (" + rowIndex + ", " + columnIndex + ") and oriented North");
                            return true;
                        }
                    }

                    //if word length does not exceed array bounds check if word is South
                    if ((rowIndex + word.length()) <= colLength) {
                        if (South(word, wordGrid, rowIndex, columnIndex)) {
                            System.out.println(word + " was found starting at (" + rowIndex + ", " + columnIndex + ") and oriented South");
                            return true;
                        }
                    }

                    //if word length does not exceed array bounds check if word is East
                    if ((columnIndex + word.length()) <= rowLength) {
                        if (East(word, wordGrid, rowIndex, columnIndex)) {
                            System.out.println(word + " was found starting at (" + rowIndex + ", " + columnIndex + ") and oriented East");
                            return true;
                        }
                    }
                
                    //if word length does not exceed array bounds check if word is West
                    if ((columnIndex - word.length()) >= -1) {
                        if (West(word, wordGrid, rowIndex, columnIndex)) {
                            System.out.println(word + " was found starting at (" + rowIndex + ", " + columnIndex + ") and oriented West");
                            return true;
                        }
                    }

                    //if word length does not exceed array bounds check if word is West
                    if (rowIndex - word.length() >= -1 && columnIndex + word.length() <= rowLength) {
                        if (NorthEast(word, wordGrid, rowIndex, columnIndex)) {
                            System.out.println(word + " was found starting at (" + rowIndex + ", " + columnIndex + ") and oriented North East");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }




    public static boolean North(String word, char[][] wordGrid, int rowIndex, int columnIndex) {
        int currentChar = 0; //starts at one since the first character was already checked in the wordLocation function
        for (int i = rowIndex; i > rowIndex - word.length(); i--) {
            //if current character in the grid != currnet character in the word exit the loop
            if (wordGrid[i][columnIndex] != word.charAt(currentChar)) {
                return false;
            }
            else {
                currentChar++;
            }
        }
        return true;
    }

    public static boolean South(String word, char[][] wordGrid, int rowIndex, int columnIndex) {
        int currentChar = 0; //starts at one since the first character was already checked in the wordLocation function
        for (int i = rowIndex; i < word.length(); i++) {
            //if current character in the grid != currnet character in the word exit the loop
            if (wordGrid[i][columnIndex] != word.charAt(currentChar)) {
                return false;
            }
            else {
                currentChar++;
            }
        }
        return true;
    }

    public static boolean East(String word, char[][] wordGrid, int rowIndex, int columnIndex) {
        int currentChar = 0; //starts at one since the first character was already checked in the wordLocation function
        for (int i = columnIndex; i < word.length(); i++) {
            //if current character in the grid != currnet character in the word exit the loop
            if (wordGrid[rowIndex][i] != word.charAt(currentChar)) {
                return false;
            }
            else {
                currentChar++;
            }
        }
        return true;
    }

    public static boolean West(String word, char[][] wordGrid, int rowIndex, int columnIndex) {
        int currentChar = 0; //starts at one since the first character was already checked in the wordLocation function
        for (int i = columnIndex; i > columnIndex - word.length(); i--) {
            //if current character in the grid != currnet character in the word exit the loop
            if (wordGrid[rowIndex][i] != word.charAt(currentChar)) {
                return false;
            }
            else {
                currentChar++;
            }
        }
        return true;
    }

    public static boolean NorthEast(String word, char[][] wordGrid, int rowIndex, int columnIndex) {
        int currentChar = 0; //starts at one since the first character was already checked in the wordLocation function
        int j = 0; //allows the column index to be incremented separately from the row index
        for (int i = rowIndex; i < word.length(); i--) {
            //if current character in the grid != currnet character in the word exit the loop
            if (wordGrid[i][columnIndex + j] != word.charAt(currentChar)) {
                return false;
            }
            else {
                currentChar++;
                j++;
            }
        }
        return true;
    }


}













