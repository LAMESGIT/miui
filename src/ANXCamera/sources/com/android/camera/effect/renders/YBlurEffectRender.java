package com.android.camera.effect.renders;

import com.android.gallery3d.ui.BasicTexture;
import com.android.gallery3d.ui.GLCanvas;

public class YBlurEffectRender extends RegionEffectRender {
    private static final String FRAG_WITHOUT_RECT = "precision mediump float; \nuniform vec2 uStep; \nuniform sampler2D sTexture; \nvarying vec2 vTexCoord; \nuniform float uAlpha; \nvoid main() { \n    vec2 step = vec2(0.0, uStep.y) ; \n    vec2 delta = step; \n    int radius = 10; \n    float factor = 1.0 / (float(radius + 1) * float(radius + 1)); \n    float weight = factor * float(radius + 1); \n    vec3 sum = texture2D(sTexture, vTexCoord).rgb * weight; \n    for (int i = 1; i <= radius; ++i) { \n        weight -= factor; \n        sum += (texture2D(sTexture, vTexCoord + delta).rgb + texture2D(sTexture, vTexCoord - delta).rgb) * weight; \n        delta += step; \n    } \n    gl_FragColor = vec4(sum, 1.0)*uAlpha; \n}";

    public YBlurEffectRender(GLCanvas gLCanvas) {
        super(gLCanvas);
    }

    public YBlurEffectRender(GLCanvas gLCanvas, int i) {
        super(gLCanvas, i);
    }

    /* Access modifiers changed, original: protected */
    public void drawTexture(BasicTexture basicTexture, float f, float f2, float f3, float f4, boolean z) {
        setStep(basicTexture.getTextureWidth(), basicTexture.getTextureHeight());
        super.drawTexture(basicTexture, f, f2, f3, f4, z);
    }

    public String getFragShaderString() {
        return FRAG_WITHOUT_RECT;
    }
}
