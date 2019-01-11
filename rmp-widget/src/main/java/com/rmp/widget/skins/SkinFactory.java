package com.rmp.widget.skins;

import com.rmp.widget.skins.defaultSkin.DefaultSkin;
import lombok.experimental.UtilityClass;

/**
 * Provides the factory to work with skins
 */
@UtilityClass
public class SkinFactory {
    
    private final RMPSkin rmpSkin = new DefaultSkin();

    /**
     * Gets the skin of {@link com.rmp.widget.RMPWidget}
     */
    public RMPSkin getRMPSkin() {
        return rmpSkin;
    }
    
}
