package contentmod.Content;

import contentmod.Content.Projectiles.anchorprojectile;
import necesse.engine.localization.Localization;
import necesse.engine.network.PacketReader;
import necesse.engine.network.packet.PacketSpawnProjectile;
import necesse.engine.util.GameRandom;
import necesse.entity.mobs.GameDamage;
import necesse.entity.mobs.GameDamage.DamageType;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.projectile.Projectile;
import necesse.entity.projectile.boomerangProjectile.BoxingGloveBoomerangProjectile;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.PlayerInventorySlot;
import necesse.inventory.item.toolItem.projectileToolItem.throwToolItem.boomerangToolItem.BoomerangToolItem;
import necesse.level.maps.Level;

public class Uendizuanka extends BoomerangToolItem {
    private int deltaSpeed;
    public Uendizuanka() {
        this.enchantCost = 500;
        this.animSpeed = 300;
        this.attackDmg = new GameDamage(DamageType.MELEE, 150.0F);
        this.velocity = 250;
        this.stackSize = 1;
        this.rarity = Rarity.LEGENDARY;
        this.projectileID = "anchorprojectile";
        this.attackRange = 960;
        this.knockback = -350;
    }

    @Override
    public ListGameTooltips getTooltips(InventoryItem item, PlayerMob perspective) {
        ListGameTooltips tooltips = super.getTooltips(item, perspective);
        tooltips.add(Localization.translate("itemtooltip", "wendysanchor"));
        tooltips.add(getAttackDamageTip(item, perspective)); // Add attack damage to tooltip
        tooltips.add(getAttackSpeedTip(item, perspective)); // Adds attack speed to tooltip
        addCritChanceTip(tooltips, item, perspective); // Adds crit chance if above 0%
        return tooltips;
    }
}



