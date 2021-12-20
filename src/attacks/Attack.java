package attacks;

import java.util.ArrayList;

import object.Player;
import object.Sprite;

public class Attack extends Sprite{
	private Player attacker;
	private String direction;
	private int duration;
	private int damage=3, knockback=20, stun=5;
	private ArrayList<Player> hitList = new ArrayList<Player> ();
	
	public Attack(String f, int x2, int y2, int w, int h, Player x, String d, int time) {
		super(f, x2, y2);
		setWidth(w);
		setHeight(h);
		attacker=x;
		direction=d;
		duration=time;
		if(f.equals("../SilverAsh/white arc.png")) {
			damage=1;
			knockback=100;
		}
		// TODO Auto-generated constructor stub
	}
	public void move() {
		setX(getX()+getVx());
		setY(getY()+getVy());
	}
	public void tick() {
		duration--;
	}
	public void hit(Player target) {
		hitList.add(target);
	}
	public ArrayList hitList(){
		return hitList;
	}
	
	public int getDuration() {
		return duration;
	}
	public void setAttacker(Player a) {
		attacker=a;
	}
	public Player getAttacker() {
		return attacker;
	}
	public void setDirection(String a) {
		direction=a;
	}
	public String getDirection() {
		return direction;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getKnockback() {
		return knockback;
	}
	public void setKnockback(int knockback) {
		this.knockback = knockback;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}	
	public int getStun() {
		return stun;
	}
	public void setStun(int a) {
		stun=a;
	}
}
