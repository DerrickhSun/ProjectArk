package attacks;

import object.Player;

public class RedBat extends Attack{
	public RedBat(int x, int y, String d, Player p) {
		super("../Warfarin/red bat.jpg",x,y,200,200,p,d,10);
		super.setKnockback(100);
		super.setDamage(1);
	}
	
	public RedBat(String f, int x2, int y2, int w, int h, Player x, String d, int time) {
		super(f, x2, y2, w, h, x, d, time);
		super.setKnockback(10);
		super.setDamage(2);
		// TODO Auto-generated constructor stub
	}

}
