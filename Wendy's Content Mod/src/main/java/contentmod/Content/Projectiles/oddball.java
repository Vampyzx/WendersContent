package contentmod.Content.Projectiles;

import necesse.engine.tickManager.TickManager;
import necesse.entity.mobs.GameDamage;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.projectile.Projectile;
import necesse.entity.trails.Trail;
import necesse.gfx.camera.GameCamera;
import necesse.gfx.drawOptions.texture.TextureDrawOptions;
import necesse.gfx.drawables.EntityDrawable;
import necesse.gfx.drawables.LevelSortedDrawable;
import necesse.gfx.drawables.OrderableDrawables;
import necesse.level.maps.Level;
import necesse.level.maps.light.GameLight;

import java.awt.*;
import java.util.List;

public class oddball extends Projectile {

    // Textures are loaded from resources/projectiles/<projectileStringID>
    // If shadow path is defined when registering the projectile, it is loaded from
    // that path into this projectile shadowTexture field

    // Each projectile must have an empty constructor for the registry to construct them
    public oddball() {
    }

    // We use this constructor on attack to spawn the projectile with the correct parameters
    public oddball(Level level, Mob owner, float x, float y, float targetX, float targetY, float speed, int distance, GameDamage damage, int knockback) {
        this.setLevel(level);
        this.setOwner(owner);
        this.x = x;
        this.y = y;
        this.setTarget(targetX, targetY);
        this.speed = speed;
        this.distance = distance;
        this.setDamage(damage);
        this.knockback = knockback;
    }

    public void init() {
        super.init();
        givesLight = true; // The projectile does not give off light
        height = 18; // It's flying 18 pixels above ground
        trailOffset = -14; // The trail is 14 pixels behind the projectile
        setWidth(16, true); // Extends the hitbox to 16 pixels wide
        knockback = 15; // knockback lmfao
    }


    public void doHitLogic(Mob mob, float x, float y) {
        if (this.getLevel().isServerLevel()) {
            if (mob != null) {
                ActiveBuff ab = new ActiveBuff("oddballdebuff", mob, 3.5F, this.getOwner());
                mob.addBuff(ab, true);
            }

        }
    }

    @Override
    public Color getParticleColor() {
        // Projectiles sometimes spawn particles. You can return null for no particles.
        return new Color(255, 255, 255);
        //new Color(35, 36, 51);
    }

    @Override
    public void addDrawables(List<LevelSortedDrawable> list, OrderableDrawables tileList, OrderableDrawables topList, OrderableDrawables overlayList, Level level, TickManager tickManager, GameCamera camera, PlayerMob perspective) {
        if (removed()) return;
        GameLight light = level.getLightLevel(this);
        int drawX = camera.getDrawX(x) - texture.getWidth() / 2;
        int drawY = camera.getDrawY(y);
        TextureDrawOptions options = texture.initDraw()
                .light(light)
                .rotate(getAngle(), texture.getWidth() / 2, 2) // We rotate the texture around the tip of it
                .pos(drawX, drawY - (int) getHeight());

        list.add(new EntityDrawable(this) {
            @Override
            public void draw(TickManager tickManager) {
                options.draw();
            }
        });

        addShadowDrawables(tileList, drawX, drawY, light, getAngle(), texture.getWidth() / 2, 2);
    }
}