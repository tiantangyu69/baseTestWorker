package utiltest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

/**
 * Created by bjlizhitao on 2016/11/16.
 */
public class DateUtilTest {
    /**
     * 获取今天还剩下多少秒
     *
     * @return
     */
    public static int getMiao() {
        Calendar curDate = Calendar.getInstance();
        Calendar tommorowDate = new GregorianCalendar(curDate.get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate.get(Calendar.DATE) + 1, 0, 0, 0);
        return (int) (tommorowDate.getTimeInMillis() - curDate.getTimeInMillis()) / 1000;
    }

    @Test
    public void test1() {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis() + getMiao() * 1000));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getStartTime()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getFirstDayOfMonth()));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getLastDayOfMonth()));

        for (int i = 0; i < 10; i++) {
            System.out.println("aaa");
            break;
        }
    }

    private Timestamp getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);

        return new Timestamp(todayStart.getTime().getTime());
    }

    public Timestamp getFirstDayOfMonth() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.DAY_OF_MONTH, 1);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);

        return new Timestamp(todayStart.getTime().getTime());
    }

    public static Timestamp getLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);

        return new Timestamp(calendar.getTime().getTime());
    }

    @Test
    public void test11() {
        Map<Long, Integer> userMaps = Maps.newTreeMap();

        for (int i = 0; i < 1988; i++) {
            userMaps.put((long) i, i);
        }

        executeTaskPartial(500, 200, userMaps);
    }


    private void executeTaskPartial(Integer offset, Integer limit, Map<Long, Integer> userMaps) {
        int currentDealCount = 0;// 当前已处理条数，200条一轮，到达200后重置为0
        int currentIndex = 1;// 记录当前处理索引
        int totalCount = userMaps.size();
        List<Long> commentUserGradeDatas = Lists.newArrayList();

        for (Map.Entry<Long, Integer> entry : userMaps.entrySet()) {
            if (currentIndex <= offset) {// 跳过已处理的任务
                currentIndex++;
                continue;
            }

            commentUserGradeDatas.add(entry.getKey());
            currentDealCount++;

            if (currentDealCount == limit || currentIndex == totalCount) {
                currentDealCount = 0;

                int taskStatus = 1;

                if (currentIndex == totalCount) {
                    taskStatus = 2;
                }

                executeTaskWithTransaction(commentUserGradeDatas, currentIndex, totalCount, taskStatus);
            }
            currentIndex++;
        }
    }

    private void executeTaskWithTransaction(List<Long> userIds, int dealCount, int totalCount, int taskStatus) {
        System.out.println("size: " + userIds.size() + "; userIds: " + userIds + "; dealCount: " + dealCount + "; totalCount: " + totalCount + "; taskStatus: " + taskStatus);
        userIds.clear();
    }
}
