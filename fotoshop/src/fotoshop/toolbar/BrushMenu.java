package fotoshop.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import app.ColorSelectionWindow;
import fotoshop.BlendingModes;
import fotoshop.Project;

public class BrushMenu extends JMenu
{
	private static final long serialVersionUID = 1L;
	
	public BrushMenu()
	{
		super("Brush");
		
		// Brush Color Item
		JMenuItem brushColor = new JMenuItem("Brush Color");
		brushColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {onBrushColor();}
		});

		this.add(brushColor);
	}
	
	private void onBrushColor()
	{
		Project.getInstance().BRUSH_COLOR = BlendingModes.packColor(255, 200, 150, 0);

		ColorSelectionWindow csw = new ColorSelectionWindow();

		csw.setAlwaysOnTop(true);
	}
}
