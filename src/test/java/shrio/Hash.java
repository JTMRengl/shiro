package shrio;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Test;

/**
 * 散列算法
 * 
 * @author JTMRengl 2016-03-08
 */
public class Hash {

	/**
	 * 测试base64编码/解码操作
	 */
	@Test
	public void md5HashTest() {
		String str = "hello";
		String salt = "123";
		String md5 = new Md5Hash(str, salt).toString();
		System.out.println(md5);
	}

	/**
	 * 测试SHA256算法生成相应的散列数据
	 */
	@Test
	public void sha256HashTest() {
		String str = "hello";
		String salt = "123";
		String sh256 = new Sha256Hash(str, salt).toString();
		System.out.println(sh256);
	}

	/**
	 * 通用的散列支持
	 */
	@Test
	public void simpleHashTest() {
		String str = "hello";
		String salt = "123";

		String simpleHash = new SimpleHash("SHA-1", str, salt).toString();
		System.out.println(simpleHash);
	}

	/**
	 * HashService
	 */
	@Test
	public void hashServiceTest() {

		// 默认算法SHA-512
		DefaultHashService hashService = new DefaultHashService();
		hashService.setHashAlgorithmName("SHA-512");
		hashService.setPrivateSalt(new SimpleByteSource("123")); // 私盐，默认无
		hashService.setGeneratePublicSalt(true);// 是否生成公盐，默认false
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());// 用于生成公盐。默认就这个
		hashService.setHashIterations(1); // 生成Hash值的迭代次数
		HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5")
				.setSource(ByteSource.Util.bytes("hello")).setSalt(ByteSource.Util.bytes("123")).setIterations(2)
				.build();
		String hex = hashService.computeHash(request).toHex();

		System.out.println(hex);
	}

	/**
	 * SecureRandomNumberGenerator生成一个随机数
	 */
	@Test
	public void secureRandomNumberGeneratorTest() {
		SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		randomNumberGenerator.setSeed("123".getBytes());
		String hex = randomNumberGenerator.nextBytes().toHex();
		System.out.println("随机数:"+hex);
	}
}
