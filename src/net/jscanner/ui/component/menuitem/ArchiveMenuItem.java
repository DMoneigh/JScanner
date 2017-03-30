package net.jscanner.ui.component.menuitem;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import net.jscanner.archive.Archive;
import net.jscanner.ui.component.ComponentMenuItem;
import net.jscanner.ui.component.filechooser.ArchiveFileChooser;
import net.jscanner.ui.impl.JScannerUI;
import net.jscanner.ui.impl.SelectThreatsUI;
import net.jscanner.ui.resource.ResourceManager;

/**
 * The "Archive" menu item.
 * 
 * @author Desmond Jackson
 */
public class ArchiveMenuItem extends ComponentMenuItem {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -5308700864416982653L;

	/**
	 * Creates a new "Archive" menu item.
	 */
	public ArchiveMenuItem() {
		super("Archive");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Archive archive = new ArchiveFileChooser(this).getSelectedArchive();
		if (archive != null) {
			final JScannerUI instance = JScannerUI.getInstance(this);
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					new SelectThreatsUI(instance, archive).setVisible(true);
				}
				
			});
		}
	}

	@Override
	public ImageIcon getImageIcon() {
		return ResourceManager.getImage("application.png");
	}

}
