package talk.view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.bind.JAXB;

import talk.Talk;
import talk.setup.Data;

public class SetupFrame extends JFrame {
	
	private JPanel panel;
	private JComboBox<String> xmlToUse;
	private JButton okButton;
	
	List<File> setupFiles;
	
	public SetupFrame() {
		
		xmlToUse = new JComboBox<String>();
		
		setupFiles = getListOfSetupFiles();
		
		for (File f : setupFiles) {
			xmlToUse.addItem(f.getPath());
		}
		
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
		okButton = new JButton("Ok");
		okButton.addActionListener(
			(arg0) -> 
			{
				loadSetupFile();
			}
		);
		
		panel.add(xmlToUse);
		panel.add(okButton);
		
		add(panel, BorderLayout.NORTH);
	}
	
	
	private List<File> getListOfSetupFiles() {
		
		File folder = new File("./data");

		File[] listOfXML = folder.listFiles(
			(dir, name) -> 
			{
				return name.toLowerCase().endsWith(".xml");
			}
		);
		
		return Arrays.asList(listOfXML);
	}
	
	private void loadSetupFile() {
		
		String selectedItemName = (String) xmlToUse.getSelectedItem();
		
		File file = new File(selectedItemName);
		Data data = JAXB.unmarshal( file, Data.class );
		Talk.start(data);
		print(data.toString());
		
		setVisible(false);
		dispose();
	}
	
	private void print(String s) {
		if(Talk.PRINT) {
			System.out.println(s);
		}
	}
}
