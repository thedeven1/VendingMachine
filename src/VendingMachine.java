public class VendingMachine {
	private static int colaStock;
	private static int chipsStock;
	private static int candyStock;
	private static final int colaPrice = 100;
	private static final int chipsPrice = 50;
	private static final int candyPrice = 65;
	private static int moneyInserted;
	private static int itemCapacity = 10;
	private String coinsInserted = "";
	private int[] moneyInTheMachine = new int[]{20,10,4}; //number of nickels, dimes and quarters respectively
	private enum buttons{
		COLA,
		CHIPS,
		CANDY,
		RETURNCOINS;		
	}
	

	
	public VendingMachine(){
		colaStock = itemCapacity;
		chipsStock = itemCapacity;
		candyStock = itemCapacity;
	}
	
	public void acceptCoins(String coins){
		this.coinsInserted = coinsInserted+coins;
		int insertedNickels = 0;
		int insertedDimes = 0;
		int insertedQuarters = 0;
		for(int i =0;  i < coins.length(); i++){
			if(coins.charAt(i) == 'N' || coins.charAt(i) == 'n'){
				insertedNickels++;			
			}else if(coins.charAt(i) == 'D' || coins.charAt(i) == 'd'){
				insertedDimes++;			
			}else if(coins.charAt(i) == 'Q' || coins.charAt(i) == 'q'){
				insertedQuarters++;			
			}
		}
		moneyInserted = valueOfCoins(coins) ;
		int nickelsContained = this.getMoneyInTheMachine()[0]+insertedNickels;
		int dimesContained = this.getMoneyInTheMachine()[1]+insertedDimes;
		int quartersContained = this.getMoneyInTheMachine()[2]+insertedQuarters;
		int[] coinsContained =	{nickelsContained, dimesContained , quartersContained};
		this.setMoneyInTheMachine(coinsContained);
	}
	
	public String selectProduct(String product){
		String change = "";
		int amountRemaining = getAmountOfMoneyRemaining();
		if(moneyInserted == 0){
			System.out.println("INSERT COIN");
			return change;
		}
		if(buttons.valueOf(product) == buttons.COLA){
			if(moneyInserted >= colaPrice && colaStock > 0){
				System.out.println("Enjoy your Cola");
				colaStock--;
				System.out.println("Thank You");
				change = this.makeChange(moneyInserted,"COLA");
			}else if(colaStock == 0){
				System.out.println("SOLD OUT");
				if(amountRemaining > 0){
					System.out.println("MONEY REMAINING: " + amountRemaining);
				}else{
					System.out.println("INSERT COINS");
				}
			}else{
				System.out.println("PRICE: " + colaPrice);
			}
		}else if(buttons.valueOf(product) == buttons.CHIPS){
			if(moneyInserted >= chipsPrice && chipsStock > 0){
				System.out.println("Enjoy your Chips");
				chipsStock--;
				System.out.println("Thank You");
				change = this.makeChange(moneyInserted,"CHIPS");
			}else if(chipsStock == 0){
				System.out.println("SOLD OUT");
				if(amountRemaining > 0){
					System.out.println("MONEY REMAINING: " + amountRemaining);
				}else{
					System.out.println("INSERT COINS");
				}
			}else{
				System.out.println("PRICE: " + chipsPrice);
			}
		}else if(buttons.valueOf(product) == buttons.CANDY){
			if(moneyInserted >= candyPrice && candyStock > 0){
				System.out.println("Enjoy your Candy");
				candyStock--;
				System.out.println("Thank You");
				change = this.makeChange(moneyInserted,"CANDY");
			}else if(candyStock == 0){
				System.out.println("SOLD OUT");
				if(amountRemaining > 0){
					System.out.println("MONEY REMAINING: " + amountRemaining);
				}else{
					System.out.println("INSERT COINS");
				}
			}else{
				System.out.println("PRICE: " + candyPrice);
			}
		}else if(buttons.valueOf(product) == buttons.RETURNCOINS){
			System.out.println("INSERT COIN");
			change = this.returnCoins();
		}
		this.coinsInserted = "";
		this.moneyInserted = 0;
		return change;	
	}
	
	public String makeChange(int moneyInput, String product){
	
		int price = 0;
		if(buttons.valueOf(product) == buttons.COLA){
			price = colaPrice;
		}else if(buttons.valueOf(product) == buttons.CHIPS){
			price = chipsPrice;
		}else if(buttons.valueOf(product) == buttons.CANDY){
			price = candyPrice;
		}
		
		
		if(moneyInput - price >= 25 && getQuartersInMachine() > 0){
			moneyInTheMachine[0]--;
			return "Q" + this.makeChange(moneyInput-25, product);
		}else if(moneyInput - price >= 10 && getDimesInMachine() > 0){
			moneyInTheMachine[1]--;
			return "D" + this.makeChange(moneyInput - 10, product);
		}else if(moneyInput - price >= 5 && getNickelsInMachine() > 0){
			moneyInTheMachine[2]--;
			return "N" + this.makeChange(moneyInput - 5, product);
		}else if(moneyInput - price == 0){
			return "";
		}else{
			System.out.println("EXACT CHANGE ONLY");
			return coinsInserted;
		}
		
	}
	
	public int[] getMoneyInTheMachine(){
		return moneyInTheMachine;
	}
	
	public void setMoneyInTheMachine(int[] coinsContained){
		moneyInTheMachine = coinsContained;
	}
	
	public int getNickelsInMachine(){
		return moneyInTheMachine[0];
	}
	public int getDimesInMachine(){
		return moneyInTheMachine[1];
	}
	public int getQuartersInMachine(){
		return moneyInTheMachine[2];
	}
	
	public int getColaStock(){
		return colaStock;
	}
	
	public static int getChipsStock() {
		return chipsStock;
	}

	public static void setChipsStock(int chipsStock) {
		VendingMachine.chipsStock = chipsStock;
	}

	public static int getCandyStock() {
		return candyStock;
	}

	public static void setCandyStock(int candyStock) {
		VendingMachine.candyStock = candyStock;
	}

	public static void setColaStock(int colaStock) {
		VendingMachine.colaStock = colaStock;
	}


	
	public String returnCoins(){
		moneyInserted = 0;
		String returnedCoins = coinsInserted;
		coinsInserted = "";
		return returnedCoins;
	}
	
	public int valueOfCoins(String coins){
		int value = 0;
		for(int i =0;  i < coins.length(); i++){
			if(coins.charAt(i) == 'N' || coins.charAt(i) == 'n'){
				value = value + 5;			
			}else if(coins.charAt(i) == 'D' || coins.charAt(i) == 'd'){
				value = value + 10;			
			}else if(coins.charAt(i) == 'Q' || coins.charAt(i) == 'q'){
				value = value + 25;		
			}
		}
		return value;	
	}
	
	private int getAmountOfMoneyRemaining(){
		int amount = 25*getQuartersInMachine();
		amount = amount + 10*getDimesInMachine();
		amount = amount + 5*getNickelsInMachine();
		return amount;
	}
}
