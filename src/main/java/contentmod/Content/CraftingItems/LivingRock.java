package contentmod.Content.CraftingItems;

import necesse.inventory.item.matItem.MatItem;

public class LivingRock extends MatItem {
    public String tooltipKey;

    public LivingRock() {
        super(100);
        tooltipKey = "Liferock";
        rarity = Rarity.RARE;


    }

}