package com.asteroids.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AsteroidsGame extends ApplicationAdapter {
	SpriteBatch batch; // область отрисовки
	Background bg; // задний фон
	Hero hero; // герой
	Asteroid[] asteroids; // массив астероидов
	public static Bullet[] bullets; // массив пуль
	final int BULLETS_COUNT = 50; // макс кол-во пуль
	final int ASTEROIDS_COUNT = 20; // макс кол-во астероидов

	@Override
	public void create () {
        batch = new SpriteBatch(); // создеам область отрисовки
        bg = new Background(); // создаем задний фон
        hero = new Hero(); // создаем персонажа
        asteroids = new Asteroid[ASTEROIDS_COUNT]; // инициализируем массив астероидов
        for (int i = 0; i < asteroids.length; i++) { // цикл
            asteroids[i] = new Asteroid(); // создаем каждый астероид
        }
        bullets = new Bullet[BULLETS_COUNT]; // инициализируем массив пуль
        for (int i = 0; i < bullets.length; i++) { // цикл
            bullets[i] = new Bullet(); // создаем каждую пулю
        }
    }

	@Override
	public void render () {
			update(); // обновляем логику игры
			Gdx.gl.glClearColor(1, 1, 1, 1); // задаем цвет очистки окна - белый
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // очищаем окно
			batch.begin(); // начинаем отрисовку(вся отрисовка должны быть между begin/end)
			bg.render(batch); // рисуем задний фон
			hero.render(batch); // рисуем игрока
			for (Asteroid o : asteroids) { // в цикле рисуем все астероиды
				o.render(batch);
			}
			for (Bullet o : bullets) { // в цикле рисуем все активные пули
				if (o.isAlive()) // если пуля активна
					o.render(batch); // рисуем ее
			}
			batch.end(); // заканчиваем отрисовку
	}

	public void update() { // метод обновления логики
			bg.update(); // обновляем задний фон
			hero.update(); // обновляем игрока
			for (Asteroid o : asteroids) { // обновляем все астероиды
				o.update(); // у каждого вызываем метод update()
			}
			for (Bullet o : bullets) { // обновляем все активные пули
				if (o.isAlive())
					o.update();
			}
			for (Asteroid a : asteroids) { // в двойном цикле проверяем столкновение пуль с астероидами
				for (Bullet b : bullets) {
					if(b.isAlive()) { // если пуля активна, то
						if(b.getPosition().x > a.getPosition().x && b.getPosition().x < a.getPosition().x + 60 && b.getPosition().y > a.getPosition().y && b.getPosition().y < a.getPosition().y + 60) { // проверяем столкнулась ли пуля с астероидом
							b.destroy(); // "выключаем" пулю
							a.recreate(); // пересоздаем астероид
							break;
						}
					}
				}
			}
		}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
