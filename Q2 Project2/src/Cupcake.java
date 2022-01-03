import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Cupcake{
	
	//image related variables
	private Image img; 	
	private AffineTransform tx;
	public int y;
	public int x;
	private double vy;
	private int xMax = 500;
	private int yMax = 400;
	private int xMin = 0;
	private int yMin = 200;
	public int width = 50;
	public int height = 50;
	Rectangle rCup = new Rectangle(x+40, y+45, width+10, height+25);


	public Cupcake(int x, int y) {
		this.x = x;
		this.y = y;
		img = getImage("/imgs/cupcake.png"); //load the image for Tree
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
		
		
		
		
		g2.drawImage(img, tx, null);
		
		g.drawRect(x+40, y+45, width+10, height+25);
		
		

	}

	private void update () {
		//update y location based on velocity in y
		y += vy; //velocity in y affects y location
		vy = -3.5;
			
		//prevent from leaving at the top of the frame
		//if(y < 10) {
			//y = 10;
			//vy = 0;
		//}
		
		tx.setToTranslation(x, y);
		tx.scale(0.12, 0.12);
		
		
			if(y <= -100) {
				y = 600;
				x = ((int)(Math.random() * (xMax - xMin)) + xMin);
			}
		
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
