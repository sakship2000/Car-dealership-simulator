/**
 *
 * @author Sakshi Padhiar
 */

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class SalesTeam {
    
    // Linked list to hold sales team names
    LinkedList<String> team;

    /**
     * constructor method to initialize LinkedList object 
     * then add sales team memebers names
     */
    public SalesTeam(){
        team = new LinkedList<String>();
        team.add("Jamie");
        team.add("Monse");
        team.add("Cesar");
        team.add("Veronica");
        team.add("Hiram");
        team.add("Rodrigues");
    }
    
    /**
     * method to randomly pick a sales person from linked list
     * using the random type
     * @return sales person string
     */ 
    public String randomSalesPerson(){
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(team.size());
        return team.get(randomInt);
    }
    
    /**
     * display method to show all the sales persons names in a string
     * @return appended string
     */
    public String displayTeam(){
        String result = "Team: ";
        ListIterator<String> iterator = team.listIterator();
        while(iterator.hasNext()){
            result = result + iterator.next() + " ";
        }
        return result;
    }

        /**
     * method to get the sales person at given index
     * as long as index is within bounds of the linked list
     * @param index
     * @return 
     */
    public String getSalesPerson(int index){
        String result = "";
        if (index >= 0 && index < team.size() - 1){
            result = team.get(index);
        }
        else{
            result = "Invalid Argument"; 
        }
        return result;
    }
}
