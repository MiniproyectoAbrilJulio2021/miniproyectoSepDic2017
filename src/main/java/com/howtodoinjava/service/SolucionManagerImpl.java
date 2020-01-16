/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.service;

import com.howtodoinjava.dao.SolucionDAO;
import com.howtodoinjava.entity.Solucion;
import com.howtodoinjava.lambdacalculo.PasoInferencia;
import com.howtodoinjava.lambdacalculo.Term;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SerializationUtils;

/**
 *
 * @author miguel
 */
@Service
public class SolucionManagerImpl implements SolucionManager {
       
    @Autowired
    private SolucionDAO solucionDAO;
    
    
    @Override
    @Transactional
    public void addSolucion(Solucion sol){        
        sol.setArregloSerializado(SerializationUtils.serialize(sol.getTypedTerm()));
        solucionDAO.addSolucion(sol);
    }
    
    
    
    /*@Override
    @Transactional
    public void addPaso(int solucionId, PasoInferencia paso){
        Solucion sol = solucionDAO.getSolucion(solucionId);
        sol.addArregloInferencias(paso);
        solucionDAO.updateSolucion(sol);
    }*/
    
    @Override
    @Transactional
    public void updateSolucion(int solucionId, Term typedTerm){
        Solucion sol = solucionDAO.getSolucion(solucionId);
        sol.setTypedTerm(typedTerm);
        sol.setArregloSerializado(SerializationUtils.serialize(typedTerm));
        solucionDAO.updateSolucion(sol);
    }
    
    @Override
    @Transactional
    public void updateSolucion(Solucion sol){
        sol.setArregloSerializado(SerializationUtils.serialize(sol.getTypedTerm()));
        solucionDAO.updateSolucion(sol);
    }
    
    @Override
    @Transactional
    public void deleteSolucion(int id){
        solucionDAO.deleteSolucion(id);
    }
    
    
    @Override
    @Transactional
    public Solucion getSolucion(int id){
        Solucion solucion = solucionDAO.getSolucion(id);
        solucion.deserialize();
        return solucion;
    }
    
    
    @Override
    @Transactional
    public List<Solucion> getAllSolucionesByResuelve(int resuelveId){
        List<Solucion> sols = solucionDAO.getAllSolucionesByResuelve(resuelveId);
        for (Solucion sol: sols)
            sol.deserialize();
        return sols;
    }
    
    
    @Override
    @Transactional
    public HashMap<String,Integer> getAllSolucionesIdByResuelve(int resuelveId){
           
        HashMap<String, Integer> listaSoluciones = new HashMap();
        
        List<Integer> idSoluciones = solucionDAO.getAllSolucionesIdByResuelve(resuelveId); 
        String nombreSolucion;
        int i = 1;
        for (int id : idSoluciones){
            
            nombreSolucion = "Proof " + i;
            listaSoluciones.put(nombreSolucion,id);
            i++;
        }
        
        return listaSoluciones;
    
    };
}
