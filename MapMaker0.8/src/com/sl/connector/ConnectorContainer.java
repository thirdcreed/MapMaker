package com.sl.connector;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The collateral class contains array of connectors and renders them.
 * The rendering can be called in a different way. E.g. JConnectors cn be just
 * added as usual component. In this case programmer must care about their size,
 * and layout.
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * @author Stanislav Lapitsky
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ConnectorContainer extends JPanel implements Scrollable {
	ArrayList<JConnector> connectors;
    public ConnectorContainer() {
    }

    public ConnectorContainer(ArrayList<JConnector> connectors) {
        this.connectors = connectors;
    }

    public void setConnectors(ArrayList<JConnector> arrayList) {
        this.connectors = arrayList;
    }

    public ArrayList<JConnector> getConnectors() {
        return connectors;
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (connectors != null) {
            for (int i = 0; i < connectors.size(); i++) {
                if (connectors.get(i) != null) {
                    connectors.get(i).paint(g);
                    
                }
            }
        }
    }

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		// TODO Auto-generated method stub
		return new Dimension(900,900);
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return 30;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return 10;
	}
}
