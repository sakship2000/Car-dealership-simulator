
/**
 *
 * @author Sakshi Padhiar
 */


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Random;

public class CarDealership {
    
    //create an instance variable of type arraylist
    private ArrayList<Car> cars;
    
    SalesTeam salesTeamObject = new SalesTeam() ;
    AccountingSystem accountingSystemObject = new AccountingSystem() ;
    
    // declaring instance variables of class CarDealership
    private boolean filterByElectric;
    private boolean filterByAWD;
    private boolean filterByPrice;
    
    private double minPrice;
    private double maxPrice;

    /**
     * constructor method for arrayList cars, sales team 
     * object and accoutning system object
     */
    public CarDealership(){
        cars = new ArrayList<Car>();
    }
    
    /**
     * method to add desired cards to CarDealership object
     * @param newCars of type ArrayList<Car>
     */
    public void addCars(ArrayList<Car> newCars){
        cars = newCars;
    }

    /**
     * method to buy desired car using VIN
     * if VIN or the array of cars is 0, throws exception
     * @param VIN 
     * @return transaction receipt
     */
    public String buyCar(int VIN){ 
        Car reference = null;
        int count = 0;
        //This 'try' and 'catch' below is to validate if the user entered a valid VIN (A2 - Question 3b)
        try { 
            for(int i = 0; i<cars.size(); i++){
                if(VIN == cars.get(i).getVIN()){
                    reference = cars.get(i);
                    cars.remove(cars.get(i));
                    
                    String salesPerson = salesTeamObject.randomSalesPerson();

                    Random random = new Random() ;
                    int month = random.nextInt(12); 
                    int day = random.nextInt(31)+1; 

                    Calendar date = new GregorianCalendar(2019, month, day);
                    return accountingSystemObject.add(date, reference, salesPerson, "BUY", reference.getPrice());
                }
                else{
                    count++;
                }
            }
            if(count == cars.size() || count==0 || cars.size()==0){
                String message = "Your VIN is invalid or there are zero cars in the list!";
                throw new IllegalArgumentException(message);
            }
        } 
        catch (IllegalArgumentException e) { 
            return e.toString(); 
        }
        
        return null; 
    }
    
    /**
     * method to return a car to dealership using transaction id -- adding car back to arraylist   
     * generates a random date later than date of purchase (but in the same month)
     * throws exception if there is nothing to return or the id is invalid
     * @param transaction of type 
     */
    public void returnCar(int transaction){ 
        try { 
            Transaction buyTransaction = accountingSystemObject.getTransaction(transaction);
            if(buyTransaction == null){
                String message = "The transaction ID '"+transaction+"' is invalid or is already returned!";
                throw new IllegalArgumentException(message);
            }
            System.out.println(buyTransaction.display()); 
            Calendar buyDate = buyTransaction.date;
            Car reference = buyTransaction.reference;
            Random random = new Random() ;
            int dayReturned;

            if(buyDate.get(Calendar.MONTH)==1){ 
                dayReturned = random.nextInt(28-buyDate.get(Calendar.DATE)+1) + buyDate.get(Calendar.DATE); 
            }
            else if(buyDate.get(Calendar.MONTH)==3 || buyDate.get(Calendar.MONTH)==5 || buyDate.get(Calendar.MONTH)==8 || buyDate.get(Calendar.MONTH)==10){ 
                dayReturned = random.nextInt(30-buyDate.get(Calendar.DATE)+1) + buyDate.get(Calendar.DATE); 
            }
            else{
                dayReturned = random.nextInt(31-buyDate.get(Calendar.DATE)+1) + buyDate.get(Calendar.DATE); 
            }
            Calendar dateReturned = new GregorianCalendar(2019, Calendar.MONTH, dayReturned);
            accountingSystemObject.add(dateReturned, reference, buyTransaction.salesPerson, "RET", reference.getPrice());
            cars.add(reference);
        }
        catch (IllegalArgumentException e) { 
            System.out.println(e);
        }
    }
    
    /**
     * method to display the inventory of cars currently in the dealership
     * uses filter methods to filter out deisred cars by electric, price and/or AWD
     * @return filtered cars
     */
    public void displayInventory(){
        System.out.println("");
	  
	  for (int i = 0; i < cars.size(); i++)
	  {
		Car car = cars.get(i);
		
		if (filterByPrice && (car.getPrice() < minPrice || car.getPrice() > maxPrice))
		   continue;
		
		if (filterByElectric && car.power != Vehicle.PowerSource.ELECTRIC_MOTOR)
		   continue;
		
		if (filterByAWD && !car.getAWD())
		   continue;
		
		System.out.println(""+ i + " " + car.display());
	  }
	  System.out.println("");
  }
    
    /**
     * method to filter car by its electric status
     * sets eletric flag to true
     */    public void filterByElectric(){
        filterByElectric = true;
    }
    
    /**
     * method to filter car by its All Wheel Drive status
     * sets AWD flag to true
     */
    public void filterByAWD(){
        filterByAWD = true;
    }
    
    /**
     * method to filter car by price range
     * sets price flag to true
     * @param min minimum price desired
     * @param max maximum price desired
     */
    public void filterByPrice(double minPrice, double maxPrice){
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        filterByPrice = true;
    }
    
    /**
     * clears any filters placed on the car
     * by setting all flags to false
     */
    public void filtersClear(){
        filterByElectric = false;
        filterByAWD = false;
        filterByPrice = false;
    }
    
    /**
     * sorts cars by price in ascending order
     * using collections.sort()
     */
    public void sortByPrice(){
        Collections.sort(cars);
    }

    /**
     * sorts cars by safety rating is descending order
     * uses comparator and collections.sort()
     */
    public void sortBySafetyRating(){
        Collections.sort(cars, new SafetyRatingComparator());
    }
    
    /**
     * private comparator that compares safety rating of two given cars
     * using a compare(Object a,Object b) method
     * @param a of type Car
     * @param b of type Car
     * @return 1 if Car a has a higher safety rating than Car b
              -1 if Car a has a lower safety rating than Car b
	           0 if they have the same safety rating
     */
    private class SafetyRatingComparator implements Comparator<Car>{
        public int compare(Car a, Car b){
            if(a.getSafetyRating()<b.getSafetyRating()){return 1;}
            else if(a.getSafetyRating()>b.getSafetyRating()){return -1;}
            else {return 0;}
        }
    }

    /**
     * private comparator that compares maximum range of two given cars
     * using a compare(Object a,Object b) method
     * @param a of type Car
     * @param b of type Car
     * @return 1 if Car a has a greater maximum range than Car b
              -1 if Car a has a lower maximum range than Car b
	           0 if they have the same maximum range
     */
    private class RangeComparator implements Comparator<Car>{
        public int compare(Car a, Car b){
            if(a.getMaxRange()<b.getMaxRange()){return 1;}
            else if(a.getMaxRange()>b.getMaxRange()){return -1;}
            else {return 0;}
        }
    }

    /**
     * sorts cars by maximum range is descending order
     * uses comparator and collections.sort()
     */
    public void sortByMaxRange(){
        Collections.sort(cars, new RangeComparator());
    }
    
    
}
