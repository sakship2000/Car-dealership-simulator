/**
 *
 * @author Sakshi Padhiar
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transaction {
    
    //declare instance variables
    int id; 
    Calendar date; 
    Car reference;
    String salesPerson;
    String type;
    double salePrice;
    
    /**
     * constructor method to initialize all instance variables
     * @param id
     * @param date
     * @param carReference
     * @param salesPerson
     * @param type
     * @param salePrice
     */
    
        public Transaction(int id, Calendar date, Car reference, String salesPerson, String type, double salePrice){
        this.id = id;
        this.date = date;
        this.reference = reference;
        this.salesPerson = salesPerson;
        this.type = type;
        this.salePrice = salePrice;
    }
    
    /**
     * display method to show a string of the id, date, transaction type, sales person
     * and the car (by calling the display method from class car)
     * @return appended string
     */
     public String display(){
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd, yyyy"); 
        return "ID: "+id+" "+sdf.format(date.getTime())+" "+type+" SalesPerson: "+salesPerson+" Car: "+reference.display();
    }

    Object get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
