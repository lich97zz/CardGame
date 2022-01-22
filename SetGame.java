import java.util.*;

class Card{
	static final Set<String> colorVal = 
		new HashSet<>(Arrays.asList("blue", "green", "yellow"));
	static final Set<String> symbolVal = 
		new HashSet<>(Arrays.asList("A", "S", "H"));
	static final Set<String> shadingVal = 
		new HashSet<>(Arrays.asList("A", "S", "H", "a", "s", "h", "@", "$", "#"));	
	static final Map<String, String> charToSymbol;
	static {
		charToSymbol = new HashMap<>();
		charToSymbol.put("A","A");
		charToSymbol.put("a","A");
		charToSymbol.put("@","A");
		charToSymbol.put("S","S");
		charToSymbol.put("s","S");
		charToSymbol.put("$","S");
		charToSymbol.put("H","H");
		charToSymbol.put("h","H");
		charToSymbol.put("#","H");
	}

	static final Map<String, String> charToShading;
	static {
		charToShading = new HashMap<>();
		charToShading.put("A","Upper");
		charToShading.put("a","Lower");
		charToShading.put("@","Symbol");
		charToShading.put("S","Upper");
		charToShading.put("s","Lower");
		charToShading.put("$","Symbol");
		charToShading.put("H","Upper");
		charToShading.put("h","Lower");
		charToShading.put("#","Symbol");
	}

	String color;
	String shading;
	String symbol;
	String text;
	int number;

	public Card(String str){
		String[] splited = str.split("\\s+");
		text = str;
		// System.out.println(text);
		color = splited[0];
		String syms = splited[1];
		shading = charToShading.get(syms.substring(0,1));
		symbol = charToSymbol.get(syms.substring(0,1));
		number = syms.length();
	}

	public void printInfo() {
		// String res = this.color+this.symbol+this.shading+String.valueOf(this.number);
		System.out.print(text+"\n");
		// System.out.print(res+"\n");
	}
};

class Deck{
	List<Card> cards;
	List<List<Card>> sets;
	List<Integer> dSets;
	int count;

	public Deck() {
		cards = new ArrayList<>();
		sets = new ArrayList<>();
		dSets = new ArrayList<>();
		count = 0;
	}

	void addCard(Card c) {
		cards.add(c);
	}

	int setLen() {
		return sets.size();
	}
	int dSetLen() {
		return dSets.size();
	}
	void printSets() {
		for(List<Card> i:sets) {
			System.out.println();
			for(Card c:i) {
				c.printInfo();
			}
		}
	}

	void printdSets() {
		for(int i:dSets) {
			System.out.println();
			for(Card c:sets.get(i)) {
				c.printInfo();
			}
		}
	}

	void findSet(){
		for(int i=0; i<cards.size(); i++) {
			for(int j=i+1; j<cards.size(); j++) {
				for(int k=j+1; k<cards.size(); k++) {
					// System.out.println(cards.size());
					// System.out.printf("%d %d %d \n",i,j,k);
					Card c1 = cards.get(i);
					Card c2 = cards.get(j);
					Card c3 = cards.get(k);
					if(!SetGame.formSet(c1, c2, c3))
						continue;
					// System.out.println("added a set");
					List<Card> elm = Arrays.asList(c1, c2, c3);
					sets.add(new ArrayList<Card>(elm));
				}
			}
		}
	}

	void finddSet(int sId, Set<Card> cardUsed, List<Integer> setUsed) {
		if(sId>=sets.size())
			return;
		if(setUsed.size()>count) {
			count = setUsed.size();
			dSets = new ArrayList<Integer>(setUsed);
		}

		finddSet(sId+1, new HashSet<>(cardUsed), new ArrayList<>(setUsed));

		Card c1 = sets.get(sId).get(0);
		Card c2 = sets.get(sId).get(1);
		Card c3 = sets.get(sId).get(2);

		if(cardUsed.contains(c1) || cardUsed.contains(c2) || cardUsed.contains(c3))
			return;

		cardUsed.add(c1);
		cardUsed.add(c2);
		cardUsed.add(c3);
		setUsed.add(sId);

		if(setUsed.size()>count) {
			count = setUsed.size();
			dSets = new ArrayList<Integer>(setUsed);
		}
		finddSet(sId+1, new HashSet<>(cardUsed), new ArrayList<>(setUsed));
	}
};
public class SetGame{
	public static Boolean formSet(Card c1, Card c2, Card c3) {
		String color;
		String shading;
		String symbol;
		String text;
		int number;
		if ((c1.color.equals(c2.color) && !c1.color.equals(c3.color)) ||
			(c1.color.equals(c3.color) && !c2.color.equals(c3.color)) ||
			(c2.color.equals(c3.color) && !c1.color.equals(c2.color)) ) {
			return false;
		}
		if ((c1.shading.equals(c2.shading) && !c1.shading.equals(c3.shading)) ||
			(c1.shading.equals(c3.shading) && !c2.shading.equals(c3.shading)) ||
			(c2.shading.equals(c3.shading) && !c1.shading.equals(c2.shading)) ) {
			return false;
		}
		if ((c1.symbol.equals(c2.symbol) && !c1.symbol.equals(c3.symbol)) ||
			(c1.symbol.equals(c3.symbol) && !c2.symbol.equals(c3.symbol)) ||
			(c2.symbol.equals(c3.symbol) && !c1.symbol.equals(c2.symbol)) ) {
			return false;
		}
		if ((c1.number==c2.number && c1.number!=c3.number) ||
			(c1.number==c3.number && c2.number!=c3.number) ||
			(c2.number==c3.number && c1.number!=c2.number) ) {
			return false;
		}
		return true;
	}
	public static void main(String[] args) {
		Deck deck = new Deck();
		// System.out.println("Please input card number N and cards:");
		Scanner stdIn = new Scanner(System.in);
		int num = stdIn.nextInt();
		stdIn.nextLine();
		for(int i=0; i<num; i++) {
			String info = stdIn.nextLine();
			Card cd = new Card(info);
			deck.addCard(cd);
		}
		deck.findSet();

		// System.out.printf("Set size := %d\n",deck.setLen());
		deck.finddSet(0, new HashSet<Card>(), new ArrayList<Integer>());
		System.out.println(deck.setLen());
		System.out.println(deck.dSetLen());
		deck.printdSets();
		// deck.printSets();
	}
};