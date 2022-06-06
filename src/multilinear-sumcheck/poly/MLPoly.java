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

    // Evaluations
    
    public BigInteger evaluate(BigInteger[] vector) {
        // terrible O(n^2), unoptimized evaluation

        BigInteger result = new BigInteger("0");
        for (Tuple<ArrayList<Boolean>, BigInteger> i : this.terms) {
            BigInteger prod = new BigInteger("0");
            for (Integer j = 0; j < vector.length; j++) {
                prod = this.field.multiply(prod, this.field.bool_mult(vector[j], i.x.get(j)));
            }
            result = this.field.add(result, prod);
        }
        return result;
    }
}

