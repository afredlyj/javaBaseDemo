package afred.javademo.kaptcha;

//import com.google.code.kaptcha.GimpyEngine;
//import com.google.code.kaptcha.NoiseProducer;
//import com.google.code.kaptcha.util.Configurable;
//import com.jhlabs.image.RippleFilter;
//import com.jhlabs.image.TransformFilter;
//import com.jhlabs.image.WaterFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with basicdemo. 
 * User: liyuanjun(80059138) 
 * Date: 2016-01-15 
 * Time: 11:34
 */
public class WaterRipple
//        extends Configurable implements GimpyEngine
{

    private static final Logger logger = LoggerFactory.getLogger(WaterRipple.class);

//    @Override public BufferedImage getDistortedImage(BufferedImage baseImage) {
//
//        logger.debug("图片样式");
//
//        NoiseProducer noiseProducer = getConfig().getNoiseImpl();
//        BufferedImage distortedImage = new BufferedImage(baseImage.getWidth(),
//                baseImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
//
//        Graphics2D graphics = (Graphics2D) distortedImage.getGraphics();
//
//        RippleFilter rippleFilter = new RippleFilter();
//        rippleFilter.setWaveType(RippleFilter.NOISE);
//        rippleFilter.setXAmplitude(2.6f);
//        rippleFilter.setYAmplitude(1.7f);
//        rippleFilter.setXWavelength(15);
//        rippleFilter.setYWavelength(5);
//        rippleFilter.setEdgeAction(TransformFilter.NEAREST_NEIGHBOUR);
////
////        WaterFilter waterFilter = new WaterFilter();
////        waterFilter.setAmplitude(1.5f);
////        waterFilter.setPhase(10);
////        waterFilter.setWavelength(2);
////
////        BufferedImage effectImage = waterFilter.filter(baseImage, null);
//        BufferedImage effectImage = rippleFilter.filter(baseImage, null);
//
//        graphics.drawImage(effectImage, 0, 0, null, null);
//
//        graphics.dispose();
//
//        noiseProducer.makeNoise(distortedImage, .1f, .1f, .25f, .25f);
//        noiseProducer.makeNoise(distortedImage, .1f, .25f, .5f, .9f);
//        return distortedImage;
//
//    }
}
