package com.security.filelocker.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created by DaiPhongPC on 9/9/2017.
 */

public class SecurityFile {

    public static void encrypt(String key, InputStream is, OutputStream os)  {
        try {
            encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static void decrypt(String key, InputStream is, OutputStream os) {
        try {
            encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) {
        DESKeySpec dks = null;
        try {
            dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            SecretKey desKey = skf.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

            if (mode == Cipher.ENCRYPT_MODE) {
                cipher.init(Cipher.ENCRYPT_MODE, desKey);
                CipherInputStream cis = new CipherInputStream(is, cipher);
                doCopy(cis, os);
            } else if (mode == Cipher.DECRYPT_MODE) {
                cipher.init(Cipher.DECRYPT_MODE, desKey);
                CipherOutputStream cos = new CipherOutputStream(os, cipher);
                doCopy(is, cos);
            }
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

    }

    public static void doCopy(InputStream is, OutputStream os) throws IOException {
        byte[] bytes = new byte[64];
        int numBytes;
        while ((numBytes = is.read(bytes)) != -1) {
            os.write(bytes, 0, numBytes);
        }
        os.flush();
        os.close();
        is.close();
    }
}
