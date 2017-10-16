package com.ociweb;

import com.ociweb.gl.api.ShutdownListener;
import com.ociweb.iot.maker.DigitalListener;
import com.ociweb.iot.maker.FogCommandChannel;
import com.ociweb.iot.maker.FogRuntime;
import com.ociweb.iot.maker.Port;

public class MorseCodeBehavior implements DigitalListener, ShutdownListener {

	private final FogCommandChannel buzzerChannel;
	private final Port buzzerPort;
	private final Port buttonPort;

	public MorseCodeBehavior(FogRuntime runtime, Port buzzerPort, Port buttonPort) {
		this.buzzerChannel = runtime.newCommandChannel(FogRuntime.PIN_WRITER);
		this.buzzerPort = buzzerPort;
		this.buttonPort = buttonPort;
	}

	@Override
	public void digitalEvent(Port port, long time, long duration, int value) {
		// Make sure the port calling this method is the port we want, the port we
		// assigned to the button
		if (port == buttonPort) {
			buzzerChannel.setValue(buzzerPort, value);
			// When the button is pushed down, the value is 1. Otherwise, the value is zero
			if (value == 1) {
				// Display console output "Buzzer pressed" when the button's value is 1
				System.out.println("Buzzer pressed.");
				// If the value is not 1, the button must have been released
			} else {
				System.out.println("Button released.");
			}
		}
	}

	@Override
	public boolean acceptShutdown() {
		buzzerChannel.setValue(buzzerPort, 0);
		return true;
	}

}
