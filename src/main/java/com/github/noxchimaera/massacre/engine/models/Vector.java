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

package com.github.noxchimaera.massacre.engine.models;

/**
 * @author Max Balushkin
 */
public class Vector {

    private float x;
    private float y;

    public Vector(Vector vector) {
        x = vector.x();
        y = vector.y();
    }

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float x() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float y() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector sub(Vector b) {
        return new Vector(x - b.x(), y - b.y());
    }

    public Vector add(Vector b) {
        return new Vector(x + b.x(), y + b.y());
    }

    public Vector mul(Vector b) {
        return new Vector(x * b.x(), y * b.y());
    }

    public Vector mul(float multiplier) {
        return new Vector(x * multiplier, y * multiplier);
    }

    public Vector div(float divider) {
        return new Vector(x / divider, y / divider);
    }

    public double norm() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector unit() {
        return div((float)norm());
    }

    @Override public String toString() {
        return "Vector{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }

}
