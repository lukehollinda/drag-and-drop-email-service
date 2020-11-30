import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.dnd.DropTarget;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame{


	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {

		new MainFrame();
	}

	public MainFrame()
	{
		super("Drag and drop frame");
		
		this.setSize(250,150);
		JLabel myLabel = new JLabel();
		myLabel.setIcon(new ImageIcon("Drag&Drop_Icon.jpg"));
		myLabel.setBackground(Color.BLACK);
		DragDropListener myListener = new DragDropListener();
		
		//Connect the label and listener 
		new DropTarget(myLabel, myListener);
		
		this.getContentPane().add(BorderLayout.CENTER, myLabel);
		this.getContentPane().setBackground(Color.BLACK);

		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
}
