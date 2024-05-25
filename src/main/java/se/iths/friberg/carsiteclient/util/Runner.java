package se.iths.friberg.carsiteclient.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.iths.friberg.carsiteclient.menu.MainMenu;

@Component
public class Runner implements CommandLineRunner{

    private final MainMenu mainMenu;

    public Runner(MainMenu mainMenu){
        this.mainMenu = mainMenu;
    }

    @Override
    public void run(String... args) throws Exception{
        mainMenu.display();
    }
}
