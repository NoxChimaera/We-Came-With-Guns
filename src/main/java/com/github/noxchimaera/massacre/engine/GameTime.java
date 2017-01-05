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
 * @author Max Balushkin
 */
public class GameTime {

    private final long elapsedGameTime;
    private final float dt;

    public GameTime(long elapsedGameTime, float dt) {
        this.elapsedGameTime = elapsedGameTime;
        this.dt = dt;
    }

    long getElapsedGameTime() {
        return elapsedGameTime;
    }

    public float getDt() {
        return dt;
    }

}
