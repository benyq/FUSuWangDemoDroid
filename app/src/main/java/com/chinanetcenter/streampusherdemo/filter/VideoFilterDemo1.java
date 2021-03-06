package com.chinanetcenter.streampusherdemo.filter;

import android.opengl.GLES20;

import com.chinanetcenter.StreamPusher.sdk.SPVideoFilter;

public class VideoFilterDemo1 extends SPVideoFilter {

	public static final String FRAGMENT_SHADER_BODY = "" +
			"  varying highp vec2 textureCoordinate;\n" +
			"  \n" +
			"  uniform sampler2D inputImageTexture;\n" +
			"  uniform highp float red;\n" +
			"  uniform highp float green;\n" +
			"  uniform highp float blue;\n" +
			"  \n" +
			"  void main()\n" +
			"  {\n" +
			"      highp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n" +
			"      \n" +
			"      gl_FragColor = vec4(textureColor.r * red, textureColor.g * green, textureColor.b * blue, 1.0);\n" +
			"  }\n";

	private int mRedLocation;
	private int mGreenLocation;
	private int mBlueLocation;

    public VideoFilterDemo1() {
        super(null, FRAGMENT_SHADER_BODY);
    }

	@Override
	public void onInit() {
		super.onInit();
		mRedLocation = GLES20.glGetUniformLocation(getProgram(), "red");
		mGreenLocation = GLES20.glGetUniformLocation(getProgram(), "green");
		mBlueLocation = GLES20.glGetUniformLocation(getProgram(), "blue");
		setFloat(mRedLocation, 1.0f);//R
		setFloat(mGreenLocation, 0.8f);//G
		setFloat(mBlueLocation, 0.8f);//B
	}
}
