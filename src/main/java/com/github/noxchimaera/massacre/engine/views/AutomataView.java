/*
 * Copyright 2017 Max Balushkin.
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
import com.github.noxchimaera.massacre.engine.utils.StatePath;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Max Balushkin
 */
public class AutomataView extends View {

    private Map<String, View> states;
    private View currentState;

    public AutomataView() {
        states = new HashMap<>();
    }

    public void addState(String key, View state) {
        if (states.size() == 0) {
            currentState = state;
        }
        states.put(key, state);
    }

    public void setState(String statePath) {
        currentState = states.get(statePath);
    }

    public void setState(StatePath statePath) {
        if (!statePath.getCurrent().equals("*")) {
            View newState = states.get(statePath.getCurrent());
            if (newState == null) {
                System.out.println("There is no such state: " + statePath);
                return;
            }

            currentState = newState;
        }
        if (!statePath.isEnd()) {
            ((AutomataView)currentState).setState(statePath.next());
        }
    }

    public boolean inState(String statePath) {
        return currentState == states.get(statePath);
    }

    public boolean inState(StatePath statePath) {
        boolean inState = inState(statePath.getCurrent());
        if (!statePath.isEnd()) {
            return ((AutomataView)currentState).inState(statePath.next());
        } else {
            return inState;
        }
    }

    @Override public Image predraw() {
        return currentState.predraw();
    }

    @Override public Vector2 getSize() {
        return currentState.getSize();
    }

    @Override public Vector2 getOrigin() {
        return currentState.getOrigin();
    }
}
