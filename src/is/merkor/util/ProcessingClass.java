package is.merkor.util;

/**
 * An abstract class for classes to inherit from that implement a method
 * process(String). Using this class as parent class makes it possible to 
 * use the children as parameters to generic processing classes, like 
 * MerkorTokenReader.
 * 
 * @author Anna B. Nikulasdottir
 * @version 0.8
 */
public abstract class ProcessingClass {
	
	public void process (String string) {
		// inheriting class should implement!
	}
	
	public void finishProcess() {
		
	}
}
