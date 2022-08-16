import contentmod.Content.CraftingItems.AmalgamatedStone;
import contentmod.Content.CraftingItems.StickyLiquid;
import contentmod.Content.MonolithicAmalgamation;
import contentmod.Content.OddStickyWand;
import contentmod.Content.Projectiles.AmalgamatedAmmoProjectile;
import contentmod.Content.Projectiles.anchorprojectile;
import contentmod.Content.Projectiles.oddball;
import contentmod.Content.Projectiles.tungstenball;
import contentmod.Content.StatusEffects.oddballdebuff;
import contentmod.Content.TungstenCannon;
import contentmod.Content.Uendizuanka;
import necesse.engine.localization.message.StaticMessage;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.modifiers.ModifierValue;
import necesse.engine.registries.*;
import necesse.inventory.enchants.ToolItemEnchantment;
import necesse.inventory.enchants.ToolItemModifiers;
import necesse.inventory.recipe.Ingredient;
import necesse.inventory.recipe.Recipe;
import necesse.inventory.recipe.Recipes;
import necesse.inventory.recipe.Tech;
import static necesse.engine.registries.RecipeTechRegistry.registerTech;


@ModEntry
public class MoreContentMod {


    public static Tech AMALGAMATION;

    public void init() {

        AMALGAMATION = registerTech("amalgamation", new StaticMessage("Amalgamator"));


        ItemRegistry.registerItem("AmalgamatedStone", new AmalgamatedStone(), 70, true);
        ItemRegistry.registerItem("StickyLiquid", new StickyLiquid(), 1, true);

        ItemRegistry.registerItem("TungstenCannon", new TungstenCannon(), 600, true);
        ItemRegistry.registerItem("OddStickyWand", new OddStickyWand(), 300, true);
        ItemRegistry.registerItem("MonolithicAmalgamation", new MonolithicAmalgamation(), 1000, true);
        ItemRegistry.registerItem("Uendizuanka", new Uendizuanka(), 750, true);

        ProjectileRegistry.registerProjectile("tungstencannonball", tungstenball.class, "tungstenball", "tungstenballshadow");
        ProjectileRegistry.registerProjectile("oddball", oddball.class, "oddprojectile", "oddprojectileshadow");
        ProjectileRegistry.registerProjectile("AmalgamatedAmmoProjectile", AmalgamatedAmmoProjectile.class, "amalgamatedbullet", "");
        ProjectileRegistry.registerProjectile("anchorprojectile", anchorprojectile.class, "anchorprojectile", "anchorprojectileshadow");

        BuffRegistry.registerBuff("oddballdebuff", new oddballdebuff());



        //ENCHANTS START HERE!!!!
        //WHOOO ENCHANTS YIPEEEE!!!!


        //enchants for melee start
        //enchants for melee start
        EnchantmentRegistry.registerMeleeEnchantment(
                "enraged",  // Enchantment Name
                new ToolItemEnchantment(
                        50, // enchantmentCostModPercent
                        new ModifierValue<>(ToolItemModifiers.DAMAGE, 0.35F), // 35% Damage
                        new ModifierValue<>(ToolItemModifiers.KNOCKBACK, 0.35F), // 35% Knockback
                        new ModifierValue<>(ToolItemModifiers.ATTACK_SPEED, -0.35F) // -35% Attack Speed
                )
        );
        EnchantmentRegistry.registerMeleeEnchantment(
                "flowing",
                new ToolItemEnchantment(
                        60, // enchantment cost percent
                        new ModifierValue<>(ToolItemModifiers.ATTACK_SPEED, 0.35F),
                        new ModifierValue<>(ToolItemModifiers.CRIT_CHANCE, 0.35F),
                        new ModifierValue<>(ToolItemModifiers.DAMAGE, -0.25F),
                        new ModifierValue<>(ToolItemModifiers.KNOCKBACK, -0.15F)
                )
        );
        //enchants for melee end
        //enchants for melee end

        //enchants for ranged start
        //enchants for ranged start
        EnchantmentRegistry.registerRangedEnchantment(
                "forceful", // Enchantment Name
                new ToolItemEnchantment(
                        50, // enchantmentCostModPercent
                        new ModifierValue<>(ToolItemModifiers.DAMAGE, 0.35F), // 35% Damage
                        new ModifierValue<>(ToolItemModifiers.VELOCITY, -0.3F), // -30% Attack Velocity
                        new ModifierValue<>(ToolItemModifiers.KNOCKBACK, 1.5F) // 150% Knockback
                )
        );
        EnchantmentRegistry.registerRangedEnchantment(
                "pinpoint",
                new ToolItemEnchantment(
                        70,
                        new ModifierValue<>(ToolItemModifiers.DAMAGE, 1F),
                        new ModifierValue<>(ToolItemModifiers.VELOCITY, 0.25F),
                        new ModifierValue<>(ToolItemModifiers.CRIT_CHANCE, 0.25F),
                        new ModifierValue<>(ToolItemModifiers.ATTACK_SPEED, -0.35F)
                )
        );
        //enchants for ranged end
        //enchants for ranged end

        //enchants for summon start
        //enchants for summon start
        EnchantmentRegistry.registerSummonEnchantment(
                "rampaging", // Enchantment Name
                new ToolItemEnchantment(
                        75, // enchantmentCostModPercent
                        new ModifierValue<>(ToolItemModifiers.DAMAGE, 0.45F), // 45% Damage
                        new ModifierValue<>(ToolItemModifiers.SUMMONS_SPEED, -0.20F), // -20% summon speed
                        new ModifierValue<>(ToolItemModifiers.KNOCKBACK, 0.25F), // 25% Knockback
                        new ModifierValue<>(ToolItemModifiers.ATTACK_SPEED, -0.35F) //-35% attack speed
                )
        );
        EnchantmentRegistry.registerSummonEnchantment(
                "rabid",
                new ToolItemEnchantment(
                        75,
                        new ModifierValue<>(ToolItemModifiers.DAMAGE, 0.3F),
                        new ModifierValue<>(ToolItemModifiers.KNOCKBACK, 0.3F),
                        new ModifierValue<>(ToolItemModifiers.ATTACK_SPEED, 0.3F),
                        new ModifierValue<>(ToolItemModifiers.SUMMONS_SPEED, 0.2F),
                        new ModifierValue<>(ToolItemModifiers.SUMMONS_TARGET_RANGE, -0.25F),
                        new ModifierValue<>(ToolItemModifiers.CRIT_CHANCE, -0.5F)
                )
        );
        //enchants for summon end
        //enchants for summon end

        //enchants for magic start
        //enchants for magic start
        EnchantmentRegistry.registerMagicEnchantment(
                "vexing", // Enchantment Name
                new ToolItemEnchantment(
                        65, // enchantmentCostModPercent
                        new ModifierValue<>(ToolItemModifiers.DAMAGE, 0.35F), // 35% Damage
                        new ModifierValue<>(ToolItemModifiers.VELOCITY, -0.35F), // -35% Velocity
                        new ModifierValue<>(ToolItemModifiers.KNOCKBACK, 0.75F), // 75% Knockback
                        new ModifierValue<>(ToolItemModifiers.ATTACK_SPEED, -0.35F) // -35% Attack Speed
                )
        );
        EnchantmentRegistry.registerMagicEnchantment(
                "noble",
                new ToolItemEnchantment(
                        75,
                        new ModifierValue<>(ToolItemModifiers.ATTACK_SPEED, 0.3F),
                        new ModifierValue<>(ToolItemModifiers.VELOCITY, 0.3F),
                        new ModifierValue<>(ToolItemModifiers.CRIT_CHANCE, 0.2F),
                        new ModifierValue<>(ToolItemModifiers.DAMAGE, -0.25F),
                        new ModifierValue<>(ToolItemModifiers.KNOCKBACK, -0.25F)
                )
        );
        //enchants for magic end
        //enchants for magic end


    }

    public void initResources() {


    }

    public void postInit() {
        // Add recipes


        Recipes.registerModRecipe(new Recipe(
                        "AmalgamatedStone",
                        1,
                        AMALGAMATION,
                        new Ingredient[]{
                                new Ingredient("deepstone", 10),
                                new Ingredient("stone", 10),
                                new Ingredient("lifequartz", 1),
                        }
                ).showAfter("sandtile")
        );

        Recipes.registerModRecipe(new Recipe(
                "StickyLiquid",
                1,
                RecipeTechRegistry.ALCHEMY,
                new Ingredient[]{
                        new Ingredient("bone", 10),
                        new Ingredient("lifequartz", 1),
                }
        ).showAfter("AmalgamatedStone")
        );






        Recipes.registerModRecipe(new Recipe(
                "MonolithicAmalgamation",
                1,
                RecipeTechRegistry.IRON_ANVIL,
                new Ingredient[]{
                        new Ingredient("AmalgamatedStone", 600),
                        new Ingredient("tungstenbar", 200),
                }
        ).showAfter("froststaff")
        );


        Recipes.registerModRecipe(new Recipe(
                "TungstenCannon",
                1,
                RecipeTechRegistry.IRON_ANVIL,
                new Ingredient[]{
                        new Ingredient("tungstenbar", 500),
                        new Ingredient("knockbackpotion", 50),
                        new Ingredient("handcannon", 1),
                }
        ).showAfter("MonolithicAmalgamation")
        );


        Recipes.registerModRecipe(new Recipe(
                "OddStickyWand",
                RecipeTechRegistry.IRON_ANVIL,
                new Ingredient[]{
                        new Ingredient("StickyLiquid", 100),
                        new Ingredient("tungstenbar", 25),
                        new Ingredient("lifequartz", 25),
                }
        ).showAfter("TungstenCannon")
        );


    }
}
