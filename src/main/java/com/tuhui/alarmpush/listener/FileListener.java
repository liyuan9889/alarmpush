package com.tuhui.alarmpush.listener;

import com.tuhui.alarmpush.domain.Alarm;
import com.tuhui.alarmpush.domain.Face;
import com.tuhui.alarmpush.domain.Official;
import com.tuhui.alarmpush.domain.Police;
import com.tuhui.alarmpush.init.Initialize;
import com.tuhui.alarmpush.services.AlarmService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class FileListener extends FileAlterationListenerAdaptor {


    /**
     * 文件创建执行
     */
    public void onFileCreate(File file) {
        log.info("【新增文件】:"  + file.getName()  );
       Alarm alarm =  Initialize.salarmService.selectsCodeByImgName(file.getName());
       if(alarm != null){
          String scode =  alarm.getFaceUserCode();
               String smallP =  alarm.getSmallImagePath(); //小图
               String largeP = alarm.getLargeImagePath();//大图
               String faceP = alarm.getFaceImagePath();//证件照
               //必须保障三张照片都有 且在服务器能找到
               if(StringUtils.isNotEmpty(smallP) || StringUtils.isNotEmpty(largeP) || StringUtils.isNotEmpty(faceP)){
                   smallP =  replaceSeparator(smallP);
                   largeP =  replaceSeparator(largeP);
                   faceP  =  replaceSeparator(faceP);
                   boolean sFile = new File(smallP).isFile() ,
                           lFile =new File(largeP).isFile(),
                           fFile = new File(faceP).isFile();
                   //保证三张图片在本地能被获取到
                   if(sFile || lFile || fFile ){
                       //比较时间
                       long cTime = alarm.getComparisonDate().getTime();  //抓拍时间
                       long nTime = new Date().getTime();  //当前时间

                       log.info("抓拍时间--当前时间:{}",cTime +"-"+ nTime);
                       //判断是否超过一分钟
                       if(Math.abs(cTime - nTime) <= 3600){
                           List<Police> list = null;
                           List<Police> list2 = null;
                           List<Police> all = new ArrayList<>();
                           if(scode.contains("xf")  | scode.contains("XF")){
                               //信访人员指定推送
                               log.info("polices:{}",Initialize.codes);
                               all = Initialize.spoliceService.selectMobileByPoliceCodes(Initialize.codes);
                            }else{
                               //找警员号  推送
                               int areaId =  alarm.getAreaId();
                               list =  Initialize.spoliceService.selectPoliceListByAreaId(areaId);
                               //每次推送都要加上 is_recv_all = 1 的条件的警员
                               list2 =  Initialize.spoliceService.selectPoliceListByIsR();
                               if(list != null){
                                   all.addAll(list);
                               }
                               if(list2 != null){
                                   all.addAll(list2);
                               }
                           }

                           Official official = new Official();
                           official.setFaceImg(faceP);
                           official.setLargeImg(largeP);
                           official.setPoliceList(all);
                           official.setSmallImg(smallP);
                            //信息描述
                           Face face = Initialize.sfaceService.selectInfoById(alarm.getId());
                           //判断是否推送成功
                            official.setContent("地点: " + "[" + alarm.getDeployDefenceName() + "] " + alarm.getEquipmentName()
                                    +", 时间: " + alarm.getComparisonDate().toString()
                                    +", 编号: "+ face.getS_code()
                                    +", 姓名: "+ face.getFace_name()
                                    +", 身份证: "+ face.getCard_num() );
                            int count =  Initialize.spushMsgService.pushMessage(official);
                          if(count == 0){
                              //0  推送成功   修改t_alarm_info   del_flag  为1
                              int index =  Initialize.salarmService.updateDelFlag(alarm.getId());
                              if (index  == 0){
                                  log.info("推送成功!");
                              }
                          }else{
                              //1  推送失败   写日志
                              log.info("推送失败!");
                          }
                       }else{
                           log.error("三逃号:" + alarm.getFaceUserCode() + "  抓拍时间超过当前时间一分钟,不推送!");
                       }
                   }else{
                       log.error("请检查三张图片文件是否存在 : ");
                       log.error("小图:{}"+ isTrue(sFile)  + " ;  大图:{}" + isTrue(lFile)+ " ;   证件照:{}" + isTrue(fFile));
                   }
               }else{
                   log.error("请检查三张图片路径是否存在 :  小图:{}"+smallP  + " ;  大图:{}" + largeP + " ;   证件照:{}" + faceP);
               }
       }
    }

    private String isTrue(boolean flag){
        if(flag){
            return "有图片";
        }else{
            return "没图片";
        }
    }

    //转换路径中的斜杠
    private String replaceSeparator(String path){
       path =  path.replace("/",File.separator).replace("\\",File.separator);
       return path;
    }


    public static void main(String[] args) {
    }


    public void onStart(FileAlterationObserver observer) {
        // TODO Auto-generated method stub
        super.onStart(observer);
    }
    public void onStop(FileAlterationObserver observer) {
        // TODO Auto-generated method stub
        super.onStop(observer);
    }

    public  void start(String dir,String suffix) throws Exception{
        // 监控目录
        String rootDir = dir;
        // 轮询间隔 5 秒
        long interval = TimeUnit.SECONDS.toMillis(1);
        // 创建过滤器
        IOFileFilter directories = FileFilterUtils.and(
                FileFilterUtils.directoryFileFilter(),
                HiddenFileFilter.VISIBLE);
        IOFileFilter files    = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),
                FileFilterUtils.suffixFileFilter(suffix));
        IOFileFilter filter = FileFilterUtils.or(directories, files);
        // 使用过滤器
        FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir), filter);
        //不使用过滤器
        //FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir));
        observer.addListener(new FileListener());
        //创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        // 开始监控
        monitor.start();
    }
}