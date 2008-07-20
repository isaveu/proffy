/*
    Copyright (C) 2008  Paul Richards.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package fractals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.OverlayLayout;
import javax.swing.event.MouseInputListener;

/**
    GUI component for creating curves as input to the
    bifurcation diagram fractal.
*/
final class CurveEditor implements ComponentListener, MouseInputListener
{
    private final JPanel panel = new JPanel();
    private final double[] controlPointValues = new double[30];
    
    CurveEditor()
    {
        panel.addComponentListener(this);
        panel.addMouseMotionListener(this);
        panel.addMouseListener(this);
        for (int i = 0; i < controlPointValues.length; i++) {
            controlPointValues[i] = 0.5;
        }
        repopulatePanel();
    }
    
    JComponent asComponent()
    {
        return panel;
    }
    
    private void repopulatePanel()
    {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        JLayeredPane layers = new JLayeredPane();
        panel.add(layers);
        layers.setLayout(new OverlayLayout(layers));
                
        //panel.add(new ShapeComponent(new Ellipse2D.Double(10, 20, 30, 40), Color.RED));
        for (int i = 0; i < controlPointValues.length; i++) {
            double x = ((i + 0.5) / controlPointValues.length) * panel.getWidth();
            double y = (1.0 - controlPointValues[i]) * panel.getHeight();
            layers.add(new ShapeComponent(new Ellipse2D.Double(x - 5, y - 5, 10, 10), Color.RED), JLayeredPane.DEFAULT_LAYER);
        }
        
        InterpolatingCubicSpline spline = new InterpolatingCubicSpline(controlPointValues);
        for (int i = 0; i < panel.getWidth(); i+=4) {
            double x = ((i + 0.5) / panel.getWidth()) * controlPointValues.length - 0.5;
            double y = (1.0 - spline.sample(x)) * panel.getHeight();
            layers.add(new ShapeComponent(new Ellipse2D.Double(i + 0.5 - 1, y - 1, 2, 2), Color.BLACK), JLayeredPane.DEFAULT_LAYER);
        }
        
        JRootPane rootPane = panel.getRootPane();
        if (rootPane != null) {
            rootPane.validate();
        }
    }
    
    private void updateControlPoint(Point p) {
        int index = (int)Math.floor((double)p.getX() * controlPointValues.length / panel.getWidth());
        index = Utilities.clamp(0, index, controlPointValues.length-1);
        controlPointValues[index] = 1.0 - ((double)p.getY() / panel.getHeight());
        repopulatePanel();
    }

    public void componentResized(ComponentEvent e) {
        repopulatePanel();
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
        repopulatePanel();
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        updateControlPoint(e.getPoint());
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        updateControlPoint(e.getPoint());
    }

    public void mouseMoved(MouseEvent e) {
    }
}