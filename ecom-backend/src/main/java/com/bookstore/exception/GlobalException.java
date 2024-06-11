package com.bookstore.exception;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
 
@RestControllerAdvice
public class GlobalException  {
 
	/**
	 * @param ex
	 * @return return the error message if car not found
	 */
	 @ExceptionHandler(RegistrationException.class)
	    public ResponseEntity<String> handleCarNotFoundException(RegistrationException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	    }

 
		/**
		 * This method is used to handle UserNotFoundException.
		 */
		@ExceptionHandler(UserNotFoundException.class)
		public ResponseEntity<String> handlerUserNotFoundException(UserNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
 
		/**
		 * This method is used to handle BookingNotFoundException.
		 */
		@ExceptionHandler(ProductNotFoundException.class)
		public ResponseEntity<String> handlerBookingNotFoundException(ProductNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		 @ExceptionHandler(RoleCreationException.class)
		    public ResponseEntity<String> handlerUserAlreadyExistsException(RoleCreationException ex) {
		        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		    }
		 /**
			 * 
			 * @param ex
			 * @return return the error message if user already exists
			 */
		 
		 @ExceptionHandler(ProductAdditionException.class)
		    public ResponseEntity<String> handlerUserAlreadyExistsException(ProductAdditionException ex) {
		        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		    }
		 
		 @ExceptionHandler(InvalidCredentialsException.class)
		    public ResponseEntity<String> handlerUserAlreadyExistsException(InvalidCredentialsException ex) {
		        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		    }
 
}
