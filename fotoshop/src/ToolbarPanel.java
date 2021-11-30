import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

public class ToolbarPanel extends JMenuBar
{
	private MainFrame m_mainFrame;

	public ToolbarPanel(MainFrame mf)
	{
		this.m_mainFrame = mf;

		// File Menu
		JMenu fileMenu = new JMenu("File");
		this.add(fileMenu);

		// Load Image Item
		JMenuItem loadImage = new JMenuItem("Load Image");
		loadImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {onLoadImage();}
		});

		fileMenu.add(loadImage);

		// New Project Item
		JMenuItem newProject = new JMenuItem("New Project");
		newProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {onNewProject();}
		});

		fileMenu.add(newProject);


		// Layer Menu
		JMenu layerMenu = new JMenu("Layer");
		this.add(layerMenu);

		// Load Image Item
		JMenuItem newLayer = new JMenuItem("New Layer");
		newLayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {onNewLayer();}
		});

		layerMenu.add(newLayer);


		// Effects Menu
		JMenu effectsMenu = new JMenu("Effects");
		this.add(effectsMenu);

		// Move Item
		JMenuItem move = new JMenuItem("Move Image");
		move.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {onMove();}
		});

		effectsMenu.add(move);
	}

	private void onNewLayer()
	{
		this.m_mainFrame.m_project.newLayer();
	}
	
	private void onNewProject()
	{
		this.m_mainFrame.createNewProject();
	}

	private void onMove()
	{
		this.m_mainFrame.m_project.layer1.m_posx += 50;
		this.m_mainFrame.repaint();
	}

	private void onLoadImage()
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
			System.out.println("Opening " + file.getAbsolutePath());
			m_mainFrame.sendImageFileToViewPanel(file);
		}
	}
}
