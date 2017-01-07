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

import com.github.noxchimaera.massacre.engine.models.Vector;
import com.github.noxchimaera.massacre.engine.views.View;

import java.awt.*;

/**
 * @author Max Balushkin
 */
public class GameObject {

    private String tag;

    private float x;
    private float y;

    private int zIndex;
    private Scene scene;

    private View view;

    public GameObject(Scene scene) {
        this(scene, "");
    }

    public GameObject(Scene scene, String tag) {
        this.scene = scene;
        this.tag = tag;
    }

    public Vector getLocation() {
        return new Vector(x, y);
    }

    public void setLocation(Vector location) {
        x = location.x();
        y = location.y();
    }

    public void setLocation(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getTag() {
        return tag;
    }

    public int getZIndex() {
        return zIndex;
    }

    public void setZIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    public Scene getScene() {
        return scene;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void draw(Graphics2D g) {
        if (view != null) {
            view.draw(getLocation().sub(getScene().getCamera().getLocation()), g);
        }
    }

}
