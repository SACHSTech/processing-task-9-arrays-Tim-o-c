import processing.core.PApplet;

public class Sketch extends PApplet {
	
	
  /**
   * A game in which a character must avoid falling snowballs, but you can click to remove them
   * @author: T. Chhor
   */
  public void settings() {
    size(1600, 800);
  }

  // Initializing Variables and Arrays
  float[] circleY = new float[100];
  boolean[] hideBallStatus = new boolean[circleY.length];
  boolean aPressed = false;
  boolean sPressed = false;
  boolean wPressed = false;
  boolean dPressed = false;
  boolean pPressed = false;
  boolean upPressed = false;
  boolean downPressed = false;
  boolean heroDmg = true;
  int heroX = 200;
  int heroY = 350;
  int heroLives = 7;
  int dmgChecker = 0;
  float circleX;

  public void setup() {

    // Setting up both arrays
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = -random(height);
      hideBallStatus[i] = false;
    }

  }

  public void draw() {

      // Resets the background
      fill(255, 255, 255);
      background(0);
      rect(0, height * 8 / 10, width, height);

      // For loop for printing snow, with all related mechanisms of the snow
      for (int i = 0; i < circleY.length; i++) {
        
        // Gives the snow an X coordinate
        circleX = width * i / circleY.length;
        fill(255, 255, 255);

        // Draws the snowball as long as the snowball is supposed to be shown
        if (hideBallStatus[i] == false){
          ellipse(circleX, circleY[i], 30, 30);
          }

        // Making the snowball disappear if clicked
        if (mousePressed) {
         for (int j = 0; j < circleY.length; j++) { 
            if (mouseY < circleY[j] + 15 && mouseY > circleY[j] - 15 && mouseX < width * j / circleY.length + 15 && mouseX > width * j / circleY.length - 15){
              hideBallStatus[j] = true;
            }
          }
        }

        // Controlling snow speed
        if (keyPressed) {
          if (upPressed) {
            circleY[i] += 1;
          }
          else if (downPressed) {
            circleY[i] += 4;
          }
          else {
          circleY[i] += 2;
          }
        }
        else {
        circleY[i] += 2;
        }
        if (circleY[i] > height) {
          circleY[i] = 0;
        }

        // Takes a life away if the character is damaged
        dmgChecker = 0;
        for (int j = 0; j < circleY.length; j++) {
          if (heroY < circleY[j] + 15 && heroY > circleY[j] - 15 && heroX < width * j / circleY.length + 15 && heroX > width * j / circleY.length - 15 && !hideBallStatus[j]) {
            dmgChecker += 1;
          }
        }
        if (dmgChecker > 0 && heroDmg) {
          heroLives--;
          heroDmg = false;
        }
        else if (dmgChecker <= 0) {
          heroDmg = true;
        }
        for (int j = 0; j < circleY.length; j++) {
          if (heroY > circleY[j] + 15 && heroY < circleY[j] - 15 && heroX > width * j / circleY.length + 15 && heroX < width * j / circleY.length - 15) {
            heroDmg = true;
          }
        }

        // Draws the hero and amount of lives on the screen
        fill(100, 100, 100);
        ellipse(heroX, heroY, 10, 10);
        textSize(20);
        text("Lives: " + heroLives, 100, 100);
      }
      
      // Displays the amount of lives
      for (int k = 0; k < heroLives + 1; k++) {
        rect(k * 50, 5, k * 30 + 15, 50);
      }
      
      // Screen goes white when all lives are lost
      if (heroLives <= 0) {
        fill(255, 255, 255);
        rect(0, 0, width, height);
      }
    }

  /**
   * Checks to see if a key is released and sets the boolean telling if it is released or not to false
   */
  public void keyReleased() {
    if (key == 'a' || key == 'A') {
      aPressed = false;
    }
    if (key == 's' || key == 'S') {
      sPressed = false;
    }
    if (key == 'w' || key == 'W') {
      wPressed = false;
    }
    if (key == 'd' || key == 'D') {
      dPressed = false;
    }
    if (key == 'p' || key == 'P') {
      pPressed = false;
    }
    if (keyCode == UP) {
      upPressed = false;
        }
    if (keyCode == DOWN) {
      downPressed = false;
    }
  }

  /**
   * If a key is pressed the value for the key being pressed is set to true, which allows the code to handle multiple inputs at once
   */
  public void keyPressed() {
    if (key == 'a' || key == 'A') {
      aPressed = true;
    }
    if (key == 's' || key == 'S') {
      sPressed = true;
    }
    if (key == 'w' || key == 'W') {
      wPressed = true;
    }
    if (key == 'd' || key == 'D') {
      dPressed = true;
    }
    if (key == 'p' || key == 'P') {
      pPressed = true;
    }
    if (keyCode == UP) {
      upPressed = true;
        }
    if (keyCode == DOWN) {
      downPressed = true;
    }
    if (aPressed) {
      heroX += -2;
    }
    if (wPressed) {
      heroY += -2;
    }
    if (sPressed) {
      heroY += 2;
    }
    if (dPressed) {
      heroX += 2;
    }
  }
}