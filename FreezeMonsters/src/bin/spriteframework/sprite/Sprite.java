package bin.spriteframework.sprite;

import java.awt.Image;
import java.awt.Rectangle;
public class Sprite{
    private boolean visible = true;
    protected Image image;
    protected String path;
    private boolean dying;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int[] direction; //direction
    protected int dx;
    protected int dy;

    public Sprite() {
        direction = new int[2];
    }

    public void die() {
        this.visible = false;
    }

    public boolean isVisible() {
        return this.visible;
    }

    protected void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

    public int[] getDirection() {
        return direction;
    }

    public void setDirection(int x, int y) {
        this.direction[0] = x;
        this.direction[1] = y;
    }

    public void setDirectionX() {
        if (this.direction[0] == 0) {
            this.direction[0] = -1;
        }
        this.direction[0] = this.direction[0] * -1;
    }

    public void setDirectionY() {
        if (this.direction[1] == 0) {
            this.direction[1] = 1;
        }
        this.direction[1] = this.direction[1] * -1;
    }

    public Rectangle getRect() {
        return new Rectangle(this.x, this.y, this.image.getWidth(null), this.image.getHeight(null));
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public boolean isDying() {
        return this.dying;
    }

    public void move(int speed) {
        this.x += this.direction[0] * speed;
        this.y += this.direction[1] * speed;
    }
    public void moveX(int direction){
        this.x += direction;
    }

}
