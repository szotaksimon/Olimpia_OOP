package hu.petrik;

public class Versenyzo {

    private String nev;
    private String ido;

    public Versenyzo(String nev, String ido) {
        this.nev = nev;
        this.ido = ido;
    }

    public int getMP(){
        String[] st = this.ido.split(":");
        return Integer.parseInt(st[0]) * 60 + Integer.parseInt(st[1]);
    }

    public String getNev() {
        return nev;
    }

    public String getIdo() {
        return ido;
    }

    @Override
    public String toString() {
        return nev + ": " + this.getMP();
    }
}
