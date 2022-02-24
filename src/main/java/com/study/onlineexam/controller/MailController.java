package com.study.onlineexam.controller;

import com.study.onlineexam.vo.ResponseVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/send")
    @ApiOperation("发送验证码")
    public ResponseVo mailSend(String mail) throws MessagingException {
        try{
            System.out.println(mail);
            String uuid = UUID.randomUUID().toString();
            Random random = new Random();
            int code =  (int) (random.nextDouble() * (999999 - 100000 + 1)) + 100000;
            //将查询结果进行缓存，并设置有效期为5分钟
            redisTemplate.opsForValue().set("mailCode:"+uuid,code,5,TimeUnit.MINUTES);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setSubject("验证码");
            helper.setText("<h2 style='font-weight:bold'>您的验证码如下(有效期五分钟):"+code+"</h2>",true);
            helper.setTo(mail);
            helper.setFrom("1025524464@qq.com");
            mailSender.send(mimeMessage);
            return ResponseVo.result(10100,"发送成功",uuid);
        }catch (Exception e){
            return ResponseVo.result(10200,"发送失败");
        }
    }

    @PostMapping("/check")
    @ApiOperation("校验验证码")
    public ResponseVo mailCheck(String uuid,int code){
        try{
            int value = (int) redisTemplate.opsForValue().get("mailCode:"+uuid);
            if (value == code){
                return ResponseVo.result(10201,"验证成功");
            }else {
                return ResponseVo.result(10202,"验证失败");
            }
        }catch (NullPointerException e){
            return ResponseVo.result(10203,"验证失败");
        }catch (NumberFormatException e){
            return ResponseVo.result(10204,"输入值错误");
        }
    }
}
