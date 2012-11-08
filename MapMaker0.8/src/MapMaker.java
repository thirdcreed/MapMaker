



import javax.swing.JFrame;




public class MapMaker {
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MapMakerModel model = new MapMakerModel();
		MapMakerView view = new MapMakerView(model);
		JFrame f = new JFrame();
		//
		f.setTitle("Map Maker");
		f.setSize(1000,1000);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(view);
		f.setVisible(true);
		f.setResizable(false);
		
	    MapMakerMenu menu = new MapMakerMenu(model);
		f.setJMenuBar(menu);
		
		
		

	}

}
