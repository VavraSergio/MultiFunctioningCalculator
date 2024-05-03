package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 * Print class for the session history, implements Printable and ActionListener.
 * 
 * This class allows printing the session history and implements Printable and ActionListener
 * interfaces.
 * 
 * @author Alec Dean, used code from Prof. Berstein's Printing lecture.
 * @version 11-16-23
 */
public class PrintHistory implements Printable, ActionListener
{
  private HistoryFrame component;

  /**
   * Constructs a PrintHistory object.
   * 
   * @param historyFrame
   *          The HistoryFrame to be printed.
   */
  public PrintHistory(final HistoryFrame historyFrame)
  {
    this.component = historyFrame;
  }

  @Override
  public int print(final Graphics g, final PageFormat format, final int page)
  {
    double cH, cW, h, scale, w, x, y;
    Graphics2D g2;
    int status;

    g2 = (Graphics2D) g;

    status = Printable.NO_SUCH_PAGE;
    if (page == 0)
    {
      // Translate the origin
      x = format.getImageableX();
      y = format.getImageableY();
      g2.translate(x, y);

      // Scale the coordinate system (without changing the
      // aspect ratio)
      h = format.getImageableHeight();
      w = format.getImageableWidth();
      cW = (double) (component.getWidth());
      cH = (double) (component.getHeight());
      scale = Math.min(w / cW, h / cH);
      g2.scale(scale, scale);

      // Have the JComponent paint itself
      component.paint(g2);

      // Inform the caller that a page has been drawn
      status = Printable.PAGE_EXISTS;
    }
    return status;
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    PrinterJob job = PrinterJob.getPrinterJob();
    component.openFrame();
    job.setPrintable(this);
    if (job.printDialog())
    {
      try
      {
        job.print();
      }
      catch (PrinterException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    }
    component.closeFrame();
  }
}
