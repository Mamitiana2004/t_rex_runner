package perso;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Obstacle {
    
    int x;
    int y;
    int width;
    int height;

    TypeObstacle typeObstacle;

    
    Image mechant = new ImageIcon("images/mechant.png").getImage();
    Image gentil = new ImageIcon("images/gentil.png").getImage();
    

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


    public TypeObstacle getTypeObstacle() {
        return typeObstacle;
    }


    public void setTypeObstacle(TypeObstacle typeObstacle) {
        this.typeObstacle = typeObstacle;
    }


    public Obstacle(int x, int y, int width, int height, TypeObstacle typeObstacle) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.typeObstacle = typeObstacle;
    }

    

    public void draw(Graphics g){
        if (typeObstacle.equals(TypeObstacle.GENTIL)) {
            g.setColor(Color.blue);
            g.fillOval(x, y, width, height);
        }
        if (typeObstacle.equals(TypeObstacle.MECHANT)) {
            g.drawImage(mechant, x, y,width,height, null);
        }
    }


    

}
