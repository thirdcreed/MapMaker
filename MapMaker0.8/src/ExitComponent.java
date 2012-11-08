import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;



@SuppressWarnings("serial")
public class ExitComponent extends JComponent {
 
	private volatile int screenX = 0;
	private volatile int screenY = 0;
    @SuppressWarnings("unused")
	private volatile int myX = 0;
    @SuppressWarnings("unused")
	private volatile int myY = 0;
    public volatile int lineX = 0;
    public volatile int lineY = 0;
    public volatile int lineX2 = 0;
    public volatile int lineY2 = 0;
    public volatile int deltaX;
    public volatile int deltaY;
    public volatile int parentX;
    public volatile int parentY;
    public volatile int xInSquare = 0;
    public volatile int yInSquare = 0;
	
   // public JConnector line;
    public static ExitComponent lastClicked;
    private Color color = Color.white;
    public static boolean first = true;
    public JFrame frame = new JFrame();
    private Exit exit;
    public boolean isConstrained = false;
   
    
    
    public ExitComponent(Exit exit2) {

    	
    	setBorder(new LineBorder(Color.BLACK, 1));
    	setBackground(Color.WHITE);
    	setBounds(0,0,10,10);
    	setOpaque(true);
    	
        Dimension dim = new Dimension(10,10);
        this.setPreferredSize(dim);
        setExitForReport(exit2);
        //this.exit.setComponent(this);
        frame.setTitle("LINK FAULT");
        
    addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			 if(e.isControlDown()){
			    	
				 if(first){
					 
				    ExitComponent.this.color = (Color.blue);
			    	if ( ExitComponent.this.exit.hasPair){
			    		
			    		ExitComponent.this.exit.pair.hasPair = false;
			    		ExitComponent.this.exit.hasPair = false;
			    		ExitComponent.this.exit.pair.setPair(null);
			    		ExitComponent.this.exit.setPair(null);
			    		
			    	}
				    lastClicked = ExitComponent.this;
			    	first = !first;
			    	
			    	ExitComponent.this.repaint();
			    	}
				 else{
			    	if (!ExitComponent.this.exit.hasPair){
			    	ExitComponent.this.exit.setPair(lastClicked.exit);
			    	lastClicked.exit.setPair(ExitComponent.this.exit);
			    	ExitComponent.this.exit.hasPair = true;
			    	lastClicked.exit.hasPair = true;
			    	
			        first = !first;
			        
			    	lastClicked.color = Color.white;
			    	ExitComponent.this.color = Color.white;
			    	lastClicked.repaint();
			    	ExitComponent.this.repaint();
			    
			    	ExitComponent.this.getTopLevelAncestor().repaint();
			    	
			    	}else{JOptionPane.showMessageDialog(frame,
	    			    "That exit is already linked", "LINK FAULT", JOptionPane.ERROR_MESSAGE);
	    		lastClicked.color = Color.white;
	    		first = !first;
	    		lastClicked.repaint();
	    		
			    		
			    	}
				 }
				 ExitComponent.this.exit.room.model.updateAllViews();
			    		
			    	
			    } 
			
		
	}
		@Override
		public void mouseEntered(MouseEvent e) {
			
			 if(ExitComponent.this.exit.hasPair){
				ExitComponent.this.color = Color.red;
				ExitComponent.this.repaint();
			 }
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(ExitComponent.this.exit.hasPair){
				ExitComponent.this.color = Color.white;
				ExitComponent.this.repaint();
			 }
		}

		@Override
		public void mousePressed(MouseEvent e) {
			screenX = e.getXOnScreen();
			screenY = e.getYOnScreen();
		    myX = getX();
		    myY = getY();
		    
			ExitComponent.this.repaint();
		}
		

		@Override
		public void mouseReleased(MouseEvent e) {
			
		    xInSquare = getLocation().x - parentX;
		    yInSquare = getLocation().y - parentY;
		    ExitComponent.this.color = Color.WHITE;
		    ExitComponent.this.repaint();
		    if (e.isControlDown()){
		    ExitComponent.this.color = Color.WHITE;
		    }
		} 
	});
    
    addMouseMotionListener(new MouseMotionListener() {
           
    	@Override
		public void mouseDragged(MouseEvent e) {
			 parentX = ExitComponent.this.exit.room.getComponent().getLocation().x;
			 parentY = ExitComponent.this.exit.room.getComponent().getLocation().y;
			int deltaX = e.getXOnScreen()  -   screenX;
			int deltaY = e.getYOnScreen()  -  screenY;
			final int ROOM_WIDTH = 100;
			final int ROOM_HEIGHT = 100;
			final int PADDING = 10;
			final int NORTH = 0;
			final int EAST = 1;
			final int SOUTH = 2;
			final int WEST = 3;
			int eastMax = parentX + ROOM_WIDTH - PADDING;
			int westMax = parentX;
			int southMax = parentY + ROOM_HEIGHT - PADDING;
			int northMax = parentY;
			int xBounds;
			int yBounds;
			
			
			if (e.isControlDown()){
				
			
			}
			 
			else{
				if (ExitComponent.this.exit.getFace() == NORTH){
				setLocation(parentX + xInSquare + deltaX, parentY );
				 yInSquare = getLocation().y - parentY;
				deltaY = 0 ;//+ PADDING;
				xBounds = ExitComponent.this.getLocation().x;
				
				eastMax = parentX + ROOM_WIDTH - PADDING;
				
				 if (xBounds >= eastMax  ){
					 deltaY = e.getYOnScreen()  -   screenY;
					  setLocation(eastMax,parentY);
					  ExitComponent.this.exit.setFace(EAST);
					  System.out.println("0->1");
				  }
				 
				 if (xBounds <= westMax  ){
					 
					 
					  setLocation(westMax,parentY );
					  deltaY = e.getYOnScreen()  -   screenY;
					  ExitComponent.this.exit.setFace(WEST);
					  System.out.println("0->3");
				  }
				 
				}
				
				 if (ExitComponent.this.exit.getFace() == EAST){
						setLocation(parentX + ROOM_WIDTH - PADDING,parentY + yInSquare + deltaY);
						deltaX = 0;
						yBounds = ExitComponent.this.getLocation().y;
						
						southMax = parentY + ROOM_HEIGHT - PADDING;
						 
						if (yBounds >= southMax ){
							 deltaX = e.getXOnScreen()  -   screenX;
							 setLocation(parentX + ROOM_WIDTH - PADDING,southMax);
							  ExitComponent.this.exit.setFace(SOUTH);
							  System.out.println("1->2");
						}
						
						if (yBounds <= northMax ){
							 deltaX = e.getXOnScreen()  -   screenX;
							 setLocation(parentX + ROOM_WIDTH - PADDING,northMax);
							  ExitComponent.this.exit.setFace(NORTH);
							  System.out.println("1->0");
						}
			   }
				 
				 if (ExitComponent.this.exit.getFace() == SOUTH){
						setLocation(parentX + xInSquare + deltaX, parentY + ROOM_HEIGHT - PADDING );
						deltaY = 0 ;
						xBounds = ExitComponent.this.getLocation().x;
						
						westMax = parentX;
						
						 if (xBounds <= westMax  ){
							 
							 
							  setLocation(westMax,parentY + ROOM_HEIGHT - PADDING);
							  deltaY = e.getYOnScreen()  -   screenY;
							  ExitComponent.this.exit.setFace(WEST);
							  System.out.println("2->3");
						  }
						 
						 if (xBounds >= eastMax  ){
							 deltaY = e.getYOnScreen()  -   screenY;
							  setLocation(eastMax ,parentY + ROOM_HEIGHT - PADDING);
							  ExitComponent.this.exit.setFace(EAST);
							  System.out.println("2->1");
						  }
						}
				 
				 if (ExitComponent.this.exit.getFace() == WEST){
						setLocation(parentX ,parentY + yInSquare + deltaY);
					
						deltaX = 0;
						yBounds = ExitComponent.this.getLocation().y;
						
						northMax = parentY;
						
						 
						if (yBounds <= northMax ){
							 deltaX = e.getXOnScreen()  -   screenX;
							 setLocation(parentX,northMax);
							  ExitComponent.this.exit.setFace(NORTH);
							  System.out.println("3->0");
						}
						if (yBounds >= southMax ){
							 deltaX = e.getXOnScreen()  -   screenX;
							 setLocation(parentX ,southMax);
							  ExitComponent.this.exit.setFace(SOUTH);
							  System.out.println("3->2");
						}
				}
			
		}
		ExitComponent.this.repaint();
		ExitComponent.this.getTopLevelAncestor().repaint();
	}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			
		}
    	
    	
    	
   });
	
  }
    
    
    
    public void setExitForReport(Exit exit){
    	this.exit = exit;
    }
    
    public Exit getExit(){
		return exit;
    }
    
    
    
  
    public void paintComponent(Graphics g){
 	   super.paintComponent(g);
 	   parentX = ExitComponent.this.exit.room.getComponent().getLocation().x;
		 parentY = ExitComponent.this.exit.room.getComponent().getLocation().y;
 	   
 	   g.setColor(this.color);
 	   g.fillRect(0, 0, 10, 10);
 	   g.setColor(Color.BLACK);
 	   g.drawRect(0,0,10,10);

       }
}