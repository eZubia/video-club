package mx.uach.videoclub.dao.jdbc;

import java.util.Date;
import java.util.List;
import mx.uach.videoclub.dao.CRUD;
import mx.uach.videoclub.hibernate.VideoHibernate;
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
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Pruebas del Hibernate.
 * 
 * @author Erik David Zubia Hernández
 */
public class HibernateJUnitTest {
    
    public HibernateJUnitTest() {
    }
    
//    /**
//     * Pruebas unitarias de creación, actualización, busqueda y borrado de 
//     * {@code Director}.
//     */
//    @Test
//    public void testDirector(){
//        VideoHibernate dao = new VideoHibernate(Director.class);
//        Director dir = new Director("Prueba");
//        dao.entityProcces(dir, CRUD.CREATE);
//        List<Director> dirs = (List) dao.getEntitysByCriteria("nombre like Prueba");
//        assertTrue(dirs.size() != 0);
//        assertTrue(dirs.get(0).getNombre().equals("Prueba"));
//        
//        Director dir2 = dirs.get(0);
//        dir = (Director) dao.getEntityById(dir2.getId());
//        assertTrue(dir2.getNombre().equals(dir.getNombre()));
//        
//        dir.setNombre("Prueba2");
//        dao.entityProcces(dir, CRUD.UPDATE);
//        dir2 = (Director) dao.getEntityById(dir.getId());
//        assertTrue(dir2.getNombre().equals("Prueba2"));
//        
//        dao.entityProcces(dir, CRUD.DELETE);
//        dirs = (List) dao.getEntitysByCriteria("nombre like Prueba2'");
//        assertTrue(dirs.isEmpty());
//        
//    }
//    
//    /**
//     * Pruebas unitarias de creación, actualización, busqueda y borrado de 
//     * {@code Actor}.
//     */
//    @Test
//    public void testActor(){
//        VideoHibernate dao = new VideoHibernate(Actor.class);
//        Actor act = new Actor("Nombre", "Prueba");
//        dao.entityProcces(act, CRUD.CREATE);
//        List<Actor> acts = (List) dao.getEntitysByCriteria("nombre like Nombre");
//        assertTrue(!acts.isEmpty());
//        assertTrue(acts.get(0).getNombre().equals("Nombre"));
//        
//        
//        Actor act2 = acts.get(0);
//        act = (Actor) dao.getEntityById(act2.getId());
//        assertTrue(act2.getNombre().equals(act.getNombre()));
//        
//        act.setNombre("Nombre2");
//        dao.entityProcces(act, CRUD.UPDATE);
//        act2 = (Actor) dao.getEntityById(act.getId());
//        assertTrue(act2.getNombre().equals("Nombre2"));
//        
//        dao.entityProcces(act, CRUD.DELETE);
//        acts = (List) dao.getEntitysByCriteria("nombre like Nombre2");
//        assertTrue(acts.isEmpty());
//        
//    }
//    
//    /**
//     * Pruebas unitarias de creación, actualización, busqueda y borrado de 
//     * {@code Cinta}.
//     */
//    @Test
//    public void testCinta(){
//        VideoHibernate dao = new VideoHibernate(Cinta.class);
//        
//        Director dir = new Director("Jony");
//        dao.entityProcces(dir, CRUD.CREATE);
//        Pelicula pelicula = new Pelicula("Titulo de prueba", EGenero.TERROR, 120, dir);
//        dao.entityProcces(pelicula, CRUD.CREATE);
//        Cinta cin = new Cinta(10, pelicula);
//        dao.entityProcces(cin, CRUD.CREATE);
//        List<Cinta> cins = (List) dao.getEntitysByCriteria("numero_cinta = 10");
//        assertTrue(!cins.isEmpty());
//        assertTrue(cins.get(0).getNumeroCinta().equals(10));
//        
//        
//        Cinta cin2 = cins.get(0);
//        cin = (Cinta) dao.getEntityById(cin2.getId());
//        assertTrue(cin2.getNumeroCinta().equals(cin.getNumeroCinta()));
//        
//        cin.setNumeroCinta(11);
//        dao.entityProcces(cin, CRUD.UPDATE);
//        cin2 = (Cinta) dao.getEntityById(cin.getId());
//        assertTrue(cin2.getNumeroCinta().equals(11));
//        
//        dao.entityProcces(cin, CRUD.DELETE);
//        cins = (List) dao.getEntitysByCriteria("numero_cinta = 11");
//        dao.entityProcces(pelicula, CRUD.DELETE);
//        dao.entityProcces(dir, CRUD.DELETE);
//        assertTrue(cins.isEmpty());
//        
//    }
//    
//    /**
//     * Pruebas unitarias de creación, actualización, busqueda y borrado de 
//     * {@code Ficha}.
//     */
//    @Test
//    public void testFicha(){
//        VideoHibernate dao = new VideoHibernate(Ficha.class);
//        Socio socio = new Socio("Nombre de prueba", "Direccion de Prueba", "12312312312");
//        dao.entityProcces(socio, CRUD.CREATE);
//        Socio socio2 = new Socio("Nombre de prueba2", "Direccion de Prueba2", "098765456789");
//        dao.entityProcces(socio2, CRUD.CREATE);
//        Ficha fic = new Ficha(new Date(), socio);
//        dao.entityProcces(fic, CRUD.CREATE);
//        List<Ficha> fichas = (List) dao.getEntitysByCriteria("");
//        assertTrue(!fichas.isEmpty());
//        assertTrue(fichas.get(0).getSocio().getId().equals(socio.getId()));
//        
//        
//        Ficha fic2 = fichas.get(0);
//        fic = (Ficha) dao.getEntityById(fic2.getId());
//        assertTrue(fic2.getSocio().getId().equals(fic.getSocio().getId()));
//        
//        fic.setSocio(socio2);
//        dao.entityProcces(fic, CRUD.UPDATE);
//        fic2 = (Ficha) dao.getEntityById(fic.getId());
//        assertTrue(fic2.getSocio().getId().equals(socio2.getId()));
//        
//        dao.entityProcces(fic, CRUD.DELETE);
//        fichas = (List) dao.getEntitysByCriteria("");
//        dao.entityProcces(socio, CRUD.DELETE);
//        dao.entityProcces(socio2, CRUD.DELETE);
//        assertTrue(fichas.isEmpty());    
//    }
//    
//    /**
//     * Pruebas unitarias de creación, actualización, busqueda y borrado de 
//     * {@code Lista}.
//     */
//    @Test
//    public void testLista(){
//        VideoHibernate dao = new VideoHibernate(Lista.class);
//        Socio socio = new Socio("Nombre de prueba", "Direccion de Prueba", "12312312312");
//        dao.entityProcces(socio, CRUD.CREATE);
//        Socio socio2 = new Socio("Nombre de prueba2", "Direccion de Prueba2", "09876567898");
//        dao.entityProcces(socio2, CRUD.CREATE);
//        Director dir = new Director("Jony");
//        dao.entityProcces(dir, CRUD.CREATE);
//        Pelicula pelicula = new Pelicula("Titulo de prueba", EGenero.TERROR, 120, dir);
//        dao.entityProcces(pelicula, CRUD.CREATE);
//        Lista lis = new Lista(new Date(), new Date(), Boolean.TRUE, socio, pelicula);
//        dao.entityProcces(lis, CRUD.CREATE);
//        List<Lista> listas = (List) dao.getEntitysByCriteria("");
//        assertTrue(!listas.isEmpty());
//        assertTrue(listas.get(0).getSocio().getId().equals(socio.getId()));
//        
//        
//        Lista lis2 = listas.get(0);
//        lis = (Lista) dao.getEntityById(lis2.getId());
//        assertTrue(lis2.getSocio().getId().equals(lis.getSocio().getId()));
//        
//        
//        lis.setSocio(socio2);
//        dao.entityProcces(lis, CRUD.UPDATE);
//        lis2 = (Lista) dao.getEntityById(lis.getId());
//        assertTrue(lis2.getSocio().getId().equals(socio2.getId()));
//        
//        dao.entityProcces(lis, CRUD.DELETE);
//        listas = (List) dao.getEntitysByCriteria("");
//        dao.entityProcces(socio, CRUD.DELETE);
//        dao.entityProcces(socio2, CRUD.DELETE);
//        dao.entityProcces(pelicula, CRUD.DELETE);
//        dao.entityProcces(dir, CRUD.DELETE);
//        assertTrue(listas.isEmpty());    
//    }
//    
//    /**
//     * Pruebas unitarias de creación, actualización, busqueda y borrado de 
//     * {@code Pelicula}.
//     */
//    @Test
//    public void testPelicula(){
//        VideoHibernate dao = new VideoHibernate(Pelicula.class);
//        Director dir = new Director("Jony");
//        dao.entityProcces(dir, CRUD.CREATE);
//        Pelicula peli = new Pelicula("Prueba2", EGenero.TERROR, 102, dir);
//        dao.entityProcces(peli, CRUD.CREATE);
//        List<Pelicula> peliculas = dao.getEntitysByCriteria("titulo like Prueba2");
//        assertTrue(!peliculas.isEmpty());
//        assertTrue(peliculas.get(0).getTitulo().equals("Prueba2"));
//        
//        
//        Pelicula peli2 = peliculas.get(0);
//        peli = (Pelicula) dao.getEntityById(peli2.getId());
//        assertTrue(peli2.getTitulo().equals(peli.getTitulo()));
//        
//        peli.setTitulo("PasamosPrueba");
//        dao.entityProcces(peli, CRUD.UPDATE);
//        peli2 = (Pelicula) dao.getEntityById(peli.getId());
//        assertTrue(peli2.getTitulo().equals("PasamosPrueba"));
//        
//        dao.entityProcces(peli, CRUD.DELETE);
//        peliculas = dao.getEntitysByCriteria("titulo like PasamosPrueba");
//        dao.entityProcces(dir, CRUD.DELETE);
//        assertTrue(peliculas.isEmpty());    
//    }
//    
//    /**
//     * Pruebas unitarias de creación, actualización, busqueda y borrado de 
//     * {@code Prestamo}.
//     */
//    @Test
//    public void testPrestamo(){
//        VideoHibernate dao = new VideoHibernate(Prestamo.class);
//        Director dir = new Director("Jony");
//        dao.entityProcces(dir, CRUD.CREATE);
//        Pelicula peli = new Pelicula("Prueba2", EGenero.TERROR, 102, dir);
//        dao.entityProcces(peli, CRUD.CREATE);
//        
//        Socio socio = new Socio("Nombre de prueba2", "Direccion de Prueba2", "098765456789");
//        dao.entityProcces(socio, CRUD.CREATE);
//        Ficha ficha = new Ficha(new Date(), socio);
//        dao.entityProcces(ficha, CRUD.CREATE);
//        Ficha ficha2 = new Ficha(new Date(), socio);
//        dao.entityProcces(ficha2, CRUD.CREATE);
//        
//        Cinta cinta = new Cinta(10, peli);
//        dao.entityProcces(cinta, CRUD.CREATE);
//        Prestamo pres = new Prestamo(new Date(), EEstatusPrestamo.E, ficha, cinta);
//        dao.entityProcces(pres, CRUD.CREATE);
//        List<Prestamo> prestamos = dao.getEntitysByCriteria("");
//        assertTrue(!prestamos.isEmpty());
//        assertTrue(prestamos.get(0).getFicha().getId().equals(ficha.getId()));
//        
//        
//        Prestamo pres2 = prestamos.get(0);
//        pres = (Prestamo) dao.getEntityById(pres2.getId());
//        assertTrue(pres2.getFicha().getId().equals(pres.getFicha().getId()));
//        
//        pres.setFicha(ficha2);
//        dao.entityProcces(pres, CRUD.UPDATE);
//        pres2 = (Prestamo) dao.getEntityById(pres.getId());
//        assertTrue(pres2.getFicha().getId().equals(ficha2.getId()));
//        
//        dao.entityProcces(pres, CRUD.DELETE);
//        prestamos = dao.getEntitysByCriteria("");
//        dao.entityProcces(ficha2, CRUD.DELETE);
//        dao.entityProcces(ficha, CRUD.DELETE);
//        dao.entityProcces(cinta, CRUD.DELETE);
//        dao.entityProcces(socio, CRUD.DELETE);
//        dao.entityProcces(peli, CRUD.DELETE);
//        dao.entityProcces(dir, CRUD.DELETE);
//        assertTrue(prestamos.isEmpty());    
//    }
//    
//    /**
//     * Pruebas unitarias de creación, actualización, busqueda y borrado de 
//     * {@code Socio}.
//     */
//    @Test
//    public void testSocio(){
//        VideoHibernate dao = new VideoHibernate(Socio.class);
//        Socio socio = new Socio("Pruebonio", "Pruebitas", "12345");
//        dao.entityProcces(socio, CRUD.CREATE);
//        List<Socio> socios = dao.getEntitysByCriteria("nombre like Pruebonio");
//        assertTrue(!socios.isEmpty());
//        assertTrue(socios.get(0).getNombre().equals("Pruebonio"));
//        
//        
//        Socio socio2 = socios.get(0);
//        socio = (Socio) dao.getEntityById(socio2.getId());
//        assertTrue(socio2.getNombre().equals(socio.getNombre()));
//        
//        socio.setNombre("Prubita");
//        dao.entityProcces(socio, CRUD.UPDATE);
//        socio2 = (Socio) dao.getEntityById(socio.getId());
//        assertTrue(socio2.getNombre().equals("Prubita"));
//        
//        dao.entityProcces(socio, CRUD.DELETE);
//        socios = dao.getEntitysByCriteria("nombre like Prubita");
//        assertTrue(socios.isEmpty());    
//    }
    
}
