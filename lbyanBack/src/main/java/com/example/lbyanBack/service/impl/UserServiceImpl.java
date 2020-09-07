package com.example.lbyanBack.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.lbyanBack.mapper.UserInfoMapper;
import com.example.lbyanBack.mapper.UserMapper;
import com.example.lbyanBack.pojo.Result;
import com.example.lbyanBack.pojo.User;
import com.example.lbyanBack.pojo.UserInfo;
import com.example.lbyanBack.pojo.DTO.UserUserInfoDTO;
import com.example.lbyanBack.pojo.DVO.MailVo;
import com.example.lbyanBack.service.IUserService;
import com.example.lbyanBack.util.AESUtil;
import com.example.lbyanBack.util.SystemUtil;

@Service
public class UserServiceImpl implements IUserService {
    private Logger logger = LoggerFactory.getLogger(getClass());// 提供日志类

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private JavaMailSenderImpl mailSender;// 注入邮件工具类

    @Value("${spring.mail.username}")
    private String email;

    public String register(UserUserInfoDTO userAndInfo) {

        List<User> userlist = userMapper.getUserInfo(userAndInfo.getAccount());
        List<UserInfo> userListInfo = userInfoMapper.getUserInfo(userAndInfo.getEmail());
        if (!CollectionUtils.isEmpty(userlist)) {
            return "账号已存在";
        }
        if (!CollectionUtils.isEmpty(userListInfo)) {
            return "邮箱已被注册";
        }

        String enpwd = AESUtil.aesCbcEncrypt(userAndInfo.getPassword(), "bykangs", "0000000000000000");
        User user = new User();
        String userid = SystemUtil.getUuid();
        user.setId(userid);
        user.setAccount(userAndInfo.getAccount());
        user.setPassword(enpwd);
        userMapper.register(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setId(SystemUtil.getUuid());
        userInfo.setNum(userAndInfo.getNum());
        userInfo.setUserid(userid);
        userInfo.setUsername(userAndInfo.getUsername());
        userInfo.setCjsj(new Date());
        userInfoMapper.insertUserInfo(userInfo);

        logger.info("注册成功，开始发送邮件");
        new Thread() {
            @Override
            public void run() {
                MailVo mailVol = new MailVo();
                StringBuilder emails = new StringBuilder();
                emails.append(userAndInfo.getEmail()).append(",").append(email);
                StringBuilder content = new StringBuilder();
                content.append("<div><div style='margin-left:4%;'>");
                content.append("<h2 style='color:#017167;margin-left: -2%;'>");
                content.append(userAndInfo.getUsername()).append("(").append(userAndInfo.getAccount()).append(")");
                content.append("您好：</h2>");
                content.append("<p style='text-indent: 2em;'>您已成功注册，请点击下面的连接进行账号激活</p>");
                content.append("<p style='text-indent: 2em;display: block;word-break: break-all;'>");
                content.append("链接地址：<a style='text-decoration: none;' href='www.lbyan.cn'> www.lbyan.cn</a></p>");
                content.append("</div>");
                content.append("<ul style='color: rgb(169, 169, 189);font-size: 18px;'>");
                content.append("<li>为了保障您帐号的安全，该链接有效期为12小时。</li>");
                content.append("<li>该链接只能使用一次，请周知。</li>");
                content.append("<li>如果该链接无法点击，请直接复制以上网址到浏览器地址栏中访问。</li>");
                content.append("<li>请您妥善保管，此邮件无需回复。</li>");
                content.append("</ul>");
                content.append("</div>");
                mailVol.setTo(emails.toString());
                mailVol.setSubject("lbyan.cn--账号激活");
                mailVol.setText(content.toString());
                mailVol.setBcc("");
                sendMail(mailVol);
                logger.info("发送邮件完成");
            }
        }.start();

        return "注册成功";
    }
    /**
     * 发送邮件
     */
    public MailVo sendMail(MailVo mailVo) {
        try {
            checkMail(mailVo); // 1.检测邮件
            sendMimeMail(mailVo); // 2.发送邮件
            return saveMail(mailVo); // 3.保存邮件
        } catch (Exception e) {
            logger.error("发送邮件失败:", e);// 打印错误信息
            mailVo.setStatus("fail");
            mailVo.setError(e.getMessage());
            return mailVo;
        }

    }

    // 检测邮件信息类
    private void checkMail(MailVo mailVo) {
        if (StringUtils.isEmpty(mailVo.getTo())) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getSubject())) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getText())) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    // 构建复杂邮件信息类
    private void sendMimeMail(MailVo mailVo) {
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);// true表示支持复杂类型
            mailVo.setFrom(getMailSendFrom());// 邮件发信人从配置项读取
            messageHelper.setFrom(mailVo.getFrom());// 邮件发信人
            messageHelper.setTo(mailVo.getTo().split(","));// 邮件收信人
            messageHelper.setSubject(mailVo.getSubject());// 邮件主题
            messageHelper.setText(mailVo.getText(), true);// 邮件内容
            if (!StringUtils.isEmpty(mailVo.getCc())) {// 抄送
                messageHelper.setCc(mailVo.getCc().split(","));
            }
            if (!StringUtils.isEmpty(mailVo.getBcc())) {// 密送
                messageHelper.setCc(mailVo.getBcc().split(","));
            }
            if (mailVo.getMultipartFiles() != null) {// 添加邮件附件
                for (MultipartFile multipartFile : mailVo.getMultipartFiles()) {
                    messageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
                }
            }
            if (StringUtils.isEmpty(mailVo.getSentDate())) {// 发送时间
                mailVo.setSentDate(new Date());
                messageHelper.setSentDate(mailVo.getSentDate());
            }
            mailSender.send(messageHelper.getMimeMessage());// 正式发送邮件
            mailVo.setStatus("ok");
            logger.info("发送邮件成功：{}->{}", mailVo.getFrom(), mailVo.getTo());
        } catch (Exception e) {
            throw new RuntimeException(e);// 发送失败
        }
    }

    // 保存邮件
    private MailVo saveMail(MailVo mailVo) {
        // 将邮件保存到数据库..
        return mailVo;
    }

    // 获取邮件发信人
    public String getMailSendFrom() {
        return mailSender.getJavaMailProperties().getProperty("from");
    }

    @Override
    public Result login(String userinfo, String password) {
        User user = userMapper.getuserinfoByMailOrAccount(userinfo);
        if (null == user) {
            return new Result("账户不存在", false, null);
        }
        String pwd = AESUtil.aesCbcDecrypt(user.getPassword(), "bykangs", "0000000000000000");
        if (password.equals(pwd)) {
            return new Result("登录成功", true, null);
        } else {
            return new Result("密码错误", false, null);
        }
    }
}
