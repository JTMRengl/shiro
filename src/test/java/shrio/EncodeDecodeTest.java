package shrio;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.junit.Assert;
import org.junit.Test;


/**
 * shiro±àÂë
 * @author JTMRengl
 * 2016-03-08
 */
public class EncodeDecodeTest {
	
	/**
	 * ²âÊÔbase64±àÂë/½âÂë²Ù×÷
	 */
	@Test
	public void encodeDecodetestBase64(){
		String str1 = "hello";
		String eTString = Base64.encodeToString(str1.getBytes());
		System.out.println(eTString);
		String str2 = Base64.decodeToString(eTString);
		Assert.assertEquals(str1, str2);		
	}
	
	/**
	 * ²âÊÔ16½øÖÆ×Ö·û´®±àÂë/½âÂë²Ù×÷
	 */
	@Test
	public void encodeDecodetest16(){
		String str1 = "hello";
		String eTString = Hex.encodeToString(str1.getBytes());
		System.out.println(eTString);
		String str2 = new String(Hex.decode(eTString.getBytes()));
		Assert.assertEquals(str1, str2);
	}
}
