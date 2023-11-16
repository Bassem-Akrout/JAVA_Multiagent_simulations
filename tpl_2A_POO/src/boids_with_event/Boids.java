import java.util.ArrayList;
import java.util.Random;
import java.awt.geom.Point2D;

public class Boids {
    private ArrayList<Boid> boids;
    private ArrayList<Boid> initialBoids; // Stocke les boids initiaux

    public Boids(ArrayList<Boid> boids){
        this.boids=boids;
        this.initialBoids=boids;
    }
    public Boids(int numBoids, double screenWidth, double screenHeight,int nbrTypes) {
        boids = new ArrayList<>();
        initialBoids = new ArrayList<>(); // Initialise la liste des boids initiaux

        Random random = new Random();

        for (int i = 0; i < numBoids; i++) {
            double randint=random.nextInt(10*nbrTypes);
            double mass;
            if (randint>7*nbrTypes){ //30% de prédateurs
                mass = 1 + random.nextInt(nbrTypes); // Masse entre 1 et nbrTypes
            }
            else{
                mass = 1;
            }
            
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
    public int getnumber(){
        return boids.size();
    }
    public ArrayList<Boid> getGroup(int boidGroup){
        ArrayList<Boid> result=new ArrayList<>();
        for (Boid boid:boids){
            if (boid.getMass()==boidGroup){
                result.add(boid);
            }
        }
        return result;
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
    
    public ArrayList<Boid>  nextLogic(double screenWidth, double screenHeight,int boidGroup) {
        // For each boid, calculate the acceleration and update the position
        ArrayList<Boid> boidsToRemove = new ArrayList<>(); // Store boids to be removed
        ArrayList<Boid> concenedBoids=this.getGroup(boidGroup);
        for (Boid boid : concenedBoids) {
            // Get neighbors within the boid's radius
            ArrayList<Boid> neighborsMass = getNeighbors(boid, screenWidth, screenHeight, 100);
            ArrayList<Boid> neighborsSep = getNeighbors(boid, screenWidth, screenHeight, 20);

            Boids neighborsMassBoids=new Boids(neighborsMass);
            Boids neighborsSepBoids=new Boids(neighborsSep);

            ArrayList<Boid> neighborsSepSameSize=neighborsSepBoids.getGroup(boid.getMass());

            neighborsSep.removeAll(neighborsSepSameSize);

            ArrayList<Boid> smallerNeighbors=new ArrayList<>();
            
            int mass=boid.getMass();
            for (int i = 1; i < mass; i++){
                
                smallerNeighbors.addAll(neighborsMassBoids.getGroup(i));
                neighborsSep.removeAll(neighborsSepBoids.getGroup(i));
            }
            smallerNeighbors.addAll(neighborsMassBoids.getGroup(mass));

            // Calculate cohesion, separation, and alignment forces

            Point2D.Double cohesionForce = boid.cohesion(centerOfMass(smallerNeighbors), totalMass(smallerNeighbors), screenWidth, screenHeight);
            Point2D.Double separationForce = boid.separation(neighborsSep, screenWidth, screenHeight);
            Point2D.Double alignmentForce = boid.alignement(smallerNeighbors);
    
            // Calculate the total acceleration
            Point2D.Double totalAcceleration = new Point2D.Double(
                    (cohesionForce.getX() + separationForce.getX() + alignmentForce.getX()) / boid.getMass(),
                    (cohesionForce.getY() + separationForce.getY() + alignmentForce.getY()) / boid.getMass()
            );
    
            for (Boid otherBoid : neighborsSep) {
                if (otherBoid.getMass() < mass) {
                    double distanceX = Math.abs(otherBoid.getPosition().getX() - boid.getPosition().getX());
                    double distanceY = Math.abs(otherBoid.getPosition().getY() - boid.getPosition().getY());
                    double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
                    if (distance <= 10) {
                        // Add boid to the removal list
                        boidsToRemove.add(otherBoid);
                    }
                }
            }
            // Update the position of the boid based on the acceleration
            boid.update(totalAcceleration, screenWidth, screenHeight);
                        
        }
    
        // Remove boids marked for removal
        boids.removeAll(boidsToRemove);
        return boidsToRemove;
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
