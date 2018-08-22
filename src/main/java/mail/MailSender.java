package mail;

import java.util.List;
import javax.mail.internet.MimeMessage;
import model.Result;
import model.Student;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class MailSender {

    public static boolean sendResults(Student s) {
        return sendResultsEmail(s);     
    }
    
    public static boolean sendResultsEmail(final Student s) {
        JavaMailSender mailSender = MailConfig.getJavaMailSender();
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
                helper.setTo(s.getEmail());
                helper.setFrom("mcit_cgs2@gmail.com");
                helper.setSubject("College Grading System: Results for " + s.getFirstName() + " " + s.getLastName() + 
                        " (Student ID" + s.getStudentID() + ")");
                mimeMessage.setContent(buildResultsEmail(s), "text/html; charset=utf-8");
                }          
            };
        try {
            mailSender.send(preparator);
        }
        catch(MailException ex) {
            //TODO: Logging
            return false;
        }
        return true;
    }
    
    public static String buildResultsEmail(Student s) {
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

