package mail;

import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import model.Result;
import model.Student;
import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import util.Level;
import util.LogThis;

public class MailMail {   
    
    private JavaMailSender mailSender;
    final static Logger LOGGER = Logger.getLogger(MailMail.class);
    
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    public boolean sendResults(final Student s) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setTo(s.getEmail());
            helper.setFrom("mcit_cgs2@gmail.com");
            helper.setSubject("College Grading System: Results for " + s.getFirstName() + " " + s.getLastName() + 
                    " (Student ID " + s.getStudentID() + ")");
            mimeMessage.setContent(buildResultsEmail(s), "text/html");        
            mailSender.send(mimeMessage);
        }
        catch(MailException ex) {
            LogThis.log(LOGGER, Level.ERROR, "Error sending results email to student ID " + s.getStudentID() + ". Reason: " + ex.getMessage());
            return false;
        }
        catch(MessagingException ex) {
            LogThis.log(LOGGER, Level.ERROR, "Error preparing results email for student ID " + s.getStudentID() + ". Reason: " + ex.getMessage());
            return false;
        }
        return true;
    }
    
    public String buildResultsEmail(Student s) {
        StringBuilder mailBody = new StringBuilder();
        mailBody.append("<html><body style='font-family:Verdana, sans-serif; font-size:15px; line-height:1.5'>");
        mailBody.append("<h2>Hello, ").append(s.getFirstName()).append(" ").append(s.getLastName()).append(".</h2>");
        mailBody.append("<h3>Here is a table of your results so far:</h3>");
        mailBody.append("<table border='1'><tr><th>Course</th><th>Session ID</th><th>Mark</th></tr>");
        List<Result> results = s.getResults();
        for(Result r : results) {
            mailBody.append("<tr>");
            mailBody.append("<td>").append(r.getCourseResult().getCourseName()).append("</td>");
            mailBody.append("<td>").append(r.getSessionID()).append("</td>");
            mailBody.append("<td>").append(r.getMark()).append("</td>");
            mailBody.append("</tr>");
        }
        mailBody.append("</table><p><i>Sent by the College Grading System</i></p></body></html>");
        return mailBody.toString();
    }
}

