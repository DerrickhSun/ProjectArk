package object;

import attacks.Attack;
import attacks.RedBat;

public class Warfarin extends Player{

	public Warfarin(int x, int y) {
		super("../Warfarin/idle 1.png");
		super.setCharacter("Warfarin");
		super.setIcon("../Warfarin/Warfarin Portrait.png");
		// TODO Auto-generated constructor stub
		super.setx(x);
		super.sety(y);
		setStartX(x);
		setStartY(y);
		super.setFallSpeed(10);
		super.setRunSpeed(25);
		//super.setHeight(140);
		//super.setMaxFallSpeed(50);
	}
	public Attack regularAttack() {
		Attack a = super.regularAttack();
		a.setDamage(3);
		return a;
	}
	public Attack specialAttack() {
		Attack a = super.specialAttack();
		if(super.getDirection().equals("right")) {
			a = new RedBat("../Warfarin/red bat.jpg", super.getX()+(super.getWidth()/2)-50,super.getY(),100,100,this,super.getDirection(),15);
			a.setVx(50);
			super.setVx(0);
		}
		else if(super.getDirection().equals("left")) {
			a = new RedBat("../Warfarin/red bat.jpg", super.getX()+(super.getWidth()/2)-50,super.getY(),100,100,this,super.getDirection(),15);
			a.setVx(-50);
			super.setVx(0);
		}
		super.setAx(0);
		this.setCooldown(15);
		return a;
	}
	/*public void jump() {
		super.setVy(-60);
		
	}
	public void falling() {
		if(super.getVy()>super.getMaxFallSpeed()) {
			super.setVy(super.getMaxFallSpeed());
		}
		super.setAy(super.getFallSpeed());
	}*/
}