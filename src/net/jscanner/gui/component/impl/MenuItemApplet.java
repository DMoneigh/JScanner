package net.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JOptionPane;

import net.jscanner.JScanner;
import net.jscanner.gui.component.ComponentMenuItem;
import net.jscanner.gui.impl.GuiThreatSelection;
import net.jscanner.util.ExecutionSecurityManager;

/**
 * The "Applet" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemApplet extends ComponentMenuItem {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 2057818350158250361L;

	/**
	 * Creates the "Applet" menu item.
	 */
	public MenuItemApplet() {
		super("Applet");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (getParentMenu() instanceof MenuScan) {
			new GuiThreatSelection(JScanner.getInstance(this),
					this).setVisible(true);
		} else {
			if (JOptionPane.showConfirmDialog(this,
					"It is recommended to run unknown programs in a "
							+ "Virtual Machine.") != 0)
				return;
			final MenuItemApplet mia = this;
			new Thread(new Runnable() {

				@Override
				public void run() {
					String url = JOptionPane.showInputDialog("Enter URL below.");
					if (url == null || url.isEmpty()) {
						JScanner.getInstance(mia).print("URL was not specified");
						return;
					}
					ClassLoader classLoader = ClassLoader.getSystemClassLoader();
					try {
						Class<?> clazz = classLoader.loadClass("sun.applet.AppletViewer");
						Method method = clazz.getDeclaredMethod("main", String[].class);
						String[] params = new String[] {url};
						System.setSecurityManager(new ExecutionSecurityManager(
								JScanner.getInstance(mia)));
						method.invoke(null, (Object) params);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (NoSuchMethodException e1) {
						e1.printStackTrace();
					} catch (SecurityException e1) {
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						e1.printStackTrace();
					} catch (IllegalArgumentException e1) {
						e1.printStackTrace();
					} catch (InvocationTargetException e1) {
						e1.printStackTrace();
					}
				}
			}).start();
		}
	}

}
