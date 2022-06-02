import java.math.BigInteger;
import java.util.Vector;

import fields.Field;

public class UPoly implements Poly {


    // Public parameters
    public Field field;


    /* Term representation: Each term will have an associated
     * binary string of length var_no. The ith bit acts as an 
     * indicator as to whether the ith element is in that 
     * particular term or not. */

    public Vector<BigInteger> terms;


}