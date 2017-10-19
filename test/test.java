import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class test {
	VendingMachine vm;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	
	@Before
	public void setUp(){
		System.setOut(new PrintStream(outContent));
		vm = new VendingMachine();
	}
	
	@Test
	public void vendingMachineAcceptsCoins() {
		vm.acceptCoins("QDN");
		int[] result = vm.getMoneyInTheMachine();
		int[] expectedResult = {21,11,5};
		Assert.assertArrayEquals( expectedResult,result);
		vm.selectProduct("RETURNCOINS");
		
	}

	@Test
	public void customerCanSelectProduct(){
		vm.acceptCoins("QQQQ");
		vm.selectProduct("COLA");
		int colaStockAfter = vm.getColaStock();
		assertEquals(9, colaStockAfter);	
	} 
	
	@Test
	public void canMakeChange(){
		String change = vm.makeChange(105, "CHIPS");
		assertEquals(vm.valueOfCoins(change), 55);
	}
	
	@Test
	public void canReturnCoins(){
		vm.acceptCoins("QQQNQNQND");
		String coinsReturned = vm.selectProduct("RETURNCOINS");
		assertEquals(coinsReturned, "QQQNQNQND");
		
	}
	
	@Test
	public void displaysInsertCoinAfterCoinsReturned(){
		vm.selectProduct("RETURNCOINS");
		assertEquals("INSERT COIN\n", outContent.toString());
		outContent.reset();
	}
	
	@Test
	public void displaysInsertCoinIfNotMoneyIsInserted(){
		vm.selectProduct("COLA");
		assertEquals("INSERT COIN\n", outContent.toString());
		outContent.reset();
	}
	
	@Test
	public void displaysSoldOut(){
		vm = new VendingMachine();
		for(int i = 0; i < 10; i ++){
			vm.acceptCoins("QQQQ");
			vm.selectProduct("COLA");
		}
		vm.acceptCoins("QQQQ");
		outContent.reset();
		vm.selectProduct("COLA");
		assertEquals("SOLD OUT\nMONEY REMAINING: 1400\n", outContent.toString());
	}
	
}

