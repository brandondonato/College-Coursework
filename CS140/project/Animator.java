package project;
import javax.swing.Timer;

public class Animator {
	private static final int TICK = 500;
	private boolean autoStepOn = false;
	private Timer timer;
	private ViewsOrganizer view;
	
	public Animator(ViewsOrganizer theView){
		view = theView;
	}

	public boolean isAutoStepOn() {
		return autoStepOn;
	}

	public void setAutoStepOn(boolean autoStepOn) {
		this.autoStepOn = autoStepOn;
	}
	
	public void toggleAutoStep(){
		if(autoStepOn){
			autoStepOn = false;
		}else{
			autoStepOn = true;
		}
	}
	
	public void setPeriod(int period){
		timer.setDelay(period);
	}
	
	public void start() {
		timer = new Timer(TICK, e -> {if(autoStepOn) view.step();});
		timer.start();
	}

}
