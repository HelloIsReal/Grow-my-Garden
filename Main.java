
/**
 * Write a description of class Main here.
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
        boolean menu=true;
        boolean inGame=true;
        boolean debug=true;

        
        gardenPlants Garden = new gardenPlants();
        Events events = new Events();

        System.out.println("Welcome to 'Grow my garden'! \n");
        while(menu){
            System.out.println("\n#) Load Save (W.I.P)");
            System.out.println("1) New Save");
            System.out.println("2) Settings");

            while(!keyboard.hasNextInt()){
                System.out.println("Invalid input, please enter the number of an option");
                keyboard.next();
            }

            int userOption = keyboard.nextInt();
            keyboard.nextLine();
            switch (userOption){
                case 1:
                    System.out.println("Starting new save!");
                    // Insert function code here to create a new save file.
                    menu=false;
                    break;
                case 2:
                    System.out.println("No settings available yet");
                    break;
                default:
                    System.out.println("Invalid option. Try again");
            }
        }
        System.out.println("\n================================");
        System.out.println("Welcome to Grow my Garden!");

        while(inGame){
            System.out.println("================================");
            System.out.println("What would you like to do?");
            System.out.println("You currently have "+Garden.getGold()+"g");
            System.out.println("================================");

            System.out.println("1) Go to my Garden");
            System.out.println("2) Purchase plant seeds");
            System.out.println("3) Plant a new crop using seeds");
            System.out.println("4) Harvest a plant from my garden");
            System.out.println("5) View inventory");
            System.out.println("================================");

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
                    Garden.buyPlant();
                    break;
                case 3:
                    refreshScreen(REFRESHLENGTH);
                    Garden.newPlant();
                    break;
                case 4:
                    refreshScreen(REFRESHLENGTH);
                    Garden.harvestPlant();
                    break;
                case 5:
                    refreshScreen(REFRESHLENGTH);
                    Garden.printInventory();
                    
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