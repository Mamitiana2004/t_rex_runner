package perso;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import manager.ObstacleManager;

public class Dino {

    int x;
    int y;
    int width;
    int height;
    int point;
    int nombreBalle;
    int score;
    boolean isJumping=false;
    int velociteY = 0;
    
    int normal_height;
    int duck_height;
    boolean isDucking=false;

    Image dino = new ImageIcon("images/dino.png").getImage();
    Image dino_duck = new ImageIcon("images/dino_duck.png").getImage();

    int sol_y;

    boolean isGameOver=false;

    List<Balle> balles;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getNombreBalle() {
        return nombreBalle;
    }

    public void setNombreBalle(int nombreBalle) {
        this.nombreBalle = nombreBalle;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSol_y() {
        return sol_y;
    }

    public void setSol_y(int sol_y) {
        this.sol_y = sol_y;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    public int getVelociteY() {
        return velociteY;
    }

    public void setVelociteY(int velociteY) {
        this.velociteY = velociteY;
    }

    public int getNormal_height() {
        return normal_height;
    }

    public void setNormal_height(int normal_height) {
        this.normal_height = normal_height;
    }

    public int getDuck_height() {
        return duck_height;
    }

    public void setDuck_height(int duck_height) {
        this.duck_height = duck_height;
    }

    public boolean isDucking() {
        return isDucking;
    }

    public void setDucking(boolean isDucking) {
        this.isDucking = isDucking;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public List<Balle> getBalles() {
        return balles;
    }

    public void setBalles(List<Balle> balles) {
        this.balles = balles;
    }
    
    public Dino(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        sol_y = y;
        this.width = width;
        this.height = height;
        point = 0;
        nombreBalle = 0;
        normal_height = height;
        duck_height = height/2;
        balles = new ArrayList<>();
    }

    public void draw(Graphics g){
        if (isDucking) {
            g.drawImage(dino_duck, x, y,width,height, null);
        }
        else {
            g.drawImage(dino, x, y,width,height, null);
        }
    }

    public boolean haveColision(List<Obstacle> obstacles){
        for (Obstacle obstacle : obstacles) {
            if ((x < obstacle.x + obstacle.width) && (x + width > obstacle.x) && (y < obstacle.y + obstacle.height) && (y + height > obstacle.y)) {
                return true;
            }
        }
        return false;
    }

    public Obstacle getObstacleColision(List<Obstacle> obstacles){
        for (Obstacle obstacle : obstacles) {
            if ((x < obstacle.x + obstacle.width) && (x + width > obstacle.x) && (y < obstacle.y + obstacle.height) && (y + height > obstacle.y)) {
                return obstacle;
            }
        }
        return null;
    }
    
    public int getObstacleColisionIndex(List<Obstacle> obstacles){
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle obstacle = obstacles.get(i);
            if ((x < obstacle.x + obstacle.width) && (x + width > obstacle.x) && (y < obstacle.y + obstacle.height) && (y + height > obstacle.y)) {
                return i;
            }
        }
        return -1;
    }


    public void jump(){
        if (!isJumping) {
            isJumping = true;
            velociteY = -16;
        }
    }

    public void duck(){
        if (!isDucking) {
            isDucking = true;
            height = duck_height;
            y += normal_height - duck_height;
        }
    }

    public void stand(){
        if (isDucking) {
            isDucking = false;
            height = normal_height;
            y-= normal_height - duck_height;
        }
    }

    public void tirer(){
        if (nombreBalle > 0) {
            int yBalle = y + (height/2);
            Balle balle = new Balle(x, yBalle, 25, 15);
            balles.add(balle);
            balle.tirer();
            nombreBalle--;
        }
    }

    public void update(List<Obstacle> obstacle){
        if (isJumping) {
            y+=velociteY;
            velociteY++;
            if (y >= sol_y) {
                y = sol_y;
                isJumping = false;
                velociteY = 0;
            }
        }

        if (haveColision(obstacle)) {
            Obstacle obstacleColision = getObstacleColision(obstacle);
            int obstacleColisionIndex = getObstacleColisionIndex(obstacle);
            if (obstacleColision.getTypeObstacle().equals(TypeObstacle.MECHANT)) {
                isGameOver = true;
            }
            else{
                ObstacleManager.deleteObstacle(obstacle, obstacleColisionIndex);
                nombreBalle++;
            }
        }
        
        
    }

    public void clear(List<Obstacle> obstacles,List<Balle> balles){
        score = 0;
        nombreBalle = 0;
        isGameOver = false;
        obstacles.clear();
        balles.clear();
        y = sol_y;
    }

    


}
