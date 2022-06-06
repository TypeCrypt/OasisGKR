package poly;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class MLPoly implements Poly {

    // Initial Parameters
    public Integer varNo;
    public BigInteger prime;
    public HashMap<String, BigInteger> terms;


    // Constructor
    public MLPoly(Integer v, BigInteger p, HashMap<String, BigInteger> t) throws Exception {

        // Check if terms are correctly defined
        for (String i : t.keySet()) {
            if (i.length() > varNo) {
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
            HashMap<String, BigInteger> newMap = new HashMap<String, BigInteger>();
            newMap.putAll(this.terms);

            // Add elements
            for (Map.Entry<String, BigInteger> e : m.terms.entrySet()) {
                String key = e.getKey();
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
        HashMap<String, BigInteger> newMap = new HashMap<String, BigInteger>();
        
        // Invert elements and put
        for (Map.Entry<String, BigInteger> e : this.terms.entrySet()) {
            newMap.put(e.getKey(), e.getValue().negate());
        }

        return new MLPoly(this.varNo, this.prime, newMap);
    }

    // Subtraction
    public MLPoly sub(MLPoly m) throws Exception {
        return this.add(m.addInv());
    }

    // Multiplication
    public MLPoly multiply(MLPoly m) throws Exception {
        if (!this.fieldCheck(m)) {
            throw new Exception("Polynomials defined over different fields!");
        }
        else if (!this.varNoCheck(m)) {
            throw new Exception("Polynomials have different variable numbers!");
        } else {

            // Init new HashMap
            HashMap<String, BigInteger> newMap = new HashMap<String, BigInteger>();

            // Multiply elements
            for (Map.Entry<String, BigInteger> term1 : this.terms.entrySet()) {
                for (Map.Entry<String, BigInteger> term2 : m.terms.entrySet()) {
                    String multTerm = multiply_monomial(term1.getKey(), term2.getKey());

                    if (multTerm == null) { throw new Exception("Multiplication does not yield a multilinear polynomial!"); }
                    BigInteger coeff = term1.getValue().multiply(term2.getValue()).mod(this.prime);
                    
                    if (newMap.containsKey(multTerm)) { BigInteger oldCoeff = newMap.get(multTerm); newMap.put(multTerm, oldCoeff.add(coeff).mod(this.prime)); }
                    else { newMap.put(multTerm, coeff); }
                }
            }

            // Return new MLPoly
            return new MLPoly(this.varNo, this.prime, newMap);
        }
    }

    // Multiply two monomials, returns null if resulting terms is not multilinear
    private String multiply_monomial(String term1, String term2) {
        String newTerm = "";
        for (int i = 0; i < term1.length(); i++) {
            if (term1.charAt(i) == '1' && term2.charAt(i) == '1') { return null; }
            else { newTerm += (term1.charAt(i) == '1' || term2.charAt(i) == '1') ? "1" : "0"; }
        }
        return newTerm;
    }

    // Evaluation
    public BigInteger evaluate(BigInteger[] b) throws Exception {

        // Check varNo
        if (this.varNo != b.length) {
            throw new Exception("Input array does not match number of variables!");
        }

        // Evaluate at point
        else {

            // Define counters to iterate over
            BigInteger zero = new BigInteger("0");
            BigInteger unit = new BigInteger("1");

            // First iteration over summation
            for (Map.Entry<String, BigInteger> e : this.terms.entrySet()) {
                String key = e.getKey();
                BigInteger value = e.getValue();

                // Second iteration over multiplication
                for (int i = 0; i < key.length(); i++) {
                    if (key.charAt(i) == '1') {
                        unit = unit.multiply(b[i]).mod(prime);
                    }
                }

                // Coefficient application
                unit = unit.multiply(value);

                // Sum and reset
                zero.add(unit);
                unit = new BigInteger("1");
            }

            return zero;
        }
    }

}