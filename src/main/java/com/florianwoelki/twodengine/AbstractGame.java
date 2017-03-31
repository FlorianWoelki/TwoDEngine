package com.florianwoelki.twodengine;

import com.florianwoelki.twodengine.components.State;
import com.florianwoelki.twodengine.gfx.Renderer;

import java.util.Stack;

/**
 * Created by Florian Woelki on 18.11.16.
 */
public abstract class AbstractGame {

    private Stack<State> states = new Stack<>();

    public abstract void update(GameContainer gc, float dt);

    public abstract void render(GameContainer gc, Renderer renderer);

    public void push(State s) {
        states.push(s);
    }

    public void pop() {
        states.peek().dispose();
        states.pop();
    }

    public void setState(State s) {
        pop();
        push(s);
    }

    public State peek() {
        return states.peek();
    }

}
