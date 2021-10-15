package model;

import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;

public class SecretMatchMaker {
    private Hashtable<Person, Person> perfectMatches;

    public SecretMatchMaker() {
        perfectMatches = new Hashtable<>();
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

    public LinkedList<Person> makeCast(String[][] names, boolean[][] gender){
        LinkedList<Person> contestants = new LinkedList<>();
        for(int i=0; i< names.length; i++){
        	System.out.println(names.length);
        	System.out.println(names[i][0]);
        	System.out.println(names[i][1]);
        	System.out.println(gender[i][0]);
        	System.out.println(gender[i][1]);
            Person a = new Person(names[i][0], gender[i][0]);
            Person b = new Person(names[i][1], gender[i][1]);
            perfectMatches.put(a, b);
            contestants.add(a);
            contestants.add(b);
        }
        Collections.shuffle(contestants);
        return contestants;
    }
    public LinkedList<Person> makeCastNoShuffle(String[][] names, boolean[][] gender, LinkedList<Integer> order){
        LinkedList<Person> contestants = new LinkedList<>();
        LinkedList<Person> contestants1 = new LinkedList<>();
        LinkedList<Person> contestants2 = new LinkedList<>();
        for(int i=0; i< names[0].length; i++){
            Person a = new Person(names[0][i], gender[0][i]);
            Person b = new Person(names[1][i], gender[1][i]);
            contestants1.add(a);
            contestants2.add(b);
        }
        contestants.addAll(contestants1);
        contestants.addAll(contestants2);
        for(int i=0; i< contestants1.size(); i++){
           // System.out.println("i= " +i+"  m= "+ contestants1.get(i).getName() + "  f= " + contestants2.get(order.get(i)-1).getName() + "  order="+ order.get(i));
            perfectMatches.put(contestants1.get(i), contestants2.get(order.get(i)-1));
        }
        return contestants;
    }

    public LinkedList<Person> makeCast(String[][] names){
        LinkedList<Person> contestants = new LinkedList<>();
        System.out.println("make cast");
        for(int i=0; i< names.length; i++){
        	System.out.println(names[i][0]);
        	System.out.println(names[i][1]);
            Person a = new Person(names[i][0]);
            Person b = new Person(names[i][1]);
            perfectMatches.put(a, b);
            contestants.add(a);
            contestants.add(b);
        }
        Collections.shuffle(contestants);
        return contestants;
    }
}
