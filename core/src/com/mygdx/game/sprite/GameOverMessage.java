package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.base.ActionListener;
import com.mygdx.game.base.ScaledTouchUpButton;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.math.Rect;

public class GameOverMessage extends Sprite {

    public GameOverMessage (TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
    }
    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setRight(worldBounds.getRight());
    }
}
