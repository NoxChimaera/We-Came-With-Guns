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

package com.github.noxchimaera.massacre.engine;

/**
 * 2D vector or point.
 *
 * @author Max Balushkin
 */
public class Vector2 {

    private float x;
    private float y;

    /**
     * Creates new 2D vector.
     *
     * @param x value of X coordinate
     * @param y value of Y coordinate
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns value of X coordinate.
     *
     * @return value of X
     */
    public float x() {
        return x;
    }

    /**
     * Returns value of X coordinate as integer.
     *
     * @return integer value of X
     */
    public int ix() {
        return (int)x;
    }

    /**
     * Returns value of Y coordinate.
     *
     * @return value of Y
     */
    public float y() {
        return y;
    }

    /**
     * Returns value of Y coordinate as integer.
     *
     * @return integer value of Y
     */
    public int iy() {
        return (int)y;
    }

    /**
     * Sets value of X coordinate.
     *
     * @param x value of X
     *
     * @return self
     */
    public Vector2 x(float x) {
        this.x = x;
        return this;
    }

    /**
     * Sets value of Y
     *
     * @param y value of Y
     *
     * @return self
     */
    public Vector2 y(float y) {
        this.y = y;
        return this;
    }

    /**
     * Adds vectors.
     *
     * @param b other vector
     *
     * @return new vector
     */
    public Vector2 add(Vector2 b) {
        return new Vector2(x + b.x, y + b.y);
    }

    /**
     * Subtracts vector b from this.
     *
     * @param b other vector
     *
     * @return new vector
     */
    public Vector2 sub(Vector2 b) {
        return new Vector2(x - b.x, y - b.y);
    }

    /**
     * Multiplies every vector coordinate by a constant multiplier.
     *
     * @param multiplier constant multiplier
     *
     * @return new vector
     */
    public Vector2 mul(float multiplier) {
        return new Vector2(x * multiplier, y * multiplier);
    }

    /**
     * Multiplies every vector coordinate by an appropriate value of another vector.
     *
     * @param multipliers vector of multipliers
     *
     * @return new vector
     */
    public Vector2 mul(Vector2 multipliers) {
        return new Vector2(x * multipliers.x, y * multipliers.y);
    }

    /**
     * Divides vector by a constant divider.
     *
     * @param divider constant divider
     *
     * @return new vector
     */
    public Vector2 div(float divider) {
        return new Vector2(x / divider, y / divider);
    }

    /**
     * Calculates vector norm.
     *
     * @return vector norm
     */
    public double norm() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Calculates unit vector.
     *
     * @return unit vector
     */
    public Vector2 unit() {
        return div((float)norm());
    }

    @Override public String toString() {
        return "Vector2{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }

}
