package net.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.jscanner.JScanner;
import net.jscanner.archive.Archive;
import net.jscanner.gui.component.ComponentMenuItem;
import net.jscanner.gui.impl.GuiThreatSelection;
import net.jscanner.util.ExecutionSecurityManager;

/**
 * The "Application" menu item.
 * 
 * @author Desmond Jackson
 */
public class MenuItemApplication extends ComponentMenuItem {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -6053904770113424865L;

	/**
	 * Creates the "Application" menu item.
	 */
	public MenuItemApplication() {
		super("Application");
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
			FileChooserArchive fileChooserArchive = new FileChooserArchive(this);
			fileChooserArchive.setFileFilter(new FileNameExtensionFilter(
					"Jar Files", new String[] {"jar"}));
			Archive archive = fileChooserArchive.getSelectedArchive();
			if (archive != null) {
				try {
					JarFile jarFile = new JarFile(archive.getName());
					String mainClass = jarFile.getManifest().getMainAttributes()
							.getValue(Attributes.Name.MAIN_CLASS);
					jarFile.close();
					URLClassLoader loader = new URLClassLoader(new URL[] {
							new URL("jar", "",
									"file:" + archive.getName() + "!/")
					});
					Class<?> clazz = loader.loadClass(mainClass);
					Method method = clazz.getDeclaredMethod("main",
							String[].class);
					System.setSecurityManager(new ExecutionSecurityManager(
							JScanner.getInstance(this)));
					method.invoke(null, (Object) null);
					loader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
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
		}
	}

}
