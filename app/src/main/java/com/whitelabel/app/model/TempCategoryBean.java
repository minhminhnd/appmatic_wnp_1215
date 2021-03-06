package com.whitelabel.app.model;

import com.whitelabel.app.activity.ProductListActivity;
import com.whitelabel.app.utils.JDataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by img on 2018/1/25.
 */

public class TempCategoryBean {
    public final static int TABBAR_INDEX_NONE = -1;
    public Long mGATrackTimeStart = 0L;
    public int currentProductListFragmentPosition = 0;
    private int currentFilterSortTabIndex;
    public ArrayList<SVRAppserviceProductSearchParameter> searchCategoryParameterArrayList=new ArrayList<>();
    public CategoryBaseBean.CategoryBean.ChildrenBeanX searchCategoryEntity;
    public SVRAppserviceProductSearchParameter svrAppserviceProductSearchParameter;
    public SVRAppserviceProductFilterSelectedItem filterSelectedItem = new SVRAppserviceProductFilterSelectedItem();
    public List<SVRAppserviceProductFilterSelectedItem> filterSelectedItemList = new ArrayList<>();
    public String leftMenuTitle;
    private static TempCategoryBean tempCategoryBean;
    public static TempCategoryBean getInstance(){
        if (tempCategoryBean==null){
            tempCategoryBean=new TempCategoryBean();
        }
        return tempCategoryBean;
    }

    public int getCurrentProductListFragmentPosition() {
        return currentProductListFragmentPosition;
    }

    public void setCurrentProductListFragmentPosition(int currentProductListFragmentPosition) {
        this.currentProductListFragmentPosition = currentProductListFragmentPosition;
    }

    public int getCurrentFilterSortTabIndex() {
        return currentFilterSortTabIndex;
    }

    public void resetCurrentFilterSortTabIndex() {
        currentFilterSortTabIndex = TABBAR_INDEX_NONE;
    }

    public SVRAppserviceProductSearchParameter getSVRAppserviceProductSearchParameterById(int index) {
        if (index < 0 || searchCategoryParameterArrayList.size() <= index) {
            return null;
        } else {
            return searchCategoryParameterArrayList.get(index);
        }
    }

    public CategoryBaseBean.CategoryBean.ChildrenBeanX getSearchCategoryEntity() {
        return searchCategoryEntity;
    }

    /**
     * @param type FragmentType
     */
    public SVRAppserviceProductSearchParameter getSVRAppserviceProductSearchParameterById(int type, int index) {
        if (ProductListActivity.FRAGMENT_TYPE_PRODUCTLIST_CATEGORY == type) {
            return getSVRAppserviceProductSearchParameterById(index);
        } else if (ProductListActivity.FRAGMENT_TYPE_PRODUCTLIST_KEYWORDS == type) {
            return getSvrAppserviceProductSearchParameter();
        } else {
            return null;
        }
    }

    private SVRAppserviceProductSearchParameter getSvrAppserviceProductSearchParameter() {
        if (svrAppserviceProductSearchParameter == null) {
            svrAppserviceProductSearchParameter = new SVRAppserviceProductSearchParameter();
        }
        return svrAppserviceProductSearchParameter;
    }

    public void clearSVRAppserviceProductFilterSelectedItemById(int type, int index) {
        if (ProductListActivity.FRAGMENT_TYPE_PRODUCTLIST_CATEGORY == type) {
            getSVRAppserviceProductFilterSelectedItemById(index).clearAll();
        } else if (ProductListActivity.FRAGMENT_TYPE_PRODUCTLIST_KEYWORDS == type) {
            getSVRAppserviceProductFilterSelectedItem().clearAll();
        }
    }

    public void clearSVRAppserviceProductFilterSelectedItemByCategory(){
        if(filterSelectedItemList != null){
            filterSelectedItemList.clear();
        }
    }

    public void clearSVRAppserviceProductSearchParameterByCategory() {
       if(searchCategoryParameterArrayList != null){
           searchCategoryParameterArrayList.clear();
       }

       if(svrAppserviceProductSearchParameter != null){
           svrAppserviceProductSearchParameter = null;
       }
    }

    public SVRAppserviceProductFilterSelectedItem getSVRAppserviceProductFilterSelectedItemById(int type, int index) {
        if (ProductListActivity.FRAGMENT_TYPE_PRODUCTLIST_CATEGORY == type) {
            return getSVRAppserviceProductFilterSelectedItemById(index);
        } else if (ProductListActivity.FRAGMENT_TYPE_PRODUCTLIST_KEYWORDS == type) {
            return getSVRAppserviceProductFilterSelectedItem();
        } else {
            return null;
        }
    }

    public SVRAppserviceProductFilterSelectedItem getSVRAppserviceProductFilterSelectedItem(){
        if(filterSelectedItem == null){
            filterSelectedItem = new SVRAppserviceProductFilterSelectedItem();
        }

        return filterSelectedItem;
    }

    public SVRAppserviceProductFilterSelectedItem getSVRAppserviceProductFilterSelectedItemById(int index){
        if (index < 0 || filterSelectedItemList.size() <= index) {
            return null;
        } else {
            return filterSelectedItemList.get(index);
        }
    }

    public void setSVRAppserviceProductFilterSelectedItem(int type, int index, SVRAppserviceProductFilterSelectedItem filterSelectedItem) {
        if (ProductListActivity.FRAGMENT_TYPE_PRODUCTLIST_CATEGORY == type) {
            setSVRAppserviceProductFilterSelectedItem(index, filterSelectedItem);
        } else if (ProductListActivity.FRAGMENT_TYPE_PRODUCTLIST_KEYWORDS == type) {
            setSVRAppserviceProductFilterSelectedItem(filterSelectedItem);
        }
    }

    public void setSVRAppserviceProductFilterSelectedItem(SVRAppserviceProductFilterSelectedItem filterSelectedItem){
        this.filterSelectedItem.setCategoryOption(filterSelectedItem.getCategoryOption());
        this.filterSelectedItem.setBrandOptions(filterSelectedItem.getBrandOptions());
        this.filterSelectedItem.setFlavorOptions(filterSelectedItem.getFlavorOptions());
        this.filterSelectedItem.setLifeStageOptions(filterSelectedItem.getLifeStageOptions());
    }

    public void initSVRAppserviceProductFilterSelectedItemList(int index){

        int arraySize = filterSelectedItemList.size();
        if(index > arraySize - 1) {
            SVRAppserviceProductFilterSelectedItem item = new SVRAppserviceProductFilterSelectedItem();
            filterSelectedItemList.add(item);
        }
    }

    public void setSVRAppserviceProductFilterSelectedItem(int index, SVRAppserviceProductFilterSelectedItem filterSelectedItem){

        if (index < 0 || filterSelectedItemList.size() <= index) {
            return;
        }

        SVRAppserviceProductFilterSelectedItem item = filterSelectedItemList.get(index);
        item.setCategoryOption(filterSelectedItem.getCategoryOption());
        item.setBrandOptions(filterSelectedItem.getBrandOptions());
        item.setFlavorOptions(filterSelectedItem.getFlavorOptions());
        item.setLifeStageOptions(filterSelectedItem.getLifeStageOptions());
    }

    private void setSVRAppserviceProductSearchParameterCategoryId(int index, String categoryId) {
        if (index < 0) {
            return;
        }
        int arraySize = searchCategoryParameterArrayList.size();
        if (index >= arraySize) {
            for (int newInstallIndex = arraySize; newInstallIndex <= index; ++newInstallIndex) {
                SVRAppserviceProductSearchParameter parameter = new SVRAppserviceProductSearchParameter();
                if (newInstallIndex == index) {
                    parameter.setCategory_id(categoryId);
                    tempCategoryBean.searchCategoryParameterArrayList.add(parameter);
                    break;
                } else {
                    tempCategoryBean.searchCategoryParameterArrayList.add(parameter);
                    continue;
                }
            }
        } else {
            tempCategoryBean.searchCategoryParameterArrayList.get(index).setCategory_id(categoryId);
        }
    }

    private void setSVRAppserviceProductSearchParameterCategoryId(String categoryId) {
        if (tempCategoryBean.svrAppserviceProductSearchParameter == null) {
            tempCategoryBean.svrAppserviceProductSearchParameter = new SVRAppserviceProductSearchParameter();
        }

        tempCategoryBean.svrAppserviceProductSearchParameter.setCategory_id(categoryId);
    }

    private void setSVRAppserviceProductSearchParameterBrandId(int index, String brandId) {
        if (index < 0) {
            return;
        }
        int arraySize = tempCategoryBean.searchCategoryParameterArrayList.size();
        if (index >= arraySize) {
            for (int newInstallIndex = arraySize; newInstallIndex <= index; ++newInstallIndex) {
                SVRAppserviceProductSearchParameter parameter = new SVRAppserviceProductSearchParameter();
                if (newInstallIndex == index) {
                    parameter.setBrandId(brandId);
                    tempCategoryBean.searchCategoryParameterArrayList.add(parameter);
                    break;
                } else {
                    tempCategoryBean.searchCategoryParameterArrayList.add(parameter);
                    continue;
                }
            }
        } else {
            tempCategoryBean.searchCategoryParameterArrayList.get(index).setBrandId(brandId);
        }
    }

    private void setSVRAppserviceProductSearchParameterBrandId(String brandId) {
        if (tempCategoryBean.svrAppserviceProductSearchParameter == null) {
            tempCategoryBean.svrAppserviceProductSearchParameter = new SVRAppserviceProductSearchParameter();
        }
        tempCategoryBean.svrAppserviceProductSearchParameter.setBrandId(brandId);
    }

    public void setSVRAppserviceProductSearchParameterMinPriceMaxPrice(long minPrice, long maxPrice) {
        if (svrAppserviceProductSearchParameter == null) {
            svrAppserviceProductSearchParameter = new SVRAppserviceProductSearchParameter();
        }

        svrAppserviceProductSearchParameter.setPrice(minPrice + "-" + maxPrice);
    }

    private void setSVRAppserviceProductSearchParameterBrand(String brandValue) {
        if (svrAppserviceProductSearchParameter == null) {
            svrAppserviceProductSearchParameter = new SVRAppserviceProductSearchParameter();
        }

        svrAppserviceProductSearchParameter.setBrand(brandValue);
    }

    private void setSVRAppserviceProductSearchParameterType(String typeValue) {
        if (svrAppserviceProductSearchParameter == null) {
            svrAppserviceProductSearchParameter = new SVRAppserviceProductSearchParameter();
        }

        svrAppserviceProductSearchParameter.setModel_type(typeValue);
    }

    private void setSVRAppserviceProductSearchParameterSort(String sortValue) {
        if (svrAppserviceProductSearchParameter == null) {
            svrAppserviceProductSearchParameter = new SVRAppserviceProductSearchParameter();
        }

        String orderValue = null;
        String dirValue = null;

        if (!JDataUtils.isEmpty(sortValue)) {
            String[] sortValueArray = sortValue.split("__");
            if (sortValueArray != null && sortValueArray.length >= 2) {
                orderValue = sortValueArray[0];
                dirValue = sortValueArray[1];
            }
        }
        svrAppserviceProductSearchParameter.setOrder(orderValue);
        svrAppserviceProductSearchParameter.setDir(dirValue);
    }

    public void setSVRAppserviceProductSearchParameterCategoryId(int type, int index, String categoryId) {
        if (ProductListActivity.FRAGMENT_TYPE_PRODUCTLIST_CATEGORY == type) {
            setSVRAppserviceProductSearchParameterCategoryId(index, categoryId);
        } else if (ProductListActivity.FRAGMENT_TYPE_PRODUCTLIST_KEYWORDS == type) {
            setSVRAppserviceProductSearchParameterCategoryId(categoryId);
        }
    }


    public void setSVRAppserviceProductSearchParameterBrandId(int type, int index, String brandId) {
        if (ProductListActivity.FRAGMENT_TYPE_PRODUCTLIST_CATEGORY == type) {
            setSVRAppserviceProductSearchParameterBrandId(index, brandId);
        } else if (ProductListActivity.FRAGMENT_TYPE_PRODUCTLIST_KEYWORDS == type) {
            setSVRAppserviceProductSearchParameterBrandId(brandId);
        }
    }



    public void setSVRAppserviceProductSearchParameterMinPriceMaxPrice(int index, long minPrice, long maxPrice) {
        if (index < 0) {
            return;
        }

        int arraySize = tempCategoryBean.searchCategoryParameterArrayList.size();
        if (index >= arraySize) {
            for (int newInstallIndex = arraySize; newInstallIndex <= index; ++newInstallIndex) {
                SVRAppserviceProductSearchParameter parameter = new SVRAppserviceProductSearchParameter();
                if (newInstallIndex == index) {
                    parameter.setPrice(minPrice + "-" + maxPrice);
                    tempCategoryBean.searchCategoryParameterArrayList.add(parameter);
                    break;
                } else {
                    tempCategoryBean.searchCategoryParameterArrayList.add(parameter);
                    continue;
                }
            }
        } else {
            tempCategoryBean.searchCategoryParameterArrayList.get(index).setPrice(minPrice + "-" + maxPrice);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public void setSVRAppserviceProductSearchParameterBrand(int type, int index, String brandValue) {
        if (ProductListActivity.FRAGMENT_TYPE_PRODUCTLIST_CATEGORY == type) {
            setSVRAppserviceProductSearchParameterBrand(index, brandValue);
        } else if (ProductListActivity.FRAGMENT_TYPE_PRODUCTLIST_KEYWORDS == type) {
            setSVRAppserviceProductSearchParameterBrand(brandValue);
        }
    }

    private void setSVRAppserviceProductSearchParameterBrand(int index, String brandValue) {
        if (index < 0) {
            return;
        }
        int arraySize = tempCategoryBean.searchCategoryParameterArrayList.size();

        if (index >= arraySize) {
            for (int newInstallIndex = arraySize; newInstallIndex <= index; ++newInstallIndex) {
                SVRAppserviceProductSearchParameter parameter = new SVRAppserviceProductSearchParameter();
                if (newInstallIndex == index) {
                    parameter.setBrand(brandValue);
                    tempCategoryBean.searchCategoryParameterArrayList.add(parameter);
                    break;
                } else {
                    tempCategoryBean.searchCategoryParameterArrayList.add(parameter);
                    continue;
                }
            }
        } else {
            tempCategoryBean.searchCategoryParameterArrayList.get(index).setBrand(brandValue);
        }
    }

    private void setSVRAppserviceProductSearchParameterType(int index, String typeValue) {
        if (index < 0) {
            return;
        }

        int arraySize = tempCategoryBean.searchCategoryParameterArrayList.size();
        if (index >= arraySize) {
            for (int newInstallIndex = arraySize; newInstallIndex <= index; ++newInstallIndex) {
                SVRAppserviceProductSearchParameter parameter = new SVRAppserviceProductSearchParameter();
                if (newInstallIndex == index) {
                    parameter.setModel_type(typeValue);
                    tempCategoryBean.searchCategoryParameterArrayList.add(parameter);
                    break;
                } else {
                    tempCategoryBean.searchCategoryParameterArrayList.add(parameter);
                    continue;
                }
            }
        } else {
            tempCategoryBean.searchCategoryParameterArrayList.get(index).setModel_type(typeValue);
        }
    }

    public void setSVRAppserviceProductSearchParameterType(int type, int index, String typeValue) {
        if (ProductListActivity.FRAGMENT_TYPE_PRODUCTLIST_CATEGORY == type) {
            setSVRAppserviceProductSearchParameterType(index, typeValue);
        } else if (ProductListActivity.FRAGMENT_TYPE_PRODUCTLIST_KEYWORDS == type) {
            setSVRAppserviceProductSearchParameterType(typeValue);
        }
    }

    private void setSVRAppserviceProductSearchParameterSort(int index, String sortValue) {
        if (index < 0) {
            return;
        }

        int arraySize = tempCategoryBean.searchCategoryParameterArrayList.size();
        if (index >= arraySize) {
            for (int newInstallIndex = arraySize; newInstallIndex <= index; ++newInstallIndex) {
                SVRAppserviceProductSearchParameter parameter = new SVRAppserviceProductSearchParameter();
                if (newInstallIndex == index) {
                    String orderValue = null;
                    String dirValue = null;

                    if (!JDataUtils.isEmpty(sortValue)) {
                        String[] sortValueArray = sortValue.split("__");
                        if (sortValueArray != null && sortValueArray.length >= 2) {
                            orderValue = sortValueArray[0];
                            dirValue = sortValueArray[1];
                        }
                    }

                    parameter.setOrder(orderValue);
                    parameter.setDir(dirValue);

                    tempCategoryBean.searchCategoryParameterArrayList.add(parameter);
                    break;
                } else {
                    tempCategoryBean.searchCategoryParameterArrayList.add(parameter);
                    continue;
                }
            }
        } else {
            String orderValue = null;
            String dirValue = null;

            if (!JDataUtils.isEmpty(sortValue)) {
                String[] sortValueArray = sortValue.split("__");
                if (sortValueArray != null && sortValueArray.length >= 2) {
                    orderValue = sortValueArray[0];
                    dirValue = sortValueArray[1];
                }
            }
            tempCategoryBean.searchCategoryParameterArrayList.get(index).setOrder(orderValue);
            tempCategoryBean.searchCategoryParameterArrayList.get(index).setDir(dirValue);
        }
    }

    public void setSVRAppserviceProductSearchParameterSort(int type, int index, String sortValue) {
        if (ProductListActivity.FRAGMENT_TYPE_PRODUCTLIST_CATEGORY == type) {
            setSVRAppserviceProductSearchParameterSort(index, sortValue);
        } else if (ProductListActivity.FRAGMENT_TYPE_PRODUCTLIST_KEYWORDS == type) {
            setSVRAppserviceProductSearchParameterSort(sortValue);
        }
    }

    public void setSVRAppserviceProductSearchParameterBrandName(int index, String brandValue) {
        /*if (index < 0) {
            return;
        }
        int arraySize = tempCategoryBean.searchCategoryParameterArrayList.size();

        if (index >= arraySize) {
            for (int newInstallIndex = arraySize; newInstallIndex <= index; ++newInstallIndex) {
                SVRAppserviceProductSearchParameter parameter = new SVRAppserviceProductSearchParameter();
                if (newInstallIndex == index) {
                    tempCategoryBean.searchCategoryParameterArrayList.add(parameter);
                    break;
                } else {
                    tempCategoryBean.searchCategoryParameterArrayList.add(parameter);
                    continue;
                }
            }
        } else {
            tempCategoryBean.searchCategoryParameterArrayList.get(index).setVesBrand(brandValue);
        }*/
    }

}
