package perso;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import manager.ObstacleManager;

public class Balle {
    int x;
    int y;
    int width;
    int height;

    boolean exist = true;
    boolean isTirer = false;
    
    Image ball = new ImageIcon("images/ball.png").getImage();
    
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
    
    public boolean isTirer() {
        return isTirer;
    }

    public void setTirer(boolean isTirer) {
        this.isTirer = isTirer;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public void tirer(){
        if (!isTirer) {
            isTirer = true;
        }
    }
    
    public void update(List<Obstacle> obstacle,List<Balle> balles,Dino dino){
        if (isTirer) {
            x += 15;
        }
        if (haveColision(obstacle) && exist) {
            Obstacle obstacleColision = getObstacleColision(obstacle);
            int obstacleColisionIndex = getObstacleColisionIndex(obstacle);
            if (obstacleColision.getTypeObstacle().equals(TypeObstacle.MECHANT)) {
                ObstacleManager.deleteObstacle(obstacle, obstacleColisionIndex);
                exist = false;
                dino.setScore(dino.getScore()+2);
            }
        }
        if (x >= 1015) {
            exist = false;
        }
    }
    
    
    public Balle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g){
        if (exist) {
            g.drawImage(ball, x, y, width, height,null);
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


}
