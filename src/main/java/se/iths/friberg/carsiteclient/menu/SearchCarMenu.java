package se.iths.friberg.carsiteclient.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import se.iths.friberg.carsiteclient.client.CarWebClient;
import se.iths.friberg.carsiteclient.io.IO;
import se.iths.friberg.carsiteclient.model.Car;

import java.util.List;

@Component
public class SearchCarMenu{

    private final IO io;
    private final CarWebClient client;

    @Autowired
    public SearchCarMenu(IO io, CarWebClient client){
        this.io = io;
        this.client = client;
    }

    public void display(){
        boolean keepLooping = true;
        while(keepLooping){
            io.println("""
                    
                    Search Cars Menu:
                    1. Search for all cars.
                    2. Search by registration number.
                    3. Search by model.
                    4. Search by year.
                    0. Back to main menu.""");
            io.print("Choose an option: ");
            String choice = io.nextLine().trim();

            switch(choice){
                case "1" -> searchAll();
                case "2" -> searchByRegNr();
                case "3" -> searchByModel();
                case "4" -> searchByYear();
                case "0" -> keepLooping = false;
                default -> io.println("\nInvalid option. Please try again.");
            }
        }
    }

    private void searchAll(){
        io.println("\nSearching for all cars.");
        io.println();
        try{
            List<Car> allCars = client.findAllCars();
            
            if(allCars.isEmpty()){
                io.println("No cars found.");
            }else{
                for(Car c : allCars){
                    io.println(c);
                }
            }
        }catch(WebClientRequestException e){
            io.println("[ERROR] Unknown Server error.");
        }
    }

    private void searchByRegNr(){
        io.print("\nEnter registration number: ");
        String regNr = io.nextLine().toUpperCase().trim();
        io.println("\nSearching for car with registration number: " + regNr);
        io.println();
        
        try{
            Car foundCar = client.findByRegNr(regNr);
            if(foundCar == null){
                io.println("No car found.");
            }else{
                io.println(foundCar);
            }
        }catch(WebClientRequestException e){
            io.println("[ERROR] Unknown Server error.");
        }
    }

    private void searchByModel(){
        io.print("\nEnter model: ");
        String model = io.nextLine().trim();
        io.println("\nSearching for cars of model: " + model);
        io.println();
        
        try{
            List<Car> foundCars = client.findByModel(model);
            if(foundCars.isEmpty()){
                io.println("No cars found.");
            }else{
                for(Car c : foundCars){
                    io.println(c);
                }
            }
        }catch(WebClientRequestException e){
            io.println("[ERROR] Unknown Server error.");
        }
    }

    private void searchByYear(){
        io.print("\nEnter year: ");
        int year = io.validNextInt(0,9999);
        io.println("\nSearching for cars from year: " + year);
        io.println();
        
        try{
            List<Car> foundCars = client.findByYear(year);
            if(foundCars.isEmpty()){
                io.println("No cars found.");
            }else{
                for(Car c : foundCars){
                    io.println(c);
                }
            }
        }catch(WebClientRequestException e){
            io.println("[ERROR] Unknown Server error.");
        }
    }
}