package model;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;

public class SecretMatchMaker {
    private Hashtable<Person, Person> perfectMatches;
    //private Hashtable<String, String> perfectMatchesNames;

    public SecretMatchMaker() {
        perfectMatches = new Hashtable<>();
        //perfectMatchesNames = new Hashtable<>();
    }

    @Override
    public String toString() {
        return "SecretMatchMaker{" +
                "perfectMatches=" + perfectMatches +
                '}';
    }

    public int ceremony(Picks p){
        int beams =0;
        //System.out.println(p.size());
        for(int i=0; i<p.size(); i++ ) {
            Match pair = p.getPair(i);
            //System.out.println(i);
            if(isMatch(pair.getP1(), pair.getP2())){
                beams++;
            }
        }
        return beams;
    }

    public boolean isMatch(Person p1, Person p2){
        boolean ismatch = false;
        if (perfectMatches.get(p1) == p2 || perfectMatches.get(p2) == p1) {
            ismatch = true;
        }
        return ismatch;
    }
    
    /*public boolean isMatch(String p1, String p2){
        boolean ismatch = false;
        if (perfectMatchesNames.get(p1).equals(p2) || perfectMatchesNames.get(p2).equals(p1)) {
            ismatch = true;
        }
        return ismatch;
    }*/

    public LinkedList<Person> makeCast(String[][] names, boolean[][] gender){
        LinkedList<Person> contestants = new LinkedList<>();
        for(int i=0; i< names[0].length; i++){
            Person a = new Person(names[0][i], gender[0][i]);
            Person b = new Person(names[1][i], gender[1][i]);
            perfectMatches.put(a, b);
            //perfectMatchesNames.put(names[0][i], names[1][i]);
            //perfectMatchesNames.put(names[1][i], names[0][i]);
            contestants.add(a);
            contestants.add(b);
        }
        Collections.shuffle(contestants);
        return contestants;
    }

    public LinkedList<Person> makeCast(String[][] names){
        LinkedList<Person> contestants = new LinkedList<>();
        for(int i=0; i< names[0].length; i++){
            Person a = new Person(names[0][i]);
            Person b = new Person(names[1][i]);
            perfectMatches.put(a, b);
            //perfectMatchesNames.put(names[0][i], names[1][i]);
            contestants.add(a);
            contestants.add(b);
        }
        Collections.shuffle(contestants);
        return contestants;
    }
}
