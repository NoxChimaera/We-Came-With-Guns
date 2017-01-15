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

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Max Balushkin
 */
public class VectorTest {

    @Test public void sub() throws Exception {
        Vector a = new Vector(10, 5);
        Vector b = new Vector(1, 5);
        Vector c = a.sub(b);
        assertEquals(10 - 1, c.x(), 0.01);
        assertEquals(5 - 5, c.y(), 0.01);
    }

    @Test public void add() throws Exception {
        Vector a = new Vector(1, 6);
        Vector b = new Vector(7, 10);
        Vector c = a.add(b);
        assertEquals(1 + 7, c.x(), 0.01);
        assertEquals(6 + 10, c.y(), 0.01);
    }

    @Test public void mul() throws Exception {
        Vector a = new Vector(5, 4);
        Vector b = new Vector(2, 0.5f);
        Vector c = a.mul(b);
        assertEquals(5 * 2, c.x(), 0.01);
        assertEquals(4 * 0.5f, c.y(), 0.01);
    }

    @Test public void mul1() throws Exception {
        Vector a = new Vector(4, 10);
        Vector b = a.mul(0.5f);
        assertEquals(4 * 0.5f, b.x(), 0.01);
        assertEquals(10 * 0.5f, b.y(), 0.01);
    }

    @Test public void unit() {
        Vector b = new Vector(10, 0);
        Vector c = b.unit();
        assertEquals(1, c.x(), 0.01);
    }

}