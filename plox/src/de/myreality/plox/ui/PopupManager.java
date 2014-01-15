package de.myreality.plox.ui;

import java.util.LinkedList;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import de.myreality.plox.tweens.ActorTween;

public class PopupManager implements TweenCallback {

    // ===========================================================
    // Constants
    // ===========================================================
    
    private static final int MOVING_DISTANCE = 300;

    // ===========================================================
    // Fields
    // ===========================================================
    
    private TweenManager tweenManager;
    
    private Stage stage;
    
    private LabelStyle popupStyle;

    private float duration;
    
    private LinkedList<Label> queue;

    // ===========================================================
    // Constructors
    // ===========================================================
    
    public PopupManager(Stage stage, TweenManager tweenManager, LabelStyle popupStyle) {
            this.tweenManager = tweenManager;
            this.stage = stage;
            this.popupStyle = popupStyle;
            duration = 2f;
            queue = new LinkedList<Label>();
    }

    // ===========================================================
    // Getters and Setters
    // ===========================================================

    // ===========================================================
    // Methods from Superclass
    // ===========================================================

    @Override
    public void onEvent(int type, BaseTween<?> source) {
            Label label = queue.remove();
            tweenManager.killTarget(label);
            label.remove();
            label.invalidate();
    }

    // ===========================================================
    // Methods
    // ===========================================================
    
    public void popup(float x, float y, String text) {
            popup(x, y, text, popupStyle);
    }
    
    public void popup(float x, float y, String text, LabelStyle popupStyle) {
            Label label = new Label(text, popupStyle);
            stage.addActor(label);
            label.setPosition(x - label.getWidth() / 2f, Gdx.graphics.getHeight() - y - label.getHeight() / 2f);
            
            Tween.to(label, ActorTween.ALPHA, duration)
             .target(0f)
            .ease(TweenEquations.easeInOutQuad)
            .setCallback(this)
            .setCallbackTriggers(TweenCallback.COMPLETE)
            .start(tweenManager);
            Tween.to(label, ActorTween.POPUP, duration)
             .target(y - MOVING_DISTANCE)
            .ease(TweenEquations.easeInOutQuad)
            .start(tweenManager);
            queue.add(label);
    }
    
    public void setDurartion(float duration) {
            this.duration = duration;
    }

    // ===========================================================
    // Inner classes
    // ===========================================================
}
