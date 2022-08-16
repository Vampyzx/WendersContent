//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package contentmod.Content;

import necesse.gfx.gameTexture.GameTexture;
import necesse.inventory.item.toolItem.ToolType;
import necesse.inventory.recipe.Tech;
import necesse.level.gameObject.CraftingStationObject;
import java.awt.*;


public class AmalgamatorWorkstation extends CraftingStationObject {

    public GameTexture texture;

    public AmalgamatorWorkstation() {
        super();
        mapColor = new Color(29, 32, 38);
        rarity = rarity.RARE;
        drawDmg = false;
        toolType = ToolType.PICKAXE;
        isLightTransparent = false;
        roomProperties.add("metalwork");
    }

    @Override
    public void loadTextures() {
        super.loadTextures();
        this.texture = GameTexture.fromFile("objects/AmalgamatorWorkstation");
    }

    public Tech[] getCraftingTechs() {
        return new Tech[]{AMALGAMATION};
        }
    }

