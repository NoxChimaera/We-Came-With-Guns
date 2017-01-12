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
import com.github.noxchimaera.massacre.engine.scene.GameObject;

/**
 * @author Max Balushkin
 */
public class Wall extends Actor {



    public Wall(GameObject gameObject) {
        super(gameObject, true, "wall");

        float x = gameObject.getLocation().x();
        float y = gameObject.getLocation().y();
        float w = gameObject.getView().getSize().x();
        float h = gameObject.getView().getSize().y();

        Collider collider = new Collider(x, y, w, h);
        colliders.add(collider);
    }

    @Override public void update(GameTime gameTime) {
    }

}
