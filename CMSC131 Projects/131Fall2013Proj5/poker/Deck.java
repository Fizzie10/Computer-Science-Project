package poker;

public class Deck {

	private Card[] cards;
	
	//basic deck constructor
	public Deck() {
		this.cards = new Card[52];
		int i = 0;
		for(int suit=0;suit<=3;suit++){
			for(int value=1;value<=13;value++){
				this.cards[i] = new Card(value,suit);
				i++;
			}
		}
	}
	//basic deck copy constructor
	public Deck(Deck other) {
		cards = new Card[other.cards.length];
		for(int i=0; i<other.cards.length;i++){
			cards[i] = other.cards[i];
		}
	}
	
	//gets card at a position
	public Card getCardAt(int position) {
		return this.cards[position];
	}
	
	//gets the length of the cards array
	public int getNumCards() {
		return cards.length;
	}
	
	//shuffles the deck for even and odd deck values
	public void shuffle() {
		int counter = 0;
		int counterA = 0;
		if(cards.length%2==0){
			Card[] top = new Card[cards.length/2];
			Card[] bottom = new Card[cards.length/2];
			for(int i=0; i<cards.length/2;i++){
				top[i] = cards[i];
			}
			for(int j=cards.length/2; j<cards.length;j++){
				bottom[counter]=cards[j];
				counter++;
			}
			for(int k=0; k<cards.length/2;k++){
				cards[counterA]=top[k];
				counterA++;
				cards[counterA]=bottom[k];
				counterA++;
			}
		}else{
			Card[] top = new Card[(cards.length/2)+1];
			Card[] bottom = new Card[cards.length/2];
			for(int i=0; i<(cards.length/2)+1;i++){
				top[i] = cards[i];
			}
			for(int j=(cards.length/2)+1;j<cards.length;j++){
				bottom[counter] = cards[j];
				counter++;
			}
			int upHalf = 0;
			int bottomHalf = 0;
			for(int i = 0; i<cards.length; i++){
				if(i%2==0){
					cards[i] = top[upHalf];
					upHalf++;
				}
				else{
					cards[i] = bottom[bottomHalf];
					bottomHalf++;
				}
			}
		}
			
		}
	
	//Cuts deck depending on the position and moves it to the back
	public void cut(int position) {
		Card[] sub = new Card[position];
		int sub2length = cards.length-position;
		Card[] sub2 = new Card[sub2length];
		for(int i=0; i<position; i++){
			sub[i] = cards[i];
		}
		for(int j=position; j<cards.length;j++){
			cards[j-position] = cards[j];
		}
		for(int k=0; k<position;k++){
			cards[k+(sub2length)]=sub[k];
		}
	}

	//deals the number of cards asked for off of the top.
	public Card[] deal(int numCards) {
		int smallerlength = cards.length-numCards;
		Card[] smaller = new Card[smallerlength];
		Card[] dealt = new Card[numCards];
		int counter = 0;
		for(int j=0; j<numCards;j++){
			dealt[j] = cards[j];
		}
		for(int i=numCards; i<cards.length; i++){
			smaller[counter] =cards[i];
			counter++;
		}
		cards = smaller;
		return dealt;
	}

}
