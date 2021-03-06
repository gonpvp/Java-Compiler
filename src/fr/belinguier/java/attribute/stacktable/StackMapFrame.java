package fr.belinguier.java.attribute.stacktable;

import fr.belinguier.java.compiler.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eliott Belinguier
 */
public class StackMapFrame implements Serializable {

    private final FrameType frameType;
    public short offsetDelta;
    private List<VerificationType> locals;
    private List<VerificationType> stack;

    protected StackMapFrame(FrameType frameType) {
        this.frameType = frameType;
        this.locals = new ArrayList<VerificationType>();
        this.stack = new ArrayList<VerificationType>();
    }

    public List<VerificationType> getLocals() {
        return this.locals;
    }

    public List<VerificationType> getStack() {
        return this.stack;
    }

    public FrameType getFrameType() {
        return this.frameType;
    }



    @Override
    public int sizeOfByteArray() {
        VerificationType verificationType;

        switch (this.frameType) {
            case SAME:
                return 1;
            case SAME_LOCALS_1_STACK_ITEM:
                return 1 + ((this.stack.size() > 0) ? this.stack.get(0).sizeOfByteArray() : 0);
            case SAME_LOCALS_1_STACK_ITEM_EXTENDED:
                return 3 + ((this.stack.size() > 0) ? this.stack.get(0).sizeOfByteArray() : 0);
            case CHOP:
            case SAME_EXTENDED:
                return 3;
            case APPEND:
                return 3;
        }
        return 0;
    }

    @Override
    public byte[] toByte() {
        return null;
    }
}
