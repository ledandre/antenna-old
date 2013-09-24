package br.com.ledtom.antenna.domain.structs;

public class NotifyResponse {
	private final int code;
	
	public NotifyResponse(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
