package com.whitelabel.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.whitelabel.app.WhiteLabelApplication;


/**
 * Created by ray on 2017/4/21.
 */

public class PaypalHelper{
//    private  PayPalConfiguration payPalConfiguration=null;
//    public static  final   int REQUEST_CODE_PAYMENT=10000;
    public PaypalHelper(){
        String CONFIG_CLIENT_ID = WhiteLabelApplication.getAppConfiguration().getThirdPartyConfig().getPaypalClientId().getSandbox();
//        CONFIG_CLIENT_ID="Aas9UBfT7TVygV7BXM9CFGZTHKNgMSy70gx7YVXcSvn-6QCgb-kjlAj38t_JzCy1MCvw8eoZqpSkBOhX";
//        payPalConfiguration=new
//                PayPalConfiguration()
//                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
//                .clientId(CONFIG_CLIENT_ID);
    }
    public void startPaypalService(Context context){
//        Intent intent=new Intent(context,PayPalService.class);
//        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,payPalConfiguration);
//        context.startService(intent);
    }
    public void startPaypalPayment(Activity activity,String price, String unit, String productName, String orderNumber){
//        PayPalPayment payPalPayment=new PayPalPayment(new BigDecimal(price),unit,productName,PayPalPayment.PAYMENT_INTENT_SALE);
//        payPalPayment.invoiceNumber(orderNumber);
////        payPalPayment.softDescriptor(productName+", Shipping Fee " + shippingFee);
////        ShippingAddress shippingAddress=null;
////        payPalPayment.providedShippingAddress(shippingAddress);
////        shippingAddress.city("qingdao").countryCode("HK").postalCode("").state("").line1("");
//        Intent intent=new Intent(activity, PaymentActivity.class);
//        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, payPalConfiguration);
//        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
//        activity.startActivityForResult(intent, REQUEST_CODE_PAYMENT);

    }
}
