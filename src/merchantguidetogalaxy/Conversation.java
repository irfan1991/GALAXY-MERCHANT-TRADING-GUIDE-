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
public class Conversation {
    public static enum Type {
        ASSIGNED,
        CREDITS,
        HOW_MUCH, 
        HOW_MANY, 
        NOMATCH
 
    }

    public class Filter {

        private Conversation.Type type;
	private String pattern;
        
	public Filter(Conversation.Type type,String pattern)    {
            this.type = type;
            this.pattern = pattern;}

	public String getPattern() {
            return this.pattern; }		
        
        public Conversation.Type getType() {
            return this.type; }

	}

        public static String assigned = "^([A-Za-z]+) is ([I|V|X|L|C|D|M])$";
        public static String credits = "^([A-Za-z]+)([A-Za-z\\s]*) is ([0-9]+) ([c|C]redits)$";
        public static String howMuch = "^how much is (([A-Za-z\\s])+)\\?$";
        public static String howMany= "^how many [c|C]redits is (([A-Za-z\\s])+)\\?$";
        private Filter[] linefilter;


	public Conversation() {

            this.linefilter = new Filter[4];
            this.linefilter[0] = new Filter(Conversation.Type.ASSIGNED, assigned);
            this.linefilter[1] = new Filter(Conversation.Type.CREDITS, credits);
            this.linefilter[2] = new Filter(Conversation.Type.HOW_MUCH, howMuch);
            this.linefilter[3] = new Filter(Conversation.Type.HOW_MANY, howMany); }

	public Conversation.Type getLineType(String line) {

            line = line.trim();
            Conversation.Type result = Type.NOMATCH;
            boolean matched = false;

            for(int i =0;i<linefilter.length && !matched ;i++) {

		if( line.matches(linefilter[i].getPattern()) ) {

			matched = true;
			result = linefilter[i].getType();}

                }

		return result;

	}	
    
}
