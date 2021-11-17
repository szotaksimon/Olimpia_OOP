package hu.petrik;

import java.awt.image.DataBufferDouble;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Feladatok {
    private HashMap<String, ArrayList<Versenyzo>> adatok;

    public Feladatok(){
        this.adatok = new HashMap<>();
        Beolvasas();
        System.out.println("A sportágak száma: " + adatok.size());
        halmazResztvevok();
        idoSzerintiResztvevok();
        legtobbGyoztes();
        rendezesNevsorSzerint();
        kiir();

    }

    private void Beolvasas(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("eredmenyek.txt"));
            String sor = br.readLine();
            while (sor != null){
                String [] st = sor.split(" ");
                String sportag = st[0];
                String ido = st[1];
                String nev = st[2] + " " + st[3];
                Versenyzo v = new Versenyzo(nev, ido);
                adatok.putIfAbsent(sportag, new ArrayList<>());
                adatok.put(sportag, adatok.get(sportag)).add(v);

                sor = br.readLine();
            }
            br.close();

        }catch (IOException e){
            e.getMessage();
        }
    }

    private void kiir(){
        for(Map.Entry<String, ArrayList<Versenyzo>> entry : adatok.entrySet()){
            System.out.println(entry.getKey() + ": ");
            for(Versenyzo item : entry.getValue()){
                System.out.println("\t" + item);
            }
            System.out.println();
        }
    }

    //halmaz: a halmazvan az elemek csak egyszer szerepelhetnek

    private void halmazResztvevok(){
        //HashSet<String> nevek = new HashSet<>();
        TreeSet<String> nevek = new TreeSet<>();
        for(Map.Entry<String, ArrayList<Versenyzo>> entry : adatok.entrySet()){
            for(Versenyzo item : entry.getValue()){
                nevek.add(item.getNev());
            }
        }
        System.out.println("A versenyzők: ");
        for (String item : nevek){
            System.out.println(item);
        }
    }

    private void idoSzerintiResztvevok(){
        for(Map.Entry<String, ArrayList<Versenyzo>> entry : adatok.entrySet()){
            entry.getValue().sort(new IdoSzerintiComparator());
        }
    }

    private void legtobbGyoztes(){
        HashMap<String, Integer> gyoztesek = new HashMap<>();
        for(Map.Entry<String, ArrayList<Versenyzo>> entry : adatok.entrySet()){
            String nev = entry.getValue().get(0).getNev();
            gyoztesek.putIfAbsent(nev, 0);
            gyoztesek.put(nev, gyoztesek.get(nev) + 1);
        }
        //alaktisuk át a HashMapet listává
        ArrayList<Map.Entry<String, Integer>> ee = new ArrayList<>(gyoztesek.entrySet());
        //ee.sort(new HashMapComparator());
        /*Collections.sort(ee, Map.Entry.comparingByValue(Comparator.reverseOrder()));
        for(Map.Entry<String, Integer> entry : ee){
            System.out.println(entry);
        }*/

        System.out.println("A győztes: " + ee.get(0));
    }

    private void rendezesNevsorSzerint(){
        for(Map.Entry<String, ArrayList<Versenyzo>> entry : adatok.entrySet()){
            entry.getValue().sort(new NevsorSzerintiComparator());
        }
    }
}
