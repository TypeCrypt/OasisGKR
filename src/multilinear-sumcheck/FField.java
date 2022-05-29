import java.math.BigInteger;

public class FField extends Number{

    public BigInteger ff_prime;
    public BigInteger ff_elem;

    public FField(BigInteger ff_p, BigInteger ff_e) {
        this.ff_prime = ff_p;
        this.ff_elem = ff_e.mod(ff_p);
    }

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
}
