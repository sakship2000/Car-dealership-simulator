/**
 *
 * @author Sakshi Padhiar
 */
//ElectricCar is a subclass of Car, allowing it to inheret from Car and Vehicle
public class ElectricCar extends Car{ 

    /**
     * declaring instance variables of class ElectricCar
     */
    int rechargeTime;
    String batteryType; 

    /**
     * default constructor method for ElectricCar
     * @param mfr
     * @param color
     * @param model
     * @param power
     * @param safety
     * @param range
     * @param awd
     * @param price
     * @param recharge
     */
    public ElectricCar(String mfr, String color, Model model, PowerSource power, 
    double safety, int range, boolean awd, double price, int recharge){
        super(mfr, color, model, power, safety, range, awd, price);
	  rechargeTime = recharge;
	  batteryType = "Lithium";
    }

    /**
     * @Override of the inhereted display() method in superclass Car
     * uses super.display() to call super method of the same name
     * @return the string of appended instance varaibles from class ElectricCar
     */
    public String display(){
        return super.display() + " EL, BAT: " + batteryType + " RCH: " + rechargeTime;
    }
}
