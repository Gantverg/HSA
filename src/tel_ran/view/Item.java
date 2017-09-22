package tel_ran.view;

public interface Item {
String displayedName();
void perform();
default boolean isExit(){
	return false;
}
}
