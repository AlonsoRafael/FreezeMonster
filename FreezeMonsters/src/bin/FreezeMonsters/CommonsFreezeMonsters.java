package bin.FreezeMonsters;

import bin.spriteframework.Commons;

public interface CommonsFreezeMonsters extends Commons {
    int SLIME_HEIGHT = 30; //
    int SLIME_WIDTH = 30;
    int SLIME_SPEED = 2;
    int MONSTER_HEIGHT = 50;
    int MONSTER_WIDTH = 50;
    int NUMBER_OF_MONSTERS_TO_DESTROY = 24;
    int PLAYER_WIDTH = 50;
    int PLAYER_HEIGHT = 50;
    int MONSTER_SPEED = 1;
    int PLAYER_SPEED = 5;
    int RAY_WIDTH = 30;
    int RAY_HEIGHT = 30;
    int RAY_SPEED = 5;
    String pathMonster = "FreezeMonsters/images/monster";
    String IMAGE_PLAYER = "FreezeMonsters/images/woody.png";
    String IMAGE_SLIME = "FreezeMonsters/images/gosma.png";
    String IMAGE_RAY = "FreezeMonsters/images/ray.png";
    int VARIETY_MONSTERS = 9;
    int BORDER_TOLERANCE = 5;
    int INIT_PLAYER_X = 500;
    int INIT_PLAYER_Y = 700;
    int DELAY = 17;
}
