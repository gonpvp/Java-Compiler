package fr.belinguier.java.access;

import java.util.ArrayList;

/**
 * @author Eliott Belinguier
 */
public enum MethodAccessFlag {

    PUBLIC((short) 0x0001),
    PRIVATE((short) 0x0002),
    PROTECTED((short) 0x0004),
    STATIC((short) 0x0008),
    FINAL((short) 0x0010),
    SYNCHRONIZED((short) 0x0020),
    BRIDGE((short) 0x0040),
    VARARGS((short) 0x0080),
    NATIVE((short) 0x0100),
    ABSTRACT((short) 0x0400),
    STRICT((short) 0x0800),
    SYNTHETIC((short) 0x1000);

    private final short value;

    MethodAccessFlag(short value) {
        this.value = value;
    }

    public short getValue() {
        return this.value;
    }

    public static short toValue(MethodAccessFlag... methodAccessFlags) {
        short result;

        if (methodAccessFlags == null)
            return 0;
        result = 0;
        for (MethodAccessFlag methodAccessFlag : methodAccessFlags)
            result |= methodAccessFlag.value;
        return result;
    }

    public static MethodAccessFlag[] getFromValue(short value) {
        ArrayList<MethodAccessFlag> result = new ArrayList<MethodAccessFlag>();

        for (MethodAccessFlag methodAccessFlag : MethodAccessFlag.values())
            if ((value & methodAccessFlag.getValue()) != 0)
                result.add(methodAccessFlag);
        if (result.size() < 1)
            return null;
        return result.toArray(new MethodAccessFlag[result.size()]);
    }
}
