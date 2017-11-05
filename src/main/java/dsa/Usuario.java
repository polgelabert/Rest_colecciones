package dsa;

import dsa.Exceptions.ObjetoNoEncontradoException;
import dsa.Exceptions.UsuarioSinObjetosException;

import java.util.ArrayList;
import java.util.List;


public class Usuario {

    public String nombre;
    public String password;
    public int nivel;
    public int ataque;
    public int defensa;
    public int resistencia;
    public List<Objeto> listaObjetos;

    public Usuario(){}

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public int getNivel() { return nivel; }

    public void setNivel(int nivel) { this.nivel = nivel; }

    public int getAtaque() { return ataque; }

    public void setAtaque(int ataque) { this.ataque = ataque; }

    public int getDefensa() { return defensa; }

    public void setDefensa(int defensa) { this.defensa = defensa; }

    public int getResistencia() { return resistencia; }

    public void setResistencia(int resistencia) { this.resistencia = resistencia; }

    public List<Objeto> getListaObjetos() { return listaObjetos; }

    public void setListaObjetos(List<Objeto> listaObjetos) { this.listaObjetos = listaObjetos; }


    @Override
    public String toString() {
        return "Usuario [nombre=" + nombre + ", password=" + password + ", nivel=" + nivel + ", ataque=" + ataque + ", defensa=" + defensa + ", resistencia=" + resistencia + "]";
    }



    public Usuario(String nombre, String password, int nivel, int ataque, int defensa, int resistencia, List<Objeto> listaObjetos) {
        this.nombre = nombre;
        this.password = password;
        this.nivel = nivel;
        this.ataque = ataque;
        this.defensa = defensa;
        this.resistencia = resistencia;
        this.listaObjetos = listaObjetos;
    }


    public Usuario(String nombre, String password, int nivel, int ataque, int defensa, int resistencia) {
        this(nombre,password, nivel, ataque, defensa, resistencia, new ArrayList<Objeto>());
    }

    public boolean usuarioEsIgual(Usuario player) {

        if (player.nombre == this.nombre && player.password == this.password && player.nivel == this.nivel && player.ataque == this.ataque && player.defensa == this.defensa && player.resistencia == this.resistencia) {
            if (player.listaObjetos.get(0).nombreObjeto == this.listaObjetos.get(0).nombreObjeto && player.listaObjetos.get(0).tipoObjeto == this.listaObjetos.get(0).tipoObjeto && player.listaObjetos.get(0).descripcionObjeto == this.listaObjetos.get(0).descripcionObjeto && player.listaObjetos.get(0).valorObjeto == this.listaObjetos.get(0).valorObjeto && player.listaObjetos.get(0).costeObjeto == this.listaObjetos.get(0).costeObjeto) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean listaEsIgual (List<Objeto> lobj_consult){

        boolean resp = false;
        int m = 0;
        for (int n = 0; n< lobj_consult.size(); n++) {

            if (lobj_consult.get(m).nombreObjeto == this.listaObjetos.get(m).nombreObjeto && lobj_consult.get(m).tipoObjeto == this.listaObjetos.get(m).tipoObjeto && lobj_consult.get(m).descripcionObjeto == this.listaObjetos.get(m).descripcionObjeto && lobj_consult.get(m).valorObjeto == this.listaObjetos.get(m).valorObjeto && lobj_consult.get(m).costeObjeto == this.listaObjetos.get(m).costeObjeto) {
                resp = true;
            } else {
                m = m+1;
            }
        }

        return resp;
    }

    public void insertarObjeto(Objeto o) {
        this.listaObjetos.add(o);
    }

    public Objeto getObjeto(String nombreObjeto) throws UsuarioSinObjetosException, ObjetoNoEncontradoException {
    //public Objeto getObjeto(String nombreObjeto) throws Exception {
        for (Objeto o: this.listaObjetos) {
            if (o.nombreObjeto.equals(nombreObjeto)) {
                return o;
            }
        }

        if (this.listaObjetos.size() != 0) throw new ObjetoNoEncontradoException();
        throw new UsuarioSinObjetosException();
    }

    public List<Objeto> getListaObjetos(String nombre) {

        return this.listaObjetos;
    }
}