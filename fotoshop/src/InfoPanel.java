import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel
{
	MainFrame m_mainFrame;
	
	JPanel m_selectionPanel, m_propertiesPanel;
	
	public InfoPanel(MainFrame mf)
	{
		this.m_mainFrame = mf;
		
		this.setBackground(new Color(100,100,100));
		
		generateSelectionPanel();
		generatePropertiesPanel();
		
		this.setLayout(new BorderLayout());
		
		this.add(this.m_selectionPanel, BorderLayout.PAGE_START);
		this.add(this.m_propertiesPanel, BorderLayout.CENTER);
		
		/*CardLayout cl = new CardLayout();
		this.setLayout(cl);
		
		JPanel cardA = new JPanel();
		cardA.setBackground(Color.RED);
		
		JPanel cardB = new JPanel();
		cardB.setBackground(Color.BLUE);
		
		this.add(cardA, "A");
		this.add(cardB, "B");
		
		cl.show(this, "A");*/
	}
	
	private void generateSelectionPanel()
	{
		this.m_selectionPanel = new JPanel();
		this.m_selectionPanel.setBackground(Color.GREEN);
		
		String[] selectionItems = {"Project"};
		
		this.m_selectionPanel.add(new JComboBox(selectionItems));
	}
	
	private void generatePropertiesPanel()
	{
		this.m_propertiesPanel = new ProjectPropertiesPanel(m_mainFrame.m_project);
	}
}
