package tel_ran.view;

import java.util.*;

public class Menu {
private InputOutput inputOutput;
private List<Item> items;
public Menu(InputOutput inputOutput, Iterable<Item> items) {
	super();
	this.inputOutput = inputOutput;
	this.items=new ArrayList<>();
	for(Item item:items)
		this.items.add(item);
}
public void runMenu(){
	while (true) {
		inputOutput.put("Select any item (type number) from the following:");
		int nItem = 1;
		for (Item item : items) {
			inputOutput.put(nItem++ + ". " + item.displayedName());
		}
		nItem = inputOutput.getInteger("select item number", 1, items.size()+1);
		Item item = items.get(nItem - 1);
		item.perform();
		if(item.isExit())
			break;
	}
}
}
