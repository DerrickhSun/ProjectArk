package attacks;

import object.Player;

public class NoirShield extends Attack{
	public NoirShield(int x, int y, String d, Player p) {
		super("../NoirCorne/shield.png",x,y,110,150,p,d,1);
		super.setKnockback(10);
		super.setDamage(0);
		super.setStun(0);
	}
	public NoirShield(String f, int x2, int y2, int w, int h, Player x, String d, int time) {
		super(f, x2, y2, w, h, x, d, time);
		super.setKnockback(1);
		super.setDamage(0);
		super.setStun(0);
		// TODO Auto-generated constructor stub
	}
	public void move() {
		if(getAttacker().defenseMode()) {
			setX(getAttacker().getX());
			setY(getAttacker().getY());
			setDirection(getAttacker().getDirection());
		}
		else {
			setDuration(-1);
		}
	}
	public void tick() {
		
	}
	public void hit(Player target) {
	}
}
