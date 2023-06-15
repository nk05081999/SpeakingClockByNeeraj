package com.SpeakingClock.Service;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.allegro.finance.tradukisto.ValueConverters;

@Service
public class SpeakingClockService {

    public String getCurrentTimeInWords(String time){

        StringBuilder sb = new StringBuilder();

        ValueConverters valueConverters = ValueConverters.ENGLISH_INTEGER;
        int hour = 0;
        int min = 0;
        if(!StringUtils.isEmpty(time)){

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.from(formatter.parse(time));
            hour = localTime.getHour();
            min = localTime.getMinute();
        } else {
            LocalTime localTime = LocalTime.now();
            hour = localTime.getHour();
            min = localTime.getMinute();
        }
        sb = sb.append("It's ");
        if(hour == 12 && min == 0){
            sb = sb.append("Midday");
        } else if(hour == 0 && min == 0){
            sb = sb.append("Midnight");
        } else {
            sb = sb.append(valueConverters.asWords(hour))
                    .append(" ").append(valueConverters.asWords(min));
        }

        return sb.toString();
    }


}