package com.ociweb;

import com.ociweb.gl.api.TimeListener;
import com.ociweb.iot.maker.DigitalListener;
import com.ociweb.iot.maker.FogCommandChannel;
import com.ociweb.iot.maker.FogRuntime;
import com.ociweb.iot.maker.Port;

public class MorseCodeBehavior implements TimeListener, DigitalListener {

	private final FogCommandChannel buzzerChannel;
	private final Port buzzerPort;
	private final Port buttonPort;

	private int buttonPressed = 0;

	public MorseCodeBehavior(FogRuntime runtime, Port buzzerPort, Port buttonPort) {
		this.buzzerChannel = runtime.newCommandChannel(FogRuntime.PIN_WRITER);
		this.buzzerPort = buzzerPort;
		this.buttonPort = buttonPort;
	}
	@Override
	public void digitalEvent(Port port, long time, long duration, int value) {
		if (port == buttonPort) {
			buttonPressed = value;
		}
	}
	
	@Override
	public void timeEvent(long time, int iteration) {
		buzzerChannel.setValue(buzzerPort, buttonPressed);
	}
	
}
