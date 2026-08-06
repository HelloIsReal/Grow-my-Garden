import java.time.*;
import java.util.ArrayList;
/**
 * Write a description of class Plant here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Plant
{
    private String plantType;
    private double plantValue;
    
    private int currentState=0;
    private int[] growthTime = {1,2,3,5,15};
    private String[] growthStages = {"Seedling","Sprouting","Ripening","Mature","Wilted"};
    private String plantState;
    
    // Frozen = 10x (Replaces wet if gotten, happens on freezing rainy days. 1/4 chance to replace rainy days)
    // Wet = 2x (Happens during rainy days, common)
    // Electrified = 3x (Happens during thunderstorms.)
    // Gold = 20x (happens during rare golden days. Basically a day where stuff has a chance to become golden)
    // Eclipsed = 20x (happens during solar eclipses)
    // Sandy = 3x (gotten during sandstorms)

    private ArrayList<String> plantModifiers = new ArrayList<String>();

    // used for checking when the plant was planted, and when it's grown.
    private LocalDateTime plantedTime;

    public Plant(String plantType, double plantValue){
        plantedTime = LocalDateTime.now();
        this.plantType = plantType;
        this.plantValue = plantValue;
        this.plantState = growthStages[0];
    }

    public void setPlantType(String plantType){
        this.plantType = plantType;
    }

    public void setPlantValue(double plantValue){
        this.plantValue = plantValue;
    }

    public LocalDateTime getPlantedDate(){
        return(this.plantedTime);
    }

    public String getPlantType(){
        return(this.plantType);
    }

    public double getPlantValue(){
        return(this.plantValue);
    }

    public void addModifier(String modifier){
        plantModifiers.add(modifier);
    }

    public void getModifiers(){
        for(int i=0; i<plantModifiers.size(); i++){
            System.out.println(plantModifiers.get(i));
        }
    }
    public int getPlantState(){
        return(currentState);
    }

    public void plantUpdate(){
        Duration duration = Duration.between(plantedTime,LocalDateTime.now());

        System.out.print(duration.toMinutes()+" minutes old, ");
        //getPlantStage();
        //System.out.println(getPlantStage());
        if(duration.toMinutes()>=growthTime[currentState] && currentState<=growthStages.length){
            currentState++;
            //plantState = growthStages[currentState];
        }
        System.out.println(plantType+" is "+growthStages[currentState-1]+"!");
        if(currentState>=4){
            System.out.println("This plant is also ready to be harvested!\n");
        }
        // for(int i=currentState; duration.toMinutes()>=growthTime[i]; currentState++){
        //     System.out.println("New plant state!!");
        //     plantState = growthStages[i];

        // }

        // for(int i=0; i>growthStages.length; i++){
        //     if(duration.toMinutes()>growthTime[i]){
        //         plantState = growthStages[i];
        //         //System.out.println(getPlantStage());
        //     }
        // }
    }
}