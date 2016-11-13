package mx.uach.videoclub.hibernate;

import mx.uach.videoclub.dao.jdbc.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import mx.uach.videoclub.conexiones.Conexion;
import mx.uach.videoclub.conexiones.VideoEntityManager;
import mx.uach.videoclub.dao.CRUD;
import mx.uach.videoclub.dao.VideoDao;
import mx.uach.videoclub.dao.jdbc.helper.VideoDaoJdbcHelper;
import mx.uach.videoclub.modelos.Actor;
import mx.uach.videoclub.modelos.Cinta;
import mx.uach.videoclub.modelos.Director;
import mx.uach.videoclub.modelos.Ficha;
import mx.uach.videoclub.modelos.Lista;
import mx.uach.videoclub.modelos.Pelicula;
import mx.uach.videoclub.modelos.Prestamo;
import mx.uach.videoclub.modelos.Socio;
import mx.uach.videoclub.modelos.genericos.Model;


/**
 * Encargado de relizar la logica para la busqueda, actualizacion, creacion y 
 * eliminacion de los datos por medio del .
 * 
 * @author Erik David Zubia Hernandez
 * @version 1.0
 */
public class VideoHibernate<T extends Model> implements VideoDao{

    private Class<T> type;

    public VideoHibernate(Class<T> type) {
        this.type = type;
    }
    
    
    /**
     * Realiza una búsqueda dentro de la base de datos por medio de un identificador
     * para encontrar un {@code Director}.
     * 
     * @param id {@code Long} identificador único del {@code Director}
     * @return {@code Director} mapeado con el resultado del búsqueda
     */
    @Override
    public Director getDirectorById(Long id) {
        Director d = (Director) VideoEntityManager.getINSTANCE().getEm()
                .createQuery(String.format("%s %s %s", Director.Q_HIBERNATE, Director.Q_WHERE_ID, Director.ID_HIBERNATE))
                .setParameter("id", id).getSingleResult();
        return d != null ? d : null;
    }

    /**
     * Realiza una búsqueda a la base de datos, basado en un criterio mandado
     * para conseguir una {@code List} de {@code Director}.
     * 
     * @param criterio {@code String} criterio de búsqueda
     * @return {@code List} de {@code Director} que coincidieron con el criterio
     */
    @Override
    public List<Director> getDirectoresByCriteria(String criterio) {
        Query q;
        try {
            q = makeQueryByTuples(makeCriteriosByString(criterio));
            List<Director> directores = q.getResultList();
            return directores;
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(VideoHibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Realiza según la opción mandada, la creación de un nuevo {@code Director},
     * la actualización de un {@code Director} encontrado o su eliminación.
     * 
     * @param director {@code Director} para cual se realizara la operación
     * @param crud {@code CRUD} tipo de operación a realizar
     */
    @Override
    public void direactorProcces(Director director, CRUD crud) {
        EntityManager em = VideoEntityManager.getINSTANCE().getEm();
        em.getTransaction().begin();
        switch (crud) {
            case CREATE:
            case UPDATE:
                em.persist(director);
                break;
            case DELETE:
                em.remove(director);
                break;
            default:
                break;
        }
        em.getTransaction().commit();
    }

    /**
     * Realiza una búsqueda dentro de la base de datos por medio de un identificador
     * para encontrar un {@code Actor}.
     * 
     * @param id {@code Long} identificador único del {@code Actor}
     * @return {@code Actor} mapeado con el resultado del búsqueda
     */
    @Override
    public Actor getActorById(Long id) {
        Actor object = (Actor) VideoEntityManager.getINSTANCE().getEm().createQuery("from Actor where id = :id").setParameter("id", id).getSingleResult();
        return object != null ? object : null;
    }

    /**
     * Realiza una búsqueda a la base de datos, basado en un criterio mandado
     * para conseguir una {@code List} de {@code Actor}.
     * 
     * @param criterio {@code String} criterio de búsqueda
     * @return {@code List} de {@code Actor} que coincidieron con el criterio
     */
    @Override
    public List<Actor> getActoresByCriteria(String criterio) {
        List<Actor> actores = VideoEntityManager.getINSTANCE().getEm()
                .createQuery("SELECT a FROM Actor a").getResultList();
        return actores;
    }

    /**
     * Realiza según la opción mandada, la creación de un nuevo {@code Actor},
     * la actualización de un {@code Actor} encontrado o su eliminación.
     * 
     * @param actor {@code Actor} para cual se realizara la operación
     * @param crud {@code CRUD} tipo de operación a realizar
     */
    @Override
    public void actorProcces(Actor actor, CRUD crud) {
        EntityManager em = VideoEntityManager.getINSTANCE().getEm();
        em.getTransaction().begin();
        switch (crud) {
            case CREATE:
            case UPDATE:
                em.persist(actor);
                break;
            case DELETE:
                em.remove(actor);
                break;
            default:
                break;
        }
        em.getTransaction().commit();
    }

    /**
     * Realiza una búsqueda dentro de la base de datos por medio de un identificador
     * para encontrar una {@code Cinta}.
     * 
     * @param id {@code Long} identificador único de la {@code Cinta}
     * @return {@code Cinta} mapeada con el resultado del búsqueda
     */
    @Override
    public Cinta getCintaById(Long id) {
        Cinta object = (Cinta) VideoEntityManager.getINSTANCE().getEm().createQuery("from Cinta where id = :id").setParameter("id", id).getSingleResult();
        return object != null ? object : null;
    }

    /**
     * Realiza una búsqueda a la base de datos, basado en un criterio mandado
     * para conseguir una {@code List} de {@code Cinta}.
     * 
     * @param criterio {@code String} criterio de búsqueda
     * @return {@code List} de {@code Actor} que coincidieron con el criterio
     */
    @Override
    public List<Cinta> getCintasByCriteria(String criterio) {
        List<Cinta> objects = VideoEntityManager.getINSTANCE().getEm()
                .createQuery("SELECT a FROM Cinta a").getResultList();
        return objects;
    }

    /**
     * Realiza según la opción mandada, la creación de un nuevo {@code Cinta},
     * la actualización de un {@code Cinta} encontrado o su eliminación.
     * 
     * @param cinta {@code Cinta} para cual se realizara la operación
     * @param crud {@code CRUD} tipo de operación a realizar
     */
    @Override
    public void cintaProcces(Cinta cinta, CRUD crud) {
        EntityManager em = VideoEntityManager.getINSTANCE().getEm();
        em.getTransaction().begin();
        switch (crud) {
            case CREATE:
            case UPDATE:
                em.persist(cinta);
                break;
            case DELETE:
                em.remove(cinta);
                break;
            default:
                break;
        }
        em.getTransaction().commit();
    }

    /**
     * Realiza una búsqueda dentro de la base de datos por medio de un identificador
     * para encontrar una {@code Ficha}.
     * 
     * @param id {@code Long} identificador único de la {@code Ficha}
     * @return {@code Ficha} mapeada con el resultado del búsqueda
     */
    @Override
    public Ficha getFichaById(Long id) {
        Ficha object = (Ficha) VideoEntityManager.getINSTANCE().getEm().createQuery("from Ficha where id = :id").setParameter("id", id).getSingleResult();
        return object != null ? object : null;
    }

    /**
     * Realiza una búsqueda a la base de datos, basado en un criterio mandado
     * para conseguir una {@code List} de {@code Ficha}.
     * 
     * @param criterio {@code String} criterio de búsqueda
     * @return {@code List} de {@code Ficha} que coincidieron con el criterio
     */
    @Override
    public List<Ficha> getFichasByCriteria(String criterio) {
        List<Ficha> objects = VideoEntityManager.getINSTANCE().getEm()
                .createQuery("SELECT a FROM Ficha a").getResultList();
        return objects;
    }

    /**
     * Realiza según la opción mandada, la creación de un nuevo {@code Ficha},
     * la actualización de un {@code Ficha} encontrado o su eliminación.
     * 
     * @param ficha {@code Ficha} para cual se realizara la operación
     * @param crud {@code CRUD} tipo de operación a realizar
     */
    @Override
    public void fichaProcces(Ficha ficha, CRUD crud) {
        EntityManager em = VideoEntityManager.getINSTANCE().getEm();
        em.getTransaction().begin();
        switch (crud) {
            case CREATE:
            case UPDATE:
                em.persist(ficha);
                break;
            case DELETE:
                em.remove(ficha);
                break;
            default:
                break;
        }
        em.getTransaction().commit();
    }

    /**
     * Realiza una búsqueda dentro de la base de datos por medio de un identificador
     * para encontrar una {@code Lista}.
     * 
     * @param id {@code Long} identificador único de la {@code Lista}
     * @return {@code Lista} mapeada con el resultado del búsqueda
     */
    @Override
    public Lista getListaById(Long id) {
        Lista object = (Lista) VideoEntityManager.getINSTANCE().getEm().createQuery("from Lista where id = :id").setParameter("id", id).getSingleResult();
        return object != null ? object : null;
    }

    /**
     * Realiza una búsqueda a la base de datos, basado en un criterio mandado
     * para conseguir una {@code List} de {@code Lista}.
     * 
     * @param criterio {@code String} criterio de búsqueda
     * @return {@code List} de {@code Lista} que coincidieron con el criterio
     */
    @Override
    public List<Lista> getListasByCriteria(String criterio) {
        List<Lista> objects = VideoEntityManager.getINSTANCE().getEm()
                .createQuery("SELECT a FROM Lista a").getResultList();
        return objects;
    }

    /**
     * Realiza según la opción mandada, la creación de un nuevo {@code Lista},
     * la actualización de un {@code Lista} encontrado o su eliminación.
     * 
     * @param lista {@code Lista} para cual se realizara la operación
     * @param crud {@code CRUD} tipo de operación a realizar
     */
    @Override
    public void listaProcces(Lista lista, CRUD crud) {
        EntityManager em = VideoEntityManager.getINSTANCE().getEm();
        em.getTransaction().begin();
        switch (crud) {
            case CREATE:
            case UPDATE:
                em.persist(lista);
                break;
            case DELETE:
                em.remove(lista);
                break;
            default:
                break;
        }
        em.getTransaction().commit();
    }

    /**
     * Realiza una búsqueda dentro de la base de datos por medio de un identificador
     * para encontrar una {@code Pelicula}.
     * 
     * @param id {@code Long} identificador único de la {@code Pelicula}
     * @return {@code Pelicula} mapeada con el resultado del búsqueda
     */
    @Override
    public Pelicula getPeliculaById(Long id) {
        Pelicula object = (Pelicula) VideoEntityManager.getINSTANCE().getEm().createQuery("from Pelicula where id = :id").setParameter("id", id).getSingleResult();
        return object != null ? object : null;
    }

    /**
     * Realiza una búsqueda a la base de datos, basado en un criterio mandado
     * para conseguir una {@code List} de {@code Pelicula}.
     * 
     * @param criterio {@code String} criterio de búsqueda
     * @return {@code List} de {@code Pelicula} que coincidieron con el criterio
     */
    @Override
    public List<Pelicula> getPeliculaByCriteria(String criterio) {
        List<Pelicula> objects = VideoEntityManager.getINSTANCE().getEm()
                .createQuery("SELECT a FROM Pelicula a").getResultList();
        return objects;
    }

    /**
     * Realiza según la opción mandada, la creación de un nuevo {@code Pelicula},
     * la actualización de un {@code Pelicula} encontrado o su eliminación.
     * 
     * @param pelicula {@code Pelicula} para cual se realizara la operación
     * @param crud {@code CRUD} tipo de operación a realizar
     */
    @Override
    public void peliculaProcces(Pelicula pelicula, CRUD crud) {
        EntityManager em = VideoEntityManager.getINSTANCE().getEm();
        em.getTransaction().begin();
        switch (crud) {
            case CREATE:
            case UPDATE:
                em.persist(pelicula);
                break;
            case DELETE:
                em.remove(pelicula);
                break;
            default:
                break;
        }
        em.getTransaction().commit();
    }

    /**
     * Realiza una búsqueda dentro de la base de datos por medio de un identificador
     * para encontrar un {@code Prestamo}.
     * 
     * @param id {@code Long} identificador único del {@code Prestamo}
     * @return {@code Prestamo} mapeado con el resultado del búsqueda
     */
    @Override
    public Prestamo getPrestamoById(Long id) {
        Prestamo object = (Prestamo) VideoEntityManager.getINSTANCE().getEm().createQuery("from Prestamo where id = :id").setParameter("id", id).getSingleResult();
        return object != null ? object : null;
    }

    /**
     * Realiza una búsqueda a la base de datos, basado en un criterio mandado
     * para conseguir una {@code List} de {@code Prestamo}.
     * 
     * @param criterio {@code String} criterio de búsqueda
     * @return {@code List} de {@code Prestamo} que coincidieron con el criterio
     */
    @Override
    public List<Prestamo> getPrestamosByCriteria(String criterio) {
        List<Prestamo> objects = VideoEntityManager.getINSTANCE().getEm()
                .createQuery("SELECT a FROM Prestamo a").getResultList();
        return objects;
    }

    /**
     * Realiza según la opción mandada, la creación de un nuevo {@code Prestamo},
     * la actualización de un {@code Prestamo} encontrado o su eliminación.
     * 
     * @param prestamo {@code Prestamo} para cual se realizara la operación
     * @param crud {@code CRUD} tipo de operación a realizar
     */
    @Override
    public void prestamoProcces(Prestamo prestamo, CRUD crud) {
        EntityManager em = VideoEntityManager.getINSTANCE().getEm();
        em.getTransaction().begin();
        switch (crud) {
            case CREATE:
            case UPDATE:
                em.persist(prestamo);
                break;
            case DELETE:
                em.remove(prestamo);
                break;
            default:
                break;
        }
        em.getTransaction().commit();
    }

    /**
     * Realiza una búsqueda dentro de la base de datos por medio de un identificador
     * para encontrar un {@code Socio}.
     * 
     * @param id {@code Long} identificador único del {@code Socio}
     * @return {@code Socio} mapeado con el resultado del búsqueda
     */
    @Override
    public Socio getSocioById(Long id) {
        Socio object = (Socio) VideoEntityManager.getINSTANCE().getEm().createQuery("from Socio where id = :id").setParameter("id", id).getSingleResult();
        return object != null ? object : null;
    }

    /**
     * Realiza una búsqueda a la base de datos, basado en un criterio mandado
     * para conseguir una {@code List} de {@code Socio}.
     * 
     * @param criterio {@code String} criterio de búsqueda
     * @return {@code List} de {@code Socio} que coincidieron con el criterio
     */
    @Override
    public List<Socio> getSociosByCriteria(String criterio) {
        List<Socio> objects = VideoEntityManager.getINSTANCE().getEm()
                .createQuery("SELECT a FROM Socio a").getResultList();
        return objects;
    }

    @Override
    public void socioProcces(Socio socio, CRUD crud) {
        EntityManager em = VideoEntityManager.getINSTANCE().getEm();
        em.getTransaction().begin();
        switch (crud) {
            case CREATE:
            case UPDATE:
                em.persist(socio);
                break;
            case DELETE:
                em.remove(socio);
                break;
            default:
                break;
        }
        em.getTransaction().commit();
    }

    private HashMap<String, String> makeCriteriosByString(String criterio) {
        System.out.println("criterio.split(, ) = " + criterio.split(", "));
        List<String> criterios = Arrays.asList(criterio.split(", "));
        HashMap<String, String> tuples = new HashMap<>();
        criterios.stream().forEach(parameter ->{
            String[] s = {};
            if(parameter.contains("like")){
                s = parameter.trim().split("like");
                tuples.put(parameter.trim().replace(s[1], String.format(" :%s", s[0])), s[1]);
            } else {
                s = parameter.trim().split("=");
                tuples.put(parameter.trim().replace(s[1], String.format(":%s", s[0])), s[1]);
            }
            System.out.println("s = " + s);
        });
        return tuples;
    }

    private Query makeQueryByTuples(HashMap<String, String> values) throws NoSuchFieldException {
        
        StringBuilder builder = new StringBuilder();
        for (String string : values.keySet()) {
            builder.append(",");
            builder.append(string);
        }
        System.out.println("builder.toString = " + builder.toString());
        System.out.println("type = " + type.getSimpleName());
        
        String s = String.format("%s %s ", T.HIBERNATE.replace("%s", type.getSimpleName()), values.isEmpty() ? "" : String.format("%s %s", Model.Q_WHERE, builder.toString().substring(1)));
        System.out.println("query = " + s);
        Query q = VideoEntityManager.getINSTANCE().getEm()
                .createQuery(s);
        for (String string : values.keySet()) {
            System.out.println("values.get(string) = " + values.get(string));
            q.setParameter(new String(string.split(":")[1]).trim(), values.get(string).trim());
        }
        return q;
    }
    
}
