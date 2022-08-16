package contentmod.Content;

import contentmod.Content.Projectiles.oddball;
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

public class OddStickyWand extends ProjectileToolItem {

    // This weapon will shoot out some projectiles.
    // Different classes for specific projectile weapon are already in place that you can use:
    // GunProjectileToolItem, BowProjectileToolItem, BoomerangToolItem, etc.

    public OddStickyWand() {
        super(700);
        rarity = Rarity.EPIC;
        animSpeed = 600; // 600 (0.6) ms attack time
        attackDmg = new GameDamage(GameDamage.DamageType.MAGIC, 69); // 45 Magic damage
        velocity = 75; // Velocity of projectiles
        knockback = 15; // Knockback of projectiles
        attackRange = 750; // Range of the projectile


        // Offsets of the attack item sprite relative to the player arm
        attackXOffset = 20;
        attackYOffset = 20;
    }

    @Override
    public ListGameTooltips getTooltips(InventoryItem item, PlayerMob perspective) {
        ListGameTooltips tooltips = super.getTooltips(item, perspective);
        tooltips.add(Localization.translate("itemtooltip", "whitewand"));
        tooltips.add(getAttackDamageTip(item, perspective)); // Add attack damage to tooltip
        tooltips.add(getAttackSpeedTip(item, perspective)); // Adds attack speed to tooltip
        addCritChanceTip(tooltips, item, perspective); // Adds crit chance if above 0%
        return tooltips;
    }

    @Override
    public void showAttack(Level level, int x, int y, AttackAnimMob mob, int attackHeight, InventoryItem item, int seed, PacketReader contentReader) {
        if (level.isClientLevel()) {
            // Play magic bolt splash effect with 70% volume, and a random pitch between 100 and 110%
            Screen.playSound(GameResources.splash, SoundEffect.effect(mob)
                    .volume(0.7f)
                    .pitch(GameRandom.globalRandom.getFloatBetween(0.7f, 0.9f)));
        }
    }

    @Override
    public InventoryItem onAttack(Level level, int x, int y, PlayerMob player, int attackHeight, InventoryItem item, PlayerInventorySlot slot, int animAttack, int seed, PacketReader contentReader) {
        // This method is ran on the attacking client and on the server.
        // This means we need to tell other clients that a projectile is being "summoned".
        // Every projectile weapon is set to include an integer seed used to make sure that the attacking client
        // and the server gives the projectiles summoned the same uniqueID.

        // Example we use our example projectile
        Projectile projectile = new oddball(
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

        // We can move the projectile 40 units out
        projectile.moveDist(40);

        // Add the projectile without sending it to clients
        level.entityManager.projectiles.addHidden(projectile);

        // Since we didn't send it to clients, we can do it here
        if (level.isServerLevel()) {
            // We do attacking client as an exception, since the above logic is already running on his side
            level.getServer().network.sendToClientsAtExcept(new PacketSpawnProjectile(projectile), player.getServerClient(), player.getServerClient());
        }

        // Should return the item after it's been used.
        // Example: if it consumes the item, you can use item.setAmount(item.getAmount() - 1)
        return item;
    }

}
