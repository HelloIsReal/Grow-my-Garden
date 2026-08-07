
import java.time.*; // time package used for time in game
/**
 * Grow my Garden is a farming/time management simulator about growing plants
 * The user is given 7 different options that all do different things,
 * but all connect back to growing, harvesting, selling, and purchasing more plants.
 *
 * Tishar Sreekantam
 * Version 1
 */
import java.util.Scanner;
public class Main
{
    public static void main(String args[]){
        Scanner keyboard = new Scanner(System.in); // used for picking up keyboard input
        final int REFRESHLENGTH = 100; // This is how many blank messages are sent to clear previous console history
        boolean inGame=true; // repeats as long as the game is running


        // Creates garden and events classes
        gardenPlants Garden = new gardenPlants(); 
        Events events = new Events();

        System.out.println("\n================================");
        System.out.println("Welcome to Grow my Garden!");

        while(inGame){ // runs as long as this variable is true
            System.out.println("================================");
            System.out.println("What would you like to do?");
            System.out.println("You currently have "+Garden.getGold()+"g"); // Gets the player's gold from garden class
            System.out.println("================================");

            System.out.println("1) View my Garden");
            System.out.println("2) Plant a new crop using seeds");
            System.out.println("3) Harvest and sell plant from my Garden");
            System.out.println("4) Purchase plant seeds");
            System.out.println("5) Garden upgrades");
            System.out.println("6) View inventory");
            System.out.println("7) Check the forecast");
            System.out.println("================================");

            // This updates the weather in order for events to start, and plants to start receiving modifiers.
            String currentEvent = events.startEvent();
            if(currentEvent!=""){
                System.out.println("The weather is currently "+currentEvent+"!");
                Garden.modifierTick(currentEvent);
            }else{
                System.out.println("The weather seems clear and sunny, for now.");
            }
            
            
            while(!keyboard.hasNextInt()){
                System.out.println("Invalid input, please enter the number of an option\n");
                keyboard.next();
            }
            int userOption = keyboard.nextInt();
            keyboard.nextLine();

            switch (userOption){
                case 1: // Shows the current user garden
                    refreshScreen(REFRESHLENGTH);
                    Garden.checkGarden();
                    break;
                case 2: // Plants a new plant into the garden
                    refreshScreen(REFRESHLENGTH);
                    Garden.newPlant();
                    break;
                case 3: // Harvests a plant from the garden
                    refreshScreen(REFRESHLENGTH);
                    Garden.harvestPlant();
                    break;
                case 4: // Opens the shop and allows the user to purchase seeds to plant
                    refreshScreen(REFRESHLENGTH);
                    Garden.buyPlant();
                    break;
                case 5: // Opens the upgrades menu to allow the user to upgrade their garden
                    refreshScreen(REFRESHLENGTH);
                    Garden.plantUpgrades();
                    break;
                case 6: // Prints out all the seeds the user has in their inventory
                    refreshScreen(REFRESHLENGTH);
                    Garden.printInventory();
                    break;
                case 7: // Shows when the next upcoming weather is.
                    refreshScreen(REFRESHLENGTH);
                    events.forecastCheck();
                    break;
                default:
                    System.out.println("Invalid option. Try again\n"); // Invalid option
            }
        }
    }

    public static void refreshScreen(int length){ // Clears old history in console.
        for(int i=0; i<length; i++){
            System.out.println();
        }
    }
}