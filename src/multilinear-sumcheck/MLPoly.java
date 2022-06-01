import java.math.BigInteger;
import java.util.Vector;

import util.Tuple;

public class MLPoly implements Poly {
    
    public Integer var_no;
    public Field field;
    public Vector<Tuple<String, BigInteger>> terms;

    public MLPoly(Integer var_no, Field field, Vector<Tuple<String, BigInteger>> terms) {
        this.var_no = var_no;
        this.field = field;
        this.terms = terms;
    }
}

