package com.liberymutual.goforcode.wimp.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such actor. Look elsewhere. You're a smartiepants!")  // 404
public class ActorNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

}
