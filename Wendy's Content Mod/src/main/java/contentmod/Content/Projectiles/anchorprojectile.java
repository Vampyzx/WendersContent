//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package contentmod.Content.Projectiles;

import necesse.engine.Screen;
import necesse.engine.registries.MobRegistry;
import necesse.engine.sound.SoundEffect;
import necesse.engine.tickManager.Performance;
import necesse.engine.tickManager.TickManager;
import necesse.engine.util.GameMath;
import necesse.engine.util.GameRandom;
import necesse.entity.chains.Chain;
import necesse.entity.chains.ChainLocation;
import necesse.entity.chains.StaticChainLocation;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.projectile.boomerangProjectile.BoomerangProjectile;
import necesse.entity.trails.Trail;
import necesse.gfx.GameResources;
import necesse.gfx.camera.GameCamera;
import necesse.gfx.drawOptions.DrawOptions;
import necesse.gfx.drawOptions.DrawOptionsList;
import necesse.gfx.drawOptions.texture.TextureDrawOptions;
import necesse.gfx.drawables.EntityDrawable;
import necesse.gfx.drawables.LevelSortedDrawable;
import necesse.gfx.drawables.OrderableDrawables;
import necesse.gfx.gameTexture.GameTexture;
import necesse.level.maps.Level;
import necesse.level.maps.light.GameLight;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.List;

import static necesse.engine.Screen.drawShape;
import static necesse.engine.Screen.tickManager;


public class anchorprojectile extends BoomerangProjectile {
    public anchorprojectile() {}
    private int deltaSpeed;


    public void init() {
        this.setLevel(getLevel());
        this.setOwner(getOwner());
        super.init();
        this.setWidth(160.0F);
        this.height = 18.0F;
        this.bouncing = 10;
        this.piercing = 3;
        this.heightBasedOnDistance = true;
        this.isSolid = true;
        this.knockback = -500;
        this.doesImpactDmg = true;
        //this is for speed acceleration
        this.deltaSpeed = 500;
        this.speed = 35;


    }




@Override
    public void onMoveTick(Point2D.Float startPos, double movedDist) {
    super.onMoveTick(startPos, movedDist);
    float acceleration = this.traveledDistance / 960;
    this.speed = GameMath.lerp(acceleration, this.speed, this.deltaSpeed);
    {


    }
}
@Override
    public Trail getTrail() {
        return null;
    }

    public void addDrawables(List<LevelSortedDrawable> list, OrderableDrawables tileList, OrderableDrawables topList, OrderableDrawables overlayList, Level level, TickManager tickManager, GameCamera camera, PlayerMob perspective) {
        if (!this.removed()) {
            GameLight light = level.getLightLevel(this);
            int drawX = camera.getDrawX(this.x) - this.texture.getWidth() / 2;
            int drawY = camera.getDrawY(this.y) - this.texture.getHeight() / 2;
            float angle = this.getAngle() + ( this.returningToOwner ? 180.0F : 0.0F);
            final TextureDrawOptions options = this.texture.initDraw().light(light).rotate(angle, this.texture.getWidth() / 2, this.texture.getHeight() / 2).pos(drawX, drawY - (int)this.getHeight());
            list.add(new EntityDrawable(this) {
                public void draw(TickManager tickManager) {
                    options.draw();
                }
            });
            this.addShadowDrawables(tileList, drawX, drawY, light, angle, this.texture.getHeight() / 2);
        }
    }

    public float getAngle() {
        return this.angle % 360.0F;
    }

    public void playMoveSound() {
        Screen.playSound(GameResources.swing2, SoundEffect.effect(this));
    }


    public void playHitSound(float x, float y) {
        Screen.playSound(GameResources.punch, SoundEffect.effect(x, y)
                .volume(5.0F)
                .pitch(GameRandom.globalRandom.getFloatBetween(1f, 1.5f)));
    }
}
