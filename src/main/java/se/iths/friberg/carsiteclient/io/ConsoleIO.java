package se.iths.friberg.carsiteclient.io;

import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class ConsoleIO implements IO{
    private final Scanner scanner;

    public ConsoleIO(){
        this.scanner = new Scanner(System.in);
    }
    @Override
    public void print(Object o){
        System.out.print(o);
    }

    @Override
    public void println(Object o){
        System.out.println(o);
    }
    @Override
    public void println(){
        System.out.println();
    }

    @Override
    public String next(){
        return scanner.next();
    }

    @Override
    public String nextLine(){
        return scanner.nextLine();
    }
    
    @Override
    public Integer nextInt(){
        int i;
        try{
            i = scanner.nextInt();
            scanner.nextLine();
            return i;
        }catch(InputMismatchException e){
            scanner.nextLine();
            return null;
        }
    }

    @Override
    public int validNextInt(int min, int max){
        int input;
        while(true){
            try{
                input = scanner.nextInt();
                if(input >= min && input <= max){
                    scanner.nextLine();
                    break;
                }else{
                    System.out.printf("Please enter an integer between %d and %d.", min, max);
                    System.out.println();
                }
            }catch(InputMismatchException e){
                scanner.nextLine();
                System.out.println("Error: Please enter a valid number.");
            }
        }
        return input;
    }
    
}
