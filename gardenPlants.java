import java.util.ArrayList;

/**
 * Write a description of class Garden here.
 *
 * @author Tishar Sreekantam
 * @version Version 1
 */
import java.util.Scanner;
public class gardenPlants
{
    String[] seeds = {"strawberry", "tomato", "pepper", "mango"};
    int[] seedPrices = {5,10,15,50};

    int gold=100;

    private ArrayList<Plant> gardenPlants = new ArrayList<Plant>();
    private ArrayList<String> inventory = new ArrayList<String>();
    Plant[][] plots = new Plant[3][3]; 

    public gardenPlants(){
        System.out.println("garden created!");
    }

    public void newPlant(){
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Which plants in your inventory do you want to place? (type 'none' to exit)");
        printInventory();
        String userInput = keyboard.nextLine().toLowerCase();
        while(!inventory.contains(userInput) || userInput == "none"){
            System.out.println("Invalid Option, please try again!");
        }
        if(userInput !="none"){
            Plant newPlant = new Plant("none",0);
            // boolean seedFound=false;
            // boolean noSeed=false;
            // while(seedFound=false){

            // }
            // while(seedFound=false && noSeed==false){
            //     count++;
            for(int i=0; i<inventory.size(); i++){
                System.out.println("checking");
                if (inventory.get(i) == userInput){
                    
                    // seedFound=true;
                    newPlant.setPlantType(inventory.get(i).toLowerCase());
                    inventory.remove(i);
                    newPlant.setPlantValue(10);

                    boolean findingPlot=true;
                    while(findingPlot){
                        System.out.println("Which plot to place in?");
                        checkGarden();
                        System.out.println("X value first, Y value after (each value seperately))");
                        int plotX = keyboard.nextInt();
                        int plotY = keyboard.nextInt();
                        if(plots[plotX][plotY] == null){
                            plots[plotX][plotY] = newPlant; 
                            findingPlot=false;
                        } else {
                            System.out.println("That plot already has a plant in it!");
                        }
                    }

                }
            }
        }

        // Plant newPlant = new Plant("none",0);
        // System.out.println("What type of plant?");
        // newPlant.setPlantType(keyboard.nextLine().toLowerCase());
        // //String plantType = keyboard.nextLine();

        // System.out.println("Plant value?");
        // newPlant.setPlantValue(keyboard.nextInt());
        // //double plantValue = keyboard.nextDouble();

        // System.out.println("Which plot to place in?");
        // checkGarden();
        // System.out.println("X value first, Y value after (each value seperately))");
        // int plotX = keyboard.nextInt();
        // int plotY = keyboard.nextInt();
        // if(plots[plotX][plotY] == null){
        // plots[plotX][plotY] = newPlant; 
        // } else {
        //     System.out.println("That plot already has a plant in it!");
        // }
    }

    public void buyPlant(){
        Scanner keyboard = new Scanner(System.in);
        //List plants

        String selectedSeed = "none";
        boolean validOption=false;

        while(!validOption){
            System.out.println("Which plant would you like to buy?");
            for(int i=0; i<seeds.length; i++){
                System.out.println(seeds[i]+" - "+seedPrices[i]+"g");
            }
            selectedSeed = keyboard.nextLine();
            for(int i=0; i<seeds.length; i++){
                if(seeds[i].equals(selectedSeed)){
                    validOption=true;
                    if(gold>=seedPrices[i]){
                        System.out.println("================================");
                        System.out.println("purchased a "+seeds[i]+" seed!");
                        System.out.println("-"+(gold-seedPrices[i])+"g");
                        gold = gold - seedPrices[i];
                        inventory.add(seeds[i]);
                    } else {
                        System.out.println("insufficient gold!");
                    }
                }
            }
            if(!validOption){
                System.out.println("Invalid input, please enter a valid seed!\n");
            }
        }
    }

    public int getGold(){
        return(this.gold);
    }

    public void printInventory(){
        System.out.println("Your inventory currently is:");
        for(int i=0; i<inventory.size(); i++){
            System.out.println("- "+inventory.get(i));
        }
    }

    public void harvestPlant(){
        Scanner keyboard = new Scanner(System.in);
        checkGarden();
        System.out.print("which plant to harvest?");
        System.out.println("X value first, Y value after (each value seperately))");
        int plotX = keyboard.nextInt();
        int plotY = keyboard.nextInt();
        if(plots[plotX][plotY] != null){
            // Need to add checking for if the plant is fully grown.
            System.out.println(plots[plotX][plotY].getPlantType()+" Harvested!");
            plots[plotX][plotY] = null;
        } else {
            System.out.println("This plot is already empty!");
        }

    }

    public void checkGarden(){
        for(int y=0; y<plots.length; y++){
            System.out.print("|");
            for(int x=0; x<plots.length; x++){
                if(plots[x][y] == null){
                    System.out.print("X|");
                }else if(plots[x][y].getPlantType().equals("mango")){
                    System.out.print("M|");
                }else if(plots[x][y].getPlantType().equals("pepper")){
                    System.out.print("P|");
                }
                // if(this.plots[x][y] != null){
                // System.out.print(plots[x][y].getPlantType()+"|");
                // } else {
                // System.out.print("None|");
                // }   
            }
            System.out.println("");

        }
    }
}
