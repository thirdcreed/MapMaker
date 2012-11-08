
 
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import becker.util.IView;

import com.sl.connector.JConnector;
 
public class XmlWriter {
 
	
	public  void writeRoom(Room room) {}

	public void write( ArrayList<Room> rooms, File file) {

		 
		  try {
	 
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// root elements
			Document doc = docBuilder.newDocument();
			
			Element rootElement = doc.createElement("map");
			doc.appendChild(rootElement);
	 
			// staff elements
			for (int i = 0; i < rooms.size(); i++){
			Element roomElement = doc.createElement("room");
			rootElement.appendChild(roomElement);
	 
			// staff elements
			Element id = doc.createElement("id");
			id.appendChild(doc.createTextNode(rooms.get(i).getId()));
			roomElement.appendChild(id);
			
			
			Element descriptionElement = doc.createElement("description");
			descriptionElement.appendChild(doc.createTextNode(rooms.get(i).getDescription()));
			roomElement.appendChild(descriptionElement);
			
			Element exitsElement = doc.createElement("exits");
		     roomElement.appendChild(exitsElement);
		     
			  for (int j = 0; j < rooms.get(i).exits.size();j++){
			     
			  if (rooms.get(i).exits.get(j).hasPair){
			     Element pairElement = doc.createElement("pair");
			     pairElement.setAttribute("face",rooms.get(i).exits.get(j).getFaceString());
			     pairElement.appendChild(doc.createTextNode(rooms.get(i).exits.get(j).pair.room.getId()));
			 
			     exitsElement.appendChild(pairElement);
			  }
			   }
			   
			Element NPCElement = doc.createElement("npcs");
			roomElement.appendChild(NPCElement);
			}
			
	 
	 
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
	 
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
	 
			transformer.transform(source, result);
	 
			System.out.println("File saved!");
	 
		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
		
		
	}
	
}
