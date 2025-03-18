package bin.FreezeMonsters;

import java.awt.*;

import static bin.spriteframework.Commons.BOARD_HEIGHT;
import static bin.spriteframework.Commons.BOARD_WIDTH;

public class LoseScreenDecorator implements GameResultDecorator { // Implementação do método de decorar a tela para exibir uma mensagem de derrota
    @Override
    public void decorateScreen(Graphics g, String message) {
        // Define a lógica de decoração para a tela de derrota
        g.setColor(Color.RED);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        g.setColor(Color.WHITE);
        g.drawString("Game Over! " + message, BOARD_WIDTH / 2 - 120, BOARD_HEIGHT / 2);
    }
}