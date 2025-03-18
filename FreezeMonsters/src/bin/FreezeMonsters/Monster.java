package bin.FreezeMonsters;

import bin.ImageResizer;
import bin.spriteframework.sprite.Bomb;
import bin.spriteframework.sprite.BomberSprite;

import javax.swing.*;

import java.util.Random;

import static bin.FreezeMonsters.CommonsFreezeMonsters.*;

public class Monster extends BomberSprite {
    int numMonster;
    boolean freezed;
    public Monster(int x, int y, int width, int height) { // Construtor da classe Monster, recebe coordenadas x e y, largura e altura
        super(width, height);
        initBomber(x,y); // Inicializa o monstro com as coordenadas x e y fornecidas
    }

    private void initBomber(int x, int y) { // Método para inicializar o monstro com as coordenadas x e y fornecidas
        this.x = x;
        this.y = y;
        long seed = System.currentTimeMillis();
        Random rand = new Random(seed);
        // Gera um número aleatório para determinar o tipo de monstro
        numMonster = rand.nextInt(1,VARIETY_MONSTERS);
        // Cria uma bomba associada ao monstro
        bomb = new Bomb(IMAGE_SLIME, x, y, SLIME_WIDTH, SLIME_HEIGHT);
        // Redimensiona a imagem do monstro de acordo com as dimensões especificadas
        ImageIcon resizedIcon = ImageResizer.resizeImage(pathMonster + numMonster + ".png", MONSTER_WIDTH, MONSTER_HEIGHT);
        // Define a imagem redimensionada como a imagem do monstro
        setImage(resizedIcon.getImage());
    }
    public void freeze() { // Método para congelar o monstro
        setFreeze(true); // Define que o monstro está congelado
        setDirection(0, 0); // Define a direção do monstro como 0, 0 para pará-lo
        // Redimensiona a imagem do monstro para mostrar a imagem congelada
        ImageIcon resizedIcon = ImageResizer.resizeImage(pathMonster + numMonster + "bg.png", MONSTER_WIDTH, MONSTER_HEIGHT);
        // Define a imagem redimensionada como a imagem congelada do monstro
        setImage(resizedIcon.getImage());

    }

    public void setFreeze(boolean bool) { // Método para definir o estado de congelamento do monstro
        freezed = bool;
    }

    public boolean isFreezed() { // Método para verificar se o monstro está congelado
        return freezed;
    }
}
