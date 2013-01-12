package ch.m02.comet.pinball.core.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationImplTest {
	
	private static final String TEST_PROPERTY = "test.property"; 

	@Mock
	private Properties properties;
	
	@InjectMocks
	private ConfigurationImpl configuration;
	
	@Test
	public void testGetBooleanPropertyTrue() {
		when(properties.getProperty(anyString())).thenReturn("true");
		
		Boolean value = configuration.getBooleanProperty(TEST_PROPERTY);
		
		assertTrue(value);
	}
	
	@Test
	public void testGetBooleanPropertyFalse() {
		when(properties.getProperty(anyString())).thenReturn("false");
		
		Boolean value = configuration.getBooleanProperty(TEST_PROPERTY);
		
		assertFalse(value);
	}
	
	@Test
	public void testGetBooleanPropertyEmpty() {
		when(properties.getProperty(anyString())).thenReturn("");
		
		Boolean value = configuration.getBooleanProperty(TEST_PROPERTY);
		
		assertFalse(value);
	}

	@Test
	public void testGetBooleanPropertyNull() {
		when(properties.getProperty(anyString())).thenReturn(null);
		
		Boolean value = configuration.getBooleanProperty(TEST_PROPERTY);
		
		assertNull(value);
	}
	
	@Test
	public void testGetStringProperty() {
		final String expected = "value";
		when(properties.getProperty(anyString())).thenReturn(expected);
		
		String value = configuration.getStringProperty(TEST_PROPERTY);
		
		assertEquals(expected, value);
	}
	
	@Test
	public void testGetStringPropertyNull() {
		when(properties.getProperty(anyString())).thenReturn(null);
		
		String value = configuration.getStringProperty(TEST_PROPERTY);
		
		assertNull(value);
	}
	
	@Test
	public void testGetIntegerProperty() {
		final String expected = "123";
		when(properties.getProperty(anyString())).thenReturn(expected);
		
		Integer value = configuration.getIntegerProperty(TEST_PROPERTY);
		
		assertEquals(Integer.valueOf(expected), value);
	}
	
	@Test
	public void testGetIntegerPropertyNull() {
		when(properties.getProperty(anyString())).thenReturn(null);
		
		Integer value = configuration.getIntegerProperty(TEST_PROPERTY);
		
		assertNull(value);
	}
	
	@Test
	public void testGetIntegerPropertyNumberFormat() {
		when(properties.getProperty(anyString())).thenReturn("invalid number");
		
		Integer value = configuration.getIntegerProperty(TEST_PROPERTY);
		
		assertNull(value);
	}

}
