package com.epam.handle;

public class Handler {

	private String method;
	private String uri;
	private IHandle iHandle;

	public Handler(String method, String uri, IHandle iHandler) {
		this.method = method;
		this.uri = uri;
		this.iHandle = iHandler;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public IHandle getiHandle() {
		return iHandle;
	}

	public void setiHandle(IHandle iHandle) {
		this.iHandle = iHandle;
	}

}
