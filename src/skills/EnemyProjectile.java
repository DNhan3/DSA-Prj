package skills;

import main.GamePanel;

public class EnemyProjectile {

    public int x, y;
    public int tx, ty;
    public int speed = 10;
    public int damage = 2;
    public boolean active = true;
    private int range = 300;
    private GamePanel gp;
    private double dx, dy, len;

    public EnemyProjectile(int x, int y, int tx, int ty, int damage, GamePanel gp) {
        this.x = x;
        this.y = y;
        this.tx = tx;
        this.ty = ty;
        this.damage = damage;
        this.gp = gp;
        dx = tx - x;
        dy = ty - y;
        len = Math.sqrt(dx * dx + dy * dy);
        dx = dx / len * speed;
        dy = dy / len * speed;
    }

    public void update() {
        x += dx;
        y += dy;

        if (Math.abs(x - gp.player.worldX) < 10 && Math.abs(y - gp.player.worldY) < 10) {
            gp.player.damageHp(damage);
            active = false;
        }

        range -= speed;
        if (range <= 0) {
            active = false;
            return;
        }
        return;
    }

    public void draw(java.awt.Graphics g2) {
        g2.setColor(java.awt.Color.RED);
        g2.fillOval(x - 8 - gp.screenManagement.getScreenX(), y - 8 - gp.screenManagement.getScreenY(), 16, 16);
    }
}
