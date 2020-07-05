package com.howtodoinjava.entity;
// Generated Mar 20, 2017 12:50:11 PM by Hibernate Tools 3.2.1.GA

import com.howtodoinjava.lambdacalculo.App;
import com.howtodoinjava.lambdacalculo.Const;
import com.howtodoinjava.lambdacalculo.PasoInferencia;
import com.howtodoinjava.lambdacalculo.Term;
import com.howtodoinjava.lambdacalculo.TypedA;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import org.springframework.util.SerializationUtils;

/**
 * Solucion generated by hbm2java
 */
public class Solucion implements java.io.Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "solucion_id_seq")
    @SequenceGenerator(name = "solucion_id_seq", sequenceName = "solucion_id_seq")
    private int id;
    //private List<PasoInferencia> arregloInferencias = new ArrayList<PasoInferencia>();
    private Term typedTerm;
    private Resuelve resuelve;
    private boolean resuelto;
    private String demostracion;

    /*public String getNteoinicial() {
        return nteoinicial;
    }

    public void setNteoinicial(String nteoinicial) {
        this.nteoinicial = nteoinicial;
    }*/

    public Solucion() {
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }

    public boolean isResuelto() {
        return resuelto;
    }
    
    public void setTypedTerm(Term typedTerm)
    {
        this.typedTerm = typedTerm;
        this.demostracion = typedTerm.toString();
    }
    
    public Term getTypedTerm()
    {
        return typedTerm;
    }
    
    public String getDemostracion() {
    	return demostracion;
    }
    
    public Solucion(Term typeTerm) {
        this.typedTerm = typeTerm;
    }
    
    public Solucion(Resuelve resuelve, boolean resuelto, Term typeTerm) {
        this.resuelve = resuelve;
        this.resuelto = resuelto;
        this.typedTerm = typeTerm;
        this.demostracion = typeTerm.toString();
    }

   /* public Solucion(PasoInferencia paso) {
        this.arregloInferencias.add(paso);
        this.resuelto = false;
//        this.arregloSerializado = SerializationUtils.serialize(this.arregloInferencias);
        this.serialize();
//        this.deserialize();

    }

    public Solucion(Resuelve resuelve, PasoInferencia paso) {
        this.resuelve = resuelve;
        this.resuelto = false;
        this.arregloInferencias.add(paso);
//        this.arregloSerializado = SerializationUtils.serialize(this.arregloInferencias);
        this.serialize();
//        this.deserialize();

    }
    
    public Solucion(Resuelve resuelve, boolean resuelto, PasoInferencia paso) {
        this.resuelve = resuelve;
        this.resuelto = resuelto;
        this.arregloInferencias.add(paso);
//        this.arregloSerializado = SerializationUtils.serialize(this.arregloInferencias);
        this.serialize();
//        this.deserialize();

    }*/
    

    public void setId(int id) {
        this.id = id;
    }

    /*public void setArregloInferencias(List<PasoInferencia> arregloInferencias) {
        this.arregloInferencias = arregloInferencias;
//        this.arregloSerializado = SerializationUtils.serialize(arregloInferencias);
        this.serialize();
//        this.deserialize();
    }*/

    public void setResuelve(Resuelve resuelve) {
        this.resuelve = resuelve;
    }
    
    public void setDemostracion(String demostracion) {
    	this.demostracion = demostracion;
    }

    public int getId() {
        return id;
    }

    /*public List<PasoInferencia> getArregloInferencias() {
        return arregloInferencias;
    }*/

    public Resuelve getResuelve() {
        return resuelve;
    }

    /*public void addArregloInferencias(PasoInferencia paso) {
        this.deserialize();
        List<PasoInferencia> newArray;// = new ArrayList<PasoInferencia>();
        newArray = getArregloInferencias();
        newArray.add(paso);
        setArregloInferencias(newArray);
        this.serialize();
    }

    public void serialize() {
//        this.arregloInferencias.add(paso);
//        this.arregloSerializado = SerializationUtils.serialize(this.arregloInferencias);
        List<byte[]> newArray = new ArrayList<byte[]>();
        for (PasoInferencia x : this.getArregloInferencias()) {
            newArray.add(SerializationUtils.serialize(x.getExpresion()));
            newArray.add(SerializationUtils.serialize(x.getTeoIzq()));
            newArray.add(SerializationUtils.serialize(x.getTeoDer()));
            newArray.add(SerializationUtils.serialize(x.getLeibniz()));
            newArray.add(SerializationUtils.serialize(x.getInstancia()));
            newArray.add(SerializationUtils.serialize(x.getResult()));            
        }

        this.arregloSerializado = SerializationUtils.serialize(newArray);

    }*/

    public int retrocederPaso(){
    
            /*int tam = this.arregloInferencias.size();
            this.deserialize();
            if(tam>0){
                this.arregloInferencias.remove(tam - 1);
            }
            
            this.serialize();*/
     //       System.out.println(typedTerm.toStringInfFinal());
            if (typedTerm.type() == null){
                typedTerm=null;
                return 0;
            }
            if (typedTerm instanceof App && ((App)typedTerm).p.containTypedA())
            {
                typedTerm = ((App)typedTerm).p;
     //           System.out.println(typedTerm.toStringInfFinal());
     //           System.out.println("2");
                return 2;
            }
            else
            {
                typedTerm = ((App)typedTerm.type()).q;
     //           System.out.println(typedTerm.toStringInfFinal());
     //           System.out.println("1");
                return 1;
            }
    }
}
