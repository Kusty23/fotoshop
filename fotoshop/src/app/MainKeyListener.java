package app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainKeyListener implements KeyListener
{

	@Override
	public void keyTyped(KeyEvent e) 
	{

	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_B: ViewMouseListener.setMouseMode(ViewMouseListener.BRUSH_MODE); break;
		case KeyEvent.VK_V: ViewMouseListener.setMouseMode(ViewMouseListener.MOVE_MODE); break;
		
		default: System.out.println("Key Code " + e.getKeyCode() + " does not match");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{

	}

}
