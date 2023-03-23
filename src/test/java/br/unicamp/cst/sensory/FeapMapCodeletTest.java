
/***********************************************************************************************
 * Copyright (c) 2012  DCA-FEEC-UNICAMP
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Contributors:
 * K. Raizer, A. L. O. Paraense, E. M. Froes, R. R. Gudwin - initial API and implementation
 ***********************************************************************************************/
package br.unicamp.cst.sensory;

import br.unicamp.cst.core.entities.Codelet;
import br.unicamp.cst.core.entities.MemoryObject;
import br.unicamp.cst.core.entities.Mind;

import br.unicamp.cst.support.TimeStamp;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Test for Codelet implementation of Feature Maps generated by the Attentional System of 
 * Conscious Attention-Based Integrated Model (CONAIM). The maps, from a bottom-
 * up perspective, provide information that present saliences in the state to 
 * which attention should be oriented to and that, if attended, will enhace the
 * corresponding region in the attentional map for a certaiin time and inhibit
 * it in the sequence (inhibition of return). From a top-down perspective, 
 * depending on the system goal and on the attentional dynamic current state 
 * (orienting, selecting or sustaining), voluntary attention can be directed to
 * a region of space or object in two ways: by deliberative enhancing a region
 * in the attentional map or by adjusting the weights that define the 
 * contribution of each feature dimension.
 * 
 * @author L. L. Rossi (leolellisr)
 * @see Codelet
 * @see MemoryObject
 * @see SensorBufferCodelet
 */
public class FeapMapCodeletTest {

    // This class contains tests covering some core Codelet methods
    
     public MemoryObject source;
    public MemoryObject destination;
    
    public FeapMapCodeletTest() {
        Mind testMind = new Mind();
        source = testMind.createMemoryObject("SOURCE");
        //source.setI(0);
        destination = testMind.createMemoryObject("DESTINATION");
        destination.setI(new ArrayList<Float>());
        CopyOnWriteArrayList<String> FMnames = new CopyOnWriteArrayList<>();
        FMnames.add("SOURCE");
        FMnames.add("SOURCE2");
        FMnames.add("SOURCE3");
        BottomUpFM testFeapMapCodelet = new BottomUpFM(3, FMnames, "DESTINATION", 100, 16, 1, 16, 4, 3, 2, false);
        testMind.insertCodelet(testFeapMapCodelet);
        testFeapMapCodelet.addInput(source);
        testFeapMapCodelet.addOutput(destination);
        testFeapMapCodelet.setIsMemoryObserver(true);
	source.addMemoryObserver(testFeapMapCodelet);
        testMind.start();
        
        
        //List fulllist = (List)destination.getI();
        
        
    }
    
    @Test
    public void testFeapMapCodelet() {
        FeapMapCodeletTest test = new FeapMapCodeletTest();
        //for (int i=0;i<64;i++) {
            System.out.println("Testing ... ");
            long oldtimestamp = test.destination.getTimestamp();
            System.out.println("Timestamp before: "+TimeStamp.getStringTimeStamp(oldtimestamp, "dd/MM/yyyy HH:mm:ss.SSS"));
            
            ArrayList<MemoryObject> mo_arrList = new ArrayList<MemoryObject>();
            MemoryObject source_arrList = new MemoryObject();
            
            // Test 1
            ArrayList<Float> int_arrList = new ArrayList<Float>(256);
            for (int i = 0; i < 256*3; i++) {
                int_arrList.add((float)(i % 3) + 1);
            }
            ArrayList<Float> ass_arrList = new ArrayList<Float>(16);
            for (int i = 0; i < 15; i++) {
                ass_arrList.add((float) 3/256);
            }
            ass_arrList.add((float) 0.0);
                    
            source_arrList.setI(int_arrList);
            mo_arrList.add(source_arrList);
            test.source.setI(mo_arrList);
            
            long newtimestamp = test.destination.getTimestamp();
            while(newtimestamp == oldtimestamp) {
                newtimestamp = test.destination.getTimestamp();
                System.out.println("Timestamp after: "+TimeStamp.getStringTimeStamp(newtimestamp,"dd/MM/yyyy HH:mm:ss.SSS"));
            }
            
            System.out.println("\n   Input 1: "+test.source.getI());
            
            
            System.out.print("\n   Output 1: "+ test.destination.getI());
            List fulllist = (List) test.destination.getI();
            if (fulllist != null && fulllist.size() > 0) {
                //printList(fulllist);
                System.out.println("          sizef: "+((List)(test.destination.getI())).size()+"\n");
                
                
                assertEquals(fulllist.size(),16);
                assertEquals(fulllist,ass_arrList);
                
            }
            
            // Test 2
            oldtimestamp = test.destination.getTimestamp();
            System.out.println("Timestamp before: "+TimeStamp.getStringTimeStamp(oldtimestamp, "dd/MM/yyyy HH:mm:ss.SSS"));
            
            int_arrList = new ArrayList<Float>(256);
            for (int i = 0; i < 256; i++) {
                int_arrList.add((float)(i % 3) + 1);
            }
            ass_arrList = new ArrayList<Float>(16);
            for (int i = 0; i < 4; i++) {
                ass_arrList.add((float) 1.0);
            }
            ass_arrList.add((float) 0.50390625);
            for (int i = 0; i < 11; i++) {
                ass_arrList.add((float) 0.0);
            }
            
                    
            source_arrList.setI(int_arrList);
            mo_arrList.add(source_arrList);
            test.source.setI(mo_arrList);
            
            newtimestamp = test.destination.getTimestamp();
            while(newtimestamp == oldtimestamp) {
                newtimestamp = test.destination.getTimestamp();
                System.out.println("Timestamp after: "+TimeStamp.getStringTimeStamp(newtimestamp,"dd/MM/yyyy HH:mm:ss.SSS"));
            }
            
            System.out.println("\n \n   Input 2: "+test.source.getI());
            
            
            System.out.print("\n   Output 2: "+ test.destination.getI());
            fulllist = (List) test.destination.getI();
            if (fulllist != null && fulllist.size() > 0) {
                //printList(fulllist);
                System.out.println("          sizef: "+((List)(test.destination.getI())).size()+"\n");
                
                
                assertEquals(fulllist.size(),16);
                assertEquals(fulllist,ass_arrList);
                
            }
            
            // Test 3
            oldtimestamp = test.destination.getTimestamp();
            System.out.println("Timestamp before: "+TimeStamp.getStringTimeStamp(oldtimestamp, "dd/MM/yyyy HH:mm:ss.SSS"));
            
            int_arrList = new ArrayList<Float>(256);
            for (int i = 0; i < 256; i++) {
                int_arrList.add((float) 0);
            }
            ass_arrList = new ArrayList<Float>(16);
            for (int i = 0; i < 16; i++) {
                ass_arrList.add((float) 0);
            }
            
            
                    
            source_arrList.setI(int_arrList);
            mo_arrList.add(source_arrList);
            test.source.setI(mo_arrList);
            
            newtimestamp = test.destination.getTimestamp();
            while(newtimestamp == oldtimestamp) {
                newtimestamp = test.destination.getTimestamp();
                System.out.println("Timestamp after: "+TimeStamp.getStringTimeStamp(newtimestamp,"dd/MM/yyyy HH:mm:ss.SSS"));
            }
            System.out.println("\n \n   Input 3: "+test.source.getI());
            
            
            System.out.print("\n   Output 3: "+ test.destination.getI());
            fulllist = (List) test.destination.getI();
            if (fulllist != null && fulllist.size() > 0) {
                //printList(fulllist);
                System.out.println("          sizef: "+fulllist.size()+"\n");
                
                
                assertEquals(fulllist.size(),16);
                assertEquals(fulllist,ass_arrList);
                
            }
        //}
    }
}
