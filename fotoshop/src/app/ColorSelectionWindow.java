package app;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fotoshop.BlendingModes;

public class ColorSelectionWindow extends JDialog
{
	private static final long serialVersionUID = 1L;

	private JColorChooser m_cc;
	
	public ColorSelectionWindow()
	{
		super();
		
		this.m_cc = new JColorChooser();
		
		this.m_cc.getSelectionModel().addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {onUpdateColor();}
        });
		
		this.add(m_cc);
		
		this.setSize(new Dimension(700, 400));
		
		this.setVisible(true);
	}
	
	private void onUpdateColor()
	{
		Color color = m_cc.getColor();
		
		MainFrame.getProject().BRUSH_COLOR = BlendingModes.packColor(color.getAlpha(), color.getRed(), color.getGreen(), color.getBlue());
		System.out.println("brush changed to " + color);
	}
	
	public Color getColor()
	{
		this.dispose();
		return m_cc.getColor();
	}
}
