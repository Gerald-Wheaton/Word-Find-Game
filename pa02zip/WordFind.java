//Gerald Wheaton

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WordFind {
    public static void main(String[] args) throws IOException {

        //only run program if program recieves both command line arguments
        //arguments be specified in this order
            //1) character grid file 2) list of words to find file
        if (args.length < 1) {
            System.out.println("\nERROR");
            System.out.println("Must provide command line arguments.");
            System.out.println("Commands must be entered in the following order:");
            System.out.println("\t characterGridFilename.txt wordListFileName.txt\n");
            //System.out.println();
        }
        
        else {
            String word, wordToFind, characterGridFile = "", wordListFile = "";
            char character;
            char[][] wordGrid;
            int colLength = 0; //assumes that grid is square
            int rowLength = 0, r = 0, c = 0;

            //if there is only one argument provided request a second
            if (args.length == 1) {
                System.out.print("Please enter the name of your words to find file: ");
                Scanner in = new Scanner(System.in);
                wordListFile = in.nextLine(); //user enters inputs file name
                characterGridFile = args[0];
                System.out.println();
            }

            else {
                for(int a = 0; a < args.length; a++) {
                    switch (a){
                        case 0:
                            characterGridFile = args[a];
                            break;
                        case 1:
                            wordListFile = args[a];
                    }
                }
                System.out.println();
            }

            Scanner fileLengthScan = new Scanner (new File(characterGridFile));
            //counters for row and column lengths
            while(fileLengthScan.hasNext()) {
                word = fileLengthScan.nextLine();
                if(word.charAt(0) != '-') {
                    colLength++;
                    rowLength = (word.length()-1) / 2;
                }
            }
    
            fileLengthScan.close();
            Scanner characterScan = new Scanner (new File(characterGridFile));

            wordGrid = new char[rowLength][colLength];
            //loop to read in each line to a string
            while(characterScan.hasNext()) { 
                word = characterScan.nextLine();
                //exclude lines and characters with a '-'
                if(word.charAt(0) != '-') {
                    //exclude lines and characters with a '|'
                    for(int i = 0; i < word.length(); i++) {
                        character = word.charAt(i);
                        if (Character.isAlphabetic(character)/*character != '|'*/) {
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

            //output matrix and size
            System.out.println("Matrix size: " + colLength + " x " + rowLength);

            for(int rowIndex = 0; rowIndex < rowLength; rowIndex++) {
                for (int columnIndex = 0; columnIndex < colLength; columnIndex++) {
                    System.out.print(wordGrid[rowIndex][columnIndex] + "  ");
                }
                System.out.println();
            }
            
            //read in list of words to search for from a file
            //Search for words in word grid
            Scanner wordScan = new Scanner (new File(wordListFile));
            while(wordScan.hasNext()) { 
                wordToFind = wordScan.nextLine();
                wordLocation(wordToFind, wordGrid, rowLength, colLength);
            }
        }
    }

    //find word
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
