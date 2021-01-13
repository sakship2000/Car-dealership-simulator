/**
 * @author Sakshi Padhiar
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class CarDealershipSimulator 
{
    /**
	 * method to check if a file is valid
	 * @param givenFile
	 * @return boolean true or false
	 */

    public static boolean isFilenameValid(String file) {
        File f = new File(file);
        try {
           f.getCanonicalPath();
           return true;
        }
        catch (IOException e) {
           return false;
        }
    }
  public static void main(String[] args)
  {
	  // Create a CarDealership object
      CarDealership carDealership = new CarDealership();
         // Creates a Car object to check the reference.
      Car reference = null;
        //Create an AccountingSystem object 
      AccountingSystem accountingSystemObject = new AccountingSystem();
	  // Then create an (initially empty) array list of type Car
      ArrayList<Car> newCars = new ArrayList<Car>();

      String filename = "cars.txt";
        try{
            //check is the file name is valid
            if(!isFilenameValid(filename)){ 
                throw new FileNotFoundException(); 
            }
            else{
                Scanner readFile = new Scanner(new File(filename)) ;
                while (readFile.hasNextLine()) { 
                    String line = readFile.nextLine() ;
                    Scanner word = new Scanner(line);
                    while(word.hasNext()){
                    String mfr = word.next();
                    String colour = word.next();
                    String model = word.next();
                    String powerSource = word.next();
                    String safetyRating = word.next();
                    String maxRange = word.next();
                    String AWD = word.next();
                    String price = word.next();

                    if(!powerSource.equalsIgnoreCase("ELECTRIC_MOTOR")){
                        newCars.add(new Car(mfr, colour, Car.Model.valueOf(model), Vehicle.PowerSource.valueOf(powerSource), Double.parseDouble(safetyRating), Integer.parseInt(maxRange), Boolean.parseBoolean(AWD), Double.parseDouble(price)));
                    }
                    else{
                        String rechargeTime = word.next();
                        newCars.add(new ElectricCar(mfr, colour, Car.Model.valueOf(model), Vehicle.PowerSource.valueOf(powerSource), Double.parseDouble(safetyRating), Integer.parseInt(maxRange), Boolean.parseBoolean(AWD), Double.parseDouble(price), Integer.parseInt(rechargeTime)));
                    }
                 }
                }
                
	  // Create a scanner object
	  Scanner scan = new Scanner(System.in);
          //Initializes the input
          String input = "";
	      // this while loop checks if input is NOT 'Q' or 'q'
          while(!input.equalsIgnoreCase("Q")){
              System.out.print("\n>"); //Prints out '>' to indicate that this line is where user inputs command
	        //read the input line
              input=scan.nextLine();
	        //create another scanner object (call it "commandLine" or something) using the input line instead of System.in
              Scanner commandLine = new Scanner(input);
	         //read the next word from the commandLine scanner
              String readCommand = ""; 
              readCommand = commandLine.next();


            /**
            * if command is "L" impelemt the displayInventory method
            */
              if(readCommand.equalsIgnoreCase("L")){
                carDealership.displayInventory();
              }
			/**
            * else if command is "Q" or "QUIT" exit the program
              */ 
              else if(readCommand.equalsIgnoreCase("Q")){
                  System.out.print("See ya later!");
              }

            /**
			 * else if command is "BUY" implement buyCar() method
			 */

              else if(readCommand.equalsIgnoreCase("BUY")){
                try {
                    if(commandLine.hasNextInt()){
                        int index = commandLine.nextInt();
                        System.out.println(carDealership.buyCar(index));
                        
                    }
                    else{
                        throw new BadDataException("ERROR: VIN must be an integer");
                    }
                }  
                catch(BadDataException e){
                    System.out.println(e);
                }
              }
             /**
			 * else if command is "RET" implement the returnCar() method
			 */
             else if(readCommand.equalsIgnoreCase("RET")){
                //This whole try and catch loop is to check if the user entered an integer for 'BUY'
                try {
                    if(commandLine.hasNextInt()){
                       int transaction = Integer.parseInt(commandLine.next());
                       carDealership.returnCar(transaction);
                    }
                    else if(commandLine.hasNext()){
                        String message = "Invalid! Enter 'RET' alone or with a valid ID";
                        throw new BadDataException(message);
                    }
                    else if(accountingSystemObject.transaction.size()==0){
                        String message = "There are no transactions. Try again later";
                        throw new BadDataException(message);
                    }
                    else{ 
                        carDealership.returnCar(accountingSystemObject.transaction.get(accountingSystemObject.transaction.size()-1).id);
                    }             
                }
                catch(BadDataException e){
                    System.out.println(e);
                }
              }

            /**
            * else if command is "ADD" implement the addCars() method
            */            
            else if(readCommand.equalsIgnoreCase("ADD")){
                  carDealership.addCars(newCars);
                }

            /**
             * else if command is "SPR" implement the sortByPrice() method
             */              
            else if(readCommand.equalsIgnoreCase("SPR")){
                  carDealership.sortByPrice();
              } 

            /**
             * else if command is "SSR" implement the sortBySafetyRating() method
             */
            else if(readCommand.equalsIgnoreCase("SSR")){
                  carDealership.sortBySafetyRating();
              } 

            /**
             * else if command is "SMR" implement the sortyByMaxRange() method
             */
            else if(readCommand.equalsIgnoreCase("SMR")){
                  carDealership.sortByMaxRange();
              } 

            /**
             * else if command is "FPR" implement the filterByPrice(double minPrice, double maxPrice) method
             */
            else if(readCommand.equalsIgnoreCase("FPR")){
                  Double minPrice = Double.parseDouble(commandLine.next());
                  Double maxPrice = Double.parseDouble(commandLine.next());
                  carDealership.filterByPrice(minPrice, maxPrice);
              } 
            /**
             * else if command is "FEL" implement the filterByElectric() method
             */
            else if(readCommand.equalsIgnoreCase("FEL")){
                  carDealership.filterByElectric();
              } 

            /**
             * else if command is "FAW" implement the filterByAWD() method
             */
            
            else if(readCommand.equalsIgnoreCase("FAW")){
                  carDealership.filterByAWD();
              } 

            /**
             * else if command is "FCL" implement the filtersClear() method 
             */                                                                     
              else if(readCommand.equalsIgnoreCase("FCL")){
                  carDealership.filtersClear();
              } 
              
            /**
             * else if command is "SALES" prints all the transactions of the year
             * as long as there is nothing after it
             * if there is, implement that specific command
             */
              else if(readCommand.equalsIgnoreCase("SALES")){
                  if(commandLine.hasNextInt()){ 
                      int month = commandLine.nextInt();
                      accountingSystemObject.displayMonth(month);
                  }
                  
                  else if(commandLine.hasNext()){ 
                    readCommand = commandLine.next();
                    // if command is "SALES TEAM" implement displayTeam() method
                    if(readCommand.equalsIgnoreCase("TEAM")){
                        SalesTeam salesTeamObject = new SalesTeam();
                        System.out.println(salesTeamObject.displayTeam());
                    }
                    // if command is "SALES TOPSP" implement displayTopSP() method
                    else if(readCommand.equalsIgnoreCase("TOPSP")){
                        accountingSystemObject.displayTopSP();
                    }
                    // if command is "STATS" implement displayStats() method
                    else if(readCommand.equalsIgnoreCase("STATS")){
                        accountingSystemObject.displayStats();
                    }
                    else{
                        System.out.println("Invalid command");
                    }
                  }
                  //if the command is only "SALES" print transactions of the year
                  else{ 
                    accountingSystemObject.displaySales();
                  }
              }
              else{
                  System.out.println("Invalid Command. Enter again.");
              }
	 } 
       }
       }
       /**
        * catches any exception in the try clause
        */
       catch(Exception e){
	    System.out.println(e);
        }
  }
}

/**
 * class BadDataException
 * extends IOException
 */
class BadDataException extends IOException
{
    public BadDataException() {}
    public BadDataException(String message) 
    {
	super(message) ;
    }
}