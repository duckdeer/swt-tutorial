package greeter.main;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Greeter {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));
		shell.setText("Greeter");
		
		Greeter greeter = new Greeter();
		greeter.createUi(shell);
		greeter.makeActions();
		greeter.addListeners();
		greeter.initUi();
		
		shell.pack();
		shell.open();
		
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}

	private void createUi(Composite parent) {
		// TODO
	}
	
	private void makeActions() {
		// TODO
	}
	
	private void addListeners() {
		// TODO
	}

	private void initUi() {
		// TODO
	}
}
