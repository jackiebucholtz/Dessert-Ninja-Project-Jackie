import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Donut{
	
	//image related variables
	private Image img;
	private Image img1, img2; 	//slice
 	
	private AffineTransform tx, tx1, tx2;
	private int y;
	private int x;
	private int xMax = 400;
	private int yMax = 400;
	boolean slice = false;
	private int xMin = 0;
	private int yMin = 200;
	public int width = 50;
	public int height = 50;
	public int score = 0;
	public Rectangle donBoundary;
	private double vy = -25, vx; //throw
	private int a = 1;
	int og = 0;

	//Rectangle rDon = new Rectangle(x+20, y+20, width+15, height+10);

	public boolean ifDonClicked (int x, int y) {
		Rectangle rCook = new Rectangle(x, y, 10, 10);
		donBoundary = new Rectangle(this.x+40, this.y+45, width+10, height+25);
		if(donBoundary.intersects(rCook)) {
			System.out.println("in bound");
		//	this.y = (int) ((Math.random()*(1000-650))+650);
			slice = true; //slice
			return true;
		}
		return false;
	}
	
	public Donut (int x, int y) {
		this.x = x;
		this.y = y;
		og = y;
		img = getImage("/imgs/donut.png"); //load the image for Tree
		img1 = getImage("/imgs/donutHalf2.png"); //load the image for Tree //slice

		img2 = getImage("/imgs/donutHalf.png"); //load the image for Tree //slice

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
		
		
		//call update to update the actualy picture location
		update();
		
		
		
		if(slice) {
			tx1 = AffineTransform.getTranslateInstance(x-40, y ); //slice
			g2.drawImage(img2, tx1, null);
			tx2 = AffineTransform.getTranslateInstance(x+40, y ); //slice

			g2.drawImage(img1, tx2, null); //slice

		}else {
			g2.drawImage(img, tx, null); //slice

		}
		
		//g.drawRect(x+20, y+20, width+15, height+10); //slice

		

	}
	
	private void update () {
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
	}
	
	public void reset() {
		
		if(!slice && y > 650) {
			score--;
		}
		vx = (int)(Math.random()*(9))-4;
		y = og+50;
		vy = -25;
		x = ((int) (Math.random() * (xMax - xMin)) + xMin);
		slice = false;
	}

	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1.0, 1.0);
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