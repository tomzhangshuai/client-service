package com.wufanbao.api.distributionservice.tools;


public class Token {
    private TokenFactor tokenFactor;

    public Token(TokenFactor tokenFactor) {
        this.tokenFactor=tokenFactor;
    }

    /**
     * 创建token
     * @return
     */
    public String create(String aesKey) {
        String newStr = tokenFactor.getUserAgentMd5() + ";" + tokenFactor.getEmployeeId() + ";" + tokenFactor.getCreateTime();
        try {
            return AES256.encryptToBase64(aesKey, newStr);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }

    }

    /**
     * 根据token 获取token因子
     * @param aesKey
     * @param token
     * @return
     */
    public static TokenFactor GetFactor(String aesKey,String token)
    {
        TokenFactor tokenFactor=new TokenFactor();
        String clientStr="";
        try{
            clientStr=AES256.decryptFromBase64(aesKey,token);
        }catch (Exception ex) {
            return  tokenFactor;
        }
        String[] array=clientStr.split(";");
        if(array.length!=3) {
            tokenFactor.setEffectived(false);
            return  tokenFactor;
        }
        tokenFactor.setEffectived(true);
        tokenFactor.setUserAgentMd5(array[0]);
        tokenFactor.setEmployeeId(array[1]);
        tokenFactor.setCreateTime(array[2]);
        return tokenFactor;
    }

}
