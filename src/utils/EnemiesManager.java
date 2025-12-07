package utils;

import main.GamePanel;
import java.util.ArrayList;
import java.util.Random;

import entity.Crep;
import entity.Entity;
import inGameEntity.enemies.BossManticore;
import inGameEntity.enemies.ElderLich;
import inGameEntity.enemies.MiasmaMage;
import inGameEntity.enemies.Warbeast;
import main.Constant;

public class EnemiesManager {
    private static EnemiesManager instance = null;
    private Random random = new Random();

    public static EnemiesManager getInstance(GamePanel gp) {
        if (instance == null) {
            instance = new EnemiesManager(gp);
        }
        return instance;
    }

    private ArrayList<Entity> enemies = new ArrayList<>();

    private EnemiesManager(GamePanel gp) {
        enemies.add(new Crep(gp, (int) (4.5 * Constant.tileSize), (int) (4.5 * Constant.tileSize)));
        enemies.add(new Crep(gp, 10 * Constant.tileSize, 5 * Constant.tileSize));
        enemies.add(new Crep(gp, 15 * Constant.tileSize, 10 * Constant.tileSize));
        enemies.add(new Crep(gp, 20 * Constant.tileSize, 15 * Constant.tileSize));
        enemies.add(new BossManticore(gp, 10 * Constant.tileSize, 10 * Constant.tileSize));
        enemies.add(new BossManticore(gp, 20 * Constant.tileSize, 20 * Constant.tileSize));
        enemies.add(new ElderLich(gp, 10 * Constant.tileSize, 5 * Constant.tileSize));
        enemies.add(new MiasmaMage(gp, 15 * Constant.tileSize, 10 * Constant.tileSize));
        enemies.add(new Warbeast(gp, 20 * Constant.tileSize, 15 * Constant.tileSize));
    }

    public void updateEnemies() {
        for (Entity enemy : enemies) {
            if (!enemy.isInFrame() && !enemy.isAlive()) {
                if (!enemy.isAlive()) {
                    enemy.worldX = random.nextInt(Constant.maxWorldCol * Constant.tileSize);
                    enemy.worldY = random.nextInt(Constant.maxWorldRow * Constant.tileSize);
                    enemy.revive();
                    continue;
                }
                continue;
            }
            if (!enemy.isInFrame()) {
                continue;
            }
            enemy.update();
        }
    }

    public void drawEnemies(java.awt.Graphics g2) {
        for (Entity enemy : enemies) {
            if (!enemy.isAlive()) {
                continue;
            }
            enemy.draw(g2);
        }
    }

    public ArrayList<Entity> getEnemies() {
        return enemies;
    }
}
