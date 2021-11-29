import java.awt.Color;
import java.awt.Graphics;

public class TransparencyTile 
{
	public static final int WIDTH = 100; 
    public static final int HEIGHT = 100;

    // Unique id across all transparency tiles
    public static int currentId = 0; 

    private int id;
    private int x;
    private int y;
    private int width;
    private int height;

    public TransparencyTile(int x, int y) 
    {
        this.id = currentId++;
        this.x = x;
        this.y = y;
        width = WIDTH;
        height = HEIGHT;
    }

    public int getId()
    {
        return id;
    }

    public void draw(Graphics g)
    {
        g.setColor( id % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
        g.fillRect(x, y, width, height);
    }
}
