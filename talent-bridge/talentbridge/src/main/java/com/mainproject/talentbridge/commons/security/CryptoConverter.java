package com.mainproject.talentbridge.commons.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.mainproject.talentbridge.commons.constants.Constants;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.security.Key;
import java.util.Base64;

@Converter
public class CryptoConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(final String attribute) {
        try {
            final Key secretKey = new SecretKeySpec(Constants.PASSWORD_KEY, Constants.ENCRYPTION_ALGORITHM);
            final Cipher cipher = Cipher.getInstance(Constants.ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            final byte[] encryptedBytes = cipher.doFinal(attribute.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting the string", e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            Key secretKey = new SecretKeySpec(Constants.PASSWORD_KEY, Constants.ENCRYPTION_ALGORITHM);
            Cipher cipher = Cipher.getInstance(Constants.ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(dbData));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting the string", e);
        }
    }
}
