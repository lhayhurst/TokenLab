package net.sozinsoft.tokenlab;

import net.sozinsoft.tokenlab.dtd.Special;
import org.apache.commons.lang.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: lhayhurst
 * Date: 11/29/11
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Vision {
    public static final String DARKVISION_30 = "Darkvision30";
    public static final String DARKVISION_60 = "Darkvision60";
    public static final String DARKVISION_90 = "Darkvision90";
    public static final String DARKVISION_120 = "Darkvision120";
    public static final String DARKVISION30_AND_LOWLIGHT = "Darkvision30 and Lowlight";
    public static final String DARKVISION60_AND_LOWLIGHT = "Darkvision60 and Lowlight";
    public static final String DARKVISION90_AND_LOWLIGHT = "Darkvision90 and Lowlight";
    public static final String DARKVISION120_AND_LOWLIGHT = "Darkvision120 and Lowlight";

    /* here are the MT vision properties:
    Normal: circle r2
    Lowlight: circle x2 r1.2
    Blind: circle distance=2.5 r2
    Darkvision30: circle r32
    Darkvision60: circle r62
    Darkvision90: circle r92
    Darkvision120: circle r122
    Darkvision30 and Lowlight: circle x2 r16
    Darkvision60 and Lowlight: circle x2 r31
    Darkvision90 and Lowlight: circle x2 r46
    Darkvision120 and Lowlight: circle x2 r61
    */


    private String _vision;

    public static final String HEROLABS_DARKVISION = "Darkvision";
    public static final String MT_DARKVISION = HEROLABS_DARKVISION;
    public static final String HEROLABS_LOWLIGHT_VISION = "Low-Light Vision";
    public static final String MT_LOWLIGHT_VISION = "Lowlight";
    public static final String MT_NORMAL_VISION = "Normal";



    public static final HashMap<String, String > visionMap = new HashMap<String, String>();
    static {
        visionMap.put( HEROLABS_DARKVISION, MT_DARKVISION);
        visionMap.put( HEROLABS_LOWLIGHT_VISION, MT_LOWLIGHT_VISION);
    }

    private List<Special> _senses;
    public Vision( List<Special> senses ) {
        _senses = senses;
    }

    public String getVision() {

        boolean hasDarkVision      = false;
        boolean hasLowLight        = false;
        String  darkVisionDistance = null;
        _vision = "";

        for( Special s: _senses ) {
            if ( s.getName().indexOf( HEROLABS_DARKVISION ) >= 0 ) {
                hasDarkVision = true;
                Pattern regex = Pattern.compile(".*\\((\\d+)\\s+feet\\)");
                java.util.regex.Matcher matcher = regex.matcher(s.getName());
                if ( matcher.matches()) {
                    darkVisionDistance =  matcher.group(1);
                }
            }
            else if ( s.getName().indexOf( HEROLABS_LOWLIGHT_VISION ) >= 0 ) {
                hasLowLight = true;
            }
        }

        //build the vision string
        if( !hasDarkVision && !hasLowLight  ) {
            _vision = MT_NORMAL_VISION;
        }

        else {
            if( hasDarkVision ) {
                _vision = MT_DARKVISION;
                if( darkVisionDistance != null ) {
                    _vision = _vision + darkVisionDistance;
                }
            }
            if( hasLowLight ) {
                if ( _vision.length() > 0 ) { //had darkvision
                    _vision = _vision + " and " + MT_LOWLIGHT_VISION;
                }
                else {
                    _vision = MT_LOWLIGHT_VISION;
                }
            }
        }
        return _vision;

    }
}
