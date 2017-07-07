package com.whitelabel.app.data.service;

import com.whitelabel.app.application.WhiteLabelApplication;
import com.whitelabel.app.data.DataManager;
import com.whitelabel.app.data.preference.PreferHelper;
import com.whitelabel.app.data.retrofit.ProductApi;
import com.whitelabel.app.model.AddressBook;
import com.whitelabel.app.model.ApiFaildException;
import com.whitelabel.app.model.ResponseModel;
import com.whitelabel.app.model.SVRAppserviceCatalogSearchReturnEntity;
import com.whitelabel.app.model.SVRAppserviceSaveBillingEntity;
import com.whitelabel.app.model.TMPLocalCartRepositoryProductEntity;
import com.whitelabel.app.utils.ErrorHandlerAction;
import com.whitelabel.app.utils.RxUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/7/5.
 */
public class CommodityManager  implements ICommodityManager{
    private ProductApi  productApi;
    private PreferHelper  cacheHelper;
    private SVRAppserviceCatalogSearchReturnEntity svrAppserviceCatalogSearchReturnEntity;
    public CommodityManager(ProductApi productApi, PreferHelper preferHelper){
        this.productApi=productApi;
        cacheHelper=preferHelper;
    }
    @Override
    public Observable<SVRAppserviceCatalogSearchReturnEntity> getAllCategoryManager() {
        if(svrAppserviceCatalogSearchReturnEntity==null) {
            return productApi.getBaseCategory()
                    .subscribeOn(Schedulers.io())
                    .doOnNext(new Action1<SVRAppserviceCatalogSearchReturnEntity>() {
                @Override
                public void call(SVRAppserviceCatalogSearchReturnEntity svrAppserviceCatalogSearchReturnEntity) {
                    CommodityManager.this.svrAppserviceCatalogSearchReturnEntity = svrAppserviceCatalogSearchReturnEntity;
                }
            });
        }else{
            return Observable.just(svrAppserviceCatalogSearchReturnEntity);
        }
    }
    @Override
    public Observable<Integer> getLocalShoppingProductCount() {
        return cacheHelper.getShoppingCartProduct()
                .subscribeOn(Schedulers.io())
        .flatMap(new Func1<List<TMPLocalCartRepositoryProductEntity>, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(List<TMPLocalCartRepositoryProductEntity> tmpLocalCartRepositoryProductEntities) {
                if(tmpLocalCartRepositoryProductEntities!=null){
                    return Observable.just(sumProductCount(tmpLocalCartRepositoryProductEntities));
                }
                return Observable.just(0);
            }
        });
    }
    public int sumProductCount(List<TMPLocalCartRepositoryProductEntity>  tmps){
        int count=0;
        for(TMPLocalCartRepositoryProductEntity  tmp: tmps){
                count+=tmp.getSelectedQty();
         }
         return count;
    }

    @Override
    public Observable<List<AddressBook>> getAddressListCache(String userId) {
        return cacheHelper.getAddressListCache(userId);
    }
}
