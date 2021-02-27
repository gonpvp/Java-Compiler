package fr.belinguier.java.access;

import java.util.ArrayList;

/**
 * @author Eliott Belinguier
 */
public enum FieldAccessFlag {

    PUBLIC((short) 0x0001),
    PRIVATE((short) 0x0002),
    PROTECTED((short) 0x0004),
    STATIC((short) 0x0008),
    FINAL((short) 0x0010),
    VOLATILE((short) 0x0040),
    TRANSIENT((short) 0x0080),
    SYNTHETIC((short) 0x1000),
    ENUM((short) 0x4000);

    private final short value;

    FieldAccessFlag(short value) {
        this.value = value;
    }

    public short getValue() {
        return this.value;
    }

    public static FieldAccessFlag[] getFromValue(short value) {
        ArrayList<FieldAccessFlag> result = new ArrayList<FieldAccessFlag>();

        for (FieldAccessFlag fieldAccessFlag : FieldAccessFlag.values())
            if ((value & fieldAccessFlag.getValue()) != 0)
                result.add(fieldAccessFlag);
        if (result.size() < 1)
            return null;
        return result.toArray(new FieldAccessFlag[result.size()]);
    }
}
