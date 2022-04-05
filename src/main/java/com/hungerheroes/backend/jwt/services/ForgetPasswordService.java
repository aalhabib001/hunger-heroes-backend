package com.hungerheroes.backend.jwt.services;//package com.zaatun.zaatunecommerce.jwt.services;
//
//import com.dokanne.DokaneeBackend.jwt.dto.request.GenerateOTPRequest;
//import com.dokanne.DokaneeBackend.jwt.dto.request.GenerateOTPRequest1;
//import com.dokanne.DokaneeBackend.jwt.dto.request.GenerateOTPRequest2;
//import com.dokanne.DokaneeBackend.jwt.model.User;
//import com.dokanne.DokaneeBackend.jwt.repository.UserRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.io.IOException;
//import java.util.Optional;
//
//
//@Service
//@AllArgsConstructor
//
//public class ForgetPasswordService {
//
//    private final UserRepository userRepository;
//    private final JavaMailSender javaMailSender;
//    PasswordEncoder encoder;
//
//    public String generateOTP(GenerateOTPRequest generateOTPRequest) throws IOException, MessagingException {
//        Optional<User> userOptional = userRepository.findByEmail(generateOTPRequest.getEmail());
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            int random_int = (int) (Math.random() * (999999 - 100000 + 1) + 100000);
//
//            user.setGeneratedOTP(random_int);
//            user.setPasswordReset(true);
//            userRepository.save(user);
//
//            String emailBody = "" +
//                    "<h3>Hello <b>" + userOptional.get().getFirstName() + "</b>,</h3>" +
//                    "<h2>The password reset code is <b>" + random_int + "</b> . </h2>" +
//                    "<br><h3>Thank You </h3>";
//
//            String emailBody1 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
//                    "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
//                    "\n" +
//                    "<head>\n" +
//                    "<meta name=\"viewport\" content=\"width=device-width\" />\n" +
//                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
//                    "<title>The Dokanee Team</title>\n" +
//                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
//                    "\n" +
//                    "<style>\n" +
//                    "\n" +
//                    "/* main style */\n" +
//                    "* { \n" +
//                    "\tmargin:0;\n" +
//                    "\tpadding:0;\n" +
//                    "}\n" +
//                    "* { font-family: \"Helvetica Neue\", \"Helvetica\", Helvetica, Arial, sans-serif; }\n" +
//                    "\n" +
//                    "img { \n" +
//                    "\tmax-width: 100%; \n" +
//                    "}\n" +
//                    ".collapse {\n" +
//                    "\tmargin:0;\n" +
//                    "\tpadding:0;\n" +
//                    "}\n" +
//                    "body {\n" +
//                    "\t-webkit-font-smoothing:antialiased; \n" +
//                    "\t-webkit-text-size-adjust:none; \n" +
//                    "\twidth: 100%!important; \n" +
//                    "\theight: 100%;\n" +
//                    "}\n" +
//                    "\n" +
//                    "/* Elements Style */\n" +
//                    "\n" +
//                    "a { color: #2BA6CB;}\n" +
//                    "\n" +
//                    ".btn {\n" +
//                    "\ttext-decoration:none;\n" +
//                    "\tcolor: #FFF;\n" +
//                    "\tbackground-color: #25AA94;\n" +
//                    "\tpadding:15px 16px;\n" +
//                    "    font-weight:bold;\n" +
//                    "    border:1px;\n" +
//                    "    border-radius:5px;\n" +
//                    "    font-size:15px;\n" +
//                    "\ttext-align:center;\n" +
//                    "\tcursor:pointer;\n" +
//                    "\tdisplay: inline-block;\n" +
//                    "}\n" +
//                    "\n" +
//                    "p.callout {\n" +
//                    "\tpadding:15px;\n" +
//                    "\tbackground-color:#ECF8FF;\n" +
//                    "\tmargin-bottom: 15px;\n" +
//                    "}\n" +
//                    ".callout a {\n" +
//                    "\tfont-weight:bold;\n" +
//                    "\tcolor: #2BA6CB;\n" +
//                    "}\n" +
//                    "\n" +
//                    "/* header */\n" +
//                    "\n" +
//                    "table.head-wrap { width: 100%;}\n" +
//                    "\n" +
//                    ".header.container table td.logo { padding: 15px; }\n" +
//                    ".header.container table td.label { padding: 15px; padding-left:0px;}\n" +
//                    "\n" +
//                    "\n" +
//                    "/* body style */\n" +
//                    "\n" +
//                    "table.body-wrap { width: 100%;}\n" +
//                    "\n" +
//                    ".container {\n" +
//                    "\tdisplay:block!important;\n" +
//                    "\tmax-width:600px!important;\n" +
//                    "\tmargin:0 auto!important; /* makes it centered */\n" +
//                    "\tclear:both!important;\n" +
//                    "}\n" +
//                    "\n" +
//                    ".content {\n" +
//                    "\tpadding:15px;\n" +
//                    "\tmax-width:600px;\n" +
//                    "\tmargin:0 auto;\n" +
//                    "\tdisplay:block; \n" +
//                    "}\n" +
//                    "\n" +
//                    ".content table { width: 100%; }\n" +
//                    "\n" +
//                    "/* Footer Style */\n" +
//                    "\n" +
//                    "table.footer-wrap { width: 100%;\tclear:both!important;\n" +
//                    "}\n" +
//                    ".footer-wrap .container td.content  p { border-top: 1px solid rgb(215,215,215); padding-top:15px;}\n" +
//                    ".footer-wrap .container td.content p {\n" +
//                    "\tfont-size:10px;\n" +
//                    "\tfont-weight: bold;\n" +
//                    "\t\n" +
//                    "}\n" +
//                    "\n" +
//                    "/* Text Style */\n" +
//                    "\n" +
//                    "h1,h2,h3,h4,h5,h6 {\n" +
//                    "font-family: \"HelveticaNeue-Light\", \"Helvetica Neue Light\", \"Helvetica Neue\", Helvetica, Arial, \"Lucida Grande\", sans-serif; line-height: 1.1; margin-bottom:15px; color:#000;\n" +
//                    "}\n" +
//                    "h1 small, h2 small, h3 small, h4 small, h5 small, h6 small { font-size: 60%; color: #6f6f6f; line-height: 0; text-transform: none; }\n" +
//                    "\n" +
//                    "h1 { font-weight:200; font-size: 44px;}\n" +
//                    "h2 { font-weight:200; font-size: 37px;}\n" +
//                    "h3 { font-weight:500; font-size: 27px;}\n" +
//                    "h4 { font-weight:500; font-size: 23px;}\n" +
//                    "h5 { font-weight:900; font-size: 17px;}\n" +
//                    "h6 { font-weight:900; font-size: 14px; text-transform: uppercase; color:#444;}\n" +
//                    "\n" +
//                    ".collapse { margin:0!important;}\n" +
//                    "\n" +
//                    "p, ul { \n" +
//                    "\tmargin-bottom: 10px; \n" +
//                    "\tfont-weight: normal; \n" +
//                    "\tfont-size:14px; \n" +
//                    "\tline-height:1.6;\n" +
//                    "}\n" +
//                    "p.lead { font-size:17px; }\n" +
//                    "p.last { margin-bottom:0px;}\n" +
//                    "\n" +
//                    "/* Socila Style */\n" +
//                    ".fa {\n" +
//                    "  padding: 10px;\n" +
//                    "  font-size: 15px;\n" +
//                    "  width: 15px;\n" +
//                    "  text-align: center;\n" +
//                    "  text-decoration: none;\n" +
//                    "  margin: 5px 2px;\n" +
//                    "  border-radius: 50%;\n" +
//                    "}\n" +
//                    "\n" +
//                    ".fa:hover {\n" +
//                    "    opacity: 0.7;\n" +
//                    "}\n" +
//                    ".fa-link {\n" +
//                    "  background: #333;\n" +
//                    "  color: white;\n" +
//                    "}\n" +
//                    "\n" +
//                    ".fa-facebook {\n" +
//                    "  background: #3B5998;\n" +
//                    "  color: white;\n" +
//                    "}.fa-facebook {\n" +
//                    "  background: #3B5998;\n" +
//                    "  color: white;\n" +
//                    "}\n" +
//                    "\n" +
//                    ".fa-twitter {\n" +
//                    "  background: #55ACEE;\n" +
//                    "  color: white;\n" +
//                    "}\n" +
//                    "\n" +
//                    ".fa-linkedin {\n" +
//                    "  background: #007bb5;\n" +
//                    "  color: white;\n" +
//                    "}\n" +
//                    "\n" +
//                    ".fa-youtube {\n" +
//                    "  background: #bb0000;\n" +
//                    "  color: white;\n" +
//                    "}\n" +
//                    "\n" +
//                    "</style>\n" +
//                    "\n" +
//                    "</head>\n" +
//                    "\n" +
//                    "<body style=\"color:#4b4b4b; font-family: 'Helvetica', 'Arial', sans-serif;\" bgcolor=\"#efefef\">\n" +
//                    "\n" +
//                    "<!-- HEADER -->\n" +
//                    "<table class=\"head-wrap\" bgcolor=\"#efefef\">\n" +
//                    "\t<tr>\n" +
//                    "\t\t<td></td>\n" +
//                    "\t\t<td class=\"header container\" >\n" +
//                    "\n" +
//                    "\t\t\t\t<div style=\"margin-top:-20px;margin-bottom:-25px\" class=\"content\">\n" +
//                    "\t\t\t\t<table bgcolor=\"#efefef\">\n" +
//                    "\t\t\t\t\t<tr>\n" +
//                    "\t\t\t\t\t\t<td align=\"center\"><img width=\"60%\" height=\"auto\" src=\"https://i.ibb.co/N9BjjPS/logo.png\" /></td>\n" +
//                    "\t\t\t\t\t</tr>\n" +
//                    "\t\t\t\t</table>\n" +
//                    "\t\t\t</div>\n" +
//                    "\t\t</td>\n" +
//                    "\t\t<td></td>\n" +
//                    "\t</tr>\n" +
//                    "</table><!-- /HEADER -->\n" +
//                    "<!-- BODY -->\n" +
//                    "<table style=\"padding:5px\" class=\"body-wrap\" bgcolor=\"#efefef\">\n" +
//                    "\t<tr>\n" +
//                    "\t\t<td></td>\n" +
//                    "\t\t<td style=\"border-top:3px solid #25AA94;\" class=\"container\" bgcolor=\"#FFFFFF\">\n" +
//                    "\n" +
//                    "\t\t\t<div style=\"padding:30px;\"class=\"content\">\n" +
//                    "\t\t\t\t<table>\n" +
//                    "\t\t\t\t\t<tr>\n" +
//                    "\t\t\t\t\t\t<td>\n" +
//                    "\t\t\t\t\t\t\t<h4 align=\"center\" style=\"color:#4b4b4b;text-decoration:bold\">Password Reset</h4>\n" +
//                    "\t\t\t\t\t\t\t<br><br> <p align=\"center\" class=\"lead\"><b>Hi, " + userOptional.get().getFirstName() + " " + userOptional.get().getLastName() + "</b> <br> If you've lost your password or wish to reset it, use this OTP code below to get started.</p>\n" +
//                    "\t\n" +
//                    "\t\t\t\t\t\t\t<p style=\"font-size:25px;\" align=\"center\" class=\"callout\">\n" +
//                    "\t\t\t\t\t\t\t\t" + random_int + "\n" +
//                    "\t\t\t\t\t\t\t</p>\n" +
//                    "\t\t\t\t\t\t\t<br/>\n" +
//                    "\t\t\t\t\t\t\t<p align=\"center\">If you did not request a password reset, you can safely ignore this email. Only a person with access to you email can reset your accout password.</p>\n" +
//                    "\t\t\t\t\t\t</td>\n" +
//                    "\t\t\t\t\t</tr>\n" +
//                    "\t\t\t\t</table>\n" +
//                    "\t\t\t\t</div>\n" +
//                    "\n" +
//                    "\t\t</td>\n" +
//                    "\t\t<td></td>\n" +
//                    "\t</tr>\n" +
//                    "</table><!-- /BODY -->\n" +
//                    "\n" +
//                    "<!-- FOOTER -->\n" +
//                    "<table class=\"footer-wrap\" bgcolor=\"#efefef\">\n" +
//                    "\t<tr>\n" +
//                    "\t\t<td></td>\n" +
//                    "\t\t<td class=\"container\">\n" +
//                    "\t\t\t\t<div class=\"content\">\n" +
//                    "\t\t\t\t<table>\n" +
//                    "\t\t\t\t<tr>\n" +
//                    "\t\t\t\t\t<td align=\"center\">\n" +
//                    "\t\t\t\t\t\t<p class=\"lead\">Stay safe , and stay with us</p>\n" +
//                    "\t\t\t\t\t<a style=\"color:#2196F3;font-size:20px; text-decoration:solid;\" href=\"mailto:dokaneeteam@gmail.com?subject = Feedback & body = Message\">The Dokanee Team</a>\n" +
//                    "\t\t\t\t\t\t<br><br><br>\n" +
//                    "\t\t\t\t\t\t<p>\n" +
//                    "\t\t\t\t\t\t\t<a href=\"https://dokanee.com.bd\"><img class=\"fa fa-link\" src=\"https://i.ibb.co/MhdH7pD/link.png\" alt=\"link\"></a>\n" +
//                    "\t\t\t\t\t\t\t<a href=\"https://facebook.com/dokanee.com.bd\"><img class=\"fa fa-facebook\" src=\"https://i.ibb.co/CW4MG1R/facebook.png\" alt=\"facebook\"></a>\n" +
//                    "\t\t\t\t\t\t\t<a href=\"#\"><img class=\"fa fa-twitter\" src=\"https://i.ibb.co/s38X67B/twitter.png\" alt=\"twitter\"></a>\n" +
//                    "\t\t\t\t\t\t\t<a href=\"#\"><img class=\"fa fa-linkedin\" src=\"https://i.ibb.co/JjpsYn7/linkedin.png\" alt=\"linkedin\"></a>\n" +
//                    "\t\t\t\t\t\t\t<a href=\"#\"><img class=\"fa fa-youtube\" src=\"https://i.ibb.co/Mk5xPyS/youtube.png\" alt=\"youtube\"></a>\n" +
//                    "\t\t\t\t\t\t</p>\n" +
//                    "\t\t\t\t\t\t<p>Copyright © 2020 Dokanee.com.bd , All rights reserved.</p>\n" +
//                    "\t\t\t\t\t</td>\n" +
//                    "\t\t\t\t</tr>\n" +
//                    "\t\t\t</table>\n" +
//                    "\t\t\t</div>\n" +
//                    "\t\t</td>\n" +
//                    "\t\t<td></td>\n" +
//                    "\t</tr>\n" +
//                    "</table><!-- /FOOTER -->\n" +
//                    "</body>\n" +
//                    "</html>\n";
//
//            String emailSubject = "Password Reset Code";
//
//            sendMail(userOptional.get().getEmail(), userOptional.get().getFirstName(), emailSubject, emailBody1);
//
//            return userOptional.get().getEmail();
//        } else {
//            return "User Not Found";
//        }
//    }
//
//    public String sendMail(String sendTo, String name, String subject, String body) throws MessagingException, IOException {
//
//        MimeMessage msg = javaMailSender.createMimeMessage();
//
//        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//        helper.setFrom("dokaneeteam@gmail.com", "Dokanee Team");
//
//        helper.setTo(sendTo);
//
//        helper.setSubject(subject);
//
//        // default = text/plain
//        //helper.setText("Check attachment for image!");
//
//        // helper.setText(body, true);
//        helper.setText(body, true);
//
//        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));
//
//
//        javaMailSender.send(msg);
//        return "Send to " + sendTo;
//    }
//
//    public String verifyOTP(GenerateOTPRequest1 generateOTPRequest) {
//        Optional<User> userOptional = userRepository.findByEmail(generateOTPRequest.getEmail());
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            if (user.getGeneratedOTP() == generateOTPRequest.getOtp() && user.isPasswordReset()) {
//                return "Matched";
//            } else {
//                return "Not Matched";
//            }
//        } else {
//            return "Not Matched";
//        }
//    }
//
//    public String forgetPassChange(GenerateOTPRequest2 generateOTPRequest) throws IOException, MessagingException {
//        Optional<User> userOptional = userRepository.findByEmail(generateOTPRequest.getEmail());
//
//
//        User user = userOptional.get();
//        if (user.getGeneratedOTP() == generateOTPRequest.getOtp() && user.isPasswordReset()) {
//
//            user.setPassword(encoder.encode(generateOTPRequest.getNewPassword()));
//            user.setGeneratedOTP(0);
//            user.setPasswordReset(false);
//
//            userRepository.save(user);
//
//            String emailBody = "" +
//                    "<h3>Hello <b>" + userOptional.get().getFirstName() + "</b>,</h3>" +
//                    "<h2>Your password has been changed successfully! </h2>" +
//                    "<br><h3>Thank You </h3>";
//
//            String emailSubject = "Password Changed";
//
//            sendMail(userOptional.get().getEmail(), userOptional.get().getFirstName(), emailSubject, emailBody);
//
//            return "Password Changed";
//        } else {
//            return "Not Matched";
//        }
//
//    }
//}