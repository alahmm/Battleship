package battelship;

public class Ship {
    private int startOfIndexI;
    private int startOfIndexJ;
    private int endOfIndexI;
    private int endOfIndexJ;
    private int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getEndOfIndexI() {
        return endOfIndexI;
    }

    public int getEndOfIndexJ() {
        return endOfIndexJ;
    }

    public int getStartOfIndexI() {
        return startOfIndexI;
    }

    public int getStartOfIndexJ() {
        return startOfIndexJ;
    }

    public void setEndOfIndexI(int endOfIndexI) {
        this.endOfIndexI = endOfIndexI;
    }

    public void setStartOfIndexI(int startOfIndexI) {
        this.startOfIndexI = startOfIndexI;
    }

    public void setStartOfIndexJ(int startOfIndexJ) {
        this.startOfIndexJ = startOfIndexJ;
    }

    public void setEndOfIndexJ(int endOfIndexJ) {
        this.endOfIndexJ = endOfIndexJ;
    }
    public Ship (int startOfIndexI, int startOfIndexJ, int endOfIndexI, int endOfIndexJ, int length) {
        this.endOfIndexI = endOfIndexI;
        this.endOfIndexJ = endOfIndexJ;
        this.startOfIndexI = startOfIndexI;
        this.startOfIndexJ = startOfIndexJ;
        this.length = length;
    }
}
