
import java.time.*;
/**
 * 
 *
 * Tishar Sreekantam
 * Version 1
 */
import java.util.Scanner;
public class Main
{
    public static void main(String args[]){
        Scanner keyboard = new Scanner(System.in);
        final int REFRESHLENGTH = 100;
        boolean inGame=true;

        gardenPlants Garden = new gardenPlants();
        Events events = new Events();

        System.out.println("\n================================");
        System.out.println("Welcome to Grow my Garden!");

        while(inGame){
            System.out.println("================================");
            System.out.println("What would you like to do?");
            System.out.println("You currently have "+Garden.getGold()+"g");
            System.out.println("================================");

            System.out.println("1) View my Garden");
            System.out.println("2) Plant a new crop using seeds");
            System.out.println("3) Harvest and sell plant from my Garden");
            System.out.println("4) Purchase plant seeds");
            System.out.println("5) Garden upgrades");
            System.out.println("6) View inventory");
            System.out.println("7) Check the forecast");
            System.out.println("================================");
            String currentEvent = events.startEvent();
            if(currentEvent!=""){
                System.out.println("The weather is currently "+currentEvent+"!");
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
                case 1:
                    refreshScreen(REFRESHLENGTH);
                    Garden.checkGarden();
                    break;
                case 2:
                    refreshScreen(REFRESHLENGTH);
                    Garden.newPlant();
                    break;
                case 3:
                    refreshScreen(REFRESHLENGTH);
                    Garden.harvestPlant();
                    break;
                case 4:
                    refreshScreen(REFRESHLENGTH);
                    Garden.buyPlant();
                    break;
                case 5:
                    refreshScreen(REFRESHLENGTH);
                    Garden.plantUpgrades();
                    break;
                case 6:
                    refreshScreen(REFRESHLENGTH);
                    Garden.printInventory();
                    break;
                case 7:
                    refreshScreen(REFRESHLENGTH);
                    events.forecastCheck();
                    break;

                case 9:
                    System.out.println(events.startEvent());
                    break;
                default:
                    System.out.println("Invalid option. Try again\n");
            }
        }
    }

    public static void refreshScreen(int length){
        for(int i=0; i<length; i++){
            System.out.println();
        }
    }
}