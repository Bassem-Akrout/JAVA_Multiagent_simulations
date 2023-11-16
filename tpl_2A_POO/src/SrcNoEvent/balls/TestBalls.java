public class TestBalls {
    public static void main(String[] args) {
        Balls balls = new Balls();

        System.out.println("Positions initiales des balles :");
        System.out.println(balls);

        balls.translate(50, 50);
        System.out.println("Positions des balles après 1ere translation :");
        System.out.println(balls);
        

        balls.reInit();
        System.out.println("Positions des balles après réinitialisation :");
        System.out.println(balls);

        balls.translate(50, 50);
        System.out.println("Positions des balles après 2eme translation :");
        System.out.println(balls);

        balls.reInit();
        System.out.println("Positions des balles après réinitialisation :");
        System.out.println(balls);

        balls.translate(50, 50);
        System.out.println("Positions des balles après 3eme translation :");
        System.out.println(balls);

        balls.reInit();
        System.out.println("Positions des balles après réinitialisation :");
        System.out.println(balls);

        balls.translate(50, 50);
        System.out.println("Positions des balles après 4eme translation :");
        System.out.println(balls);

    }
}