/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.uach.videoclub.controllers;

import java.util.ArrayList;
import java.util.List;
import mx.uach.videoclub.dao.VideoDao;
import mx.uach.videoclub.dao.jdbc.VideoDaoJDBC;
import mx.uach.videoclub.modelos.Director;
import spark.Spark;

/**
 *
 * @author ezubia
 */
public class Application {
    public static void main(String[] args) {
        Spark.get("/", (req,res)->{
            VideoDao v = new VideoDaoJDBC();
            String respuesta = " ";
            List<Director> directores = new ArrayList<>();
            directores = v.getDirectoresByCriteria("");
            for (Director director : directores) {
                respuesta = String.format("%s\n %s - %s", respuesta, director.getId(), director.getNombre());   
            }
            return respuesta;
        });
    }
}
