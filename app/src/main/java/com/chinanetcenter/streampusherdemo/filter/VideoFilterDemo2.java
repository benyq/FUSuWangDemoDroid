package com.chinanetcenter.streampusherdemo.filter;

import com.chinanetcenter.StreamPusher.sdk.SPVideoFilter;

public class VideoFilterDemo2 extends SPVideoFilter {
    private static final String FRAGMENT_SHADER_BODY =
    		"varying highp vec2 textureCoordinate;\n" + 
    	            " \n" + 
    	            " uniform sampler2D inputImageTexture;\n" + 
    	            " uniform lowp float contrast;\n" + 
    	            " \n" + 
    	            " void main()\n" + 
    	            " {\n" + 
    	            "     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n" + 
    	            "     \n" + 
    	            "     gl_FragColor = vec4(((textureColor.rgb - vec3(0.5)) * contrast + vec3(0.5)), textureColor.w);\n" + 
    	            " }";

    public VideoFilterDemo2() {
        super(null, FRAGMENT_SHADER_BODY);
    }
    
    public void onInitialized() {
        int mContrastLocation = getUniformLocation("contrast");
        setFloat(mContrastLocation, 1.2f);
        
    }
}
