/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merchantguidetogalaxy;

import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class MerchantGuideToGalaxy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
	Paragraph paragraph = new Paragraph();
	ArrayList<String> output=paragraph.read();

            for(int i=0;i<output.size();i++) {
                
                System.out.println(output.get(i));

	}
    }
    
}
