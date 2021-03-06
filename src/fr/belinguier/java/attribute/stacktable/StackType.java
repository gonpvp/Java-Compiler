package fr.belinguier.java.attribute.stacktable;

/**
 * @author Eliott Belinguier
 */
public enum StackType implements VerificationType {

    TOP((byte) 0),
    INTEGER((byte) 1),
    FLOAT((byte) 2),
    LONG((byte) 4),
    DOUBLE((byte) 3),
    NULL((byte) 5),
    UNINITIALIZED((byte) 6),
    OBJECT((byte) 7),
    UNINITIALIZED_WITH_OFFSET((byte) 8);

    private final byte value;

    StackType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return this.value;
    }

    @Override
    public int sizeOfByteArray() {
        return 1;
    }

    @Override
    public byte[] toByte() {
        return new byte[] {this.value};
    }
}
