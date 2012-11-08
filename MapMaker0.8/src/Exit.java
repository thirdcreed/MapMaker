
public class Exit {

	private int face;
	public Room room;
	public Exit pair;


    ExitComponent component;
	public boolean hasComponent = false;
	public boolean hasPair = false;
	public int prefferedX;
	public int prefferedY;
	public int prefferedFace;
	public boolean hasPreffered = false;

	public Exit(Room room){
		setFace(0);
		this.room = room;
		
	}
	
	public void setPrefferedLocation(int x, int y, int face){
     this.prefferedX = x;
     this.prefferedY = y;
     this.prefferedFace = face;
     this.hasPreffered = true;
	}
	
	
	public void setComponent(ExitComponent rc){
		this.component = rc;
	}
	
	public ExitComponent getComponent(){
		return this.component;
	}
	
	 public void removeComponent(){
		 this.component.getParent().remove(component);
		 
	 }

	public int getFace() {
		return face;
	}
    
	public String getFaceString(){
		if (face == 0) {return "n";}
		if (face == 1) {return "e";}
		if (face == 2) {return "s";}
		else  {return "w";}
	}
	public void setFace(int face) {
		this.face = face;
	}
	
	public void removePair(){
		if (this.pair != null) {
			this.pair.hasPair = false;
			this.hasPair = false;
			this.pair.setPair(null);
			this.setPair(null);
		}
		
	
		
	}
	
	
	public void setPair(Exit exit){
		this.pair = exit;
	}
	
	
	
	
}
