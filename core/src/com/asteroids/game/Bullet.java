package com.asteroids.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    // Все сделано все также по аналогии с астероидами
    private Vector2 position;
    private float speed;
    private boolean alive;
    private static Texture tex;

    public Vector2 getPosition() {
        return position;
    }

    public boolean isAlive() {
        return alive;
    }

    public Bullet() {
        position = new Vector2(0.0f, 0.0f);
        speed = 15.0f;
        if(tex == null)
            tex = new Texture("bullet20.tga");
    }

    public void render(SpriteBatch batch) {
        batch.draw(tex, position.x, position.y);
    }

    public void update() {
        position.x += speed; // пуля двигается вправо
        if(position.x > 1280) { // когда пуля залетает зха првый край окна, она деактивируется
            destroy();
        }
    }

    public void setup(Vector2 position) { // когда мы хотим создать пулю, вызываем этот метод
        alive = true; // она активируется
        this.position = position.cpy(); // и устанавливается в заданное положение
    }

    public void destroy() {
        alive = false;
    } // выключаем пулю
}
