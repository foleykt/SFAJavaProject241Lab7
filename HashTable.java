/**
 * Created by Kyle on 11/23/2015.
 */
import java.text.NumberFormat;

public class HashTable {
    private final int MAX_SIZE = 37;
    private wordNode[] table;
    private int size = 0;
    private int successRet = 0;
    private double successAtt = 0;
    private int unsuccessRet = 0;
    private double unsuccessAtt = 0;

    public HashTable(){
        table = new wordNode[MAX_SIZE];
    }//end constructor

    public boolean tableIsEmpty(){
        return size == 0;
    }//end tableIsEmpty

    public int tableLength(){
        return size;
    }//end tableLength

    public int tableInsert(wordNode newWord){
        boolean debug = false;
        if (debug) {
            System.out.println("In tableInsert: ");
        }//end debug if

        int i;
        if (tableLength() < MAX_SIZE){
            i = hashIndex(newWord);

            if(debug) {
                System.out.println("Not full. i = " + i);
                System.out.println("newWord: " + newWord.value);
            }//end debug if

            if(table[i] == null) {
                table[i] = newWord;
                size++;


                if(debug){
                    System.out.println("First try empty.");
                    System.out.println(" Inserting: " + newWord.value);
                    System.out.println("    In table: " + table[i].value);
                }//end debug if
                return i;
            }
            else{//search for first empty spot
                while (table[i] != null){
                    i = (i + 1) % 37;
                }//end while
                table[i] = newWord;
                size++;


                if(debug){
                    System.out.println("Second try, traverse to empty.");
                    System.out.println(" Inserting: " + newWord.value);
                    System.out.println("    In table: " + table[i].value);
                }//end debug if
                return i;
            }//end if
        }
        else {//table full
            throw new HashException("Table is full");
        }//end if
    }//end tableInsert

    public wordNode tableRetrieve(wordNode newWord)throws HashException{
        int attempts = 0;
        int i;

        if (!tableIsEmpty()){
            i = hashIndex(newWord);

            attempts++;

            if(table[i] != null &&
                    table[i].value.equals(newWord.value)) {
                successRet++;
                successAtt += attempts;
                System.out.print("At Index: " + i);
                return table[i];
            }
            else{//search for non null value
                boolean found = false;
                while (!found){
                    i = (i + 1) % 37;
                    attempts++;
                    if (table[i] != null){
                        if (table[i].value.equals(newWord.value)){
                            found = true;
                            System.out.print("At Index: " + i);
                        }//end if
                    }

                    if (table[i] == null){
                       System.out.println("Word '" + newWord.value  + "' is not in the table.\n");
                        unsuccessRet++;
                        unsuccessAtt += attempts;
                        return null;
                    }//end if
                }//end while
                successRet++;
                successAtt += attempts;
                return table[i];
            }//end if
        }
        else{//empty table
            throw new HashException("Table is empty");
        }//end if
    }//end tableRetrieve

    public int hashIndex(wordNode newWord){
        int firstoff;
        int lastoff;

        firstoff = newWord.value.charAt(0) - 'a';
        lastoff = newWord.value.charAt(newWord.value.length()-1) - 'a';

        return (firstoff * 256 + lastoff) % 37;
    }//end hashIndex

    public void printStats(){
        //double total = successRet + unsuccessRet;
        double sAvg = successAtt/successRet;
        double uAvg = unsuccessAtt/unsuccessRet;

        System.out.println("*******************\n" + "Printing Statistics\n" + "*******************");
        System.out.printf("Total Accesses: %.0f\n" , (successAtt + unsuccessAtt));
        System.out.println("Total attempted retrievals: " + (successRet + unsuccessRet));
        System.out.println("Total successful retrievals: " + successRet);
        System.out.println("Total unsuccessful retrievals: " + unsuccessRet);
        System.out.printf("Average accesses for all retrievals: %.2f\n" , (successAtt + unsuccessAtt)/(successRet + unsuccessRet));
        System.out.printf("Average accesses for successful retrievals: %.2f\n" , sAvg);
        System.out.printf("Average accesses for unsuccessful retrievals: %.2f\n" , uAvg);
    }//end printStats

}//end HashTable
