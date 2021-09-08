package com.mx.profuturo.bolsa.util.exception.custom;

public class InterviewException extends GenericStatusException{

	public InterviewException(Exception e) {
		super(e);
	}

	public InterviewException(BackEndExceptionBean eb) {
		super(new Exception(buildBackEndMessage(eb)));
	}
	
	
	private static String buildBackEndMessage(BackEndExceptionBean eb) {
		String message = eb.getRootErrorMessage();
		
		if(null != eb.getErrorList()) {
			StringBuilder sb = new StringBuilder(message);
			for(String error : eb.getErrorList()) {
				sb.append("<br/> - ").append(error);
			}
			message = sb.toString();
		}
		
		return message;
	}
}
