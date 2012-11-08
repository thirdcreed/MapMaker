
import java.awt.Dimension;
import java.awt.Point;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import com.sl.connector.ConnectorContainer;
import becker.util.IView;


@SuppressWarnings("serial")
public class MapMakerView extends JPanel implements IView {

	private MapMakerModel model;
	private ConnectorContainer mapBoard = new ConnectorContainer();
    //private JScrollPane scroll;
    private JViewport viewport;
	
	
	
	
	public MapMakerView(MapMakerModel model){
		super();
		this.model = model;
		this.model.addView(this);
		this.layoutView();
		this.updateView();
		this.registerControllers();
		
	}
	private void layoutView() {
		
		
		Dimension prefSize = new Dimension(9000,9000);
		mapBoard.setPreferredSize(prefSize);
		mapBoard.setOpaque(true);
		mapBoard.setBorder(BorderFactory.createTitledBorder("Map Board"));
		mapBoard.setLayout(null);
		JScrollPane scroll = new JScrollPane (mapBoard,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		viewport = scroll.getViewport();		
		
		//this.add(mapBoard);
        this.add(scroll);
	}
	
	
	@Override
	public void updateView() {
		
		 for ( int i = 0; i < model.getRooms().size();i++ ){
			
			 if (!model.getRooms().get(i).hasComponent){
			 RoomComponent roomC = new RoomComponent(model.getRooms().get(i));
			 
			 

			 Point scrolledTo = viewport.getViewPosition();
			 if(!model.getRooms().get(i).hasAPrefferedLocation){
					 roomC.setLocation(scrolledTo);}
			 else{roomC.setLocation(new Point(model.getRooms().get(i).prefferedX,model.getRooms().get(i).prefferedY));}
			 model.getRooms().get(i).setComponent(roomC);
			 mapBoard.add(roomC);
			 
			 for ( int j = 0; j < model.getRooms().get(i).exits.size();j++ )
			   { ExitComponent exitC = new ExitComponent(model.getRooms().get(i).exits.get(j));
				 
			     model.getRooms().get(i).exits.get(j).setComponent(exitC);
				 model.getRooms().get(i).exits.get(j).hasComponent = true;
				 if  (model.getRooms().get(i).exits.get(j).hasPreffered){
					 System.out.print(model.getRooms().get(i).exits.get(j).prefferedX);
					 exitC.setLocation(new Point( model.getRooms().get(i).exits.get(j).prefferedX, model.getRooms().get(i).exits.get(j).prefferedY));
				 }else{
				 exitC.setLocation(exitC.getExit().room.getComponent().getLocation());
				 }
				 mapBoard.add(exitC);
				 mapBoard.setComponentZOrder(exitC, 0);
				 }
			 
			 model.getRooms().get(i).hasComponent = true;
			
			 }
			 else {
				 if (model.getRooms().get(i).hasNewExit){
				 for ( int j = 0; j < model.getRooms().get(i).exits.size();j++ )
				   { 
					 if(!model.getRooms().get(i).exits.get(j).hasComponent){
					 ExitComponent exitC = new ExitComponent(model.getRooms().get(i).exits.get(j));
					 if  (model.getRooms().get(i).exits.get(j).hasPreffered){
                     exitC.setLocation( model.getRooms().get(i).getComponent().getLocation().x, model.getRooms().get(i).getComponent().getLocation().y);
					 } else{
						 exitC.setLocation( model.getRooms().get(i).getComponent().getLocation().x + model.getRooms().get(i).exits.get(j).prefferedX, model.getRooms().get(i).getComponent().getLocation().y + model.getRooms().get(i).exits.get(j).prefferedY);
					 }
                     model.getRooms().get(i).exits.get(j).setComponent(exitC);
					mapBoard.add(exitC);
					mapBoard.setComponentZOrder(exitC, 0);
					 model.getRooms().get(i).exits.get(j).hasComponent = true;
					 
					 }
					
					 
					 }
				 model.getRooms().get(i).hasNewExit = false;
				 } 
			 }
			 model.setConnectors();
			 mapBoard.setConnectors(model.getConnectors()); 
			 
		 }
		 
		mapBoard.repaint();
		this.repaint();
		repaint();
	}
	
	
	
	public void registerControllers(){
		//this.newRoom.addActionListener(new newRoomController());
		this.mapBoard.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.isMetaDown() == true){
				model.addARoom();
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
				}

			@Override
			public void mousePressed(MouseEvent e) {
				if(e.isControlDown()){
			
				}
				
			}
			

			@Override
			public void mouseReleased(MouseEvent e) {
			 
				
			} 
		});
	    
	    this.mapBoard.addMouseMotionListener(new MouseMotionListener() {
	           
	    	
	    	
			@Override
			public void mouseDragged(MouseEvent e) {
				
				if (e.isControlDown()){
    			
    			}
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				
				
			}
	    	
	    	
	    	
	    });
		
		}
	
			
	}


