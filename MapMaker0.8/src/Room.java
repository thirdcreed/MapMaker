
import java.util.ArrayList;


public class Room {

	ArrayList<Exit> exits = new ArrayList<Exit>();
	
	private static int idMaker = 0;
	private int id;
	private String description;
	public int prefferedX;
	public int prefferedY;
	public boolean hasAPrefferedLocation = false;
	RoomComponent component;
	public boolean hasComponent = false;
	public boolean hasNewExit = false;
	MapMakerModel model;
	
	
	
	public  Room(MapMakerModel model){
	 this. model = model;
	 id = idMaker; 
	 idMaker++;
	}
	
	
	
	public void setComponent(RoomComponent rc){
		this.component = rc;
	}
	
	public RoomComponent getComponent(){
		return this.component;
	}
	
	
	 public void addAnExit(Exit exit){
	    	this.exits.add(exit);
	    	System.out.println(exit);
	    	this.model.updateAllViews();
	        
	    	
	    }
	 
	 public void removeComponent(){
		 this.component.getParent().remove(component);
	 }
	 
	 public void removeExit(Exit exit){

			for (int i=0; i < exits.size(); i++){
			if (this.exits.get(i).equals(exit)){
				
				this.exits.remove(i);
				}
		   }
		}
	 
	 public void removeAllExits(){

			for (int i=0; i < exits.size(); i++){
			    this.exits.get(i).removePair();
				this.exits.get(i).removeComponent();
				System.out.print(i);System.out.println(exits.get(i));
				this.exits.remove(i);
				
			
			}
	    }
	 
	 public String getDescription(){
		 if (this.description != null){
			 return this.description;
		 }
		 else return "NO DESCRIPTION";
	 }
	 
	 public void setDescription(String description){
		 this.description = description;
	 }



	public  String getId() {
		return "" + id;
	}

	public ArrayList<Exit> getExits(){
		return exits;
	}
	
	public void setExits(ArrayList<Exit> myExits){
		exits = myExits;
	}
	
	public void setPrefferedLocation(int x, int y){
		this.prefferedX = x;
		this.prefferedY = y;
		this.hasAPrefferedLocation = true;
	}


	
		
	
	
	
}
