/*
 * Copyright 2016 Max Balushkin.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.noxchimaera.massacre.game;

import com.github.noxchimaera.massacre.engine.GameTime;
import com.github.noxchimaera.massacre.engine.actors.Actor;
import com.github.noxchimaera.massacre.engine.collision.Collider;
import com.github.noxchimaera.massacre.engine.controls.Keyboard;
import com.github.noxchimaera.massacre.engine.scene.GameObject;

import java.awt.event.KeyEvent;

/**
 * @author Max Balushkin
 */
public class Player extends Actor {

    private Collider collider_top;
    private Collider collider_right;
    private Collider collider_bottom;
    private Collider collider_left;

    public Player(GameObject gameObject) {
        super(gameObject, false, "player");

        float x = gameObject.getLocation().x();
        float y = gameObject.getLocation().y();

        float w = gameObject.getView().getSize().x();
        float h = gameObject.getView().getSize().y();

        collider_top = new Collider(x, y - 2, w, 1);
        colliders.add(collider_top);
        collider_right = new Collider(x + w + 2, y, 1, h);
        colliders.add(collider_right);
        collider_bottom = new Collider(x, y + h + 2, w, 1);
        colliders.add(collider_bottom);
        collider_left = new Collider(x - 2, y, 1, h);
        colliders.add(collider_left);
    }

    private void updateColliders() {
        float x = gameObject.getLocation().x();
        float y = gameObject.getLocation().y();
        float w = gameObject.getView().getSize().x();
        float h = gameObject.getView().getSize().y();

        collider_top.setLocation(x,y - 2);
        collider_right.setLocation(x + w + 2, y);
        collider_bottom.setLocation(x, y + h + 2);
        collider_left.setLocation(x - 2, y);
    }

    @Override public void update(GameTime gameTime) {
        float v = 150 * (float)gameTime.getDt();
        float x = gameObject.getLocation().x();
        float y = gameObject.getLocation().y();

        if (colliders.stream().filter(i -> i.wasCollision()).count() > 0) {
            System.out.println("Collided");
        }

        if (Keyboard.shared().isPressed(KeyEvent.VK_UP) && !collider_top.wasCollision()) {
            y -= v;
        } else if (Keyboard.shared().isPressed(KeyEvent.VK_DOWN) && !collider_bottom.wasCollision()) {
            y += v;
        }
        if (Keyboard.shared().isPressed(KeyEvent.VK_LEFT) && !collider_left.wasCollision()) {
            x -= v;
        } else if (Keyboard.shared().isPressed(KeyEvent.VK_RIGHT) && !collider_right.wasCollision()) {
            x += v;
        }
        gameObject.setLocation(x, y);
        updateColliders();
    }

}
