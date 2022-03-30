package me.TahaCheji.levelData;


import org.bukkit.entity.Player;

public enum LevelsXpTo {

    ONE(100),
    TWO(LevelsXpTo.ONE.getXp() * 2),
    THREE(LevelsXpTo.TWO.getXp() * 2),
    FOUR(LevelsXpTo.THREE.getXp() * 2),
    FIVE(LevelsXpTo.FOUR.getXp() * 2),
    SIX(LevelsXpTo.FIVE.getXp() * 2),
    SEVEN(LevelsXpTo.SIX.getXp() * 2),
    EIGHT(LevelsXpTo.SEVEN.getXp() * 2),
    NINE(LevelsXpTo.EIGHT.getXp() * 2),
    TEN(LevelsXpTo.NINE.getXp() * 2);

    private final int xp;

    LevelsXpTo(int xp) {
        this.xp = xp;
    }

    public int getXp() {
        return xp;
    }

    public static LevelsXpTo getXpTo (Player player) {
        if(new PlayerLevels(player).getLevel() == 0) {
            return LevelsXpTo.ONE;
        }
        if(new PlayerLevels(player).getLevel() == 1) {
            return LevelsXpTo.TWO;
        }
        if(new PlayerLevels(player).getLevel() == 2) {
            return LevelsXpTo.THREE;
        }
        if(new PlayerLevels(player).getLevel() == 3) {
            return LevelsXpTo.FOUR;
        }
        if(new PlayerLevels(player).getLevel() == 4) {
            return LevelsXpTo.FIVE;
        }
        if(new PlayerLevels(player).getLevel() == 5) {
            return LevelsXpTo.SIX;
        }
        if(new PlayerLevels(player).getLevel() == 6) {
            return LevelsXpTo.SEVEN;
        }
        if(new PlayerLevels(player).getLevel() == 7) {
            return LevelsXpTo.EIGHT;
        }
        if(new PlayerLevels(player).getLevel() == 8) {
            return LevelsXpTo.NINE;
        }
        if(new PlayerLevels(player).getLevel() == 9) {
            return LevelsXpTo.TEN;
        }
        return null;
    }
}
