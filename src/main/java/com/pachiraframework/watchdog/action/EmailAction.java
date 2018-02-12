package com.pachiraframework.watchdog.action;

import java.io.StringWriter;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.pachiraframework.watchdog.event.event.MetricReportEvent;

/**
 * 邮件通知
 * 
 * @author KevinWang
 *
 */
@Component
public class EmailAction extends LimitedAction {
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	protected void doExecute(MetricReportEvent context) throws Exception {
		StringWriter out = new StringWriter();
		getTemplate(context).process(context, out);
		String text = out.toString();
		javaMailSender.send(new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
				helper.setTo("12708826@qq.com");
				helper.setFrom("kepler2017@163.com");
				helper.setText(text, true);
				helper.setSubject(String.format("【监控中心】-【(%s)%s】发生告警",context.getMonitor().getType(),context.getMonitor().getName()));
			}
		});
	}

	@Override
	protected String name() {
		return "email";
	}

}
