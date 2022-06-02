import java.math.BigInteger;
import java.util.Vector;

import fields.Field;

public class UPoly implements Poly {


    // Public parameters

    public Field field;

    
    /* Term representation: ith bit of the n-bit vector represents
     * the coefficient of x^n. */

    public Vector<BigInteger> terms;


    // Constructors

    public UPoly(Field field, Vector<BigInteger> terms) {
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

}