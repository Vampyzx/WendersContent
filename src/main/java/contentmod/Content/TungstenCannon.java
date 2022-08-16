package contentmod.Content;

import contentmod.Content.Projectiles.tungstenball;
import necesse.engine.Screen;
import necesse.engine.localization.Localization;
import necesse.engine.network.PacketReader;
import necesse.engine.network.packet.PacketSpawnProjectile;
import necesse.engine.sound.SoundEffect;
import necesse.engine.util.GameRandom;
import necesse.entity.mobs.AttackAnimMob;
import necesse.entity.mobs.GameDamage;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.projectile.Projectile;
import necesse.gfx.GameResources;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.PlayerInventorySlot;
import necesse.inventory.item.toolItem.projectileToolItem.ProjectileToolItem;
import necesse.level.maps.Level;

import java.awt.*;

public class TungstenCannon extends ProjectileToolItem {

    public TungstenCannon() {
        super(1600);
        this.rarity =  Rarity.EPIC;
        this.animSpeed = 1000; // 1 (1 second(s)) ms attack time
        this.attackDmg = new GameDamage(GameDamage.DamageType.MELEE, 80.0F); // 80 ranged damage;
        this.velocity = 400; // Velocity of projectiles
        this.knockback = 750; // Knockback of projectiles
        this.attackRange = 960; // Range of the projectile
        // Offsets of the attack item sprite relative to the player arm
        this.attackXOffset = 20;
        this.attackYOffset = 20;
    }

    @Override
    public ListGameTooltips getTooltips(InventoryItem item, PlayerMob perspective) {
        ListGameTooltips tooltips = super.getTooltips(item, perspective);
        tooltips.add(Localization.translate("itemtooltip", "tungstencannon"));
        tooltips.add(getAttackDamageTip(item, perspective)); // Add attack damage to tooltip
        tooltips.add(getAttackSpeedTip(item, perspective)); // Adds attack speed to tooltip
        addCritChanceTip(tooltips, item, perspective); // Adds crit chance if above 0%
        return tooltips;
    }

    @Override
    public void showAttack(Level level, int x, int y, AttackAnimMob mob, int attackHeight, InventoryItem item, int seed, PacketReader contentReader) {
        if (level.isClientLevel()) {
            // Play magic bolt sound effect with 70% volume, and a random pitch between 100 and 110%
            Screen.playSound(GameResources.magicbolt1, SoundEffect.effect(mob)
                    .volume(1.0f)
                    .pitch(GameRandom.globalRandom.getFloatBetween(0.5f, 0.7f)));
        }
    }

    @Override
    public InventoryItem onAttack(Level level, int x, int y, PlayerMob player, int attackHeight, InventoryItem item, PlayerInventorySlot slot, int animAttack, int seed, PacketReader contentReader) {
        Projectile projectile = new tungstenball(
                level, player, // Level and owner
                player.x, player.y, // Start position of projectile
                x, y, // Target position of projectile
                getVelocity(item, player), // Will add player buffs, enchantments etc
                getAttackRange(item), // Will add player buffs, enchantments etc
                getDamage(item), // Will add player buffs, enchantments etc
                getKnockback(item, player) // Will add player buffs, enchantments etc
        );
        // Sync the uniqueID using the given seed
        GameRandom random = new GameRandom(seed);
        projectile.resetUniqueID(random);
        projectile.moveDist(30);
        level.entityManager.projectiles.addHidden(projectile);
        if (level.isServerLevel()) {
            // We do attacking client as an exception, since the above logic is already running on his side
            level.getServer().network.sendToClientsAtExcept(new PacketSpawnProjectile(projectile), player.getServerClient(), player.getServerClient());
        }

        // Should return the item after it's been used.
        // Example: if it consumes the item, you can use item.setAmount(item.getAmount() - 1)
        return item;
    }

}
