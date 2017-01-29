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
import com.github.noxchimaera.massacre.engine.controls.Mouse;
import com.github.noxchimaera.massacre.engine.Vector2;
import com.github.noxchimaera.massacre.engine.scene.GameObject;

/**
 * @author Max Balushkin
 */
public class Cursor extends Actor {

    public Cursor(GameObject gameObject) {
        super(gameObject, false, "mouse");
    }

    @Override public void update(GameTime gameTime) {
        Vector2 origin = getGameObject().getView().getOrigin();
        gameObject.setLocation(Mouse.shared().getLocation().sub(origin));
    }

}
