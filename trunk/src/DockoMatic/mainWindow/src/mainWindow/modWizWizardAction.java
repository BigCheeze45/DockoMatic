# Program: "DockoMatic"
# Project: "DNA Safeguard"
# Filename: "modWizWizardAction.java"
#
# Dr. Tim Andersen
# Department of Computer Science
# College of Engineering
# Boise State University
#
# Original Author(s): "Casey Bullock"
#                     "Nic Cornia"
#
# Last Modified
#   Date: "November 14, 2012"
#
#
#  This file is part of DockoMatic.
#
#  DockoMatic is free software: you can redistribute it and/or modify
#  it under the terms of the GNU Lesser General Public License as published by
#  the Free Software Foundation, either version 3 of the License, or
#  (at your option) any later version.
#
#  DockoMatic is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU Lesser General Public License for more details.
#
#  You should have received a copy of the GNU Lesser General Public License
#  along with DockoMatic.  If not, see <http://www.gnu.org/licenses/>.
#

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainWindow;

import java.awt.Component;
import java.awt.Dialog;
import java.text.MessageFormat;
import javax.swing.JComponent;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.actions.CallableSystemAction;

// An example action demonstrating how the wizard could be called from within
// your code. You can copy-paste the code below wherever you need.
public final class modWizWizardAction extends CallableSystemAction {

	//WizardDescriptor wizardDescriptor;
	private WizardDescriptor.Panel<org.openide.WizardDescriptor>[] panels;
	private String from;

	public String doWizard(String fromWhere){
		this.from=fromWhere;
		performAction();
		return getModelPath();
	}

	public void performAction() {
		//WizardDescriptor wizardDescriptor;// = new WizardDescriptor(getPanels());
		//try{
		WizardDescriptor wizardDescriptor = new WizardDescriptor(getPanels());
		//    wizardDescriptor = new WizardDescriptor(getPanels());
		//}catch( Exception e){e.printStackTrace();}
		//wizardDescriptor = new WizardDescriptor(getPanels());
		// {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
		wizardDescriptor.setTitleFormat(new MessageFormat("{0}"));
		wizardDescriptor.setTitle("The Wizard T.I.M. creating : "+from);
		Dialog dialog = DialogDisplayer.getDefault().createDialog(wizardDescriptor);
		dialog.setVisible(true);
		dialog.toFront();
		boolean cancelled = wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;
		if (!cancelled) {
			// do something
		}
	}

	/**
	 * Initialize panels representing individual wizard's steps and sets
	 * various properties for them influencing wizard appearance.
	 */
	@SuppressWarnings("unchecked")
	private WizardDescriptor.Panel<org.openide.WizardDescriptor>[] getPanels() {
		panels = null;
		if (panels == null) {
			panels = new WizardDescriptor.Panel[]{
					new modWizWizardPanel1(),
					new modWizWizardPanel2(),
					new modWizWizardPanel3(),
					new modWizWizardPanel4(),
					new modWizWizardPanel5(from)
				};
			String[] steps = new String[panels.length];
			for (int i = 0; i < panels.length; i++) {
				Component c = panels[i].getComponent();
				// Default step name to component name of panel. Mainly useful
				// for getting the name of the target chooser to appear in the
				// list of steps.
				steps[i] = c.getName();
				if (c instanceof JComponent) { // assume Swing components
					JComponent jc = (JComponent) c;
					// Sets step number of a component
					jc.putClientProperty("WizardPanel_contentSelectedIndex", new Integer(i));
					// Sets steps names for a panel
					jc.putClientProperty("WizardPanel_contentData", steps);
					// Turn on subtitle creation on each step
					jc.putClientProperty("WizardPanel_autoWizardStyle", Boolean.TRUE);
					// Show steps on the left side with the image on the background
					jc.putClientProperty("WizardPanel_contentDisplayed", Boolean.TRUE);
					// Turn on numbering of all steps
					jc.putClientProperty("WizardPanel_contentNumbered", Boolean.TRUE);
				}
			}
		}
		return panels;
	}

	public String getModelPath(){
		Component c = panels[4].getComponent();
		JComponent jc = (JComponent) c;
		String path = (String) jc.getClientProperty("MODPATH");
		return path;
	}

	public String getName() {
		return "Start TIM";
	}

	@Override
	public String iconResource() {
		return null;
	}

	public HelpCtx getHelpCtx() {
		return HelpCtx.DEFAULT_HELP;
	}

	@Override
	protected boolean asynchronous() {
		return false;
	}
}
