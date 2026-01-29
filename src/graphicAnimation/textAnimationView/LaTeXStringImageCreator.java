/**
 * 
 */
package graphicAnimation.textAnimationView;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JLabel;

import org.scilab.forge.jlatexmath.ParseException;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import proplogic.formula.Formula;

/**
 * @author user
 * @since 2020/08/21
 * 
 * Create an image for a formula. The image renders the formula as a good-looking mathematical formula.
 *
 */
public class LaTeXStringImageCreator {

	public LaTeXStringImageCreator() {
	}

	public static BufferedImage createImage(String formulaString, Color bg, Color fg, float fontSize) throws ParseException {
		// create a formula
		TeXFormula formula = new TeXFormula(formulaString);
		TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, fontSize);
		
		// insert a border 
		icon.setInsets(new Insets(0, 0, 0, 0)); 

		// now create an actual image of the rendered equation
		BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2 = image.createGraphics();
		if (bg != null) {
			g2.setColor(bg);
			g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
		}
		JLabel jl = new JLabel();
		if (fg != null) jl.setForeground(fg); 
		else jl.setForeground(Color.BLACK);
		icon.paintIcon(jl, g2, 0, 0);
		// at this point the image is created, you could also save it with ImageIO

		return image;
	}
	
	public static BufferedImage createImage(Formula formula, Color bg, Color fg, float fontSize) throws ParseException {
		String formulaString = formula.toLaTeXString();
		return createImage(formulaString, bg, fg, fontSize);
	}
	
	public static BufferedImage createImage(String formulaString, JComponent place) {
		Color bg = place.getBackground();
		Color fg = place.getForeground();
		float fontSize = (float)1.5 * place.getFont().getSize();
		return createImage(formulaString, bg, fg, fontSize);
	}
	
	public static BufferedImage createImage(Formula formula, JComponent place) {
		String formulaString = formula.toLaTeXString();
		Color bg = place.getBackground();
		Color fg = place.getForeground();
		float fontSize = (float)1.5 * place.getFont().getSize();
		return createImage(formulaString, bg, fg, fontSize);
	}
}
