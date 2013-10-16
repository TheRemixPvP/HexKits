package me.theremixpvp.hexkits.utils;

import org.bukkit.potion.PotionEffectType;

public enum PotionType {
	
	ABSORPTION(PotionEffectType.ABSORPTION),
	BLINDNESS(PotionEffectType.BLINDNESS),
	NAUSEA(PotionEffectType.CONFUSION),
	RESISTANCE(PotionEffectType.DAMAGE_RESISTANCE),
	FIRE_RESISTANCE(PotionEffectType.FIRE_RESISTANCE),
	HASTE(PotionEffectType.FAST_DIGGING),
	FATIGUE(PotionEffectType.SLOW_DIGGING),
	DAMAGE(PotionEffectType.HARM),
	HEALTH(PotionEffectType.HEAL),
	HEALTHBOOST(PotionEffectType.HEALTH_BOOST),
	HUNGER(PotionEffectType.HUNGER),
	STRENGTH(PotionEffectType.INCREASE_DAMAGE),
	INVISIBILITY(PotionEffectType.INVISIBILITY),
	JUMPBOOST(PotionEffectType.JUMP),
	NIGHTVISION(PotionEffectType.NIGHT_VISION),
	POISON(PotionEffectType.POISON),
	REGEN(PotionEffectType.REGENERATION),
	SATURATION(PotionEffectType.SATURATION),
	SLOWNESS(PotionEffectType.SLOW),
	SPEED(PotionEffectType.SPEED),
	WATERBREATHING(PotionEffectType.WATER_BREATHING),
	WEAKNESS(PotionEffectType.WEAKNESS),
	WITHER(PotionEffectType.WITHER);
	
	private PotionEffectType type;
	
	private PotionType(PotionEffectType type) { this.type = type; }
	
	public PotionEffectType getType() {
		return this.type;
	}

}
