package com.example.home_pc.mytestapp.Api;

import java.util.List;

/**
 * Created by admin on 21.11.2017.
 */

public class Image {

    private Source source;

    private List<Resolution> resolutions = null;

    private Variants variants;

    private String id;

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public List<Resolution> getResolutions() {
        return resolutions;
    }

    public void setResolutions(List<Resolution> resolutions) {
        this.resolutions = resolutions;
    }

    public Variants getVariants() {
        return variants;
    }

    public void setVariants(Variants variants) {
        this.variants = variants;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
