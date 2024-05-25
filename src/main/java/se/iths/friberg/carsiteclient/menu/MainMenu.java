package se.iths.friberg.carsiteclient.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.iths.friberg.carsiteclient.io.IO;

@Component
public class MainMenu{

    private IO io;
    private final ManageCarMenu manageCarMenu;
    private final SearchCarMenu searchCarMenu;

    @Autowired
    public MainMenu(IO io,
                    ManageCarMenu manageCarMenu,
                    SearchCarMenu searchCarMenu){
        this.io = io;
        this.manageCarMenu = manageCarMenu;
        this.searchCarMenu = searchCarMenu;
    }

    public void display(){
        boolean keepLooping = true;
        while (keepLooping){
            io.println("""
                    
                    Main Menu:
                    1. Search for cars.
                    2. Manage cars.
                    0. Exit.""");
            io.print("Choose an option: ");
            String choice = io.nextLine().trim();

            switch (choice){
                case "1" -> searchCarMenu.display();
                case "2" -> manageCarMenu.display();
                case "0" -> keepLooping = false;
                default -> io.println("\nInvalid option. Please try again.");
            }
        }
        io.println("\nExiting application...");
    }
}