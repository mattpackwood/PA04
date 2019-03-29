/* Matt Packwood, Orchard Ridge Campus, Monday Evening Class, Fall Semester 2003
 * 
 * PA04: Use scrollbars to relocate and resize computer on screen.
 *
 * ~288x288 applet screen; initial CPU height= 1/20 screen ht
 * Use scrollbar event handling to control the size and location
 * of the figure.
 * 
 * Use initial base variable values and the Scrollbar definition parameter
 * values to constrain the figure size/location to keep it on the screen.
 *
 * Use Dimension class and getSize method to adjust applet to screen
 * size parameters from HTML applet tag per C6 demo 
 */
import java.awt.*;
import java.applet.*;
import java.awt.event.*; // event lib
 
public class CmptrL4 extends Applet
		implements AdjustmentListener { // scrollbar event handling
	private Scrollbar barX, barY, barH; // ref vars to Scrollbar objects...
	private int bX, bY, bS; // base vars
	private int sW, sHt; // applet screen width/Ht
	private int wHt= 60; // space for SB widgets at top of screen...
	int qS, hS, dS; // work ratio vars 
	Graphics g; // global ref for Graphic obj

public void init ( ) {
	setBackground (Color.cyan);
	Dimension size= getSize ( ); // get screen size
	sW= size.width; sHt= size.height; // set screen constraints
	bS= sHt/20; // start CPU ht to 1/20 screen ht
	bX= 0; // start bX to left constraint; adj to fig
	bY= sHt-bS; // start bY to bottom constraint 
	// define scroll bar widgets w/scrollbar definition attributes per specs
	add (new Label ("bX")); 
	barX= new Scrollbar (Scrollbar.HORIZONTAL, bX, 1, bX, sW-(bX+(5*bS))); 
	add (barX); 
	barX.addAdjustmentListener (this); 
	add (new Label ("bY")); 
	barY= new Scrollbar (Scrollbar.VERTICAL, bY, 1, (wHt+(4*bS)), bY); 
	add (barY); barY.addAdjustmentListener (this); 
	add (new Label ("bSz")); 
	barH= new Scrollbar(Scrollbar.HORIZONTAL, bS, 1, (bS/2), (sHt-wHt)/5); 
	add (barH); barH.addAdjustmentListener (this);
   	}
public void paint (Graphics hh) {
	g= hh; // set Graphic obj ref global
	showStatus ("Lab4: Computer:" + "bX="+ bX+ ", bY="+ bY+ ", Height="+ bS); // dsply title & base values
	dsplyFig ( );
	}
private void dsplyFig ( ) { 
	calcRatios ( );
	// code from PA03 without area calc  
	dsplyMonitor ( ); 
	g.setColor (Color.yellow);
	g.fillRect (bX, bY-bS, dS+dS+bS, bS); // CPU
	g.fillRect (bX, bY, dS+dS, bS); // Keyboard
	g.fillOval (bX+dS+dS+qS, bY+qS, hS, hS); // Mouse
	g.setColor (Color.black);
	g.drawRect (bX, bY-bS, dS+dS+bS, bS); // CPU outline
	g.drawRect (bX, bY, dS+dS, bS); // Keyboard outline
	g.drawOval (bX+dS+dS+qS, bY+qS, hS, hS); // Mouse outline
	g.drawLine (bX+dS+dS, bY+hS, bX+dS+dS+qS, bY+hS); // Mouse cord
	g.fillRect (bX+dS+hS, bY-hS-qS, dS, qS); // DVD slot
	g.fillRect (bX+qS, bY+qS, dS+bS, hS); // Keys
	g.fillArc (bX+dS+dS+qS, bY+qS, hS, hS, 45, 135); // Mouse 1
	g.fillArc (bX+dS+dS+qS, bY+qS, hS, hS, 225, 135); // Mouse 2
	}
private void calcRatios ( ) { 
	// code from PA03 
	qS= Math.round (bS/4.0f); // calc ratio vals
	hS= Math.round (bS/2.0f); // calc ratio vals
	dS= Math.round (bS*2.0f); // calc ratio vals
	}
private void dsplyMonitor ( ) { // ref from dsplyFig
	// code from PA03
	g.setColor (Color.yellow);
	g.fillRect (bX+hS, bY-dS-dS, dS+dS, dS+bS); // Monitor
	g.setColor (Color.black);
	g.drawRect (bX+hS, bY-dS-dS, dS+dS, dS+bS); // Monitor Outline
	g.setColor (Color.lightGray);
	g.fillRect (bX+hS+qS, bY-dS-bS-hS-qS, dS+bS+hS, dS+hS); // Screen	
	}
public void adjustmentValueChanged (AdjustmentEvent e) { // handle SB event
	// retrieve updated SB values....
	bX= barX.getValue ( ); // retrieve updated SB value; 
	bY= barY.getValue ( ); // will be 1 of the 3; 
	bS= barH.getValue ( ); // other 2 same as last update... 
	repaint ( ); // redraw with updated SB value...
	}
}
