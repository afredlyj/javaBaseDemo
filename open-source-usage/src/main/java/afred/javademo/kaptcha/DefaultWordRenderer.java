package afred.javademo.kaptcha;

//import com.google.code.kaptcha.text.WordRenderer;
//import com.google.code.kaptcha.util.Configurable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2016-01-15 
 * Time: 11:36
 */
public class DefaultWordRenderer
//        extends Configurable implements WordRenderer
{

    private static final Logger logger = LoggerFactory.getLogger(DefaultWordRenderer.class);

    public DefaultWordRenderer() {
    }

    /**
     * Renders a word to an image.
     *
     * @param word
     *            The word to be rendered.
     * @param width
     *            The width of the image to be created.
     * @param height
     *            The height of the image to be created.
     * @return The BufferedImage created from the word.
     */
    public BufferedImage renderWord(String word, int width, int height)
    {

        logger.debug("开始渲染");
//
//        int fontSize = getConfig().getTextProducerFontSize();
//        Font[] fonts = getConfig().getTextProducerFonts(fontSize);
//        Color color = getConfig().getTextProducerFontColor();
//        int charSpace = getConfig().getTextProducerCharSpace();
//        BufferedImage image = new BufferedImage(width, height,
//                BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2D = image.createGraphics();
//        g2D.setColor(color);
//
//        RenderingHints hints = new RenderingHints(
//                RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        hints.add(new RenderingHints(RenderingHints.KEY_RENDERING,
//                        RenderingHints.VALUE_RENDER_QUALITY));
//        g2D.setRenderingHints(hints);
//
//        FontRenderContext frc = g2D.getFontRenderContext();
//        Random random = new Random();
//
//        int startPosY = (height - fontSize) / 5 + fontSize;
//
//        char[] wordChars = word.toCharArray();
//        Font[] chosenFonts = new Font[wordChars.length];
//        int [] charWidths = new int[wordChars.length];
//        int widthNeeded = 0;
//        for (int i = 0; i < wordChars.length; i++)
//        {
//            chosenFonts[i] = fonts[random.nextInt(fonts.length)];
//
//            char[] charToDraw = new char[]{
//                    wordChars[i]
//            };
//            GlyphVector gv = chosenFonts[i].createGlyphVector(frc, charToDraw);
//            charWidths[i] = (int)gv.getVisualBounds().getWidth();
//            if (i > 0)
//            {
//                widthNeeded = widthNeeded + 2;
//            }
//            widthNeeded = widthNeeded + charWidths[i];
//        }
//
//        int startPosX = (width - widthNeeded) / 2;
//        for (int i = 0; i < wordChars.length; i++)
//        {
//            g2D.setFont(chosenFonts[i]);
//            char[] charToDraw = new char[] {
//                    wordChars[i]
//            };
//            g2D.drawChars(charToDraw, 0, charToDraw.length, startPosX, startPosY);
//            startPosX = startPosX + (int) charWidths[i] + charSpace;
//        }

//        return image;
        return null;
    }
}