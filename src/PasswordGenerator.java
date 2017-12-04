import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import java.util.Random;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.program.Program;

public class PasswordGenerator {
	private static Text txtGeneratedPassword;

	private static final String charsetNumber = "0123456789";
    private static final String charsetBigLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String charsetSmallLetter = "abcdefghijklmnopqrstuvwxyz";
    private static final String charsetSpecialChar = "!^+%&/()=*?-_<>£#${[]}|\\@~";
    private static String selectedCharSet;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shlPasswordGenerator = new Shell(display, SWT.CLOSE | SWT.TITLE);
		shlPasswordGenerator.setSize(450, 250);
		shlPasswordGenerator.setText("Password Generator v1.0");
		shlPasswordGenerator.setLayout(null);
		
		Group grpGeneratedPassword = new Group(shlPasswordGenerator, SWT.NONE);
		grpGeneratedPassword.setBounds(10, 10, 430, 77);
		grpGeneratedPassword.setText("Generated Password");
		
		txtGeneratedPassword = new Text(grpGeneratedPassword, SWT.BORDER);
		txtGeneratedPassword.setEditable(false);
		txtGeneratedPassword.setBounds(10, 10, 408, 29);
		
		Group grpPasswordChars = new Group(shlPasswordGenerator, SWT.NONE);
		grpPasswordChars.setText("Password can include:");
		grpPasswordChars.setBounds(10, 93, 430, 86);
		
		Button chkNumbers = new Button(grpPasswordChars, SWT.CHECK);
		chkNumbers.setBounds(10, 10, 200, 20);
		chkNumbers.setText("Numbers");
		
		Button chkSmallLetters = new Button(grpPasswordChars, SWT.CHECK);
		chkSmallLetters.setText("Small Letters");
		chkSmallLetters.setBounds(216, 10, 200, 20);
		
		Button chkBigLetters = new Button(grpPasswordChars, SWT.CHECK);
		chkBigLetters.setText("Big Letters");
		chkBigLetters.setBounds(216, 36, 200, 20);
		
		Button chkSpecialChars = new Button(grpPasswordChars, SWT.CHECK);
		chkSpecialChars.setText("Special Chars");
		chkSpecialChars.setBounds(10, 36, 200, 20);
		
		Spinner inputPasswordLength = new Spinner(shlPasswordGenerator, SWT.BORDER);
		inputPasswordLength.setBounds(223, 182, 128, 35);
		inputPasswordLength.setPageIncrement(1);
		inputPasswordLength.setMaximum(50);
		inputPasswordLength.setMinimum(8);
		inputPasswordLength.setSelection(8);
		
		Link linkToDeveloper = new Link(shlPasswordGenerator, SWT.NONE);
		linkToDeveloper.setBounds(10, 197, 128, 17);
		linkToDeveloper.setText("<a href=\"http://mesuterdemir.com\">Mesut Erdemir</a>");
		linkToDeveloper.addSelectionListener(new SelectionAdapter()  {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		        Program.launch("http://mesuterdemir.com");
		    }
		    
		});
		
		Button btnNewButton = new Button(shlPasswordGenerator, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Clear Variable
		        PasswordGenerator.selectedCharSet = "";

		        // Check for Big Letter Selection
		        if (chkBigLetters.getSelection() == true)
		        {
		        	PasswordGenerator.selectedCharSet += PasswordGenerator.charsetBigLetter;
		        }
		        
		        // Check for Small Letter Selection
		        if (chkSmallLetters.getSelection() == true)
		        {
		        	PasswordGenerator.selectedCharSet += PasswordGenerator.charsetSmallLetter;
		        }
		        
		        // Check for Number Selection
		        if (chkNumbers.getSelection() == true)
		        {
		        	PasswordGenerator.selectedCharSet += PasswordGenerator.charsetNumber;
		        }
		        
		        // Check for Special Chars
		        if (chkSpecialChars.getSelection() == true)
		        {
		        	PasswordGenerator.selectedCharSet += PasswordGenerator.charsetSpecialChar;
		        }
		        
		        // If Selected Charset is empty, force to selection
		        if(PasswordGenerator.selectedCharSet.isEmpty() == true)
		        {
		        	txtGeneratedPassword.setText("Please select a charset!");
		        }
		        else
		        {
		        	// Generate Password and show
		        	txtGeneratedPassword.setText(
		        			PasswordGenerator.generateRandomPassword(
		        					Integer.parseInt(inputPasswordLength.getText()), 
		        					PasswordGenerator.selectedCharSet
        					)
        			);
		        }
			}
		});
		btnNewButton.setBounds(357, 185, 83, 29);
		btnNewButton.setText("Generate");

		shlPasswordGenerator.open();
		shlPasswordGenerator.layout();
		while (!shlPasswordGenerator.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public static String generateRandomPassword(int length, String charset) {
        Random rand = new Random(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int pos = rand.nextInt(charset.length());
            sb.append(charset.charAt(pos));
        }
        return sb.toString();
    }
}
