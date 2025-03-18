package bin.spriteframework.sprite;

import bin.ImageResizer;

import javax.swing.ImageIcon;


public class Shot extends BadSprite {
    public Shot() { // Construtor sem argumentos, inicializa o tiro como não visível
        setVisible(false);
    }

    public Shot(String path, int x, int y, int width, int height) {  // Construtor que recebe o caminho da imagem, posição inicial e dimensões do tiro
        this.path = path; // Define o caminho da imagem
        this.width = width; // Define a largura do tiro
        this.height = height; // Define a altura do tiro
        initializeRay(x, y); // Inicializa o tiro com a posição inicial
    }

    private void initializeRay(int x, int y) { // Método para inicializar o tiro com posição inicial
        ImageIcon resizedIcon = ImageResizer.resizeImage(path, width, height); // Redimensiona a imagem com base nas dimensões do tiro
        setImage(resizedIcon.getImage()); // Define a imagem redimensionada do tiro
        setVisible(true); // Define o tiro como visível
        // Define espaços horizontais e verticais de ajuste para o tiro
        int H_SPACE = 6;// Espaço horizontal
        setX(x + H_SPACE); // Define a posição x do tiro com base no espaço horizontal

        int V_SPACE = 1; // Espaço vertical
        setY(y - V_SPACE); // Define a posição y do tiro com base no espaço vertical
    }
}
