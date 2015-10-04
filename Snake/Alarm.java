/**
 *	Alarm.java
 * 	@author Gaston
 *									A l a r m
 *									=========
 *	
 *	This class defines an alarm, which "beeps" after a resetable delay.
 *	On each "beep," the alarm will notify the object registered when the timer
 *		is instantiated..
*/

public class Alarm extends Thread
{
	/**
	 * The object to notify	in case of emergency
	 */
	private AlarmInterface whoWantsToKnow;
	
	/**
	 * The first beep will occur without any delay
	 */
	private int delay = 0;			

	/**
	 * Constructor
	 */
	public Alarm()
	{
		super("Alarm");						//	With the default constructor,
		whoWantsToKnow = null;				//		nobody will be notified
	}
	
	/**
	 * Constructor
	 * @param someBody - whoWantsToKnow
	 */
	public Alarm(AlarmInterface someBody)
	{
		super("Alarm");						//	In general,  we expect to know who
		whoWantsToKnow = someBody;			//		(i.e., which object) to notify
	}
	
	/**
	 * Constructor
	 * @param name - Name of string
	 * @param someBody - whoWantsToKnow
	 */
	public Alarm(String name, AlarmInterface someBody)
	{
		super(name);						//	We can also give a name to the alarm
		whoWantsToKnow = someBody;
	}

	/**
	 * The setPeriod method will set or reset the period by which beeps will occur.
	 * Note that the new period will be used after reception of the following beep.
	 * @param someDelay - The delay in the thread
	 */
	public void setPeriod(int someDelay)
	{										//	Note:  The period should be expressed
		delay = someDelay;					//				in milliseconds
	}
	
	/**
	 * The setPeriodicBeep method will keep on notifying the "object in charge"
	 * at set time intervals.
	 * Note that the time interval can be changed at any time through setPeriod.
	 */
	private void setPeriodicBeep(int someDelay)
	{
		delay = someDelay;

		try {
			while (true){						//	For as long as we have energy,
				Thread.sleep(delay);			//		first we wait
				if (whoWantsToKnow != null)		//		then we notify the
					whoWantsToKnow.takeNotice();//		responsible party
			}									//		(if anybody wants to hear)
		}
		catch(InterruptedException e) {
			System.err.println("Oh, oh ... " + e.getMessage());
		}
	}

	/**
	 * The alarm is a Thread, and the run method gets the thread started, and running.
	 */
	public void run()
	{
		System.out.println("The alarm is now running.");
		setPeriodicBeep(delay);
	}

}
