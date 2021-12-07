package app;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fotoshop.BlendingModes;
import fotoshop.Project;

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
		
		this.m_cc.setPreviewPanel(new JPanel());
		
		this.add(m_cc);
		
		this.setSize(new Dimension(700, 400));
		
		this.setVisible(true);
	}
	
	private void onUpdateColor()
	{
		Color color = m_cc.getColor();
		
		Project.getInstance().BRUSH_COLOR = BlendingModes.packColor(color.getAlpha(), color.getRed(), color.getGreen(), color.getBlue());
	}
	
	public Color getColor()
	{
		this.dispose();
		return m_cc.getColor();
	}
}
