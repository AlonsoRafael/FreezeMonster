# FreezeMonster

**FreezeMonster** é um jogo de ação e estratégia onde você controla Woody, um herói que deve congelar todos os monstros enquanto evita ser atingido pelas gosmas que eles soltam. O objetivo é navegar pelo cenário, disparar raios congelantes e sobreviver ao ataque das gosmas até conseguir congelar todos os monstros.

![imagemFreeze](https://github.com/user-attachments/assets/952e95f1-5e52-4710-94a0-6628f14eb854)

## Regras do Jogo

1. **Movimentação de Woody**: Você pode mover Woody em qualquer direção usando as setas do teclado.
2. **Raio Congelante**: Woody dispara um raio congelante ao pressionar a tecla "espaço". O raio vai em linha reta na direção em que Woody estava se movendo.
3. **Comportamento do Raio Congelante**: O raio desaparece quando sai do quadro, atinge um monstro, uma gosma ou bate em uma parede.
4. **Movimento dos Monstros**: Os monstros se movem aleatoriamente em qualquer direção pelo quadro.
5. **Gosmas**: Quando um monstro se move, ele solta uma gosma que escolhe uma direção e vai em linha reta.
6. **Desaparecimento das Gosmas**: A gosma desaparece quando bate em uma parede, é atingida por um raio ou sai do quadro.
7. **Fim do Jogo**: O jogo acaba quando Woody congela todos os monstros ou quando uma gosma ou um monstro atinge Woody.

## Implementação

O jogo foi implementado utilizando o framework **Arcade-Games-Sprite-Based**, que facilita o desenvolvimento de jogos 2D com sprites, controle de animações e manipulação de eventos de entrada, como teclado e colisões.

## Como Rodar

1. Clone este repositório em sua máquina:

   git clone https://github.com/AlonsoRafael/FreezeMonster.git

2. Configuração do ambiente

   Instalar o Java.
   
   Após instalar o JDK, certifique-se de que o Java está corretamente configurado no PATH.
   
   Para verificar, execute o seguinte comando no terminal:

   java --version

   Se o comando retornar a versão do JDK instalada, o Java está configurado corretamente.

4. Execute o arquivo principal do jogo:

   java .\FreezeMonsters\src\bin\FreezeMonsters\FreezeMonstersGame.java

5. Divirta-se jogando FreezeMonster!
