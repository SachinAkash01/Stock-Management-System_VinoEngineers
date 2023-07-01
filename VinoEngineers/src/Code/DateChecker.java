/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import DBLayer.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DateChecker {
    private static final int CHECK_HOUR = 0; // Hour at which the check should be performed

    public static void main(String[] args) {
        // Schedule the recursive check at the specified hour
        scheduleRecursiveCheck();
    }
    
    public static void scheduleRecursiveCheck() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        
        // Calculate the initial delay until the next check time
        long initialDelay = calculateInitialDelay();
        
        executor.scheduleAtFixedRate(DateChecker::checkDateRecursive, initialDelay, 24, TimeUnit.HOURS);
    }
    
    private static long calculateInitialDelay() {
        LocalDate today = LocalDate.now();
        LocalTime checkTime = LocalTime.of(CHECK_HOUR, 24, 02); // Set the check time
        
        LocalDate nextCheckDate = today.with(checkTime);
        if (nextCheckDate.isBefore(today) || nextCheckDate.equals(today)) {
            nextCheckDate = nextCheckDate.plusDays(1);
        }
        
        return java.time.Duration.between(LocalTime.now(), checkTime).toMillis()
                + java.time.Duration.between(LocalDateTime.now(), nextCheckDate.atTime(checkTime)).toMillis();
    }
    
    private static void checkDateRecursive() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from reminders");
            
            while (rs.next()){
                String reminderID = rs.getString("reminderID");
                int userID = rs.getInt("userID");
                String Description = rs.getString("description");
                String Rdate = rs.getString("date");
                String Status = rs.getString("status");
                
                //SQL date we want to convert
                Date sqlDate = Date.valueOf(Rdate);
                
                //Convert sql date into calendar
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(sqlDate);
                
                //Extract year, month, day
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                
                LocalDate targetDate = LocalDate.of(year, month, day); // Replace with your target date
        
                // Call the recursive function to check the date
                boolean isToday = checkDateRecursive(targetDate);

                if (isToday && Status.equals("Not Completed")) {
                    try {
                        Statement st1 = con.createStatement();
                        ResultSet rs1 = st1.executeQuery("select email from users where userid = "+userID+";");
                        rs.next();
                        String email = rs1.getString("email");

                        JavaEmailSender j = new JavaEmailSender();
                        j.createAndSendEmail(email, "REMINDER FROM VINO ENGINEERS", "This is to inform you that your reminder for , "+Description+" where reminder ID = "+reminderID+" is due today!   Vino Engineers");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static boolean checkDateRecursive(LocalDate date) {
        LocalDate today = LocalDate.now();
        
        if (date.equals(today)) {
            return true;
        }
        
        // Recursive call with the next day
        return checkDateRecursive(date.plusDays(1));
    }
}
