package se.iths.friberg.carsiteclient.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import se.iths.friberg.carsiteclient.model.Car;

import java.time.Duration;
import java.util.List;

@Component
public class CarWebClient{

    private final WebClient client = WebClient.create("http://localhost:8080");
    
    public Car registerNewCar(Car car){
        try{
            return client
                    .post()
                    .uri("/rs/cars")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(car)
                    .retrieve()
                    .bodyToMono(Car.class)
                    .block(Duration.ofSeconds(5));
        }catch(Exception e){
            return null;
        }
    }

    public List<Car> findAllCars(){
    
        return client
                .get()
                .uri("/rs/cars/all")
                .retrieve()
                .bodyToFlux(Car.class)
                .collectList()
                .block(Duration.ofSeconds(5));
    }

    public Car findByRegNr(String regNr){
        try{
            
            return client
                    .get()
                    .uri("/rs/cars/{regnr}", regNr)
                    .retrieve()
                    .bodyToMono(Car.class)
                    .block(Duration.ofSeconds(5));
        }catch(Exception e){
            return null;
        }
    }
    public List<Car> findByModel(String model){

        return client
                .get()
                .uri("/rs/cars/model/{model}", model)
                .retrieve()
                .bodyToFlux(Car.class)
                .collectList()
                .block(Duration.ofSeconds(5));
    }
    public List<Car> findByYear(int year){

        return client
                .get()
                .uri("/rs/cars/year/{year}", year)
                .retrieve()
                .bodyToFlux(Car.class)
                .collectList()
                .block(Duration.ofSeconds(5));
    }
    
    public Car updateCar(String regNr, Car updatedCar){
        try{
            return client
                    .put()
                    .uri("/rs/cars/{regNr}", regNr)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(updatedCar)
                    .retrieve()
                    .bodyToMono(Car.class)
                    .block(Duration.ofSeconds(5));
        }catch(Exception e){
            return null;
        }
    }
    public void removeCar(String regNr){
        try{
            client
                    .delete()
                    .uri("/rs/cars/{regNr}", regNr)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
