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

package com.github.noxchimaera.massacre.engine.views;

import com.github.noxchimaera.massacre.engine.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Max Balushkin
 */
public class Animation extends View {

    private List<View> frames;

    private int currentFrame = 0;

    private long frameStartTime;
    private long frameDelay;

    public Animation(long frameDelay) {
        frames = new ArrayList<>();
        this.frameDelay = frameDelay;
    }

    public void addFrame(View ... frames) {
        for (View frame : frames) {
            this.frames.add(frame);
        }
    }

    @Override public Image predraw() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - frameStartTime >= frameDelay) {
            if (++currentFrame >= frames.size()) {
                currentFrame = 0;
            }
            frameStartTime = currentTime;
        }

        return frames.get(currentFrame).predraw();
    }

    @Override public Vector2 getSize() {
        return frames.get(currentFrame).getSize();
    }

    @Override public Vector2 getOrigin() {
        return frames.get(currentFrame).getOrigin();
    }

}
