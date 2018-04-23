package hr.fer.zemris.java.hw06.observer2;

/**
 * Implementation of {@link IntegerStorageObserver} that 
 * will print square of last value set in the subject.
 * @author Josip Trbuscic
 *
 */
public class SquareValue implements IntegerStorageObserver {
	
	/**
	 * Action performed when value of subject has changed. 
	 * Prints square of last value set in the subject.
	 * @param istorage - subject  
	 */
	@Override
	public void valueChanged(IntegerStorageChange istorageChange) {
		int value = istorageChange.getAfterValue();
		
		System.out.println("Provided new value: "+value+
							", square is "+value*value);
	}
}
