package greeter.main;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class GreeterSolution {

	// Widgets
	private Button btnMale;
	private Button btnFemale;
	private Text txtFirstName;
	private Text txtLastName;
	private DateTime dtBirthday;
	private Button btnGreet;
	private Button btnClear;

	// Actions
	private IAction greetAction;
	private IAction clearAction;

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));
		shell.setText("Greeter");
		
		GreeterSolution greeter = new GreeterSolution();
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
		Composite container = new Composite(parent, SWT.None);
		container.setLayout(new GridLayout(2, false));
		
		Label lblFirstName = new Label(container, SWT.None);
		lblFirstName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		lblFirstName.setText("Vorname: ");
		
		txtFirstName = new Text(container, SWT.BORDER);
		txtFirstName.setLayoutData(new GridData(SWT.FILL, SWT. FILL, true, false));
		
		
		Label lblLastName = new Label(container, SWT.None);
		lblLastName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		lblLastName.setText("Nachname: ");
		
		txtLastName = new Text(container, SWT.BORDER);
		txtLastName.setLayoutData(new GridData(SWT.FILL, SWT. FILL, true, false));
		
		
		btnMale = new Button(container, SWT.RADIO);
		btnMale.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		btnMale.setText("Männlich");
		
		btnFemale = new Button(container, SWT.RADIO);
		btnFemale.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		btnFemale.setText("Weiblich");
		

		Label lblBirthday = new Label(container, SWT.None);
		lblBirthday.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		lblBirthday.setText("Geburtstag: ");
		
		dtBirthday = new DateTime(container, SWT.CALENDAR);
		dtBirthday.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
	
		
		btnGreet = new Button(container, SWT.None);
		btnGreet.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		btnGreet.setText("Grüßen");
		
		btnClear = new Button(container, SWT.None);
		btnClear.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false));
		btnClear.setText("Felder leeren");
	}
	
	private void makeActions() {
		clearAction = new ClearAction();
		greetAction = new GreetAction();
	}
	
	private void addListeners() {
		btnClear.addSelectionListener(new ClearSelectionListener());
		btnGreet.addSelectionListener(new GreetSelectionListener());
		txtFirstName.addModifyListener(new TextModifyListener());
		txtLastName.addModifyListener(new TextModifyListener());
	}

	private void initUi() {
		btnMale.setSelection(true);
		btnFemale.setSelection(false);
		btnGreet.setEnabled(false);
		
		Calendar cal = new GregorianCalendar();
		dtBirthday.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
	}
	
	private void enableButtons() {
		if (txtFirstName.getText().trim().isEmpty() || txtLastName.getText().trim().isEmpty()) {
			btnGreet.setEnabled(false);
		} else {
			btnGreet.setEnabled(true);
		}
	}
	
	private class ClearAction extends Action {
		@Override
		public void run() {
			txtFirstName.setText("");
			txtLastName.setText("");
			
			initUi();
		}
	}
	
	private class GreetAction extends Action {
		
		@Override
		public void run() {
			StringBuilder greetBuilder = new StringBuilder("Hallo, ");
			if (btnMale.getSelection()) {
				greetBuilder.append("Herr ");
			} else if (btnFemale.getSelection()) {
				greetBuilder.append("Frau ");
			}
			
			greetBuilder.append(txtFirstName.getText().trim()).append(" ").append(txtLastName.getText().trim()).append("!\n");
			greetBuilder.append("Ihr Geburtstag ist am: ").append(dtBirthday.getDay()).append(".");
			greetBuilder.append(dtBirthday.getMonth() + 1).append(".");
			greetBuilder.append(dtBirthday.getYear()).append("!");
			
			MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Begrüßung", greetBuilder.toString());
		}
	}
	
	private class ClearSelectionListener extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			clearAction.run();
		}
	}
	
	private class GreetSelectionListener extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent e) {
			greetAction.run();
		}
	}
	
	private class TextModifyListener implements ModifyListener {

		@Override
		public void modifyText(ModifyEvent e) {
			enableButtons();
		}
	}
}
