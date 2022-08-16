package contentmod.Content.CraftingItems;

import necesse.inventory.item.matItem.MatItem;

public class StickyLiquid extends MatItem {
    public String tooltipKey;
    public StickyLiquid() {
        super(100);
        tooltipKey = "oddliquid";
        rarity = Rarity.UNCOMMON;
    }

}