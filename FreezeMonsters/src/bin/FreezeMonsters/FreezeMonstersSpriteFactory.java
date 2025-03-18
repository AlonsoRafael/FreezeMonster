package bin.FreezeMonsters;

import bin.spriteframework.sprite.*;

import static bin.FreezeMonsters.CommonsFreezeMonsters.*;

public class FreezeMonstersSpriteFactory implements SpriteFactory {
    @Override
    public Player createPlayer() { // Implementa o método para criar um jogador
        // Retorna uma nova instância de Player com as propriedades de imagem, largura e altura definidas
        return new Player(IMAGE_PLAYER, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    @Override
    public BomberSprite createBadSprite(int x, int y) { // Implementa o método para criar um sprite ruim (neste caso, um monstro)
        // Retorna uma nova instância de Monster com as coordenadas x e y e as propriedades de largura e altura definidas
        return new Monster(x, y, MONSTER_WIDTH, MONSTER_HEIGHT);
    }

    @Override
    public Shot createRay(int x, int y) { // Implementa o método para criar um tiro (raio) em uma posição específica
        // Retorna uma nova instância de Shot com a imagem, coordenadas x e y, e as propriedades de largura e altura definidas
        return new Shot(IMAGE_RAY, x, y, RAY_WIDTH, RAY_HEIGHT);
    }

    @Override
    public Shot createRay(){ // Implementa o método para criar um tiro (raio) com valores padrão
        // Retorna uma nova instância de Shot sem parâmetros específicos
        return new Shot();
    }

}

