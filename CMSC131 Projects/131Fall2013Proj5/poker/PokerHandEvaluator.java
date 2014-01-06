package poker;
public class PokerHandEvaluator {
	
	//Runs two simple for loops to check for a pair
	public static boolean hasPair(Card[] cards) {
		for(int i=0; i<cards.length-1;i++){
			for(int j =i+1; j<cards.length; j++){
				if(cards[i].getValue()==cards[j].getValue()){
					return true;
				}
			}
		}
		return false;
	}
	
	//Same concept as hasPair except with an added if statement to check for two pairs
	public static boolean hasTwoPair(Card[] cards) {
		int counter = 0;
		for(int i = 0; i<cards.length; i++){
			for(int j = 0; j<cards.length; j++){
				if(i==j){
					continue;
				}
				if(cards[i].getValue() == cards[j].getValue()){
					if(counter == 0){
						counter = cards[i].getValue();
					}else if(counter != cards[i].getValue()){
							return true;
						}
					}
				}
			}
		return false;
		}

	//basic concept. Runs multiple for loops to check for the same card.
	public static boolean hasThreeOfAKind(Card[] cards) {
		for(int i =0; i<cards.length - 1; i++){
			for(int j = i+1; j<cards.length;j++){
				for(int k = j+1; k<cards.length;k++){
					if(cards[i].getValue()==cards[j].getValue() && cards[j].getValue() == cards[k].getValue()){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//Uses a boolean array to make sure it has 5 cards in numerical order to get a straight
	public static boolean hasStraight(Card [] cards) {
		boolean truthArr[] = new boolean[14];
		for(int i=0; i<cards.length; i++){
			truthArr[cards[i].getValue()]=true;
		}
		for(int i=0; i<=9; i++){
			if(truthArr[i]==true && truthArr[i+1]==true && truthArr[i+2]==true&& truthArr[i+3]==true && truthArr[i+4]){
				return true;
			}
		}
		if(truthArr[1] == true && truthArr[10] == true && truthArr[11]==true && truthArr[12]==true && truthArr[13]){
			return true;
		}
		return false;
	}
	//for loop that checks to see if it has equal suits and once it has 5 it returns true
	public static boolean hasFlush(Card[] cards) {
		int flush = 0;
		int suit = cards[0].getSuit();
		for (int i = 1; i<cards.length; i++){
			if (suit == cards[i].getSuit()){
				flush++;
			}
		}
		if(flush == 4){
			return true;
		}
		return false;
	}
	//if you have a three of a king and a two pair you have a Full house. Simple enough.
	public static boolean hasFullHouse(Card[] cards) {
		if(hasThreeOfAKind(cards)&&hasTwoPair(cards)){
			return true;
		}
		return false;
	}
	//runs an even longer four loop to check for four of a kind
	public static boolean hasFourOfAKind(Card[] cards) {
		for(int i=0; i<cards.length - 1; i++){
			for(int j=i+1; j<cards.length;j++){
				for(int k=j+1; k<cards.length; k++){
					for(int l=k+1; l<cards.length; l++){
						if(cards[i].getValue()==cards[j].getValue()&&cards[j].getValue()==cards[k].getValue()&&cards[k].getValue()==cards[l].getValue()){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	//if you have a straight and you have a flush you have a StraightFlush.
	public static boolean hasStraightFlush(Card[] cards) {
		if(hasStraight(cards) == true && hasFlush(cards) == true){
			return true;
		}
		return false;
	}
}

