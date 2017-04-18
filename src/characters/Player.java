package characters;

import javax.management.timer.Timer;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import main.Main;
import states.GameState;

public class Player {
	private Image PLAYER_IMAGE;
	private Image PROJECTILE_IMAGE;
	private Rectangle player;
	public Rectangle projectile;
	private Vector2f projectileVelocity;
	private final int PLAYER_WIDTH = 52;
	private final int PLAYER_HEIGHT = 32;
	private final int PROJECTILE_WIDTH = 2;
	private final int PROJECTILE_HEIGHT = 12;
	public boolean canShoot = true;
	public boolean spaceState = false;
	
	public Player(int x, int y) throws SlickException {
		PLAYER_IMAGE = new Image("textures/tracyDepot2/player.png");
		PROJECTILE_IMAGE = new Image("textures/tracyDepot2/projectile.png");
		player = new Rectangle(x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
		//projectile = new Rectangle(x + 25, y, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);\
		projectile = new Rectangle(1281, 721, PROJECTILE_WIDTH, PROJECTILE_HEIGHT);
		projectileVelocity = new Vector2f(0, -5);
		//g.draw(player);
	}
	
	public void move(GameContainer gc, Graphics g) {
		g.texture(player, PLAYER_IMAGE.getScaledCopy(0.5f), true);
		if (gc.getInput().isKeyDown(Input.KEY_A)) {
			if (player.getMinX() > 0) {
				player.setX(player.getX() - 10.0f);
				GameState.playerXPosition -= 10;
			}
		} else if (gc.getInput().isKeyDown(Input.KEY_D)) {
			if (player.getMaxX() < Main.GAME_WIDTH) {
				player.setX(player.getX() + 10.0f);
				GameState.playerXPosition += 10;
			}
		} else if (gc.getInput().isKeyDown(Input.KEY_W)) {
			if (player.getMaxY() > 0) {
				player.setY(player.getY() - 10.0f);
				GameState.playerYPosition -= 10;
			}
		} else if (gc.getInput().isKeyDown(Input.KEY_S)) {
			if (player.getMinY() < Main.GAME_HEIGHT) {
				player.setY(player.getY() + 10.0f);
				GameState.playerYPosition += 10;
			}
		}
		
	}
	
	
	public void shoot(GameContainer gc, Graphics g) {
		g.texture(projectile, PROJECTILE_IMAGE, true);
		//projectile.setLocation(projectile.getX() + projectileVelocity.getX(), projectile.getY() + projectileVelocity.getY());
		

		if(gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
			spaceState = true;
			if (canShoot == true) {
				projectile.setLocation(player.getX() + 25, player.getY());
				canShoot = false;
			}
		}
		
		if (spaceState == true) {
			
			if (projectile.getMaxY() > 0 && !GameState.largeInvader0.isBeingShot() &&
											!GameState.largeInvader0.isDead &&
											!GameState.largeInvader1.isBeingShot() &&
											!GameState.largeInvader1.isDead) {
				projectile.setLocation(projectile.getX() + projectileVelocity.getX(), projectile.getY() + projectileVelocity.getY());
				canShoot = false;
			} else {
				//projectile.setLocation(player.getX() + 25, player.getY());
				projectile.setLocation(1281, 721);
				spaceState = false;
				canShoot = true;
			}
		}
			
	
	
	}
	
	public void shoot() {
		
	}
}
