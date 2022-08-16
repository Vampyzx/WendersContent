package contentmod.Content.StatusEffects;

import necesse.engine.util.GameRandom;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.entity.mobs.buffs.staticBuffs.Buff;
import necesse.entity.particle.Particle;

import java.awt.*;


public class oddballdebuff extends Buff {

    public oddballdebuff() {
        canCancel = false;
    }

    @Override
    public void init(ActiveBuff activeBuff) {
        // Apply modifiers here
        activeBuff.setModifier(BuffModifiers.SPEED, -0.5F); // -50% speed
        activeBuff.setModifier(BuffModifiers.ATTACK_SPEED, -0.3F); // -30% atk speed
    }

    @Override
    public void serverTick(ActiveBuff buff) {
        // You can do server ticks here
    }

    @Override
    public void clientTick(ActiveBuff buff) {
        // You can do client ticks here, like adding particles to buff.owner
        super.clientTick(buff);
        if (buff.owner.isVisible()) {
            Mob owner = buff.owner;
            owner.getLevel().entityManager.addParticle(owner.x + (float)(GameRandom.globalRandom.nextGaussian() * 6.0), owner.y + (float)(GameRandom.globalRandom.nextGaussian() * 8.0), Particle.GType.IMPORTANT_COSMETIC).movesConstant(owner.dx / 10.0F, owner.dy / 10.0F).color(new Color(255, 255, 255)).height(16.0F);
        }

    }
}