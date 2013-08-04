package org.apache.cayenne.testdo.testmap.auto;

import java.math.BigInteger;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

/**
 * Class _BigIntegerEntity was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _BigIntegerEntity extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    @Deprecated
    public static final String BIG_INTEGER_FIELD_PROPERTY = "bigIntegerField";

    public static final String ID_PK_COLUMN = "ID";

    public static final Property<BigInteger> BIG_INTEGER_FIELD = new Property<BigInteger>("bigIntegerField");

    public void setBigIntegerField(BigInteger bigIntegerField) {
        writeProperty("bigIntegerField", bigIntegerField);
    }
    public BigInteger getBigIntegerField() {
        return (BigInteger)readProperty("bigIntegerField");
    }

}