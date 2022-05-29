import java.math.BigInteger;

public class Field {

    /* Generic finite field class. Gives the basic
    arithmetic operations and implements some essential
    algorithms required for field arithmetic. Each
    polynomial will have an associated Field. Subclasses
    include PrimeField. */

    public BigInteger characteristic;
    public BigInteger order;

    public Field(BigInteger f_order) {
        this.order = f_order;
    }


    // field arithmetic

    public BigInteger add(BigInteger a, BigInteger b) {
        // Placeholder, to be filled in
        return new BigInteger("0");
    }

    public BigInteger subtract(BigInteger a, BigInteger b) {
        // Placeholder, to be filled in
        return new BigInteger("0");
    }

    public BigInteger multiply(BigInteger a, BigInteger b) {
        // Placeholder, to be filled in
        return new BigInteger("0");
    }

    public BigInteger divide(BigInteger a, BigInteger b) {
        return multiply(a, invert(b));
    }

    public BigInteger invert(BigInteger a) {
        // Placeholder, to be filled in
        return new BigInteger("0");
    }

    public BigInteger sqrt(BigInteger a) {
        // implementation of the Tonelli-Shanks algorithm
        // https://en.wikipedia.org/wiki/Tonelli%E2%80%93Shanks_algorithm
        return new BigInteger("0");
    }
}