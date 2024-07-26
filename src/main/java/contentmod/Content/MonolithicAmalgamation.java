package contentmod.Content;

import necesse.engine.localization.Localization;
import necesse.engine.util.GameBlackboard;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.gfx.drawOptions.itemAttack.ItemAttackDrawOptions;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.toolItem.swordToolItem.SwordToolItem;


// Extends CustomSwordToolItem
public class MonolithicAmalgamation extends SwordToolItem {
    public MonolithicAmalgamation() {
        super(800);
        rarity = Rarity.EPIC;
        width = 15.0F;
        attackAnimTime.setBaseValue(300);
        attackDamage.setBaseValue(50);
        this.attackXOffset = 16;
        this.attackYOffset = 16;

    }

    @Override
    public ListGameTooltips getPreEnchantmentTooltips(InventoryItem item, PlayerMob perspective, GameBlackboard blackboard) {
        ListGameTooltips tooltips = super.getPreEnchantmentTooltips(item, perspective, blackboard);
        tooltips.add(Localization.translate("itemtooltip", "monoamal"));
        return tooltips;

    }
}