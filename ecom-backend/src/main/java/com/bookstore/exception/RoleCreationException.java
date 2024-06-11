// RoleCreationException.java
package com.bookstore.exception;

@SuppressWarnings("serial")
public class RoleCreationException extends RuntimeException {
    public RoleCreationException(String message) {
        super(message);
    }
}
