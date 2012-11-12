import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;




@SuppressWarnings("serial")
public class MapMakerMenu extends JMenuBar {
    MapMakerModel model;
	JMenu fileMenu = new JMenu();
	JMenuItem openItem = new JMenuItem();
	JMenuItem saveItem = new JMenuItem();
	JMenuItem exitItem = new JMenuItem();
	JMenu editMenu = new JMenu();
	JMenuItem undoItem = new JMenuItem();
	JMenuItem redoItem = new JMenuItem();
	JMenuItem newRoomItem = new JMenuItem();
	JMenu helpMenu = new JMenu();
	JMenuItem helpItem = new JMenuItem();
	JMenuItem aboutItem = new JMenuItem();

	
      public MapMakerMenu(MapMakerModel model){
    	  super();
    	  this.model = model;
			fileMenu = new JMenu("File");
			fileMenu.setMnemonic(KeyEvent.VK_F);
			fileMenu.getAccessibleContext().setAccessibleDescription(
			        "File");
			

		
			
			//OPEN
			openItem = new JMenuItem("Open",
			                         KeyEvent.VK_O);
			openItem.setAccelerator(KeyStroke.getKeyStroke(
			        KeyEvent.VK_O, ActionEvent.ALT_MASK));
			openItem.getAccessibleContext().setAccessibleDescription(
			        "");
			
			//SAVE
			saveItem = new JMenuItem("Save",
	                KeyEvent.VK_S);
			saveItem.setAccelerator(KeyStroke.getKeyStroke(
					KeyEvent.VK_S, ActionEvent.CTRL_MASK));
			saveItem.getAccessibleContext().setAccessibleDescription(
					"");
			saveItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					final JFileChooser fc = new JFileChooser();
					
					int returnVal = fc.showSaveDialog(MapMakerMenu.this);
					
					    //Handle open button action.
					    
					       

					        if (returnVal == JFileChooser.APPROVE_OPTION) {
					            File file = fc.getSelectedFile();
					            MapMakerMenu.this.model.save(file);
					            
					        } else {
					           
					        }
					   
					

					
					
				}
				
			});
			
			
			//EXIT
			exitItem = new JMenuItem("Exit",
	                KeyEvent.VK_X);
			exitItem.setAccelerator(KeyStroke.getKeyStroke(
					KeyEvent.VK_X, ActionEvent.ALT_MASK));
			exitItem.getAccessibleContext().setAccessibleDescription(
					"");
			exitItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {System.exit(0);}
			       
			});
			
			
			
			fileMenu.add(openItem);
			fileMenu.add(saveItem);
			fileMenu.add(exitItem);
	  //EDIT
			editMenu = new JMenu("Edit");
			editMenu.setMnemonic(KeyEvent.VK_F);
			editMenu.getAccessibleContext().setAccessibleDescription(
			        "Edit");
			
			//UNDO
					undoItem = new JMenuItem("Undo",
					                         KeyEvent.VK_Z);
					undoItem.setAccelerator(KeyStroke.getKeyStroke(
					        KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
					undoItem.getAccessibleContext().setAccessibleDescription(
					        "");
					
					//SAVE saves as an XML file
					redoItem = new JMenuItem("Redo",
			                KeyEvent.VK_Y);
					redoItem.setAccelerator(KeyStroke.getKeyStroke(
							KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
					redoItem.getAccessibleContext().setAccessibleDescription(
							"");
					
					
					//EXIT
					newRoomItem = new JMenuItem("New Room",
			                KeyEvent.VK_N);
					newRoomItem.setAccelerator(KeyStroke.getKeyStroke(
							KeyEvent.VK_N, ActionEvent.CTRL_MASK));
					newRoomItem.getAccessibleContext().setAccessibleDescription(
							"");
					newRoomItem.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							MapMakerMenu.this.model.addARoom();
							
						}
						
					});
					
					
					
					editMenu.add(undoItem);
					editMenu.add(redoItem);
					editMenu.addSeparator();
					editMenu.add(newRoomItem);
					
					
	//HELP
					helpMenu = new JMenu("Help");
					helpMenu.setMnemonic(KeyEvent.VK_H);
					helpMenu.getAccessibleContext().setAccessibleDescription(
					        "Help");
					
					     //HELP
							helpItem = new JMenuItem("Help",
							                         KeyEvent.VK_F1);
							helpItem.setAccelerator(KeyStroke.getKeyStroke(
							        KeyEvent.VK_H, ActionEvent.CTRL_MASK));
							helpItem.getAccessibleContext().setAccessibleDescription(
							        "");
							
							//ABOUT
							aboutItem = new JMenuItem("About",
					                KeyEvent.VK_Y);
							aboutItem.setAccelerator(KeyStroke.getKeyStroke(
									KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
							aboutItem.getAccessibleContext().setAccessibleDescription(
									"");
							
					
					
					helpMenu.add(helpItem);
					helpMenu.add(aboutItem);
				
					
					
					this.add(fileMenu);
					this.add(editMenu);
					this.add(helpMenu);
}
      
      
}
