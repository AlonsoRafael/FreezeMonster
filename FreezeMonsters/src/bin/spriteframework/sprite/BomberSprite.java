package bin.spriteframework.sprite;
import java.util.LinkedList;


public class BomberSprite extends BadnessBoxSprite {

    protected Bomb bomb; // Variável que representa uma bomba associada ao bombardeiro
    public BomberSprite(int width, int height) { // Construtor da classe BomberSprite, que recebe largura e altura como parâmetros
        this.width = width; // Define a largura do sprite
        this.height = height; // Define a altura do sprite
    }



    public Bomb getBomb() { // Método para obter a bomba associada ao bombardeiro
        return bomb; // Retorna a bomba
    }

    @Override
    public LinkedList<BadSprite> getBadnesses() { // Sobrescreve o método getBadnesses da superclasse BadnessBoxSprite
        LinkedList<BadSprite> aSlime = new LinkedList<>();  // Cria uma lista vinculada para armazenar maus sprites
        aSlime.add(bomb); // Adiciona a bomba à lista de maus sprites
        return aSlime;// Retorna a lista com a bomba
    }
}
