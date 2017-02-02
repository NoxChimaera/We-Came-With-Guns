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

import com.github.noxchimaera.massacre.engine.GameTime;
import com.github.noxchimaera.massacre.engine.Vector2;
import com.github.noxchimaera.massacre.engine.views.View;

import java.awt.*;

/**
 * @author Max Balushkin
 */
public class GameObject {

    private float x;
    private float y;
    private GameObject relativeTo;

    private int zIndex;
    private Scene scene;

    private View view;

    public GameObject(Scene scene, GameObject relativeTo) {
        this.scene = scene;
        this.relativeTo = relativeTo;
    }

    public Vector2 getLocation() {
        return new Vector2(x, y);
    }

    public Vector2 getAbsoluteLocation() {
        return relativeTo == null
            ? getLocation()
            : new Vector2(x + relativeTo.x, y + relativeTo.y);
    }

    public void setLocation(Vector2 location) {
        x = location.x();
        y = location.y();
    }

    public void setLocation(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void moveBy(float offsetX, float offsetY) {
        setLocation(x + offsetX, y + offsetY);
    }

    public Vector2 getOrigin() {
        return getLocation().add(getView().getOrigin());
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
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
            if (zIndex < 0) {
                g.drawImage(view.predraw(), (int)x, (int)y, view.getSize().ix(), view.getSize().iy(), null);
            } else {
                Vector2 loc = getAbsoluteLocation().sub(getScene().getCamera().getLocation());
                g.drawImage(view.predraw(), loc.ix(), loc.iy(), view.getSize().ix(), view.getSize().iy(), null);
            }
        }
    }

}
