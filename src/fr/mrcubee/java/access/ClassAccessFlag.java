package fr.mrcubee.java.access;

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
}
