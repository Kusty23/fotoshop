package app;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import fotoshop.Layer;

public class ViewMouseListener implements MouseListener, MouseMotionListener
{	
	private int m_initialX, m_initialY;
	private int m_initialOffsetX, m_initialOffsetY;
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		System.out.println("Click - " + e.getX() + "," + e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		m_initialX = e.getX();
		m_initialY = e.getY();
		
		Layer layer = InfoPanel.getInstance().getCurrentLayer();
		if (layer != null)
		{
			m_initialOffsetX = (int) layer.getOffset().getWidth();
			m_initialOffsetY = (int) layer.getOffset().getHeight();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		//System.out.println("Enter - " + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		//System.out.println("Exit - " + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		int m_draggedX = e.getX();
		int m_draggedY = e.getY();
		
		Layer layer = InfoPanel.getInstance().getCurrentLayer();
		if (layer != null)
		{		
			int delX = m_draggedX - m_initialX;			
			int delY = m_draggedY - m_initialY;
			
			int posX = (int) (m_initialOffsetX + delX);
			int posY = (int) (m_initialOffsetY + delY);
			
			Dimension newOffset = new Dimension(posX, posY);
			
			layer.setOffset(newOffset);
			
			ViewPanel.getInstance().repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		//System.out.println("Move - " + e.getX() + "," + e.getY());
	}

}
