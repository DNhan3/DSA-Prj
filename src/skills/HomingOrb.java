package skills;

import main.GamePanel;

public class HomingOrb {
    private GamePanel gp;
    public int x, y;
    public int speed = 4;
    public int damage = 5;
    public boolean active = true;

    public HomingOrb(int x, int y, GamePanel gp) {
        this.x = x;
        this.y = y;
        this.gp = gp;
    }

    public void update(GamePanel gp) {
        int px = gp.player.worldX;
        int py = gp.player.worldY;

        double dx = px - x;
        double dy = py - y;
        double len = Math.sqrt(dx * dx + dy * dy);

        x += (dx / len) * speed;
        y += (dy / len) * speed;

        // nổ khi đến gần
        if (len < 10) {
            gp.player.damageHp(damage);
            gp.player.knockBack(20, (int)dx, (int)dy);
            active = false;
        }
    }

    public void draw(java.awt.Graphics g2) {
        g2.setColor(java.awt.Color.MAGENTA);
        g2.fillOval(x - 8 - gp.screenManagement.getScreenX(), y - 8 - gp.screenManagement.getScreenY(), 16, 16);
    }
}
