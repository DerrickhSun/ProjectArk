package object;

import attacks.Attack;
import attacks.SilverSlash;

public class SilverAsh extends Player{
	public SilverAsh(int x, int y) {
		super("../SilverAsh/idle 1.png");
		super.setCharacter("SilverAsh");
		super.setIcon("../SilverAsh/SilverAsh Portrait.png");
		// TODO Auto-generated constructor stub
		super.setx(x);
		super.sety(y);
		setStartX(x);
		setStartY(y);
		super.setFallSpeed(10);
		super.setRunSpeed(25);
		setWidth(110);
		setHeight(150);
	}
	
	public Attack regularAttack() {
		Attack a = super.regularAttack();
		return a;
	}
	
	public Attack specialAttack() {
		Attack a = super.specialAttack();
		if(super.getDirection().equals("right")) {
			a = new SilverSlash("../SilverAsh/white arc.png", super.getX()+(super.getWidth()/2),super.getY()-20,200,200,this,super.getDirection(),10);
			super.setVx(0);
			super.setAx(0);
		}
		else if(super.getDirection().equals("left")) {
			a = new SilverSlash("../SilverAsh/white arc.png", super.getX()+(super.getWidth()/2)-200,super.getY()-20,200,200,this,super.getDirection(),10);
			super.setVx(0);
			super.setAx(0);
		}
		this.setCooldown(20);
		return a;
	}
	
	public void jump() {
		super.setVy(-60);
		
	}
	public void falling() {
		if(super.getVy()>super.getMaxFallSpeed()) {
			super.setVy(super.getMaxFallSpeed());
		}
		super.setAy(super.getFallSpeed());
	}
}
