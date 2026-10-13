import java.awt.*;
public class PowerUp extends GameObject{
    private int boost;

    public PowerUp(){
        setImageFilename("powerup.png");
        setBorderColor(Color.YELLOW);
    }
    public int getBoost(){
        return boost;
    }
}
