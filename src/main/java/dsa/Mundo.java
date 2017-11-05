package dsa;
import org.apache.log4j.Logger;

import dsa.Exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//public class Mundo implements IMundo{
public class Mundo{


    HashMap<String,Usuario> map = new HashMap<>();



    public boolean crearUsuario(Usuario u) throws UsuarioYaExisteException{

        if(isUser(u.nombre)) throw new UsuarioYaExisteException();          // lanza excepcion si isUser== true (lo contiene)
        map.put(u.nombre, u);
        return true;                                                        // return true ya que operacion ok
    }

    public void añadirObjetoAUsuario (String nombre, Objeto objeto) throws UsuarioNoExisteException {

        Usuario usuario = getUser(nombre);
        usuario.insertarObjeto(objeto);
    }

    public Usuario consultarUsuario (String nombre) throws UsuarioNoExisteException {

        Usuario usuario = getUser(nombre);
        if( usuario == null) throw new UsuarioNoExisteException();
        return usuario;
    }

    /**
     * retorna un objeto asociado a un usuario. En caso que el usuario no exista, se retornará un null,
     * @param nombre
     * @param nombreObjeto
     * @return
     * //@throws lanza una excepcion en caso que ...
     */
    public Objeto consultarObjetoDeUsuario(String nombre, String nombreObjeto) throws UsuarioNoExisteException, UsuarioSinObjetosException, ObjetoNoEncontradoException {
    //public Objeto consultarObjetoDeUsuario(String nombre, String nombreObjeto) throws Exception {

        Usuario usuario = getUser(nombre);
        Objeto objeto = usuario.getObjeto(nombreObjeto);
        return objeto;

    }

    /**
     * @param nombre nombre del usuario
     * @return la lista de objetos de un usuario.
     * @throws ListaObjetosVaciaException
     * @throws UsuarioNoExisteException
     */
    public List<Objeto> consultarObjetosDeUsuario (String nombre) throws UsuarioSinObjetosException, UsuarioNoExisteException {

        Usuario usuario = getUser(nombre);
        List<Objeto> listaObjetos = usuario.getListaObjetos(nombre);
        if (listaObjetos == null || listaObjetos.size() == 0) throw new UsuarioSinObjetosException();
        return listaObjetos;
        //return  map.get(nombre).listaObjetos;
    }

    public boolean eliminarUsuario (String nombre) throws UsuarioNoExisteException {

        Usuario usuario = getUser(nombre);
        removeUser(usuario);

        return true;
    }

    public boolean eliminarObjetosDeUsuario(String nombre) throws UsuarioNoExisteException, UsuarioSinObjetosException {

        Usuario usuario = getUser(nombre);
        removeObject(usuario);
        return true;
    }

    public void transferirObjetoEntreUsuarios (String origen, String destino, String nombreObjeto) throws UsuarioNoExisteException, UsuarioSinObjetosException, ObjetoNoEncontradoException {
    //public void transferirObjetoEntreUsuarios (String origen, String destino, String nombreObjeto) throws Exception, UsuarioNoExisteException, UsuarioSinObjetosException {

        Objeto objeto = consultarObjetoDeUsuario(origen, nombreObjeto);
        Usuario dest = getUser(destino);
        dest.listaObjetos.add(objeto);
        Usuario orig = getUser(origen);
        orig.listaObjetos.remove(objeto);
    }





    public List<Usuario> consultarListaUsuarios() throws ListaUsuariosVaciaException {

        List<Usuario> listaUsers = new ArrayList<Usuario>();
        if (!listaUsers.addAll(map.values())) throw new ListaUsuariosVaciaException();
        return listaUsers;
    }

    private Usuario getUser(String nombre) throws UsuarioNoExisteException {

        if (!map.containsKey(nombre)) throw new UsuarioNoExisteException();
        return map.get(nombre);
    }

    private boolean isUser (String nombre) { return (map.containsKey(nombre)); }

    private void removeUser(Usuario usuario) {

        map.remove(usuario.nombre, usuario);
    }

    private void removeObject(Usuario usuario) throws UsuarioSinObjetosException {

        if(usuario.listaObjetos.size() == 0) throw new UsuarioSinObjetosException();
        usuario.listaObjetos.remove(0);
    }








}
