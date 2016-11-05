package talk.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import talk.Talk;

public class MainFrame implements ActionListener {
	
	private JFrame frame = new JFrame();
	
	private JPanel panel;
	private JTextField input;
	private JButton send;
	private JTextArea output;
	
	
	public MainFrame(String userName) {
		super();

		frame.setTitle("Talk - " + userName);
		
		panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
		input = new JTextField(40);
		send = new JButton("Senden");
		
		input.setFont(new Font("Monospaced", Font.PLAIN, 14));
		panel.add(input);
		
		send.addActionListener(this);
		panel.add(send);
		
		frame.add(panel, BorderLayout.NORTH);
		
		output = new JTextArea(10,45);
		output.setFont(new Font("Monospaced", Font.PLAIN, 14));
		output.setLineWrap(true);
		output.setWrapStyleWord(true);
		output.setEditable(false);
		frame.add(new JScrollPane(output), BorderLayout.CENTER);

		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent e) {
				Talk.close();
			}
		});

		frame.pack();
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String message = input.getText();
		Talk.sendMessage(message);

		input.requestFocus();
	}
	
	public void appendText(String text) {
		output.append(text);
		output.append("\n");
	}

}
