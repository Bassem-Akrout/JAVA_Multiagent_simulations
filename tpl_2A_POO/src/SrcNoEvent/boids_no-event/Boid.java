import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Boid {
    private double mass;
    private Point2D.Double position;
    private Point2D.Double velocity;

    public Boid(double boidMass,double x, double y, double vx, double vy) {
        mass=boidMass;
        position = new Point2D.Double(x, y);
        velocity = new Point2D.Double(vx, vy);
    }

    public Point2D.Double getPosition() {
        return position;
    }
    public Point2D.Double getVelocity() {
        return velocity;
    }
    public double getMass() {
        return mass;
    }

    //constantes pour controller les forces
    double MASS_CST=1;
    double SEP_CST=10;
    double ALIGN_CST=0.01;

    private double adjustForBorder(double value, double limit) {
        if (value > limit / 2) {
            value -= limit;
        } else if (value < -limit / 2) {
            value += limit;
        }
        return value;
    }
    

    public Point2D.Double cohesion(Point2D.Double centerOfMassPosition, double centerofMassPositionMass, double screenWidth, double screenHeight) {
        // Vecteur de direction entre la position actuelle et le centre de masse
        double dx = centerOfMassPosition.x - position.x;
        double dy = centerOfMassPosition.y - position.y;

        // Gestion de la bordure circulaire
        dx = adjustForBorder(dx, screenWidth);
        dy = adjustForBorder(dy, screenHeight);

        // Éviter une division par zéro
        if (dx == 0 && dy == 0) {
            return new Point2D.Double(0, 0);
        }

        // Distance entre la position actuelle et le centre de masse
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Composantes de la force de cohésion
        double magnitude = mass * centerofMassPositionMass * MASS_CST / Math.pow(distance, 2);
        double forceX = dx / distance * magnitude;
        double forceY = dy / distance * magnitude;

        return new Point2D.Double(forceX, forceY);
    }


    public Point2D.Double separation(ArrayList<Boid> boidsInRadius, double screenWidth, double screenHeight) {
        // Calcul de la force de séparation
        double separationForceX = 0;
        double separationForceY = 0;
    
        for (Boid boid : boidsInRadius) {
            double dx = boid.getPosition().getX() - position.getX();
            double dy = boid.getPosition().getY() - position.getY();
    
            // Gestion de la bordure circulaire
            dx = adjustForBorder(dx, screenWidth);
            dy = adjustForBorder(dy, screenHeight);
    
            double distance = Math.sqrt(dx * dx + dy * dy);
    
            // Éviter une division par zéro
            if (distance != 0) {
                double magnitude = SEP_CST / Math.pow(distance, 3);
                separationForceX += dx / distance * magnitude;
                separationForceY += dy / distance * magnitude;
            }
        }
    
        return new Point2D.Double(separationForceX, separationForceY);
    }
    

    public Point2D.Double alignement(ArrayList<Boid> boidsInRadius) {
        // Calcul de la force d'alignement
        double avgDirectionX = 0;
        double avgDirectionY = 0;
    
        for (Boid boid : boidsInRadius) {
            avgDirectionX += boid.getVelocity().getX();
            avgDirectionY += boid.getVelocity().getY();
        }
    
        int numNeighbors = boidsInRadius.size();
    
        if (numNeighbors > 0) {
            // Calcul de la moyenne des directions
            avgDirectionX /= numNeighbors;
            avgDirectionY /= numNeighbors;
    
            // Normalisation pour obtenir une direction moyenne
            double magnitude = Math.sqrt(avgDirectionX * avgDirectionX + avgDirectionY * avgDirectionY);
    
            if (magnitude != 0) {
                avgDirectionX /= magnitude;
                avgDirectionY /= magnitude;
            }
        }
        avgDirectionX*=ALIGN_CST;
        avgDirectionY*=ALIGN_CST;
        return new Point2D.Double(avgDirectionX, avgDirectionY);
    }

    private double adjustForBorder2(double value, double limit) {
        // Use modulo to wrap around
        return (value + limit) % limit;
    }
    
    public void update(Point2D.Double acceleration, double screenWidth, double screenHeight) {
        // Mise à jour de la vitesse en fonction de l'accélération
        velocity.x += acceleration.x;
        velocity.y += acceleration.y;
    
        // Limit the velocity to be within the range of -20 to 20
        velocity.x = Math.max(-10, Math.min(10, velocity.x));
        velocity.y = Math.max(-10, Math.min(10, velocity.y));


        // Mise à jour de la position en fonction de la vitesse
        position.x += velocity.x;
        position.y += velocity.y;
    
        // Gestion de la bordure circulaire
        position.x = adjustForBorder2(position.x, screenWidth);
        position.y = adjustForBorder2(position.y, screenHeight);
    }
}
