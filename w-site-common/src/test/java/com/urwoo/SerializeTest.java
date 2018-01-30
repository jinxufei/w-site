package com.urwoo;

import com.urwoo.model.User;
import com.urwoo.tools.ByteArrayTools;
import com.urwoo.tools.SerializeTools;
import org.junit.Test;

public class SerializeTest {

    @Test
    public void serialize(){
        User u = new User();
        u.setId(1L);
        u.setName("zs");
        byte[] bytes = SerializeTools.serialize(u);
        System.err.println("===="+ByteArrayTools.toHexString(bytes));
    }

    @Test
    public void derialize(){
        String s = "aced000573720014636f6d2e7572776f6f2e6d6f64656c2e55736572fe15cbdf0ae869830200024c000269647400104c6a6176612f6c616e672f4c6f6e673b4c00046e616d657400124c6a6176612f6c616e672f537472696e673b78707372000e6a6176612e6c616e672e4c6f6e673b8be490cc8f23df0200014a000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787000000000000000017400027a73";
        User u = SerializeTools.deserialize(ByteArrayTools.hexStringToByteArray(s), User.class);
    }
}
