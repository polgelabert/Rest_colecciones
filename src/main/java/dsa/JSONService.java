package dsa;

import dsa.Exceptions.*;
import org.apache.log4j.Logger;
import org.glassfish.grizzly.utils.Exceptions;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/json")
public class JSONService {


    protected Mundo miMundo;
    String NL = System.getProperty("line.separator");
    //final static Logger log = Logger.getLogger(Main.class.getName());

    public JSONService() throws UsuarioYaExisteException{

        Usuario user;
        Objeto objeto;
        miMundo = new Mundo();

        try {
            user = new Usuario("pol", "1234", 10, 20, 30, 40);
            objeto = new Objeto("espada", "samurai", "espada para luchar contra los enemigos", 500, 350);
            user.listaObjetos.add(objeto);
            miMundo.crearUsuario(user);

            user = new Usuario("marc", "marc_pass", 50, 60, 70, 80);
            objeto = new Objeto("punal", "asesinato", "puñal para asesinar a los enemigos", 300, 150);
            user.listaObjetos.add(objeto);
            objeto = new Objeto("granada", "explosion", "granada para lanzar", 1850, 2550);
            user.listaObjetos.add(objeto);
            miMundo.crearUsuario(user);

            user = new Usuario("oscar", "56789", 111, 222, 333, 40);
            miMundo.crearUsuario(user);

        }
        catch (Exception e) {
            //log.fatal(e.getMessage() + e.getCause());
            //e.printStackTrace();
            throw e;
        }

    }


    @GET
    @Path("/usuario/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario consultarUsuarioInJSON(@PathParam("nombre") String nombre) throws UsuarioNoExisteException {
    //public Usuario consultarUsuarioInJSON(@PathParam("nombre") String nombre) throws Exception {
        try {

            //Objeto re = new Objeto("espada", "samurai", "espada para luchar contra los enemigos", 500, 350);
            Usuario user = miMundo.consultarUsuario(nombre);
            return user;

        } catch (Exception e) {
            //log.fatal(e.getMessage() + e.getCause());
            //e.printStackTrace();
            throw e;
            //throw new Exception("Usuario no existe " + e);

            //return Response.status( 404 ).entity( e.getCause() ).build();;
        }
    }

    @GET
    @Path("/listaUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> consultarListaDeUsuarioInJSON() throws ListaUsuariosVaciaException {
    //public List<Usuario> consultarListaDeUsuarioInJSON() throws  Exception {
        try {

            return miMundo.consultarListaUsuarios();

        } catch (Exception e) {
            //throw new Exception(e);
            throw e;
        }
    }

    @GET
    @Path("/usuario/{nombre}/{nombreObjeto}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Objeto consultarObjetoDeUsuarioInJSON(@PathParam("nombre") String nombre, @PathParam("nombreObjeto") String nombreObjeto) throws UsuarioNoExisteException, UsuarioSinObjetosException, ObjetoNoEncontradoException {
    //public Objeto consultarObjetoDeUsuarioInJSON(@PathParam("nombre") String nombre, @PathParam("nombreObjeto") String nombreObjeto) throws Exception, UsuarioNoExisteException, UsuarioSinObjetosException {
        try {

            //Objeto re = new Objeto("espada", "samurai", "espada para luchar contra los enemigos", 500, 350);
            Objeto objeto = miMundo.consultarObjetoDeUsuario(nombre, nombreObjeto);
            return objeto;

        } catch (Exception e) {
            throw e;
            //throw new Exception(" Objeto no encontrado " + "\r\n" + e);
        }
    }

    @GET
    @Path("/usuario/{nombre}/listaObjetos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Objeto> consultarObjetosDeUsuarioInJSON(@PathParam("nombre") String nombre) throws UsuarioNoExisteException, UsuarioSinObjetosException {

        try {

            return miMundo.consultarObjetosDeUsuario(nombre);

        } catch (Exception e) {
            throw e;
        }

    }

    @POST
    @Path("/newUser/{nombre}/{password}/{nivel}/{ataque}/{defensa}/{resistencia}")
    @Produces(MediaType.TEXT_PLAIN)
    //@Produces(MediaType.APPLICATION_JSON)
    public Response crearUsuarioInJSON(@PathParam("nombre") String nombre, @PathParam("password") String password, @PathParam("nivel") int nivel, @PathParam("ataque") int ataque, @PathParam("defensa") int defensa, @PathParam("resistencia") int resistencia) throws UsuarioYaExisteException {

        try {
            Usuario user = new Usuario(nombre, password, nivel, ataque, defensa, resistencia);
            miMundo.crearUsuario(user);

            return Response.status(201).entity("Usuario añadido correctamente.").build();
            //return consultarListaDeUsuarioInJSON();

        } catch (Exception e) {
            throw e;
        }
    }

    @POST
    @Path("/newObject/{nombre}/{nombreObjeto}/{tipoObjeto}/{descripcionObjeto}/{valorObjeto}/{costeObjeto}")
    //@Produces(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response añadirObjetoAUsuarioInJSON(@PathParam("nombre") String nombre, @PathParam("nombreObjeto") String nombreObjeto, @PathParam("tipoObjeto") String tipoObjeto, @PathParam("descripcionObjeto") String descripcionObjeto, @PathParam("valorObjeto") int valorObjeto, @PathParam("costeObjeto") int costeObjeto) throws UsuarioNoExisteException {

        try {

            Objeto objeto = new Objeto(nombreObjeto, tipoObjeto, descripcionObjeto, valorObjeto, costeObjeto);
            miMundo.añadirObjetoAUsuario(nombre, objeto);

            //return consultarUsuarioInJSON(nombre);
            return Response.status(201).entity("Objeto añadido correctamente.").build();

        } catch (Exception e) {
            throw e;
        }
    }

    @POST
    @Path("removeUser/{nombre}/")
    @Produces(MediaType.TEXT_PLAIN)
    //@Produces(MediaType.APPLICATION_JSON)
    public Response eliminarUsuarioInJSON(@PathParam("nombre") String nombre) throws UsuarioNoExisteException {

        try {

            if (miMundo.eliminarUsuario(nombre))
                return Response.status(201).entity("Usuario eliminado correctamente.").build();

        } catch (Exception e) {
            throw e;
        }
        // Porque pide Response aqui (Missing return statement) ??
        return Response.status(500).entity("Error al eliminar el Usuario.").build();
    }

    @POST
    @Path("removeObject/{nombre}/")
    @Produces(MediaType.TEXT_PLAIN)
    //@Produces(MediaType.APPLICATION_JSON)
    public Response eliminarObjetosDeUsuarioInJSON(@PathParam("nombre") String nombre) throws UsuarioNoExisteException, UsuarioSinObjetosException {

        try {

            if (miMundo.eliminarObjetosDeUsuario(nombre))
                return Response.status(201).entity("Objeto eliminado correctamente.").build();

        } catch (Exception e) {
            throw e;
        }
        // Porque pide Response aqui (Missing return statement) ??
        return Response.status(500).entity("Error al eliminar el Objeto.").build();
    }

    @POST
    @Path("transferObject/{origen}/{destino}/{nombreObjeto}")
    @Produces(MediaType.TEXT_PLAIN)
    //@Produces(MediaType.APPLICATION_JSON)
    //public Response transferirObjetosEntreUsuariosInJSON(@PathParam("origen") String origen, @PathParam("destino") String destino, @PathParam("nombreObjeto") String nombreObjeto) throws UsuarioNoExisteException, UsuarioSinObjetosException, ObjetoNoEncontradoException {
    public Response transferirObjetosEntreUsuariosInJSON(@PathParam("origen") String origen, @PathParam("destino") String destino, @PathParam("nombreObjeto") String nombreObjeto) throws Exception {

        try {
            miMundo.transferirObjetoEntreUsuarios(origen, destino, nombreObjeto);
            return Response.status(201).entity("Objeto transferido correctamente.").build();

        } catch (Exception e) {
            throw new Exception(e);
        }
    }









    @Path("exception")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String throwIt() throws Exception {
        throw new Exception("My exception");
    }




}






