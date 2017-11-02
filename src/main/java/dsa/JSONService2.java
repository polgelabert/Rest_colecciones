package dsa;
import dsa.Exceptions.UsuarioNoExisteException;
import dsa.*;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/json2")
public class JSONService2 {

    protected Mundo miMundo;
    String nombre;
    public List<Objeto> lobj;

    @GET
    @Path("/got/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsuario(@PathParam("nombre") String nombre){
        //Usuario ret = miMundo.consultarUsuario(nombre);

        //Usuario ret = miMundo.consultarUsuario(nombre);
        String ret = "HOLA";
        return ret;
    }


    @GET
    @Path("/gott/")
    @Produces(MediaType.APPLICATION_JSON)
    public int getNum(){
        //Usuario ret = miMundo.consultarUsuario(nombre);

        //Usuario ret = miMundo.consultarUsuario(nombre);
        int ret = 100;
        return ret;
    }






}
