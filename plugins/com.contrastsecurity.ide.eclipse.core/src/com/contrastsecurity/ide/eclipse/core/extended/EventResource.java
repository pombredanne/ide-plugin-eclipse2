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
package com.contrastsecurity.ide.eclipse.core.extended;

import java.util.ArrayList;
import java.util.List;

public class EventResource {
	public static final String RED = "RED";
	public static final String CONTENT = "CONTENT";
	public static final String CODE = "CODE";
	public static final String BOLD = "BOLD";
	private int id;
	private boolean important;
	private String type;
	private String typeDescription;
	private String codeRecreation;
	private String rawCodeRecreation;
	private String probableStartLocation;
	private String htmlDataSnapshot;
	private Event event;
	private EventItem[] items;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}

	public boolean getImportant() {
		return this.important;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	public String getTypeDescription() {
		return this.typeDescription;
	}

	public void setCodeRecreation(String codeRecreation) {
		this.codeRecreation = codeRecreation;
	}

	public String getCodeRecreation() {
		return this.codeRecreation;
	}

	public void setRawCodeRecreation(String rawCodeRecreation) {
		this.rawCodeRecreation = rawCodeRecreation;
	}

	public String getRawCodeRecreation() {
		return this.rawCodeRecreation;
	}

	public void setProbableStartLocation(String probableStartLocation) {
		this.probableStartLocation = probableStartLocation;
	}

	public String getProbableStartLocation() {
		return this.probableStartLocation;
	}

	public void setHtmlDataSnapshot(String htmlDataSnapshot) {
		this.htmlDataSnapshot = htmlDataSnapshot;
	}

	public String getHtmlDataSnapshot() {
		return this.htmlDataSnapshot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventResource other = (EventResource) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public EventItem[] getItems() {
		if (items == null) {
			if (event != null) {
				List<EventItem> eventItems = new ArrayList<>();
				//EventItem eventItem = new EventItem(this, "", "at " + probableStartLocation, false);
				//eventItems.add(eventItem);
				EventItem eventItem = new EventItem(this, BOLD, "Class.Method", false);
				eventItems.add(eventItem);
				eventItem = new EventItem(this, CONTENT, event.getClazz() + '.' + event.getMethod(), false);
				eventItems.add(eventItem);
				eventItem = new EventItem(this, BOLD, "Object", false);
				eventItems.add(eventItem);
				eventItem = new EventItem(this, CONTENT, event.getfObject(), false);
				eventItems.add(eventItem);
				eventItem = new EventItem(this, BOLD, "Return", false);
				eventItems.add(eventItem);
				eventItem = new EventItem(this, CONTENT, event.getfReturn(), false);
				eventItems.add(eventItem);
				eventItem = new EventItem(this, BOLD, "Parameters", false);
				eventItems.add(eventItem);
				if (event.getParameters() != null) {
				for (Parameter paremeter:event.getParameters())
					if (paremeter.getParameter() != null) {
						eventItem = new EventItem(this, CONTENT, paremeter.getParameter(), false);
						eventItems.add(eventItem);
					}
				}
				eventItem = new EventItem(this, BOLD, "Stack Trace", false);
				eventItems.add(eventItem);
				if (event.getStacktraces() != null) {
					boolean first = true;
					for (Stacktrace stacktrace : event.getStacktraces()) {
						if (first) {
							eventItem = new EventItem(this, RED, stacktrace.getDescription(), true);
							eventItems.add(eventItem);
							first = false;
						} else {
							eventItem = new EventItem(this, CODE, stacktrace.getDescription(), true);
							eventItems.add(eventItem);
							first = false;
						}
					}
				}
				items = eventItems.toArray(new EventItem[0]);
			}
		}
		return items;
	}

}
