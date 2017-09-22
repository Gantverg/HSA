package tel_ran.view;

public class ExitItem implements Item {

	@Override
	public String displayedName() {
		return "Exit";
	}

	@Override
	public void perform() {
		

	}
	@Override
	public boolean isExit(){
		return true;
	}

}
