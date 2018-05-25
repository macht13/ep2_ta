package Java;

public class JunctionListe {
    private JunctionNode nil;

    public JunctionListe(){
        this.nil = new JunctionNode();
        this.nil.setNext(this.nil);
        this.nil.setPrev(this.nil);
    }

    public boolean add(Junction junction){
        if (junction == null) {
            return false;
        }
        if (this.nil.getPrev() == this.nil) {
            this.nil.setNext(new JunctionNode());
            this.nil.getNext().setValue(junction);
        }

        JunctionNode last = this.nil.getPrev();
        this.nil.setPrev(new JunctionNode());
        this.nil.getPrev().setValue(junction);
        this.nil.getPrev().setNext(this.nil);
        this.nil.getPrev().setPrev(last);
        last.setNext(this.nil.getPrev());

        return true;
    }

    public JunctionNode getNil() {
        return nil;
    }
}
