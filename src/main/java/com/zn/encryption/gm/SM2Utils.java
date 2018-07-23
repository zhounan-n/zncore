package com.zn.encryption.gm;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;

import java.io.*;
import java.math.BigInteger;

public class SM2Utils 
{
	//生成随机秘钥对
	public static void generateKeyPair(String filePath){
		SM2 sm2 = SM2.Instance();
		AsymmetricCipherKeyPair key = sm2.ecc_key_pair_generator.generateKeyPair();
		ECPrivateKeyParameters ecpriv = (ECPrivateKeyParameters) key.getPrivate();
		ECPublicKeyParameters ecpub = (ECPublicKeyParameters) key.getPublic();
		BigInteger privateKey = ecpriv.getD();
		ECPoint publicKey = ecpub.getQ();
		try {
			FileWriter pubfw = new FileWriter(filePath + "/gm_publicKey.keystore");
			FileWriter prifw = new FileWriter(filePath + "/gm_privateKey.keystore");
			BufferedWriter pubbw = new BufferedWriter(pubfw);
			BufferedWriter pribw = new BufferedWriter(prifw);
			pubbw.write(Util.byteToHex(publicKey.getEncoded()));
			pribw.write(Util.byteToHex(privateKey.toByteArray()));
			pubbw.flush();
			pubbw.close();
			pubfw.close();
			pribw.flush();
			pribw.close();
			prifw.close();

		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("公钥: " + Util.byteToHex(publicKey.getEncoded()));
		System.out.println("私钥: " + Util.byteToHex(privateKey.toByteArray()));
	}
	
	//数据加密
	public static String encrypt(byte[] publicKey, byte[] data) throws IOException
	{
		if (publicKey == null || publicKey.length == 0)
		{
			return null;
		}
		
		if (data == null || data.length == 0)
		{
			return null;
		}
		
		byte[] source = new byte[data.length];
		System.arraycopy(data, 0, source, 0, data.length);
		
		Cipher cipher = new Cipher();
		SM2 sm2 = SM2.Instance();
		ECPoint userKey = sm2.ecc_curve.decodePoint(publicKey);
		
		ECPoint c1 = cipher.Init_enc(sm2, userKey);
		cipher.Encrypt(source);
		byte[] c3 = new byte[32];
		cipher.Dofinal(c3);
		
//		System.out.println("C1 " + Util.byteToHex(c1.getEncoded()));
//		System.out.println("C2 " + Util.byteToHex(source));
//		System.out.println("C3 " + Util.byteToHex(c3));
		//C1 C2 C3拼装成加密字串
		return Util.byteToHex(c1.getEncoded()) + Util.byteToHex(source) + Util.byteToHex(c3);
		
	}
	
	//数据解密
	public static byte[] decrypt(byte[] privateKey, byte[] encryptedData) throws IOException
	{
		if (privateKey == null || privateKey.length == 0)
		{
			return null;
		}
		
		if (encryptedData == null || encryptedData.length == 0)
		{
			return null;
		}
		//加密字节数组转换为十六进制的字符串 长度变为encryptedData.length * 2
		String data = Util.byteToHex(encryptedData);
		/***分解加密字串
		 * （C1 = C1标志位2位 + C1实体部分128位 = 130）
		 * （C3 = C3实体部分64位  = 64）
		 * （C2 = encryptedData.length * 2 - C1长度  - C2长度）
		 */
		byte[] c1Bytes = Util.hexToByte(data.substring(0,130));
		int c2Len = encryptedData.length - 97;
		byte[] c2 = Util.hexToByte(data.substring(130,130 + 2 * c2Len));
		byte[] c3 = Util.hexToByte(data.substring(130 + 2 * c2Len,194 + 2 * c2Len));
		
		SM2 sm2 = SM2.Instance();
		BigInteger userD = new BigInteger(1, privateKey);
		
		//通过C1实体字节来生成ECPoint
		ECPoint c1 = sm2.ecc_curve.decodePoint(c1Bytes);
		Cipher cipher = new Cipher();
		cipher.Init_dec(userD, c1);
		cipher.Decrypt(c2);
		cipher.Dofinal(c3);
		
		//返回解密结果
		return c2;
	}

	public static String loadPublicKeyByFile(String path) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path
					+ "/gm_publicKey.keystore"));
			String readLine;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			throw new Exception("国密公钥数据流读取错误");
		} catch (NullPointerException e) {
			throw new Exception("国密公钥输入流为空");
		}
	}

	/**
	 * 从文件中加载私钥
	 *
	 * @param path 私钥文件名
	 * @return 是否成功
	 * @throws Exception
	 */
	public static String loadPrivateKeyByFile(String path) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path
					+ "/gm_privateKey.keystore"));
			String readLine;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			throw new Exception("国密私钥数据读取错误");
		} catch (NullPointerException e) {
			throw new Exception("国密私钥输入流为空");
		}
	}


	public static void main(String[] args) throws Exception 
	{
		String filePath = "H:\\";
		//生成密钥对
		//generateKeyPair(filePath);
		
		String plainText = "春天的夏天";
		byte[] sourceData = plainText.getBytes();
		
		/*//下面的秘钥可以使用generateKeyPair()生成的秘钥内容
		// 国密规范正式私钥
		String prik = "3690655E33D5EA3D9A4AE1A1ADD766FDEA045CDEAA43A9206FB8C430CEFE0D94";
		// 国密规范正式公钥
		String pubk = "04F6E0C3345AE42B51E06BF50B98834988D54EBC7460FE135A48171BC0629EAE205EEDE253A530608178A98F1E19BB737302813BA39ED3FA3C51639D7A20C7391A";
*/
		System.out.println("加密: ");
		String cipherText = SM2Utils.encrypt(Util.hexToByte(loadPublicKeyByFile(filePath)), sourceData);
		System.out.println(cipherText);
		System.out.println("解密: ");
		plainText = new String(SM2Utils.decrypt(Util.hexToByte(loadPrivateKeyByFile(filePath)), Util.hexToByte(cipherText)));
		System.out.println(plainText);

		//加密测试数据
		System.out.println("加密测试……");
		String name = SM2Utils.encrypt(Util.hexToByte(loadPublicKeyByFile(filePath)), "潘友其".getBytes("utf-8"));
		System.out.println(name);
		String mobile = SM2Utils.encrypt(Util.hexToByte(loadPublicKeyByFile(filePath)), "13868368852".getBytes());
		System.out.println(mobile);
		String idCard = SM2Utils.encrypt(Util.hexToByte(loadPublicKeyByFile(filePath)), "330327197902020014".getBytes());
		System.out.println(idCard);

	}
}
