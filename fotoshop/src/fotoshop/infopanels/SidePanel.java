package fotoshop.infopanels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import KSwing.components.KComboBox;
import KSwing.containers.KPanel;
import fotoshop.Layer;
import fotoshop.project.Project;

public class SidePanel extends KPanel
{
	private static final long serialVersionUID = 1L;

	private static SidePanel m_infoPanelInstance;

	// Panels
	private KPanel m_selectionPanel, m_panelContainer;
	private CardLayout m_cardLayout;

	// Current Component
	private static KComboBox<String> m_currentComponentBox;

	// Layers
	private static ArrayList<LayerInfoPanel> m_infoPanels;
	private static ArrayList<Integer> m_layerIDs;

	public SidePanel()
	{		

	}

	@Override
	public void initialize() 
	{
		this.setBackground(new Color(100,100,100));
		
		SidePanel.m_infoPanels = new ArrayList<LayerInfoPanel>();
		SidePanel.m_layerIDs = new ArrayList<Integer>();
	}

	@Override
	public void createComponents() 
	{
		// Selection Panel
		m_selectionPanel = new KPanel();

		m_currentComponentBox = new KComboBox<String>(new String[] {});
		m_selectionPanel.add(m_currentComponentBox);

		m_currentComponentBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {onCurrentComponentUpdate();}
		});
		
		// Panel Container
		m_panelContainer = new KPanel();
		
		this.m_cardLayout = new CardLayout();
		m_panelContainer.setLayout(m_cardLayout);
	}

	@Override
	public void arrangeComponents() 
	{
		this.setLayout(new BorderLayout());
		
		this.add(m_selectionPanel, BorderLayout.PAGE_START);
		this.add(m_panelContainer, BorderLayout.CENTER);
	}

	public static SidePanel getInstance()
	{
		if (m_infoPanelInstance == null)
			m_infoPanelInstance = new SidePanel();

		return SidePanel.m_infoPanelInstance;
	}

	public static ArrayList<Integer> getLayerIDs()
	{
		return SidePanel.m_layerIDs;
	}
	
	public void addLayer(Layer layer)
	{
		m_layerIDs.add(layer.getID());
		
		// Must set to current layer before initiating layer panel
		Project.getInstance().setCurrentLayer(layer);

		LayerInfoPanel newLayerPanel = new LayerInfoPanel();

		m_infoPanels.add(newLayerPanel);

		m_panelContainer.add(newLayerPanel, String.valueOf(layer.getID()));
		
		m_currentComponentBox.addItem(String.valueOf(layer.getID()));
		m_currentComponentBox.setSelectedItem(String.valueOf(layer.getID()));

		m_cardLayout.show(m_panelContainer, String.valueOf(layer.getID()));
	}

	private void onCurrentComponentUpdate()
	{
		m_cardLayout.show(m_panelContainer, (String) m_currentComponentBox.getSelectedItem());
		
		String rawID = (String) m_currentComponentBox.getSelectedItem();
		
		int id = Integer.parseInt(rawID);
		
		Project.getInstance().setCurrentLayer(Project.getInstance().getLayerFromID(id));
	}
}
