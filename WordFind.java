//Gerald Wheaton

public class WordFind {
    public static void main(String[] args) {
        //take command line arguments/parameters
            //stored in an array of length 2 [0] = first parameter and [1] = second parameter
            //param 1 => word grid
            //param 2 => lsit of words to find (optional)
        //if param 2 is present and file exists search for each word in the file
            //print for each word:
                //word and location (y, x) or indicate if the word is not in the file 
                //number of comparisons made searching for the word
                // the grid is set up as [0][0] => [14][14] (meaning a 15 x 15 grid)
        
        
        char[][] arr = new char[6][6];
        String word = "Gerald";
        
        //populate array with 'A' and "GERALD" on line (0,4)
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 6; j++) {
                if (i <= 3 || i >= 5) {
                    arr[i][j] = 'H';
                }
                else {
                    switch (j) {
                        case 0:
                            arr[i][j] = 'G';
                            break;
                        case 1:
                            arr[i][j] = 'E';
                            break;
                        case 2:
                            arr[i][j] = 'R';
                            break;
                        case 3:
                            arr[i][j] = 'A';
                            break;
                        case 4:
                            arr[i][j] = 'L';
                            break;
                        case 5:
                            arr[i][j] = 'D';
                            break;
                    }
                }
                
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }

        wordLocation(word, arr, 6, 6);
    }

    //searches in a maximum of 6 directions for a matching charater from the position wordGrid [row][column]
    public static int stringSearch(String word, char[][] wordGrid, int rowIndex, int columnIndex, int rowlength, int colLength, int[] eastWest, int[] northSouth) {
        
        //base case
        if (wordGrid[rowIndex][columnIndex] != word.charAt(0)) {
            return -1;
        }

        for (int direction = 0; direction < 6 /* 6 is the number of elements in the eastWest && northSouth arrays */; direction++) {
            
            int rowDirection, colDirection, i;

            rowDirection = rowIndex + northSouth[direction];
            colDirection = columnIndex + eastWest[direction];

            for (i = 0; i < word.length(); i++) {
                //might need to say >= instead of just >
                //might need to have rowLength-1 & colLength - 1
                if (rowDirection > rowlength  || colDirection > colLength || rowDirection < 0 || colDirection < 0) {
                    break; //or maybe break; ????
                }

                if (wordGrid[rowDirection][colDirection] != word.charAt(i)) {
                    break; //or maybe break; ????
                }

                rowDirection = rowDirection + eastWest[i];
                colDirection = colDirection + northSouth[i];
            }


            //if i is word.length - 1 that means the entire word has been found
            if (i == (word.length() - 1)) {
                return 0;
            }
        }
        
        return -1;
    }

    public static void wordLocation(String word, char[][] wordGrid, int rowLength, int colLength) {

        //declare arrays that represent directions east, west, north, south
        /*
            using the eastWest and northSouth arrays you will be able to check the character around the specified locaiton in the grid:
            ( row, col ) meaning ( northSouth, eastWest ) 
            (0,-1) (0,1) (-1,0) (1,0) (-1,-1) (1,-1) (-1,1) (1,1)
        */
        int[] eastWest =   {-1,1,0,0,-1,-1,1,1};
        int[] northSouth = {0,0,-1,1,-1,1,-1,1};

        for (int rowIndex = 0; rowIndex < rowLength; rowIndex ++) {
            for (int columnIndex = 0; columnIndex < colLength; columnIndex++) {
                if (stringSearch(word, wordGrid, rowIndex, columnIndex, rowLength, colLength, eastWest, northSouth) == 0) {
                    System.out.println("(" + rowIndex + " , " + columnIndex + ")");
                    break;
                }
            }
        }
    }
}