package dsa;

import dsa.Exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//public class Mundo implements IMundo{
public class Mundo{

    HashMap<String,Usuario> map = new HashMap<>();
    List<Objeto> list_obj_cons;


    public boolean crearUsuario(Usuario u) throws UsuarioYaExisteException{

        if(isUser(u.nombre)) throw new UsuarioYaExisteException();          // lanza excepcion si isUser== true (lo contiene)
        map.put(u.nombre, u);
        return true;                                                        // return true ya que operacion ok
    }

    public void añadirObjetoAUsuario (String nombre, Objeto objeto) throws UsuarioNoExisteException {

        // map.get(u).listaObjetos.add(o);
        Usuario usuario = map.get(nombre);

        if (usuario == null) throw new UsuarioNoExisteException();
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
    public List<Objeto> consultarObjetosDeUsuario (String nombre) throws ListaObjetosVaciaException, UsuarioNoExisteException {

        Usuario usuario = getUser(nombre);
        List<Objeto> listaObjetos = usuario.getListaObjetos(nombre);
        if (listaObjetos == null || listaObjetos.size() == 0) throw new ListaObjetosVaciaException();
        return listaObjetos;
        //return  map.get(nombre).listaObjetos;
    }

    public boolean eliminarUsuario (String nombre) throws UsuarioNoExisteException {

        Usuario usuario = getUser(nombre);
        removeUser(usuario);

        //map.remove(nombre);
        return true;
    }

    public boolean eliminarObjetosDeUsuario(String nombre) throws UsuarioNoExisteException, UsuarioSinObjetosException {

        Usuario usuario = getUser(nombre);
        removeObject(usuario);
        return true;

        /*
            if(map.get(nombre).listaObjetos.size() > 0){
            map.get(nombre).listaObjetos.remove(0);
            return true;
            } else {
            return false;
            }
        */
    }

    public void transferirObjetoEntreUsuarios (String origen, String destino, String nom_obj) throws UsuarioNoExisteException, UsuarioSinObjetosException, ObjetoNoEncontradoException {

        Objeto objeto = consultarObjetoDeUsuario(origen, nom_obj);
        Usuario dest = getUser(destino);
        dest.listaObjetos.add(objeto);
        Usuario orig = getUser(origen);
        orig.listaObjetos.remove(objeto);
    }

    public List<Usuario> consultarListaUsuarios() throws ListaUsuariosException {

        List<Usuario> listaUsers = new ArrayList<Usuario>();
        if (!listaUsers.addAll(map.values())) throw new ListaUsuariosException();
        return listaUsers;
    }

    private Usuario getUser(String nombre) throws UsuarioNoExisteException {

        if (map.get(nombre) == null) throw new UsuarioNoExisteException();
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






////////////////////////////////////////////////////////////////////////////////////

    public String nombreUsuario () throws UsuarioNoExisteException {
        Mundo miMundo = new Mundo();
        HashMap<String, Usuario> map = new HashMap<>();
        Usuario player, returned;
        Objeto o;
        ArrayList<Objeto>  lobj = new ArrayList<Objeto>();

        player = new Usuario("marc", "pass_marc", 50, 60, 70, 80, lobj);
       // o = new Objeto("espada", "samurai", "espada para luchar contra los enemigos", 500, 350);
      //  player.listaObjetos.add(o);
       // player.listaObjetos.remove(o);
        miMundo.map.put(player.nombre, player);

        Usuario re = miMundo.consultarUsuario("marc");
        return re.nombre;
    }


/*
    public Usuario consultarUsuario (int id) throws UsuarioNoExisteException {

        Usuario usuario = getUser(id);
        if( usuario == null) throw new dsa.Exceptions.UsuarioNoExisteException();
        return usuario;
    }
*/










}
