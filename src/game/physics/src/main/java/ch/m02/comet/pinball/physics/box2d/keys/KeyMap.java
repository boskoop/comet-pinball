package ch.m02.comet.pinball.physics.box2d.keys;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.m02.comet.pinball.core.config.Configuration;
import ch.m02.comet.pinball.core.config.KeyProperties;

import com.badlogic.gdx.Input.Keys;

public class KeyMap {
	
	private static final Logger log = LoggerFactory.getLogger(KeyMap.class);

	@Inject
	private Configuration config;
	
	public int getKey(KeyProperties key) {
		String value = config.getKeyProperty(key);
		if (value == null) {
			log.error("Key '{}' not set in properties!", key.toString());
			throw new RuntimeException("Could not load key for property " + key.toString());
		}
		return keyMap.get(value);
	}
	
	private static final Map<String, Integer> keyMap;
	
	static {
		keyMap = new HashMap<String, Integer>();
		keyMap.put("NUM_0", Keys.NUM_0);
		keyMap.put("NUM_1", Keys.NUM_1);
		keyMap.put("NUM_2", Keys.NUM_2);
		keyMap.put("NUM_3", Keys.NUM_3);
		keyMap.put("NUM_4", Keys.NUM_4);
		keyMap.put("NUM_5", Keys.NUM_5);
		keyMap.put("NUM_6", Keys.NUM_6);
		keyMap.put("NUM_7", Keys.NUM_7);
		keyMap.put("NUM_8", Keys.NUM_8);
		keyMap.put("NUM_9", Keys.NUM_9);
		keyMap.put("A", Keys.A);
		keyMap.put("ALT_LEFT", Keys.ALT_LEFT);
		keyMap.put("ALT_RIGHT", Keys.ALT_RIGHT);
		keyMap.put("APOSTROPHE", Keys.APOSTROPHE);
		keyMap.put("AT", Keys.AT);
		keyMap.put("B", Keys.B);
		keyMap.put("BACK", Keys.BACK);
		keyMap.put("BACKSLASH", Keys.BACKSLASH);
		keyMap.put("C", Keys.C);
		keyMap.put("CALL", Keys.CALL);
		keyMap.put("CAMERA", Keys.CAMERA);
		keyMap.put("CLEAR", Keys.CLEAR);
		keyMap.put("COMMA", Keys.COMMA);
		keyMap.put("D", Keys.D);
		keyMap.put("DEL", Keys.DEL);
		keyMap.put("BACKSPACE", Keys.BACKSPACE);
		keyMap.put("FORWARD_DEL", Keys.FORWARD_DEL);
		keyMap.put("DPAD_CENTER", Keys.DPAD_CENTER);
		keyMap.put("DPAD_DOWN", Keys.DPAD_DOWN);
		keyMap.put("DPAD_LEFT", Keys.DPAD_LEFT);
		keyMap.put("DPAD_RIGHT", Keys.DPAD_RIGHT);
		keyMap.put("DPAD_UP", Keys.DPAD_UP);
		keyMap.put("CENTER", Keys.CENTER);
		keyMap.put("DOWN", Keys.DOWN);
		keyMap.put("LEFT", Keys.LEFT);
		keyMap.put("RIGHT", Keys.RIGHT);
		keyMap.put("UP", Keys.UP);
		keyMap.put("E", Keys.E);
		keyMap.put("ENDCALL", Keys.ENDCALL);
		keyMap.put("ENTER", Keys.ENTER);
		keyMap.put("ENVELOPE", Keys.ENVELOPE);
		keyMap.put("EQUALS", Keys.EQUALS);
		keyMap.put("EXPLORER", Keys.EXPLORER);
		keyMap.put("F", Keys.F);
		keyMap.put("FOCUS", Keys.FOCUS);
		keyMap.put("G", Keys.G);
		keyMap.put("GRAVE", Keys.GRAVE);
		keyMap.put("H", Keys.H);
		keyMap.put("HEADSETHOOK", Keys.HEADSETHOOK);
		keyMap.put("HOME", Keys.HOME);
		keyMap.put("I", Keys.I);
		keyMap.put("J", Keys.J);
		keyMap.put("K", Keys.K);
		keyMap.put("L", Keys.L);
		keyMap.put("LEFT_BRACKET", Keys.LEFT_BRACKET);
		keyMap.put("M", Keys.M);
		keyMap.put("MEDIA_FAST_FORWARD", Keys.MEDIA_FAST_FORWARD);
		keyMap.put("MEDIA_NEXT", Keys.MEDIA_NEXT);
		keyMap.put("MEDIA_PLAY_PAUSE", Keys.MEDIA_PLAY_PAUSE);
		keyMap.put("MEDIA_PREVIOUS", Keys.MEDIA_PREVIOUS);
		keyMap.put("MEDIA_REWIND", Keys.MEDIA_REWIND);
		keyMap.put("MEDIA_STOP", Keys.MEDIA_STOP);
		keyMap.put("MENU", Keys.MENU);
		keyMap.put("MINUS", Keys.MINUS);
		keyMap.put("MUTE", Keys.MUTE);
		keyMap.put("N", Keys.N);
		keyMap.put("NOTIFICATION", Keys.NOTIFICATION);
		keyMap.put("NUM", Keys.NUM);
		keyMap.put("O", Keys.O);
		keyMap.put("P", Keys.P);
		keyMap.put("PERIOD", Keys.PERIOD);
		keyMap.put("PLUS", Keys.PLUS);
		keyMap.put("POUND", Keys.POUND);
		keyMap.put("POWER", Keys.POWER);
		keyMap.put("Q", Keys.Q);
		keyMap.put("R", Keys.R);
		keyMap.put("RIGHT_BRACKET", Keys.RIGHT_BRACKET);
		keyMap.put("S", Keys.S);
		keyMap.put("SEARCH", Keys.SEARCH);
		keyMap.put("SEMICOLON", Keys.SEMICOLON);
		keyMap.put("SHIFT_LEFT", Keys.SHIFT_LEFT);
		keyMap.put("SHIFT_RIGHT", Keys.SHIFT_RIGHT);
		keyMap.put("SLASH", Keys.SLASH);
		keyMap.put("SOFT_LEFT", Keys.SOFT_LEFT);
		keyMap.put("SOFT_RIGHT", Keys.SOFT_RIGHT);
		keyMap.put("SPACE", Keys.SPACE);
		keyMap.put("STAR", Keys.STAR);
		keyMap.put("SYM", Keys.SYM);
		keyMap.put("T", Keys.T);
		keyMap.put("TAB", Keys.TAB);
		keyMap.put("U", Keys.U);
		keyMap.put("UNKNOWN", Keys.UNKNOWN);
		keyMap.put("V", Keys.V);
		keyMap.put("VOLUME_DOWN", Keys.VOLUME_DOWN);
		keyMap.put("VOLUME_UP", Keys.VOLUME_UP);
		keyMap.put("W", Keys.W);
		keyMap.put("X", Keys.X);
		keyMap.put("Y", Keys.Y);
		keyMap.put("Z", Keys.Z);
		keyMap.put("META_ALT_LEFT_ON", Keys.META_ALT_LEFT_ON);
		keyMap.put("META_ALT_ON", Keys.META_ALT_ON);
		keyMap.put("META_ALT_RIGHT_ON", Keys.META_ALT_RIGHT_ON);
		keyMap.put("META_SHIFT_LEFT_ON", Keys.META_SHIFT_LEFT_ON);
		keyMap.put("META_SHIFT_ON", Keys.META_SHIFT_ON);
		keyMap.put("META_SHIFT_RIGHT_ON", Keys.META_SHIFT_RIGHT_ON);
		keyMap.put("META_SYM_ON", Keys.META_SYM_ON);
		keyMap.put("CONTROL_LEFT", Keys.CONTROL_LEFT);
		keyMap.put("CONTROL_RIGHT", Keys.CONTROL_RIGHT);
		keyMap.put("ESCAPE", Keys.ESCAPE);
		keyMap.put("END", Keys.END);
		keyMap.put("INSERT", Keys.INSERT);
		keyMap.put("PAGE_UP", Keys.PAGE_UP);
		keyMap.put("PAGE_DOWN", Keys.PAGE_DOWN);
		keyMap.put("PICTSYMBOLS", Keys.PICTSYMBOLS);
		keyMap.put("SWITCH_CHARSET", Keys.SWITCH_CHARSET);
		keyMap.put("BUTTON_CIRCLE", Keys.BUTTON_CIRCLE);
		keyMap.put("BUTTON_A", Keys.BUTTON_A);
		keyMap.put("BUTTON_B", Keys.BUTTON_B);
		keyMap.put("BUTTON_C", Keys.BUTTON_C);
		keyMap.put("BUTTON_X", Keys.BUTTON_X);
		keyMap.put("BUTTON_Y", Keys.BUTTON_Y);
		keyMap.put("BUTTON_Z", Keys.BUTTON_Z);
		keyMap.put("BUTTON_L1", Keys.BUTTON_L1);
		keyMap.put("BUTTON_R1", Keys.BUTTON_R1);
		keyMap.put("BUTTON_L2", Keys.BUTTON_L2);
		keyMap.put("BUTTON_R2", Keys.BUTTON_R2);
		keyMap.put("BUTTON_THUMBL", Keys.BUTTON_THUMBL);
		keyMap.put("BUTTON_THUMBR", Keys.BUTTON_THUMBR);
		keyMap.put("BUTTON_START", Keys.BUTTON_START);
		keyMap.put("BUTTON_SELECT", Keys.BUTTON_SELECT);
		keyMap.put("BUTTON_MODE", Keys.BUTTON_MODE);
	}
}
