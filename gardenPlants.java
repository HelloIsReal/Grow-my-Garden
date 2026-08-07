import java.util.ArrayList;

/**
 * Write a description of class Garden here.
 */
import java.util.Scanner;
public class gardenPlants
{

    String[] seeds = {"strawberry", "tomato", "pepper","carrot","mango"};
    int[] seedPrices = {5,10,15,25,50};
    double sellPriceMultiplier=1;
    double sellPriceCost=40;

    double gold=100;

    private ArrayList<String> inventory = new ArrayList<String>();
    Plant[][] plots = new Plant[3][3]; 

    public void newPlant(){
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Which plants in your inventory do you want to place? (type 'none' to exit)");
        printInventory();
        String userInput = keyboard.nextLine().toLowerCase();
        while(!inventory.contains(userInput) && !userInput.equals("none")){
            System.out.println("Invalid Option, please try again!");
            userInput = keyboard.nextLine();
        }

        if(!userInput.equals("none")){
            Plant newPlant = new Plant("none",0);
        
            for(int i=0; i<inventory.size(); i++){
                if (inventory.get(i).equals(userInput)){
                    System.out.println("found "+userInput);
                    // seedFound=true;
                    newPlant.setPlantType(inventory.get(i).toLowerCase());
                    inventory.remove(i);
                    if(newPlant.getPlantType() == "strawberry"){
                        newPlant.setPlantValue(20);
                    }else if(newPlant.getPlantType() == "tomato"){
                        newPlant.setPlantValue(25);
                    }else if(newPlant.getPlantType() == "pepper"){
                        newPlant.setPlantValue(40);
                    }else if(newPlant.getPlantType() == "carrot"){
                        newPlant.setPlantValue(60);
                    }else if(newPlant.getPlantType() == "mango"){
                        newPlant.setPlantValue(125);
                    }
                    

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
        Scanner keyboard = new Scanner(System.in);
        System.out.println("1) Increase Prices, Cost: "+sellPriceCost);
        while(!keyboard.hasNextInt()){
            System.out.println("Invalid input");
            keyboard.nextLine();
        }
        int userOption = keyboard.nextInt();

        if(userOption==1){
            if(gold>=sellPriceCost){
                gold -= sellPriceCost;
                sellPriceMultiplier = sellPriceMultiplier * 1.3;
                sellPriceCost = sellPriceCost*1.6;
                System.out.println("Increased plant sell price! ("+sellPriceMultiplier+"x Mult)");
            }
        }else{
            System.out.println("Invalid option");
        }
    }

    public void modifierTick(String newEvent){
        for(int y=0; y<plots.length; y++){
            for(int x=0; x<plots.length; x++){
                // Frozen = 5x (Replaces wet if gotten, happens on freezing rainy days. 1/4 chance to replace rainy days)
                // Wet = 2x (Happens during rainy days, common)
                // Electrified = 4x (Happens during thunderstorms.)
                // Gold = 20x (happens during rare golden days. Basically a day where stuff has a chance to become golden)
                // Eclipsed = 20x (happens during solar eclipses)
                // Sandy = 3x (gotten during sandstorms)
                if(plots[x][y] != null){
                    if(newEvent=="wet"){
                        if(plots[x][y].getMultiplier()<2){
                            plots[x][y].setMultiplier(2);
                        }
                    }else if(newEvent=="frozen"){
                        if(plots[x][y].getMultiplier()<5){
                            plots[x][y].setMultiplier(5);
                        }
                    }else if(newEvent=="golden"){
                        if(plots[x][y].getMultiplier()<20){
                            plots[x][y].setMultiplier(20);
                        }
                    }else if(newEvent=="eclipse"){
                        if(plots[x][y].getMultiplier()<20){
                            plots[x][y].setMultiplier(20);
                        }
                    }else if(newEvent=="sandy"){
                        if(plots[x][y].getMultiplier()<3){
                            plots[x][y].setMultiplier(3);
                        }
                    }else if(newEvent=="electrified"){
                        if(plots[x][y].getMultiplier()<4){
                            plots[x][y].setMultiplier(4);
                        }
                    }
                    System.out.println(plots[x][y].getPlantType()+" has gotten the modifier "+newEvent+"!");
                }
                
            }
        }
    }



    public double getGold(){
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
        keyboard.nextLine();
        if(plots[plotX][plotY] != null){
            // Need to add checking for if the plant is fully grown.
            if(plots[plotX][plotY].getPlantState()>=3){
                if(plots[plotX][plotY].getPlantType().equals("mango")){

                }
                System.out.println(plots[plotX][plotY].getPlantType()+" Harvested!");
                gold += plots[plotX][plotY].getPlantValue() * sellPriceMultiplier;
                plots[plotX][plotY] = null;
            }else{
                System.out.println(plots[plotX][plotY].getPlantType()+" is not yet mature!");
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
                }else if(plots[x][y].getPlantType().equals("carrot")){
                    System.out.print("C|");
                }else{
                    System.out.print("?|"); // Appears if the plant doesn't exist.
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
