package pt.ulusofona.lp2.deisichess;

public class Capturas {
    public Peca pecaQueCaptura;
    public Peca pecaCapturada;

    public Capturas(Peca pecaQueCaptura, Peca pecaCapturada) {
      this.pecaQueCaptura=pecaQueCaptura;
      this.pecaCapturada=pecaCapturada;
    }
    public Peca getPecaQueCaptura() {
        return pecaQueCaptura;
    }
    public Peca getPecaCapturada() {
        return pecaCapturada;
    }
}
