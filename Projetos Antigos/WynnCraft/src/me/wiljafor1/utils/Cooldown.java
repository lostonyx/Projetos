package me.wiljafor1.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cooldown {

    private static Map<String, Cooldown> cooldowns = new HashMap<String, Cooldown>();
    private long start;
    private final int timeInSeconds;
    private final UUID id;
    private final String cooldownName;
    
    public Cooldown(UUID id, String cooldownName, int timeInSeconds){
        this.id = id;
        this.cooldownName = cooldownName;
        this.timeInSeconds = timeInSeconds;
    }

    public boolean isInCooldown(String cooldownName){
        if(getTimeLeft(cooldownName)>=1){
            return true;
        } else {
            stop(id, cooldownName);
            return false;
        }
    }

    private static void stop(UUID id, String cooldownName){
        Cooldown.cooldowns.remove(id+cooldownName);
    }

    private static Cooldown getCooldown(UUID id, String cooldownName){
        return cooldowns.get(id.toString()+cooldownName);
    }

    public int getTimeLeft(String cooldownName){
        Cooldown cooldown = getCooldown(id, cooldownName);
        int f = -1;
        if(cooldown!=null){
            long now = System.currentTimeMillis();
            long cooldownTime = cooldown.start;
            int totalTime = cooldown.timeInSeconds;
            int r = (int) (now - cooldownTime) / 1000;
            f = (r - totalTime) * (-1);
        }
        return f;
    }
    
    public double getTimeLeftDouble(String cooldownName){
        Cooldown cooldown = getCooldown(id, cooldownName);
        double f = -1;
        if(cooldown!=null){
            long now = System.currentTimeMillis();
            long cooldownTime = cooldown.start;
            int totalTime = cooldown.timeInSeconds;
            double r = (double) (now - cooldownTime) / 1000;
            f = (r - totalTime) * (-1);
        }
        return f;
    }

    public void start(){
        this.start = System.currentTimeMillis();
        cooldowns.put(this.id.toString()+this.cooldownName, this);
    }

}