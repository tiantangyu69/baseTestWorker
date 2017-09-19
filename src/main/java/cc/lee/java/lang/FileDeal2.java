package cc.lee.java.lang;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bjlizhitao on 2017/7/24.
 * 用户合并
 */
public class FileDeal2 {

    public void dealFile() {
        URL url = this.getClass().getClassLoader().getResource("users.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(url.getPath());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            List<UserInfo> userInfos = Lists.newArrayList();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                List<String> infos = Splitter.on(",").splitToList(line);
                try {
                    userInfos.add(dealUserInfo(infos));
                } catch (ParseException e) {
                }
            }

            bufferedReader.close();
            fileInputStream.close();

            Multimap<String, UserInfo> multimap = LinkedListMultimap.create();
            for (UserInfo userInfo : userInfos) {
                multimap.put(userInfo.getEmail(), userInfo);
            }

            dealMergeInfo(multimap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private UserInfo dealUserInfo(List<String> infos) throws ParseException {
        UserInfo userInfo = new UserInfo();

        userInfo.setEmail(infos.get(0));
        userInfo.setUserId(Long.valueOf(infos.get(1)));
        userInfo.setNickName(infos.get(2));
        userInfo.setCommentCount(Integer.valueOf(infos.get(3)));

        if (!"null".equals(infos.get(4)))
            userInfo.setModifyTime((Date) new SimpleDateFormat("yyyy-MM-dd HH:mm").parseObject(infos.get(4)));
        else
            userInfo.setModifyTime(null);

        if (!"null".equals(infos.get(5)))
            userInfo.setLastPostTime((Date) new SimpleDateFormat("yyyy-MM-dd HH:mm").parseObject(infos.get(5)));
        else
            userInfo.setLastPostTime(null);

        userInfo.setAppVip(Boolean.valueOf(infos.get(6)));
        userInfo.setFollowCount(Integer.valueOf(infos.get(7)));
        userInfo.setFollowerCount(Integer.valueOf(infos.get(8)));
        userInfo.setFavCount(Integer.valueOf(infos.get(9)));
        userInfo.setNoticeCount(Integer.valueOf(infos.get(10)));

        return userInfo;
    }

    private static class UserInfo {
        private String email;
        private Long userId;
        private String nickName;
        private Integer commentCount;
        private Date modifyTime;
        private Date lastPostTime;
        private Boolean appVip;
        private Integer followCount;
        private Integer followerCount;
        private Integer favCount;
        private Integer noticeCount;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public Integer getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(Integer commentCount) {
            this.commentCount = commentCount;
        }

        public Date getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(Date modifyTime) {
            this.modifyTime = modifyTime;
        }

        public Date getLastPostTime() {
            return lastPostTime;
        }

        public void setLastPostTime(Date lastPostTime) {
            this.lastPostTime = lastPostTime;
        }

        public Boolean getAppVip() {
            return appVip;
        }

        public void setAppVip(Boolean appVip) {
            this.appVip = appVip;
        }

        public Integer getFollowCount() {
            return followCount;
        }

        public void setFollowCount(Integer followCount) {
            this.followCount = followCount;
        }

        public Integer getFollowerCount() {
            return followerCount;
        }

        public void setFollowerCount(Integer followerCount) {
            this.followerCount = followerCount;
        }

        public Integer getFavCount() {
            return favCount;
        }

        public void setFavCount(Integer favCount) {
            this.favCount = favCount;
        }

        public Integer getNoticeCount() {
            return noticeCount;
        }

        public void setNoticeCount(Integer noticeCount) {
            this.noticeCount = noticeCount;
        }
    }

    private void dealMergeInfo(Multimap<String, UserInfo> mergeUsers) {
        for (String key : mergeUsers.keySet()) {
            List<UserInfo> userInfos = new ArrayList<UserInfo>(mergeUsers.get(key));

            UserInfo tempMergeFrom = userInfos.get(0);
            UserInfo tempMergeTo = userInfos.get(1);

            UserInfo mergeFrom;
            UserInfo mergeTo;

            // 选出 mergeFrom 和 mergeTo
            // 比较最近发贴时间
            if (tempMergeFrom.getLastPostTime() == null) {
                mergeFrom = tempMergeFrom;
                mergeTo = tempMergeTo;
            } else if (tempMergeTo.getLastPostTime() == null) {
                mergeFrom = tempMergeTo;
                mergeTo = tempMergeFrom;
            } else if (tempMergeFrom.getLastPostTime().compareTo(tempMergeTo.getLastPostTime()) < 0) {
                mergeFrom = tempMergeFrom;
                mergeTo = tempMergeTo;
            } else if (tempMergeFrom.getLastPostTime().compareTo(tempMergeTo.getLastPostTime()) >= 0) {
                mergeFrom = tempMergeTo;
                mergeTo = tempMergeFrom;
            } else {
                mergeFrom = tempMergeFrom;
                mergeTo = tempMergeTo;
            }

            mergeUser(mergeFrom, mergeTo);
        }
    }

    private void mergeUser(UserInfo mergeFrom, UserInfo mergeTo) {
        printMergeDebugInfo(mergeFrom, mergeTo);
    }

    private void printMergeDebugInfo(UserInfo mergeFrom, UserInfo mergeTo) {
        System.out.println("mergeFrom: " + JSON.toJSONString(mergeFrom));
        System.out.println("mergeTo: " + JSON.toJSONString(mergeTo));
        System.out.println("--------------------------------------------------------------");
    }

    public static void main(String[] args) {
        FileDeal2 fileDeal = new FileDeal2();
        fileDeal.dealFile();
    }
}
