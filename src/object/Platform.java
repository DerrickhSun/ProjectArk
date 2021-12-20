package object;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Platform extends Sprite{
	
	public Platform(String f) {
		super(f);
		setWidth(100);
	}
	public Platform(String f, int a, int b) {
		super(f,a,b);
		setWidth(100);
	}
	public Platform(String f, int a, int b, int l) {
		super(f,a,b);
		setWidth(l);
	}
	public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.drawImage(img, (int) getX(), (int) getY(), super.getWidth(), super.getHeight(), null);
        g2.dispose();
    }
	public void setLength(int l) {
		setWidth(l);
	}
	public int getLength() {
		return getWidth();
	}
}
