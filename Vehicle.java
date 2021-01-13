/**
 *
 * @author Sakshi Padhiar 
 */

import java.util.Random;

public class Vehicle {

    /**
     * classifies the different types of engines
     */

    public static enum PowerSource{
        GAS_ENGINE, 
        DIESEL_ENGINE, 
        ELECTRIC_MOTOR;
    }
    

    /**
     * declaring instance variables
     */
    String mfr; 
    String color; 
    public PowerSource power; // public as it needs to be accesssed by CarDealershipSimulator.java
    int numWheels;
    
    int VIN; // vehicle identification number
    int maxVIN = 499; //highest possible VIN
    int minVIN = 100; //lowest possible VIN
    Random rand = new Random(); 

    /**
     * constructor method to initialize manufacturer using an empty string
     */
    public Vehicle()
	{
		this.mfr = "";
	}

     /**
     * constructor method to initialize instance variables
     * @param mfr manufacturer name
     * @param color vehicle color
     * @param numWheels number of wheels
     * @param power power
     */

    public Vehicle(String mfr, String color, int numWheels, PowerSource power){
        this.mfr = mfr;
        this.color = color;
        this.numWheels = numWheels;
        this.power = power;
        this.VIN = rand.nextInt((maxVIN - minVIN) + 1) + minVIN; // random number generator
    }
    
    /**
     * accesses the Vehicle Identification Number 
     * @return VIN
     */
    public int getVIN(){
        return VIN;
    }

    /**
     * sets Vehicle Identification Number to given VIN
     * @param VIN
     */
    public void setVIN(int VIN){
        this.VIN = VIN;
    }

    /**
     * sets manufacturer to given manufacturer
     * @param mfr manufacturer
     */
    public void setMfr(String mfr){
        this.mfr = mfr;
    }

    /**
     * accesses name of manufacturer 
     * @return manufacturer
     */
    public String getMfr(){
        return mfr;
    }
    
    /**
     * sets color to given color
     * @param color 
     */
    public void setColor(String color){
        this.color = color;
    }

    /**
     * accesses color 
     * @return color
     */
    public String getColor(){
        return color;
    }
    
    /**
     * sets power to given power
     * @param power 
     */
    public void setPower(PowerSource power){
        this.power = power;
    }

    /**
     * accesses power 
     * @return power
     */
    public PowerSource getPower(){
        return power;
    }
    
    /**
     * sets numWheels to given number of wheels
     * @param numWheels 
     */
    public void setNumWheels(int numWheels){
        this.numWheels = numWheels;
    }

    /**
     * accesses number of wheels 
     * @return numWheels
     */
    public int getNumWheels(){
        return numWheels;
    }
    
    /**
     * boolean equals method compares manufaturer, power and number of wheels of the 
     * parameter vehicle to the current vehicle
     * @param other of type Object
     * @return boolean true or false
     */
    public boolean equals(Object other){
        Vehicle otherVehicle = (Vehicle) other ; //casting object to type vehicle
	    return power == otherVehicle.power && mfr.equals(otherVehicle.mfr) && numWheels == otherVehicle.numWheels;
    }


    /**
     * method to display vehicle identification number, manucaturer and color variables
     * @return appended string of all variables
     */
    public String display(){
        return "VIN: " + VIN + " " + mfr + " " + color;
    }          
}
