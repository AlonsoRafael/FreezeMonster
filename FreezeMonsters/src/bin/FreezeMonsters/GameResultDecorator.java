package bin.FreezeMonsters;

import java.awt.*;

public interface GameResultDecorator { // Interface para decorar a tela do jogo com um resultado específico
    void decorateScreen(Graphics g, String message); // Método para decorar a tela com uma mensagem
}