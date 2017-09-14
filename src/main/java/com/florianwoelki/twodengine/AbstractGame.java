package com.florianwoelki.twodengine;

import com.florianwoelki.twodengine.components.State;
import com.florianwoelki.twodengine.gfx.Renderer;

import java.util.Stack;

/**
 * Created by Florian Woelki on 18.11.16.
 *
 * This class represents an game and it delivers all
 * the neccessary methods you need for creating a game.
 * This will be your main game class and you can set
 * here a state or update, render objects in your game.
 */
public abstract class AbstractGame {

    private Stack<State> states = new Stack<>();

    /**
     * This method updates the game logic.
     * It could be player movement or even
     * physics.
     *
     * @param gc GameContainer for controlling the game
     * @param dt Float delta for good fps on every computer
     */
    public abstract void update(GameContainer gc, float dt);

    /**
     * This method renders all graphics and
     * obstacles in the game.
     * You can render rectangles, but also simple
     * images.
     *
     * @param gc GameContainer for controlling the game
     * @param renderer Renderer for rendering images or obstacles more easier
     */
    public abstract void render(GameContainer gc, Renderer renderer);

    /**
     * This method will push a specific state to the current
     * top list of all states. This will be your active state.
     *
     * @param state State for pushing to the top state
     */
    public void push(State state) {
        states.push(state);
    }

    /**
     * This method will pop the current state and dispose everything
     * from the state.
     */
    public void pop() {
        states.peek().dispose();
        states.pop();
    }

    /**
     * With this method you set a state to a specific
     * state of your choice.
     * First it will pop the current state and then
     * push the new specific state.
     *
     * @param state State for setting the state
     */
    public void setState(State state) {
        pop();
        push(state);
    }

    /**
     * With this method you peek the current state.
     *
     * @return State which was peeked
     */
    public State peek() {
        return states.peek();
    }

}
