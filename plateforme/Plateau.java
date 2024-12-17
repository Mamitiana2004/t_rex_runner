package plateforme;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import manager.ObstacleManager;
import perso.Balle;
import perso.Dino;
import perso.Obstacle;

public class Plateau extends JPanel{

    Dino dino;
    List<Obstacle> obstacles; 
    List<Balle> balles;
    boolean gameStart=false;
    boolean gamePause= false;

    Image image = new ImageIcon("images/fond.png").getImage();

    
    public Dino getDino() {
        return dino;
    }
    
    public void setDino(Dino dino) {
        this.dino = dino;
    }
    
    public List<Obstacle> getObstacles() {
        return obstacles;
    }
    
    public void setObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);
        g.drawImage(image, 10, 10, 1000,400,null);

        g.drawString("Dino", 1050, 20);
        g.drawString("Score : "+dino.getScore(), 1050, 50);
        g.drawString("Nombre de balle : "+dino.getNombreBalle(), 1050, 80);
        
        if (gameStart) {
            dino.draw(g);
    
            for (Obstacle obstacle : obstacles) {
                obstacle.draw(g);
            }
    
            for (Balle balle : balles) {
                balle.draw(g);
            }
        }

    }

    private Plateau plateau ;
    
    public Plateau(Dino dino,List<Obstacle> obstacles,JFrame frame){
        super();
        setLayout(null);
        this.dino = dino;
        this.obstacles = obstacles;

        plateau = this;
        balles = dino.getBalles();
        JButton buttonStart = new JButton("Start");
        buttonStart.setBounds(1050, 150, 150, 60);
        add(buttonStart);
        buttonStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!gameStart) {
                    frame.setFocusable(true);
                    frame.requestFocusInWindow();
                    gameStart = true;
                    repaint();
                }
            }
        });

        JButton buttonPause = new JButton("Pause");
        buttonPause.setBounds(1050, 230, 150, 60);
        add(buttonPause);

        buttonPause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (gamePause) {
                    gamePause = false;
                    buttonPause.setText("Pause");
                }
                else{
                    gamePause = true;
                    buttonPause.setText("Play");
                }
                frame.setFocusable(true);
                frame.requestFocusInWindow();
            }
        });
        
        JButton buttonRestart = new JButton("restart");
        buttonRestart.setBounds(1050, 310, 150, 60);
        add(buttonRestart);
        buttonRestart.setEnabled(false);

        buttonRestart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (dino.isGameOver()) {
                    dino.clear(obstacles, balles);
                    buttonRestart.setEnabled(false);
                    frame.setFocusable(true);
                    frame.requestFocusInWindow();
                    repaint();
                }
            }
        });

        new Thread(){

            public void run() {
                boolean afficherGameOver = false;
                while (true) {
                    if (dino.isGameOver() && !afficherGameOver) {
                        JOptionPane.showMessageDialog(plateau, "Game Over");
                        buttonRestart.setEnabled(true);
                        afficherGameOver = true;
                    }
                    if (gameStart && !gamePause && !dino.isGameOver()) {
                        afficherGameOver = false;
                        for (Balle balle : balles) {
                            balle.update(obstacles,balles,dino);
                        }
                        dino.update(obstacles);
                        ObstacleManager.generateObstacle(obstacles);
                        ObstacleManager.updateObstacles(obstacles,dino);
                    }
                    try {
                        Thread.sleep(16);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            };

        }.start();

        
    }
    

}
