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

}
