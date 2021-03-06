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

package com.github.noxchimaera.massacre.engine.scene;

import com.github.noxchimaera.massacre.engine.Vector2;

/**
 * @author Max Balushkin
 */
public class Camera {

    private Vector2 location;

    private Scene parent;
    private GameObject origin;

    public Camera(Scene parent) {
        this.parent = parent;
    }

    public Vector2 getLocation() {
        return location;
    }

    public GameObject getOrigin() {
        return origin;
    }

    public void setOrigin(GameObject origin) {
        this.origin = origin;
    }

    public void moveToTarget() {
        int w = parent.getWidth();
        int h = parent.getHeight();

        Vector2 o = origin.getView() != null
            ? origin.getLocation().add(origin.getView().getOrigin())
            : origin.getLocation();
        location = new Vector2(o.x() - w / 2, o.y() - h / 2);
    }

}
