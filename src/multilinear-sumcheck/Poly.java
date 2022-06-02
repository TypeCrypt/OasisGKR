import java.math.BigInteger;

public interface Poly {
    public Integer degree();
    public Integer size();
    public Boolean is_constant();
    public BigInteger evaluate(BigInteger a);
}