import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class testVerifyCode {
    @Test
    public void fun() throws Exception{
        VerifyCode verifyCode = new VerifyCode();
        VerifyCode.output(verifyCode.getImage(), new FileOutputStream("C:\\Users\\JOE\\Desktop\\20191007.jpg"));
        System.out.println("验证码：" + verifyCode.getText());
    }
}
