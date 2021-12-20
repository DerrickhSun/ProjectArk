package object;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

import javax.swing.*;

public class Sprite extends JButton{
	private double x;
    private double y;
    private int width=100, height=100;
    public Image img;
    private int vx = 0, vy = 0, ay = 1, ax=0;
    private boolean platform=false;
    private int fallSpeed=0;
    private int runSpeed=10;
    public AffineTransform tx = AffineTransform.getTranslateInstance(getX(), getY());

    // constructs player as affinetransform instead of image
    public Sprite(String f, int x2, int y2, boolean a) {
    	setX(x2);
        setY(y2);
        img = getImage(f);
        init(getX(), getY());
        platform=a;
    }
    public Sprite(String filename) {
        tx = AffineTransform.getTranslateInstance(getX(), getY());
        setX(0); // beginning of path
        setY(405);
        img = getImage(filename);

        this.setBounds((int) getX(), (int) getY(), 100, 100);
        init(getX(), getY());
    }


    public Sprite(String f, int x2, int y2) {
        setX(x2);
        setY(y2);
        img = getImage(f);
        init(getX(), getY());
    }


    // move with input from driver
    public void move() {
    	x+=vx;
    	y+=vy;
        //tx.translate(vx, vy);
        vx=vx+ax;
        vy=vy+ay;
        //ax=0;
        for(int i=0;i<9;i++) {
        	if(getVx()>0)setVx(getVx()-1);
			if(getVx()<0)setVx(getVx()+1);
        }
    }
    
    public void moveTo(int x1, int y1) {
    	tx.translate(x1, y1);
    }

    public void setImg(String filename) {
    	img = getImage(filename);
    }
    // use find affinetransform current position
    public int gety() {
        return (int) tx.getTranslateY();
    }
    
    public void sety(int a) {
        y = a;
    }

    public int getx() {
        return (int) tx.getTranslateX();
    }

    public void setx(int a) {
        x = a;
    }

    public void rotateCW() {
        tx.rotate(2);
    }


    // set size and position and reset player
    public void init(double a, double b) {
        tx.setToTranslation(a, b);
        tx.scale(2, 2);
    }

    // draw the affinetransform
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.drawImage(img, (int) getX(), (int) getY(), getWidth(), getHeight(), null);
        g2.dispose();
    }
    public void flipPaint(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g.create();
    	g2.drawImage(img, (int) getX()+getWidth(), (int) getY(), -getWidth(), getHeight(), null);
    	g2.dispose();
    }
    // converts image to make it drawable in paint
    protected Image getImage(String path) {
        Image tempImage = null;
        try {
            URL imageURL = Sprite.class.getResource(path);
            tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempImage;
    }

	public void takeDamage(int i) {
		// TODO Auto-generated method stub
		//does nothing
	}

    public int getX() {
        return (int) x;
    }


    public void setX(double x) {
        this.x = x;
    }


    public int getY() {
        return (int) y;
    }


    public void setY(double y) {
        this.y = y;
    }
	public int getVx() {
		return vx;
	}
	public void setVx(int vx) {
		this.vx = vx;
	}
	public int getVy() {
		return vy;
	}
	public void setVy(int vy) {
		this.vy = vy;
	}
	public int getAy() {
		return ay;
	}
	public void setAy(int ay) {
		this.ay = ay;
	}
	public int getAx() {
		return ax;
	}
	public void setAx(int ax) {
		this.ax = ax;
	}
	public int getFallSpeed() {
		return fallSpeed;
	}
	public void setFallSpeed(int fallSpeed) {
		this.fallSpeed = fallSpeed;
	}
	public int getRunSpeed() {
		return runSpeed;
	}
	public void setRunSpeed(int runSpeed) {
		this.runSpeed = runSpeed;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getDirection(){
		return "";
		
	}
}
