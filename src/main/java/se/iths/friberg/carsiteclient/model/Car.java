package se.iths.friberg.carsiteclient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "regNr",
        "model",
        "year"
})

public class Car{

    @JsonProperty("regNr")
    private String regNr;
    @JsonProperty("model")
    private String model;
    @JsonProperty("year")
    private Integer year;

    public Car(String regNr, String model, Integer year){
        this.regNr = regNr;
        this.model = model;
        this.year = year;
    }
    public Car(Car car){
        this.regNr = car.getRegNr();
        this.model = car.getModel();
        this.year = car.getYear();
        
    }
    
    @JsonProperty("regNr")
    public String getRegNr() {
        return regNr;
    }

    @JsonProperty("regNr")
    public void setRegNr(String regNr) {
        this.regNr = regNr;
    }

    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    @JsonProperty("model")
    public void setModel(String model) {
        this.model = model;
    }

    @JsonProperty("year")
    public Integer getYear() {
        return year;
    }

    @JsonProperty("year")
    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString(){
        return "Car{" +
                "regNr='" + regNr + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}