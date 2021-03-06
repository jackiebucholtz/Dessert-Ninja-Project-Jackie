import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Broccoli{
	
	//image related variables
	private Image img; 	
	private AffineTransform tx;
	public int y;
	public int x;
	private int xMax = 500;
	private int yMax = 400;
	private int xMin = 0;
	private int yMin = 200;
	public int width = 50;
	public int height = 50;
	public int subScore = 0;
	public Rectangle brocBoundary;
	private double vy = -25, vx; //throw
	private int a = 1;
	boolean slice = false;
	int og = 0;

	//Rectangle rBroc = new Rectangle(x+27, y+30, width+15, height+30);

	public boolean ifBrocClicked (int x, int y) {
		Rectangle rBroc = new Rectangle(x, y, 10, 10);
		brocBoundary = new Rectangle(this.x+40, this.y+45, width+10, height+25);
		if(brocBoundary.intersects(rBroc)) {
			System.out.println("in bound");
			this.y = (int) ((Math.random()*(1000-650))+650);
			return true;
		}
		return false;
	}
	
	public Broccoli(int x, int y) {
		this.x = x;
		this.y = y;
		og = y;
		img = getImage("/imgs/broccoli.png"); //load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
		vx = (int)(Math.random()*(9))-4; //throw

	}
	
	/* update variables here */
	//private void update() {

		
		
	//}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		
		//call update to update the actualy picture location
		update();
		
		
		
		
		g2.drawImage(img, tx, null);
		
		//g.drawRect(x+27, y+30, width+15, height+30);

		
		

	}
	
	private void update () {
		//update y location based on velocity in y
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
		tx.scale(1.2, 1.2);
		
	}
	
	public void reset() {
		vx = (int)(Math.random()*(9))-4;
		y = og+50;
		vy = -25;
		x = ((int) (Math.random() * (xMax - xMin)) + xMin);
		slice = false;
	}

	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1.2, 1.2);
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
