
import java.io.File;
import java.util.ArrayList;

import com.sl.connector.JConnector;


import becker.util.IModel;
import becker.util.IView;


public class MapMakerModel extends Object implements IModel {

	
	
	private ArrayList<IView> views = new ArrayList<IView>();
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<JConnector> connectors = new ArrayList<JConnector>();
	//public ArrayList<ConnectLine> lines = new ArrayList<ConnectLine>();
    private XmlWriter writer = new XmlWriter();
	@Override
	public void addView(IView view) {
		this.views.add(view);
		
	}

	@Override
	public void removeView(IView view) {
		this.views.remove(view);
		
	}

	@Override
	public void updateAllViews() {
		for (IView view : this.views){
			view.updateView();
		}
		
	}
	
	public void addARoom(){
		Room room = new Room(this);
		room.addAnExit(new Exit(room));
		this.rooms.add(room);
		this.updateAllViews();
	}
	

/*	public void addAnExit(Room room, Exit exit){
		room.exits.add(exit);
		this.updateAllViews();
	}*/
	
	
	public ArrayList<Room> getRooms(){
		return this.rooms;
	}

	public ArrayList<JConnector> getConnectors() {
		
		return this.connectors;
	}

	public void setConnectors() {
		
		connectors.clear();
		for (int i = 0; i < rooms.size(); i++){
			
			for (int j = 0; j < rooms.get(i).exits.size(); j++){
				
				if (rooms.get(i).exits.get(j).hasPair){
			        
					ExitComponent from = rooms.get(i).exits.get(j).getComponent();
					ExitComponent to = rooms.get(i).exits.get(j).pair.getComponent();
					JConnector connection = new JConnector(from,to);
					addAConnection(connection);
					
				}
				
				
			}
			
		}
		
	}
	
	public void addAConnection(JConnector connection){
		this.connectors.add(connection);
	}
	public void removeAConnection(JConnector line){
		for (int i=0; i <= connectors.size(); i++){
			if (this.connectors.get(i).equals(line)){
				this.connectors.remove(i);
				
			}
		   }
		
	}
	public void removeRoom(Room room){
		for (int i=0; i < rooms.size(); i++){
		if (this.rooms.get(i).equals(room)){
			this.rooms.get(i).removeAllExits();
			this.rooms.get(i).removeComponent();
			
			this.rooms.remove(i);
			updateAllViews();
			
		}
	   }
	}
	
	public void duplicateRoom(Room room){
	Room clonedRoom = new Room(this);
    clonedRoom.setDescription(room.getDescription());
    for (int i = 0; i < room.exits.size(); i++){
        Exit tempExit = new Exit(clonedRoom);
        tempExit.setPrefferedLocation(room.exits.get(i).component.xInSquare,room.exits.get(i).component.yInSquare,room.exits.get(i).getFace());
        System.out.print(clonedRoom.hasAPrefferedLocation);
        clonedRoom.addAnExit(new Exit(clonedRoom));
        clonedRoom.hasNewExit = true;
    }
    this.rooms.add(clonedRoom);
    updateAllViews();
		
		
	}
	public void save(File file){
		writer.write(rooms,file);
	}
	
	
	

}
