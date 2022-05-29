import java.math.BigInteger;

public class FField extends Number{

    /* FField is implemented as a number type. Alternately
    it can store methods, but the rest is unclear.*/

    public BigInteger ff_prime;
    public BigInteger ff_elem;



    // Constructors

    public FField(BigInteger ff_p, BigInteger ff_e) {
        this.ff_prime = ff_p;
        this.ff_elem = ff_e.mod(ff_p);
    }


    /*
    Methods required for extension to Number class.
    All of them have been inherited from the BigInteger
    class implementation.
    */

    @Override
    public int intValue() {
        return ff_elem.intValue();
    }

    @Override
    public float floatValue() {
        return ff_elem.floatValue();
    }

    @Override
    public long longValue() {
        return ff_elem.longValue();
    }

    @Override
    public double doubleValue() {
        return ff_elem.doubleValue();
    }



    // Mathematical operators

    public FField add(FField ff) throws Exception {
        if (this.ff_prime == ff.ff_prime) {
            return new FField(this.ff_prime, this.ff_elem.add(ff.ff_elem));
        }
        else{
            throw new Exception("Primes do not match.");
        }
    }


}
