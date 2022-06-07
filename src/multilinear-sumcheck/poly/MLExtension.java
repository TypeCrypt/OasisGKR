package poly;

import java.math.BigInteger;

public class MLExtension {

    // Initial Parameters
    public BigInteger[] hypercube;
    public BigInteger prime;
    public int order;

    public static final BigInteger unit = new BigInteger("1");
    public static final BigInteger zero = new BigInteger("0");

    // Constructor
    public MLExtension(BigInteger[] hc, BigInteger p, int o) {
        hypercube = hc;
        prime = p;
        order = o;
    }

    // Evaluation
    public BigInteger evaluate(BigInteger[] r) {

        // Memoized Array
        BigInteger[][] arr = new BigInteger[order][];

        // Initial parameters
        arr[0] = new BigInteger[2];

        arr[0][0] = unit.subtract(r[0]).mod(prime);
        arr[0][1] = r[0];

        for (int i = 1; i < order; i++) {

            // initialize space for memoization
            arr[i] = new BigInteger[2^(i+1)];

            // sift through each layer
            for (int j = 0; j < (2^i); j+=2) {
                arr[i][j] = arr[i-1][j/2].multiply(unit.subtract(r[i])).mod(prime);
                arr[i][j+1] = arr[i-1][j/2].multiply(r[i]).mod(prime);
            }
        }
        
        // Dot product with hypercube
        BigInteger result = zero;
        for (int i = 0; i < (2^order); i++) {
            result = result.add(arr[order-1][i].multiply(hypercube[i])).mod(prime);
        }

        return result;
    }

    // Polynomial Extension: More thought required
    // public MLPoly extend() {
    // }
}