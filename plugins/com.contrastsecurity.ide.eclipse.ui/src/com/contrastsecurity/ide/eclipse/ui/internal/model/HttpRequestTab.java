/*******************************************************************************
 * Copyright (c) 2017 Contrast Security.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under 
 * the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License.
 * 
 * The terms of the GNU GPL version 3 which accompanies this distribution
 * and is available at https://www.gnu.org/licenses/gpl-3.0.en.html
 * 
 * Contributors:
 *     Contrast Security - initial API and implementation
 *******************************************************************************/
package com.contrastsecurity.ide.eclipse.ui.internal.model;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.contrastsecurity.ide.eclipse.core.Constants;
import com.contrastsecurity.ide.eclipse.core.extended.HttpRequestResource;

public class HttpRequestTab extends Composite {

		private Text area;
		private HttpRequestResource httpRequest;

		public HttpRequestTab(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout());
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		setLayoutData(gd);
		Composite control = new Composite(this, SWT.NONE);
		control.setLayout(new GridLayout());
		gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		control.setLayoutData(gd);
		area = new Text(control, SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL);
		gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		area.setLayoutData(gd);
		area.setEditable(false);
	}

	public Text getArea() {
		return area;
	}

	public HttpRequestResource getHttpRequest() {
		return httpRequest;
	}

	public void setHttpRequest(HttpRequestResource httpRequest) {
		this.httpRequest = httpRequest;
		area.setText(Constants.BLANK);
		if (httpRequest != null && httpRequest.getHttpRequest() != null && httpRequest.getHttpRequest().getFormattedText() != null) {
			area.setText(httpRequest.getHttpRequest().getFormattedText().replace(Constants.MUSTACHE_NL, Constants.BLANK));
		} else if (httpRequest != null && httpRequest.getReason() != null) {
			area.setText(httpRequest.getReason());
		}
	}
	
}
