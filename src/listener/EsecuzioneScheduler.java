package listener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EsecuzioneScheduler implements ServletContextListener {

	private final long DELAY = 0;
    private final long PERIODO_ESECUZIONE = 5;
    private ScheduledExecutorService scheduler;
    
	@Override
	public void contextInitialized(ServletContextEvent servlet) {
		
		 scheduler = Executors.newSingleThreadScheduledExecutor();
	     Runnable eliminaPrenotazione = new EliminaPrenotazione(); 
	     scheduler.scheduleAtFixedRate(eliminaPrenotazione, DELAY, PERIODO_ESECUZIONE, TimeUnit.MINUTES);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent servlet) {
		scheduler.shutdown();
	}
}
