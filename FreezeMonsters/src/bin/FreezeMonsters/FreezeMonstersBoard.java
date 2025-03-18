package bin.FreezeMonsters;

import bin.spriteframework.AbstractBoard;
import bin.spriteframework.sprite.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import static bin.FreezeMonsters.CommonsFreezeMonsters.*;

public class FreezeMonstersBoard extends AbstractBoard {
    private Shot ray;
    private int deaths = 0;
    private GameResultDecorator currentDecorator; //saber qual tela exibir

    public FreezeMonstersBoard(SpriteFactory spriteFactory) {
        super(spriteFactory);
        // Inicializa a classe com a fábrica de sprites fornecida
    }


    protected void createBadSprites() { // Método para criar monstros inimigos no tabuleiro
        long seed = System.currentTimeMillis();
        Random rand = new Random(seed);

        // Cria uma quantidade definida de monstros com posições e direções aleatórias
        for (int i = 0; i < NUMBER_OF_MONSTERS_TO_DESTROY; ++i) {
            int randX = rand.nextInt(BORDER_LEFT + BORDER_TOLERANCE, BOARD_WIDTH / 2);
            int randY = rand.nextInt(BORDER_UP + BORDER_TOLERANCE, BOARD_HEIGHT / 2);
            int xDirection = (randX % 3) - 1;
            int yDirection = (randX % 3) - 1;
            Monster monster = (Monster) this.spriteFactory.createBadSprite(randX, randY); // Cria um monstro
            monster.setDirection(xDirection, yDirection); // Define a direção do monstro
            this.badSprites.add(monster); // Adiciona o monstro à lista de monstros
        }
    }

    protected void createOtherSprites() { // Método para criar outros sprites como o tiro inicial
        this.ray = spriteFactory.createRay();
    }

    private void drawShot(Graphics g) { // Desenha o tiro
        if (this.ray.isVisible()) {
            g.drawImage(this.ray.getImage(), this.ray.getX(), this.ray.getY(), this);
        }
    }

    protected void drawOtherSprites(Graphics g) { // Desenha outros sprites
        this.drawShot(g);
    }

    protected void processOtherSprites(Player player, KeyEvent e) { // Processa outros sprites com base nas ações do jogador
        int x = player.getX();
        int y = player.getY();
        int key = e.getKeyCode();

        // Lança um tiro quando a tecla espaço é pressionada
        if (key == KeyEvent.VK_SPACE && this.inGame && !this.ray.isVisible()) {
            this.ray = spriteFactory.createRay(x,y);
            this.ray.setDirection(player.getDirection()[0], player.getDirection()[1]);
        }
    }

    protected void update() { // Atualiza o estado do jogo, incluindo verificar vitória ou derrota
        if (this.deaths == NUMBER_OF_MONSTERS_TO_DESTROY) {
            handleGameWon();
            return;
        }
        updatePlayers();
        updateRay();
        updateMonsters();
        updateOtherSprites();
    }

    protected void updateMonsters() { // Atualiza o estado dos monstros no jogo
        for (BadSprite monster : badSprites) {
            if (monster.isVisible()) {
                int x = monster.getX();
                int y = monster.getY();
                // Verifica colisões com as bordas e lida com isso
                monsterBouncesBorder(x, y, (BomberSprite) monster);
                // Verifica se o monstro saiu dos limites do tabuleiro
                if (x > BOARD_WIDTH - MONSTER_WIDTH - BORDER_TOLERANCE || x < BORDER_LEFT - BORDER_TOLERANCE ||
                        y > BOARD_HEIGHT - MONSTER_HEIGHT - BORDER_TOLERANCE|| y < BORDER_UP - BORDER_TOLERANCE) {
                    handleGameOver();
                }
                // Move o monstro com uma velocidade definida
                monster.move(MONSTER_SPEED);
            }
        }
    }

    private void handleSlimeHit(Bomb slime) { // Lida com a colisão entre o tiro e uma gosma
        slime.setDestroyed(true); //destroi a img do raio
        slime.die();// Remover a gosma ao atingir o raio congelante
        ray.die(); // O raio também deve desaparecer
    }
    protected void updateRay() { // Atualiza o estado do tiro
        if (ray.isVisible()) {
            Rectangle rayRect = ray.getRect();
            // Verifica colisões com monstros
            for (BadSprite monster : badSprites) {
                Rectangle monsterRect = monster.getRect();
                if (!((Monster)monster).isFreezed() && this.ray.isVisible() && rayRect.intersects(monsterRect)) {
                    handleMonsterHit((Monster) monster);
                }
            }

            // Verificar colisão com gosmas
            for (BadSprite monster : badSprites) {
                // Obter a gosma associada ao monstro
                Bomb slime = ((Monster)monster).getBomb();
                if (!slime.isDestroyed()) {
                    Rectangle slimeRect = slime.getRect();
                    // Verificar se o raio está visível e colide com a gosma
                    if (ray.isVisible() && rayRect.intersects(slimeRect)) {
                        handleSlimeHit(slime);
                    }
                }
            }
            // Move o tiro e verifica se ele saiu dos limites do tabuleiro
            int x = ray.getX();
            int y = ray.getY();
            ray.move(RAY_SPEED);
            if (y <= 0 || x <= BORDER_LEFT || x >= BORDER_RIGHT || y >= BORDER_DOWN - SLIME_HEIGHT) {
                this.ray.die();
            } else {
                this.ray.move(RAY_SPEED);
            }
        }
    }

    protected void updatePlayers() { // Atualiza o estado dos jogadores
        for (Player player : players) {
            player.act(); // Permite que os jogadores realizem ações
        }
    }

    protected void updateOtherSprites() { // Atualiza o estado de outros sprites, incluindo gosmas e colisões
        Random generator = new Random();
        Rectangle playerRect = players.get(0).getRect();

        for (BadSprite monster : this.badSprites) {
            Rectangle monsterRect = monster.getRect();
            // Verifica colisões entre o jogador e os monstros
            if (this.players.get(0).isVisible() && !((Monster)monster).isFreezed() && playerRect.intersects(monsterRect)) {
                this.players.get(0).setDying(true);
            }
            // Controla o lançamento de gosmas pelos monstros
            int ray = generator.nextInt(15);
            int directionX = generator.nextInt(3);
            int directionY = generator.nextInt(3);
            Bomb slime = ((Monster)monster).getBomb();
            Rectangle slimeRect = slime.getRect();

            // Verifica se um monstro deve lançar uma gosma
            if (ray == 5 && !((Monster)monster).isFreezed() && slime.isDestroyed()) {
                handleSlimeRelease(((Monster)monster), directionX, directionY);
            }

            // Verifica colisões entre o jogador e a gosma
            if (this.players.get(0).isVisible() && !slime.isDestroyed() && slimeRect.intersects(playerRect)) {
                handlePlayerHit(slime);
            }
            // Controla o movimento das gosmas
            handleSlimeMovement(slime);
        }
    }

    private void handleGameWon() { // Lida com a condição de vitória no jogo
        this.inGame = false;
        this.timer.stop();
        this.message = "You Win!";
        this.currentDecorator = new WinScreenDecorator();
        repaint();  // Redesenha o tabuleiro para aplicar a decoração
    }

    private void handleGameOver() { // Lida com a condição de derrota no jogo
        this.inGame = false;
        this.message = "Invasion!";
        this.currentDecorator = new LoseScreenDecorator();
        repaint();  // Redesenha o tabuleiro para aplicar a decoração
    }


    public void paintComponent(Graphics g) { // Método para pintar componentes gráficos, incluindo a tela de vitória ou derrota
        super.paintComponent(g);
        this.doDrawing(g);
        // Desenha a tela de vitória ou derrota se o jogo não estiver ativo
        if (!this.inGame) {
            this.decorateResultScreen(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }


    private void decorateResultScreen(Graphics g) { // Método para aplicar a decoração de vitória ou derrota
        if (this.currentDecorator != null) {
            this.currentDecorator.decorateScreen(g, this.message);
        }
    }
    private void handleMonsterHit(Monster monster) { // Lida com a colisão entre um tiro e um monstro
        monster.freeze(); // Congela o monstro ao ser atingido
        ++this.deaths; // Incrementa o contador de mortes
        this.ray.die(); // Faz o tiro desaparecer
    }

    private void handleSlimeRelease(Monster monster, int directionX, int directionY) { // Lida com o lançamento de gosmas pelos monstros
        Bomb slime = monster.getBomb();
        slime.setDestroyed(false); // Ativa a gosma
        slime.setX(monster.getX());
        slime.setY(monster.getY());
        slime.setDirection(directionX % 3 - 1, directionY % 3 - 1); // Define a direção da gosma
    }

    private void handlePlayerHit(Bomb slime) { // Lida com a colisão entre uma gosma e o jogador
        this.players.get(0).setDying(true);
        slime.setDestroyed(true); // Destrói a gosma ao atingir o jogador
    }

    private void handleSlimeMovement(Bomb slime) { // Lida com o movimento de gosmas no jogo
        if (!slime.isDestroyed()) {
            slime.move(SLIME_SPEED); // Move a gosma
            // Verifica se a gosma saiu dos limites do tabuleiro
            if (slime.getY() >= BOARD_HEIGHT - 2 * SLIME_HEIGHT || slime.getY() <= BORDER_UP || slime.getX() >= BOARD_WIDTH - 2 * SLIME_WIDTH || slime.getX() <= BORDER_LEFT) {
                slime.setDestroyed(true); // Destrói a gosma se ela sair dos limites
            }
        }
    }

    private void monsterBouncesBorder(int x, int y, BomberSprite monster) { // Lida com a colisão entre um monstro e as bordas do tabuleiro
        if (x <= 2 || x >= BOARD_WIDTH - 2 * MONSTER_WIDTH) {
            // Inverte a direção do monstro ao colidir com as bordas horizontais
            monster.setDirectionX();
        }
        if (y <= 2 || y >= BOARD_HEIGHT - 2 * MONSTER_HEIGHT) {
            // Inverte a direção do monstro ao colidir com as bordas verticais
            monster.setDirectionY();
        }
    }

}