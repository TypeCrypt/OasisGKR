package poly;

import java.math.BigInteger;
import java.util.ArrayList;

import util.Tuple;
import fields.Field;

public class MLPoly implements Poly {
    

    // Public parameters: variable number and field

    public Integer varNo;
    public Field field;


    /* Term representation: Each term will have an associated
     * binary string of length var_no. The ith bit acts as an 
     * indicator as to whether the ith element is in that 
     * particular term or not. */

    public ArrayList<Tuple<ArrayList<Boolean>, BigInteger>> terms;


    // Constructors

    public MLPoly(Integer var_no, Field field, ArrayList<Tuple<ArrayList<Boolean>, BigInteger>> terms) {
        this.varNo = var_no;
        this.field = field;
        this.terms = terms;
    }

    public MLPoly(Field field, ArrayList<Tuple<ArrayList<Boolean>, BigInteger>> terms) {
        this.varNo = terms.get(0).x.size();
        this.field = field;
        this.terms = terms;
    }


    // Identity checks

    public Boolean is_monomial() {
        return (this.terms.size() == 1);
    }

    public Boolean is_constant() {
        return (this.is_monomial()) && !(this.terms.get(0).x.contains(true));
    }

    
    // Size checks

    public Integer degree() {
        
    }
}

