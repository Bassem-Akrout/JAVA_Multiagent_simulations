import java.util.ArrayList;
import java.util.Random;
import java.awt.geom.Point2D;

public class Boids {
    private ArrayList<Boid> boids;
    private ArrayList<Boid> initialBoids; // Stocke les boids initiaux


    public Boids(int numBoids, double screenWidth, double screenHeight) {
        boids = new ArrayList<>();
        initialBoids = new ArrayList<>(); // Initialise la liste des boids initiaux

        Random random = new Random();

        for (int i = 0; i < numBoids; i++) {
            double mass = 1 + random.nextDouble(); // Masse entre 1 et 2
            double x = random.nextDouble() * screenWidth; // Position en x entre 0 et screenWidth
            double y = random.nextDouble() * screenHeight; // Position en y entre 0 et screenHeight
            double vx = (random.nextDouble()-0.5) * 10; // Vitesse en x entre -5 et 5
            double vy = (random.nextDouble()-0.5) * 10; // Vitesse en y entre -5 et 5

            Boid newBoid = new Boid(mass, x, y, vx, vy);
            boids.add(newBoid);
            initialBoids.add(new Boid(mass, x, y, vx, vy)); // Stocke le boid initial

        }
    }
    public ArrayList<Boid> getBoids(){
        return boids;
    }
    public void reInit() {
        boids.clear(); // Vide la liste actuelle de boids
    
        for (Boid initialBoid : initialBoids) {
            // Crée une copie du boid initial pour éviter la modification des valeurs initiales
            Boid newBoid = new Boid(
                    initialBoid.getMass(),
                    initialBoid.getPosition().getX(),
                    initialBoid.getPosition().getY(),
                    initialBoid.getVelocity().getX(),
                    initialBoid.getVelocity().getY()
            );
    
            boids.add(newBoid);
        }
    }
    
    public void nextLogic(double screenWidth, double screenHeight) {
        // Pour chaque boid, calculer l'accélération et mettre à jour la position
        for (Boid boid : boids) {
            // Obtenez les voisins dans le rayon du boid
            ArrayList<Boid> neighbors_mass = getNeighbors(boid,screenWidth,screenHeight,50);
            ArrayList<Boid> neighbors_sep = getNeighbors(boid,screenWidth,screenHeight,10);
            // Calculez les forces de cohésion, séparation et alignement
            Point2D.Double cohesionForce = boid.cohesion(centerOfMass(neighbors_mass), totalMass(neighbors_mass),screenWidth,screenHeight);
            Point2D.Double separationForce = boid.separation(neighbors_sep,screenWidth,screenHeight);
            Point2D.Double alignmentForce = boid.alignement(neighbors_mass);

            // Calculez l'accélération totale
            Point2D.Double totalAcceleration = new Point2D.Double(
                (cohesionForce.getX() + separationForce.getX() + alignmentForce.getX()) / boid.getMass(),
                (cohesionForce.getY() + separationForce.getY() + alignmentForce.getY()) / boid.getMass()
            );

            // Mettez à jour la position du boid en fonction de l'accélération
            boid.update(totalAcceleration,screenWidth,screenHeight);
        }
    }

    private ArrayList<Boid> getNeighbors(Boid boid,double screenWidth,double screenHeight,double neighbors_dist) {
        ArrayList<Boid> neighbors = new ArrayList<>();
        for (Boid otherBoid : boids) {
            if (otherBoid != boid) {

                double distanceX = Math.abs(otherBoid.getPosition().getX() - boid.getPosition().getX());
                double distanceY = Math.abs(otherBoid.getPosition().getY() - boid.getPosition().getY());
    
                // Gestion de la bordure circulaire
    
                if (distanceX > screenWidth / 2) {
                    distanceX = screenWidth - distanceX;
                }
    
                if (distanceY > screenHeight / 2) {
                    distanceY = screenHeight - distanceY;
                }
    
                double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    
                // Si la distance est inférieure ou égale à la portée du boid, ajoutez-le aux voisins
                if (distance <= neighbors_dist) {
                    neighbors.add(otherBoid);
                }
            }
        }
    
        return neighbors;
    }
    
    private Point2D.Double centerOfMass(ArrayList<Boid> neighbors) {
        double totalMass = 0;
        double centerOfMassX = 0;
        double centerOfMassY = 0;
    
        for (Boid neighbor : neighbors) {
            double neighborMass = neighbor.getMass();
            totalMass += neighborMass;
    
            centerOfMassX += neighbor.getPosition().getX() * neighborMass;
            centerOfMassY += neighbor.getPosition().getY() * neighborMass;
        }
    
        if (totalMass > 0) {
            centerOfMassX /= totalMass;
            centerOfMassY /= totalMass;
        }
    
        return new Point2D.Double(centerOfMassX, centerOfMassY);
    }

    private double totalMass(ArrayList<Boid> neighbors){
        double totalMass = 0;
        for (Boid neighbor : neighbors) {
            double neighborMass = neighbor.getMass();
            totalMass += neighborMass;
        }
        return totalMass;
    }
}
