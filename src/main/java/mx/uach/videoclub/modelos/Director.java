package mx.uach.videoclub.modelos;

import mx.uach.videoclub.modelos.genericos.Model;

/**
 * Modelo para mappear los directores de las películas del video club.
 *
 * @author Erik David Zubia Hernández
 * @version 1.0
 */
public class Director extends Model {

    public static final String TABLA = "Directores";
    public static final String[] FIELDS = {"id", "nombre"};
    public static final String Q = String.format("SELECT %s FROM %s", fieldsToQuery(FIELDS, Boolean.FALSE), TABLA);
    public static final String INSERT_DIRECTOR = String.format("%s %s (%s) VALUES (%s)", 
            Model.INSERT, TABLA, fieldsToQuery(FIELDS, Boolean.TRUE), paramsToStatement(FIELDS, Boolean.TRUE) );
    public static final String UPDATE_DIRECTOR = String.format("%s %s SET %s WHERE %s = ?", Model.UPDATE, TABLA, paramsToStatementToCreate(FIELDS, Boolean.TRUE), ID);
    public static final String DELETE_DIRECTOR = String.format("%s %s %s ?", Model.DELETE, TABLA, Model.Q_WHERE_ID);

    private String nombre;

    public Director() {
    }

    public Director(String nombre) {
        this.nombre = nombre;
    }

    public Director(Integer id, String nombre) {
        this(nombre);
        this.setId(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
