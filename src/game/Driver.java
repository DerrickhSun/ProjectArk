package game;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import attacks.Attack;
import attacks.NoirShield;
import object.Background;
import object.NoirCorne;
import object.Platform;
import object.Player;
import object.SilverAsh;
import object.Sprite;
import object.Warfarin;

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	int mouseX, mouseY;
	int numberPlayers=10;
	boolean pressed;
	Background bg;
	ArrayList<Platform> platforms =new ArrayList<Platform>();
	ArrayList<Player> players=new ArrayList<Player>();
	ArrayList<Attack> attacks=new ArrayList<Attack>();
	boolean mapChosen=false;
	Sprite cursorTracker = new Sprite("../resources/dart1.png", 1500, 100);
	String screen="start";
	String selecting=" ";
	private Font font = new Font("Courier New", Font.BOLD, 50);
	private Font font2 = new Font("Courier New", Font.BOLD, 30);
	private Font font3 = new Font("Courier New", Font.BOLD, 75);
	private Font font4 = new Font("Courier New", Font.BOLD, 25);
	
	
	public static void main(String []args) {
		Driver a = new Driver();
		while(true) {
			a.update();
			a.repaint();
		}
	}
	public Driver() {
		JFrame f = new JFrame();
		f.setTitle("Ark");
		int screenHeight=1000;
		int screenWidth=1500;
		f.setSize(screenWidth, screenHeight);
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseMotionListener(this);
		f.addMouseListener(this);
		
		//creating the background
		bg = new Background("../resources/Space.jpg", -200, -200);
		//creating platforms
		platforms.add(new Platform("../resources/rawplatform.JPG",100,600,1300));
		f.add(this);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	public void update() {
		if(screen.equals("character selection")) {
			if(selecting.equals("SilverAsh")) {
				if(players.size()==0) {players.add(new SilverAsh(400,450));}
				else {players.add(new SilverAsh(950,450));}
				delay(300);
				selecting=" ";
			}
			if(selecting.equals("Warfarin")) {
				if(players.size()==0) {
					players.add(new Warfarin(400,450));
				}
				else {players.add(new Warfarin(950,450));}
				delay(300);
				selecting=" ";
			}
			if(selecting.equals("NoirCorne")) {
				if(players.size()==0) {
					players.add(new NoirCorne(400,450));
				}
				else {players.add(new NoirCorne(950,450));}
				delay(300);
				selecting=" ";
			}
		}
		if(screen.equals("stage selection")){
			if(selecting.equals("map1")) {
				selecting=" ";
				mapChosen=true;
			}
		}
		if(screen.equals("fight")) {
			fightUpdate();
		}
	}
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		double[] dimensions=scale();
		super.paintComponent(g2);
		bg.paint(g2);
		cursorTracker.paint(g2);
		
		//start screen
		if(screen.equals("start")){
			startPaint(g2);
		}
//*********************************************************************
		//character selection screen
		if(players.size()==numberPlayers) {
			screen="stage selection";
		}

		if(screen.equals("character selection")) {
			characterSelectionPaint(g2);
		}
//*********************************************************************
		//stage selection screen, to be added;
		if(mapChosen==true) {
			screen="fight";
		}
		
		if(screen.equals("stage selection")) {
			Button selectMap1=new Button(100,150,150,150, "../SilverAsh/SilverAsh Portrait.png");
			selected(selectMap1);
			selectMap1.paint(g2);
			if(selectMap1.selected(mouseX, mouseY)&&pressed) {
				selecting="map1";
			}
		}
//*********************************************************************
		//fight
		if(screen.equals("fight")) {
			fightPaint(g, g2, dimensions);
		}
	}
//*********************************************************************

	//update subsections
	public void fightUpdate(){
		delay(50);
			for(Player a:players) {
				boolean footing=false;
				for(Platform b:platforms) {
					if(intersecting(a,b)) {
						footing=true;
						a.setY(b.getY()-b.getHeight()-30);
						a.setJumps(a.getTotalJumps());
					}
				}
				a.move(footing);
				
				if(a.isSpecialAttack()&&a.getCooldown()<=0&&a.getResponsive()<=0) {
					attacks.add(a.specialAttack());
				}
				else if(a.isNormalAttack()&&a.getCooldown()<=0&&a.getResponsive()<=0) {
					attacks.add(a.regularAttack());
				}
				for(Attack b:attacks) {
					b.move();
					if(b.getDuration()>0) {
						if(touching(a,b)&&b.getAttacker()!=a&&!b.hitList().contains(a)&&a.getInvincible()<=0) {
							if(a instanceof NoirCorne && a.defenseMode() && a.getDirection()!=b.getDirection()) {
								a.takeDamage(Math.min(1, b.getDamage()),b.getKnockback()/2,b.getStun(),b.getDirection().equals("right"));
							}
							else if(b instanceof NoirShield){
								a.takeDamage(b.getDamage(),b.getKnockback(),b.getStun(),b.getX()<a.getX());
							}
							else{
								a.takeDamage(b.getDamage(),b.getKnockback(),b.getStun(),b.getDirection().equals("right"));
								if(b.getAttacker() instanceof Warfarin) {
									b.getAttacker().restoreHealth(1);
								}
							}
							b.hit(a);
						}
					}
					b.tick();
				}
				
				a.tick();
			isDead(a);
			}
	}
	
	//paint subsections
	public void startPaint(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect(400, 300, 650, 150);
		g2.setFont(font);
		g2.setColor(Color.BLACK);
		g2.drawString("Projekt Ark", 540, 390);
		g2.setColor(Color.RED);
		g2.drawString("Projekt Ark", 550, 400);
		//start buttons
		
		Button startButton1=new Button(175, 600, 300, 75);
		selected(startButton1);
		drawButton(startButton1, g2, "1 Player");
		if(startButton1.selected(mouseX, mouseY)&&pressed) {
			numberPlayers=1;
			screen="character selection";
		}
		
		Button startButton=new Button(575, 600, 300, 75);
		selected(startButton);
		drawButton(startButton, g2, "2 Players");
		if(startButton.selected(mouseX, mouseY)&&pressed) {
			numberPlayers=2;
			screen="character selection";
		}
		
		Button startButton3=new Button(975, 600, 300, 75);
		selected(startButton3);
		drawButton(startButton3, g2, "3 Players");
		if(startButton3.selected(mouseX, mouseY)&&pressed) {
			numberPlayers=3;
			screen="character selection";
		}
	}	
	public void characterSelectionPaint(Graphics2D g2) {
		if(players.size()==0) {
			g2.setColor(Color.RED);
		}
		else if(players.size()==1) {
			g2.setColor(Color.BLUE);
		}
		else {
			g2.setColor(Color.GREEN);
		}
		g2.setFont(font2);
		g2.drawString("Player "+(players.size()+1), 700, 50);
		g2.setFont(font);
		g2.drawString("Select your character", 450, 100);
		
		Button selectSilverAsh=new Button(100,150,150,150, "../SilverAsh/SilverAsh Portrait.png");
		selected(selectSilverAsh);
		selectSilverAsh.paint(g2);
		if(selectSilverAsh.selected(mouseX, mouseY)&&pressed) {
			selecting="SilverAsh";
		}
		
		Button selectWarfarin=new Button(300,150,150,150, "../Warfarin/Warfarin Portrait.png");
		selected(selectWarfarin);
		selectWarfarin.paint(g2);
		if(selectWarfarin.selected(mouseX, mouseY)&&pressed) {
			selecting="Warfarin";
		}
		
		Button selectNoirCorne=new Button(500,150,150,150, "../NoirCorne/NoirCorne Portrait.png");
		selected(selectNoirCorne);
		selectNoirCorne.paint(g2);
		if(selectNoirCorne.selected(mouseX, mouseY)&&pressed) {
			selecting="NoirCorne";
		}
		
		Button selectFrostnova=new Button(700,150,150,150, "../Frostnova/Frostnova Portrait.png");
		selected(selectFrostnova);
		selectFrostnova.paint(g2);
		if(selectFrostnova.selected(mouseX, mouseY)&&pressed) {
			selecting="NoirCorne";
		}
	}
	public void fightPaint(Graphics g, Graphics2D g2, double[] dimensions) {
		adjust(g2, (int)dimensions[0], (int)dimensions[1], dimensions[2]);
			for(Sprite a:platforms) {
				a.paint(g2);
			}
			for(Player a:players) {
				directionalPaint(g2, a);
			}
			for(Attack a:attacks) {
				if(a.getDuration()>=0) {
					directionalPaint(g2, a);
				}
			}
			adjust(g2, -(int)dimensions[0], -(int)dimensions[1], 1);
			adjust(g2, 0, 0, 1/dimensions[2]);
			int numberAlive=0;
			for(Player a:players) {
				a.paintIcon(g,players.indexOf(a)+1);
				if(a.getLives()>0)numberAlive+=1;
			}
			if(numberAlive<=1) {
				win(g2);
			}
	}
	
	//code for winning
	public void win(Graphics2D g2) {
		Button winner=new Button(600,450,300,100);
		if(players.size()==1) {
			winner.setX(1100);
			winner.setY(800);
			selected(winner);
			drawButton(winner, g2, "Return");
		}
		else {
			selected(winner);
			drawButton(winner, g2, "You win!");
		}
		if(winner.selected(mouseX, mouseY)&&pressed) {
			screen="start";
			mapChosen=false;
			players=new ArrayList<Player>();
			attacks=new ArrayList<Attack>();
		}
	}
	public void isDead(Player a) {
		if(a.getY()>1500) {
			a.die();
		}
	}
	
	//code for convenience
	public void directionalPaint(Graphics g, Sprite actor) {
		if(actor.getDirection().equals("right")) {
			actor.paint(g);
		}
		else {
			actor.flipPaint(g);
		}
	}
	
	//scrolling code
	public double[] scale() {
		double[] result=new double[3];
		if(players.size()==0) {
			result[0] = 0.0;
			result[1] = 0.0;
			result[2] = 1.0;
			return result;
		}
		int smallestX=750;//players.get(0).getX()+players.get(0).getWidth()/2;
		int largestX=750;//players.get(0).getX()+players.get(0).getWidth()/2;
		int smallestY=500;//players.get(0).getY()+players.get(0).getHeight()/2;
		int largestY=500;//players.get(0).getY()+players.get(0).getHeight()/2;
		for(Player player:players) {
			if(player.getX()+player.getWidth()/2<smallestX&&player.getLives()>0)smallestX=Math.max(player.getX()+player.getWidth()/2, 0);
			if(player.getY()+player.getHeight()/2<smallestY&&player.getLives()>0)smallestY=player.getY()+player.getHeight()/2;
			if(player.getX()+player.getWidth()/2>largestX&&player.getLives()>0)largestX=Math.min(player.getX()+player.getWidth()/2, 1500);
			if(player.getY()+player.getHeight()/2>largestY&&player.getLives()>0)largestY=Math.min(player.getY()+player.getHeight()/2, 700);
		}
		result[2]=Math.min(Math.min(0.4+400.0/(largestY-smallestY+300), 0.4+600.0/(largestX-smallestX+300)), 1.3);
		result[0]=-((largestX+smallestX)/2-750);
		result[1]=-((largestY+smallestY)/2-500);
		return result;
	}
	public void adjust(Graphics2D g2, int x, int y, double scale) {
		g2.translate(750, 500);
		g2.scale(scale,scale);
		g2.translate(-750, -500);
		g2.translate(x, y);
	}
	
	//contact-checking code, intersecting for with ground, touching for attacks and players
	public boolean intersecting(Sprite a, Sprite b) {
		boolean sides=false;
		boolean top=false;
		int aUp=a.getY();
		int aDown=a.getY()+a.getHeight();
		int bUp=b.getY();
		int bDown=b.getY()+b.getHeight();
		int bCenterX=b.getX()+b.getWidth()/2;
		int aCenterX=a.getX()+a.getWidth()/2;
		
		if(Math.abs(bCenterX-aCenterX)<(b.getWidth())/2)sides=true;
		
		if(Math.abs(aDown-bUp)<30)top=true;
		return sides&&top;
	}
	public boolean touching(Sprite a, Sprite b) {
		boolean sides=false;
		boolean top=false;
		int aUp=a.getY();
		int aDown=a.getY()+a.getHeight();
		int bUp=b.getY();
		int bDown=b.getY()+b.getHeight();
		int bCenterX=b.getX()+b.getWidth()/2;
		int aCenterX=a.getX()+a.getWidth()/2;
		
		if(Math.abs(bCenterX-aCenterX)<(a.getWidth()+b.getWidth())/2)sides=true;
		
		if(aUp>bUp&&aUp<bDown)top=true;
		else if(bUp>aUp&&bUp<aDown)top=true;
		else if(bDown<aDown&&bDown>aUp)top=true;
		else if(aDown<bDown&&aDown>bUp)top=true;
		else if((aDown+aUp)/2<bDown&&(aDown+aUp)/2>bUp)top=true;
		return sides&&top;
	}
	
	//button code
	public void selected(Button a) {
		if(a.selected(mouseX, mouseY)) {
				a.setX(a.getX()-5);
				a.setY(a.getY()-5);
				a.setWidth(a.getWidth()+10);
				a.setHeight(a.getHeight()+10);
		}
	}
	public void drawButton(Button a, Graphics g, String title) {
		g.setColor(Color.WHITE);
		g.fillRect(a.getX(), a.getY(), a.getWidth(), a.getHeight());
		g.setColor(Color.RED);
		g.fillRect(a.getX()+2, a.getY()+2, a.getWidth()-4, a.getHeight()-4);
		g.setColor(Color.BLACK);
		g.setFont(font2);
		g.drawString(title, a.getX()+(a.getWidth()/2)-(title.length()*9), a.getY()+(a.getHeight()/2)+5);
	}
	
	@Override
	
	public void actionPerformed(ActionEvent arg0) {
	}
	
	public void delay(int mill) {
		double now = System.currentTimeMillis();
		while (System.currentTimeMillis() - now < mill) {

		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		pressed = false;
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY()-40;
	}
	@Override
	public void mouseExited(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY()-40;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY()-40;
		pressed = true;

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY()-40;
		pressed = false;
		/*if(screen.equals("character selection")) {
			if(mouseX>10)
		}*/
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY()-40;
		pressed = false;

	}
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY()-40;
		// TODO Auto-generated method stub

	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if(players.size()>=1) {
			if(arg0.getKeyCode()==65) {
				players.get(0).setLeft(true);
			}
			else if(arg0.getKeyCode()==68) {
				players.get(0).setRight(true);
			}
			else if(arg0.getKeyCode()==87) {
				for(Platform b:platforms) {
					if(players.get(0).getJumps()>0&&players.get(0).getResponsive()<=0) {
						players.get(0).jump();
						players.get(0).setJumps(players.get(0).getJumps()-1);
					}
				}
				players.get(0).setUp(true);
			}
			else if(arg0.getKeyCode()==83) {
				players.get(0).setDown(true);
			}
			else if(arg0.getKeyCode()==70) {
				players.get(0).setNormalAttack(true);
			}
			if(arg0.getKeyCode()==71) {
				players.get(0).setSpecialAttack(true);
			}
		}
		if(players.size()>=2) {
			if(arg0.getKeyCode()==37) {
				players.get(1).setLeft(true);
			}
			if(arg0.getKeyCode()==39) {
				players.get(1).setRight(true);
			}
			if(arg0.getKeyCode()==38) {
				players.get(0).setUp(true);
				for(Platform b:platforms) {
					if(players.get(1).getJumps()>0&&players.get(1).getResponsive()<=0) {
						players.get(1).jump();
						players.get(1).setJumps(players.get(1).getJumps()-1);
					}
				}
			}
			if(arg0.getKeyCode()==40) {
				players.get(1).setDown(true);
			}
			if(arg0.getKeyCode()==44) {
				players.get(1).setNormalAttack(true);
			}
			if(arg0.getKeyCode()==46) {
				players.get(1).setSpecialAttack(true);
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(players.size()>=1) {
			if(arg0.getKeyCode()==65) {
				players.get(0).setAx(0);
				players.get(0).setLeft(false);
			}
			if(arg0.getKeyCode()==68) {
				players.get(0).setAx(0);
				players.get(0).setRight(false);
			}
			if(arg0.getKeyCode()==87) {
				players.get(0).setUp(false);
			}
			if(arg0.getKeyCode()==83) {
				players.get(0).setDown(false);
			}
			if(arg0.getKeyCode()==70) {
				players.get(0).setNormalAttack(false);
			}
			if(arg0.getKeyCode()==71) {
				players.get(0).setSpecialAttack(false);
			}
			
		}
		if(players.size()>=2) {
			if(arg0.getKeyCode()==37) {
				players.get(1).setAx(0);
				players.get(1).setLeft(false);
			}
			if(arg0.getKeyCode()==39) {
				players.get(1).setAx(0);
				players.get(1).setRight(false);
			}
			if(arg0.getKeyCode()==38) {
				players.get(1).setUp(false);
			}
			if(arg0.getKeyCode()==40) {
				players.get(1).setDown(false);
			}
			if(arg0.getKeyCode()==44) {
				players.get(1).setNormalAttack(false);
			}
			if(arg0.getKeyCode()==46) {
				players.get(1).setSpecialAttack(false);
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
