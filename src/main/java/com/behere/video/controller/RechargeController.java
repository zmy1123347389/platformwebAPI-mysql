package com.behere.video.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.behere.common.annotation.Log;
import com.behere.common.constant.Constant;
import com.behere.common.constant.MsgConstant;
import com.behere.common.constant.PayConstant;
import com.behere.common.utils.IpUtil;
import com.behere.common.utils.NeteaseUtils;
import com.behere.common.utils.R;
import com.behere.common.utils.StringUtils;
import com.behere.common.utils.pay.MD5Util;
import com.behere.common.utils.pay.UUID;
import com.behere.common.utils.pay.WXUtil;
import com.behere.common.utils.pay.XMLUtil;
import com.behere.common.utils.pay.handler.PrepayIdRequestHandler;
import com.behere.video.dao.UserDao;
import com.behere.video.domain.*;
import com.behere.video.model.InvitationRechargeRecord;
import com.behere.video.model.UserModel;
import com.behere.video.service.RechargeService;
import com.behere.video.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author: Behere
 */
@Controller
@RequestMapping(value = "/api/v1/recharge")
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    @Autowired
    private UserService userService;

    @Log("??????????????????")
    @PostMapping("/queryRechargeRate")
    @ResponseBody
    public R queryRechargeRate() {
        try {
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put("cnyToDiamond", Constant.CNY_TO_DIAMOND_RATE);
            map.put("diamondToFlower", Constant.DIAMOND_TO_FLOWER_RATE);
            return R.ok(map);
        } catch (Exception e) {
            return R.error();
        }
    }


    @Log("??????????????????")
    @PostMapping("/queryRechargePackage")
    @ResponseBody
    public R queryRechargePackage() {
        try {
            List<RechargePackage> rechargePackages = rechargeService.queryRechargePackages();
            return R.ok(rechargePackages);
        } catch (Exception e) {
            return R.error();
        }
    }

    /**
     * ??????????????????????????????prepayId
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/wxPrepay")
    @ResponseBody
    public R wxPrepay(long userId, int rechargePackageId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);
        RechargePackage rechargePackage = rechargeService.queryRechargePackageById(rechargePackageId);
        int total_fee = rechargePackage.getPrice() * 100;
        prepayReqHandler.setParameter("appid", PayConstant.APP_ID);
        prepayReqHandler.setParameter("body", PayConstant.BODY);
        prepayReqHandler.setParameter("mch_id", PayConstant.MCH_ID);
        String nonce_str = WXUtil.getNonceStr();
        prepayReqHandler.setParameter("nonce_str", nonce_str);
        prepayReqHandler.setParameter("notify_url", PayConstant.WX_NOTIFY);
        String out_trade_no = String.valueOf(UUID.next());
        prepayReqHandler.setParameter("out_trade_no", out_trade_no);
        prepayReqHandler.setParameter("spbill_create_ip", IpUtil.getRemoteHost(request));
        String timestamp = WXUtil.getTimeStamp();
        prepayReqHandler.setParameter("time_start", timestamp);
        prepayReqHandler.setParameter("total_fee", String.valueOf(total_fee));
        prepayReqHandler.setParameter("trade_type", "APP");
        prepayReqHandler.setParameter("sign", prepayReqHandler.createMD5Sign());
        prepayReqHandler.setGateUrl(PayConstant.GATEURL);
        String prepayid = prepayReqHandler.sendPrepay();
        int row = saveRechargeRecord(out_trade_no, userId, rechargePackage, PayConstant.WX_PAY);
        if (prepayid != null && !prepayid.equals("") && row > 0) {
            String signs = "appid=" + PayConstant.APP_ID + "&noncestr=" + nonce_str + "&package=Sign=WXPay&partnerid="
                    + PayConstant.PARTNER_ID + "&prepayid=" + prepayid + "&timestamp=" + timestamp + "&key="
                    + PayConstant.APP_KEY;
            WXPay wxPay = new WXPay();
            wxPay.setAppid(PayConstant.APP_ID);
            wxPay.setNoncestr(nonce_str);
            wxPay.setPartnerid(PayConstant.PARTNER_ID);
            wxPay.setPkg("Sign=WXPay");
            wxPay.setTimestamp(timestamp);
            wxPay.setPrepayid(prepayid);
            wxPay.setSign(MD5Util.MD5Encode(signs, "utf8").toUpperCase());
            return R.ok(wxPay);
        } else {
            return R.error(-1, Constant.GET_DATA_FAIL);
        }
    }

    /**
     * ??????????????????????????????
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/wx/notify")
    public void wxPayNotify(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PrintWriter writer = response.getWriter();
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");
        Map<String, String> map = null;
        try {
            map = XMLUtil.doXMLParse(result);
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        if (Constant.RECHARGE_SUCCESS.equals(map.get("return_code"))) {
            System.out.println("???????????????");
            RechargeRecord rechargeRecord = rechargeService.queryRechargeRecordByOrderId(map.get("out_trade_no"));
            //????????????????????????????????????????????????????????????
            if(rechargeRecord.getStatus() == 0){
                int row = rechargeService.recharge(rechargeRecord);
                if (row > 0) {
                    String notifyStr = XMLUtil.setXML(Constant.RECHARGE_SUCCESS, "");
                    writer.write(notifyStr);
                    writer.flush();
                    sendRechargeMsg(rechargeRecord);
                    userService.addSharingUserFlower(rechargeRecord.getUserId(), (rechargeRecord.getDiamond() * Constant.DIAMOND_TO_FLOWER_RATE * 35 / 100));
                }
            }
        }
    }

    @PostMapping("/alipay")
    @ResponseBody
    public R alipay(long userId, int rechargePackageId) {
        RechargePackage rechargePackage = rechargeService.queryRechargePackageById(rechargePackageId);
        String businessId = StringUtils.getUUID();
        //??????????????????
        AlipayClient alipayClient = new DefaultAlipayClient(PayConstant.ALIPAY_API,
                PayConstant.ALIPAY_APP_ID,
                PayConstant.ALIPAY_PRIVATE_KEY, "json", "utf-8",
                PayConstant.ALIPAY_PUBLIC_KEY, "RSA2");
        //???????????????API?????????request???,??????????????????????????????,???????????????????????????alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK????????????????????????????????????????????????????????????????????????????????????sdk???model????????????(model???biz_content???????????????????????????biz_content)???
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(PayConstant.BODY);
        model.setSubject(PayConstant.BODY);
        model.setOutTradeNo(businessId);
        model.setTimeoutExpress("15m");
        model.setTotalAmount(String.valueOf(rechargePackage.getPrice()));
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(PayConstant.ALIPAY_NOTIFY);
        try {
            //???????????????????????????????????????????????????sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            saveRechargeRecord(businessId, userId, rechargePackage, PayConstant.ALI_PAY);
            return R.ok(response);
        } catch (AlipayApiException e) {
            return R.error();
        }
    }

    @PostMapping("/alipay/notify")
    @ResponseBody
    public R alipayNotify(HttpServletRequest request) {
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //??????????????????????????????????????????????????????
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        try{
            boolean flag = AlipaySignature.rsaCheckV1(params, PayConstant.ALIPAY_PUBLIC_KEY, "utf-8","RSA2");
            if (!flag) {
                RechargeRecord rechargeRecord = rechargeService.queryRechargeRecordByOrderId(params.get("out_trade_no"));
                if(rechargeRecord.getStatus() == 0){
                    int row = rechargeService.recharge(rechargeRecord);
                    if (row > 0) {
                        sendRechargeMsg(rechargeRecord);
                        userService.addSharingUserFlower(rechargeRecord.getUserId(), rechargeRecord.getDiamond() * Constant.DIAMOND_TO_FLOWER_RATE * 35 / 100);
                    }
                }
            }
        } catch (AlipayApiException e) {
            return R.error();
        }
        return R.ok();
    }



    public int saveRechargeRecord(String businessId, long userId, RechargePackage rechargePackage, int payType) {
        RechargeRecord rechargeRecord = new RechargeRecord();
        rechargeRecord.setOrderId(businessId);
        rechargeRecord.setPayType(payType);
        rechargeRecord.setUserId(userId);
        rechargeRecord.setPrice(rechargePackage.getPrice());
        rechargeRecord.setDiamond(rechargePackage.getDiamond());
        return rechargeService.saveRechargeRecord(rechargeRecord);
    }



    @Log("????????????")
    @PostMapping("/queryRechargeRecords")
    @ResponseBody
    public R queryRechargeRecords(long userId,
                                  @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<RechargeRecord> rechargeRecords = rechargeService.queryRechargeRecord(userId);
            PageInfo<RechargeRecord> pageInfo = new PageInfo<RechargeRecord>(rechargeRecords);
            return R.ok(pageInfo);
        } catch (Exception e) {
            return R.error(-1, Constant.GET_DATA_FAIL);
        }
    }

    @Log("??????????????????")
    @PostMapping("/queryInvitationRechargeRecords")
    @ResponseBody
    public R queryInvitationRechargeRecords(long userId,
                             @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<InvitationRechargeRecord> invitationRechargeRecords = rechargeService.queryInvitationRechargeRecords(userId);
            PageInfo<InvitationRechargeRecord> pageInfo = new PageInfo<InvitationRechargeRecord>(invitationRechargeRecords);
            return R.ok(pageInfo);
        } catch (Exception e) {
            return R.error();
        }
    }

    @Log("??????????????????")
    @PostMapping("/testRecharge")
    @ResponseBody
    public R testRecharge(String orderId) {
        try {
            RechargeRecord rechargeRecord = rechargeService.queryRechargeRecordByOrderId(orderId);
            rechargeService.recharge(rechargeRecord);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    @Log("????????????")
    @PostMapping("/withdrawalRecord")
    @ResponseBody
    public R withdrawalRecord(long userId,
                              String createTime,
                              @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        try {
            WithdrawalRecord withdrawalRecord = new WithdrawalRecord();
            double withdrawalMoney = rechargeService.sumWithdrawal(userId);
            PageHelper.startPage(pageNum, pageSize);
            List<WithdrawalRecordList> withdrawalRecords = rechargeService.queryWithdrawalRecordList(userId, createTime);
            PageInfo<WithdrawalRecordList> pageInfo = new PageInfo<WithdrawalRecordList>(withdrawalRecords);
            withdrawalRecord.setWithdrawalMoney(withdrawalMoney);
            withdrawalRecord.setWithdrawalRecordLists(pageInfo);
            return R.ok(withdrawalRecord);
        } catch (Exception e) {
            return R.error();
        }
    }

    public void sendRechargeMsg(RechargeRecord rechargeRecord) {
        try {
           // String msg = "?????????????????????" + rechargeRecord.getDiamond() + "??????????????????" + rechargeRecord.getDiamond() * 10 + "???????????????????????????????????????~";
            String msg = NeteaseUtils.setMsgExtMap(null, null, null, null, Long.valueOf(rechargeRecord.getDiamond() * 10), MsgConstant.RECHARGE_SUCCESS, null);
            NeteaseUtils.sendMsg(msg, rechargeRecord.getUserId());
        } catch (Exception e) {

        }
    }
}