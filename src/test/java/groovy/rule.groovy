package groovy

/**
 * Created by bjlizhitao on 2016/11/30.
 */
class rule {
    def execute(serverListMap) {
        def serverMap = [:];
        serverMap.putAll(serverListMap);

        // 取得 ip地址list
        def keySet = serverMap.keySet();
        def keyList = new ArrayList<String>();
        keyList.addAll(keySet);

        def random = new Random();
        def randomPos = random.nextInt(keyList.size());

        String server = keyList.get(randomPos);
        return server;
    }
}
