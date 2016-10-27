package com.asteroids.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by FlameXander on 21.10.2016.
 */
public class Hero { // игрок
    // очень много вещей похожих на логику астероида
    private Vector2 position;
    private float speed;
    private Texture tex;
    private int fireCounter; // скорострельность

    public Hero() {
        position = new Vector2(100.0f, 100.0f);
        speed = 5.0f;
        tex = new Texture("ship80x60.tga");
        fireCounter = 5;
    }

    public void render(SpriteBatch batch) {
        batch.draw(tex, position.x, position.y);
    } // отрисовка

    public void update() { // Обновление игровой логики персонажа
        // Прверяем зажатия кнопок
        // Если жмем на стрели - корабль летит
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            position.y += speed;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            position.y -= speed;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            position.x += speed;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            position.x -= speed;
        // Если зажали пробел - пробуем стрелять
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
            fire();

    }

    public void fire() {
        fireCounter++; // увеличиваем счетчик стрельбы
        if(fireCounter > 10) { // если он превысил 10
            fireCounter = 0; // обнуляем
            for (int i = 0; i < AsteroidsGame.bullets.length; i++) { // проходимся по всем пулям
                if(!AsteroidsGame.bullets[i].isAlive()) { // ищем неактивную
                    AsteroidsGame.bullets[i].setup(this.position); // активируем пулю и заставляем лететь от корабля героя
                    break; // выходим из цикла поиска неактивной пули
                }
            }
        }
    }
}
