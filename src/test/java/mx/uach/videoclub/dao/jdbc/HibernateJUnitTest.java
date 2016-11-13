package mx.uach.videoclub.dao.jdbc;

import java.util.Date;
import java.util.List;
import mx.uach.videoclub.dao.CRUD;
import mx.uach.videoclub.dao.VideoDao;
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
    
    /**
     * Pruebas unitarias de creación, actualización, busqueda y borrado de 
     * {@code Director}.
     */
    @Test
    public void testDirector(){
        VideoDao dao = new VideoHibernate(Director.class);
        Director dir = new Director("Prueba");
        dao.direactorProcces(dir, CRUD.CREATE);
        List<Director> dirs = (List) dao.getDirectoresByCriteria("nombre like Prueba");
        assertTrue(dirs.size() != 0);
        assertTrue(dirs.get(0).getNombre().equals("Prueba"));
        
        Director dir2 = dirs.get(0);
        dir = (Director) dao.getDirectorById(dir2.getId());
        assertTrue(dir2.getNombre().equals(dir.getNombre()));
        
        dir.setNombre("Prueba2");
        dao.direactorProcces(dir, CRUD.UPDATE);
        dir2 = (Director) dao.getDirectorById(dir.getId());
        assertTrue(dir2.getNombre().equals("Prueba2"));
        
        dao.direactorProcces(dir, CRUD.DELETE);
        dirs = (List) dao.getDirectoresByCriteria("nombre like Prueba2");
        assertTrue(dirs.isEmpty());
        
    }
    
    /**
     * Pruebas unitarias de creación, actualización, busqueda y borrado de 
     * {@code Actor}.
     */
    @Test
    public void testActor(){
        VideoDao dao = new VideoHibernate(Actor.class);
        Actor act = new Actor("Nombre", "Prueba");
        dao.actorProcces(act, CRUD.CREATE);
        List<Actor> acts = (List) dao.getActoresByCriteria("nombre like Nombre");
        assertTrue(!acts.isEmpty());
        assertTrue(acts.get(0).getNombre().equals("Nombre"));
        
        
        Actor act2 = acts.get(0);
        act = (Actor) dao.getActorById(act2.getId());
        assertTrue(act2.getNombre().equals(act.getNombre()));
        
        act.setNombre("Nombre2");
        dao.actorProcces(act, CRUD.UPDATE);
        act2 = (Actor) dao.getActorById(act.getId());
        assertTrue(act2.getNombre().equals("Nombre2"));
        
        dao.actorProcces(act, CRUD.DELETE);
        acts = (List) dao.getActoresByCriteria("nombre like Nombre2");
        assertTrue(acts.isEmpty());
        
    }
    
    /**
     * Pruebas unitarias de creación, actualización, busqueda y borrado de 
     * {@code Cinta}.
     */
    @Test
    public void testCinta(){
        VideoDao dao = new VideoHibernate(Cinta.class);
        
        Director dir = new Director("Jony");
        dao.direactorProcces(dir, CRUD.CREATE);
        Pelicula pelicula = new Pelicula("Titulo de prueba", EGenero.TERROR, 120, dir);
        dao.peliculaProcces(pelicula, CRUD.CREATE);
        Cinta cin = new Cinta(10, pelicula);
        dao.cintaProcces(cin, CRUD.CREATE);
        List<Cinta> cins = (List) dao.getCintasByCriteria("numero_cinta = 10");
        assertTrue(!cins.isEmpty());
        assertTrue(cins.get(0).getNumeroCinta().equals(10));
        
        
        Cinta cin2 = cins.get(0);
        cin = (Cinta) dao.getCintaById(cin2.getId());
        assertTrue(cin2.getNumeroCinta().equals(cin.getNumeroCinta()));
        
        cin.setNumeroCinta(11);
        dao.cintaProcces(cin, CRUD.UPDATE);
        cin2 = (Cinta) dao.getCintaById(cin.getId());
        assertTrue(cin2.getNumeroCinta().equals(11));
        
        dao.cintaProcces(cin, CRUD.DELETE);
        cins = (List) dao.getCintasByCriteria("numero_cinta = 11");
        dao.peliculaProcces(pelicula, CRUD.DELETE);
        dao.direactorProcces(dir, CRUD.DELETE);
        assertTrue(cins.isEmpty());
        
    }
    
    /**
     * Pruebas unitarias de creación, actualización, busqueda y borrado de 
     * {@code Ficha}.
     */
    @Test
    public void testFicha(){
        VideoDao dao = new VideoHibernate(Ficha.class);
        Socio socio = new Socio("Nombre de prueba", "Direccion de Prueba", "12312312312");
        dao.socioProcces(socio, CRUD.CREATE);
        Socio socio2 = new Socio("Nombre de prueba2", "Direccion de Prueba2", "098765456789");
        dao.socioProcces(socio2, CRUD.CREATE);
        Ficha fic = new Ficha(new Date(), socio);
        dao.fichaProcces(fic, CRUD.CREATE);
        List<Ficha> fichas = (List) dao.getFichasByCriteria(String.format("socios_id = %s", socio.getId()));
        assertTrue(!fichas.isEmpty());
        assertTrue(fichas.get(0).getSocio().getId().equals(socio.getId()));
        
        
        Ficha fic2 = fichas.get(0);
        fic = (Ficha) dao.getFichaById(fic2.getId());
        assertTrue(fic2.getSocio().getId().equals(fic.getSocio().getId()));
        
        fic.setSocio(socio2);
        dao.fichaProcces(fic, CRUD.UPDATE);
        fic2 = (Ficha) dao.getFichaById(fic.getId());
        assertTrue(fic2.getSocio().getId().equals(socio2.getId()));
        
        dao.fichaProcces(fic, CRUD.DELETE);
        fichas = (List) dao.getFichasByCriteria(String.format("socios_id = %s", socio.getId()));
        dao.socioProcces(socio, CRUD.DELETE);
        dao.socioProcces(socio2, CRUD.DELETE);
        assertTrue(fichas.isEmpty());    
    }
    
    /**
     * Pruebas unitarias de creación, actualización, busqueda y borrado de 
     * {@code Lista}.
     */
    @Test
    public void testLista(){
        VideoDao dao = new VideoHibernate(Lista.class);
        Socio socio = new Socio("Nombre de prueba", "Direccion de Prueba", "12312312312");
        dao.socioProcces(socio, CRUD.CREATE);
        Socio socio2 = new Socio("Nombre de prueba2", "Direccion de Prueba2", "09876567898");
        dao.socioProcces(socio2, CRUD.CREATE);
        Director dir = new Director("Jony");
        dao.direactorProcces(dir, CRUD.CREATE);
        Pelicula pelicula = new Pelicula("Titulo de prueba", EGenero.TERROR, 120, dir);
        dao.peliculaProcces(pelicula, CRUD.CREATE);
        Lista lis = new Lista(new Date(), new Date(), Boolean.TRUE, socio, pelicula);
        dao.listaProcces(lis, CRUD.CREATE);
        List<Lista> listas = (List) dao.getListasByCriteria(String.format("socios_id = %s", socio.getId()));
        assertTrue(!listas.isEmpty());
        assertTrue(listas.get(0).getSocio().getId().equals(socio.getId()));
        
        
        Lista lis2 = listas.get(0);
        lis = (Lista) dao.getListaById(lis2.getId());
        assertTrue(lis2.getSocio().getId().equals(lis.getSocio().getId()));
        
        
        lis.setSocio(socio2);
        dao.listaProcces(lis, CRUD.UPDATE);
        lis2 = (Lista) dao.getListaById(lis.getId());
        assertTrue(lis2.getSocio().getId().equals(socio2.getId()));
        
        dao.listaProcces(lis, CRUD.DELETE);
        listas = (List) dao.getListasByCriteria(String.format("socios_id = %s", socio.getId()));
        dao.socioProcces(socio, CRUD.DELETE);
        dao.socioProcces(socio2, CRUD.DELETE);
        dao.peliculaProcces(pelicula, CRUD.DELETE);
        dao.direactorProcces(dir, CRUD.DELETE);
        assertTrue(listas.isEmpty());    
    }
    
    /**
     * Pruebas unitarias de creación, actualización, busqueda y borrado de 
     * {@code Pelicula}.
     */
    @Test
    public void testPelicula(){
        VideoDao dao = new VideoHibernate(Pelicula.class);
        Director dir = new Director("Jony");
        dao.direactorProcces(dir, CRUD.CREATE);
        Pelicula peli = new Pelicula("Prueba2", EGenero.TERROR, 102, dir);
        dao.peliculaProcces(peli, CRUD.CREATE);
        List<Pelicula> peliculas = dao.getPeliculaByCriteria("titulo like Prueba2");
        assertTrue(!peliculas.isEmpty());
        assertTrue(peliculas.get(0).getTitulo().equals("Prueba2"));
        
        
        Pelicula peli2 = peliculas.get(0);
        peli = dao.getPeliculaById(peli2.getId());
        assertTrue(peli2.getTitulo().equals(peli.getTitulo()));
        
        peli.setTitulo("PasamosPrueba");
        dao.peliculaProcces(peli, CRUD.UPDATE);
        peli2 = dao.getPeliculaById(peli.getId());
        assertTrue(peli2.getTitulo().equals("PasamosPrueba"));
        
        dao.peliculaProcces(peli, CRUD.DELETE);
        peliculas = dao.getPeliculaByCriteria("titulo like PasamosPrueba");
        dao.direactorProcces(dir, CRUD.DELETE);
        assertTrue(peliculas.isEmpty());    
    }
    
    /**
     * Pruebas unitarias de creación, actualización, busqueda y borrado de 
     * {@code Prestamo}.
     */
    @Test
    public void testPrestamo(){
        VideoDao dao = new VideoHibernate(Prestamo.class);
        Director dir = new Director("Jony");
        dao.direactorProcces(dir, CRUD.CREATE);
        Pelicula peli = new Pelicula("Prueba2", EGenero.TERROR, 102, dir);
        dao.peliculaProcces(peli, CRUD.CREATE);
        
        Socio socio = new Socio("Nombre de prueba2", "Direccion de Prueba2", "098765456789");
        dao.socioProcces(socio, CRUD.CREATE);
        Ficha ficha = new Ficha(new Date(), socio);
        dao.fichaProcces(ficha, CRUD.CREATE);
        Ficha ficha2 = new Ficha(new Date(), socio);
        dao.fichaProcces(ficha2, CRUD.CREATE);
        
        Cinta cinta = new Cinta(10, peli);
        dao.cintaProcces(cinta, CRUD.CREATE);
        Prestamo pres = new Prestamo(new Date(), EEstatusPrestamo.E, ficha, cinta);
        dao.prestamoProcces(pres, CRUD.CREATE);
        List<Prestamo> prestamos = dao.getPrestamosByCriteria(String.format("fichas_id = %s", ficha.getId()));
        assertTrue(!prestamos.isEmpty());
        assertTrue(prestamos.get(0).getFicha().getId().equals(ficha.getId()));
        
        
        Prestamo pres2 = prestamos.get(0);
        pres = dao.getPrestamoById(pres2.getId());
        assertTrue(pres2.getFicha().getId().equals(pres.getFicha().getId()));
        
        pres.setFicha(ficha2);
        dao.prestamoProcces(pres, CRUD.UPDATE);
        pres2 = dao.getPrestamoById(pres.getId());
        assertTrue(pres2.getFicha().getId().equals(ficha2.getId()));
        
        dao.prestamoProcces(pres, CRUD.DELETE);
        prestamos = dao.getPrestamosByCriteria(String.format("fichas_id = %s", ficha.getId()));
        dao.fichaProcces(ficha2, CRUD.DELETE);
        dao.fichaProcces(ficha, CRUD.DELETE);
        dao.cintaProcces(cinta, CRUD.DELETE);
        dao.socioProcces(socio, CRUD.DELETE);
        dao.peliculaProcces(peli, CRUD.DELETE);
        dao.direactorProcces(dir, CRUD.DELETE);
        assertTrue(prestamos.isEmpty());    
    }
    
    /**
     * Pruebas unitarias de creación, actualización, busqueda y borrado de 
     * {@code Socio}.
     */
    @Test
    public void testSocio(){
        VideoDao dao = new VideoHibernate(Socio.class);
        Socio socio = new Socio("Pruebonio", "Pruebitas", "12345");
        dao.socioProcces(socio, CRUD.CREATE);
        List<Socio> socios = dao.getSociosByCriteria("nombre like Pruebonio");
        assertTrue(!socios.isEmpty());
        assertTrue(socios.get(0).getNombre().equals("Pruebonio"));
        
        
        Socio socio2 = socios.get(0);
        socio = dao.getSocioById(socio2.getId());
        assertTrue(socio2.getNombre().equals(socio.getNombre()));
        
        socio.setNombre("Prubita");
        dao.socioProcces(socio, CRUD.UPDATE);
        socio2 = dao.getSocioById(socio.getId());
        assertTrue(socio2.getNombre().equals("Prubita"));
        
        dao.socioProcces(socio, CRUD.DELETE);
        socios = dao.getSociosByCriteria("nombre like Prubita");
        assertTrue(socios.isEmpty());    
    }
    
}
