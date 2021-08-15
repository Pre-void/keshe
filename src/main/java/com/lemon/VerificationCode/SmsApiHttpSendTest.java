package com.lemon.VerificationCode;



import java.net.URLEncoder;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 短信AP发送
 * @author fanjiawen
 * @version $Id: javaHttpNewApiDemo, v 0.1 2019/1/23 11:42 JiangPengFei Exp $$
 */
public class SmsApiHttpSendTest {

    public  String getRandNum(){
        String randNum = new Random().nextInt(1000000) + "";
        if (randNum.length() != 6){
            return getRandNum();
        }
        return randNum;
    }
    /**
     * 手机号码
     * phone number
     */

    private ExecutorService executorService = new ThreadPoolExecutor(10,
            15,
            5 * 60,
            // idle timeout
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
            new DefaultThreadFactory("sendSms-"));

    /**
     * 发送短信
     */
    //yzm是前台传入的
    public void execute(String to,String yzm) throws Exception{
        for (int i = 1; i <= 1; i++) {

            StringBuilder sb = new StringBuilder();
            sb.append("accountSid").append("=").append(Config.ACCOUNT_SID);
            sb.append("&sendBy").append("=").append("2");
            sb.append("&to").append("=").append((to));
            sb.append("&param").append("=").append(URLEncoder.encode(String.valueOf(i),"UTF-8"));
//			sb.append("&templateid").append("=").append("1326");
            //消息模板 message

            sb.append("&smsContent").append("=").append( URLEncoder.encode("【Lemon】您的验证码为"+yzm+"，该验证码5分钟内有效。请勿泄漏于他人。","UTF-8"));
            String body = sb.toString() + HttpUtil.createCommonParam(Config.ACCOUNT_SID, Config.AUTH_TOKEN);
            executorService.execute(() -> {
                long startTime1 = System.currentTimeMillis();
                String result = HttpUtil.post(Config.BASE_URL, body);
                System.out.println("=======================请求总共耗时:" + (System.currentTimeMillis() - startTime1));
                System.out.println(result);
            });

            if(i % 2 == 0){
                Thread.sleep(100);
            }
        }

    }
}
