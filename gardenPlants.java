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
    private ArrayList<Plant> gardenPlants = new ArrayList<Plant>();
    Plant[][] plots = new Plant[3][3]; 
    public gardenPlants(){
        System.out.println("garden created!");
    }

    public void newPlant(){
        Scanner keyboard = new Scanner(System.in);
        Plant newPlant = new Plant("none",0);

        System.out.println("What type of plant?");
        newPlant.setPlantType(keyboard.nextLine().toLowerCase());
        //String plantType = keyboard.nextLine();

        System.out.println("Plant value?");
        newPlant.setPlantValue(keyboard.nextInt());
        //double plantValue = keyboard.nextDouble();

        System.out.println("Which plot to place in?");
        printGarden();
        System.out.println("X value first, Y value after (each value seperately))");
        int plotX = keyboard.nextInt();
        int plotY = keyboard.nextInt();
        if(plots[plotX][plotY] == null){
        plots[plotX][plotY] = newPlant; 
        } else {
            System.out.println("That plot already has a plant in it!");
        }
    }

    public void harvestPlant(){
        Scanner keyboard = new Scanner(System.in);
        printGarden();

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

    public void printGarden(){
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
