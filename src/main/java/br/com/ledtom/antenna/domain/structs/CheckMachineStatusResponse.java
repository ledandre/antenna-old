package br.com.ledtom.antenna.domain.structs;

import br.com.ledtom.antenna.domain.controller.MachineController;
import br.com.ledtom.antenna.model.enums.MachineStatus;

/**
 * This class represents a response of a call to checkStatus method on {@link MachineController} class.
 * @author Leandro Andre
 *
 */
public final class CheckMachineStatusResponse {
	private final MachineStatus status;
	
	public CheckMachineStatusResponse(MachineStatus status) {
		this.status = status;
	}
}