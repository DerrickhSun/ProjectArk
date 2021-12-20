package object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import attacks.Attack;
import attacks.SilverSlash;

public class Player extends Sprite{
	private int animationDelay=0;
	private int damageReceived=0;
	private String character="";
	public Image icon;
	private String direction="right";
	private int jumps=3;
	private int totalJumps=2;
	private int maxFallSpeed=50;
	private Sprite attack=null;
	private String imgName;
	private int cooldown=0;
	private int unresponsive=0;
	private boolean left=false,right=false,down=false,up=false,normalAttack=false,specialAttack=false;
	private double startX, startY;
	private int lives=3;
	private int invincible=0;
	
	public Player(String filename) {
		super(filename);
		imgName=filename;
		icon=getImage(filename);
		super.setWidth(100);
		super.setHeight(150);
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(invincible>0) {
			Graphics2D g2 = (Graphics2D) g.create();
        	g2.drawImage(getImage("../resources/bubble.png"), (int) getX()+getWidth()/2-invincible, (int) getY()+getHeight()/2-invincible, 2*invincible, 2*invincible, null);
        	g2.dispose();
        }
    }
	
	public void flipPaint(Graphics g) {
		super.flipPaint(g);
		if(invincible>0) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.drawImage(getImage("../resources/bubble.png"), (int) getX()+getWidth()/2-invincible, (int) getY()+getHeight()/2-invincible, 2*invincible, 2*invincible, null);
    		g2.dispose();
        }
    }
	
	public void die() {
		lives-=1;
		damageReceived=0;
		if(lives>0) {
			setX(startX);
			setY(startY-600);
			invincible=75;
		}
	}
	public void paintIcon(Graphics g, int number) {
        Graphics2D g2 = (Graphics2D) g.create();
        if(number==1) {
        	g.setColor(Color.RED);
        	g.fillRect(100, 800, 300, 100);
        	g2.drawImage(icon, 50, 750, 150, 150, null);
        	g.setColor(Color.white);
        	g.setFont(new Font("Courier New", Font.BOLD, 80));
        	g.drawString(""+damageReceived+"%", 250, 880);
        	if(lives>=1) g2.drawImage(getImage("../resources/heart.png"), 210, 800, 20, 20, null);
        	if(lives>=2) g2.drawImage(getImage("../resources/heart.png"), 210, 820, 20, 20, null);
        	if(lives>=3) g2.drawImage(getImage("../resources/heart.png"), 210, 840, 20, 20, null);
        }
        else if(number==2) {
        	g.setColor(Color.blue);
        	g.fillRect(600, 800, 300, 100);
        	g2.drawImage(icon, 550, 750, 150, 150, null);
        	g.setColor(Color.white);
        	g.setFont(new Font("Courier New", Font.BOLD, 80));
        	g.drawString(""+damageReceived+"%", 750, 880);
        	if(lives>=1) g2.drawImage(getImage("../resources/heart.png"), 710, 800, 20, 20, null);
        	if(lives>=2) g2.drawImage(getImage("../resources/heart.png"), 710, 820, 20, 20, null);
        	if(lives>=3) g2.drawImage(getImage("../resources/heart.png"), 710, 840, 20, 20, null);
        }
        else {
        	g.setColor(Color.green);
        	g.fillRect(1100, 800, 300, 100);
        	g2.drawImage(icon, 1050, 750, 150, 150, null);
        	g.setColor(Color.white);
        	g.setFont(new Font("Courier New", Font.BOLD, 80));
        	g.drawString(""+damageReceived+"%", 1250, 880);
        	if(lives>=1) g2.drawImage(getImage("../resources/heart.png"), 1210, 800, 20, 20, null);
        	if(lives>=2) g2.drawImage(getImage("../resources/heart.png"), 1210, 820, 20, 20, null);
        	if(lives>=3) g2.drawImage(getImage("../resources/heart.png"), 1210, 840, 20, 20, null);
        }
        g2.dispose();
    }
	public void setIcon(String a) {
		icon=getImage(a);
	}
	public void jump() {
		setVy(-60);
		System.out.println(jumps);
	}
	
	public void walkLeft() {
		setDirection("left");
		super.setAx(-15);
		if(getAnimationDelay()<=1) {
			if(getImgName().equals("../"+character+"/idle 1.png")){
				setImg("../"+character+"/walking 1.png");
				setAnimationDelay(2);
			}
			else if(getImgName().equals("../"+character+"/walking 1.png")){
				setImg("../"+character+"/walking 2.png");
				setAnimationDelay(2);
			}
			else if(getImgName().equals("../"+character+"/walking 2.png")){
				setImg("../"+character+"/walking 3.png");
				setAnimationDelay(2);
			}
			else if(getImgName().equals("../"+character+"/walking 3.png")){
				setImg("../"+character+"/walking 4.png");
				setAnimationDelay(2);
			}
			else if(getImgName().equals("../"+character+"/walking 4.png")){
				setImg("../"+character+"/walking 5.png");
				setAnimationDelay(2);
			}
			else if(getImgName().equals("../"+character+"/walking 5.png")){
				setImg("../"+character+"/walking 6.png");
				setAnimationDelay(2);
			}
			else if(getImgName().equals("../"+character+"/walking 6.png")){
				setImg("../"+character+"/walking 1.png");
				setAnimationDelay(2);
			}
		}
		else {
			setAnimationDelay(getAnimationDelay()-1);
		}
	}
	
	public void walkRight() {
		setDirection("right");
		super.setAx(15);
		if(getAnimationDelay()<=1) {
			if(getImgName().equals("../"+character+"/idle 1.png")){
				setImg("../"+character+"/walking 1.png");
				setAnimationDelay(2);
			}
			else if(getImgName().equals("../"+character+"/walking 1.png")){
				setImg("../"+character+"/walking 2.png");
				setAnimationDelay(2);
			}
			else if(getImgName().equals("../"+character+"/walking 2.png")){
				setImg("../"+character+"/walking 3.png");
				setAnimationDelay(2);
			}
			else if(getImgName().equals("../"+character+"/walking 3.png")){
				setImg("../"+character+"/walking 4.png");
				setAnimationDelay(2);
			}
			else if(getImgName().equals("../"+character+"/walking 4.png")){
				setImg("../"+character+"/walking 5.png");
				setAnimationDelay(2);
			}
			else if(getImgName().equals("../"+character+"/walking 5.png")){
				setImg("../"+character+"/walking 6.png");
				setAnimationDelay(2);
			}
			else if(getImgName().equals("../"+character+"/walking 6.png")){
				setImg("../"+character+"/walking 1.png");
				setAnimationDelay(2);
			}
		}
		else {
			setAnimationDelay(getAnimationDelay()-1);
		}
	}
	
	public void falling() {
		if(getVy()>maxFallSpeed) {
			setVy(maxFallSpeed);
		}
		super.setAy(super.getFallSpeed());
	}
	
	public void attacking() {	
	}
	
	public void takeDamage(int damage, int knockback, int stun, boolean right) {
		unresponsive=stun;
		damageReceived+=damage;
		if(right) {
			setVx((knockback)*(100+damageReceived)/100);
			setVy((knockback)*(100+damageReceived)/1000);
		}
		else {
			setVx(-(knockback)*(100+damageReceived)/100);
			setVy((knockback)*(100+damageReceived)/1000);
		}
	}
	public void restoreHealth(int damage) {
		if(damageReceived-damage<=0) {
			damageReceived=0;
		}
		else {
			damageReceived-=damage;
		}
	}
	public Attack regularAttack() {
		invincible=0;
		setVx(0);
		Attack a = new Attack("../resources/basicstrike.png", super.getX()+super.getWidth()-15,super.getY()+30,100,60,this,direction,8);
		if(a.getDirection().equals("right")) {
			a = new Attack("../resources/basicstrike.png", super.getX()+super.getWidth()-15,super.getY()+40,100,60,this,direction,8);
			a.setVx(0);
			a.setAx(0);
		}
		else if(a.getDirection().equals("left")) {
			a = new Attack("../resources/basicstrike.png", super.getX()-super.getWidth()+15,super.getY()+40,100,60,this,direction,8);
			a.setVx(0);
			a.setAx(0);
		}
		this.setCooldown(5);
		return a;
	}
	public Attack specialAttack() {
		invincible=0;
		Attack a = new Attack("../resources/basicstrike.png", super.getX()+super.getWidth()-15,super.getY()+30,100,60,this,super.getDirection(),8);
		return a;
	}
	
	public void forwardSpecial() {
	}
	
	public void move(boolean touchingGround) {
		if(Math.abs(super.getVx())>super.getRunSpeed()) {
			for(int i=0;i<9;i++) {
	        	if(getVx()>0)setVx(getVx()-1);
				if(getVx()<0)setVx(getVx()+1);
	        }
		}
		if(touchingGround) {
			if(getVy()>0)setVy(0);
			if(specialAttack==true && (cooldown>0 || unresponsive>0)) {
				//specialAttack();
			}
			else if(normalAttack==true && (cooldown>0 || unresponsive>0)) {
				//regularAttack();
			}
			else {
				if(left==true&&right==false&&unresponsive<=0) {
					walkLeft();
				}
				if(right==true&&left==false&&unresponsive<=0) {
					walkRight();
				}
				if(up==true) {
					//jump();
				}
				if(down==true) {
					
				}
			}
		}
		else {
			falling();
			if(specialAttack==true && (cooldown>0 || unresponsive>0)) {
				//specialAttack();
			}
			else if(normalAttack==true && (cooldown>0 || unresponsive>0)) {
				//regularAttack();
			}
			else {
				if(left==true&&right==false&&unresponsive<=0) {
					if (unresponsive<=0) {
					walkLeft();}
				}
				if(right==true&&left==false&&unresponsive<=0) {
					if(unresponsive<=0) {
					walkRight();}
				}
				if(up==true) {
					//jump();
				}
				if(down==true) {
					
				}
			}
		}
		move();
    }
	public int getDamageReceived() {
		return damageReceived;
	}
	public void setImg(String f) {
		super.setImg(f);
		imgName=f;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String a) {
		character=a;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getJumps() {
		return jumps;
	}
	public void setJumps(int jumps) {
		this.jumps = jumps;
	}
	public int getTotalJumps() {
		return totalJumps;
	}
	public void setTotalJumps(int totalJumps) {
		this.totalJumps = totalJumps;
	}
	public int getMaxFallSpeed() {
		return maxFallSpeed;
	}
	public void setMaxFallSpeed(int maxFallSpeed) {
		this.maxFallSpeed = maxFallSpeed;
	}
	public Sprite getAttack() {
		return attack;
	}
	public void setAttack(Sprite attack) {
		this.attack = attack;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isNormalAttack() {
		return normalAttack;
	}
	public void setNormalAttack(boolean normalAttack) {
		this.normalAttack = normalAttack;
	}
	public boolean isSpecialAttack() {
		return specialAttack;
	}
	public void setSpecialAttack(boolean specialAttack) {
		this.specialAttack = specialAttack;
	}
	public int getAnimationDelay() {
		return animationDelay;
	}
	public void setAnimationDelay(int animationDelay) {
		this.animationDelay = animationDelay;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public void setCooldown(int a) {
		cooldown=a;
	}
	public int getCooldown() {
		return cooldown;
	}
	public void tick() {
		cooldown--;
		unresponsive--;
		invincible--;
	}
	public int getResponsive() {
		return unresponsive;
	}
	public boolean defenseMode() {
		// TODO Auto-generated method stub
		return false;
	}
	public double getStartX() {
		return startX;
	}
	public void setStartX(double startX) {
		this.startX = startX;
	}
	public double getStartY() {
		return startY;
	}
	public void setStartY(double startY) {
		this.startY = startY;
	}
	public int getLives() {
		return lives;
	}
	public int getInvincible() {
		return invincible;
	}
}
