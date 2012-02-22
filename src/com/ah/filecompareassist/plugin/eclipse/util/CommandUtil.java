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
package com.ah.filecompareassist.plugin.eclipse.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;

public class CommandUtil {
	
	public static SelectionElement getSelectedElementInfo(IWorkbenchWindow window, ISelection selection) {
		SelectionElement selectoionEl = null;
		Object firstElement;
		String cPrjName = null;
		IPath cfileLocation = null;
		if (selection instanceof IStructuredSelection) {
			firstElement = ((IStructuredSelection) selection).getFirstElement();
			if (firstElement instanceof ICompilationUnit) {
				ICompilationUnit cu = (ICompilationUnit) firstElement;
				cPrjName = cu.getJavaProject().getElementName();
				cfileLocation = cu.getResource().getLocation();
				
				selectoionEl = new SelectionElement(firstElement, cPrjName, cfileLocation);

				DebugUtil.debug(">>Current Project path: " + cu.getJavaProject().getPath().toString());
				DebugUtil.debug(">>Current Project element name: " + cPrjName);
				DebugUtil.debug(">>Current Resource: " + cu.getResource().getClass());
				DebugUtil.debug(">>Current Resource location: " + cfileLocation.toString());
			} else if (firstElement instanceof IFile) {
				IFile file = (IFile) firstElement;
				cPrjName = file.getProject().getName();
				cfileLocation = file.getLocation();
				
				selectoionEl = new SelectionElement(firstElement, cPrjName, cfileLocation);
				
				DebugUtil.debug(">>Current Non JAVA file: "+file);
				DebugUtil.debug(">>Current Project element name: "+file.getProject().getName());
				DebugUtil.debug(">>Current File location: "+file.getLocation().toString());
			}
		} else if (selection instanceof TextSelection){
			IEditorPart editor = window.getActivePage().getActiveEditor();
			firstElement = editor.getEditorInput().getAdapter(IFile.class);
			cPrjName = ((IFile) firstElement).getProject().getName();
			cfileLocation = ((IFile) firstElement).getLocation();
			
			selectoionEl = new SelectionElement(firstElement, cPrjName, cfileLocation);

			DebugUtil.debug(">>Current editor = "+editor);
			DebugUtil.debug(">>Current editor title = "+editor.getTitle());
			DebugUtil.debug(">>Current editor title tooltip= "+editor.getTitleToolTip());
			DebugUtil.debug(">>Current editor project name= "+selectoionEl.getPrjName());
			DebugUtil.debug(">>Current editor file location= "+selectoionEl.getResourceLocation().toString());
		}
		return selectoionEl;
	}
}
