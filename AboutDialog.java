package gui;

import static gui.CalculatorWindow.STRINGS;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
/**
 * Custom aboutDialog for the "about" section of the help bar.
 * 
 * @author Alec Vietry
 * @version 11-2-23
 */
public class AboutDialog extends JDialog
{
  //private static final String ABOUT = "About";
  private static final long serialVersionUID = 1L;
  
  /**
   * Creates an about dialog message with custom format. 
   * 
   * @param owner on god
   */
  public AboutDialog(final Frame owner)
  {
    super(owner, STRINGS.getString("ABOUT_TWO"), true);
    Container contentPane;
    GridBagConstraints gbc;
    GridBagLayout gbl;
    JLabel imageLabel, title, version;
    JTextPane description;
    
    contentPane = getContentPane();   
    gbl = new GridBagLayout();
    contentPane.setLayout(gbl);
    
    setSize(350,200);
    
    // Sets the title
    title = new JLabel(STRINGS.getString("ABOUT"));
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    //gbc.weighty = 10;
    gbc.anchor = GridBagConstraints.NORTH;
    gbl.setConstraints(title, gbc);
    contentPane.add(title);  
    
    // Sets the Image
    gbc = new GridBagConstraints();
    imageLabel = new JLabel();
    
    URL url = this.getClass().getResource("/icons/fragile.png");
    imageLabel = new JLabel(new ImageIcon(url));
    
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weighty = 100;
    gbc.anchor = GridBagConstraints.SOUTH;
    gbl.setConstraints(imageLabel, gbc);
    contentPane.add(imageLabel);
    
    // Sets the version
    version = new JLabel("Fragile v1.0");
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weighty = 100;
    gbc.anchor = GridBagConstraints.NORTH;
    gbl.setConstraints(version, gbc);
    contentPane.add(version);
    
    // Sets the description
    description = new JTextPane();
    description.setText(     
        STRINGS.getString("FRAGILE") + ".\n"
        + STRINGS.getString("FRAGILE_TWO") + "\n" 
        + STRINGS.getString("DEVELOP") +":\n"
        + "Fragile Development Team2C"
    ); 
    description.setFont(new Font("Arial", Font.BOLD, 12));
    description.setBackground(new Color(190, 191, 194));
    description.setEditable(false);
    StyledDocument doc = description.getStyledDocument();
    SimpleAttributeSet center = new SimpleAttributeSet();
    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
    doc.setParagraphAttributes(0, doc.getLength(), center, false);
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.weighty = 100;
    gbc.weightx = 100;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    //gbc.anchor = GridBagConstraints.SOUTH;
    gbl.setConstraints(description, gbc);
    contentPane.add(description);
    
    //setResizable(false);
    setMinimumSize(new Dimension(375, 230));
    getContentPane().setBackground(new Color(190, 191, 194));
    setVisible(true);
  }
}
