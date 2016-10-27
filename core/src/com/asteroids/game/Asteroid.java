package com.asteroids.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Asteroid {
    private Vector2 position; // положение астероида: position.x - по X, position.y - по Y
    private float speed; // скорость полета по X
    private static Texture tex; // текстура астероида

    public Vector2 getPosition() {
        return position;
    } // геттер, позволяющий узнать координаты астероида

    public Asteroid() { // конструктор(метод, вызываемый при создании астероида)
        position = new Vector2(1280 + 1280 * (float)Math.random(), 720 * (float)Math.random()); // задаем координаты(где-то за правой стороной окна)
        speed = 3.0f + (float)Math.random() * 5.0f; // задаем скорость полета: от 3 до 8 пикселей за такт
        if(tex == null) // если текстура еще не была загружена
            tex = new Texture("asteroid60.tga"); // загружаем её
    }

    public void render(SpriteBatch batch) { // рисуем астероид
        batch.draw(tex, position.x, position.y); // batch.draw(откуда берем картинку, положение по X, положение по Y)
    }

    public void update() { // обновляем логику астероида
        position.x -= speed; // перемещаем его на заданную величину влево
        if(position.x < -60) { // если он улетел за левый край экрана
            recreate(); // пересоздаем его
        }
    }

    public void recreate() { // метод пересоздания астероида
        position.set(1280 + 1280 * (float)Math.random(), 720 * (float)Math.random()); // задаем координаты по X и Y
        speed = 3.0f + (float)Math.random() * 5.0f; // задаем скорость
    }
}
