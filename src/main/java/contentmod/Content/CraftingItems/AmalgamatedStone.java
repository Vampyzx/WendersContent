package contentmod.Content.CraftingItems;

import necesse.inventory.item.matItem.MatItem;

public class AmalgamatedStone extends MatItem {
    public String tooltipKey;

    public AmalgamatedStone() {
        super(100);
            tooltipKey = "mixedstone";
            rarity = Rarity.RARE;


    }

}