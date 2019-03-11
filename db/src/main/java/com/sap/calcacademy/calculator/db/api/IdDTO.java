package com.sap.calcacademy.calculator.db.api;

import java.net.URI;

public class IdDTO {

	private String id;
	private URI uri;
	private String type;

	public IdDTO() {

	}

	public IdDTO(String id, URI uri, String type) {
		super();
		this.id = id;
		this.uri = uri;
		this.type = type;
	}

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "IdDTO [id=" + id + ", uri=" + uri + ", type=" + type + "]";
	}

}
