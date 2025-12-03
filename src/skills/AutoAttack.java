package skills;

import java.awt.Point;
import entity.Entity;
import main.GamePanel;

public class AutoAttack extends Skill {
    public AutoAttack(GamePanel gp) {
        super(gp);
        this.name = "Auto Attack";
        this.cooldown = 1.0f; // 1 second cooldown
        this.manaCost = 0.0f; // no mana cost
    }

    @Override
    public void activate(Entity caster, Point target) {
        // // Implementation of auto attack logic
        // int damage = caster.getAttackPower();
        // // Point target = gp.mouseTrack.getMouseWorldPosition(gp);
        // Projectile projectile = new Projectile(gp, target, caster, damage, 10, 500);
        // gp.projectileManager.addProjectile(projectile);
        // System.out.println(caster.getName() + " attacks for " + damage + " damage.");
    }

}
