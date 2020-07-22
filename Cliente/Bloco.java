public class Bloco {
    int quem = 0;
    int pos = 0;

    Bloco(int a, int b) {
        this.quem = a;
        this.pos = b;    
    }

    public String toString() {
        return "Quem: " + this.quem + " Pos: " + this.pos;
    }
}