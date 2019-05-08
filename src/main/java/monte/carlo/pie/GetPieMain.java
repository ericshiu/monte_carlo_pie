package monte.carlo.pie;

public class GetPieMain {

    private static int padding;

    private static void init(int size) {
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, size);
        StdDraw.setYscale(0, size);
        // 撰寫上方
        StdDraw.text(size * 0.5, size * 0.95, "n = 0, π = 0");
        // 畫x軸
        StdDraw.line(padding / 2, padding / 2, size - (padding / 2), padding / 2);
        // 畫y軸
        StdDraw.line(padding / 2, padding / 2, padding / 2, size - (padding / 2));
        // 寫字 0 跟 1
        StdDraw.text(padding / 2 - 5000, padding / 2 - 2000, "0.0");
        StdDraw.text(padding / 2 - 5000, size - (padding / 2) - 2000, "1.0");
        StdDraw.text(size - (padding / 2), padding / 2 - 4000, "1.0");
        // 顯示
        StdDraw.show();
    }

    private static void start(int size) throws InterruptedException {
        int padding_2 = (padding / 2);
        int n = 100000;
        int count = 0;
        double PI = 0;
        for (int i = 1; i < n; i++) {
            double x = Math.random();
            double y = Math.random();

            // 假設有一半徑為1的圓，那麼其面積就是PI的值,1/4圓的面積就是PI/4。現在有邊長為1的正方形包括這1/4的圓
            // 對於圓，其函數為x的平方+y的平方等於1，對於某點坐標的x和y，x的平方和y的平方和小於1時，說明在圓內。
            // 標準式 ： (x-h)² + (y-k)² = r² 圓心(h,k) 半徑 r
            if ((x * x + y * y) < 1) {
                count++;
                StdDraw.setPenColor(StdDraw.BLUE);
                // 有計算到園內的
                // pi要從新計算
                PI = (double) (4 * count) / i;
            } else {
                StdDraw.setPenColor(StdDraw.RED);
            }
            // 畫小點
            // 一秒120fps
            StdDraw.filledCircle(x * (size - padding) + 200 + padding_2, y * (size - padding) + 200 + padding_2, 500);
            StdDraw.show();
            StdDraw.pause(8);
            // 清除上方的字
            // 一秒120fps
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledRectangle(size * 0.5, size * 0.97, size, padding_2 / 2);
            StdDraw.show();
            StdDraw.pause(1);
            // 改上方的字
            // 一秒120fps
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(size * 0.5, size * 0.95, "n = " + i + ", π = " + PI);
            StdDraw.show();
            StdDraw.pause(7);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int size = 120000;
        padding = size - 100000;
        init(size);
        start(size);
    }

}
