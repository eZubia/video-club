package mx.uach.videoclub.modelos.genericos;

import com.google.common.base.CaseFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Modelo general de todo el sistema.
 * 
 * @author Erik David Zubia Hernández
 * @version 1.0
 */
public class Model {
    
    public static final String Q_WHERE_ID = "WHERE id = ";
    public static final String Q_WHERE = "WHERE";
    public static final String INSERT = "INSERT INTO";
    public static final String UPDATE = "UPDATE";
    public static final String DELETE = "DELETE FROM";
    public static final String ID = "id";
    
    private Integer id;

    /**
     * 
     */
    public Model() {
    }

    public Model(Integer id) {
        this.id = id;
    }
    
    

    /**
     * 
     * @return 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * Convierte de un arreglo de campos a un {@code String} para un query.
     * 
     * @param fields {@code String[]} son los atributos de la tabla
     * @param noId si es verdadero excluye el campo id
     * @return attr1,attr2, ..., attrn
     */
    public static String fieldsToQuery(String[] fields, Boolean noId){
        String campos = "";
        List<String> fieldsList = Arrays.asList(fields);
        fieldsList = noId ? 
                fieldsList.stream()
                        .filter(field -> !field.equals(ID))
                        .collect(Collectors.toList()) 
                : fieldsList;
        for (String field : fieldsList) {
            campos = String.format("%s, %s", campos, field);
        }
        return campos.substring(1);
    };
    
    public static String paramsToStatement(String[] fields, Boolean noId){
        String campos = "";
        List<String> fieldsList = Arrays.asList(fields);
        fieldsList = noId ? 
                fieldsList.stream()
                        .filter(field -> !field.equals(ID))
                        .collect(Collectors.toList()) 
                : fieldsList;
        for (String field : fieldsList) {
            campos = String.format("%s, ?", campos);
        }
        return campos.substring(1);
    }
    
    public static String paramsToStatementToCreate(String[] fields, Boolean noId){
        String campos = "";
        List<String> fieldsList = Arrays.asList(fields);
        fieldsList = noId ? 
                fieldsList.stream()
                        .filter(field -> !field.equals(ID))
                        .collect(Collectors.toList()) 
                : fieldsList;
        for (String field : fieldsList) {
            campos = String.format("%s, %s = ?", campos, field);
        }
        return campos.substring(1);
    }
    
    /**
     * Convierte un nombre de atributo que se encuentra en CamelCase a SnakeCase
     * para poder ser insertado en la query de la base de datos.
     *
     * @param fieldName {@code String} nombre del atributo a cambiar
     * @return {@code String} con el nombre del atributo en SnakeCase
     * (snake_case)
     */
    public final static String convertCamelToSnake(String fieldName) {
        String snakeCase = "";
        snakeCase = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, fieldName);
        return snakeCase;
    }

    /**
     * Convierte un nombre de una columna de un modelo entidad-relacion que se
     * encuentra en SnakeCase a CamelCase para poder ser utilizado como atributo
     * en cuna clase de java.
     *
     * @param columnName {@code String} nombre de la columna a convertir
     * @return {@code String} con el nombre de la columna en CamelCase
     * (camelCase)
     */
    public final static String convertSnakeToCamel(String columnName) {
        String snakeCase = "";
        snakeCase = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, columnName);
        return snakeCase;
    }

    /**
     * Realiza un cambio de minuscula o mayusucula a la primera letra del
     * {@code String}.
     * 
     * @param line {@code String} línea a cambiar
     * @return {@code String} con la oración empezando por mayúscula
     */
    public static String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    
}
