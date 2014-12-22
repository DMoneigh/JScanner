package net.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import net.jscanner.archive.Archive;
import net.jscanner.archive.ArchiveScanner;
import net.jscanner.gui.component.ComponentButton;
import net.jscanner.gui.impl.GuiThreatSelection;
import net.jscanner.threat.impl.ThreatClass;
import net.jscanner.util.WebPage;

/**
 * Creates a new "Accept" button.
 * 
 * @author Desmond Jackson
 */
public class ButtonAccept extends ComponentButton {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 865266489895019039L;

	/**
	 * Creates a new "Accept" button.
	 */
	public ButtonAccept() {
		super("Accept");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GuiThreatSelection guiThreatSelection =
				GuiThreatSelection.getInstance(this);
		List<ThreatClass> threats = guiThreatSelection.getSelectedThreats();
		if (guiThreatSelection.getComponentMenuItem() instanceof MenuItemApplet) {
			String url = JOptionPane.showInputDialog("Enter URL below.");
			if (url != null) {
				List<Archive> archives = new WebPage(url).getArchives();
				if (archives.size() > 0)
					for (Archive archive : archives)
						guiThreatSelection.getJScannerInstance().print(
								new ArchiveScanner(archive, threats)
								.getResults());
				else
					guiThreatSelection.getJScannerInstance().print(
							"Could not find any archives on: " + url);
			}
		} else {
			Archive archive = new FileChooserArchive(this).getSelectedArchive();
			if (archive != null)
				guiThreatSelection.getJScannerInstance().print(
						new ArchiveScanner(archive, threats).getResults());
		}
		guiThreatSelection.dispose();
		System.gc();
	}

}
