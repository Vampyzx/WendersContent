package contentmod.Content;

import necesse.engine.localization.Localization;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.gfx.drawOptions.itemAttack.ItemAttackDrawOptions;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.toolItem.swordToolItem.CustomSwordToolItem;


// Extends CustomSwordToolItem
public class MonolithicAmalgamation extends CustomSwordToolItem {
    public MonolithicAmalgamation() {
        super(Rarity.LEGENDARY, 1000, 100, 100, 300, 3500);
        this.width = 15.0F;
        this.cooldown = 5;
        this.attackXOffset = 16;
        this.attackYOffset = 16;

    }


    // Weapon attack textures are loaded from resources/player/weapons/<itemStringID>
@Override
    public ListGameTooltips getTooltips(InventoryItem item, PlayerMob perspective) {
        ListGameTooltips tooltips = super.getTooltips(item, perspective);
        tooltips.add(Localization.translate("itemtooltip", "monoamal"));
        tooltips.add(this.getAttackDamageTip(item, perspective));
        tooltips.add(this.getKnockbackTip(item, perspective));
        tooltips.add(this.getAttackSpeedTip(item, perspective));
        this.addCritChanceTip(tooltips, item, perspective);
        return tooltips;


    }
}