
/**
 *
 * @author Sakshi Padhiar
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AccountingSystem {
    
     // an array of type Transaction
    static ArrayList<Transaction> transaction = new ArrayList<Transaction>();


    /**
     * method that takes information about type of transaction ("BUY" or "RET"),
     * generates required parameters and makes a transaction object to add
     * to the transaction array 
     * @param date
     * @param car
     * @param salesPerson
     * @param type
     * @param salePrice
     * @return string by calling the display mehtod of class Transaction
     */
    public String add(Calendar date, Car car, String salesPerson, String type, double salePrice){
        if(type.equalsIgnoreCase("BUY")){
            Random random = new Random() ;
            int id = random.nextInt(99) + 1;
            transaction.add(new Transaction(id, date, car, salesPerson, type, salePrice));
        }
        else{ 
            Transaction previousTransaction = getTransaction(transaction.get(transaction.size()-1).id);
            if(previousTransaction != null){
                previousTransaction.type = type; 
            }
            else{
                System.out.println("Transaction is NULL! There is nothing to return.");
            }
        }
        return transaction.get(transaction.size()-1).display();
    }
    
/**
     * searches through all transaction objects for matching id
     * once found, returns reference to it
     * otherwise returns null
     * @param id 
     * @return reference to transaction object (or null)
     */    public Transaction getTransaction(int id){
        for(int i = 0; i<transaction.size(); i++){
            if(id==transaction.get(i).id && transaction.get(i).type.equalsIgnoreCase("BUY")){
                return transaction.get(i);
            }
        }
        return null;
    }
    
    /**
     * method to all of the transactions of the year (2019 in this case)
     */    public void displaySales(){
        if(transaction.size()==0){
            System.out.println("There are no transactions.");
        }
        else{
            for(int i = 0; i < transaction.size(); i++){
                System.out.println(transaction.get(i).display()); 
            }
        }
    }
    
    /** 
     * method to Print the name of the the sales person who sold the most number of cars for the year, 
     * if there is a tie, print both. 
     */    
        public void displayTopSP(){
        if(transaction.size()==0){
            System.out.println("There are no transactions.");
        }
        else{
            ArrayList<String> salesPersonList = new ArrayList<String>();
            for(int i = 0; i < transaction.size(); i++){
                salesPersonList.add(transaction.get(i).salesPerson);
            }

            Map<String,Integer> countSP = new HashMap<>() ; 
            for (String salesPerson : salesPersonList) {  /* Counts the quantity of each element */
                if (! countSP.containsKey(salesPerson)) {             
                    countSP.put(salesPerson, 1 ) ; 
                }

                else {
                    int value = countSP.get(salesPerson) ; 
                    value++ ; 

                    countSP.put(salesPerson, value) ;
                }       
            }

            Map<String,Integer> mostCommons = new HashMap<>() ; 
            for ( Map.Entry<String,Integer> e : countSP.entrySet() ) {

                if (e.getValue() == Collections.max(countSP.values() )){ /* The max value of count  */
                    mostCommons.put(e.getKey(), e.getValue());
                }   
            }

            for(int i = 0; i < mostCommons.size(); i++){
                System.out.println("Top SP: "+mostCommons.keySet().toArray()[i]+" "+ mostCommons.values().toArray()[i]);
            }
        }
    }
    
    /**
     * Method to display the transaction for given month (e.g. 0 is January, Feb = 1 etc) 
     * @param month
    */    public void displayMonth(int month){
        if(month<0 || month >11){
            System.out.println("Month can't be less than 0 or greater than 11!");
        }
        else{ //month>=0 && month <= 11
            int count = 0;
            for(int i = 0; i<transaction.size(); i++){
                if(month==transaction.get(i).date.get(Calendar.MONTH)){
                    System.out.print(transaction.get(i).display());
                }
                else{
                    count++;
                }
            }
            if(count==transaction.size()){
                System.out.println("This month doesn't have any transactions.");
            }
        }
    }
    
    /**
     * method to print the statistics:
     * total sales for the year ($)
     * average sales per month ($)
     * total number of cars sold
     * the month with the highest number of cars sold
     * the total number of car returns
     * 
     */    public void displayStats(){
        double totalSalesPrice = 0;
        int totalSold = 0;
        for(int i = 0; i<transaction.size(); i++){
            if(transaction.get(i).type=="BUY"){
                totalSalesPrice+=transaction.get(i).salePrice;
                totalSold++;
            }
        }
        int avgSales = (int)totalSalesPrice/12;
        int totalReturned = transaction.size()-totalSold; //Subtracts the total with the sold ones to see which ones are returned
        
        ArrayList<String> monthList = new ArrayList<String>();
        for(int i = 0; i < transaction.size(); i++){
            Calendar date = transaction.get(i).date;
            SimpleDateFormat getMonthName = new SimpleDateFormat("MMM");
            String month = getMonthName.format(date.getTime()).toString();
            monthList.add(month);
        }
        
        Map<String,Integer> countMonths = new HashMap<>() ; 
        for (String months : monthList) {  /* Counts the quantity of each element */
            if (! countMonths.containsKey(months)) {             
                countMonths.put(months, 1 ) ; 
            }

            else {
                int value = countMonths.get(months) ; 
                value++ ; 

                countMonths.put(months, value) ;
            }       
        }

        Map<String,Integer> mostCommonMonth = new HashMap<>() ; 
        for ( Map.Entry<String,Integer> e : countMonths.entrySet() ) {

            if (e.getValue() == Collections.max(countMonths.values() )){ /* The max value of count  */
                mostCommonMonth.put(e.getKey(), e.getValue());
            }   
        }
        String bestMonth = "";
        for(int i = 0; i < mostCommonMonth.size(); i++){
            bestMonth += mostCommonMonth.keySet().toArray()[i]+" cars sold: "+mostCommonMonth.values().toArray()[i]+" ";
        }
        
        System.out.println("Total Sales: "+totalSalesPrice+" Total Sold: "+totalSold+" Avg Sales: "+avgSales+" Total Returned: "+totalReturned+" Best Month: "+bestMonth);
    }
}
