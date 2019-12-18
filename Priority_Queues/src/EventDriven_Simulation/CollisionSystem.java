package EventDriven_Simulation;


import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

import java.awt.*;



public class CollisionSystem
{
    // Variables
    private static final double HZ = 0.5;                                               // Number of redraw events per clock tick

    private MinPQ<Event> pq;                                                            // Priority queue
    private double t  = 0.0;                                                            // Simulation clock time
    private Particle[] particles;                                                       // Array of particles


    // Operations
    /**
     * Initializes a system with the specified collection of particles.
     * The individual particles will be mutated during the simulation.
     *
     * @param  particles the array of particles
     */
    public CollisionSystem(Particle[] particles)
    {
        // Defensive copy
        this.particles = particles.clone();
    }

    // Update priority queue with all new events for particle a
    private void predict(Particle a, double limit)
    {
        if (a == null)
            return;
        // Else
        // Particle-particle collisions
        for (Particle particle : particles)
        {
            double dt = a.timeToHit(particle);
            if (t + dt <= limit)
                pq.insert(new Event(t + dt, a, particle));
        }


        // Particle-wall collisions
        double dtX = a.timeToHitVerticalWall();
        double dtY = a.timeToHitHorizontalWall();

        if (t + dtX <= limit)
            pq.insert(new Event(t + dtX, a, null));
        if (t + dtY <= limit)
            pq.insert(new Event(t + dtY, null, a));
    }

    // Redraw all particles
    private void redraw(double limit)
    {
        StdDraw.clear();
        for (Particle particle : particles)
        {
            particle.draw();
        }
        StdDraw.show();
        StdDraw.pause(20);
        if (t < limit)
            pq.insert(new Event(t + 1.0 / HZ, null, null));
    }

    /**
     * Simulates the system of particles for the specified amount of time.
     *
     * @param  limit the amount of time
     */
    public void simulate(double limit)
    {
        // Initialize PQ with collision events and redraw event
        pq = new MinPQ<Event>();

        for (Particle particle : particles)
            predict(particle, limit);
        // Redraw event
        pq.insert(new Event(0, null, null));


        // The main event-driven simulation loop
        while (!pq.isEmpty())
        {
            // Get impending event, discard if invalidated
            Event e = pq.delMin();
            if (!e.isValid())
                continue;
            Particle a = e.a;
            Particle b = e.b;

            // Physical collision, so update positions, and then simulation clock
            for (Particle particle : particles)
                particle.move(e.time - t);
            t = e.time;

            // Process event
            if (a != null && b != null)                                             // Particle-particle collision
                a.bounceOff(b);
            else if (a != null && b == null)                                        // Particle-wall collision
                a.bounceOffVerticalWall();
            else if (a == null && b != null)                                        // Particle-wall collision
                b.bounceOffHorizontalWall();
            else if (a == null && b == null)                                        // Redraw event
                redraw(limit);

            // Update the priority queue with new collisions involving a or b
            predict(a, limit);
            predict(b, limit);
        }
    }


    /***************************************************************************
     *  An event during a particle collision simulation. Each event contains
     *  the time at which it will occur (assuming no supervening actions)
     *  and the particles a and b involved.
     *
     *    -  a and b both null:      redraw event
     *    -  a null, b not null:     collision with vertical wall
     *    -  a not null, b null:     collision with horizontal wall
     *    -  a and b both not null:  binary collision between a and b
     *
     ***************************************************************************/
    private static class Event implements Comparable<Event>
    {
        private final double time;                                              // Time that event is scheduled to occur
        private final Particle a, b;                                            // Particles involved in event, possibly null
        private final int countA, countB;                                       // Collision counts at event creation


        // Create a new event to occur at time t involving a and b
        public Event(double t, Particle a, Particle b)
        {
            // Variables
            this.time = t;
            this.a    = a;
            this.b    = b;


            // Operations
            if (a != null)
                countA = a.count();
            else
                countA = -1;

            if (b != null)
                countB = b.count();
            else
                countB = -1;
        }

        // Compare times when two events will occur
        public int compareTo(Event that)
        {
            return Double.compare(this.time, that.time);
        }

        // Has any collision occurred between when event was created and now?
        public boolean isValid()
        {
            if (a != null && a.count() != countA)
                return false;
            if (b != null && b.count() != countB)
                return false;
            return true;
        }
    }



    /**
     * Unit tests the {@code CollisionSystem} data type.
     * Reads in the particle collision system from a standard input
     * (or generates {@code N} random particles if a command-line integer
     * is specified); simulates the system.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args)
    {

        StdDraw.setCanvasSize(600, 600);

        // Enable double buffering
        StdDraw.enableDoubleBuffering();

        // Array of particles
        Particle[] particles;

        // Create n random particles
        if (args.length == 1)
        {
            int n = Integer.parseInt(args[0]);
            particles = new Particle[n];
            for (int i = 0; i < n; i++)
                particles[i] = new Particle();
        }
        // Or read from standard input
        else
            {
            int n = StdIn.readInt();
            particles = new Particle[n];
            for (int i = 0; i < n; i++) {
                double rx     = StdIn.readDouble();
                double ry     = StdIn.readDouble();
                double vx     = StdIn.readDouble();
                double vy     = StdIn.readDouble();
                double radius = StdIn.readDouble();
                double mass   = StdIn.readDouble();
                int r         = StdIn.readInt();
                int g         = StdIn.readInt();
                int b         = StdIn.readInt();
                Color color   = new Color(r, g, b);
                particles[i] = new Particle(rx, ry, vx, vy, radius, mass, color);
            }
        }

        // Create collision system and simulate
        CollisionSystem system = new CollisionSystem(particles);
        system.simulate(10000);
    }
}