package com.chinanetcenter.streampusherdemo.filter;

import com.chinanetcenter.StreamPusher.sdk.SPVideoFilter;

public class VideoFilterDemo3 extends SPVideoFilter {
    private static final String FRAGMENT_SHADER_BODY =
    		"precision highp float;\n" +
    	            "\n" +
    	            "varying vec2 textureCoordinate;\n" +
    	            "\n" +
    	            "uniform sampler2D inputImageTexture;\n" +
    	            "\n" +
    	            "const highp vec3 W = vec3(0.2125, 0.7154, 0.0721);\n" +
    	            "\n" +
    	            "void main()\n" +
    	            "{\n" +
    	            "  lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n" +
    	            "  float luminance = dot(textureColor.rgb, W);\n" +
    	            "\n" +
    	            "  gl_FragColor = vec4(vec3(luminance), textureColor.a);\n" +
    	            "}";

    public VideoFilterDemo3() {
        super(null, FRAGMENT_SHADER_BODY);
    }
}
