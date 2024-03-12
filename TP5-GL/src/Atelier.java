import java.util.Date;

public class Atelier {
    private int numAtelier;
    private Date entrée;
    private Date sortie;

    public Atelier(int numAtelier, Date entrée, Date sortie) {
        this.numAtelier = numAtelier;
        this.entrée = entrée;
        this.sortie = sortie;
    }

    public Atelier() {
        this.numAtelier = 0;
        this.entrée = new Date(0);
        this.sortie = new Date(0);
    }

    public int getNumAtelier() {
        return numAtelier;
    }

    public void setNumAtelier(int numAtelier) {
        this.numAtelier = numAtelier;
    }

    public Date getEntrée() {
        return entrée;
    }

    public void setEntrée(Date entrée) {
        this.entrée = entrée;
    }

    public Date getSortie() {
        return sortie;
    }

    public void setSortie(Date sortie) {
        this.sortie = sortie;
    }

    @Override
    public String toString() {
        return "Atelier [numAtelier=" + numAtelier + ", entrée=" + entrée + ", sortie=" + sortie + "]";
    }

    
    
}
