package utils;

import java.util.HashMap;
import java.util.Map;
import skills.Skill;

public class CooldownManager {
    private static CooldownManager instance = null;

    public static CooldownManager getInstance() {
        if (instance == null) {
            instance = new CooldownManager();
        }
        return instance;
    }

    private Map<Skill, Float> cooldowns = new HashMap<>();

    public boolean available(Skill s) {
        return currentTime() >= cooldowns.getOrDefault(s, 0f);
    }

    public void start(Skill s) {
        cooldowns.put(s, currentTime() + s.cooldown);
    }

    private float currentTime() {
        return System.currentTimeMillis() / 1000f;
    }
}
