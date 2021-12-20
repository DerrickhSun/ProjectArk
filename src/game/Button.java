package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import object.Sprite;

public class Button {
	private int x, y;
	private int height, width;
	public Image img;
	
	public Button(int a, int b, int c, int d) {
		x=a;
		y=b;
		height=d;
		width=c;
		img=null;
	}
	public Button(int a, int b, int c, int d, String url) {
		x=a;
		y=b;
		height=d;
		width=c;
		img=getImage(url);
	}
	// draw the affinetransform
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.drawImage(img, (int) getX(), (int) getY(), getWidth(), getHeight(), null);
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
	public boolean selected(int mouseX, int mouseY) {
		if(mouseX>x&&mouseX<x+width&&mouseY>y&&mouseY<y+height) {
			return true;
		}
		return false;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
}
