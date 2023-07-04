package br.com.fiap.postech.monitoraconsumo.service.exception;

public class ControllerNotFoundException extends RuntimeException {
    public ControllerNotFoundException(String message){
        super(message);
    }
}
