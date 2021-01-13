/**
 *
 * @author Sakshi Padhiar
 */


//Class Car inherets all variables from Vehicle and also implements a Comparable later on
public class Car extends Vehicle implements Comparable<Car>{ 

    /**
     * classifies the different models of cars
    */
    public static enum Model{
        SEDAN, 
        SUV, 
        SPORTS, 
        MINIVAN; 
    }
    

    /**
     * declaring the instance varibles of the class Car
     */
     int maxRange;
     double safetyRating;
     boolean AWD;
     double price;
     Model model; // public as they need to be accesssed by CarDealershipSimulator.java

    /**
     * constructor method to initialize model of type Model enum
     */
    public Car(){
        this.model = Model.SEDAN;
    }
     

    /**
     * constructor method that initializes instance variables of class Car
     * as well as the inhereted varaibles from the superclass Vehicle
     * @param mfr
     * @param color
     * @param model
     * @param power
     * @param safety
     * @param range
     * @param awd
     * @param price
     */

    public Car(String mfr, String color, Model model, Vehicle.PowerSource power, double safety, int range, boolean awd, double price){
        super(mfr, color, 4, power);
        this.model = model;
        safetyRating = safety;
        maxRange = range;
        AWD = awd;
        this.price = price;
    }
    
    /**
     * sets the maximum range to given range
     * @param maxRange maximum range
     */
    public void setMaxRange(int maxRange){
        this.maxRange = maxRange;
    }

    /**
     * accesses maximum range of the car 
     * @return maximum range
     */
    public int getMaxRange(){
        return maxRange;
    }
    
    /**
     * sets the safety rating to given rating
     * @param safetyRating safety rating
     */
    public void setSafetyRating(double safetyRating){
        this.safetyRating = safetyRating;
    }

    /**
     * accesses safety rating of the car 
     * @return safety rating
     */
    public double getSafetyRating(){
        return safetyRating;
    }
    
    /**
     * sets the all wheel drive staus to true or false
     * @param AWD All Wheel Drive
     */
    public void setAWD(boolean AWD){
        this.AWD = AWD;
    }

    /**
     * accesses the variable that determines if the car has all wheel drive or not 
     * @return AWD(All Wheel Drive) boolean true or false
     */
    public boolean getAWD(){
        return AWD;
    }
    
    /**
     * sets the price to given price
     * @param price price
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * accesses price of the car 
     * @return price
     */
    public double getPrice(){
        return price;
    }
    
    /**
     * @Override of the inhereted display() method in superclass Vehicle
     * uses super.display() to call super method of the same name
     * @return the string of appended instance varaibles from class Car
     */
    public String display(){
        return super.display() + " " + model + " " + price + "$ SF: " + safetyRating + " RNG: " + maxRange;
    }
    
    /**
     * @Override of the inhereted equals() method in superclass Vehicle
     * compares manufacturer, power and number of wheels by calling the super.equals() method 
     * also compares equality of the models and AWD status from the Car methods
     * @param other of type Object
     * @return boolean true or false
     */
    public boolean equals(Object other){
        Car otherCar = (Car)other;
        if (super.equals(other) && this.model == otherCar.model && this.AWD == otherCar.AWD){
    	  return true;
        }
        else{
    	  return false;
        }  
    }

    /**
     * public interface compareTo is the comparable (as implemented during declaration of class Car)
     * compares price of two cars as required by the interface
     * @param other of type Object
     * @return 1 if this price is greater than the other price
              -1 if this price is less than the other price
	           0 if they are the same.
     */
    public int compareTo(Car other){  

        if(this.price > other.price){
            return 1;
        }
        else if (this.price < other.price){
            return -1;
        }
        else{ 
            return 0;
        }
    }
}
