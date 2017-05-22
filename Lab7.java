/**
 * Created by Kyle on 11/23/2015.
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Lab7 {

    public static void main(String args[]) throws FileNotFoundException {
        HashTable table = new HashTable();
        boolean retrieving = false;
        String line;

        boolean debug = false;

        File file = new File("lab7.dat");
        Scanner in = new Scanner(file);

        if (debug) {
            System.out.println("begin while loop");
        }//end debug if

        while (in.hasNext()){
            line = in.nextLine();

            if (debug) {
                System.out.println(" Line is: " + line);
            }//end debug if

            if (!line.equals("**********") &&
                    !retrieving){

                if (debug){
                    System.out.println("In inserting if: ");
                }//end debug if

                try {
                    wordNode newWord = new wordNode(line);
                    int index = table.tableInsert(newWord);
                    System.out.println("Index: " + index +
                            " Inserting: " + newWord.value);
                }catch( HashException e){
                    e.printStackTrace();
                }//end trycatch

                /*if (debug){
                    System.out.println("newWord: " + table.tableRetrieve(newWord).value);
                    //System.out.println("newWord: " + newWord.value);
                }//end debug if
                */


            }
            else if (!line.equals("**********") &&
                    retrieving){
                try {
                    System.out.println("line to retrieve: " + line);
                    wordNode findWord = new wordNode(line);
                    wordNode printWord = table.tableRetrieve(findWord);

                    if (printWord != null) {
                        System.out.println(" Word: '" + printWord.value + "' found!\n");// +
//                                table.hashIndex(findWord) + "\n");
                    }
                }catch(HashException e){
                    e.printStackTrace();
                }//end trycatch
            }
            else if (line.equals("**********")){
                if (!retrieving){
                    try {
                        retrieving = true;
                        System.out.println("**********");
                        System.out.println("Now retrieving!");
                        System.out.println("**********");
                    }catch(HashException e){
                        e.printStackTrace();
                    }//end trycatch
                }
                else {//shouldn't happen
                    try {
                        retrieving = false;
                        System.out.println("Now inserting!");
                    }catch(HashException e){
                        e.printStackTrace();
                    }//end trycatch
                }//end if
            }
            else{
                System.out.println("Oops! Something went wrong.");
            }//end if

        }//end while

        table.printStats();

    }//end main
}//end Lab7
