package contentmod.Content;

import necesse.engine.localization.Localization;
import necesse.engine.util.GameBlackboard;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.toolItem.projectileToolItem.throwToolItem.boomerangToolItem.BoomerangToolItem;

public class WendysAnchor extends BoomerangToolItem {
    private int deltaSpeed;

    public WendysAnchor() {
        super(1500, "anchorprojectile");
        this.rarity = Rarity.LEGENDARY;
        this.stackSize = 1;
        this.attackAnimTime.setBaseValue(300);
        this.attackDamage.setBaseValue(150);
        this.velocity.setBaseValue(250);
        this.projectileID = "anchorprojectile";
        this.attackRange.setBaseValue(1000);
        this.knockback.setBaseValue(-350);
    }


    @Override
    public ListGameTooltips getPreEnchantmentTooltips(InventoryItem item, PlayerMob perspective, GameBlackboard blackboard) {
        ListGameTooltips tooltips = super.getPreEnchantmentTooltips(item, perspective, blackboard);
        tooltips.add(Localization.translate("itemtooltip", "wendysanchor"));
        return tooltips;
    }
}



