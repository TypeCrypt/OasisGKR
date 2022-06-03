package poly;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.IntStream;

import exceptions.FieldOrderException;
import fields.Field;

public class UPoly implements Poly {


    // Public parameters

    public Field field;

    
    /* Term representation: ith bit of the n-bit ArrayList represents
     * the coefficient of x^n. */

    public ArrayList<BigInteger> terms;


    // Constructors

    public UPoly(Field field, ArrayList<BigInteger> terms) {
        this.field = field;
        this.terms = terms;
    }


    // Identity Checks

    public Boolean is_constant() {
        return (this.terms.size() == 1);
    }


    // Size Checks

    public Integer degree() {
        return this.terms.size()-1;
    }

    public Integer size() {
        Integer counter = 0;
        for (BigInteger elem : this.terms) {
            if (elem != field.zero) {
                counter++;
            }
        }
        return counter;
    }

    
    // Evaluation
    
    public BigInteger evaluate(BigInteger b) {
        return IntStream.range(0, this.degree())
                        .mapToObj(i -> this.field.multiply(this.field.power(b, i), this.terms.get(i)))
                        .reduce(this.field.zero, BigInteger::add);
    }


    // Operations on univariate polynomials

    public UPoly add(UPoly u) throws FieldOrderException{
        if (this.field.equals(u.field)) {

            // Find which polynomial has greater degree
            UPoly min;
            UPoly max;
            if (u.degree() >= this.degree()) {min = this; max = u;} else {min = u; max = this;}

            // Initialize output term
            ArrayList<BigInteger> addList = new ArrayList<BigInteger>();

            // Run through addition
            for (Integer i = 0; i <= min.degree(); i++) {
                addList.add(this.field.add(u.terms.get(i), this.terms.get(i)));
            }
            for (Integer i=min.degree()+1; i <= max.degree(); i++) {
                addList.add(max.terms.get(i));
            }

            // Initialize new UPoly to return
            return new UPoly(this.field, addList);
        }
        
        else {
            throw new FieldOrderException("Polynomials defined over different fields!");
        }
    }

    public UPoly multiply(UPoly u) throws FieldOrderException {
        if (this.field.equals(u.field)){
            // Implement FFT
        }
        else {
            throw new FieldOrderException("Polynomials defined over different fields!");
        }
    }

}