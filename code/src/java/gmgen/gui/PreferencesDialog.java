/*
 *  Initiative - A role playing utility to track turns
 *  Copyright (C) 2002 Devon D Jones
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 *  The author of this program grants you the ability to use this code
 *  in conjunction with code that is covered under the Open Gaming License
 *
 *  PreferencesDialog.java
 *
 *  Created on August 29, 2002, 2:17 PM
 */
package gmgen.gui;

import pcgen.core.SettingsHandler;
import pcgen.gui.panes.FlippingSplitPane;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *@author     devon
 *@since    April 7, 2003
 */
public class PreferencesDialog extends javax.swing.JDialog
{
	private javax.swing.JButton bApply;
	private javax.swing.JButton bCancel;
	private javax.swing.JButton bOk;
	private javax.swing.JPanel jPanel2;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private FlippingSplitPane jSplitPane1;
	private javax.swing.JTabbedPane prefsPane;
	private javax.swing.JTree prefsTree;
	private PreferencesRootTreeNode root;

	/**
	 *  Creates new form PreferencesDialog
	 *
	 *@param  parent      Description of the Parameter
	 *@param  modal       Description of the Parameter
	 * @param root
	 */
	public PreferencesDialog(java.awt.Frame parent, boolean modal, PreferencesRootTreeNode root)
	{
		super(parent, modal);
		this.root = root;
		initComponents();
		initLast();
		initPreferences();
	}

	/**
	 * Apply the new preferences
	 */
	public void applyPreferences()
	{
		for (PreferencesPanel panel : root.getPanelList())
		{
			panel.applyPreferences();
		}
	}

	//GEN-LAST:event_bCancelActionPerformed
	private void prefsTreeActionPerformed()
	{
		// Add your handling code here:
		Object obj = prefsTree.getLastSelectedPathComponent();

		if (obj instanceof DefaultMutableTreeNode)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) obj;
			Object uobj = node.getUserObject();

			if (uobj instanceof PreferencesPanel)
			{
				prefsPane.removeAll();
				prefsPane.addTab(obj.toString(), (PreferencesPanel) uobj);
			}
		}
	}

	private void bApplyActionPerformed()
	{
		//GEN-FIRST:event_bApplyActionPerformed
		// Add your handling code here:
		applyPreferences();
	}

	//GEN-LAST:event_bOkActionPerformed
	private void bCancelActionPerformed()
	{
		//GEN-FIRST:event_bCancelActionPerformed
		// Add your handling code here:
		setVisible(false);
		dispose();
	}

	//GEN-LAST:event_bApplyActionPerformed
	private void bOkActionPerformed()
	{
		//GEN-FIRST:event_bOkActionPerformed
		// Add your handling code here:
		applyPreferences();
		setVisible(false);
		dispose();
	}

	/**
	 *  Closes the dialog
	 *
	 */
	private void closeDialog()
	{
		//GEN-FIRST:event_closeDialog
		SettingsHandler.setGMGenOption("PreferencesDialog.PrefsDividerLocation", jSplitPane1.getDividerLocation());

		SettingsHandler.setGMGenOption("PreferencesDialog.PrefsWindowX", this.getX());
		SettingsHandler.setGMGenOption("PreferencesDialog.PrefsWindowY", this.getY());
		SettingsHandler.setGMGenOption("PreferencesDialog.PrefsWindowWidth", this.getSize().width);
		SettingsHandler.setGMGenOption("PreferencesDialog.PrefsWindowHeight", this.getSize().height);
		setVisible(false);
		dispose();
	}

	/**
	 *  This method is called from within the constructor to initialize the form.
	 *  WARNING: Do NOT modify this code. The content of this method is always
	 *  regenerated by the Form Editor.
	 */
	private void initComponents()
	{ //GEN-BEGIN:initComponents
		jPanel2 = new javax.swing.JPanel();
		bOk = new javax.swing.JButton();
		bCancel = new javax.swing.JButton();
		bApply = new javax.swing.JButton();
		jSplitPane1 = new FlippingSplitPane();
		prefsTree = new javax.swing.JTree(root);
		prefsPane = new javax.swing.JTabbedPane();

		addWindowListener(new java.awt.event.WindowAdapter()
			{
				public void windowClosing(java.awt.event.WindowEvent evt)
				{
					closeDialog();
				}
			});

		jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.X_AXIS));

		bOk.setText("Ok");
		bOk.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent evt)
				{
					bOkActionPerformed();
				}
			});

		jPanel2.add(bOk);

		bCancel.setText("Cancel");
		bCancel.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent evt)
				{
					bCancelActionPerformed();
				}
			});

		jPanel2.add(bCancel);
		bCancel.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent evt)
				{
					bCancelActionPerformed();
				}
			});

		bApply.setText("Apply");
		bApply.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent evt)
				{
					bApplyActionPerformed();
				}
			});

		jPanel2.add(bApply);

		getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

		jSplitPane1.setLeftComponent(prefsTree);
		prefsTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener()
			{
				public void valueChanged(javax.swing.event.TreeSelectionEvent evt)
				{
					prefsTreeActionPerformed();
				}
			});

		jSplitPane1.setRightComponent(prefsPane);

		getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);
	}
	 //GEN-END:initComponents

	//GEN-LAST:event_closeDialog

	/** Moves and resizes the preferences dialog based on your last opening of it */
	private void initLast()
	{
		int iDividerLocation = SettingsHandler.getGMGenOption("PreferencesDialog.PrefsDividerLocation", 118);
		jSplitPane1.setDividerLocation(iDividerLocation);

		int iWinX = SettingsHandler.getGMGenOption("PreferencesDialog.PrefsWindowX", 0);
		int iWinY = SettingsHandler.getGMGenOption("PreferencesDialog.PrefsWindowY", 0);
		this.setLocation(iWinX, iWinY);

		int iWinWidth = SettingsHandler.getGMGenOption("PreferencesDialog.PrefsWindowWidth", 550);
		int iWinHeight = SettingsHandler.getGMGenOption("PreferencesDialog.PrefsWindowHeight", 385);
		this.setSize(iWinWidth, iWinHeight);
	}

	/** Sets all the widgets to refelct the current preferences */
	private void initPreferences()
	{
		for (PreferencesPanel panel : root.getPanelList())
		{
			panel.initPreferences();
		}
	}

	// End of variables declaration//GEN-END:variables
}
