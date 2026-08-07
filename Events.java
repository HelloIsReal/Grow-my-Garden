import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Write a description of class Garden here.
 */
public class Events
{
    private LocalDateTime eventTimer;
    private final int weatherDelay;
    public Events(){
       this.eventTimer = LocalDateTime.now();
       this.weatherDelay=5;
    }

    public String startEvent(){
        int rng = (int)(Math.random() * 301);
        String event="";
    
        

        if(Duration.between(eventTimer,LocalDateTime.now()).toMinutes() > weatherDelay){
            eventTimer=LocalDateTime.now();
        // Frozen = 5x (Replaces wet if gotten, happens on freezing rainy days. 1/4 chance to replace rainy days)
        // Wet = 2x (Happens during rainy days, common)
        // Electrified = 4x (Happens during thunderstorms.)
        // Gold = 20x (happens during rare golden days. Basically a day where stuff has a chance to become golden)
        // Eclipsed = 20x (happens during solar eclipses)
        // Sandy = 3x (gotten during sandstorms)
        if (rng == 1){
            event="golden"; // the whole sky is gold.
        } else if (rng>=2 && rng<=10){
            event="eclipse"; // eclipse event, the sun is blocked by the moon
        } else if (rng>=11 && rng <=50){
            event="sandy"; // dessert like sand in the wind
        } else if (rng>=51 && rng<= 71){
            event="electrified"; // thunderstorm
        } else {
            if ((int)(Math.random() * 4) == 1){
                event="frozen"; // rainy event variant, gives stronger modifier
            } else {
                event="wet"; // rainy event
            }
        }
        }
        return(event);
    }   
    public void forecastCheck(){
        System.out.println("*You turn on the TV*");
        System.out.println("'blah blah blah blah'\n...");
        System.out.println(weatherDelay-Duration.between(eventTimer,LocalDateTime.now()).toMinutes()+" minutes till the next weather event");
    }
}