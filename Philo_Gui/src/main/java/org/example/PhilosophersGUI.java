import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PhilosophersGUI extends JPanel {
    private final int numPhilosophers; // Number of philosophers
    private final int radius = 150;   // Radius of the circular table
    private final int centerX = 250;  // Center X-coordinate
    private final int centerY = 250;  // Center Y-coordinate
    private final int personRadius = 90; // Radius of each philosopher (circle)
    private final boolean[] isForkDirty; // Fork status: true = dirty, false = clean

    public PhilosophersGUI(int numPhilosophers) {
        this.numPhilosophers = numPhilosophers;
        this.isForkDirty = new boolean[numPhilosophers];
        Random random = new Random();

        // Randomly decide whether each fork is dirty
        for (int i = 0; i < numPhilosophers; i++) {
            isForkDirty[i] = random.nextBoolean(); // Randomly assign true (dirty) or false (clean)
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw philosophers (circles)
        for (int i = 0; i < numPhilosophers; i++) {
            double angle = 2 * Math.PI * i / numPhilosophers; // Angle for each philosopher
            int personX = centerX + (int) (radius * Math.cos(angle)) - personRadius / 2;
            int personY = centerY + (int) (radius * Math.sin(angle)) - personRadius / 2;

            g2d.setColor(Color.BLACK);
            g2d.fillOval(personX, personY, personRadius, personRadius);
        }

        // Draw forks (only if there are 2 or more philosophers)
        if (numPhilosophers > 1) {
            for (int i = 0; i < numPhilosophers; i++) {
                // Calculate angle for the fork between philosophers
                double forkAngle = (2 * Math.PI * i / numPhilosophers) + Math.PI / numPhilosophers;
                int forkX = centerX + (int) ((radius - 30) * Math.cos(forkAngle));
                int forkY = centerY + (int) ((radius - 30) * Math.sin(forkAngle));

                // Draw fork with appropriate color based on cleanliness
                drawFork(g2d, forkX, forkY, forkAngle, isForkDirty[i]);
            }
        }
    }

    // Method to draw a fork
    private void drawFork(Graphics2D g2d, int x, int y, double angle, boolean isDirty) {
        // Set fork color based on its state
        if (isDirty) {
            g2d.setColor(Color.YELLOW); // Dirty fork
        } else {
            g2d.setColor(Color.GRAY); // Clean fork
        }

        // Translate and rotate to position the fork correctly
        g2d.translate(x, y);
        g2d.rotate(angle - Math.PI / 2); // Rotate fork to vertical orientation

        // Draw fork handle
        g2d.fillRect(-2, 0, 4, 30);

        // Draw fork tines (3 lines)
        for (int i = -4; i <= 4; i += 4) {
            g2d.fillRect(i, -20, 2, 20);
        }

        // Reset transformations
        g2d.rotate(-(angle - Math.PI / 2));
        g2d.translate(-x, -y);
    }

    public static void main(String[] args) {
        // Set number of philosophers to a random value between 1 and 10
        Random random = new Random();
        int numPhilosophers = random.nextInt(10) + 1;

        // Create the GUI frame
        JFrame frame = new JFrame("Dining Philosophers Problem");
        PhilosophersGUI panel = new PhilosophersGUI(numPhilosophers);
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        System.out.println("Number of philosophers: " + numPhilosophers);
    }
}
