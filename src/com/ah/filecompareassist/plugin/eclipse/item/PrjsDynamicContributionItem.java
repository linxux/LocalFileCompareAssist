/*******************************************************************************
 * Copyright (c) Jason.Y.Lin - linxux.hz@gmail.com
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jason.Y.Lin (linxux.hz@gmail.com) - initial API and implementation
 *******************************************************************************/
package com.ah.filecompareassist.plugin.eclipse.item;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;

import com.ah.filecompareassist.plugin.eclipse.util.CommandUtil;
import com.ah.filecompareassist.plugin.eclipse.util.DebugUtil;
import com.ah.filecompareassist.plugin.eclipse.util.SelectionElement;

/**
 * Create dynamic menu items according current workspace.
 * 
 * @author Jason.Y.Lin (linxux.hz@gmail.com)
 *
 */
public class PrjsDynamicContributionItem extends CompoundContributionItem {

	private static final String COMPAREFILE_COMMAND_ID = "com.ah.filecompareassit.plugin.eclipse.CompareFileCommand";

	@Override
	protected IContributionItem[] getContributionItems() {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		
		ISelection selection = window.getSelectionService().getSelection();
		DebugUtil.debug("activeWorkbenchWindow.getSelectionService: "+selection);
		DebugUtil.debug("activeWorkbenchWindow.getSelectionService class: "+selection.getClass());
		
		SelectionElement selectedEl = CommandUtil.getSelectedElementInfo(window, selection);
		if(null == selectedEl || null == selectedEl.getPrjName()) {
			return new IContributionItem[0];
		}
		String currentPrjName = selectedEl.getPrjName();
		
		// initial the dynamic menu parameter
		CommandContributionItemParameter parameter = new CommandContributionItemParameter(
				window, null,
				COMPAREFILE_COMMAND_ID,
				CommandContributionItem.STYLE_PUSH);
		
		List<IContributionItem> items = initMenuItems(currentPrjName, parameter);
		
		return items.toArray(new IContributionItem[items.size()]);
	}

	private List<IContributionItem> initMenuItems(String currentPrjName,
			CommandContributionItemParameter parameter) {
		
		List<IContributionItem> items = new LinkedList<IContributionItem>();
		
		// get the projects in Workspace
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject[] projects = root.getProjects();
		
		DebugUtil.debug("Root location: "+root.getLocation());
		int priorityIndex = 0;
		for (IProject project : projects) {
			DebugUtil.debug(">>project: " + project + " accessible:" + project.isAccessible()
					+ " hidden:" + project.isHidden());
			if(project.isOpen()) {
				String prjName = project.getName();
				if(!prjName.equals(currentPrjName)) {
					parameter.label = prjName;
					if(isSimilarText(currentPrjName, prjName)) {
						items.add(priorityIndex++, new CommandContributionItem(parameter));
					} else {
						items.add(new CommandContributionItem(parameter));
					}
					DebugUtil.debug(">>>Location: "+project.getLocation().toString());
				}
			}
		}
		return items;
	}

	private boolean isSimilarText(String text1, String text2) {
		String text1lc, text2lc;
		if(text1.length() > text2.length()) {
			text1lc = text1.toLowerCase();
			text2lc = text2.toLowerCase();
		} else {
			text1lc = text2.toLowerCase();
			text2lc = text1.toLowerCase();
		}
		if(text1lc.startsWith(text2lc)) {
			return true;
		} else {
			String text1half = text1lc.substring(0, text1lc.length()/2);
			String text2half = text2lc.substring(0, text2lc.length()/2);
			if(text1half.startsWith(text2half)) {
				return true;
			}
		}
		return false;
	}

}
