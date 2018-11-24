package com.ford.qiye.lisenter;

import com.ford.qiye.model.DtUser;
import org.apache.shiro.SecurityUtils;

/**
 * Created by wanglijun on 16/12/3.
 */
public class LoginUtils {

    public  static String getUserName(){
      DtUser object=(DtUser)SecurityUtils.getSubject ().getPrincipal ();
        if(object!=null){
            return object.getUserName ();
        }
        return null;
    }

    public static String getRealName() {

        DtUser object=(DtUser)SecurityUtils.getSubject ().getPrincipal ();
        if(object!=null){
            return object.getRealName ();
        }
        return null;
    }
}
