import java.time.*;
import java.util.ArrayList;

/**
 * Write a description of class Garden here.
 */
public class Plant
{

    // the plant type, plant value, growth stage, time to grow to each stage, and the stages.
    private String plantType;
    private double plantValue;
    
    private int currentState=0;
    private int[] growthTime = {2,3,5,15,60};
    private String[] growthStages = {"Seedling","Sprouting","Ripening","Mature","Wilted"};
    //private String plantState;
    
    // Frozen = 10x (Replaces wet if gotten, happens on freezing rainy days. 1/4 chance to replace rainy days)
    // Wet = 2x (Happens during rainy days, common)
    // Electrified = 3x (Happens during thunderstorms.)
    // Gold = 20x (happens during rare golden days. Basically a day where stuff has a chance to become golden)
    // Eclipsed = 20x (happens during solar eclipses)
    // Sandy = 3x (gotten during sandstorms)

    private String plantModifier;
    private int plantMultiplier;

    // used for checking when the plant was planted, and when it's grown.
    private LocalDateTime plantedTime;

    public Plant(String plantType, double plantValue){
        plantedTime = LocalDateTime.now();
        this.plantType = plantType;
        this.plantValue = plantValue;
        //this.plantState = growthStages[0];
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

    public void setModifier(String modifier){
        this.plantModifier = modifier;
    }

    public String getModifiers(){
        return(this.plantModifier);
    }
    public int getMultiplier(){
        return(this.plantMultiplier);
    }
    public void setMultiplier(int newMult){
        this.plantMultiplier = newMult;
    }

    public int getPlantState(){
        return(currentState);
    }

    public void plantUpdate(){ // This shows how old the plant is, and it's current growth stage to the user.
        Duration duration = Duration.between(plantedTime,LocalDateTime.now());

        System.out.print(duration.toMinutes()+" minutes old, ");
        //getPlantStage();
        //System.out.println(getPlantStage());
        while(duration.toMinutes()>=growthTime[currentState] && currentState<growthStages.length-1){
            currentState++;
            //plantState = growthStages[currentState];
        }
        System.out.println(plantType+" is "+growthStages[currentState]+"!");
        if(currentState>=4){
            System.out.println("This plant is also ready to be harvested!\n");
        }
    }
}