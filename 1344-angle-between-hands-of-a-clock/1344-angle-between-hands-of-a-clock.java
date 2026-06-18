class Solution {
    public double angleClock(int hour, int minutes) {
        // Calculate the position of each hand in degrees
        double minuteAngle = minutes * 6.0;
        double hourAngle = (hour % 12 + minutes / 60.0) * 30.0;
        
        // Calculate the absolute difference
        double diff = Math.abs(hourAngle - minuteAngle);
        
        // Return the smaller angle
        return Math.min(diff, 360.0 - diff);
    }
}