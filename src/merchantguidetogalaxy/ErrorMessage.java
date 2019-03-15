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
public class ErrorMessage {

    public ErrorMessage() {
    }
    
    public String getMessage(ErrorCode err){
        String msg = null ;
       
        switch(err)
        {		
            case EMPTY : msg = "Please input field into program !";
                break;
            case INVALID : msg = "Input format is wrong !";
                break;
            case INVALID_ROMAN_CHARACTER : msg = "Illegal character specified in roman number !";
                break;
            case INVALID_ROMAN_STRING : msg =  "Invalid roman number format !";
                break;
            case INCORRECT_LINE_TYPE : msg =  "Incorrect line type supplied";
                break;
            case NO_IDEA : msg = "I have no idea what you are talking about";
                break;
            default : break;

		}
        return msg;
        
    }
    
    public void printMessage(ErrorCode err){
        String msg = getMessage(err);
        
        if(msg != null){
            System.out.println(msg);
        }
    }

}
