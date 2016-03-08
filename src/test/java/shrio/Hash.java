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
 * ɢ���㷨
 * 
 * @author JTMRengl 2016-03-08
 */
public class Hash {

	/**
	 * ����base64����/�������
	 */
	@Test
	public void md5HashTest() {
		String str = "hello";
		String salt = "123";
		String md5 = new Md5Hash(str, salt).toString();
		System.out.println(md5);
	}

	/**
	 * ����SHA256�㷨������Ӧ��ɢ������
	 */
	@Test
	public void sha256HashTest() {
		String str = "hello";
		String salt = "123";
		String sh256 = new Sha256Hash(str, salt).toString();
		System.out.println(sh256);
	}

	/**
	 * ͨ�õ�ɢ��֧��
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

		// Ĭ���㷨SHA-512
		DefaultHashService hashService = new DefaultHashService();
		hashService.setHashAlgorithmName("SHA-512");
		hashService.setPrivateSalt(new SimpleByteSource("123")); // ˽�Σ�Ĭ����
		hashService.setGeneratePublicSalt(true);// �Ƿ����ɹ��Σ�Ĭ��false
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());// �������ɹ��Ρ�Ĭ�Ͼ����
		hashService.setHashIterations(1); // ����Hashֵ�ĵ�������
		HashRequest request = new HashRequest.Builder().setAlgorithmName("MD5")
				.setSource(ByteSource.Util.bytes("hello")).setSalt(ByteSource.Util.bytes("123")).setIterations(2)
				.build();
		String hex = hashService.computeHash(request).toHex();

		System.out.println(hex);
	}

	/**
	 * SecureRandomNumberGenerator����һ�������
	 */
	@Test
	public void secureRandomNumberGeneratorTest() {
		SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		randomNumberGenerator.setSeed("123".getBytes());
		String hex = randomNumberGenerator.nextBytes().toHex();
		System.out.println("�����:"+hex);
	}
}
