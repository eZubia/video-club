package mx.uach.videoclub.dao.jdbc.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import mx.uach.videoclub.dao.VideoDao;
import mx.uach.videoclub.dao.jdbc.VideoDaoJDBC;
import mx.uach.videoclub.modelos.Actor;
import mx.uach.videoclub.modelos.Cinta;
import mx.uach.videoclub.modelos.Director;
import mx.uach.videoclub.modelos.Ficha;
import mx.uach.videoclub.modelos.Lista;
import mx.uach.videoclub.modelos.Pelicula;
import mx.uach.videoclub.modelos.Prestamo;
import mx.uach.videoclub.modelos.Socio;
import mx.uach.videoclub.modelos.enums.EEstatusPrestamo;
import mx.uach.videoclub.modelos.enums.EGenero;

/**
 *
 * Helper que genera objetos del Dao.
 * 
 * @author Erik David Zubia Hernádez.
 */
public class VideoDaoJdbcHelper {
    
    public final static Director makeDirector(ResultSet rs) throws SQLException{
        Director obj = new Director(rs.getInt(Director.FIELDS[0]), rs.getString(Director.FIELDS[1]));
        return obj;
    }
    
    public final static Actor makeActor(ResultSet rs) throws SQLException{
        Actor obj = new Actor(rs.getString(Actor.FIELDS[1]), rs.getString(Actor.FIELDS[2]), rs.getInt(Actor.FIELDS[0]));
        return obj;
    }
    
    public final static Cinta makeCinta(ResultSet rs) throws SQLException{
        Pelicula pelicula;
        VideoDao dao = new VideoDaoJDBC();
        pelicula = (Pelicula) dao.getPeliculaById(rs.getInt(Cinta.FIELDS[2]));
        Cinta obj = new Cinta(rs.getInt(Cinta.FIELDS[1]), pelicula, rs.getInt(Cinta.FIELDS[0]));
        return obj;
    }
    
    public final static Ficha makeFicha(ResultSet rs) throws SQLException{
        Socio socio;
        VideoDao dao = new VideoDaoJDBC();
        socio = (Socio) dao.getSocioById(rs.getInt(Ficha.FIELDS[2]));
        Ficha obj = new Ficha(rs.getDate(Ficha.FIELDS[1]), socio, rs.getInt(Ficha.FIELDS[0]));
        return obj;
    }
    
    public final static Lista makeLista(ResultSet rs) throws SQLException{
        VideoDao dao = new VideoDaoJDBC();
        Socio socio = dao.getSocioById(rs.getInt(Lista.FIELDS[2]));
        Pelicula pelicula = dao.getPeliculaById(rs.getInt(Lista.FIELDS[1]));
        Lista obj = new Lista(rs.getDate(Lista.FIELDS[3]), rs.getDate(Lista.FIELDS[4]), rs.getBoolean(Lista.FIELDS[5]), socio, pelicula, rs.getInt(Lista.FIELDS[0]));
        return obj;
    }
    
    public final static Pelicula makePelicula(ResultSet rs) throws SQLException{
        VideoDao dao = new VideoDaoJDBC();
        Director director = dao.getDirectorById(rs.getInt(Pelicula.FIELDS[4]));
        Pelicula obj = new Pelicula(rs.getString(Pelicula.FIELDS[1]), EGenero.valueOf(rs.getString(Pelicula.FIELDS[2])), rs.getInt(Pelicula.FIELDS[3]), director, rs.getInt(Pelicula.FIELDS[0]));
        return obj;
    }
    
    public final static Prestamo makePrestamo(ResultSet rs) throws SQLException{
        VideoDao dao = new VideoDaoJDBC();
        Ficha ficha = dao.getFichaById(rs.getInt(Prestamo.FIELDS[2]));
        Cinta cinta = dao.getCintaById(rs.getInt(Prestamo.FIELDS[1]));
        Prestamo obj = new Prestamo(rs.getDate(Prestamo.FIELDS[3]), EEstatusPrestamo.valueOf(rs.getString(Prestamo.FIELDS[4])), ficha, cinta, rs.getInt(Prestamo.FIELDS[0]));
        return obj;
    }
    
    public final static Socio makeSocio(ResultSet rs) throws SQLException{
        Socio obj = new Socio(rs.getString(Socio.FIELDS[1]), rs.getString(Socio.FIELDS[2]), rs.getString(Socio.FIELDS[3]), rs.getInt(Socio.FIELDS[0]));
        return obj;
    }
    
}
