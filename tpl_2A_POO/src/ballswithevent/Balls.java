import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Balls  {

    private ArrayList<Point> balls; // Liste de positions de balles
    private ArrayList<Point> initial_balls;// Liste initiale de positions de balles
    private ArrayList<Integer> balls_directions;// Liste de [0/1/2/3] 0: +dx,+dy 
                                                                    //1: +dx,-dy
                                                                    //2: -dx,-dy
                                                                    //3: -dx,+dy
    private  ArrayList<int[]> ballsColors; // Crée une liste de couleurs des balles 

    // Constructeur pour initialiser les positions des balles


    public Balls() {

        balls = new ArrayList<>();
        initial_balls = new ArrayList<>();
        ballsColors = new ArrayList<int[]>();
        balls_directions = new ArrayList<>();
        Random random = new Random();
        int nbBalls = random.nextInt(1,200); // On met le nombre maximal de balles à 6
        
        for (int i=0 ; i<nbBalls ; i++){
            
            // On initalise un point aléatoire
            int x = random.nextInt(20,1830);
            int y = random.nextInt(20,910);
            Point ball = new Point(x, y);
            
            // On ajoute le point dans la liste des balles 
            this.balls.add(ball);
            this.initial_balls.add(ball);
            
            // On initialise une direction aléatoire pour la balle
            this.balls_directions.add(random.nextInt(4));

            // On colore la balle 
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            ballsColors.add(new int[] {red,green,blue});
        }   
    }    

    public ArrayList<Point> getBalls() {
        return this.balls;
    }

    public ArrayList<int[]> getColors(){
        return this.ballsColors;
    }

    // Méthode pour translater toutes les balles
    public void translate(int dx, int dy) {
        for (int i = 0; i < balls.size(); i++) {
            Point ball = balls.get(i);
            int direction=balls_directions.get(i);
            int tempDx = dx;
            int tempDy = dy;
            if (direction==2 || direction==3){
                tempDx=-dx;
            } 
            if (direction==1 || direction==2){
                tempDy=-dy;
            } 
            ball.translate(tempDx, tempDy);
            if (ball.x>1840){
                if (direction==0){
                    balls_directions.set(i, 3);
                }
                else if(direction==1){
                    balls_directions.set(i, 2);
                }
                /*else{
                    
                    System.out.println(ball);
                }*/
            }else if(ball.x<8){
                if (direction==2){
                    balls_directions.set(i, 1);
                }
                else if(direction==3){
                    balls_directions.set(i, 0);
                }
                /*else{
                    System.out.println("problem 00001 avec ball");
                    System.out.println(ball);
                }*/
            }
            if (ball.y>920){
                if (direction==0){
                    balls_directions.set(i, 1);
                    
                    
                }
                else if(direction==3){
                    balls_directions.set(i, 2);
                }
                /*else{
                    System.out.println("problem 00002 avec ball");
                    System.out.println(ball);
                }*/
            }else if(ball.y<8){
                if (direction==1){
                    balls_directions.set(i, 0);
                }
                else if(direction==2){
                    balls_directions.set(i, 3);
                }
                /*else{
                    System.out.println("problem 00002 avec ball");
                    System.out.println(ball);
                }*/

            }
            
            // Vous pouvez également effectuer des modifications sur 'index' si nécessaire
        }
    }
    public void reInit() {
        // Réinitialisez les positions initiales des balles
        balls.clear();
        for (Point ball : initial_balls) {
            balls.add(new Point(ball) );
        }
    }
    // Redéfinissez la méthode toString pour afficher les positions des balles
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Point ball : balls) {
            result.append("(").append(ball.x).append(", ").append(ball.y).append(")\n");
        }
        return result.toString();
    }
    }
