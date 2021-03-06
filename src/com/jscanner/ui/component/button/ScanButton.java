package com.jscanner.ui.component.button;

import java.awt.event.ActionEvent;

import com.jscanner.archive.ArchiveScanner;
import com.jscanner.ui.component.ComponentButton;
import com.jscanner.ui.impl.JScannerUI;
import com.jscanner.ui.impl.SelectThreatsUI;

/**
 * The "Scan" button.
 * 
 * @author Desmond Jackson
 */
public class ScanButton extends ComponentButton {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 2616320470283675465L;

	/**
	 * Creates a new "Scan" button.
	 */
	public ScanButton() {
		super("Scan");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SelectThreatsUI threatsUI = SelectThreatsUI.getInstance(this);
		JScannerUI jscannerUI = threatsUI.getParent();
		jscannerUI.getLogger().log(new ArchiveScanner(threatsUI.getArchive()).scan(threatsUI.getThreatTree().getSelectThreats()));
		threatsUI.dispose();
		jscannerUI.setVisible(true);
	}

}
