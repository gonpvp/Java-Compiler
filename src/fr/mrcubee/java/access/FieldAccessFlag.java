package fr.mrcubee.java.access;

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
}
