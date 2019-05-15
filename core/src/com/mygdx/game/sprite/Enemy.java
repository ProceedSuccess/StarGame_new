package com.mygdx.game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Ship;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;
import com.mygdx.game.pool.EnemyPool;
import com.mygdx.game.pool.ExplosionPool;

public class Enemy extends Ship {

        private enum State { DESCENT, FIGHT }

        public enum Size {SMALL, MEDIUM, BIG}
        private Size size;

        public void setSize(Size size){
            this.size = size;
        }
        public Size getSize(){return size;}

        private Vector2 v0 = new Vector2();

        private State state;
        private Vector2 descentV = new Vector2(0, -0.15f);

        public Enemy(BulletPool bulletPool, ExplosionPool explosionPool, Rect worldBounds, Sound shootSound) {
            super(shootSound);
            this.bulletPool = bulletPool;
            this.explosionPool = explosionPool;
            this.worldBounds = worldBounds;
            this.v.set(v0);
        }

        @Override
        public void update(float delta) {
            super.update(delta);
            pos.mulAdd(v, delta);

            switch (state) {
                case DESCENT:
                    if (getTop() <= worldBounds.getTop()) {
                        v.set(v0);
                        state = State.FIGHT;
                    }
                    break;
                case FIGHT:
                    reloadTimer += delta;
                    if (reloadTimer >= reloadInterval) {
                        shoot();
                        reloadTimer = 0f;
                    }
                    if (getBottom() < worldBounds.getBottom()) {
                        boom();
                        destroy();
                    }
                    break;
            }
        }

        public void set(
                TextureRegion[] regions,
                Vector2 v0,
                TextureRegion bulletRegion,
                float bulletHeight,
                float bulletVY,
                int bulletDamage,
                float reloadInterval,
                float height,
                int hp
        ) {
            this.regions = regions;
            this.v0.set(v0);
            this.bulletRegion = bulletRegion;
            this.bulletHeight = bulletHeight;
            this.bulletV.set(0f, bulletVY);
            this.bulletDamage = bulletDamage;
            this.reloadInterval = reloadInterval;
            this.hp = hp;
            this.reloadTimer = reloadInterval;
            setHeightProportion(height);
            v.set(descentV);
            state = State.DESCENT;
        }

        public boolean isBulletCollision(Rect bullet) {
            return !(bullet.getRight() < getLeft()
                    || bullet.getLeft() > getRight()
                    || bullet.getBottom() > getTop()
                    || bullet.getTop() < pos.y
            );
        }
    public boolean isLaserCollision(Rect laser) {
        return !(laser.getRight() < getLeft()
                || laser.getLeft() > getRight()
                || laser.getBottom() > getTop()
                || laser.getTop() < pos.y
        );
    }
        @Override
        public void destroy() {
            boom();
            hp = 0;
            super.destroy();
        }

        public void delete(){
            isDestroyed = true;
        }
}