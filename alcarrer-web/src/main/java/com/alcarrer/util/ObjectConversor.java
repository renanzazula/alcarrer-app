package com.alcarrer.util;

import java.beans.PropertyEditorSupport;
import java.io.StringReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class ObjectConversor extends PropertyEditorSupport {

	private Class<?> type;

	public ObjectConversor(Class<?> type) {
		this.type = type;
	}

	@Override
	public String getAsText() {
		Object value = getValue();
		if (value == null) {
			return "";
		}
		Gson gson = new Gson();
 		return gson.toJson(value);
	}

	@Override
	public void setAsText(String text) {
		if (text == null || text.equals("") || text.equals("NONE")) {
			setValue(null);
		} else {
			Gson gson = new Gson();
			JsonReader reader = new JsonReader(new StringReader(text));
			reader.setLenient(true);
			setValue(gson.fromJson(reader, type));
		}
	}

}