package de.myreality.plox.tweens;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelTween implements TweenAccessor<Label> {
	
	public static final int ALPHA = 1;

	@Override
	public int getValues(Label target, int tweenType, float[] returnValues) {
		 switch (tweenType) {
         case ALPHA:
                 returnValues[0] = target.getColor().a;
                 return 1;
         default:
                 return 0;
         }
	}

	@Override
	public void setValues(Label target, int tweenType, float[] newValues) {
		 switch (tweenType) {
	         case ALPHA:
	        	 	 Color c = target.getColor();
	                 target.setColor(c.r, c.g, c.b, newValues[0]);
	                 break;
		 }
	}

}
