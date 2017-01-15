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

package com.github.noxchimaera.massacre.engine.controls;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Max Balushkin
 */
public class Keyboard extends KeyAdapter {

    private static Keyboard shared = new Keyboard();

    public static Keyboard shared() {
        return shared;
    }

    private Set<Integer> keys;

    private Keyboard() {
        keys = new HashSet<>(8);
    }

    public boolean isPressed(int keyCode) {
        return keys.contains(keyCode);
    }

    public boolean isPressed(int ... keyCode) {
        for (Integer key : keyCode) {
            if (keys.contains(key)) {
                return true;
            }
        }
        return false;
    }

    @Override public void keyPressed(KeyEvent e) {
        keys.add(e.getKeyCode());
    }

    @Override public void keyReleased(KeyEvent e) {
        keys.remove(e.getKeyCode());
    }

}
