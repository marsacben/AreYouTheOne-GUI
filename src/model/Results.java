package model;

import java.util.Hashtable;
import java.util.LinkedList;

public class Results {
    LinkedList<Picks> picks;
    LinkedList<Match> matches;
    LinkedList<Boolean> tb;
    LinkedList<Integer> ceremony;
    LinkedList<Hashtable<Integer, LinkedList<Person>>> ruledOut;

    public Results(LinkedList<Picks> picks, LinkedList<Match> matches, LinkedList<Boolean> tb, LinkedList<Integer> ceremony, LinkedList<Hashtable<Integer,LinkedList<Person>>> ruledOut) {
        this.picks = picks;
        this.matches = matches;
        this.tb = tb;
        this.ceremony= ceremony;
        this.ruledOut = ruledOut;
    }

	public Picks getPick(int i) {
		return picks.get(i);
	}

	public Match getMatch(int i) {
		return matches.get(i);
	}

	public Boolean getTruthBooth(int i) {
		return tb.get(i);
	}

	public Integer getCeremony(int i) {
		return ceremony.get(i);
	}
	
	public Hashtable<Integer, LinkedList<Person>> getRuledOut(int i) {
		return ruledOut.get(i);
	}
    
    
}
