package utils;

import main.GamePanel;

import java.awt.Graphics2D;
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
    GamePanel gp;
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
        this.gp = gp;
        addEnemy(Crep.class, 5);
        addEnemy(MiasmaMage.class, 5);
        addEnemy(ElderLich.class, 5);
        addEnemy(Warbeast.class, 1);
        addEnemy(BossManticore.class, 1);
    }

    public void addEnemy(Class<? extends Entity> enemyClass, int count) {
        for (int i = 0; i < count; i++) {
            try {
                Entity enemy = enemyClass.getDeclaredConstructor(GamePanel.class).newInstance(gp);
                enemy.setRandomPosition();
                enemies.add(enemy);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void enemiesGrowth() {
        for (Entity enemy : enemies) {
            enemy.growth();
        }

        addEnemy(Crep.class, 5);
        addEnemy(MiasmaMage.class, 5);
        addEnemy(ElderLich.class, 5);
        addEnemy(Warbeast.class, 1);
        addEnemy(BossManticore.class, 1);
    }

    public void proccessEnemiesOutOfBound() {
        for (Entity enemy : enemies) {
            if (enemy.worldX < 1.5 * Constant.tileSize) {
                enemy.worldX = (int) (1.5 * Constant.tileSize);
            }
            if (enemy.worldY < 1.5 * Constant.tileSize) {
                enemy.worldY = (int) (1.5 * Constant.tileSize);
            }
            if (enemy.worldX > (Constant.maxWorldCol - 1.5) * Constant.tileSize) {
                enemy.worldX = (int) ((Constant.maxWorldCol - 1.5) * Constant.tileSize);
            }
            if (enemy.worldY > (Constant.maxWorldRow - 1.5) * Constant.tileSize) {
                enemy.worldY = (int) ((Constant.maxWorldRow - 1.5) * Constant.tileSize);
            }
        }
    }

    public void updateEnemies() {
        for (Entity enemy : enemies) {
            if (!enemy.isInFrame() && !enemy.isAlive()) {
                if (!enemy.isAlive()) {
                    enemy.worldX = 1 + random.nextInt(Constant.maxWorldCol * Constant.tileSize - 1);
                    enemy.worldY = 1 + random.nextInt(Constant.maxWorldRow * Constant.tileSize - 1);
                    enemy.revive();
                    continue;
                }
                continue;
            }
            if (!enemy.isInFrame()) {
                continue;
            }
            proccessEnemiesOutOfBound();
            enemy.update();
        }
    }

    public void drawEnemies(Graphics2D g2) {
        for (Entity enemy : enemies) {
            if (!enemy.isAlive() || !enemy.isInFrame()) {
                continue;
            }
            enemy.draw(g2);
        }
    }

    public ArrayList<Entity> getEnemies() {
        return enemies;
    }
}
