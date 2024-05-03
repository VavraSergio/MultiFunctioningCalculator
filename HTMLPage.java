package gui;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;

/**
 * This class represents an HTML page that can be opened in a web browser.
 */
public class HTMLPage
{
  
  public static final String ENGLISH = "en";
  public static final String HTML = "/html2/Index.html";
  public static final String FRENCH = "fr";
  public static final String SPAIN = "es";
  public static final String HTML_TWO = "/html2/IndexMac.html";

  /**
   * Constructs an HTMLPage object and opens the appropriate HTML file based on the operating system
   * and userLocale.
   *
   * @param userLocale
   *          The userLocale for which to open the HTML page.
   */
  public HTMLPage(final Locale userLocale)
  {
    String os = System.getProperty("os.name").toLowerCase();
    if (os.contains("nix") || os.contains("nux") || os.contains("aix"))
    {
      try
      {
        String pathToFile;
        String language = userLocale.getLanguage();
        if ((language.equals(ENGLISH)))
        {
          pathToFile = HTML; // Replace with the actual path
        }
        else if ((language.equals(FRENCH)))
        {
          pathToFile = "/html2/IndexFN.html";
        }
        else if ((language.equals(SPAIN)))
        {
          pathToFile = "/html2/IndexES.html";
        }
        else
        {
          pathToFile = HTML; // Replace with the actual path
        }

        InputStream is = HTMLPage.class.getResourceAsStream(pathToFile);
        if (is != null)
        {
          File temp = File.createTempFile("Fragile", ".html");

          try (OutputStream os2 = new FileOutputStream(temp))
          {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) != -1)
            {
              os2.write(buffer, 0, length);
            }
          }

          temp.deleteOnExit();
          Desktop.getDesktop().browse(temp.toURI());
        }
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      try
      {
        URL htmlURL;
        String selectedLanguage = userLocale.getLanguage();
        if (selectedLanguage.equals(ENGLISH))
        {
          htmlURL = HTMLPage.class.getResource(HTML_TWO);
        }
        else if (selectedLanguage.equals(FRENCH))
        {
          htmlURL = HTMLPage.class.getResource("/html2/IndexFNMac.html");
        }
        else if (selectedLanguage.equals(SPAIN))
        {
          htmlURL = HTMLPage.class.getResource("/html2/IndexESMac.html");
        }
        else
        {
          // Default to English if language is not recognized
          htmlURL = HTMLPage.class.getResource(HTML_TWO);
        }

        if (htmlURL != null)
        {
          Desktop.getDesktop().browse(htmlURL.toURI());
        }
        else
        {
          System.out.println("HTML file not found.");
        }
      }
      catch (IOException | URISyntaxException e)
      {
        e.printStackTrace();
      }
    }
  }
}
