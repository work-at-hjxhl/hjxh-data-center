package com.mulaobao.administration.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.google.common.math.DoubleMath;
import com.mulaobao.administration.dao.LsSaleDao;
import com.mulaobao.administration.entity.*;
import com.mulaobao.administration.listener.TmPxbListener;
import com.mulaobao.administration.service.*;
import com.mulaobao.administration.util.Csvs;
import com.mulaobao.administration.util.DDmcSheng;
import com.mulaobao.administration.util.Delecwj;
import com.mulaobao.administration.util.UtilId;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.rmi.CORBA.Util;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@RestController
public class Csv extends ApiController {

    @Autowired
    private TmZtcService ztcService;

    @Autowired
    private LsSaleService lsSaleService;

    @Resource
    private TmPxbService tmPxbService;

    @Resource
    private LsSaleDao lsSaleDao;

    @Autowired
    private PddCjtgService pddCjtgService;

    @Autowired
    private PddSstgService sstgService;

    @Autowired
    private LsShopService lsShopService;

    @Autowired
    private TmCjzzService tmCjzzService;

    @Autowired
    private TmCjtgService tmCjtgService;

    @Autowired
    private TmAitgService tmAitgService;
    @Autowired
    private TmTktgService tmTktgService;
    @Autowired
    private TmXssjService tmXssjService;
    @Autowired
    private TmZtcService tmZtcService;

    @Autowired
    private JdXssjService jdXssjService;

    @Autowired
    private JdJdkcService jdJdkcService;

    @Autowired
    private JdCjtgService jdCjtgService;

    @Autowired
    private JdGwcdService jdGwcdService;

    @Autowired
    private MgjXssjService mgjXssjService;

    @Autowired
    private DdmcXssjService ddmcXssjService;

    @Autowired
    private DyXssjService dyXssjService;


    @Autowired
    private TtZtcService ttZtcService;

    @Autowired
    private PddFxtService fxtService;

    @Autowired
    private MtXssjService mtXssjService;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    /**
     * ????????????
     * @return
     */
    @GetMapping("ks")
   public String show(){
        Map<String, Object> stringObjectHashMap = new HashMap<>();
        String [] fileName = Delecwj.getFileName("D:\\????????????9");
        List<String> strings = Arrays.asList(fileName);
        List<String> strings1 = new ArrayList<>();
        List<String> strings3 = new ArrayList<>(); //???????????????
        List<String> strings4 = new ArrayList<>(); //?????????
        for (int i = 0;i<strings.size();i++){
            strings1.add("D:\\????????????9\\"+strings.get(i));
        }
        for (int i = 0;i<strings1.size();i++){
            String [] fileName1 = Delecwj.getFileName(strings1.get(i));
            List<String> strings41= Arrays.asList(fileName1);
            for (int ii = 0;ii<strings41.size();ii++){
                strings3.add(strings1.get(i)+"\\"+strings41.get(ii));
            }
        }

        for (int i = 0;i<strings3.size();i++){
            String [] fileName1 = Delecwj.getFileName(strings3.get(i));
            List<String> strings5= Arrays.asList(fileName1);
            for (int ii = 0;ii<strings5.size();ii++){
                strings4.add(strings3.get(i)+"\\"+strings5.get(ii));
            }
        }

        for (int i = 0;i<strings4.size();i++){
            String pt = strings4.get(i).split("\\\\")[2]; //?????????????????????
            String ms = strings4.get(i).split("\\\\")[3]; //???????????????
            String ms1 = strings4.get(i).split("\\\\")[4]; //????????????

            String data1 = ms.split("_")[0];  //??????
            String file = ms.split("_")[1];  //????????????
            int ais = 0;
            if(file.equals("????????????")){
                file = file+"(??????)";
            }
            if(file.equals("????????????")){
                file = file+"(??????)";
            }
            LsShop shop_name = lsShopService.getOne(new QueryWrapper<LsShop>().eq("Shop_name", file));
            System.out.println(shop_name);
            Date date2 = new Date();
            try {
                date2 = simpleDateFormat.parse(data1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (shop_name!=null){

                if (shop_name.getPlatformBh().equals("101")){  //?????????

                    if (ms1.substring(0,2).equals("??????")){
                        List<PddCjtg> date_time = pddCjtgService.list(new QueryWrapper<PddCjtg>().eq("Date_time", date2).eq("Shop_bh",shop_name.getShopBh()));
                        if (date_time.size()==0){
                            pddCjtgService.getInformation1(strings4.get(i),ms,shop_name,date2);
                        }
                    }
                    if (ms1.substring(0,2).equals("??????")){
                        List<PddSstg> date_time = sstgService.list(new QueryWrapper<PddSstg>().eq("Date_time", date2).eq("Shop_bh",shop_name.getShopBh()));
                        if (date_time.size()==0){
                            sstgService.getInformation1(strings4.get(i),ms,shop_name,date2);
                        }

                    }
                    if (ms1.substring(0,2).equals("??????")){
                        List<PddFxt> date_time = fxtService.list(new QueryWrapper<PddFxt>().eq("Date_time", date2).eq("Shop_bh",shop_name.getShopBh()));
                        if (date_time.size()==0) {
                            fxtService.getInformation1(strings4.get(i), ms, shop_name, date2);
                        }
                    }
                        if (ms1.substring(0,2).equals("QQ")){
                            Map<String, String> stringStringMap = DDmcSheng.readTxtFile(strings4.get(i) + "\\result.txt");
                            LsSale lsSale = lsSaleService.getOne(new QueryWrapper<LsSale>().eq("Shop_bh", shop_name.getShopBh()).eq("Date_time", date2));
                            LsSale lsSale1 = new LsSale();
                            if (lsSale==null){
                               lsSale1.setId(UtilId.getUUID());
                               lsSale1.setDateTime(date2);
                                lsSale1.setXgdatetime(new Date());
                               lsSale1.setPlatformBh(shop_name.getPlatformBh());
                               lsSale1.setShopBh(shop_name.getShopBh());
                               if (stringStringMap.size()==1){
                                   lsSale1.setRefundamount(UtilId.getMl(UtilId.getnlll(stringStringMap.get("????????????").substring(0,stringStringMap.get("????????????").length()-1))));
                                        lsSaleDao.insert(lsSale1);
                               }
                               if (stringStringMap.size()!=1){
                                   lsSale1.setTotaltraffic(UtilId.getMl(UtilId.getnlll(stringStringMap.get("???????????????"))));
                                   lsSale1.setNumBuyers(UtilId.getMl(UtilId.getnlll(stringStringMap.get("???????????????"))));
                                   lsSale1.setOrderquantity(UtilId.getMl(UtilId.getnlll(stringStringMap.get("???????????????"))));
                                   String x = stringStringMap.get("????????????");
                                   if (x!=null){
                                       lsSale1.setSales(UtilId.getMl(UtilId.getnlll(stringStringMap.get("????????????").substring(0,stringStringMap.get("????????????").length()-1))));
                                   }else {
                                       lsSale1.setSales(UtilId.getMl(UtilId.getnlll(stringStringMap.get("????????????").substring(0,stringStringMap.get("????????????").length()-1))));
                                   }                                   lsSaleDao.insert(lsSale1);

                               }
                           }else {
                               if (stringStringMap.size()==1){
                                   lsSale.setRefundamount(UtilId.getMl(UtilId.getnlll(stringStringMap.get("????????????").substring(0,stringStringMap.get("????????????").length()-1))));
                                    lsSaleService.updateById(lsSale);
                               }
                               if (stringStringMap.size()!=1){
                                   lsSale.setTotaltraffic(UtilId.getMl(UtilId.getnlll(stringStringMap.get("???????????????"))));
                                   lsSale.setNumBuyers(UtilId.getMl(UtilId.getnlll(stringStringMap.get("???????????????"))));
                                  lsSale.setOrderquantity(UtilId.getMl(UtilId.getnlll(stringStringMap.get("???????????????"))));
                                   String x = stringStringMap.get("????????????");
                                if (x!=null){
                                    lsSale.setSales(UtilId.getMl(UtilId.getnlll(stringStringMap.get("????????????").substring(0,stringStringMap.get("????????????").length()-1))));
                                }else {
                                    lsSale.setSales(UtilId.getMl(UtilId.getnlll(stringStringMap.get("????????????").substring(0,stringStringMap.get("????????????").length()-1))));
                                }


                                  lsSaleService.updateById(lsSale);
                               }
                           }
                        }
                }
                ////////////////////////////////////
                if (shop_name.getPlatformBh().equals("102")){  //??????
                    if (ms1.substring(0,4).equals("????????????")){ //????????????
                        List<TmCjzz> list = tmCjzzService.list(new QueryWrapper<TmCjzz>().eq("Date_time", date2).eq("Shop_bh", shop_name.getShopBh()));
                        if (list.size()==0) {
                            tmCjzzService.getInformation1(strings4.get(i), ms, shop_name, date2);
                        }
                    }
                    if (ms1.substring(0,4).equals("????????????")){//????????????
                        List<TmCjtg> list = tmCjtgService.list(new QueryWrapper<TmCjtg>().eq("Date_time", date2).eq("Shop_bh", shop_name.getShopBh()));
                        if (list.size()==0) {
                            tmCjtgService.getInformation1(strings4.get(i), ms, shop_name, date2);
                        }
                    }
                    if (ms1.substring(0,4).equals("2021")){ //?????????
                        List<TmZtc> list = tmZtcService.list(new QueryWrapper<TmZtc>().eq("Date_time", date2).eq("Shop_bh", shop_name.getShopBh()));
                        if (list.size()==0) {
                            tmZtcService.getInformation1(strings4.get(i), ms, shop_name, date2);
                        }

                    }
                    if (ms1.substring(0,4).equals("????????????")){//????????????
                        List<TmXssj> list = tmXssjService.list(new QueryWrapper<TmXssj>().eq("Date_time", date2).eq("Shop_bh", shop_name.getShopBh()));
                        if (list.size()==0) {
                            tmXssjService.getInformation1(strings4.get(i), ms, shop_name,date2);
                        }
                    }
                    if (ms1.substring(0,4).equals("??????_???")){ //AI??????
                        List<TmAitg> list = tmAitgService.list(new QueryWrapper<TmAitg>().eq("Date_time", date2).eq("Shop_bh", shop_name.getShopBh()));
                        if (list.size()==0) {
                            tmAitgService.getInformation1(strings4.get(i), ms, shop_name, date2);
                        }
                    }
                    if (ms1.substring(0,4).equals("????????????")){ //?????????
                        List<TmPxb> list = tmPxbService.list(new QueryWrapper<TmPxb>().eq("Date_time", date2).eq("Shop_bh", shop_name.getShopBh()));
                        if (list.size()==0) {
                            tmPxbService.getInformation1(strings4.get(i), ms, shop_name, date2);
                        }
                    }
                    if (ms1.substring(0,4).equals("arre")){ //????????????
                        List<TmTktg> list = tmTktgService.list(new QueryWrapper<TmTktg>().eq("Date_time", date2).eq("Shop_bh", shop_name.getShopBh()));
                        if (list.size()==0) {
                            tmTktgService.getInformation1(strings4.get(i), ms, shop_name, date2);
                        }
                    }
                    if (ms1.substring(0,4).equals("????????????")){ //?????????
                        List<TtZtc> list = ttZtcService.list(new QueryWrapper<TtZtc>().eq("Date_time", date2).eq("Shop_bh", shop_name.getShopBh()));
                        if (list.size()==0) {
                            ttZtcService.getInformation1(strings4.get(i), ms, shop_name, date2);
                        }
                    }
                    if (ms1.substring(0,2).equals("QQ")){//
                        Map<String, String> stringStringMap = DDmcSheng.readTxtFile(strings4.get(i) + "\\result.txt");
                        LsSale lsSale = lsSaleService.getOne(new QueryWrapper<LsSale>().eq("Shop_bh", shop_name.getShopBh()).eq("Date_time", date2));
                        LsSale lsSale1 = new LsSale();
                        if (lsSale==null){
                            lsSale1.setId(UtilId.getUUID());
                            lsSale1.setDateTime(date2);
                            lsSale1.setXgdatetime(new Date());
                            lsSale1.setPlatformBh(shop_name.getPlatformBh());
                            lsSale1.setShopBh(shop_name.getShopBh());
                            if (stringStringMap.size()==2){
                                lsSale1.setOrderquantity(UtilId.getMl(UtilId.getnlll(stringStringMap.get("??????????????????"))));
                                lsSale1.setRefundamount(UtilId.getMl(UtilId.getnlll(stringStringMap.get("??????????????????"))));
                            }
                            if (stringStringMap.size()==5){
                                lsSale1.setSales(UtilId.getMl(UtilId.getnlll(stringStringMap.get("????????????"))));
                                lsSale1.setTotaltraffic(UtilId.getMl(UtilId.getnlll(stringStringMap.get("?????????"))));
                                lsSale1.setNumBuyers(UtilId.getMl(UtilId.getnlll(stringStringMap.get("???????????????"))));
                            }
                            lsSaleDao.insert(lsSale1);
                        }else {
                            if (stringStringMap.size()==2){
                                lsSale.setOrderquantity(UtilId.getMl(UtilId.getnlll(stringStringMap.get("??????????????????"))));
                                lsSale.setRefundamount(UtilId.getMl(UtilId.getnlll(stringStringMap.get("??????????????????"))));
                            }
                            if (stringStringMap.size()==5){
                                lsSale.setSales(UtilId.getMl(UtilId.getnlll(stringStringMap.get("????????????"))));
                                lsSale.setTotaltraffic(UtilId.getMl(UtilId.getnlll(stringStringMap.get("?????????"))));
                                lsSale.setNumBuyers(UtilId.getMl(UtilId.getnlll(stringStringMap.get("???????????????"))));
                            }
                            lsSaleDao.updateById(lsSale);
                        }

                    }
                }
                ////////////////////////////////////
                if (shop_name.getPlatformBh().equals("103")){  //??????
                    if (ms1.substring(0,4).equals("????????????")){ //????????????
                        List<JdJdkc> list = jdJdkcService.list(new QueryWrapper<JdJdkc>().eq("Date_time", date2).eq("Shop_bh", shop_name.getShopBh()));
                      if (list.size()==0){
                          jdJdkcService.getInformation1(strings4.get(i),ms,shop_name,date2);
                      }
                    }

                    if (ms1.split("_")[1].equals("?????????")){//?????????
                        List<JdCjtg> list = jdCjtgService.list(new QueryWrapper<JdCjtg>().eq("Date_time", date2).eq("Shop_bh", shop_name.getShopBh()));
                        if (list.size()==0){
                            jdCjtgService.getInformation1(strings4.get(i),ms,shop_name,date2);
                        }
                    }
                    if (ms1.split("_")[1].equals("?????????")){
                    }else {
                        String substring = ms1.split("_")[1].substring(0, 3);
                        if (substring.equals("?????????")){ //????????????
                            List<JdGwcd> list = jdGwcdService.list(new QueryWrapper<JdGwcd>().eq("Date_time", date2).eq("Shop_bh", shop_name.getShopBh()));
                            if (list.size()==0){
                                jdGwcdService.getInformation1(strings4.get(i),ms,shop_name,date2);
                            }
                        }
                    }
                    if (ms1.substring(0,4).equals("????????????")){//????????????
                        List<JdXssj> list = jdXssjService.list(new QueryWrapper<JdXssj>().eq("Date_time", date2).eq("Shop_bh", shop_name.getShopBh()));
                        if (list.size()==0){
                            jdXssjService.getInformation1(strings4.get(i),ms,shop_name,date2);
                        }
                    }
                    if (ms1.substring(0,2).equals("QQ")){//
                        Map<String, String> stringStringMap = DDmcSheng.readTxtFile(strings4.get(i) + "\\result.txt");
                        LsSale lsSale = lsSaleService.getOne(new QueryWrapper<LsSale>().eq("Shop_bh", shop_name.getShopBh()).eq("Date_time", date2));
                       if (lsSale==null){
                           LsSale lsSale1 = new LsSale();
                           lsSale1.setId(UtilId.getUUID());
                           lsSale1.setDateTime(date2);
                           lsSale1.setXgdatetime(new Date());
                           lsSale1.setPlatformBh(shop_name.getPlatformBh());
                           lsSale1.setShopBh(shop_name.getShopBh());
                           lsSale1.setRefundamount(UtilId.getMl(UtilId.getnlll(stringStringMap.get("????????????").substring(1,stringStringMap.get("????????????").length()-1))));
                           lsSaleDao.insert(lsSale1);
                       }

                    }
                }
                ////////////////////////////////////

                if (shop_name.getPlatformBh().equals("105")){
                    if (ms1.substring(0,4).equals("????????????")){//?????????
                        List<MgjXssj> list = mgjXssjService.list(new QueryWrapper<MgjXssj>().eq("Date_time", date2).eq("Shop_bh", shop_name.getShopBh()));
                        if (list.size()==0){
                            mgjXssjService.getInformation1(strings4.get(i),ms,shop_name,date2);
                        }
                    }
                    if (ms1.substring(0,4).equals("????????????")){//??????
                        List<DyXssj> list = dyXssjService.list(new QueryWrapper<DyXssj>().eq("Date_time", date2).eq("Shop_bh", shop_name.getShopBh()));
                        if (list.size()==0){
                            dyXssjService.getInformation1(strings4.get(i),ms,shop_name,date2);
                        }

                    }
                    if (file.substring(file.length()-3,file.length()-1).equals("??????")){//??????
                        if (ms1.substring(0,2).equals("QQ")) {//
                            Map<String, String> stringStringMap = DDmcSheng.readTxtFile(strings4.get(i) + "\\result.txt");
                            LsSale lsSale = lsSaleService.getOne(new QueryWrapper<LsSale>().eq("Shop_bh", shop_name.getShopBh()).eq("Date_time", date2));
                            LsSale lsSale1 = new LsSale();
                            lsSale1.setId(UtilId.getUUID());
                            lsSale1.setDateTime(date2);
                            lsSale1.setXgdatetime(new Date());
                            lsSale1.setPlatformBh(shop_name.getPlatformBh());
                            lsSale1.setShopBh(shop_name.getShopBh());
                            if (lsSale == null) {


                                if (stringStringMap != null) {
                                    lsSale1.setSales(UtilId.getMl(stringStringMap.get("????????????")));
                                    lsSale1.setOrderquantity(UtilId.getMl(stringStringMap.get("??????????????????")));
                                    lsSale1.setPaymentrate(Double.parseDouble(stringStringMap.get("???????????????").substring(0, stringStringMap.get("???????????????").length() - 1)) / 100);
                                    lsSale1.setNumBuyers(UtilId.getMl(stringStringMap.get("???????????????")));
                                    lsSale1.setTotaltraffic(lsSale1.getNumBuyers() / lsSale1.getPaymentrate());
                                    lsSale1.setFreetraffic(lsSale1.getTotaltraffic());
                                    lsSale1.setFreetrafficzb(lsSale1.getFreetraffic() / lsSale1.getTotaltraffic());
                                    lsSale1.setPlatformbuckle(lsSale1.getSales() * 0.115);
                                    lsSale1.setTotalcost(lsSale1.getPlatformbuckle());
                                    lsSale1.setCostratio(UtilId.sting2double(lsSale1.getTotalcost() / lsSale1.getSales()));
                                    lsSaleDao.insert(lsSale1);
                                }

                            }
                        }
                    }else {
                        if (ms1.substring(0,2).equals("QQ")){//
                            Map<String, String> stringStringMap = DDmcSheng.readTxtFile(strings4.get(i) + "\\result.txt");
                            // LsSale lsSale = lsSaleService.getOne(new QueryWrapper<LsSale>().eq("Shop_bh", shop_name.getShopBh()).eq("Date_time", date2));
                            LsSale lsSale1 = new LsSale();
                            lsSale1.setId(UtilId.getUUID());
                            lsSale1.setDateTime(date2);
                            lsSale1.setXgdatetime(new Date());
                            lsSale1.setPlatformBh(shop_name.getPlatformBh());
                            lsSale1.setShopBh(shop_name.getShopBh());
                            String as = stringStringMap.get("???????????????").substring(0,stringStringMap.get("???????????????").length()-1);
                            lsSale1.setPaymentrate(Double.parseDouble(as)/100);
                            lsSaleDao.insert(lsSale1);
                        }
                    }


                }
                ////////////////////////////////////

                if (shop_name.getPlatformBh().equals("104")){  //??????
                        if (file.substring(0,4).equals("????????????")){//????????????
                                ddmcXssjService.getInformation1(strings4.get(i), ms, shop_name, date2);

                    }
                    if (file.substring(file.length()-3,file.length()-1).equals("??????")){//????????????
                        if (ms1.substring(0,2).equals("QQ")){//
                            Map<String, String> stringStringMap = DDmcSheng.readTxtFile(strings4.get(i) + "\\result.txt");
                            LsSale lsSale = lsSaleService.getOne(new QueryWrapper<LsSale>().eq("Shop_bh", shop_name.getShopBh()).eq("Date_time", date2));

                            LsSale lsSale1 = new LsSale();

                            lsSale1.setId(UtilId.getUUID());
                            lsSale1.setDateTime(date2);
                            lsSale1.setXgdatetime(new Date());
                            lsSale1.setPlatformBh(shop_name.getPlatformBh());
                            lsSale1.setShopBh(shop_name.getShopBh());
                            if (lsSale==null){
                            if (stringStringMap!=null && stringStringMap.size()==2){
                                if (stringStringMap.get("???????????????") !=null){
                                        lsSale1.setTotaltraffic(UtilId.getMl(stringStringMap.get("???????????????")));
                                }
                                if (stringStringMap.get("????????????") !=null){
                                    lsSale1.setRefundamount(UtilId.getMl(stringStringMap.get("????????????")));
                                    lsSaleDao.insert(lsSale1);
                                }

                            }
                            if (stringStringMap!=null && stringStringMap.size()!=2){
                                if (stringStringMap.get("????????????")!=null){
                                        lsSale1.setSales(UtilId.getMl(stringStringMap.get("????????????").substring(0,stringStringMap.get("????????????").length()-1)));
                                }
                                if (stringStringMap.get("????????????")!=null){
                                            lsSale1.setNumBuyers(UtilId.getMl(stringStringMap.get("????????????").substring(0,stringStringMap.get("????????????").length()-1)));
                                }
                                if (stringStringMap.get("???????????????")!=null){
                                    lsSale1.setOrderquantity(UtilId.getMl(stringStringMap.get("???????????????").substring(0,stringStringMap.get("???????????????").length()-1)));
                                }

                            }

                            }else {
                                if (stringStringMap!=null && stringStringMap.size()==2){
                                    if (stringStringMap.get("???????????????") !=null){
                                        lsSale.setTotaltraffic(UtilId.getMl(stringStringMap.get("???????????????")));
                                    }
                                    if (stringStringMap.get("????????????") !=null){
                                        if (stringStringMap.get("????????????").indexOf("???")==-1){
                                            lsSale.setRefundamount(UtilId.getMl(stringStringMap.get("????????????")));
                                        }else {
                                            lsSale.setRefundamount(UtilId.getMl(stringStringMap.get("????????????").substring(0,stringStringMap.get("????????????").length()-1)));
                                        }

                                    }
                                }
                                if (stringStringMap!=null && stringStringMap.size()!=2){
                                    if (stringStringMap.get("????????????")!=null){
                                        lsSale.setSales(UtilId.getMl(stringStringMap.get("????????????").substring(0,stringStringMap.get("????????????").length()-1)));
                                    }
                                    if (stringStringMap.get("????????????")!=null){
                                        lsSale.setNumBuyers(UtilId.getMl(stringStringMap.get("????????????").substring(0,stringStringMap.get("????????????").length()-1)));
                                    }
                                    if (stringStringMap.get("???????????????")!=null){
                                        lsSale.setOrderquantity(UtilId.getMl(stringStringMap.get("???????????????").substring(0,stringStringMap.get("???????????????").length()-1)));
                                    }
                                }

                                lsSale.setActualsales(lsSale.getSales()-lsSale.getRefundamount());//???????????????
                                lsSale.setRefundamount(UtilId.sting2double(lsSale.getRefundamount()/lsSale.getSales()));//?????????
                                lsSale.setCustomerprice(lsSale.getSales()/lsSale.getNumBuyers());//?????????
                                if (lsSale.getTotaltraffic()!=null){
                                    lsSale.setPaymentrate(UtilId.sting2double(lsSale.getNumBuyers()/lsSale.getTotaltraffic()));//???????????????
                                    lsSale.setFreetrafficzb(UtilId.sting2double(lsSale.getFreetraffic()/lsSale.getTotaltraffic()));//??????????????????
                                }

                                lsSale.setFreetraffic(lsSale.getTotaltraffic());//????????????


                                lsSale.setPlatformbuckle(lsSale.getActualsales()*0.006); //????????????
                                lsSale.setTotalcost(lsSale.getPlatformbuckle());//???????????????
                                if (lsSale.getSales()!=null){
                                    lsSale.setCostratio(UtilId.sting2double(lsSale.getTotalcost()/lsSale.getSales()));//?????????
                                }

                                lsSaleDao.insert(lsSale);
                            }
                        }
                    }
                    if (file.substring(0,4).equals("????????????")){//????????????
                        List<MtXssj> list = mtXssjService.list(new QueryWrapper<MtXssj>().eq("Xs_datetime", date2).eq("Shop_bh", shop_name.getShopBh()));
                      if (list.size()==0){
                          mtXssjService.getInformation1(strings4.get(i),ms,shop_name,date2);
                      }
                    }
                }
            }
        }        return "200";

    }


    /**
     * ????????????
     */
        @GetMapping("dshz")
    public void dshzShow(){
            List<LsShop> shop_name = lsShopService.list();
            for (int iis = 0;iis<shop_name.size();iis++){
               /* if (shop_name.get(iis).getPlatformBh().equals("101")){ //?????????

                    if (shop_name.get(iis).getShopBh().equals("101016")){
                        List<LsSale> lsSales = lsSaleService.pddlists1(shop_name.get(iis).getShopBh());
                        for (int ai = 0;ai<lsSales.size();ai++){
                            LsSale one = lsSaleService.getOne(new QueryWrapper<LsSale>().eq("Date_time",lsSales.get(ai).getDateTime()).eq("Shop_bh",lsSales.get(ai).getShopBh()));
                            if (one!=null){
                                LsSale lsSale = lsSales.get(ai);
                                one.setManyscenesfy(lsSale.getManyscenesfy());
                                one.setManyscenesll(lsSale.getManyscenesll());
                                lsSaleDao.updateById(one);
                            }
                        }
                    }

                    List<LsSale> pddlists = lsSaleService.pddlists(shop_name.get(iis).getShopBh());

                    for (int i = 0;i<pddlists.size();i++){
                        List<LsSale> on3 = lsSaleService.list(new QueryWrapper<LsSale>().eq("Date_time", pddlists.get(i).getDateTime()).eq("Shop_bh", pddlists.get(i).getShopBh()));
                        LsSale one = pddlists.get(i);
                        LsSale one1 = null;
                        if (on3.size()==0){
                        }else {
                            one1 = on3.get(0);
                        }
                        if (on3.size()==0){
                            one.setId(UtilId.getUUID());
                            one.setSales(0.0); //???????????????
                            one.setOrderquantity(0.0); //?????????
                            one.setNumBuyers(0.0);//???????????????
                            one.setRefundamount(0.0);//????????????
                            one.setRefundrate(0.0);//?????????
                            one.setActualsales(0.0); //???????????????
                             one.setPaymentrate(0.0);//???????????????
                            one.setCustomerprice(0.0);//?????????
                             one.setTotaltraffic(0.0);//???????????????
                           one.setFreetraffic(0.0); //????????????
                           one.setFreetrafficzb(0.0); //??????????????????
                            one.setRestassuredpushll(0.0);//?????????/????????????/????????????????????????
                            one.setTaokepromotionll(0.0);//????????????(??????)
                            one.setAipromotionll(0.0);//AI??????(??????)
                            one.setPinxiaobaoll(0.0);//?????????(??????)
                            one.setPromotionflow(UtilId.getnlllduoble(one.getMoresearchll())+UtilId.getnlllduoble(one.getManyscenesll()));//???????????????
                            one.setPromotionflowzb(0.0);//??????????????????
                            one.setTaokepromotionfy(0.0);//????????????(??????)
                            one.setAipromotionfy(0.0);//AI??????(??????)
                            one.setPinxiaobaofy(0.0);//?????????(??????)

                                one.setPromotioncost(UtilId.getnlllduoble(one.getMoresearchfy())+UtilId.getnlllduoble(one.getManyscenesfy())+UtilId.getnlllduoble(one.getRestassuredpushfy()));//???????????????
                            one.setPromotioncostzb(0.0);//??????????????????
                            one.setPlatformbuckle(0.0);//????????????
                            one.setOtherexpenses(0.0);//????????????
                            one.setTotalcost(one.getPromotioncost());//???????????????
                            one.setCostratio(0.0);//?????????
                           // lsSaleDao.insert(one);
                        }else {
                                one1.setMoresearchll(one.getMoresearchll());
                                one1.setManyscenesll(one.getManyscenesll());
                                one1.setManyscenesfy(one.getManyscenesfy());
                                one1.setMoresearchfy(one.getMoresearchfy());
                                one1.setActualsales(one1.getSales()-one1.getRefundamount()); //???????????????
                                one1.setRestassuredpushfy(one.getRestassuredpushfy());

                                one1.setRefundrate(UtilId.sting2double(UtilId.getnlllduoble(one1.getRefundamount())/UtilId.getnlllduoble(one1.getSales())));//?????????

                                one1.setPaymentrate(UtilId.sting2double(one1.getNumBuyers()/one1.getTotaltraffic()));//???????????????
                                one1.setCustomerprice(one1.getSales()/one1.getNumBuyers());//?????????
                                one1.setRestassuredpushll(0.0);//?????????/????????????/????????????????????????
                                one1.setTaokepromotionll(0.0);//????????????(??????)
                                one1.setAipromotionll(0.0);//AI??????(??????)
                                one1.setPinxiaobaoll(0.0);//?????????(??????)

                                one1.setPromotionflow(UtilId.getnlllduoble(one.getMoresearchll())+UtilId.getnlllduoble(one.getManyscenesll()));//???????????????

                                one1.setPromotionflowzb(UtilId.sting2double(one1.getPromotionflow()/one1.getTotaltraffic()));//??????????????????
                                one1.setTaokepromotionfy(0.0);//????????????(??????)
                                one1.setAipromotionfy(0.0);//AI??????(??????)
                                one1.setPinxiaobaofy(0.0);//?????????(??????)

                                one1.setPromotioncost(UtilId.getnlllduoble(one.getMoresearchfy())+UtilId.getnlllduoble(one.getManyscenesfy())+UtilId.getnlllduoble(one.getRestassuredpushfy()));//???????????????

                                one1.setPromotioncostzb(UtilId.sting2double(one1.getPromotioncost()/one1.getSales()));//??????????????????
                                one1.setPlatformbuckle(one1.getActualsales()*0.006);//????????????
                                one1.setOtherexpenses(0.0);//????????????
                                one1.setTotalcost(one1.getPromotioncost()+one1.getPlatformbuckle());//???????????????

                                one1.setCostratio(UtilId.sting2double(one1.getTotalcost()/one1.getSales()));//?????????
                                one1.setFreetraffic(one1.getTotaltraffic()-one1.getPromotionflow()); //????????????
                            one1.setFreetrafficzb(UtilId.sting2double(one1.getFreetraffic()/one1.getTotaltraffic()));
                            if (one1.getTotaltraffic()!=0 || one1.getTotaltraffic()!=null){

                            }
                            lsSaleDao.updateById(one1);
                        }
                    }
                }
                if (shop_name.get(iis).getPlatformBh().equals("102")){ //??????
                    if (!shop_name.get(iis).getShopName().substring(shop_name.get(iis).getShopName().length()-4,shop_name.get(iis).getShopName().length()-1).equals("?????????")){
                        List<LsSale> tmlists = lsSaleService.tmlists(shop_name.get(iis).getShopBh());
                    for (int i =0;i<tmlists.size();i++) {
                        List<LsSale> on3 = lsSaleService.list
                                (new QueryWrapper<LsSale>().eq("Date_time", tmlists.get(i).getDateTime()).eq("Shop_bh", tmlists.get(i).getShopBh()));
                        LsSale one = tmlists.get(i);
                        if (on3.size()==0){
                            System.out.println(one);
                            one.setId(UtilId.getUUID());
                            if (one.getNumBuyers()!=null){
                                if (one.getNumBuyers()!=0){
                                    one.setCustomerprice(one.getSales()/one.getNumBuyers());
                                }
                            }

                            if (one.getSales()!=null ){
                                if (one.getSales()!=0){
                                    one.setRefundrate(UtilId.sting2double(one.getRefundamount()/one.getSales())); //?????????
                                }
                            }

                                one.setPromotionflow(UtilId.getnlllduoble(one.getMoresearchll())+UtilId.getnlllduoble(one.getManyscenesll())+UtilId.getnlllduoble(one.getRestassuredpushll())+
                                    UtilId.getnlllduoble(one.getAipromotionll())+UtilId.getnlllduoble(one.getPinxiaobaoll())+UtilId.getnlllduoble(one.getTaokepromotionll()));//???????????????
                            if (one.getTotaltraffic()!=null){
                                if (one.getTotaltraffic()!=0){
                                    one.setPromotionflowzb(UtilId.sting2double(one.getPromotionflow()/one.getTotaltraffic()));//??????????????????
                                }
                            }

                            one.setPromotioncost(UtilId.getnlllduoble(one.getMoresearchfy())+UtilId.getnlllduoble(one.getManyscenesfy())+UtilId.getnlllduoble(one.getRestassuredpushfy())+
                                    UtilId.getnlllduoble(one.getAipromotionfy())+UtilId.getnlllduoble(one.getPinxiaobaofy())+UtilId.getnlllduoble(one.getTaokepromotionfy()));//???????????????
                            if (one.getSales()!=null){
                                if (one.getSales()!=0){
                                    one.setPromotioncostzb(UtilId.sting2double(one.getPromotioncost()/one.getSales()));//??????????????????
                                }
                            }

                           one.setOtherexpenses(0.0); //????????????
                            one.setFreetraffic(one.getTotaltraffic()-one.getPromotionflow());//????????????
                            if (one.getTotaltraffic()!=null){
                                if (one.getTotaltraffic()!=0){
                                    one.setFreetrafficzb(UtilId.sting2double(one.getFreetraffic()/one.getTotaltraffic()));//??????????????????
                                }
                            }

                            one.setPlatformbuckle(one.getActualsales()*0.025);//????????????
                            one.setTotalcost(one.getPromotioncost()+one.getPlatformbuckle()+one.getOtherexpenses());//???????????????
                            if (one.getSales()!=null ){
                                if (one.getSales()!=0){
                                    one.setCostratio(UtilId.sting2double(one.getTotalcost()/one.getSales()));//?????????
                                }
                            }

                            lsSaleDao.insert(one);
                        }

                        }
                    }else{
                        List<LsSale> tjblists = lsSaleService.tjblists(shop_name.get(iis).getShopBh());
                        for (int i =0;i<tjblists.size();i++){
                            List<LsSale> on3 = lsSaleService.list(new QueryWrapper<LsSale>().eq("Date_time", tjblists.get(i).getDateTime()).eq("Shop_bh", tjblists.get(i).getShopBh()));
                            LsSale one = tjblists.get(i);
                            LsSale one1 = null;
                            if (on3.size()==0){
                            }else {
                                one1 = on3.get(0);
                            }
                            if (on3.size()==0){

                            }else {
                                one1.setMoresearchll(one.getMoresearchll());
                                one1.setManyscenesll(0.0);
                                one1.setManyscenesfy(0.0);
                                one1.setMoresearchfy(one.getMoresearchfy());
                                one1.setRestassuredpushfy(0.0);
                                one1.setRefundrate(UtilId.sting2double(one1.getRefundamount()/one1.getSales()));//?????????
                                one1.setActualsales(one1.getSales()-one1.getRefundamount()); //???????????????
                                one1.setPaymentrate(UtilId.sting2double(one1.getNumBuyers()/one1.getTotaltraffic()));//???????????????
                                one1.setCustomerprice(one1.getSales()/one1.getNumBuyers());//?????????
                                one1.setRestassuredpushll(0.0);//?????????/????????????/????????????????????????
                                one1.setTaokepromotionll(0.0);//????????????(??????)
                                one1.setAipromotionll(0.0);//AI??????(??????)
                                one1.setPinxiaobaoll(0.0);//?????????(??????)
                                one1.setPromotionflow(UtilId.getnlllduoble(one.getMoresearchll()));//???????????????
                                one1.setPromotionflowzb(UtilId.sting2double(one1.getPromotionflow()/one1.getTotaltraffic()));//??????????????????
                                one1.setTaokepromotionfy(0.0);//????????????(??????)
                                one1.setAipromotionfy(0.0);//AI??????(??????)
                                one1.setPinxiaobaofy(0.0);//?????????(??????)
                                one1.setPromotioncost(UtilId.getnlllduoble(one.getMoresearchfy())+UtilId.getnlllduoble(one.getManyscenesfy())+UtilId.getnlllduoble(one.getRestassuredpushfy()));//???????????????
                                one1.setPromotioncostzb(UtilId.sting2double(one1.getPromotioncost()/one1.getSales()));//??????????????????
                                one1.setPlatformbuckle(one1.getActualsales()*0.02);//????????????
                                one1.setOtherexpenses(0.0);//????????????
                                one1.setTotalcost(one1.getPromotioncost()+one1.getPlatformbuckle());//???????????????
                                one1.setCostratio(UtilId.sting2double(one1.getTotalcost()/one1.getSales()));//?????????
                                one1.setFreetraffic(one1.getTotaltraffic()-one1.getPromotionflow()); //????????????
                                one1.setFreetrafficzb(UtilId.sting2double(one1.getFreetraffic()/one1.getTotaltraffic())); //??????????????????
                                lsSaleDao.updateById(one1);
                            }
                        }
                    }
                    }
                if (shop_name.get(iis).getPlatformBh().equals("103")){ //??????
                    List<LsSale> jdlists = lsSaleService.jdlists(shop_name.get(iis).getShopBh());

                    for (int i =0;i<jdlists.size();i++){
                        List<LsSale> on3 = lsSaleService.list(new QueryWrapper<LsSale>().eq("Date_time", jdlists.get(i).getDateTime()).eq("Shop_bh", jdlists.get(i).getShopBh()));
                        LsSale one = jdlists.get(i);
                        LsSale one1 = null;
                        if (on3.size()==0){
                        }else {
                            one1 = on3.get(0);
                        }
                            if (on3.size()==0){
                                one.setId(UtilId.getUUID());
                                one.setRefundamount(0.0);//????????????
                                one.setRefundrate(0.0);//?????????
                                one.setActualsales(0.0); //???????????????
                                one.setTaokepromotionll(0.0);//????????????(??????)
                                one.setAipromotionll(0.0);//AI??????(??????)
                                one.setPinxiaobaoll(0.0);//?????????(??????)
                                one.setPromotionflow(UtilId.getnlllduoble(one.getMoresearchll())+UtilId.getnlllduoble(one.getManyscenesll())+UtilId.getnlllduoble(one.getRestassuredpushll()));//???????????????
                                one.setFreetraffic(one.getTotaltraffic()-one.getPromotionflow()); //????????????
                                one.setFreetrafficzb(one.getFreetraffic()/one.getTotaltraffic()); //??????????????????
                                if (one.getTotaltraffic()!=null){
                                    if (one.getTotaltraffic()!=0){
                                        one.setPromotionflowzb(one.getPromotionflow()/one.getTotaltraffic());//??????????????????
                                    }
                                }

                                one.setTaokepromotionfy(0.0);//????????????(??????)
                                one.setAipromotionfy(0.0);//AI??????(??????)
                                one.setPinxiaobaofy(0.0);//?????????(??????)
                                one.setPromotioncost(UtilId.getnlllduoble(one.getMoresearchfy())+UtilId.getnlllduoble(one.getManyscenesfy())+UtilId.getnlllduoble(one.getRestassuredpushfy()));//???????????????
                                if (one.getSales()!=null){
                                    if (one.getSales()!=0){
                                        one.setPromotioncostzb(one.getPromotioncost()/one.getSales());//??????????????????
                                    }
                                }

                                one.setPlatformbuckle(one.getActualsales()*0.05);//????????????
                                one.setOtherexpenses(0.0);//????????????
                                one.setTotalcost(UtilId.getnlllduoble(one.getPromotioncost())+UtilId.getnlllduoble(one.getPlatformbuckle()));//???????????????
                                if (one.getSales()!=null){
                                    if (one.getSales()!=0){
                                        one.setCostratio(UtilId.getnlllduoble(one.getTotaltraffic())/UtilId.getnlllduoble(one.getSales()));//?????????
                                    }
                                }

                                lsSaleDao.insert(one);
                            }else {
                                one1.setMoresearchll(one.getMoresearchll());
                                one1.setManyscenesll(one.getManyscenesll());
                                one1.setManyscenesfy(one.getManyscenesfy());
                                one1.setMoresearchfy(one.getMoresearchfy());
                                one1.setRestassuredpushll(one.getRestassuredpushll());
                                one1.setRestassuredpushfy(one.getRestassuredpushfy());
                                one1.setSales(one.getSales());
                                one1.setOrderquantity(one.getOrderquantity());
                                one1.setNumBuyers(one.getNumBuyers());
                                one1.setTotaltraffic(one.getTotaltraffic());
                                one1.setRefundrate(UtilId.sting2double(one1.getRefundamount()/one1.getSales()));//?????????
                                one1.setPaymentrate(UtilId.sting2double(one1.getNumBuyers()/one1.getTotaltraffic()));
                                one1.setCustomerprice(one1.getSales()/one1.getNumBuyers());
                                one1.setActualsales(one1.getSales()-one1.getRefundamount()); //???????????????
                                one1.setTaokepromotionll(0.0);//????????????(??????)
                                one1.setAipromotionll(0.0);//AI??????(??????)
                                one1.setPinxiaobaoll(0.0);//?????????(??????)
                                one1.setPromotionflow(UtilId.getnlllduoble(one.getMoresearchll())+UtilId.getnlllduoble(one.getManyscenesll())+UtilId.getnlllduoble(one.getRestassuredpushll()));//???????????????
                                one1.setFreetraffic(one.getTotaltraffic()-one1.getPromotionflow()); //????????????
                                one1.setFreetrafficzb(one1.getFreetraffic()/one.getTotaltraffic()); //??????????????????
                                one1.setPromotionflowzb(one1.getPromotionflow()/one.getTotaltraffic());//??????????????????
                                one1.setTaokepromotionfy(0.0);//????????????(??????)
                                one1.setAipromotionfy(0.0);//AI??????(??????)
                                one1.setPinxiaobaofy(0.0);//?????????(??????)
                                one1.setPromotioncost(UtilId.getnlllduoble(one.getMoresearchfy())+UtilId.getnlllduoble(one.getManyscenesfy())+UtilId.getnlllduoble(one.getRestassuredpushfy()));//???????????????
                                one1.setPromotioncostzb(one1.getPromotioncost()/one.getSales());//??????????????????
                                one1.setPlatformbuckle(one1.getActualsales()*0.05);//????????????
                                one1.setOtherexpenses(0.0);//????????????
                                one1.setTotalcost(UtilId.getnlllduoble(one1.getPromotioncost())+UtilId.getnlllduoble(one1.getPlatformbuckle()));//???????????????
                                one1.setCostratio(UtilId.getnlllduoble(one1.getTotaltraffic())/UtilId.getnlllduoble(one1.getSales()));//?????????
                                lsSaleService.updateById(one1);
                            }
                    }
                }
                if (shop_name.get(iis).getPlatformBh().equals("104")){  //?????????
                    List<LsSale> qtlists = lsSaleService.qtlists(shop_name.get(iis).getShopBh());

                    for (int i =0;i<qtlists.size();i++){
                        List<LsSale> on3 = lsSaleService.list(new QueryWrapper<LsSale>().eq("Date_time", qtlists.get(i).getDateTime()).eq("Shop_bh", qtlists.get(i).getShopBh()));
                        LsSale one = qtlists.get(i);
                        if (on3.size()==0){
                            one.setId(UtilId.getUUID());
                            one.setNumBuyers(0.0);//???????????????
                            one.setRefundamount(0.0);//????????????
                            one.setRefundrate(0.0);//?????????
                            one.setActualsales(one.getSales()); //???????????????
                            one.setPaymentrate(0.0);//???????????????
                            one.setCustomerprice(0.0);//?????????
                            one.setTotaltraffic(0.0);//???????????????
                            one.setFreetraffic(0.0); //????????????
                            one.setFreetrafficzb(0.0); //??????????????????
                            one.setMoresearchll(0.0); //????????????/?????????/????????????????????????
                            one.setManyscenesll(0.0);//????????????/????????????/????????????????????????
                            one.setMoresearchll(0.0);//?????????/????????????/????????????????????????
                            one.setTaokepromotionll(0.0);//????????????(??????)
                            one.setAipromotionll(0.0);//AI??????(??????)
                            one.setPinxiaobaoll(0.0);//?????????(??????)
                            one.setPromotionflow(0.0);//???????????????
                            one.setPromotionflowzb(0.0);//??????????????????
                            one.setMoresearchfy(0.0);//????????????/?????????/????????????(??????)
                            one.setManyscenesfy(0.0);//????????????/????????????/????????????(??????)
                            one.setRestassuredpushfy(0.0);//?????????/????????????/????????????(??????)
                            one.setTaokepromotionfy(0.0);//????????????(??????)
                            one.setAipromotionfy(0.0);//AI??????(??????)
                            one.setPinxiaobaofy(0.0);//?????????(??????)
                            one.setPromotioncost(0.0);//???????????????
                            one.setPromotioncostzb(0.0);//??????????????????
                            one.setOtherexpenses(0.0);//????????????
                            one.setTotalcost(one.getPlatformbuckle());//???????????????
                            one.setCostratio(UtilId.sting2double(one.getTotalcost()/one.getSales()));//?????????
                            if (shop_name.get(iis).getShopName().substring(0,4).equals("????????????")){
                                    one.setPlatformbuckle(one.getActualsales()*0.0115);
                            }else {
                                one.setPlatformbuckle(one.getActualsales()*0);
                            }
                            one.setTotalcost(one.getPlatformbuckle());
                            one.setCostratio(UtilId.sting2double(one.getTotalcost()/one.getSales()));
                            lsSaleDao.insert(one);
                        }
                    }
                }

                */
                if (shop_name.get(iis).getPlatformBh().equals("105")){
                    if (shop_name.get(iis).getShopName().substring(shop_name.get(iis).getShopName().length()-4,shop_name.get(iis).getShopName().length()-1).equals("?????????")){
                        List<LsSale> mgjlists = lsSaleService.mgjlists(shop_name.get(iis).getShopBh());
                        for (int i =0;i<mgjlists.size();i++){
                            List<LsSale> on3 = lsSaleService.list(new QueryWrapper<LsSale>().eq("Date_time", mgjlists.get(i).getDateTime()).eq("Shop_bh", mgjlists.get(i).getShopBh()));
                            LsSale one = mgjlists.get(i);
                            if (on3.size()==0){
                                one.setId(UtilId.getUUID());
                                one.setPlatformbuckle(one.getActualsales()*0.14);// ????????????
                                one.setTotalcost(one.getPlatformbuckle());//???????????????
                                one.setPromotioncostzb(0.0);//??????????????????
                                one.setPromotionflowzb(0.0);//??????????????????
                                one.setFreetrafficzb(0.0); //??????????????????
                                one.setCostratio(one.getTotalcost()/one.getSales());//?????????

                                one.setMoresearchll(0.0); //????????????/?????????/????????????????????????
                                one.setManyscenesll(0.0);//????????????/????????????/????????????????????????
                                one.setMoresearchll(0.0);//?????????/????????????/????????????????????????
                                one.setTaokepromotionll(0.0);//????????????(??????)
                                one.setAipromotionll(0.0);//AI??????(??????)
                                one.setPinxiaobaoll(0.0);//?????????(??????)
                                one.setPromotionflow(0.0);//???????????????
                                one.setMoresearchfy(0.0);//????????????/?????????/????????????(??????)
                                one.setManyscenesfy(0.0);//????????????/????????????/????????????(??????)
                                one.setRestassuredpushfy(0.0);//?????????/????????????/????????????(??????)
                                one.setTaokepromotionfy(0.0);//????????????(??????)
                                one.setAipromotionfy(0.0);//AI??????(??????)
                                one.setPinxiaobaofy(0.0);//?????????(??????)
                                one.setPromotioncost(0.0);//???????????????
                                one.setOtherexpenses(0.0);//????????????
                                one.setFreetraffic(one.getTotaltraffic()-one.getPromotionflow()); //????????????

                                one.setFreetrafficzb(UtilId.sting2double(one.getFreetraffic()/one.getTotaltraffic()));
                                lsSaleDao.insert(one);
                            }
                        }
                    }else {  //??????
                        List<LsSale> dyjlists = lsSaleService.dylists(shop_name.get(iis).getShopBh());

                        for (int i =0;i<dyjlists.size();i++){
                                List<LsSale> on3 = lsSaleService.list(new QueryWrapper<LsSale>().eq("Date_time", dyjlists.get(i).getDateTime()).eq("Shop_bh", dyjlists.get(i).getShopBh()));
                            LsSale one = dyjlists.get(i);
                            LsSale one1 = null;
                            if (on3.size()==0){
                            }else {
                                one1 = on3.get(0);
                            }
                            if (on3.size()==0){
                                one.setId(UtilId.getUUID());
                                one.setPlatformbuckle(one.getActualsales()*0.02);// ????????????
                                one.setTotalcost(one.getPlatformbuckle());//???????????????
                                one.setPromotioncostzb(0.0);//??????????????????
                                one.setPromotionflowzb(0.0);//??????????????????
                                one.setFreetrafficzb(0.0); //??????????????????
                                one.setRestassuredpushll(0.0);
                                one.setRestassuredpushfy(0.0);
                                one.setCostratio(one.getTotalcost()/one.getSales());//?????????
                                one.setRefundrate(UtilId.sting2double(one.getRefundamount()/one.getSales()));
                                one.setPaymentrate(0.0);
                                one.setCustomerprice(one.getSales()/one.getNumBuyers());//?????????
                                one.setTotaltraffic(0.0);//???????????????
                                one.setFreetraffic(0.0); //????????????
                                one.setMoresearchll(0.0); //????????????/?????????/????????????????????????
                                one.setManyscenesll(0.0);//????????????/????????????/????????????????????????
                                one.setMoresearchll(0.0);//?????????/????????????/????????????????????????
                                one.setTaokepromotionll(0.0);//????????????(??????)
                                one.setAipromotionll(0.0);//AI??????(??????)
                                one.setPinxiaobaoll(0.0);//?????????(??????)
                                one.setPromotionflow(0.0);//???????????????
                                one.setMoresearchfy(0.0);//????????????/?????????/????????????(??????)
                                one.setManyscenesfy(0.0);//????????????/????????????/????????????(??????)
                                one.setRestassuredpushfy(0.0);//?????????/????????????/????????????(??????)
                                one.setTaokepromotionfy(0.0);//????????????(??????)
                                one.setAipromotionfy(0.0);//AI??????(??????)
                                one.setPinxiaobaofy(0.0);//?????????(??????)
                                one.setPromotioncost(0.0);//???????????????
                                one.setOtherexpenses(0.0);//????????????
                                lsSaleDao.insert(one);
                            }else {
                                one1.setSales(one.getSales());
                                one1.setOrderquantity(one.getOrderquantity());
                                one1.setNumBuyers(one.getNumBuyers());
                                one1.setRefundamount(one.getRefundamount());
                                one1.setActualsales(one.getActualsales());
                                one1.setPlatformbuckle(one.getActualsales()*0.02);// ????????????
                                one1.setTotalcost(one1.getPlatformbuckle());//???????????????
                                one1.setPromotioncostzb(0.0);//??????????????????
                                one1.setPromotionflowzb(0.0);//??????????????????
                                one1.setFreetrafficzb(0.0); //??????????????????
                                if(one.getSales() !=0 || one.getSales() !=null ){
                                    one1.setCostratio(one1.getTotalcost()/one.getSales());//?????????
                                }
                               if (one.getSales() !=0 || one.getSales()!=null){
                                   one1.setRefundrate(one.getRefundamount()/one.getSales());
                               }

                                if (one.getNumBuyers()!=0 || one.getNumBuyers() !=null){
                                    one1.setCustomerprice(one.getSales()/one.getNumBuyers());//?????????
                                }

                                if (one1.getPaymentrate() !=0||one1.getPaymentrate()!=null){
                                    one1.setTotaltraffic((double) Math.round(one1.getNumBuyers()/one1.getPaymentrate()));//???????????????
                                    one1.setFreetraffic(one1.getTotaltraffic()); //????????????
                                    one1.setFreetrafficzb(one1.getFreetraffic()/one1.getTotaltraffic());
                                }


                                one1.setMoresearchll(0.0); //????????????/?????????/????????????????????????
                                one1.setManyscenesll(0.0);//????????????/????????????/????????????????????????
                                one1.setMoresearchll(0.0);//?????????/????????????/????????????????????????
                                one1.setTaokepromotionll(0.0);//????????????(??????)
                                one1.setAipromotionll(0.0);//AI??????(??????)
                                one1.setPinxiaobaoll(0.0);//?????????(??????)
                                one1.setPromotionflow(0.0);//???????????????
                                one1.setMoresearchfy(0.0);//????????????/?????????/????????????(??????)
                                one1.setManyscenesfy(0.0);//????????????/????????????/????????????(??????)
                                one1.setRestassuredpushfy(0.0);//?????????/????????????/????????????(??????)
                                one1.setTaokepromotionfy(0.0);//????????????(??????)
                                one1.setAipromotionfy(0.0);//AI??????(??????)
                                one1.setPinxiaobaofy(0.0);//?????????(??????)
                                one1.setPromotioncost(0.0);//???????????????
                                one1.setOtherexpenses(0.0);//????????????
                                lsSaleDao.updateById(one1);
                            }
                        }
                    }
                }
            }
            List<LsSale> zhzlists = lsSaleService.zhzlists();
            for (int i =0;i<zhzlists.size();i++){
                List<LsSale> list = lsSaleService.list(new QueryWrapper<LsSale>().eq("Shop_bh", zhzlists.get(i).getShopBh()).eq("Date_time", zhzlists.get(i).getDateTime()));
                if (list.size()==0){
                    zhzlists.get(i).setId(UtilId.getUUID());
                    lsSaleDao.insert(zhzlists.get(i));
                }
            }
        }


    /**
     * ???????????????
     */
    @GetMapping("cjmr")
    public void cjmrShow(){
        List<LsShop> list = lsShopService.list(new QueryWrapper<LsShop>().eq("state",1));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        ArrayList<String> strings = new ArrayList<>();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("101","D:\\????????????9\\?????????\\");
        stringStringHashMap.put("102","D:\\????????????9\\??????\\");
        stringStringHashMap.put("103","D:\\????????????9\\??????\\");
        stringStringHashMap.put("104","D:\\????????????9\\????????????\\");
        stringStringHashMap.put("105","D:\\????????????9\\?????????\\");
        ArrayList<String> strings1 = new ArrayList<>();
        strings1.add("2021-10-16");
        strings1.add("2021-10-17");
        strings1.add("2021-10-18");
        strings1.add("2021-10-19");
        strings1.add("2021-10-20");
        strings1.add("2021-10-21");
        strings1.add("2021-10-22");
        strings1.add("2021-10-23");
        strings1.add("2021-10-24");
        strings1.add("2021-10-25");
        strings1.add("2021-10-26");
        strings1.add("2021-10-27");
        strings1.add("2021-10-28");
        strings1.add("2021-10-29");
        strings1.add("2021-10-30");

        for (int ics =0;ics<strings1.size();ics++){


        for (int i =0;i<list.size();i++){
            if (list.get(i).getPlatformBh().equals("101")){
                File file = new File(stringStringHashMap.get("101")+strings1.get(ics)+"_"+list.get(i).getShopName());
                if (!file.exists()){
                    file.mkdir();
                }
            }
            if (list.get(i).getPlatformBh().equals("102")){
                File file = new File(stringStringHashMap.get("102")+strings1.get(ics)+"_"+list.get(i).getShopName());
                if (!file.exists()){
                    file.mkdir();
                }
            }
            if (list.get(i).getPlatformBh().equals("103")){
                File file = new File(stringStringHashMap.get("103")+strings1.get(ics)+"_"+list.get(i).getShopName());
                if (!file.exists()){
                    file.mkdir();
                }
            }

            if (list.get(i).getPlatformBh().equals("104")){
                if (list.get(i).getShopName().substring(list.get(i).getShopName().length()-3,list.get(i).getShopName().length()-1).equals("??????")){
                    File file = new File(stringStringHashMap.get("104")+strings1.get(ics)+"_"+list.get(i).getShopName());
                    if (!file.exists()){
                        file.mkdir();
                    }
                }
                if (list.get(i).getShopName().substring(0,4).equals("????????????") || list.get(i).getShopName().substring(0,4).equals("????????????")){
                    File file = null;
                    if (list.get(i).getShopName().substring(0,4).equals("????????????")){
                        calendar.add(Calendar.DATE, -3);
                        file= new File(stringStringHashMap.get("104")+strings1.get(ics)+"_"+list.get(i).getShopName().substring(0,4));
                        calendar.add(Calendar.DATE, +3);
                    }else {
                        file = new File(stringStringHashMap.get("104")+strings1.get(ics)+"_"+list.get(i).getShopName().substring(0,4));
                    }
                    if (!file.exists()){
                        file.mkdir();
                    }
                }
            }
            if (list.get(i).getPlatformBh().equals("105")){
                File file = new File(stringStringHashMap.get("105")+strings1.get(ics)+"_"+list.get(i).getShopName());
                if (!file.exists()){
                    file.mkdir();
                }
            }

        }
                    }
    }

    /**
     * ???????????????
     */
    @GetMapping("deletwj")
    public void deletShow(){
        List<LsShop> list = lsShopService.list(new QueryWrapper<LsShop>().eq("state",1));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -2);
        for (int i =0;i<list.size();i++){
            if (list.get(i).getShopName().substring(0,4).equals("????????????") || list.get(i).getShopName().substring(0,4).equals("????????????")){
             if (list.get(i).getShopName().substring(0,4).equals("????????????")){
                 calendar.add(Calendar.DATE, -3);
                 Delecwj.delFolder("D:\\????????????\\"+df.format(calendar.getTime())+"_"+list.get(i).getShopName().substring(0,4));
                 calendar.add(Calendar.DATE, +3);
             }else {
                 Delecwj.delFolder("D:\\????????????\\"+df.format(calendar.getTime())+"_"+list.get(i).getShopName().substring(0,4));
             }

            }else {

                Delecwj.delFolder("D:\\????????????\\"+df.format(calendar.getTime())+"_"+list.get(i).getShopName());
            }
        }

    }


    @GetMapping("zonghuzong")
    public void zonghuzong(){
        List<LsSale> zhzlists = lsSaleService.zhzlists();
        for (int i =0;i<zhzlists.size();i++){
            List<LsSale> list = lsSaleService.list(new QueryWrapper<LsSale>().eq("Shop_bh", zhzlists.get(i).getShopBh()).eq("Date_time", zhzlists.get(i).getDateTime()));
            if (list.size()==0){
                zhzlists.get(i).setId(UtilId.getUUID());
                lsSaleDao.insert(zhzlists.get(i));
            }
        }
    }

    @GetMapping("zonghuzong1")
    public void zonghuzong1(){
        DDmcSheng.readTxtFile("D:\\??????\\result.txt");
    }



    //????????????????????????
    @GetMapping("pdsfzq")
    public R pdshop(){
        Map<String, Object> stringObjectHashMap = new HashMap<>();
        List<Map<String,String>> objects = new ArrayList<>();

        String [] fileName = Delecwj.getFileName("D:\\????????????");
        List<String> strings = Arrays.asList(fileName);
        List<String> strings1 = new ArrayList<>();
        List<String> strings3 = new ArrayList<>(); //???????????????
        List<String> strings4 = new ArrayList<>(); //?????????
        for (int i = 0;i<strings.size();i++){
            strings1.add("D:\\????????????\\"+strings.get(i));
        }
        for (int i = 0;i<strings1.size();i++){
            String [] fileName1 = Delecwj.getFileName(strings1.get(i));
            List<String> strings41= Arrays.asList(fileName1);
            for (int ii = 0;ii<strings41.size();ii++){
                strings3.add(strings1.get(i)+"\\"+strings41.get(ii));
            }
        }

        for (int i = 0;i<strings3.size();i++){
            String [] fileName1 = Delecwj.getFileName(strings3.get(i));
            List<String> strings5= Arrays.asList(fileName1);
            for (int ii = 0;ii<strings5.size();ii++){
                strings4.add(strings3.get(i)+"\\"+strings5.get(ii));
            }
        }
        List<Map<String, String>> maps = new ArrayList<>();
       // HashMap<String, String> zdMap = new HashMap<>();
        HashMap<String, String> pddHashMap = new HashMap<>();
       // for (String key : pddHashMap.keySet()){
        //    String value = pddHashMap.get(key);

       // }
        maps.add(pddHashMap);
        HashMap<String, String> txHashMap = new HashMap<>();

        maps.add(txHashMap);
        HashMap<String, String> jdHashMap = new HashMap<>();

        maps.add(jdHashMap);
        HashMap<String, String> mtyxHashMap = new HashMap<>();
        maps.add(mtyxHashMap);
        HashMap<String, String> ddmcHashMap = new HashMap<>();

        maps.add(ddmcHashMap);
        HashMap<String, String> dyHashMap = new HashMap<>();

        maps.add(dyHashMap);
        HashMap<String, String> mgjHashMap = new HashMap<>();

        maps.add(mgjHashMap);
        HashMap<String, String> xmHashMap = new HashMap<>();

        HashMap<String, String> mtHashMap = new HashMap<>();

        int s =0;

        for (int i = 0;i<strings4.size();i++){
            String ms = strings4.get(i).split("\\\\")[3]; //???????????????

        }

        for (int i = 0;i<strings4.size();i++) {
            String pt = strings4.get(i).split("\\\\")[2]; //?????????????????????
            String ms = strings4.get(i).split("\\\\")[3]; //???????????????
            String ms1 = strings4.get(i).split("\\\\")[4]; //????????????

            String data1 = ms.split("_")[0];  //??????
            String file = ms.split("_")[1];  //????????????
            if (file.equals("????????????")) {
                file = file + "(??????)";
            }
            if (file.equals("????????????")) {
                file = file + "(??????)";
            }
            LsShop shop_name = lsShopService.getOne(new QueryWrapper<LsShop>().eq("Shop_name", file));

            if (shop_name!=null){
                if (shop_name.getPlatformBh().equals("101")){ //?????????
                    int s1 = 0;
                    HashMap<String, String> stringStringHashMap = new HashMap<>();
                    if (ms1.substring(0,15).equals("????????????_??????_????????????_??????")){
                        pddHashMap.put(shop_name.getShopName()+",????????????_??????_????????????_??????","1");
                        }
                    if (ms1.substring(0,15).equals("?????????_??????_????????????_??????")){
                        pddHashMap.put(shop_name.getShopName()+",?????????_??????_????????????_??????","1");
                    }
                    if (ms1.substring(0,15).equals("????????????_??????_????????????_??????")){
                        pddHashMap.put(shop_name.getShopName()+",????????????_??????_????????????_??????","1");
                    }
                    if (ms1.substring(0,2).equals("QQ")){
                        Map<String, String> stringStringMap = DDmcSheng.readTxtFile(strings4.get(i) + "\\result.txt");
                        if (stringStringMap.size()!=0){
                            if (stringStringMap.size()==1 && stringStringMap.get("????????????") !=null){
                                pddHashMap.put(shop_name.getShopName()+",????????????","1");
                            }
                            if (stringStringMap.size()!=1 && stringStringMap.get("???????????????") !=null && stringStringMap.get("???????????????") !=null && stringStringMap.get("???????????????") !=null && stringStringMap.get("????????????") !=null){
                                pddHashMap.put(shop_name.getShopName()+",??????","1");
                            }
                        }
                    }
                }
                if (shop_name.getPlatformBh().equals("102")){ //??????
                    if (ms1.substring(0,4).equals("????????????")){ //????????? ???
                        txHashMap.put(shop_name.getShopName()+",????????????","1");
                    }
                    if (ms1.substring(0,11).equals("????????????_??????????????????")){//????????????
                        txHashMap.put(shop_name.getShopName()+",????????????_??????????????????","1");
                    }
                    if (ms1.substring(0,4).equals("2021")){ //?????????
                        txHashMap.put(",2021","1");
                    }
                    if (ms1.substring(0,4).equals("????????????")){//????????????
                        txHashMap.put(shop_name.getShopName()+",????????????","1");
                    }
                    if (ms1.substring(0,7).equals("??????_????????????")){ //AI??????
                        txHashMap.put(shop_name.getShopName()+",??????_????????????","1");
                    }
                    if (ms1.substring(0,4).equals("????????????")){ //?????????
                        txHashMap.put(shop_name.getShopName()+",????????????","1");
                    }
                    if (ms1.substring(0,4).equals("arre")){ //????????????
                        txHashMap.put(shop_name.getShopName()+",arre","1");
                    }
                    if (ms1.substring(0,4).equals("????????????")){ //?????????
                        txHashMap.put(shop_name.getShopName()+",????????????","1");
                    }
                    if (ms1.substring(0,2).equals("QQ")){
                        Map<String, String> stringStringMap = DDmcSheng.readTxtFile(strings4.get(i) + "\\result.txt");
                        if (stringStringMap.size()==2 && stringStringMap.get("??????????????????")!=null && stringStringMap.get("??????????????????") !=null){
                            txHashMap.put(shop_name.getShopName()+",2","1");
                        }
                        if (stringStringMap.size()>=2 && stringStringMap.size()!=0 && stringStringMap.get("????????????") !=null && stringStringMap.get("?????????") !=null && stringStringMap.get("???????????????") !=null){
                            txHashMap.put(shop_name.getShopName()+",5","1");
                        }
                    }
                }

                if (shop_name.getPlatformBh().equals("103")){  //??????
                    if (ms1.substring(0,4).equals("????????????_????????????_??????????????????")){ //????????????
                        jdHashMap.put(shop_name.getShopName()+",????????????_????????????_??????????????????","1");
                    }
                    if (ms1.substring(0,18).equals("???????????????????????????_?????????_????????????")){//?????????
                        jdHashMap.put(shop_name.getShopName()+",???????????????????????????_?????????_????????????????????????","1");
                    }
                        if (ms1.substring(0,18).equals("???????????????????????????_????????????????????????")){ //????????????
                            jdHashMap.put(shop_name.getShopName()+",???????????????????????????_????????????????????????_????????????","1");
                        }
                    if (ms1.substring(0,14).equals("????????????_????????????_????????????")){//????????????
                        jdHashMap.put(shop_name.getShopName()+",????????????_????????????_????????????","1");
                    }
                    if (ms1.substring(0,2).equals("QQ")){//
                        Map<String, String> stringStringMap = DDmcSheng.readTxtFile(strings4.get(i) + "\\result.txt");
                        if (stringStringMap!=null && stringStringMap.size()==1){
                            jdHashMap.put(shop_name.getShopName()+"1","1");
                        }
                    }
                    }
                if (shop_name.getPlatformBh().equals("104")){  //??????
                    if (file.substring(0,4).equals("????????????")){//????????????
                        if (ms1.substring(9,12).equals("????????????")){
                            ddmcHashMap.put(shop_name.getShopName()+",????????????",""+s++);
                        }
                    }
                    if (file.substring(0,4).equals("????????????")){//????????????
                       if (ms1.substring(0,4).equals("????????????")){
                           mtyxHashMap.put(shop_name.getShopName()+",????????????","1");
                       }
                    }
                    if (file.substring(file.length()-2,file.length()-1).equals("??????")){//??????
                        Map<String, String> stringStringMap1 = DDmcSheng.readTxtFile1(strings4.get(i) + "\\result.txt");
                        if (stringStringMap1 !=null && stringStringMap1.size() ==1 ){
                            if(stringStringMap1.get("????????????") !=null){

                            }
                            if(stringStringMap1.get("???????????????") !=null){

                            }
                        }
                        if (stringStringMap1 !=null && stringStringMap1.size() ==3 && stringStringMap1.get("????????????") !=null && stringStringMap1.get("????????????") !=null && stringStringMap1.get("???????????????") !=null ){
                        }
                    }
                }
                if (shop_name.getPlatformBh().equals("105")){
                    if (ms1.substring(0,4).equals("????????????")){//?????????
                        mgjHashMap.put(shop_name.getShopName()+"," +
                                "","1");
                    }
                    if (ms1.substring(0,4).equals("????????????")){//??????
                        dyHashMap.put(shop_name.getShopName()+",????????????","1");
                    }
                    if (ms1.substring(0,2).equals("QQ")){//
                        Map<String, String> stringStringMap = DDmcSheng.readTxtFile(strings4.get(i) + "\\result.txt");
                            if (stringStringMap != null && stringStringMap.get("???????????????")!= null && stringStringMap.size()==1){
                                dyHashMap.put(shop_name.getShopName()+",1","1"+shop_name.getShopName());
                            }
                    }
                }
            }
        }
        for (int as =0;as<maps.size();as++){
            System.out.println(maps.get(as));
        }
        return success(maps);
    }

}
