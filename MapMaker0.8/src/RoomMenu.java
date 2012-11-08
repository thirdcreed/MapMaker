import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;


public class RoomMenu extends JPopupMenu{

	
	JMenuItem addExitItem = new JMenuItem("Add Exit");
	JMenuItem addDescItem = new JMenuItem("Add Description");
	JMenuItem removeRoom = new JMenuItem("Remove This Room");
	JMenuItem readDesc = new JMenuItem("Read Description");
	JMenuItem changeColor = new JMenuItem("Change Room Color");
	JMenuItem duplicateRoom = new JMenuItem("Duplicate This Room");
	
	RoomComponent room;
	
	public RoomMenu(RoomComponent room){
		this.room = room;
		
		addExitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				 RoomMenu.this.room.getRoom().hasNewExit = true;
					 
				RoomMenu.this.room.getRoom().addAnExit(new Exit(RoomMenu.this.room.getRoom()));
			    
			}
			
		});
		
		addDescItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String response = javax.swing.JOptionPane.showInputDialog("Enter A Room Description");   
			    String msg = "";   
			    if(response == null) msg = "Ok, be sure every room has a description.";   
			    else if(response.equals("")) msg = "You didn't write anything???";   
			    else {msg = ""+response;   
			    javax.swing.JOptionPane.showMessageDialog(null, msg);   
			    RoomMenu.this.room.getRoom().setDescription(msg);
			    }
			    }
	});
		
		readDesc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
			String  msg = RoomMenu.this.room.getRoom().getDescription();
				    javax.swing.JOptionPane.showMessageDialog(null, msg);   
				   
				    }
				    
		});
		
		removeRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		 
		int response = javax.swing.JOptionPane.showConfirmDialog(null, "Are you sure you want" +
			    	" to remove this room?","Remove Room?", JOptionPane.YES_NO_OPTION);   
		          if (response == JOptionPane.YES_OPTION){
		        	  RoomMenu.this.room.getRoom().model.removeRoom(RoomMenu.this.room.getRoom());
		          }
			   
			    }
			    
	});
		
		changeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Color newColor = JColorChooser.showDialog(
	                     RoomMenu.this.room,
	                     "Choose Room Color",
	                     Color.gray);
				RoomMenu.this.room.setColor(newColor);

			}
			    
	});
		
		duplicateRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RoomMenu.this.room.getRoom().model.duplicateRoom(RoomMenu.this.room.getRoom());
			}
			    
	});
				
				
				
				
		this.add(addExitItem);
		this.add(addDescItem);
		this.add(removeRoom);
		this.add(duplicateRoom);
		this.addSeparator();
		this.add(readDesc);
		this.addSeparator();
		this.add(changeColor);
		
		
	}
	
	
	
}
