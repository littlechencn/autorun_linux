package cn.littlechen.linux.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Shell脚本工具类；
 * Created by lenovo on 2017/8/24.
 */
public class ShellUtils {

    /**
     * 执行shell命令；
     * @param shellCmd 待执行的sh命令
     * @return
     */
    public List<String> execSh(String shellCmd) {
        Process proc = null;
        List<String> execResult = new ArrayList<String>();
        try {
            //cmd 调用 python 脚本；
            proc = Runtime.getRuntime().exec(shellCmd);
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));
            String result_temp = "";
            while ((result_temp = in.readLine()) != null) {
                execResult.add(result_temp);
            }
            in.close();
            proc.waitFor();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } finally {
            return execResult;
        }
    }
}
