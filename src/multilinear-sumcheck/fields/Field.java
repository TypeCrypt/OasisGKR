package fields;

import java.math.BigInteger;

import exceptions.DivideByZeroException;

public class Field {


    /* Generic finite field class. Gives the basic
    arithmetic operations and implements some essential
    algorithms required for field arithmetic. Each
    polynomial will have an associated Field. Subclasses
    include PrimeField. */

    public BigInteger characteristic;
    public BigInteger order;

    public final BigInteger unit = new BigInteger("1");
    public final BigInteger zero = new BigInteger("0");


    // Basics

    public Field(BigInteger f_order) {
        this.order = f_order;
    }

    public Boolean equals(Field f) {
        return (this.order == f.order);
    }


    // field arithmetic

    public BigInteger add(BigInteger a, BigInteger b) {
        return (a.add(b)).mod(order);
    }

    public BigInteger multiply(BigInteger a, BigInteger b) {
        return (a.multiply(b)).mod(order);
    }

    public BigInteger divide(BigInteger a, BigInteger b) throws DivideByZeroException {
        if (!b.equals(zero)) {
            return multiply(a, invert(b));
        }
        else{
            throw new DivideByZeroException("Division by 0!");
        }
    }
    
    public BigInteger power(BigInteger a, Integer b) {
        return a.pow(b).mod(order);
    }

    public BigInteger bool_mult(BigInteger a, Boolean b) {
        if (b) {
            return a;
        }
        else {
            return zero;
        }
    }

    public BigInteger invert(BigInteger a) {
        return a.modInverse(order);
    }

    
    // Polynomial Arithmetic

    // public UPoly sum(UPoly u, UPoly v) throws Exception{
    //     if (u.field.equals(v.field)) {
    //         Vector<BigInteger> terms = new Vector<BigInteger>();
    //     }
    // }
}