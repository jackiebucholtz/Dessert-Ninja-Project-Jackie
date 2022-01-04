import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Font;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//CREATE THE OBJECT (STEP 1)
	private int xMax = 500;
	private int yMax = 400;
	private int xMin = 0;
	private int yMin = 200;
	public int mx;
	public int my;
	private int score = 0;
	private int xr = (int) ((Math.random() * (xMax - xMin)) + xMin);
	private int yr = (int) ((Math.random() * (yMax - yMin)) + yMin);
	private int xr1 = (int) ((Math.random() * (xMax - xMin)) + xMin);
	private int yr1 = (int) ((Math.random() * (yMax - yMin)) + yMin);
	private int xr2 = (int) ((Math.random() * (xMax - xMin)) + xMin);
	private int yr2 = (int) ((Math.random() * (yMax - yMin)) + yMin);
	private int xr3 = (int) ((Math.random() * (xMax - xMin)) + xMin);
	private int yr3 = (int) ((Math.random() * (yMax - yMin)) + yMin);
	Background 	bg 	= new Background(0, 0);	
	Cookie2 ck = new Cookie2 (xr, yr);
	Donut dt = new Donut(xr1, yr1);
	Cupcake cp = new Cupcake(xr2, yr2);
	Broccoli bc = new Broccoli(xr3, yr3);


	public void paint(Graphics g) {
		super.paintComponent(g);
		bg.paint(g);
		ck.paint(g);
		dt.paint(g);
		cp.paint(g);
		bc.paint(g);
		//IceCream.paint(g);
		
		
		g.setColor(Color.white);
		g.setFont(new Font ("Mistral", Font.PLAIN, 50));
		g.drawString("" + score, 20, 50);

		
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Wood Background");
		f.setSize(new Dimension(600, 400));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		mx = arg0.getX()-10;
		my = arg0.getY()-25;

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
			System.out.println(arg0.getKeyCode());

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
