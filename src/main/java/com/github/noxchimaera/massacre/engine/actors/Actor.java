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

package com.github.noxchimaera.massacre.engine.actors;

import com.github.noxchimaera.massacre.engine.collision.Collider;
import com.github.noxchimaera.massacre.engine.interfaces.UpdatableObject;
import com.github.noxchimaera.massacre.engine.scene.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Max Balushkin
 */
public abstract class Actor implements UpdatableObject {

    private boolean enabled = true;
    private boolean fixed;

    private String tag;

    protected GameObject gameObject;
    protected List<Collider> colliders;

    public Actor(GameObject gameObject, boolean isFixed) {
        this(gameObject, isFixed, "");
    }

    public Actor(GameObject gameObject, boolean isFixed, String tag) {
        this.gameObject = gameObject;
        colliders = new ArrayList<>();
        fixed = isFixed;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public List<Collider> getColliders() {
        return colliders;
    }

}
