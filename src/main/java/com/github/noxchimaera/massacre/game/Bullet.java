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
import com.github.noxchimaera.massacre.engine.Vector2;
import com.github.noxchimaera.massacre.engine.actors.Actor;
import com.github.noxchimaera.massacre.engine.collision.Collider;
import com.github.noxchimaera.massacre.engine.scene.GameObject;

/**
 * @author Max Balushkin
 */
public class Bullet extends Actor {

    private Vector2 direction;
    private float speed = 300;

    private Collider collider;

    private double distance = 0;

    public Bullet(GameObject gameObject, Vector2 direction) {
        super(gameObject, false, "bullet");
        this.direction = direction;

        Vector2 size = gameObject.getView().getSize();
        collider = new Collider(0, 0, size.x(), size.y());
        getColliders().add(collider);
    }

    private void updateCollider() {
        collider.setLocation(getGameObject().getLocation());
    }

    @Override public void update(GameTime gameTime) {
        if (wasCollision()) {
            for (Collider c : getColliders()) {
                for (Actor actor : c.getCollisions()) {
                    if (actor.getTag().equals("wall")) {
                        setReadyToDestroy(true);
                    }
                }
            }
        }
        if (distance > 1000) {
            setReadyToDestroy(true);
        }

        Vector2 dir = direction.mul(speed * (float)gameTime.getDt());
        Vector2 location = getGameObject().getLocation();
        getGameObject().setLocation(location.add(dir));
        updateCollider();
        distance += dir.norm();
    }

}
