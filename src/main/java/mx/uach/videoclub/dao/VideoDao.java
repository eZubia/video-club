package mx.uach.videoclub.dao;

import java.util.List;
import mx.uach.videoclub.modelos.Director;

/**
 *
 * Declaración de los métodos de escritura y lectura de un modelo a otro;
 * 
 * @author Erik David Zubia Hernández
 * @version 1.0
 */
public interface VideoDao {
    
    public Director getDirectorById(Integer id);
    
    public List<Director> getDirectoresByCriteria(String criterio);
    
    public void direactorProcces(Director director, CRUD crud);
    
    
}
