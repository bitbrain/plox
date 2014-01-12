package de.myreality.plox.tweens;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteTween implements TweenAccessor<Sprite> {
	
	public static final int BOUNCE = 1;

	@Override
	public int getValues(Sprite target, int tweenType, float[] returnValues) {
		 switch (tweenType) {
         case BOUNCE:
                 returnValues[0] = target.getY();
                 return 1;
         default:
                 return 0;
         }
	}

	@Override
	public void setValues(Sprite target, int tweenType, float[] newValues) {
		 switch (tweenType) {
	         case BOUNCE:
	                 target.setY(newValues[0]);
	                 break;
		 }
	}

}
