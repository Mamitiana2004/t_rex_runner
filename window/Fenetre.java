package window;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import perso.Dino;
import perso.Obstacle;
import plateforme.Plateau;

public class Fenetre{

    
    
    public void init(){
        JFrame frame = new JFrame("Dino game");
        frame.setSize(1400,490);
        // frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dino dino = new Dino(100, 370, 40, 40);

        List<Obstacle> obstacles = new ArrayList<>();

        Plateau plateau = new Plateau(dino,obstacles,frame);
        frame.getContentPane().add(plateau);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        dino.jump();
                        break;
                    case KeyEvent.VK_DOWN:
                        dino.duck();
                        break;
                    case KeyEvent.VK_SPACE:
                        dino.tirer();
                        break;
                    default:
                        break;
                }
            }  

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    dino.stand();
                }
            }
        });
        


        frame.setVisible(true);
    }

    public Fenetre(){
        init();
    }

}