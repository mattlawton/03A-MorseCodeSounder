package com.ociweb;

import static com.ociweb.iot.grove.simple_digital.SimpleDigitalTwig.Button;
import static com.ociweb.iot.grove.simple_digital.SimpleDigitalTwig.Buzzer;
import static com.ociweb.iot.maker.Port.D3;
import static com.ociweb.iot.maker.Port.D4;

import com.ociweb.iot.maker.FogApp;
import com.ociweb.iot.maker.FogRuntime;
import com.ociweb.iot.maker.Hardware;
import com.ociweb.iot.maker.Port;


public class IoTApp implements FogApp {
           
	public static Port BUZZER_PORT = D4;
	public static Port BUTTON_PORT = D3;
    
    @Override
    public void declareConnections(Hardware hardware) {
        hardware.connect(Buzzer, BUZZER_PORT);
        hardware.connect(Button, BUTTON_PORT);  
        hardware.setTimerPulseRate(100);
    }

    @Override
    public void declareBehavior(FogRuntime runtime) {
    	runtime.registerListener(new MorseCodeBehavior(runtime, BUZZER_PORT, BUTTON_PORT)); 
    }  
}