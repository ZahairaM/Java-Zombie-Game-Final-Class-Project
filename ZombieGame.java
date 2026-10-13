import java.awt.*;
import java.awt.event.KeyEvent;

public class ZombieGame {
    private SimpleCanvas canvas;
    private boolean isVisible;

    private GameObject player;
    private Zombie zombie;
    private PowerUp powerUp;
    private int speed;
    private int score;

    public ZombieGame()
    {
        isVisible = false;
        canvas = new SimpleCanvas(600, 400, "Turn off the alarm clock!");

        speed=10;
        score = 0;
        player = new GameObject();
        player.setLocation(new Location(300,200));
        player.setImageFilename("person.png");

        zombie = new Zombie();
        zombie.setLocation(new Location(50,50));

        powerUp = new PowerUp();
        powerUp.setLocation(new Location  (500,300));

    }

    /** Draw the state of the game on the canvas. */
    public void draw()
    {
        canvas.clear(); // always clear first.
        canvas.drawImage(0,0,"grass.png",600,400);


        // Draw all the game objects
        player.draw(canvas);
        zombie.draw(canvas);
        powerUp.draw(canvas);


        // Draw status message
        canvas.setPenColor(Color.BLACK);
        canvas.drawString(300, 10, "Score:"+score+"Speed:"+speed);

        // Show the window if needs be.
        if (!isVisible) {
            canvas.show();
            isVisible = true;
        }
        else {
            canvas.update();
        }
    }

    /** Is the game over? */
    private boolean isGameOver() {
        return player.overlaps(zombie);
    }

    /** Start the game running. */
    public void runGame()
    {
        // Create starting screen
        draw();
        canvas.setPenColor(Color.BLACK);
        canvas.drawString(10, 10, "Click canvas to start!");
        canvas.update();
        canvas.waitForClick();

        // Loop while the game is not over:
        while (!isGameOver()) {
            // update the state of the game
            zombie.chase(player);
            if (player.overlaps(powerUp)) {
                score++;
                if (speed < 20) {
                    speed = speed + powerUp.getBoost();
                }

                int newX = (int)(Math.random() * 550) + 25;
                int newY = (int)(Math.random() * 350) + 25;

                powerUp.setLocation(new Location(newX, newY));

            }

            // draw the state of the game
            draw();

            // handle keyboard
            handleKeyboard();

            // handle mouse
            handleMouse();

            canvas.pause(50); // can adjust this higher or lower as needed
        }
        // Game is now over,
        // draw the state of the game one last time in case there were any last updates
        // that we need to show.
        draw();

        canvas.setPenColor(Color.RED);
        canvas.drawStringCentered(300,200,"GAME OVER",20);
        canvas.update();
    }

    private void handleKeyboard() {
        Location myLocation = player.getLocation();
        if (canvas.isKeyPressed(KeyEvent.VK_UP)) {
            myLocation.setY(myLocation.getY() - speed);
        }
        if (canvas.isKeyPressed(KeyEvent.VK_DOWN)) {
            myLocation.setY(myLocation.getY() + speed);
        }
        if (canvas.isKeyPressed(KeyEvent.VK_LEFT)) {
            myLocation.setX(myLocation.getX() - speed);
        }
        if (canvas.isKeyPressed(KeyEvent.VK_RIGHT)) {
            myLocation.setX(myLocation.getX() + speed);
        }
        if (canvas.isKeyPressed(KeyEvent.VK_LEFT)) {
            myLocation.setX(myLocation.getX() - speed);
        }
        if (canvas.isKeyPressed(KeyEvent.VK_RIGHT)) {
            myLocation.setX(myLocation.getX() + speed);
        }
        if (myLocation.getX() < 25) {
            myLocation.setX(25);
        }
        if (myLocation.getX() > 575) {
            myLocation.setX(575);
        }
        if (myLocation.getY() < 25) {
            myLocation.setY(25);
        }
        if (myLocation.getY() > 375) {
            myLocation.setY(375);
        }

    }

    private void handleMouse() {
        if (canvas.isMousePressed()) {
            System.out.println("Mouse click detected at " + canvas.getMouseClickX() + " " + canvas.getMouseClickY());
        }
    }
}
