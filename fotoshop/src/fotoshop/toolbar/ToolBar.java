package fotoshop.toolbar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class ToolBar extends JMenuBar
{
	private static final long serialVersionUID = 1L;

	private static ToolBar m_toolbarPanelInstance;

	private JMenu m_fileMenu, m_layerMenu, m_brushMenu, m_filterMenu;

	public ToolBar()
	{
		// File Menu
		m_fileMenu = new FileMenu();
		this.add(m_fileMenu);


		// Layer Menu
		m_layerMenu = new LayerMenu();
		this.add(m_layerMenu);

		// Brush Menu
		m_brushMenu = new BrushMenu();
		this.add(m_brushMenu);	

		// Filter Menu
		m_filterMenu = new FilterMenu();
		this.add(m_filterMenu);
	}

	public static ToolBar getInstance()
	{
		if (m_toolbarPanelInstance == null)
			m_toolbarPanelInstance = new ToolBar();

		return ToolBar.m_toolbarPanelInstance;
	}
}
