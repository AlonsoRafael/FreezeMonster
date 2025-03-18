package bin.FreezeMonsters;

import bin.spriteframework.AbstractBoard;
import bin.spriteframework.MainFrame;
import bin.spriteframework.sprite.SpriteFactory;

import java.awt.*;

public class FreezeMonstersGame extends MainFrame {
    public FreezeMonstersGame() {
        super("Freeze Monsters", new FreezeMonstersSpriteFactory());
    }

    protected AbstractBoard createBoard(SpriteFactory spriteFactory) {
        return new FreezeMonstersBoard(spriteFactory);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(FreezeMonstersGame::new);
    }
}