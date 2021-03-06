package fr.belinguier.java.access;

import java.util.ArrayList;

/**
 * @author Eliott Belinguier
 */
public enum ClassAccessFlag {

    PUBLIC((short) 0x0001),
    FINAL((short) 0x0010),
    SUPER((short) 0x0020),
    INTERFACE((short) 0x0200),
    ABSTRACT((short) 0x0400),
    SYNTHETIC((short) 0x1000),
    ANNOTATION((short) 0x2000),
    ENUM((short) 0x4000);

    private final short value;

    ClassAccessFlag(short value) {
        this.value = value;
    }

    public short getValue() {
        return this.value;
    }

    public static short toValue(ClassAccessFlag... classAccessFlags) {
        short result;

        if (classAccessFlags == null)
            return 0;
        result = 0;
        for (ClassAccessFlag classAccessFlag : classAccessFlags)
            result |= classAccessFlag.value;
        return result;
    }

    public static ClassAccessFlag[] getFromValue(short value) {
        ArrayList<ClassAccessFlag> result = new ArrayList<ClassAccessFlag>();

        for (ClassAccessFlag classAccessFlag : ClassAccessFlag.values())
            if ((value & classAccessFlag.getValue()) != 0)
                result.add(classAccessFlag);
        if (result.size() < 1)
            return null;
        return result.toArray(new ClassAccessFlag[result.size()]);
    }
}
