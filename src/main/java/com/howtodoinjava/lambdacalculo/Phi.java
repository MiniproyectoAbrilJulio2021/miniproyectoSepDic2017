/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.howtodoinjava.lambdacalculo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author federico
 */
public class Phi extends Term{
    
    public ListaInd ind;
    
    public Phi(Indice in){
        ind=new ListaInd(in);
    }
    
    public Phi(){
        ind=new ListaInd();
    }

    public boolean occur(Var x)
    {
        return false;
    }
    
    public Term sust(Var x,Term t)
    {
        return this;
    }
    
    public Term type()
    {
        return null;
    }
    
    public boolean containTypedA()
    {
        return false;
    }
    
    public Term leibniz(int z, Term subterm)
   {
       if (this == subterm)
           return new Var(z);
       else
           return this;
   }
    
    public int setAlias(int currentAlia)
    {
        if(alias != null)
        {
            alias = alias+"@"+currentAlia;
            currentAlia++;
        }
        
        return currentAlia;
    }
    
    public Term bracketAbsBD(Var x)
    {
        return new App(new Const("\\Phi_{K}"),this);
    }
    
    public int maxVar()
    {
        return -1;
    }
    
    public Term traducBD()
    {
        return this;
    }
    
    public List<Term> contandotraducBD()
    {
        List<Term> list=new ArrayList<Term>();
        
        return list;
    }
    
    public Tipo esRedex()
    {
        return new Tipo(false,false);
    }
    
    public Tipo esRedexFinal()
    {
        return new Tipo(false,false,false);
    }
    
    public Redex buscarRedexIzq(Term contexto,boolean p)
    {
        return null;
    }
    
    public Redex buscarRedexIzqFinal(Term contexto,boolean p)
    {
        return null;
    }
    
    public Term invBraBD()
    {
        Var x0=new Var(0);
        
        return new Bracket(x0,(new App(this,x0)).kappaIndexado(0,x0));
    }
    
    public Term invBD()
    {
        return this.invBraBD().invBD();
    }
    
    public Term invBDOneStep()
    {
        return this.invBraBD();
    }
    
    public Term bracketAbsSH(Var x)
    {
        return new App(new Const("K"),this);
    }
    
    public String toString(){
        return "\\Phi_{"+ind.toString()+"}";
    }
    
    public String toStringInFin() {
        return "\\Phi_{"+ind.toString()+"}";
    }
    
    public String toStringInfLabeled(int z, Term t, List<String> leibniz, Id id, int nivel){
        String term = "\\cssId{"+id.id+"}{\\class{"+nivel+" terminoClick}{\\Phi_{"+ind.toString()+"}}}";
        leibniz.add(t.leibniz(z, this).toStringInfFinal().replace("\\", "\\\\"));
        id.id++;
        return term;
    }
    
    
    public String toStringInf() {
        return "\\Phi_{"+ind.toString()+"}";
    }
    
    public ToString toStringAbrvV1(ToString toString)
    {
        toString.term=this.toString();
        return toString;
    }
    
    public ToString toStringAbrv(ToString toString)
    {
        toString.term=this.toString();
        return toString;
    }
    
    public ToString toStringInfAbrv(ToString toString)
    {
        toString.term=this.toStringInf();
        return toString;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Phi other = (Phi) obj;
        if (this.ind != other.ind && (this.ind == null || !this.ind.equals(other.ind))) {
            return false;
        }
        return true;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        ListaInd cloneInd=(ListaInd)ind.clone();
        Phi phi=new Phi();
        phi.ind=cloneInd;
        phi.alias=this.alias;
        return phi;
    }

    public Term sustParall(List<Var> Vars, List<Term> varsTerm) {
        return this;
    }

    @Override
    public Term checkApp() {
        return this;
    }

    
}
