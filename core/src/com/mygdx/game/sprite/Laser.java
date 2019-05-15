package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;

public class Laser extends Sprite {
    private Rect worldBounds;
    private Vector2 v = new Vector2();
    Texture laser = new Texture("laser.png");

    private float timer;
    private float interval = 10f;

    public Laser() {
        regions = new TextureRegion[1];
        regions[0] = new TextureRegion(laser);
        timer = 0;
    }
    public void set(
            Vector2 pos0,
            float height,
            Rect worldBounds
    ) {
        this.regions[0] = new TextureRegion(laser);
        this.pos.set(pos0);
        setHeightProportion(height);
        setWidth(0.02f);
        this.worldBounds = worldBounds;
    }
    public void update(Vector2 v, float delta) {
        this.pos.set(v);
        timer += delta;
        if(timer >= interval){
            this.destroy();
            timer = 0;
        }
    }
    @Override
    public void destroy(){
        setHeightProportion(0);

    }
}

