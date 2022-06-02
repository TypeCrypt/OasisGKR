package poly;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.IntStream;

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

}