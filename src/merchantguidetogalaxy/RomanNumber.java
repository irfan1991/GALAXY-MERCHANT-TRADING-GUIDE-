/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merchantguidetogalaxy;

/**
 *
 * @author Asus
 */
public class RomanNumber {
    
    public static String romanValidator = "^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

	enum Roman{
            
            I(1) , V(5), X(10), L(50), C(100), D(500), M(1000);
            private int value;
            
            Roman(int value) {

		this.value = value; }

            public int getValue() {

                return this.value; }

	}

    private static int getValueChar(char romanChar) {

	int value = -1;

	switch(romanChar)

            {

		case 'I' : value = Roman.I.getValue();
                    break;

		case 'V' : value = Roman.V.getValue();
                    break;

		case 'X' : value = Roman.X.getValue();
                    break;

		case 'L' : value = Roman.L.getValue();
                    break;

		case 'C' : value = Roman.C.getValue();
                    break;

		case 'D' : value = Roman.D.getValue();
                    break;

		case 'M' : value = Roman.M.getValue();
                    break;
			
		}
            
            return value;

	}
	
    public static String romanToArabic(String roman){

	String result="";

	switch(validateRomanNumber(roman)) {

            case 1 :  result = convert(roman);
                break;
            
            default : result = "Invalid roman number format !";

            }
               return result;

	}

    
    private static int validateRomanNumber(String roman) {

	int result = 0;

            if(roman.matches(romanValidator)) {

		result = 1; 
            }

		return result;

    }

	
    private static String convert(String roman) {

	int decimal = 0;
        int lastNumber = 0;
     
	for(int i=roman.length()-1;i>=0;i--) {

            char ch = roman.charAt(i);
            decimal = CheckRoman(getValueChar(ch), lastNumber, decimal);
            lastNumber = getValueChar(ch);

	}

            return decimal+"";

    }

	

	

    private static int CheckRoman(int TotalDecimal, int LastRomanLetter, int LastDecimal){

        if (LastRomanLetter > TotalDecimal) {

            return LastDecimal - TotalDecimal;

        } else {

            return LastDecimal + TotalDecimal;

        }

    }
    
}
