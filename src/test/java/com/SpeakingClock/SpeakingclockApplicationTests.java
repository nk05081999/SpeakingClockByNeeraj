package com.SpeakingClock;

import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SpeakingClock.Service.SpeakingClockService;

import pl.allegro.finance.tradukisto.ValueConverters;

@SpringBootTest
class SpeakingclockApplicationTests {


	@Autowired
	private SpeakingClockService speakingClockService;

	@Test
	void testSpecificTime() {
		String str  = speakingClockService.getCurrentTimeInWords("12:02");
		Assertions.assertEquals("It's twelve two", str);
	}

	@Test
	void testMidnightTime() {
		String str  = speakingClockService.getCurrentTimeInWords("00:00");
		Assertions.assertEquals("It's Midnight", str);
	}

	@Test
	void testMiddayTime() {
		String str  = speakingClockService.getCurrentTimeInWords("12:00");
		Assertions.assertEquals("It's Midday", str);
	}

	@Test
	void testCurrentTime() {
		String str = speakingClockService.getCurrentTimeInWords("");
		if(!str.contains("It's")){
			Assertions.fail();
		} else {
			String substring = str.substring(str.indexOf("It's ") + 5);
			LocalTime localTime = LocalTime.now();
			int hour = localTime.getHour();
			int min = localTime.getMinute();
			if(substring.equals("Midday")) {
				Assertions.assertEquals(hour, 12);
				Assertions.assertEquals(min, 0);
			} else if(substring.equals("Midday")) {
				Assertions.assertEquals(hour, 0);
				Assertions.assertEquals(min, 0);
			} else {
				String []timeArr = substring.split(" ");
				Assertions.assertEquals(ValueConverters.ENGLISH_INTEGER.asWords(hour), timeArr[0]);
				Assertions.assertEquals(ValueConverters.ENGLISH_INTEGER.asWords(min), timeArr[1]);
			}
		}
	}

}