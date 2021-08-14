package ie.cct.gtp.robs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -65206095946594411L;

	public NoContentException(String message) {
		super(message);
	}
}
