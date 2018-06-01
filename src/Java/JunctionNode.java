package Java;

public class JunctionNode {
    // doppelt verkettete Liste
    private JunctionNode next, prev;
    private Junction value;

    public JunctionNode getNext() {
        return next;
    }

    public JunctionNode getPrev() {
        return prev;
    }

    public void setNext(JunctionNode next) {
        this.next = next;
    }

    public void setPrev(JunctionNode prev) {
        this.prev = prev;
    }

    public Junction getValue() {
        return value;
    }

    public void setValue(Junction value) {
        this.value = value;
    }
}
