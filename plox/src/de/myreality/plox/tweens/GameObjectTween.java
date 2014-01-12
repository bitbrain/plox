package de.myreality.plox.tweens;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.Color;

import de.myreality.plox.GameObject;

public class GameObjectTween implements TweenAccessor<GameObject> {
	
	public static final int SHAKE_X = 1;
	public static final int SHAKE_Y = 2;
	public static final int ALPHA = 3;
	@Override
	public int getValues(GameObject target, int tweenType, float[] returnValues) {
		 switch (tweenType) {
	         case SHAKE_X:
	                 returnValues[0] = target.getX();
	                 return 1;
	         case SHAKE_Y:
		             returnValues[0] = target.getY();
		             return 1;
	         case ALPHA:
	             returnValues[0] = target.getColor().a;
	             return 1;
	         default:
	                 return 0;
         }
	}

	@Override
	public void setValues(GameObject target, int tweenType, float[] newValues) {
		 switch (tweenType) {
	         case SHAKE_X:
	        	 target.setX(newValues[0]);
	             break;
	         case SHAKE_Y:
	        	 target.setY(newValues[0]);
	         case ALPHA:
	             target.setColor(new Color(target.getColor().r, target.getColor().g, target.getColor().b, newValues[0]));
		 }
	}

}
