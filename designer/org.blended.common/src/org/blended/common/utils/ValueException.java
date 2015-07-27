package org.blended.common.utils;

public class ValueException extends Exception {
	private static final long serialVersionUID = 1L;
	public ValueException() { super(); }
	public ValueException(String message) { super(message); }
	public ValueException(String message, Throwable cause) { super(message, cause); }
	public ValueException(Throwable cause) { super(cause); }
}