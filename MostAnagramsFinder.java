import java.io.*;
import java.util.Iterator;
/**
 * Class that is as a command line java application that uses several data
 * structures and insertionSort, along with many helper methods to
 * find the word or words with the most anagrams in a given txt file
 * @author Benjamin Sidley
 * @version 1.1.1? May 5, 2024
 */
public class MostAnagramsFinder {
    /**
     * takes an array of lowerecased char array and sorts it lexicographically
     * and transformed it back into a string to be returned. uses insertion
     * sort as we have seen in class
     * @param arr the array to process
     * @return String representation of the sorted and lowercased char array
     */
    public static String insertionSort(char[] arr) {

        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int k = 0;
            char current = arr[i];
            for (k = i - 1; k >= 0 && arr[k] > current; --k) {
                arr[k + 1] = arr[k];
            }
            arr[k + 1] = current;
        }
        String finalStr = new String(arr); //converting the ordered array into the string to be returned as the key
        return finalStr;

    }
    /**
     * inserts a key( ordered string for lookup) value (linked list of all anagrams
     * of the key) pair into a Binary Search Tree. If the BST already has the
     * ordered string, there is an anagram and we lookup that key to find the
     * linked list where the anagram shall be inserted
     * @param original the original word we are checking for anagrams
     * @param ordered the ordered and lowercased version of the original word
     *                as the key for looking up if there is an anagram or not
     * @param BSTree the tree we are looking up / adding to to store all the
     *               anagrams
     */
    public static void BSTinsert(String original, String ordered, MyMap<String, MyList<String>> BSTree){
        if (BSTree.get(ordered) != null) { //looking up to see if there is already an anagram in the map
            BSTree.get(ordered).add(original); //if there is, add the original word to the linked list
        } else {
            MyList<String> startList = new MyLinkedList<>(); //if not there, need to create a new list for a new ordered word
            startList.add(original); //adding new word to the linked list
            BSTree.put(ordered, startList); //creating a key value pair from the ordered word and new linked list
            // and putting it into the map
        }
    }
    /**
     * inserts a key( ordered string for lookup) value (linked list of all anagrams
     * of the key) pair into a Red Black Tree. If the RBT already has the
     * ordered string, there is an anagram and we lookup that key to find the
     * linked list where the anagram shall be inserted
     * @param original the original word we are checking for anagrams
     * @param ordered the ordered and lowercased version of the original word
     *                as the key for looking up if there is an anagram or not
     * @param RBTree the tree we are looking up / adding to to store all the
     *               anagrams
     */

    public static void RBTinsert(String original, String ordered, MyMap<String, MyList<String>> RBTree){
        if (RBTree.get(ordered) != null) { //looking up to see if there is already an anagram in the map
            RBTree.get(ordered).add(original); //if there is, add the original word to the linked list
        } else {
            MyList<String> startList = new MyLinkedList<>(); //if not there, need to create a new list for a new ordered word
            startList.add(original);//adding new word to the linked list
            RBTree.put(ordered, startList);//creating a key value pair from the ordered word and new linked list
            // and putting it into the map
        }
    }

    /**
     * inserts a key( ordered string for lookup) value (linked list of all anagrams
     * of the key) pair into a Hash table. If the BST already has the
     * ordered string, there is an anagram and we lookup that key to find the
     * linked list where the anagram shall be inserted
     * @param original the original word we are checking for anagrams
     * @param ordered the ordered and lowercased version of the original word
     *                as the key for looking up if there is an anagram or not
     * @param Hash the Hash table we are looking up / adding to to store all the
     *               anagrams
     */
    public static void Hashinsert(String original, String ordered, MyMap<String, MyList<String>> Hash){
        if (Hash.get(ordered) != null) { //looking up to see if there is already an anagram in the map
            Hash.get(ordered).add(original); //if there is, add the original word to the linked list
        } else {
            MyList<String> startList = new MyLinkedList<>(); //if not there, need to create a new list for a new ordered word
            startList.add(original);//adding new word to the linked list
            Hash.put(ordered, startList);//creating a key value pair from the ordered word and new linked list
            // and putting it into the map
        }

    }
    /**
     * this method iterates through the given map to find the value (which is a linked list ) with the most
     * elements, representing the word(s) with the most anagrams, and returning the amount of anagrams
     * @param map the data structure we are using to check and store out anagrams
     * @return int largest anagram count for any word in the map
     */

    public static int MaxValueInt(MyMap<String, MyList<String>> map){
        Iterator<Entry<String, MyList<String>>> mapIt = map.iterator(); //creating iterator to iterate through map
        int max = 0;
        while (mapIt.hasNext()){
            Entry<String, MyList<String>> currentList = mapIt.next();
            int currentSize = currentList.value.size(); //getting size of each linked list in map
            if (currentSize > max){ //finding the max size aka number of anagrams for word with most anagrams
                max = currentSize;
            }

        }
        return max; //returning that number of anagrams


    }
    /**
     * This method creates a list that will store all the lists of anagrams that have the most anagrams.
     * it will take the max anagrams integer from the MaxValueInt method(called in main method) and
     * iterate through the map again, adding the linked list to this new list if the amount of anagrams
     * in such linkedlist is the greatest amount by any word
     * @param map the data structure we are using to check and store out anagrams
     * @return the list of all lists of max amount of anagrams
     */
    public static MyList<MyList<String>> ListOfMaxAnagrams(MyMap<String, MyList<String>> map, int max){
        MyList<MyList<String>> ListofMax = new MyLinkedList<>(); // creating the list of lists
        Iterator<Entry<String, MyList<String>>> mapIt2 = map.iterator(); //creating iterator to iterate the map
        while (mapIt2.hasNext()){
            Entry<String, MyList<String>> currentEntry = mapIt2.next();
            MyList<String> currentAnagramList = currentEntry.value; //getting every linkedlist to check if it has the max
                                                                    //amount of anagrams
            int currentSize = currentEntry.value.size(); //checking amount of anagrams in such linked list
            if (currentSize == max){ //checking if that amount indeed is the max or not
                ListofMax.add(currentAnagramList); // if it is, add it to the list of lists
            }

        }
        return ListofMax; //return the list of lists
    }

    /**
     * takes the list of all lists of the max anagrams and sorts it by the first word in every list
     * lexicographically for printing
     * @param ListOfMaxAnagrams the list of lists that store the anagrams for the words with the most anagrams
     */
    public static void insertionSort(MyList<MyList<String>> ListOfMaxAnagrams) {
        int n = ListOfMaxAnagrams.size();
        for (int i = 1; i < n; ++i) {
            int k = 0;
            MyList<String> currentList = ListOfMaxAnagrams.get(i);
            String currentString = currentList.get(0);
            // for loop to compare the strings in the list to see where to insert the lists to sort lexicographically
            for (k = i - 1; k >= 0 && ListOfMaxAnagrams.get(k).get(0).compareTo(currentString) > 0; --k) {
                ListOfMaxAnagrams.set(k+1, ListOfMaxAnagrams.get(k)); //making space to insert as seen in class
            }
            ListOfMaxAnagrams.set(k+1, currentList); //inserting
        }
    }


    /**
     * helper method for the main method to smoothly print out the lists of anagrams of words
     * with the most anagrams
     * @param ListOfMaxAnagrams the list of lists that stores the anagrams for the words with the most anagrams
     * @param AnagramCount the amount of anagrams the word(s) with the most has for printing purposes
     */
    public static void finalPrint(MyList<MyList<String>> ListOfMaxAnagrams, int AnagramCount){
        int n = ListOfMaxAnagrams.size(); //finding out how many words have the same amount of anagrams for printing
        String Groups = String.valueOf(n);
        String AnaCount = String.valueOf(AnagramCount);
        System.out.println("Groups: " + Groups + ", Anagram count: " + AnaCount); //printing the counts
        Iterator<MyList<String>> ListItr = ListOfMaxAnagrams.iterator(); //iterator to go through the list of lists
        while (ListItr.hasNext()){
            MyList<String> currentStringList = ListItr.next();
            int g = currentStringList.size();
            insertionSort(currentStringList, g); //sorting the individual words in a single linked list with max anagram count
            System.out.print("["); // starting the printing of the lists
            Iterator<String> finalItr = currentStringList.iterator(); //iterator to print every word in the lists
            while (finalItr.hasNext()){
                String finalPrint = finalItr.next();
                System.out.print(finalPrint); //printing the word
                if (finalItr.hasNext()){
                    System.out.print(", "); //printing "," if it is not the last element
                }

            }
            System.out.println("]"); //close out the print statement for a given list and new line



        }
        System.exit(0);
    }
    /**
     * takes an individual list of words to sort lexicographicall for printing
     * @param ListOfWords the list of words that need to be sorted for printing
     * @param n found n in the print method just to add a parameter as i was having
     *          problems overloading this insertionSort with another
     */
    public static void insertionSort(MyList<String> ListOfWords, int n) {
        for (int i = 1; i < n; ++i) {
            int k = 0;
            String currentString = ListOfWords.get(i);
            for (k = i - 1; k >= 0 && ListOfWords.get(k).compareTo(currentString) > 0; --k) {
                ListOfWords.set(k+1, ListOfWords.get(k));
            }
            ListOfWords.set(k+1, currentString);
        }
    }




    /**
     * Main method to take the input from the user in the command line to determine the
     * text file and data structure to use to find the max number of anagrams in a
     * given text file. performs a lot of the method calling in order to print the
     * list(s) of words with the most anagrams.
     * @param args is the command line arguments from the user to determine
     *             the file and data structure type
     */

    public static void main(String[] args) {
        if (args.length != 2){ //if the number of command line arguements is not 2, prints error message
            System.err.println("Usage: java MostAnagramsFinder <dictionary file> <bst|rbt|hash>");
            System.exit(1);

        }

        String filename = args[0];
        String type = args[1];
        File file = new File(filename);

        if (!file.exists()) { //if the file name given by user does not exist, prints error message
            System.err.println("Error: Cannot open file '" + args[0] + "' for input.");
            System.exit(1);
        }
        // if the data structure type given is invalid, print error message
        if (!type.equals("bst") && !type.equals("rbt") && !type.equals("hash") ) {
            System.err.println("Error: Invalid data structure '" + args[1] + "' received.");
            System.exit(1);
        }
        String path = file.getAbsolutePath(); //getting path to file for the file reader
        /**
         * Now that we confirm the text file and data structure type is valid, we can use the
         * specified data structure type to hold all the anagrams
         */

        if (type.equals("bst")){ //if specified type is Binary search tree
            MyMap<String, MyList<String>> BSTmap = new BSTreeMap<>(); //create the BST
            //bufferedreader to read through each line of the text file. each string(line) is converted to
            //lowercase and ordered to create the key for the map, and inserted into the map by calling the
            //BSTinsert method. if there is an IO exeption while reading, an error message is printed
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.length() != 1){
                        String lowerString = line.toLowerCase();
                        char[] charArray = lowerString.toCharArray();
                        String ordered = insertionSort(charArray);
                        BSTinsert(line, ordered, BSTmap);
                    }

                }
            } catch (IOException e) {
                System.err.println("Error: An I/O error occurred reading '" + args[0] + "'.");
                System.exit(1);
            }
            int biggestIntValue = MaxValueInt(BSTmap); //calling MaxValueInt to find the number of max anagrams
            //making sure that the value is above 2. if below, it means no words have anagrams and the
            //corresponding message is printed
            if (biggestIntValue < 2){
                System.out.println("No anagrams found.");
                System.exit(0);
            }
            //creating the lists of all lists of anagrams of words with the most anagrams
            MyList<MyList<String>> ListOfMaxAna = ListOfMaxAnagrams(BSTmap, biggestIntValue);
            insertionSort(ListOfMaxAna); //sorting that list for printing
            finalPrint(ListOfMaxAna, biggestIntValue); //calling the final print method to finish off the application


        } else if (type.equals("rbt")){ //if specified type is Red Black tree
            MyMap<String, MyList<String>> RBTmap = new RBTreeMap<>();//creating new RBT
            //bufferedreader to read through each line of the text file. each string(line) is converted to
            //lowercase and ordered to create the key for the map, and inserted into the map by calling the
            //RBTinsert method. if there is an IO exeption while reading, an error message is printed
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.length() != 1){
                        String lowerString = line.toLowerCase();
                        char[] charArray = lowerString.toCharArray();
                        String ordered = insertionSort(charArray);
                        RBTinsert(line, ordered, RBTmap);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error: An I/O error occurred reading '" + args[0] + "'.");
                System.exit(1);
            }
            int biggestIntValue = MaxValueInt(RBTmap);  //calling MaxValueInt to find the number of max anagrams
            //making sure that the value is above 2. if below, it means no words have anagrams and the
            //corresponding message is printed
            if (biggestIntValue < 2){
                System.out.println("No anagrams found.");
                System.exit(0);
            }
            //creating the lists of all lists of anagrams of words with the most anagrams
            MyList<MyList<String>> ListOfMaxAna = ListOfMaxAnagrams(RBTmap, biggestIntValue);
            insertionSort(ListOfMaxAna);//sorting that list for printing
            finalPrint(ListOfMaxAna, biggestIntValue);//calling the final print method to finish off the application

        } else { //if specified type is a Hash map
            MyMap<String, MyList<String>> Hashmap = new MyHashMap<>(); //creating hash table
            //bufferedreader to read through each line of the text file. each string(line) is converted to
            //lowercase and ordered to create the key for the map, and inserted into the map by calling the
            //BSTinsert method. if there is an IO exeption while reading, an error message is printed
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.length() != 1){
                        String lowerString = line.toLowerCase();
                        char[] charArray = lowerString.toCharArray();
                        String ordered = insertionSort(charArray);
                        Hashinsert(line, ordered, Hashmap);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error: An I/O error occurred reading '" + args[0] + "'.");
                System.exit(1);
            }
            int biggestIntValue = MaxValueInt(Hashmap);  //calling MaxValueInt to find the number of max anagrams
            //making sure that the value is above 2. if below, it means no words have anagrams and the
            //corresponding message is printed
            if (biggestIntValue < 2){
                System.out.println("No anagrams found.");
                System.exit(0);
            }
            //creating the lists of all lists of anagrams of words with the most anagrams
            MyList<MyList<String>> ListOfMaxAna = ListOfMaxAnagrams(Hashmap, biggestIntValue);
            insertionSort(ListOfMaxAna);//sorting that list for printing
            finalPrint(ListOfMaxAna, biggestIntValue);//calling the final print method to finish off the application
        }

    }

}
