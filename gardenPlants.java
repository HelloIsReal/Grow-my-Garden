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
    int sellPriceModifier=1;

    int gold=100;

    private ArrayList<String> inventory = new ArrayList<String>();
    Plant[][] plots = new Plant[3][3]; 

    public void newPlant(){
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Which plants in your inventory do you want to place? (type 'none' to exit)");
        printInventory();
        String userInput = keyboard.nextLine().toLowerCase();
        while(!inventory.contains(userInput) || userInput.equals("none")){
            System.out.println("Invalid Option, please try again!");
            userInput = keyboard.nextLine();
        }

        if(!userInput.equals("none")){
            Plant newPlant = new Plant("none",0);
        
            for(int i=0; i<inventory.size(); i++){
                // System.out.println(inventory.get(i));
                // System.out.println("User input: "+userInput);
                if (inventory.get(i).equals(userInput)){
                    System.out.println("found "+userInput);
                    // seedFound=true;
                    newPlant.setPlantType(inventory.get(i).toLowerCase());
                    inventory.remove(i);
                    newPlant.setPlantValue(10);

                    boolean findingPlot=true;
                    while(findingPlot){
                        System.out.println("Which plot to place in?");
                        checkGarden();
                        System.out.println("X value first, Y value after (each value seperately))");
                        while(!keyboard.hasNextInt()){
                            System.out.println("Invalid input");
                            keyboard.nextLine();
                        }
                        int plotX = keyboard.nextInt()-1;
                        while(!keyboard.hasNextInt()){
                            System.out.println("Invalid input");
                            keyboard.nextLine();
                        }
                        int plotY = keyboard.nextInt()-1;
                        if(plotX>=3 || plotY>=3){
                            System.out.println("Invalid plot point(s)!");
                        }else{
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
        }
    }

    public void buyPlant(){
        Scanner keyboard = new Scanner(System.in);

        String selectedSeed = "none";
        boolean validOption=false;

        while(!validOption){
            System.out.println("Which plant would you like to buy?  (type 'none' to exit)");
            for(int i=0; i<seeds.length; i++){
                System.out.println(seeds[i]+" - "+seedPrices[i]+"g");
            }
            selectedSeed = keyboard.nextLine();
            if(selectedSeed.equals("none")){
                validOption=true;
            }else{
                for(int i=0; i<seeds.length; i++){
                    if(seeds[i].equals(selectedSeed)){
                        validOption=true;
                        if(gold>=seedPrices[i]){
                            System.out.println("================================");
                            System.out.println("purchased a "+seeds[i]+" seed!");
                            System.out.println("-"+(seedPrices[i])+"g");
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
    }
    public void plantUpgrades(){
        System.out.println("Increase Prices, Cost: ");
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
        int plotX = keyboard.nextInt()-1;
        int plotY = keyboard.nextInt()-1;
        if(plots[plotX][plotY] != null){
            // Need to add checking for if the plant is fully grown.
            if(plots[plotX][plotY].getPlantState()>=3){
                if(plots[plotX][plotY].getPlantType().equals("mango")){

                }
                System.out.println(plots[plotX][plotY].getPlantType()+" Harvested!");
                plots[plotX][plotY] = null;
            }
        } else {
            System.out.println("This plot is already empty!");
        }

    }
    public void checkGarden(){
        System.out.print("#");
        for(int n=0; n<plots.length; n++){
            System.out.print(" "+(n+1));
        }
        System.out.println();
        for(int y=0; y<plots.length; y++){
            System.out.print(y+1+"|");

            for(int x=0; x<plots.length; x++){
                if(plots[x][y] == null){
                    System.out.print("X|");
                }else if(plots[x][y].getPlantType().equals("mango")){
                    System.out.print("M|");
                }else if(plots[x][y].getPlantType().equals("pepper")){
                    System.out.print("P|");
                }else if(plots[x][y].getPlantType().equals("strawberry")){
                    System.out.print("S|");
                }else if(plots[x][y].getPlantType().equals("tomato")){
                    System.out.print("T|");
                }else{
                    System.out.print("?|"); // Appears if the plant is a mystery type
                }
            }
            System.out.println("");
        }
        System.out.println("\nX = empty plot\n");
        for(int y=0; y<plots.length; y++){   
            for(int x=0; x<plots.length; x++){
                if(plots[x][y] != null){
                    plots[x][y].plantUpdate();
                }
            }
        }
    }

}
