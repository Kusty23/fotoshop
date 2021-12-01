package fotoshop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import app.MainFrame;

public class NewProjectDialog extends JDialog
{
	private static final long serialVersionUID = 1L;
	
	JTextField m_projectName;
	
	public NewProjectDialog(MainFrame mf)
	{
		this.setLocation(mf.getWidth() / 2, mf.getHeight() / 2);
		this.setSize(500, 200);
		this.setLayout(new FlowLayout());
		
		JLabel projectNameLabel = new JLabel("New Project Name:");
		this.add(projectNameLabel);
		
		m_projectName = new JTextField("");
		m_projectName.setMinimumSize(new Dimension(100, 20));
		this.add(m_projectName);
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {onOK();}
		});
		this.add(ok);
		
		this.setVisible(true);
	}
	
	private void onOK()
	{
		
	}
}
