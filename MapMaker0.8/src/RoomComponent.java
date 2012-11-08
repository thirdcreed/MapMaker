import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;


@SuppressWarnings("serial")
public class RoomComponent extends JComponent {
 
	private volatile int screenX = 0;
	private volatile int screenY = 0;
    private volatile int myX = 0;
    private volatile int myY = 0;
    private Room room;
    public boolean isConstrained = false;
    private RoomMenu popup = new RoomMenu(this);
    private Color myColor = Color.gray;
   
    
    public RoomComponent(Room room) {
    	setBorder(new LineBorder(Color.BLACK, 3));
    	setBackground(Color.BLACK);
    	setBounds(0,0,100,100);
    	setOpaque(true);
        Dimension dim = new Dimension(100,100);
        this.setPreferredSize(dim);
        this.room = room;
        
    
    addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.isMetaDown()){
				
			    	 popup.show(e.getComponent(),
		             e.getX(), e.getY());
			    
				/*RoomComponent.this.room.hasNewExit = true;
				RoomComponent.this.room.addAnExit(new Exit(RoomComponent.this.room));*/
				
			RoomComponent.this.repaint();
			RoomComponent.this.getTopLevelAncestor().repaint();
		    RoomComponent.this.room.model.updateAllViews();
			   
			
			}
		
			RoomComponent.this.repaint();
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		    
		    	screenX = e.getXOnScreen();
				screenY = e.getYOnScreen();
			    myX = getX();
			    myY = getY();
		    
		}
		

		@Override
		public void mouseReleased(MouseEvent arg0) {
			
			
		} 
		
		public void maybeShowPopup(MouseEvent e){
			 

		}
	});
    
    addMouseMotionListener(new MouseMotionListener() {

		@Override
		public void mouseDragged(MouseEvent e) {
			int deltaX = e.getXOnScreen() - screenX;
			int deltaY = e.getYOnScreen() - screenY;
			
			setLocation(myX + deltaX, myY + deltaY);
			for (int i = 0 ; i < RoomComponent.this.room.exits.size(); i++){
				RoomComponent.this.room.exits.get(i).getComponent().setLocation(myX + deltaX + RoomComponent.this.room.exits.get(i).getComponent().xInSquare,myY + deltaY + RoomComponent.this.room.exits.get(i).getComponent().yInSquare );
			    RoomComponent.this.room.exits.get(i).getComponent().getTopLevelAncestor().repaint();
			}
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			
			
		}
    	
    	
    	
    });
	
    }
    
    public void setRoomForReport(Room room){
    	this.room = room;
    }
  
    public void paintComponent(Graphics g){
 	   super.paintComponent(g);
 	   
 	   g.setColor(myColor);
 	   g.fillRect(0, 0, 100, 100);
 	   g.setColor(Color.BLACK);
 	   g.drawRect(0,0,100,100);
 	   
 	   
    }
    
    public Room getRoom(){
    	return this.room;
    }
    
    public void setColor(Color color){
    	this.myColor = color;
    }
 



}