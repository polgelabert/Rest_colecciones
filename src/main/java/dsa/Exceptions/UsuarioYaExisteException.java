package dsa.Exceptions;

public class UsuarioYaExisteException extends Exception{


    public UsuarioYaExisteException() {
        super ("El usuario ya existe, prueba con otro nombre.");
    }


}
