package fotoshop.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

import fotoshop.project.Project;

public class LayerMenu extends JMenu
{
	private static final long serialVersionUID = 1L;
	
	public LayerMenu()
	{
		super("Layer");
		
		// New Layer Item
		JMenuItem newLayer = new JMenuItem("New Layer");
		newLayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {onNewLayer();}
		});

		this.add(newLayer);

		// New Layer Item
		JMenuItem importLayer = new JMenuItem("Import Layer");
		importLayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {onImportLayer();}
		});

		this.add(importLayer);
	}
	
	private void onNewLayer()
	{
		Project.getInstance().newLayer();
	}

	private void onImportLayer()
	{
		/*
		 * At some point should probably store the previous working directory to make it better but
		 * for now this is fine and easy for testing
		 */
		JFileChooser fileChooser = new JFileChooser("C:\\Users\\ryank\\OneDrive\\Dillusional\\"); 

		// Filter class to limit to good files
		class filter extends FileFilter 
		{
			public boolean accept(File file) {
				String filename = file.getName();
				return filename.endsWith(".png") 
						|| filename.endsWith(".jpg")
						|| file.isDirectory();
			}

			public String getDescription() 
			{
				return "*.png, *.jpg";
			}
		}

		fileChooser.setFileFilter(new filter());

		// Actually open the chooser
		int status = fileChooser.showOpenDialog(this);

		// Only load new image if new image is selected, otherwise will overwrite on cancel
		if (status == JFileChooser.APPROVE_OPTION)
		{
			File file = fileChooser.getSelectedFile();
			Project.getInstance().importLayer(file);
		}
	}
}
