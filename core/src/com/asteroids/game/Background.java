package com.asteroids.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {
    class Star { // класс, описывающий звезду
        private Vector2 position; // положение
        private float speed; // скорость

        public Vector2 getPosition() {
            return position;
        }

        public Star() { // тут все по аналогии с астероидом
            position = new Vector2(1280 * (float)Math.random(), 720 * (float)Math.random());
            speed = 1.0f + (float)Math.random() * 3.0f;
        }

        public void update() { // тут все по аналогии с астероидом
            position.x -= speed;
            if(position.x < 0) {
                position.x = 1300;
                position.y = (float)Math.random() * 720;
            }
        }
    }

    private Texture tex; // текстура заднего фона
    private Texture texStar; // текстура одной звезды
    private Star[] stars; // массив звезд
    private final int STARS_COUNT = 200; // кол-во звезд

    public Background() { // создание заднего фона
        // загрузили текстуры
        tex = new Texture("bg.png");
        texStar = new Texture("star12.tga");
        // создали массив звезд и проинициализировали их
        stars = new Star[STARS_COUNT];
        for (int i = 0; i < STARS_COUNT; i++) {
            stars[i] = new Star();
        }
    }

    public void render(SpriteBatch batch) { // отрисовка
        batch.draw(tex, 0, 0); // вначале присуем большую картинку
        for (int i = 0; i < STARS_COUNT; i++) { // рисуем все звезды
            batch.draw(texStar, stars[i].position.x, stars[i].position.y);
        }
    }

    public void update() { // обновляем логику заднего фона(заставляем звезды двигаться
        for (int i = 0; i < STARS_COUNT; i++) {
            stars[i].update();
        }
    }
}
