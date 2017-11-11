package org.provaHubFintech.view;

import java.awt.BorderLayout;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class WebBrowser extends JFrame implements HyperlinkListener{

	private static final long serialVersionUID = -4222939053884781184L;
	//private JTextField txtURL = new JTextField();
	JEditorPane ep = new JEditorPane();
	private JLabel lblStatus = new JLabel();
	
	public WebBrowser() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel pnURL = new JPanel();
		pnURL.setLayout(new BorderLayout());
		getContentPane().add(pnURL, BorderLayout.NORTH);
		getContentPane().add(ep, BorderLayout.CENTER);
		getContentPane().add(lblStatus, BorderLayout.SOUTH);
		try {
		ep.setPage("file:///C:\\Users\\junic\\git\\provaHubFintech\\WebContent\\index.html");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(WebBrowser.this, "Browser problem: " + e.getMessage());
		}
		setSize(500, 500);
		setVisible(true);
	}
	
	public void hyperlinkUpdate(HyperlinkEvent hle) {
		HyperlinkEvent.EventType evtype = hle.getEventType();
		if (evtype == HyperlinkEvent.EventType.ENTERED) {
			lblStatus.setText(hle.getURL().toString());
		} else if (evtype == HyperlinkEvent.EventType.EXITED) {
			lblStatus.setText(" ");
		}
	}
	
	public static void main(String[] args) {
		new WebBrowser();
	}

}