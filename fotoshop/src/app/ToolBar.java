package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

import fotoshop.BlendingModes;

public class ToolBar extends JMenuBar
{
	private static final long serialVersionUID = 1L;

	private static ToolBar m_toolbarPanelInstance;
	
	private JMenu m_fileMenu, m_layerMenu, m_brushMenu;
	
	public ToolBar()
	{
		// File Menu
		m_fileMenu = new JMenu("File");
		this.add(m_fileMenu);

		// New Project Item
		JMenuItem newProject = new JMenuItem("New Project");
		newProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {onNewProject();}
		});

		m_fileMenu.add(newProject);


		// Layer Menu
		m_layerMenu = new JMenu("Layer");
		this.add(m_layerMenu);

		// New Layer Item
		JMenuItem newLayer = new JMenuItem("New Layer");
		newLayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {onNewLayer();}
		});

		m_layerMenu.add(newLayer);

		// New Layer Item
		JMenuItem importLayer = new JMenuItem("Import Layer");
		importLayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {onImportLayer();}
		});

		m_layerMenu.add(importLayer);
		
		// Brush Menu
		m_brushMenu = new JMenu("Brush");
		this.add(m_brushMenu);

		// New Layer Item
		JMenuItem brushColor = new JMenuItem("Brush Color");
		brushColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {onBrushColor();}
		});

		m_brushMenu.add(brushColor);
	}

	public static ToolBar getInstance()
	{
		if (m_toolbarPanelInstance == null)
			m_toolbarPanelInstance = new ToolBar();
		
		return ToolBar.m_toolbarPanelInstance;
	}
	
	private void onNewLayer()
	{
		MainFrame.getProject().newLayer();
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
			System.out.println("Opening " + file.getAbsolutePath());
			MainFrame.getProject().importLayer(file);
		}
	}
	
	private void onNewProject()
	{
		MainFrame.getInstance().createNewProject();
	}
	
	private void onBrushColor()
	{
		MainFrame.getProject().BRUSH_COLOR = BlendingModes.packColor(255, 200, 150, 0);
		
		ColorSelectionWindow csw = new ColorSelectionWindow();
		
		csw.setAlwaysOnTop(true);
	}
}
