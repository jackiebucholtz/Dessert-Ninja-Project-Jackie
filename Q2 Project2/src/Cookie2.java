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

public class Cookie2{
	
	//image related variables
	private Image img; 	
	private Image img1, img2;
	private AffineTransform tx, tx1, tx2;
	private int y;
	private int x;
	private double vy;
	private int xMax = 500;
	private int yMax = 400;
	private int xMin = 0;
	private int yMin = 200;
	public int width = 50;
	public int height = 50;
	public int addScore = 0;
	boolean slice = false;
	private Rectangle cookBoundary;


	Frame f = new Frame();
	//Rectangle rCook = new Rectangle(x+43, y+50, width+15, height+15);

	public boolean ifCookClicked (int x, int y) {
		Rectangle rCook = new Rectangle(x, y, 10, 10);
		cookBoundary = new Rectangle(this.x+40, this.y+45, width+10, height+25);
		if(cookBoundary.intersects(rCook)) {
			System.out.println("in bound");
			//this.y = (int) ((Math.random()*(1000-650))+650);
			slice = true;
			return true;
		}
		return false;
	}
	
	public Cookie2(int x, int y) {
		this.x = x;
		this.y = y;
		img = getImage("/imgs/cookie.png"); //load the image for cookie
		img1 = getImage("/imgs/cookieHalf2.png"); // load the image for cookie //slice

		img2 = getImage("/imgs/cookieHalf.png"); // load the image for cookie //slice
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	/* update variables here */
	//private void update() {

		
		
	//}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		
		//call update to update the actually picture location
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
		
		g.drawRect(x+43, y+50, width+15, height+15);

		
		

	}
	
	private void update () {
		//update y location based on velocity in y
		if (slice) { // slice
			vy += 3;
		} else {
			vy = -3.5;

		}
		y += vy;
			
		//prevent from leaving at the top of the frame
		//if(y < 10) {
			//y = 10;
			//vy = 0;
		//}
		
		tx.setToTranslation(x, y);
		tx.scale(0.13, 0.13);
		
		if (y > 450 && slice) { // slice
			vy = -3.5;
			slice = false;
			this.y = (int) ((Math.random() * (1000 - 650)) + 650);

			tx.setToTranslation(x, y);
			tx.scale(0.12, 0.12);

		}
		
		if(y <= -100) {
			y = 600;
			x = ((int)(Math.random() * (xMax - xMin)) + xMin);
		}
	}

	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(0.13, 0.13);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Cookie2.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
