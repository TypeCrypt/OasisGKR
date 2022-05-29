import java.math.BigInteger;

public class FField {

    public BigInteger ff_order;

}

class eFField extends Number{

    public FField eff_field;
    public BigInteger eff_num;

    public eFField (BigInteger num, FField ffield) {
        eff_num = num.mod(ffield.ff_order);
        eff_field = ffield;
    }

    @Override
    public int intValue() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double doubleValue() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float floatValue() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long longValue() {
        // TODO Auto-generated method stub
        return 0;
    }
}