package tel_ran.view;

import java.util.List;
import java.util.function.Predicate;

public class PredicateStrings implements Predicate<String> {
List<String>strings;

	public PredicateStrings(List<String> strings) {
	super();
	this.strings = strings;
}

	@Override
	public boolean test(String t) {
		
		return strings.contains(t);
	}

}
