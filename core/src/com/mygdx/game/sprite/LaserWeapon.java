package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;

public class LaserWeapon extends Sprite {
    private Rect worldBounds;
    private Vector2 v = new Vector2();
    Texture lWeapon = new Texture("lazerr.png");

    public boolean flag = false;

    public LaserWeapon() {
        regions = new TextureRegion[1];
        regions[0] = new TextureRegion(lWeapon);
    }
    public void set(
            Vector2 pos0,
            Vector2 v0,
            float height,
            Rect worldBounds
    ) {
        this.regions[0] = new TextureRegion(lWeapon);
        this.pos.set(pos0);
        this.v.set(v0);
        setHeightProportion(height);
        this.worldBounds = worldBounds;
        flag = true;
    }
    @Override
    public void update(float delta) {
        this.pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }
    @Override
    public void destroy(){
        setHeightProportion(0);
    }
}

