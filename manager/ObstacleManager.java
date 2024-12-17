package manager;

import java.util.List;
import java.util.Random;

import perso.Dino;
import perso.Obstacle;
import perso.TypeObstacle;

public class ObstacleManager {

    static final int SCREEN_WIDTH = 840; 
    static final int OBSTACLE_MIN_DISTANCE = 300;

    static final int[] height_possible ={370,340,310,250};

    public static Obstacle genererObstacleRandom(){
        Random random = new Random();
        int x = 940;
        int y = height_possible[random.nextInt(4)];

        TypeObstacle typeObstacle = random.nextBoolean() ? TypeObstacle.GENTIL : TypeObstacle.MECHANT;
        if (typeObstacle.equals(TypeObstacle.GENTIL)) {
            return new Obstacle(x, y, 30, 30, typeObstacle);
        }
        else{
            return new Obstacle(x, y, 40, 40, typeObstacle);
        }
    }

    public static void generateObstacle(List<Obstacle> obstacles) {
        // Vérifie si une génération est nécessaire
        if (obstacles.isEmpty() || obstacles.get(obstacles.size() - 1).getX() < SCREEN_WIDTH - OBSTACLE_MIN_DISTANCE) {
            Obstacle newObstacle = genererObstacleRandom();
            obstacles.add(newObstacle);
        }
    }

    public static void deleteObstacle(List<Obstacle> obstacles,int index){
        if (index >= 0 && index < obstacles.size()) {
            obstacles.remove(index);
        }   
    }

    public static void updateObstacles(List<Obstacle> obstacles,Dino dino) {
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle obstacle = obstacles.get(i);

            // Déplace l'obstacle vers la gauche
            obstacle.setX(obstacle.getX() - 5); // Par exemple, déplacement de 5px par frame

            // Supprime les obstacles qui sortent de l'écran
            if (obstacle.getX() + obstacle.getWidth() < 0) {
                obstacles.remove(i);
                if (obstacle.getTypeObstacle().equals(TypeObstacle.MECHANT)) {
                    dino.setScore(dino.getScore()+1);
                }
                i--; // Ajuste l'index après suppression
            }
        }
    }

}
