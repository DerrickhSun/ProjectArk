package object;

import attacks.Attack;
import attacks.NoirShield;

public class NoirCorne extends Player{
	private boolean defenseMode=false;
	public NoirCorne(int x, int y) {
		super("../NoirCorne/idle 1.png");
		super.setCharacter("NoirCorne");
		super.setIcon("../NoirCorne/NoirCorne Portrait.png");
		// TODO Auto-generated constructor stub
		super.setx(x);
		super.sety(y);
		setStartX(x);
		setStartY(y);
		super.setFallSpeed(10);
		super.setRunSpeed(18);
		setWidth(110);
	}
	
	public Attack regularAttack() {
		Attack a = super.regularAttack();
		if(defenseMode) {
			a.setDamage(1);
		}
		return a;
	}
	
	public Attack specialAttack() {
		Attack a = super.specialAttack();
		if(defenseMode) {
			setRunSpeed(18);
			setImg("../NoirCorne/idle 1.png");
			defenseMode=false;
		}
		else {
			setRunSpeed(9);
			setImg("../NoirCorne/shield.png");
			defenseMode=true;
		}
		setCooldown(10);
		a=new NoirShield(getX(),getY(),super.getDirection(),this);
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
	public boolean defenseMode() {
		return defenseMode;
	}
}