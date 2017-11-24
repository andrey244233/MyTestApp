package com.example.home_pc.mytestapp.Api;

import java.util.List;

/**
 * Created by admin on 21.11.2017.
 */

public class Preview {

    private List<Image> images = null;

    private boolean enabled;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
