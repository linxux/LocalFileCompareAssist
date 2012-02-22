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

import org.eclipse.core.runtime.Platform;

import com.ah.filecompareassist.plugin.eclipse.CompareFileActivator;

public class DebugUtil {

	private static final boolean DEBUG = "true".equalsIgnoreCase(Platform
			.getDebugOption(CompareFileActivator.PLUGIN_ID+"/debug"));
	
	public static void debug(String debugMsg) {
		if(DEBUG) {
			System.out.println(debugMsg);
		}
	}
}
