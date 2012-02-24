/*******************************************************************************
 * Copyright (c) Jason.Y.Lin - linxux.hz@gmail.com
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jason.Y.Lin (linxux.hz@gmail.com) - initial API and implementation, reference from org.eclipse.team.internal.ui.actions.CompareAction.
 *******************************************************************************/
package com.ah.filecompareassist.plugin.eclipse.compare;

import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.CompareUI;
import org.eclipse.compare.ITypedElement;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.team.internal.ui.Utils;
import org.eclipse.team.internal.ui.history.CompareFileRevisionEditorInput;
import org.eclipse.team.internal.ui.synchronize.SaveablesCompareEditorInput;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IReusableEditor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

public class CompareAction {
	private IWorkbenchWindow window;
	private IResource[] selectedFiles;
	
	public CompareAction(IWorkbenchWindow window, IResource[] selectedFiles) {
		this.window = window;
		this.selectedFiles = selectedFiles;
	}
	
	public void execute() {

		ITypedElement ancestor = null;
		ITypedElement left = null;
		ITypedElement right = null;

		if (selectedFiles.length == 2) {
			if (selectedFiles[0] != null)
				left = getElementFor(selectedFiles[0]);

			if (selectedFiles[1] != null)
				right = getElementFor(selectedFiles[1]);

		} else {
			return;
		}
		openInCompare(ancestor, left, right);
	}

	private void openInCompare(ITypedElement ancestor, ITypedElement left, ITypedElement right) {
		IWorkbenchPage workBenchPage = window.getActivePage();
		CompareEditorInput input = new SaveablesCompareEditorInput(ancestor, left, right,
				workBenchPage);
		IEditorPart editor = Utils.findReusableCompareEditor(input, workBenchPage,
				new Class[] { CompareFileRevisionEditorInput.class });
		if (editor != null) {
			IEditorInput otherInput = editor.getEditorInput();
			if (otherInput.equals(input)) {
				// simply provide focus to editor
				workBenchPage.activate(editor);
			} else {
				// if editor is currently not open on that input either re-use
				// existing
				CompareUI.reuseCompareEditor(input, (IReusableEditor) editor);
				workBenchPage.activate(editor);
			}
		} else {
			CompareUI.openCompareEditor(input);
		}
	}

	private ITypedElement getElementFor(IResource resource) {
		return SaveablesCompareEditorInput.createFileElement((IFile) resource);
	}

}
