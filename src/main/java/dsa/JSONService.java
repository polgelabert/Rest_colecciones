package dsa;

import dsa.Exceptions.*;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/json")
public class JSONService {

    protected List<Track> tracks;
    protected Mundo miMundo;
    final static Logger log = Logger.getLogger(Main.class.getName());

    public JSONService() {
        tracks = new ArrayList<>();

        Track t1 = new Track();
        t1.setTitle("Enter Sandman");
        t1.setSinger("Metallica");
        tracks.add(t1);

        Track t2 = new Track();
        t2.setTitle("La Barbacoa");
        t2.setSinger("Georgie Dann");
        tracks.add(t2);


        Usuario user;
        Objeto objeto;
        ArrayList<Objeto>  listaObjetos;

        miMundo = new Mundo();




        try {
            user = new Usuario("pol", "1234", 10, 20, 30, 40);
            objeto = new Objeto("espada", "samurai", "espada para luchar contra los enemigos", 500, 350);
            user.listaObjetos.add(objeto);
            miMundo.crearUsuario(user);

            user = new Usuario("marc", "marc_pass", 50, 60, 70, 80);
            objeto = new Objeto("punal", "asesinato", "puñal para asesinar a los enemigos", 300, 150);
            user.listaObjetos.add(objeto);
            miMundo.crearUsuario(user);

            user = new Usuario("oscar", "56789", 111, 222, 333, 40);
            miMundo.crearUsuario(user);


        } catch (Exception e){
            log.fatal(e.getMessage() + e.getCause());
            e.printStackTrace();
        }


    }

    @GET
    @Path("/got/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Track getTrack(@PathParam("id") int id) {
        return tracks.get(id);
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Track getTrackInJSON() {

        Track track = new Track();
        track.setTitle("Enter Sandman");
        track.setSinger("Metallica");

        return track;

    }






    @GET
    @Path("/usuario/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario consultarUsuarioInJSON(@PathParam("nombre") String nombre) throws UsuarioNoExisteException {

        try {

            //Objeto re = new Objeto("espada", "samurai", "espada para luchar contra los enemigos", 500, 350);
            Usuario user = miMundo.consultarUsuario(nombre);
            return user;

        } catch (Exception e){
            //log.fatal(e.getMessage() + e.getCause());
            //e.printStackTrace();

            //return Response.status(201).entity("Usuario modificado: ").build();
            //Response.status(201).entity("Objeto añadido: ").build();

            throw e;
            //return Response.status( 404 ).entity( e.getCause() ).build();;
        }
    }

    @GET
    @Path("/listaUsuarios")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> consultarListaDeUsuarioInJSON() throws ListaUsuariosException {
        try {

            return miMundo.consultarListaUsuarios();

        } catch (Exception e){
            throw e;
        }
    }

    @GET
    @Path("/usuario/{nombre}/{nombreObjeto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Objeto consultarObjetoDeUsuarioInJSON(@PathParam("nombre") String nombre, @PathParam("nombreObjeto") String nombreObjeto) throws UsuarioNoExisteException, UsuarioSinObjetosException, ObjetoNoEncontradoException {

        try {

            //Objeto re = new Objeto("espada", "samurai", "espada para luchar contra los enemigos", 500, 350);
            Objeto objeto = miMundo.consultarObjetoDeUsuario(nombre, nombreObjeto);
            return objeto;

        } catch (Exception e){
            throw e;
        }
    }

    @GET
    @Path("/usuario/{nombre}/listaObjetos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Objeto> consultarObjetosDeUsuarioInJSON(@PathParam("nombre") String nombre) throws UsuarioNoExisteException, ListaObjetosVaciaException{

        try {

            return miMundo.consultarObjetosDeUsuario(nombre);

        } catch (Exception e) {
            throw e;
        }

    }

    @POST
    @Path("/usuario/{nombre}/{password}/{nivel}/{ataque}/{defensa}/{resistencia}")
    //@Produces(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> crearUsuarioInJSON(@PathParam("nombre") String nombre, @PathParam("password") String password, @PathParam("nivel") int nivel, @PathParam("ataque") int ataque, @PathParam("defensa") int defensa, @PathParam("resistencia") int resistencia) throws ListaUsuariosException, UsuarioYaExisteException {

        try {
            Usuario user = new Usuario(nombre, password, nivel, ataque, defensa, resistencia);
            miMundo.crearUsuario(user);
            return consultarListaDeUsuarioInJSON();
            //return Response.status(201).entity("Usuario añadido correctamente.").build();

        } catch (Exception e){
            throw e;
        }
    }







    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newTrack(Track track) {
        tracks.add(track);
        // Atencion: siempre añade en la misma posicion por el scope de tracks
        return Response.status(201).entity("Track added in position "+tracks.size()).build();
    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrackInJSON(Track track) {

        String result = "Track saved : " + track;
        return Response.status(201).entity(result).build();
    }

}