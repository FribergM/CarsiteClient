package se.iths.friberg.carsiteclient.util;

import se.iths.friberg.carsiteclient.model.Car;

import java.time.Year;

public class Validator{
    
    private static final String REG_NR_PATTERN = "^[A-Za-z]{3}[0-9]{3}$|^[A-Za-z]{3}[0-9]{2}[A-Za-z]$";
    private static final String MODEL_PATTERN = "^[A-Za-z]{3,20}$";
    public static final int CURRENT_YEAR = Year.now().getValue();
    public static final int YEAR_OF_FIRST_CAR = 1886;
    
    public static boolean isValidRegNr(String regNr) {
        return regNr != null && regNr.matches(REG_NR_PATTERN);
    }
    
    public static boolean isValidModel(String model) {
        return model != null && model.matches(MODEL_PATTERN);
    }
    
    public static boolean isValidYear(Integer year) {
        return year != null && year >= YEAR_OF_FIRST_CAR && year <= CURRENT_YEAR;
    }
    
    public static boolean isValidCar(Car car) {
        return isValidRegNr(car.getRegNr()) && isValidModel(car.getModel()) && isValidYear(car.getYear());
    }
    
}
