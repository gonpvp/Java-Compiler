package fr.belinguier.java.access;

import java.util.ArrayList;

/**
 * @author Eliott Belinguier
 */
public enum InnerClassAccessFlag {

    PUBLIC((short) 0x0001),
    PRIVATE((short) 0x0002),
    PROTECTED((short) 0x0004),
    STATIC((short) 0x0008),
    FINAL((short) 0x0010),
    INTERFACE((short) 0x0200),
    ABSTRACT((short) 0x0400),
    SYNTHETIC((short) 0x1000),
    ANNOTATION((short) 0x2000),
    ENUM((short) 0x4000);

    private final short value;

    InnerClassAccessFlag(short value) {
        this.value = value;
    }

    public short getValue() {
        return this.value;
    }

    public static short toValue(InnerClassAccessFlag... innerClassAccessFlags) {
        short result;

        if (innerClassAccessFlags == null)
            return 0;
        result = 0;
        for (InnerClassAccessFlag innerClassAccessFlag : innerClassAccessFlags)
            result |= innerClassAccessFlag.value;
        return result;
    }

    public static InnerClassAccessFlag[] getFromValue(short value) {
        ArrayList<InnerClassAccessFlag> result = new ArrayList<InnerClassAccessFlag>();

        for (InnerClassAccessFlag innerClassAccessFlag : InnerClassAccessFlag.values())
            if ((value & innerClassAccessFlag.getValue()) != 0)
                result.add(innerClassAccessFlag);
        if (result.size() < 1)
            return null;
        return result.toArray(new InnerClassAccessFlag[result.size()]);
    }
}
