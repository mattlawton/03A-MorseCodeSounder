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
		if (port == buttonPort) {
			buzzerChannel.setValue(buzzerPort, value);
		}
	}

	@Override
	public boolean acceptShutdown() {
		buzzerChannel.setValue(buzzerPort, 0);
		return true;
	}

}
