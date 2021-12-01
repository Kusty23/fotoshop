package app;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import fotoshop.Layer;

public class InfoPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private static InfoPanel m_infoPanelInstance;
	
	public static final String PROJECT_ID_STRING = "Project";
	
	private CardLayout m_cardLayout;
	
	private PropertiesPanel m_projectPropertiesPanel;

	private ArrayList<PropertiesPanel> m_layerPropertiesPanels;
	private static ArrayList<Integer> m_layerIDs;
	
	public InfoPanel()
	{		
		this.setBackground(new Color(100,100,100));
		
		this.m_layerPropertiesPanels = new ArrayList<PropertiesPanel>();
		InfoPanel.m_layerIDs = new ArrayList<Integer>();
		
		generatePropertiesPanel();
		
		this.m_cardLayout = new CardLayout();
		this.setLayout(m_cardLayout);
		
		PropertiesPanel layerPanel = new PropertiesPanel();
		layerPanel.addDimensionProperty();
		layerPanel.addDimensionProperty();
		layerPanel.addOffsetProperty();
		
		this.add(this.m_projectPropertiesPanel, PROJECT_ID_STRING);
		
		m_cardLayout.show(this, PROJECT_ID_STRING);
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
		
		newLayerPanel.addNameProperty();
		newLayerPanel.addDimensionProperty();
		newLayerPanel.addOffsetProperty();
		
		m_layerPropertiesPanels.add(newLayerPanel);
		
		this.add(newLayerPanel, String.valueOf(layer.getID()));
				
		m_cardLayout.show(this, String.valueOf(layer.getID()));
	}
	
	private void generatePropertiesPanel()
	{
		m_projectPropertiesPanel = new PropertiesPanel();
		m_projectPropertiesPanel.addNameProperty();
		m_projectPropertiesPanel.addDimensionProperty();
	}
}
