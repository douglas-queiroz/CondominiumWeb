/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscob.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Douglas
 */
public class Util {

    private static MessageDigest md = null;

    /**
     * Metodo estatico para a geracao do algoritmo de criptografia.
     */
    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Criptografa a senha.
     *
     * @param pwd String A senha normal.
     * @return String A senha criptografaga.
     */
    public static String criptografar(String pwd) {
        if (md != null) {
            return new String(hexCodes(md.digest(pwd.getBytes())));
        }
        return null;
    }

    private static char[] hexCodes(byte[] text) {
        char[] hexOutput = new char[text.length * 2];
        String hexString;

        for (int i = 0; i < text.length; i++) {
            hexString = "00" + Integer.toHexString(text[i]);
            hexString.getChars(hexString.length() - 2, hexString.length(), hexOutput, i * 2);
        }
        return hexOutput;
    }
    
    public static boolean validaCPF(String cpf) {  
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");
        
        if (cpf.length() != 11)  
            return false;  
  
        String numDig = cpf.substring(0, 9);  
        return calcDigVerif(numDig).equals(cpf.substring(9, 11));  
    }

    private static String calcDigVerif(String num) {  
        Integer primDig, segDig;  
        int soma = 0, peso = 10;  
        for (int i = 0; i < num.length(); i++)  
                soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;  
  
        if (soma % 11 == 0 | soma % 11 == 1)  
            primDig = new Integer(0);  
        else  
            primDig = new Integer(11 - (soma % 11));  
  
        soma = 0;  
        peso = 11;  
        for (int i = 0; i < num.length(); i++)  
                soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;  
          
        soma += primDig.intValue() * 2;  
        if (soma % 11 == 0 | soma % 11 == 1)  
            segDig = new Integer(0);  
        else  
            segDig = new Integer(11 - (soma % 11));  
  
        return primDig.toString() + segDig.toString();  
    }  
  
    private static int calcSegDig(String cpf, int primDig) {  
        int soma = 0, peso = 11;  
        for (int i = 0; i < cpf.length(); i++)  
                soma += Integer.parseInt(cpf.substring(i, i + 1)) * peso--;  
          
        soma += primDig * 2;  
        if (soma % 11 == 0 | soma % 11 == 1)  
            return 0;  
        else  
            return 11 - (soma % 11);  
    }  
}
