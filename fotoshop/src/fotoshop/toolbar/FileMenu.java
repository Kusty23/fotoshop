package fotoshop.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import fotoshop.project.NewProjectDialog;

public class FileMenu extends JMenu
{
	private static final long serialVersionUID = 1L;

	public FileMenu()
	{
		super("File");
		
		// New Project
		JMenuItem newProject = new JMenuItem("New Project");

		newProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {onNewProject();}
		});

		this.add(newProject);
		
		// Quit
	}

	private void onNewProject()
	{
		@SuppressWarnings("unused")
		NewProjectDialog test = new NewProjectDialog();
	}
}
