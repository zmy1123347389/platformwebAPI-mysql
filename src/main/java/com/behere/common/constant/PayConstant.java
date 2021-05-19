package com.behere.common.constant;

/**
 * @author: Behere
 */
public class PayConstant {

    public static final String APP_ID="wx81fef5acff6e9c72";

    public static final int WX_PAY = 0;
    public static final int ALI_PAY = 1;
    /**
     * 应用对应的凭证
     */
    public static final String APP_SECRET="39ed572801764c0ce59c8c96fd7bf553";
    /**
     * 应用对应的密钥
     */
    public static final String APP_KEY="91510107MA6CQBC90H91510107MA6Cqb";
    /**
     * 微信支付商户号
     */
    public static final String MCH_ID="1504258391";
    /**
     * 商品描述
     */
    public static final String BODY="颜语-充值";
    /**
     * 商户号对应的密钥
     */
    public static final String PARTNER_key="123466";

    /**
     * 商户id
     */
    public static final String PARTNER_ID="1504258391";
    /**
     * 常量固定值
     */
    public static final String GRANT_TYPE="client_credential";
    /**
     * 获取预支付id的接口url
     */
    public static final String GATEURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 微信服务器回调通知url
     */
//    public static final String WX_NOTIFY = "http://47.92.104.89:8081/api/v1/recharge/wx/notify";
//
//    public static final String ALIPAY_NOTIFY = "http://47.92.104.89:8081/api/v1/recharge/alipay/notify";
    public static final String WX_NOTIFY = "http://47.92.67.139/api/v1/recharge/wx/notify";

    public static final String ALIPAY_NOTIFY = "http://47.92.67.139/api/v1/recharge/alipay/notify";
//    public static final String WX_NOTIFY = "http://b0d7777b.ngrok.io/api/v1/recharge/wx/notify";
//
//    public static final String ALIPAY_NOTIFY = "http://b0d7777b.ngrok.io/api/v1/recharge/alipay/notify";

    public static final String ALIPAY_APP_ID = "2018061260391230";

    public static final String ALIPAY_PRIVATE_KEY  = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCD2DT8Gpn8iQerh/E7GmZ0MzC1qJvpx46FHTxlEVF0k4CtZx17oMVb5HxK+5MIP+ePvTPRT1A9HvGlCHgZJtXLYTdPSJR7Q702A9MPZ71guwJJKV8utYvnPHY7rGGDXxyyW8HlX9aVkcS3NTGSDWapURs68HYmpyiWHBHmpR4OjB5y6J9tZMGeLTEHI4O4XCaM140QnaPtNy+b2qqu9JpQ9d84TVCe4KaPVjMIN/iHYnsuXwUN+UbEVnrX03wBPpbKWQE6wTALFIPHnXqfwKdVTkuxLZYjJJ5DdZLvlrxyOE9/90d4oj/179RFw+iDpYlzeWuHAIL4eWKPPUooYC9pAgMBAAECggEAExPW3t/Ohk8hr/1Ut5OjXY8GrlJ+dP5uLe7EDW/LFI4/YVf5WSkzRY+zOHpclSYCELd05wWN+7odhx7JmgIXj306rps2+PsdRtKY2LLPIU8ODp4Q6sZUPxptlUblOUgq89f5H4KVn0DfcQ8eZB7SSw+0W/vtbLxlUAsxv+kGh14j17ChSDT7q7Q/9L6pwlLNVGfY0YsmGkJ9ipZucrwMluktP1ICOrd1TzU5dPdky3JtRvxoNlk/809hSIhiCruu0/82GDlbhTDU2tBhprlS32alpeJXeIrU+Kx0J1gjfhGpOO6+0559tA1Z61MsRieIyAVijDySKBvueniueO3NcQKBgQC/UYQhqewL2lmpuTNh5HyxBsQJ7/Z2mJlGW6uiaGiG2nt6DUF/nVn4UJeojXHaDkx3oXqqFW1FmbOBkLYnsh0yziu+lrgqJqo3QUr3EM0X6V48JZJzLtHrkoDi88mPZfgcP6OF/4Llb5thB2dAAA9rlZb0l/N0b24C6NDfmp7c5QKBgQCwa0RXFUnLeligW0i+zs8+eVRZiy3JfU9wqK9Jrn59C0HxZbNq8VZNvpvAp54lF1sMnBlemEt6EKhpLV6ksvfGa4GC59z5FNzr3N00gyDDC3fY/+4/JBji6zpeMTDnA/V+y9CQMjknHDxDR/FjZW/5N/b3SA1zexXVBDPA+jdkNQKBgDetwJePfUvhyxBy/roxpfxbLLh7pq3TMWBhk2zbl/gPI7pcm0BJigKTad+68nR0jtqOG0XeoION/MjrVaft1UOQ7vgzovtZb1ZPYULQF/AqkatNf/aXnK5DX9EtA+8AzRljh/KabvzY4050uh7NJa+4xEsA4fBz8X9VRFyfAjblAoGACTFdu0fxs3epnJmFHeQ66l96JLxa42/zLsSrdaRxFOT8R67r/Xub6qsvBIf3dqlT5kFQFRzMegzaKbjigKSOwrkhY4fffN84b9XvdOgMUf2L/jO/32xrG4/0V6oqiS8soLHQKWNklQQko8Dg9DP59LuU0+feQ5E7XwE8DSc8sa0CgYEApkrPvAv5DWMQ0KG1lUnqCah6AF6Mm/0HF16osPnu9E/QrwRp92mFNHWluVYMLBAsINsxXGO7eJJnP8asLRShMGst5Pl8X3LeLvY7ucTkMPRcji7srhYW5G0nZnijqjqCNTeUo4VhYrSZItbiNyz45vhxDra0Mh/DxBmy1cXYvqk=";

    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg9g0/BqZ/IkHq4fxOxpmdDMwtaib6ceOhR08ZRFRdJOArWcde6DFW+R8SvuTCD/nj70z0U9QPR7xpQh4GSbVy2E3T0iUe0O9NgPTD2e9YLsCSSlfLrWL5zx2O6xhg18cslvB5V/WlZHEtzUxkg1mqVEbOvB2JqcolhwR5qUeDowecuifbWTBni0xByODuFwmjNeNEJ2j7Tcvm9qqrvSaUPXfOE1QnuCmj1YzCDf4h2J7Ll8FDflGxFZ619N8AT6WylkBOsEwCxSDx516n8CnVU5LsS2WIySeQ3WS75a8cjhPf/dHeKI/9e/URcPog6WJc3lrhwCC+Hlijz1KKGAvaQIDAQAB";

    public static final String ALIPAY_API = "https://openapi.alipay.com/gateway.do";

}
