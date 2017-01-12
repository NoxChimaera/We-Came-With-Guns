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

package com.github.noxchimaera.massacre.engine.collision;

import com.github.noxchimaera.massacre.engine.actors.Actor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Max Balushkin
 */
public class Collider {

    private float x;
    private float y;
    private float width;
    private float height;

    private List<Actor> collisions;

    public Collider(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        collisions = new ArrayList<>();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setLocation(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean checkCollision(Collider b) {
        if (this == b) {
            return false;
        }
        if (collisions.contains(b)) {
            return true;
        }
        boolean collides = !(x > b.x + b.width
            || x + width < b.x
            || y > b.y + b.height
            || y + height < b.y);
        return collides;
    }

    public boolean wasCollision() {
        return collisions.size() != 0;
    }

    public void addCollision(Actor collision) {
        collisions.add(collision);
    }

    public List<Actor> getCollisions() {
        return new ArrayList<>(collisions);
    }

    public void reset() {
        collisions.clear();
    }

}
