/**
 * 
 */
package poker.ui.server;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author luciofranco
 *
 */
public class ConsoleMessage {
	private String who;
	private String message;
	private Date timestamp;
	private final DateFormat df;
	
	public ConsoleMessage(String who, String message) {
		this.who = who;
		this.message = message;
		this.df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		this.timestamp = new Date();
	}

	public String getWho() {
		return who;
	}

	public String getMessage() {
		return message;
	}
	
	public Date getTimeStamp() {
		return this.timestamp;
	}
	
	public String getFormatedTimestamp() {
		return df.format(this.timestamp);
	}
	
	public String toString() {
		
		return this.getFormatedTimestamp() + " | " + this.who + " - " + this.message;
	}
}
