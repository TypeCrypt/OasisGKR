package poly;

import java.math.BigInteger;
import java.security.spec.ECGenParameterSpec;
import java.util.HashMap;
import java.util.Map;

import util.Tuple;

public class MLPoly implements Poly {

    // Initial Parameters
    public Integer varNo;
    public BigInteger prime;
    public HashMap<Boolean[], BigInteger> terms;


    // Constructor
    public MLPoly(Integer v, BigInteger p, HashMap<Boolean[], BigInteger> t) throws Exception {

        // Check if terms are correctly defined
        for (Boolean[] i : t.keySet()) {
            if (i.length > varNo) {
                throw new Exception("Incorrect variable number!");
            }
            if (t.get(i).compareTo(p) == 1) {
                throw new Exception("Coefficients have to be smaller than the field size!");
            }
        }

        // Initialize parameters
        this.varNo = v;
        this.prime = p;
        this.terms = t;
    }


    // Constancy checks
    public Boolean isConstant() {
        return (varNo == 0);
    }

    public Boolean isUnivariate() {
        return (varNo == 1);
    }


    // Basic parameters
    public Boolean equals(MLPoly m) {
        return (this.varNo == m.varNo) && (this.prime.equals(m.prime)) && (this.terms.equals(m.terms));
    }

    public Boolean fieldCheck(MLPoly m) {
        return (this.prime.equals(m.prime));
    }

    public Boolean varNoCheck(MLPoly m) {
        return (this.varNo == m.varNo);
    }


    // Operations

    // Addition
    public MLPoly add(MLPoly m) throws Exception {
        if (!this.fieldCheck(m)) {
            throw new Exception("Polynomials defined over different fields!");
        }
        else if (!this.varNoCheck(m)) {
            throw new Exception("Polynomials have different variable numbers!");
        }
        else {

            // Init new HashMap
            HashMap<Boolean[], BigInteger> newMap = new HashMap<Boolean[], BigInteger>();
            newMap.putAll(this.terms);

            // Add elements
            for (Map.Entry<Boolean[], BigInteger> e : m.terms.entrySet()) {
                Boolean[] key = e.getKey();
                BigInteger value = e.getValue();

                if (newMap.containsKey(key)) {
                    newMap.put(key, value.add(this.terms.get(key).mod(prime)));
                }
                else {
                    newMap.put(key, value);
                }
            }            

            // Return new MLPoly
            return new MLPoly(this.varNo, this.prime, newMap);
        }
    }

    // Additive Inverse
    public MLPoly addInv() throws Exception{

        // Init new HashMap
        HashMap<Boolean[], BigInteger> newMap = new HashMap<Boolean[], BigInteger>();
        
        // Invert elements and put
        for (Map.Entry<Boolean[], BigInteger> e : this.terms.entrySet()) {
            newMap.put(e.getKey(), e.getValue().negate());
        }

        return new MLPoly(this.varNo, this.prime, newMap);
    }

    // Subtraction
    public MLPoly sub(MLPoly m) throws Exception {
        return this.add(m.addInv());
    }

    // Multiplication
}
