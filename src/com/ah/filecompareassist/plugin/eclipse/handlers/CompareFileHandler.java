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
package com.ah.filecompareassist.plugin.eclipse.handlers;

import java.io.File;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareUI;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.ah.filecompareassist.plugin.eclipse.compare.ResourceCompareInput2;
import com.ah.filecompareassist.plugin.eclipse.util.CommandUtil;
import com.ah.filecompareassist.plugin.eclipse.util.DebugUtil;
import com.ah.filecompareassist.plugin.eclipse.util.SelectionElement;

/**
 * This Handler will invoke the CompareEditor to compare files in workspace.
 * 
 * @author Jason.Y.Lin (linxux.hz@gmail.com)
 * 
 */
public class CompareFileHandler extends AbstractHandler {

	private final String CONFIRM_SAVE_PROPERTY = "org.eclipse.compare.internal.CONFIRM_SAVE_PROPERTY";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			DebugUtil.debug("--------- Trigger(Start) ----------");
			IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

			// get the project which need to compare with by current selected
			// menu
			String comparedPrjName = getComparePrjName(event);

			ISelection selection = HandlerUtil.getActiveMenuSelection(event);
			DebugUtil.debug("Selection class: " + selection.getClass());

			// get the selection information
			// only handle the StructuredSelection or the TextSelection
			SelectionElement selectoionEl = CommandUtil.getSelectedElementInfo(window, selection);

			if (null == selectoionEl || selectoionEl.isNull()) {
				MessageDialog.openWarning(window.getShell(), "Locale Compare Warning",
						"Unknown error when compare with the project - " + comparedPrjName);
				return null;
			}

			// open compare editor
			openCompareEditor(window, comparedPrjName, selectoionEl);
		} finally {
			DebugUtil.debug("--------- Trigger(End) ----------");
		}
		return null;
	}

	private void openCompareEditor(IWorkbenchWindow window, String comparedPrjName,
			SelectionElement selectoionEl) {
		IPath workspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocation();
		DebugUtil.debug("Append prj name to Root: " + workspacePath.append(selectoionEl.getPrjName()).toString());

		if (!(null == comparedPrjName || comparedPrjName.trim().isEmpty())) {
			IPath comparedPrjPath = workspacePath.append(comparedPrjName);
			if (comparedPrjPath.toFile().exists()) {
				// get the compared file location string
				String nfileLocationStr = selectoionEl.getResourceLocation().toString().replace(
						workspacePath.append(selectoionEl.getPrjName()).toString(),
						comparedPrjPath.toString());
				DebugUtil.debug("find the " + nfileLocationStr + " need to compare with.");
				if (new File(nfileLocationStr).exists()) {
					// mock the second resource according the selected sub menu
					IFile secondElement = ResourcesPlugin
							.getWorkspace()
							.getRoot()
							.getFile(
									new Path(nfileLocationStr.replace(
											workspacePath.toString(), "")));

					// construct new selection with two element
					IStructuredSelection newSelection = new StructuredSelection(
							new Object[] { selectoionEl.getElement(), secondElement });
					
					CompareConfiguration cc = new CompareConfiguration();
					// buffered merge mode: don't ask for confirmation when switching between modified resources
					cc.setProperty(CONFIRM_SAVE_PROPERTY, new Boolean(false));
					ResourceCompareInput2 fInput = new ResourceCompareInput2(cc);
					IWorkbenchPage fWorkbenchPage = window.getActivePage();
					boolean ok = fInput.setSelection(newSelection, fWorkbenchPage
							.getWorkbenchWindow().getShell(), true);
					if (ok) {
						fInput.initializeCompareConfiguration();
						CompareUI.openCompareEditorOnPage(fInput, fWorkbenchPage);
					}

				} else {
					MessageDialog.openWarning(window.getShell(), "Locale Compare Warning",
							"The file - " + nfileLocationStr + " doesn't exists.");
				}
			} else {
				MessageDialog.openWarning(window.getShell(), "Locale Compare Warning",
						"The project - " + comparedPrjName + " doesn't exists.");
			}
		}
	}

	private String getComparePrjName(ExecutionEvent event) {
		String comparedPrjName = null;
		final Object trigger = event.getTrigger();
		if (trigger instanceof Event) {
			Event uiEvent = (Event) trigger;
			DebugUtil.debug("Event's widget display attribute: " + uiEvent.widget);
			if (uiEvent.widget instanceof MenuItem) {
				MenuItem menuItem = (MenuItem) uiEvent.widget;
				comparedPrjName = menuItem.getText();
				DebugUtil.debug("menu Text: " + comparedPrjName);
			}
		}
		return comparedPrjName;
	}

}
