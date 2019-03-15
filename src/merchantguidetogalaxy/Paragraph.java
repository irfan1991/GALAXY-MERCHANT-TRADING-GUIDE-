/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merchantguidetogalaxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Asus
 */
public class Paragraph {
    
    private Scanner scan;
    private Conversation conversation;
    private ErrorMessage eMessage;
    private HashMap<String, String> assignments;
    private HashMap<String, String> literals;
    private ArrayList<String> output;

    public Paragraph() {

        this.scan = new Scanner(System.in);
        this.conversation = new Conversation();
        this.eMessage = new ErrorMessage();
        this.assignments = new HashMap<String, String>();
        this.literals = new  HashMap<String, String>();
        this.output = new ArrayList<String>();

    }

    public ArrayList<String> read() {

	String roman;
        int count=0;
        ErrorCode err = null;
        
        while(this.scan.hasNextLine() && (roman = this.scan.nextLine()).length()>0 ) {

            err = validate(roman);

		switch(err) {

                    case NO_IDEA :  this.output.add(this.eMessage.getMessage(err));
                        break;
                        
                    default : this.eMessage.printMessage(err);

		}
                    count++;

            }
        
                switch(count){    
                    case 0: err = ErrorCode.EMPTY;
                    this.eMessage.printMessage(err);break;
                    default : 

		}
                return this.output;

	}


	private ErrorCode validate(String roman) {
            
            ErrorCode err = ErrorCode.SUCCES;
            Conversation.Type romanType = this.conversation.getLineType(roman);
            
            switch(romanType) {
                case ASSIGNED : 		 
                    processAssign(roman);
                    break;

		case CREDITS :			 
                    processCredits(roman);
                    break;

		case HOW_MUCH : 
                    processHowMuch(roman);
                    break;

		case HOW_MANY : 
                    processHowMany(roman);
                    break;

		default : err = ErrorCode.NO_IDEA; 
                    break;

		}				

		return err;

	}

        
    private void processAssign(String roman){
    
            String[] splited = roman.trim().split("\\s+");

		try {

			assignments.put(splited[0], splited[2]);
                        
                }catch(ArrayIndexOutOfBoundsException e){

			this.eMessage.printMessage(ErrorCode.INCORRECT_LINE_TYPE);

			System.out.println(e.getMessage());

		}

    }

    private void processHowMuch(String roman) {
        try {
            
            String formatted = roman.split("\\sis\\s")[1].trim();
            formatted = formatted.replace("?","").trim();
            String keys[] = formatted.split("\\s+");

            String romanResult="";
            String completeResult = null;
            boolean errOccured = false;

		for(String key : keys) {

                    String romanValue = assignments.get(key);
                        if(romanValue==null)
                            {

				completeResult = this.eMessage.getMessage(ErrorCode.NO_IDEA);
                                errOccured = true;
                                break;
				}

				romanResult += romanValue;

			}

			

		if(!errOccured) {

                    romanResult = RomanNumber.romanToArabic(romanResult);
                    completeResult = formatted+" is "+romanResult;

		}

                    output.add(completeResult);

			

		}catch(Exception e){

                    this.eMessage.printMessage(ErrorCode.INCORRECT_LINE_TYPE);
                      System.out.println(e.getMessage());

		}

	}

	

    private void processCredits(String roman) {

	try {

            String formatted = roman.replaceAll("(is\\s+)|([c|C]redits\\s*)","").trim();
            String[] keys = formatted.split("\\s");

            String toBeComputed = keys[keys.length-2];
            float value = Float.parseFloat(keys[keys.length-1]);
            String roman2="";
            
                for(int i=0;i<keys.length-2;i++) {

                    roman2 += assignments.get(keys[i]); }

			int romanNumber = Integer.parseInt(RomanNumber.romanToArabic(roman2));
                        float credit = (float)(value/romanNumber);
                        literals.put(toBeComputed, credit+"");

		}catch(Exception e){

                    this.eMessage.printMessage(ErrorCode.INCORRECT_LINE_TYPE);
                    System.out.println(e.getMessage());

		}

	}


    private void processHowMany(String roman) {	

	try {

            String formatted = roman.split("(\\sis\\s)")[1];
            formatted = formatted.replace("?","").trim();
            String[] keys = formatted.split("\\s");
            boolean found = false;
            String roman2 = "";
            String outputResult = null;
            Stack<Float> cvalues = new Stack<Float>();
            
            for(String key : keys) {

		found = false;
                String romanValue = assignments.get(key);
                    if(romanValue!=null) {

			roman += romanValue;
                        found = true;

		}

		String computedValue = literals.get(key);

		 if(!found && computedValue!=null)
                 {
                     cvalues.push(Float.parseFloat(computedValue));
                     found = true;

		}

		if(!found) {
                    
                    outputResult = this.eMessage.getMessage(ErrorCode.NO_IDEA);
                    break;

		}

            }
			
            if(found){
                float res=1;
                  for(int i =0;i<cvalues.size();i++)
                      res *= cvalues.get(i);
                      int finalres= (int) res;
                      
                        if(roman.length()>0)
                            finalres = (int)(Integer.parseInt(RomanNumber.romanToArabic(roman))*res);
                            outputResult = formatted +" is "+ finalres +" Credits";

			}
			this.output.add(outputResult);
		}catch(Exception e){

                    this.eMessage.printMessage(ErrorCode.INCORRECT_LINE_TYPE);
                    System.out.println(e.getMessage());

		}

		

	}
    
}
