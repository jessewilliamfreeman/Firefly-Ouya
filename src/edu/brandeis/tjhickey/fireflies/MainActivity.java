package edu.brandeis.tjhickey.fireflies;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.graphics.PointF;
import tv.ouya.console.api.*;

public class MainActivity extends Activity {

	GameController gameController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		OuyaController.init(this);
		Log.d("Controler", "Controler Init");
		setContentView(R.layout.activity_main);
		gameController = (GameController) this.findViewById(R.id.game_controller );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onStop(){
		super.onStop();		
		gameController.stop();
	}
	
	
	@Override
	public boolean onGenericMotionEvent(final MotionEvent event) {
		//OuyaController.onGenericMotionEvent(event);
		float LS_X = event.getAxisValue(OuyaController.AXIS_LS_X);
		float LS_Y = event.getAxisValue(OuyaController.AXIS_LS_Y);
		//float RS_X = event.getAxisValue(OuyaController.AXIS_RS_X);
		//float RS_Y = event.getAxisValue(OuyaController.AXIS_RS_Y);
		double speedMultiplier = 1+(0.9)*Math.log10((gameController.gameView.gm.numActors-gameController.gameView.gm.numActive-gameController.gameView.gm.score+1));
		gameController.gameView.gm.moveAvatar(new PointF(LS_X * 2/(float)speedMultiplier, LS_Y * 2/(float)speedMultiplier));
		return true;
		
	}
	
	

}
