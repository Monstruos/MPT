package io.github.monstruos.MPT2.editors;

public class BasedEditor implements Editor {
    private int base;

    @Override
    public void clear() {

    }

    @Override
    public void backspace() {

    }

    @Override
    public void addSeparator() {

    }

    @Override
    public void addDigit(int digit) {

    }

    @Override
    public void setOperation(SupportedOperation operation) {

    }

    @Override
    public void applyFunction(SupportedFunction function) {

    }

    @Override
    public void reset() {

    }

    @Override
    public void changeSign() {

    }

    @Override
    public void execute() {

    }

    @Override
    public void memoryClear() {

    }

    @Override
    public void memoryRead() {

    }

    @Override
    public void memorySave() {

    }

    @Override
    public void memoryAdd() {

    }

    @Override
    public boolean isMemoryEnabled() {
        return true;
    }

    @Override
    public String getCurrentOperand() {
        return null;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }
}
