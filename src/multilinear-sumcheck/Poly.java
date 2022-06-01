import java.math.BigInteger;

public interface Poly {

    public Integer degree();
    public Integer psize();
    public Boolean isconstant();
    public BigInteger evaluate(BigInteger a);
}