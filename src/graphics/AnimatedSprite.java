package graphics;

public class AnimatedSprite extends Sprite {

    private int frame = 0;
    private Sprite sprite;
    private int rate = 5;
    private int time = 0;
    private int length = -1;

    public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {
        super(sheet, width, height);
        this.length = length;
        sprite = sheet.getSprites()[0];
        if (length > sheet.getSprites().length) {
            throw new IllegalArgumentException("Length of animated sprite exceeds number of sprites in sheet");
        }
    }

    public void update() {

        time++;
        if (time % rate == 0) {
            if(frame >= length - 1) frame = 0;
            else frame++;
            sprite = sheet.getSprites()[frame];
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setFrameRate(int frames) {
        rate = frames;
    }

    public void setFrame(int frame) {
        
        if (frame > sheet.getSprites().length - 1) {
            throw new IllegalArgumentException("Frame exceeds number of sprites in sheet");
        }
        this.frame = frame;
        sprite = sheet.getSprites()[frame];
    }

}
