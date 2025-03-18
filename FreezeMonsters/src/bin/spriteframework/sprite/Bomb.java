package bin.spriteframework.sprite;

import bin.ImageResizer;

import javax.swing.*;

public class Bomb extends BadSprite {
    private boolean destroyed; // Indicador de se a bomba está destruída

    public Bomb(String path, int x, int y, int width, int height) {  // Construtor da classe Bomb, recebe o caminho da imagem, coordenadas x e y, largura e altura
        this.path = path;
        this.width = width;
        this.height = height;
        initializeBomb(x, y); // Inicializa a bomba com as coordenadas x e y fornecidas
    }

    private void initializeBomb(int x, int y) { // Método para inicializar a bomba com as coordenadas x e y fornecidas
        setDestroyed(true); // Define a bomba como destruída inicialmente

        this.x = x; // Define a posição x da bomba
        this.y = y; // Define a posição y da bomba

        // Redimensiona a imagem da bomba com base no caminho, largura e altura fornecidos
        ImageIcon resizedIcon = ImageResizer.resizeImage(path,  width, height);
        setImage(resizedIcon.getImage()); // Define a imagem redimensionada como a imagem da bomba
    }

    public void setDestroyed(boolean isDestroyed) { // Método para definir o estado de destruição da bomba
        destroyed = isDestroyed; // Atualiza o estado de destruição da bomba
    }

    public boolean isDestroyed() { // Método para verificar se a bomba está destruída
        return destroyed; // Retorna o estado de destruição da bomba
    }
}
