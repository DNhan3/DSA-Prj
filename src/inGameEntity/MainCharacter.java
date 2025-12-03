package inGameEntity;

import java.awt.Graphics2D;

import entity.Entity;
import main.GamePanel;
import utils.KeyHandler;

public class MainCharacter extends Entity {
    private static MainCharacter instance = null;

    private MainCharacter(GamePanel gp) {
        super(gp, "MainCharacter", 0, 0, gp.tileSize, gp.tileSize, 
                new java.awt.Rectangle(0, 0, gp.tileSize, gp.tileSize), 
                5, 10, 100, 100,
                new boolean[4],
                new int[4]);
    }

    public static MainCharacter getInstance(GamePanel gp) {
        if (instance == null) {
            instance = new MainCharacter(gp);
        }
        return instance;
    }

    // Projectile 
    

    private void handleMovementInput(KeyHandler keyH) {
        if (keyH.leftPressed) {
            if (!getCollisionOn()[0])
                worldX = worldX - getSpeed();
            else
                worldX = getCollisionTile()[0] * gp.tileSize - this.getColGap();
        } else if (keyH.rightPressed) {
            if (!getCollisionOn()[1])
                worldX = worldX + getSpeed();
            else
                worldX = getCollisionTile()[1] * gp.tileSize - gp.tileSize + this.getColGap() - 1;
        }
        if (keyH.upPressed) {
            if (!getCollisionOn()[2])
                worldY = worldY - getSpeed();
            else
                worldY = getCollisionTile()[2] * gp.tileSize - this.getRowGap();
        } else if (keyH.downPressed) {
            if (!getCollisionOn()[3])
                worldY = worldY + getSpeed();
            else
                worldY = getCollisionTile()[3] * gp.tileSize - gp.tileSize + this.getRowGap() - 1;
        }

    }

    public void draw(Graphics2D g2) {
        g2.setColor(java.awt.Color.RED);
        g2.fillRect(getScreenX(), getScreenY(), gp.tileSize, gp.tileSize);
    }

    public void update(KeyHandler keyH) {
        handleMovementInput(keyH);
        gp.collisionChecker.checkTile(this);
    }
}
