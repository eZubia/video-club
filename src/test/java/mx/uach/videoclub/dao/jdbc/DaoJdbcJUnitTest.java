package mx.uach.videoclub.dao.jdbc;

import mx.uach.videoclub.dao.CRUD;
import mx.uach.videoclub.dao.VideoDao;
import mx.uach.videoclub.modelos.Director;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas del Dao JDBC
 * 
 * @author Erik David Zubia Hernández
 */
public class DaoJdbcJUnitTest {
    
    public DaoJdbcJUnitTest() {
    }
    
//    @Test
//    public void directorByIdTest(){
//        VideoDao dao = new VideoDaoJDBC();
//        Director d = dao.getDirectorById(1);
//        assertNotNull(d);
//        assertEquals(d.getNombre(), "James Cameron");
//        assertNotEquals(d.getNombre(), "Alfonso Cuaron");
//        
//        d = dao.getDirectorById(2);
//        assertNotNull(d);
//        assertEquals(d.getNombre(), "Alfonso Cuaron");
//        
////        List<Director> directores = dao.getDirectoresByCriteria("");
////        assertEquals(2, directores.size());
//        
//    }
    
//    @Test
//    public void makeDirector(){
//        VideoDao dao = new VideoDaoJDBC();
//        dao.direactorProcces(new Director("Tim Burton"), CRUD.CREATE);
//        Director d = dao.getDirectorById(3);
//        assertNotNull(d);
//        assertEquals(d.getNombre(), "Tim Burton");
//    }
    
     @Test
    public void updateDirector(){
        VideoDao dao = new VideoDaoJDBC();
        Director d = dao.getDirectorById(1);
        assertNotNull(d);
//        assertEquals(d.getNombre(), "James Cameron");
        d.setNombre("David Yates");
        dao.direactorProcces(d, CRUD.UPDATE);
        
        d = dao.getDirectorById(1);
        assertEquals(d.getNombre(), "David Yates");
        
    }
    
     @Test
    public void deleteDirector(){
        VideoDao dao = new VideoDaoJDBC();
        Director d = dao.getDirectorById(3);
        assertNotNull(d);
//        assertEquals(d.getNombre(), "James Cameron");
        d.setNombre("David Yates");
        dao.direactorProcces(d, CRUD.DELETE);
        
        d = dao.getDirectorById(3);
        assertNull(d);
        
    }
    
}
