import java.awt.*;
public class Zombie extends GameObject{
    private int speed;

    public Zombie(){
        speed= 5;
        setImageFilename("zombie.png");
        setBorderColor(Color.BLACK);
    }
    public void chase(GameObject player){
        if(player.getCenterX()> getCenterX()){
            setLocationX(getCenterX()+speed);
        }else{
            setLocationX(getCenterX()-speed);
        }
        if (player.getCenterY()>getCenterY()){
            setLocationY(getCenterY()+speed);
        }else{
            setLocationY(getCenterY()-speed);
        }
    }
}
