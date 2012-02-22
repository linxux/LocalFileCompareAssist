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

import org.eclipse.core.runtime.IPath;

public class SelectionElement {
	private Object element;
	private String prjName;
	private IPath resourceLocation;
	
	public SelectionElement(Object element, String prjName, IPath resourceLocation) {
		this.element = element;
		this.prjName = prjName;
		this.resourceLocation= resourceLocation;
	}
	public boolean isNull() {
		return (null == this.element
				|| null == this.prjName
				|| null == this.resourceLocation); 
	}
	public Object getElement() {
		return element;
	}
	public void setElement(Object element) {
		this.element = element;
	}
	public String getPrjName() {
		return prjName;
	}
	public void setPrjName(String prjName) {
		this.prjName = prjName;
	}
	public IPath getResourceLocation() {
		return resourceLocation;
	}
	public void setResourceLocation(IPath resourceLocation) {
		this.resourceLocation = resourceLocation;
	}
}
