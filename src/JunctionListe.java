public class JunctionListe {
    private Junction nil;

    public JunctionListe(){
        this.nil = new Junction();
        this.nil.setNext(this.nil);
        this.nil.setPrev(this.nil);
    }

    public void add(Junction junction){
        if (this.nil.getPrev() == this.nil) {
            this.nil.setNext(junction);
        }

        Junction last = this.nil.getPrev();
        this.nil.setPrev(junction);
        junction.setNext(this.nil);
        junction.setPrev(last);
        last.setNext(junction);
    }

    public Junction getNil() {
        return nil;
    }
}
