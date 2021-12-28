package app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import KSwing.KComboBox;
import KSwing.KPanel;

import fotoshop.Layer;
import fotoshop.Project;

public class InfoPanel extends KPanel
{
	private static final long serialVersionUID = 1L;

	private static InfoPanel m_infoPanelInstance;

	public static final String PROJECT_ID_STRING = "Project";

	// Panels
	private KPanel m_selectionPanel, m_panelContainer;
	private CardLayout m_cardLayout;
	private PropertiesPanel m_projectPropertiesPanel;

	// Current Component
	private static KComboBox<String> m_currentComponentBox;

	// Layers
	private static ArrayList<PropertiesPanel> m_layerPropertiesPanels;
	private static ArrayList<Integer> m_layerIDs;

	public InfoPanel()
	{		

	}

	@Override
	public void initialize() 
	{
		this.setBackground(new Color(100,100,100));
		
		InfoPanel.m_layerPropertiesPanels = new ArrayList<PropertiesPanel>();
		InfoPanel.m_layerIDs = new ArrayList<Integer>();
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

	public static InfoPanel getInstance()
	{
		if (m_infoPanelInstance == null)
			m_infoPanelInstance = new InfoPanel();

		return InfoPanel.m_infoPanelInstance;
	}

	public static ArrayList<Integer> getLayerIDs()
	{
		return InfoPanel.m_layerIDs;
	}
	
	public void addLayer(Layer layer)
	{
		m_layerIDs.add(layer.getID());

		PropertiesPanel newLayerPanel = new PropertiesPanel();

		newLayerPanel.setLayer(layer);

		newLayerPanel.addNameProperty(layer.getName());
		newLayerPanel.addDimensionProperty(layer.getDimension());
		newLayerPanel.addOffsetProperty(layer.getOffset());
		newLayerPanel.addOpacityProperty(layer.getOpacity());
		newLayerPanel.addBlendProperty();

		m_layerPropertiesPanels.add(newLayerPanel);

		m_panelContainer.add(newLayerPanel, String.valueOf(layer.getID()));
		
		m_currentComponentBox.addItem(String.valueOf(layer.getID()));
		m_currentComponentBox.setSelectedItem(String.valueOf(layer.getID()));

		m_cardLayout.show(m_panelContainer, String.valueOf(layer.getID()));
		
		Project.getInstance().setCurrentLayer(layer);;
	}

	private void onCurrentComponentUpdate()
	{
		m_cardLayout.show(m_panelContainer, (String) m_currentComponentBox.getSelectedItem());
		
		String rawID = (String) m_currentComponentBox.getSelectedItem();
		
		if (rawID.equals(PROJECT_ID_STRING))
		{
			return;
		}
		
		int id = Integer.parseInt(rawID);
		
		Project.getInstance().setCurrentLayer(Project.getInstance().getLayerFromID(id));
	}

	public void initProjectPropertiesPanel()
	{
		m_projectPropertiesPanel = new PropertiesPanel();
		m_panelContainer.add(m_projectPropertiesPanel, PROJECT_ID_STRING);
		
		m_currentComponentBox.addItem(PROJECT_ID_STRING);
		
		m_projectPropertiesPanel.addNameProperty("Project");
		m_projectPropertiesPanel.addDimensionProperty(Project.getInstance().getDimension());
		
		m_cardLayout.show(m_panelContainer, PROJECT_ID_STRING);
	}
}
