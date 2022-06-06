package poly;

import java.math.BigInteger;

public interface Poly {
    public Boolean isConstant();
    public Boolean isUnivariate();
    public BigInteger evaluate();
}