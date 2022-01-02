package fotoshop.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import fotoshop.filters.BlurFilterDialog;
import fotoshop.filters.NoiseFilterDialog;

public class FilterMenu extends JMenu
{
	private static final long serialVersionUID = 1L;
	
	public FilterMenu()
	{
		super("Filter");
		
		// New Blur Filter
		JMenuItem newBlurFilter = new JMenuItem("Add Blur Filter");
		newBlurFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {onBlurFilter();}
		});

		this.add(newBlurFilter);
		
		// New Noise Filter
		JMenuItem newNoiseFilter = new JMenuItem("Add Noise Filter");
		newNoiseFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {onNoiseFilter();}
		});

		this.add(newNoiseFilter);
	}
	
	private void onBlurFilter()
	{
		@SuppressWarnings("unused")
		BlurFilterDialog bfd = new BlurFilterDialog();
	}

	private void onNoiseFilter()
	{
		@SuppressWarnings("unused")
		NoiseFilterDialog nfd = new NoiseFilterDialog();
	}
}
