public class Angle_Between_Hands_Of_A_Clock {
    public double angleClock(int hour, int minutes) {
        double hourValue = (hour == 12) ? (minutes/60.0) * 30.0 : 30.0 * hour + (minutes/60.0) * 30;
        double minutesValue = minutes * 6;
        double output = Math.abs(hourValue-minutesValue);
        return (output < 180) ? output : 360 - output;
    }
}