package se.iths.friberg.carsiteclient.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.iths.friberg.carsiteclient.client.CarWebClient;
import se.iths.friberg.carsiteclient.io.IO;
import se.iths.friberg.carsiteclient.model.Car;
import se.iths.friberg.carsiteclient.util.Validator;
import static se.iths.friberg.carsiteclient.util.Validator.YEAR_OF_FIRST_CAR;
import static se.iths.friberg.carsiteclient.util.Validator.CURRENT_YEAR;

@Component
public class ManageCarMenu{

    private final IO io;
    private final CarWebClient client;

    @Autowired
    public ManageCarMenu(IO io, CarWebClient client){
        this.io = io;
        this.client = client;
    }
    
    public void display(){
        boolean keepLooping = true;
        while (keepLooping){
            io.println("""
                    
                    Car Management Menu:
                    1. Create car.
                    2. Remove car.
                    3. Update car.
                    0. Back to main menu.""");
            io.print("Choose an option: ");
            String choice = io.nextLine().trim();
            
            switch(choice){
                case "1" -> createCar();
                case "2" -> removeCar();
                case "3" -> updateCar();
                case "0" -> keepLooping = false;
                default -> io.println("\nInvalid option. Please try again.");
            }
        }
    }
    
    private void createCar(){
        io.println("""
                
                Car registration
                ----------------""");
        
        String regNr = getRegNr();
        
        if(regNr.equals("0")){
            return;
        }
        
        String model = getModel();
        
        if(model.equals("0")){
            return;
        }
        
        int year = getYear();
        
        if(year == 0){
            return;
        }
        
        Car createdCar = client.registerNewCar(new Car(regNr,model,year));
        if(createdCar == null){
            io.println("\n"+regNr + " is already registered!");
        }else{
            io.println("\n"+createdCar.getRegNr() + " has been registered!");
        }
        
    }

    private void removeCar(){
        io.println("""
                
                Car Removal
                ----------------""");
        
        String regNr = getRegNr();
        
        if(regNr.equals("0")){
            io.println("\nCancelled.");
            return;
        }
        
        Car car = client.findByRegNr(regNr);
        if(car == null){
            io.println("\nNo car found.");
            return;
        }
        
        client.removeCar(car.getRegNr());
        io.println("\n"+car.getRegNr()+" has been removed");
    }

    private void updateCar(){
        io.println("""
                
                Car Update
                ----------------""");
        
        String regNr = getRegNr();
        
        if(regNr.equals("0")){
            io.println("\nCancelled.");
            return;
        }
        
        Car car = client.findByRegNr(regNr);
        if(car == null){
            io.println("\nNo car found.");
            return;
        }
        
        Car updatedCar = updateMenu(car);
        
        if(updatedCar.getModel().equals(car.getModel()) && updatedCar.getYear().equals(car.getYear())){
            return;
        }
        
        updatedCar = client.updateCar(regNr,updatedCar);
        
        if(updatedCar != null){
            io.println("\nBefore update:");
            io.println(car);
            io.println("After update:");
            io.println(updatedCar);
        }
        
        
    }
    
    public Car updateMenu(Car car){
        boolean keepLooping = true;
        while (keepLooping){
            io.println(String.format("""
                    
                    Update menu for: %s
                    1. Update model.
                    2. Update year.
                    0. Finish update.""", car.getRegNr()));
            io.print("Choose an option: ");
            String choice = io.nextLine().trim();
            
            switch(choice){
                case "1" -> car = updateModel(new Car(car));
                
                case "2" -> car = updateYear(new Car(car));
                
                case "0" -> keepLooping = false;
                default -> io.println("\nInvalid option. Please try again.");
            }
        }
        return car;
    }
    
    private Car updateModel(Car car){
        String model = getModel();
        
        if(model.equals("0")){
            return car;
        }
        car.setModel(model);
        return car;
    }
    
    private Car updateYear(Car car){
        int year = getYear();
        
        if(year == 0){
            return car;
        }
        car.setYear(year);
        return car;
    }
    
    private String getRegNr(){
        String regNr;
        io.print("\nEnter registration number (enter '0' to cancel): ");
        do{
            regNr = io.nextLine().toUpperCase();
            
            if(regNr.equals("0")){
                io.println("\nCancelled.");
                return "0";
            }
            
            if(!Validator.isValidRegNr(regNr)){
                io.println("\nEnter a valid registration number. E.g. \"ABC123\"/\"ABC12A\" (enter '0' to cancel)");
            }
        }while(!Validator.isValidRegNr(regNr));
        
        return regNr.trim();
    }
    
    private String getModel(){
        String model;
        io.print("\nEnter model (enter '0' to cancel): ");
        do{
            model = io.nextLine().toUpperCase();
            
            if(model.equals("0")){
                io.println("\nCancelled.");
                return "0";
            }
            
            if(!Validator.isValidModel(model)){
                io.println("\nEnter a valid car model. Only letters. 3-20 letters. (enter '0' to cancel)");
            }
        }while(!Validator.isValidModel(model));
        
        return model;
    }
    
    private int getYear(){
        Integer year;
        io.print("\nEnter year (enter '0' to cancel): ");
        do{
            year = io.nextInt();
            
            if(year != null && year == 0){
                io.println("\nCancelled.");
                return 0;
            }
            
            if(!Validator.isValidYear(year)){
                io.println("\nEnter a valid year ("+YEAR_OF_FIRST_CAR+"-"+CURRENT_YEAR+")(enter '0' to cancel).");
            }
            
        }while(!Validator.isValidYear(year));
        
        return year;
    }
}