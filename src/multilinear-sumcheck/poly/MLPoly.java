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

    public Tuple<ArrayList<Boolean>, BigInteger>[] terms;


    // Constructors

    public MLPoly(Integer var_no, Field field, Tuple<ArrayList<Boolean>, BigInteger>[]  terms) {
        this.varNo = var_no;
        this.field = field;
        this.terms = terms;
    }

    public MLPoly(Field field, Tuple<ArrayList<Boolean>, BigInteger>[]  terms) {
        this.varNo = terms[0].x.size();
        this.field = field;
        this.terms = terms;
    }


    // Identity checks

    public Boolean is_monomial() {
        return (this.terms.length == 1);
    }

    public Boolean is_constant() {
        return (this.is_monomial()) && !(this.terms[0].x.contains(true));
    }

    
    // Size checks

    public Integer degree() {
        Integer counter1 = 0;

        for (Tuple<ArrayList<Boolean>, BigInteger> i : this.terms) {
            Integer counter2 = 0;
            for (Boolean j : i.x) {
                if (j.equals(true)) {
                    counter2++;
                }
            }
            counter1 = Math.max(counter1, counter2);
        }
        return counter1;
    }

    public Integer size() {
        Integer counter1 = 0;

        for (Tuple<ArrayList<Boolean>, BigInteger> i : this.terms) {
            Integer counter2 = 0;
            for (Boolean j : i.x) {
                if (j.equals(true)) {
                    counter2++;
                }
            }
            counter1 += counter2;
        }
        return counter1;
    }


    // Evaluation

    public BigInteger[] evaluate(BigInteger[] vector) {
        BigInteger[] result = new BigInteger[this.terms.length];
        for (Integer i; i<=)
    }
}

