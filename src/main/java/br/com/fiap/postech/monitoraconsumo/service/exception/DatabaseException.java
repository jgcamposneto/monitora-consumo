package br.com.fiap.postech.monitoraconsumo.service.exception;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String msg) {
        super(msg);
    }
}
