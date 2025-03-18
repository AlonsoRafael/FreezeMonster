package bin.FreezeMonsters;

import java.awt.*;

import static bin.spriteframework.Commons.BOARD_HEIGHT;
import static bin.spriteframework.Commons.BOARD_WIDTH;

public class WinScreenDecorator implements GameResultDecorator { // Implementação do método de decorar a tela para exibir uma mensagem de vitoria
    @Override
    public void decorateScreen(Graphics g, String message) {
        // Define a lógica de decoração para a tela de vitoria
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawString("You Win! " + message, BOARD_WIDTH / 2 - 100, BOARD_HEIGHT / 2);
    }
}