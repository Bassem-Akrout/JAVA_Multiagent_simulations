import java.awt.Point;
import java.util.ArrayList;
public class Balls  {

    private ArrayList<Point> balls; // Liste de positions de balles
    private ArrayList<Point> initial_balls;// Liste initiale de positions de balles
    private ArrayList<Integer> balls_directions;// Liste de [0/1/2/3] 0: +dx,+dy 
                                                                    //1: +dx,-dy
                                                                    //2: -dx,-dy
                                                                    //3: -dx,+dy
    
    // Constructeur pour initialiser les positions des balles


    public Balls() {
        balls = new ArrayList<>();
        initial_balls = new ArrayList<>();
        this.balls_directions = new ArrayList<>();
   
        // Ajoutez ici les positions initiales des balles
        balls.add(new Point(20, 400));
        balls.add(new Point(100, 20));
        balls.add(new Point(300, 300));
        initial_balls.add(new Point(20, 400));
        initial_balls.add(new Point(100, 20));
        initial_balls.add(new Point(300, 300));
        //init directions to 0 (on peut init random)
        //for (Point ball : balls.getBalls()) {
            balls_directions.add(0);
            balls_directions.add(3);
            balls_directions.add(1);
        //} 
            
    }    

    public ArrayList<Point> getBalls() {
        return this.balls;
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
            if (ball.x>480){
                if (direction==0){
                    balls_directions.set(i, 3);
                }
                else if(direction==1){
                    balls_directions.set(i, 2);
                }
                else{
                    System.out.println("problem 00000 avec ball");
                    System.out.println(ball);
                }
            }else if(ball.x<20){
                if (direction==2){
                    balls_directions.set(i, 1);
                }
                else if(direction==3){
                    balls_directions.set(i, 0);
                }
                else{
                    System.out.println("problem 00001 avec ball");
                    System.out.println(ball);
                }
            }
            if (ball.y>480){
                if (direction==0){
                    balls_directions.set(i, 1);
                    
                    System.out.println(balls_directions);
                }
                else if(direction==3){
                    balls_directions.set(i, 2);
                }
                else{
                    System.out.println("problem 00002 avec ball");
                    System.out.println(ball);
                }
            }else if(ball.y<20){
                if (direction==1){
                    balls_directions.set(i, 0);
                }
                else if(direction==2){
                    balls_directions.set(i, 3);
                }
                else{
                    System.out.println("problem 00002 avec ball");
                    System.out.println(ball);
                }

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
