import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Cupcake {

	// image related variables
	private Image img;
	private Image img1, img2;
	private AffineTransform tx, tx1, tx2;
	public int y;
	public int x;
	private double vy = -25, vx; //throw
	private int a = 1;
	private int xMax = 500;
	private int yMax = 400;
	private int xMin = 0;
	private int yMin = 200;
	public int width = 50;
	public int height = 50;
	public int addScore = 0;
	boolean slice = false;
	private Rectangle cupBoundary;
	// Rectangle rCup = new Rectangle(x+40, y+45, width+10, height+25);
	int og = 0;

	public boolean ifCupClicked(int x, int y) {
		Rectangle rCup = new Rectangle(x, y, 10, 10);
		cupBoundary = new Rectangle(this.x + 40, this.y + 45, width + 10, height + 25);
		if (cupBoundary.intersects(rCup)) {
			System.out.println("in bound");
			// this.y = (int) ((Math.random()*(1000-650))+650);
			slice = true;
			return true;
		}
		return false;
	}

	public Cupcake(int x, int y) {
		this.x = x;
		this.y = y;
		og = y;
		img = getImage("/imgs/cupcake.png"); // load the image for cupcake
		img1 = getImage("/imgs/cupcakeHalf2.png"); // load the image for cupcake //slice

		img2 = getImage("/imgs/cupcakeHalf.png"); // load the image for cupcake //slice
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); // initialize the location of the image
					// use your variables
		
		vx = (int)(Math.random()*(9))-4; //throw
	}

	/* update variables here */
	// private void update() {

	// }

	/* Drawing commands */
	public void paint(Graphics g) {
		// these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;

		// call update to update the actually picture location
		update();

		if (slice) {
			tx1 = AffineTransform.getTranslateInstance(x - 40, y); // slice
			tx1.scale(.12, .12);
			g2.drawImage(img2, tx1, null);
			tx2 = AffineTransform.getTranslateInstance(x + 40, y); // slice
			tx2.scale(.12, .12);

			g2.drawImage(img1, tx2, null); // slice

		} else {
			g2.drawImage(img, tx, null); // slice

		}

		//g.drawRect(x + 40, y + 45, width + 10, height + 20); // slice

	}

	private void update() {
		
		vy += a;//-3.5;
		y += vy;
		x += vx;

		if (y > 650 ) { // slice
			reset(); //throw
		}

		if (y <= -100) {
			reset(); //throw
		}

		
		tx.setToTranslation(x, y);
		tx.scale(0.12, 0.12);
		
	}
	
	//added reset - throw
	public void reset() {
		vx = (int)(Math.random()*(9))-4;
		y = og+50;
		vy = -25;
		x = ((int) (Math.random() * (xMax - xMin)) + xMin);
		slice = false;
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(0.12, 0.12);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
