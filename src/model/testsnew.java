package model;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class testsnew {
	public void testCorrectPicks(int numPairs, String[][] ans, LinkedList<Picks> picks){
        for(int i=0; i<picks.size(); i++) {
            Picks p = picks.get(i);
            for (int j = 0; j < numPairs; j++) {
                Match m = p.getPair(j);
                Person person = m.getP2();
                String n = person.getName();
                assertEquals(n, ans[i][j]);
            }
        }
    }
	
	@Test
    public void teststraightTrimAlgo(){
        String[][] names = {{"F1","F2","F3", "F4", "F5","F6","F7", "F8", "F9", "F10"},
                {"M1","M2","M3","M4", "M5","M6","M7","M8","M9","M10"}};
        LinkedList<Integer> order = new LinkedList<>();
        order.add(10);
        order.add(9);
        order.add(8);
        order.add(7);
        order.add(6);
        order.add(5);
        order.add(4);
        order.add(3);
        order.add(2);
        order.add(1);
        boolean[][] gender= {{false,false,false,false,false,false,false,false,false,false},
                {true,true,true,true,true,true,true,true,true,true}};
        String[][] ans = {{"M10","M9","M8","M7", "M6","M5","M4","M3","M2","M1"}};
        Season s = new Season(names, gender, order);
        LinkedList<Person>[] contestants = s.getContestantsSplit();
        System.out.println("group1:" + contestants[0]);
        System.out.println("group2:" + contestants[1]);
        MiniMax util = new MiniMax(contestants[0], contestants[1]);
        LinkedList<Picks> picks = new LinkedList<>();
        int beams =0;
        int i=1;
        while(beams != 10){
            System.out.println("Round " + (i));
            Match m = util.getTruthBooth();
            boolean resp = s.truthBoth(m);
            util.recordTruthBooth(resp);
            System.out.println("TruthBooth: " + m.toString() + " ans: " + resp);
            Picks p = util.getCeremony();
            picks.add(p);
            System.out.println("Pick: " + p.toString());
            beams = s.ceremony(p);
            System.out.println("beams: " + beams);
            util.recordCeremony(beams);
            System.out.println("------");
            i++;
        }
        LinkedList<Picks> last = new LinkedList<>();
        last.add(picks.getLast());
        //System.out.println(last.add(picks.getLast()).toSt);
        testCorrectPicks(10,ans,last);
    }

}
