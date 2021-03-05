package com.example.lib;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyClass {


    /***
     -
     -
     */


/***
 **************************************************************
 *                                                            *
 *   .=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.       *
 *    |                     ______                     |      *
 *    |                  .-"      "-.                  |      *
 *    |                 /            \                 |      *
 *    |     _          |              |          _     |      *
 *    |    ( \         |,  .-.  .-.  ,|         / )    |      *
 *    |     > "=._     | )(__/  \__)( |     _.=" <     |      *
 *    |    (_/"=._"=._ |/     /\     \| _.="_.="\_)    |      *
 *    |           "=._"(_     ^^     _)"_.="           |      *
 *    |               "=\__|IIIIII|__/="               |      *
 *    |              _.="| \IIIIII/ |"=._              |      *
 *    |    _     _.="_.="\          /"=._"=._     _    |      *
 *    |   ( \_.="_.="     `--------`     "=._"=._/ )   |      *
 *    |    > _.="                            "=._ <    |      *
 *    |   (_/                                    \_)   |      *
 *    |                                                |      *
 *    '-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-='      *
 *                                                            *
 *           LASCIATE OGNI SPERANZA, VOI CH'ENTRATE           *
 **************************************************************
 */


/***                              _
 *  _._ _..._ .-',     _.._(`))
 * '-. `     '  /-._.-'    ',/
 *    )         \            '.
 *   / _    _    |             \
 *  |  a    a    /              |
 *  \   .-.                     ;
 *   '-('' ).-'       ,'       ;
 *      '-;           |      .'
 *         \           \    /
 *         | 7  .__  _.-\   \
 *         | |  |  ``/  /`  /
 *        /,_|  |   /,_/   /
 *           /,_/      '`-'
 */


/***
 *
 *
 *                                                    __----~~~~~~~~~~~------___
 *                                   .  .   ~~//====......          __--~ ~~
 *                   -.            \_|//     |||\\  ~~~~~~::::... /~
 *                ___-==_       _-~o~  \/    |||  \\            _/~~-
 *        __---~~~.==~||\=_    -_--~/_-~|-   |\\   \\        _/~
 *    _-~~     .=~    |  \\-_    '-~7  /-   /  ||    \      /
 *  .~       .~       |   \\ -_    /  /-   /   ||      \   /
 * /  ____  /         |     \\ ~-_/  /|- _/   .||       \ /
 * |~~    ~~|--~~~~--_ \     ~==-/   | \~--===~~        .\
 *          '         ~-|      /|    |-~\~~       __--~~
 *                      |-~~-_/ |    |   ~\_   _-~            /\
 *                           /  \     \__   \/~                \__
 *                       _--~ _/ | .-~~____--~-/                  ~~==.
 *                      ((->/~   '.|||' -_|    ~~-/ ,              . _||
 *                                 -_     ~\      ~~---l__i__i__i--~~_/
 *                                 _-~-__   ~)  \--______________--~~
 *                               //.-~~~-~_--~- |-------~~~~~~~~
 *                                      //.-~~~--\
 *                               神兽保佑
 *                              代码无BUG!
 */


    /***
     *               ii.                                         ;9ABH,
     *              SA391,                                    .r9GG35&G
     *              &#ii13Gh;                               i3X31i;:,rB1
     *              iMs,:,i5895,                         .5G91:,:;:s1:8A
     *               33::::,,;5G5,                     ,58Si,,:::,sHX;iH1
     *                Sr.,:;rs13BBX35hh11511h5Shhh5S3GAXS:.,,::,,1AG3i,GG
     *                .G51S511sr;;iiiishS8G89Shsrrsh59S;.,,,,,..5A85Si,h8
     *               :SB9s:,............................,,,.,,,SASh53h,1G.
     *            .r18S;..,,,,,,,,,,,,,,,,,,,,,,,,,,,,,....,,.1H315199,rX,
     *          ;S89s,..,,,,,,,,,,,,,,,,,,,,,,,....,,.......,,,;r1ShS8,;Xi
     *        i55s:.........,,,,,,,,,,,,,,,,.,,,......,.....,,....r9&5.:X1
     *       59;.....,.     .,,,,,,,,,,,...        .............,..:1;.:&s
     *      s8,..;53S5S3s.   .,,,,,,,.,..      i15S5h1:.........,,,..,,:99
     *      93.:39s:rSGB@A;  ..,,,,.....    .SG3hhh9G&BGi..,,,,,,,,,,,,.,83
     *      G5.G8  9#@@@@@X. .,,,,,,.....  iA9,.S&B###@@Mr...,,,,,,,,..,.;Xh
     *      Gs.X8 S@@@@@@@B:..,,,,,,,,,,. rA1 ,A@@@@@@@@@H:........,,,,,,.iX:
     *     ;9. ,8A#@@@@@@#5,.,,,,,,,,,... 9A. 8@@@@@@@@@@M;    ....,,,,,,,,S8
     *     X3    iS8XAHH8s.,,,,,,,,,,...,..58hH@@@@@@@@@Hs       ...,,,,,,,:Gs
     *    r8,        ,,,...,,,,,,,,,,.....  ,h8XABMMHX3r.          .,,,,,,,.rX:
     *   :9, .    .:,..,:;;;::,.,,,,,..          .,,.               ..,,,,,,.59
     *  .Si      ,:.i8HBMMMMMB&5,....                    .            .,,,,,.sMr
     *  SS       :: h@@@@@@@@@@#; .                     ...  .         ..,,,,iM5
     *  91  .    ;:.,1&@@@@@@MXs.                            .          .,,:,:&S
     *  hS ....  .:;,,,i3MMS1;..,..... .  .     ...                     ..,:,.99
     *  ,8; ..... .,:,..,8Ms:;,,,...                                     .,::.83
     *   s&: ....  .sS553B@@HX3s;,.    .,;13h.                            .:::&1
     *    SXr  .  ...;s3G99XA&X88Shss11155hi.                             ,;:h&,
     *     iH8:  . ..   ,;iiii;,::,,,,,.                                 .;irHA
     *      ,8X5;   .     .......                                       ,;iihS8Gi
     *         1831,                                                 .,;irrrrrs&@
     *           ;5A8r.                                            .:;iiiiirrss1H
     *             :X@H3s.......                                .,:;iii;iiiiirsrh
     *              r#h:;,...,,.. .,,:;;;;;:::,...              .:;;;;;;iiiirrss1
     *             ,M8 ..,....,.....,,::::::,,...         .     .,;;;iiiiiirss11h
     *             8B;.,,,,,,,.,.....          .           ..   .:;;;;iirrsss111h
     *            i@5,:::,,,,,,,,.... .                   . .:::;;;;;irrrss111111
     *            9Bi,:,,,,......                        ..r91;;;;;iirrsss1ss1111
     */


    /***
     *            .,,       .,:;;iiiiiiiii;;:,,.     .,,
     *          rGB##HS,.;iirrrrriiiiiiiiiirrrrri;,s&##MAS,
     *         r5s;:r3AH5iiiii;;;;;;;;;;;;;;;;iiirXHGSsiih1,
     *            .;i;;s91;;;;;;::::::::::::;;;;iS5;;;ii:
     *          :rsriii;;r::::::::::::::::::::::;;,;;iiirsi,
     *       .,iri;;::::;;;;;;::,,,,,,,,,,,,,..,,;;;;;;;;iiri,,.
     *    ,9BM&,            .,:;;:,,,,,,,,,,,hXA8:            ..,,,.
     *   ,;&@@#r:;;;;;::::,,.   ,r,,,,,,,,,,iA@@@s,,:::;;;::,,.   .;.
     *    :ih1iii;;;;;::::;;;;;;;:,,,,,,,,,,;i55r;;;;;;;;;iiirrrr,..
     *   .ir;;iiiiiiiiii;;;;::::::,,,,,,,:::::,,:;;;iiiiiiiiiiiiri
     *   iriiiiiiiiiiiiiiii;;;::::::::::::::::;;;iiiiiiiiiiiiiiiir;
     *  ,riii;;;;;;;;;;;;;:::::::::::::::::::::::;;;;;;;;;;;;;;iiir.
     *  iri;;;::::,,,,,,,,,,:::::::::::::::::::::::::,::,,::::;;iir:
     * .rii;;::::,,,,,,,,,,,,:::::::::::::::::,,,,,,,,,,,,,::::;;iri
     * ,rii;;;::,,,,,,,,,,,,,:::::::::::,:::::,,,,,,,,,,,,,:::;;;iir.
     * ,rii;;i::,,,,,,,,,,,,,:::::::::::::::::,,,,,,,,,,,,,,::i;;iir.
     * ,rii;;r::,,,,,,,,,,,,,:,:::::,:,:::::::,,,,,,,,,,,,,::;r;;iir.
     * .rii;;rr,:,,,,,,,,,,,,,,:::::::::::::::,,,,,,,,,,,,,:,si;;iri
     *  ;rii;:1i,,,,,,,,,,,,,,,,,,:::::::::,,,,,,,,,,,,,,,:,ss:;iir:
     *  .rii;;;5r,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,sh:;;iri
     *   ;rii;:;51,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,.:hh:;;iir,
     *    irii;::hSr,.,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,.,sSs:;;iir:
     *     irii;;:iSSs:.,,,,,,,,,,,,,,,,,,,,,,,,,,,..:135;:;;iir:
     *      ;rii;;:,r535r:...,,,,,,,,,,,,,,,,,,..,;sS35i,;;iirr:
     *       :rrii;;:,;1S3Shs;:,............,:is533Ss:,;;;iiri,
     *        .;rrii;;;:,;rhS393S55hh11hh5S3393Shr:,:;;;iirr:
     *          .;rriii;;;::,:;is1h555555h1si;:,::;;;iirri:.
     *            .:irrrii;;;;;:::,,,,,,,,:::;;;;iiirrr;,
     *               .:irrrriiiiii;;;;;;;;iiiiiirrrr;,.
     *                  .,:;iirrrrrrrrrrrrrrrrri;:.
     *                        ..,:::;;;;:::,,.
     */

    /***
     *                    _ooOoo_
     *                   o8888888o
     *                   88" . "88
     *                   (| -_- |)
     *                    O\ = /O
     *                ____/`---'\____
     *              .   ' \\| |// `.
     *               / \\||| : |||// \
     *             / _||||| -:- |||||- \
     *               | | \\\ - /// | |
     *             | \_| ''\---/'' | |
     *              \ .-\__ `-` ___/-. /
     *           ___`. .' /--.--\ `. . __
     *        ."" '< `.___\_<|>_/___.' >'"".
     *       | | : `- \`.;`\ _ /`;.`/ - ` : | |
     *         \ \ `-. \_ __\ /__ _/ .-` / /
     * ======`-.____`-.___\_____/___.-`____.-'======
     *                    `=---='
     *
     * .............................................
     *          佛祖保佑             永无BUG
     */


    /***
     * _ooOoo_
     * o8888888o
     * 88" . "88
     * (| -_- |)
     *  O\ = /O
     * ___/`---'\____
     * .   ' \\| |// `.
     * / \\||| : |||// \
     * / _||||| -:- |||||- \
     * | | \\\ - /// | |
     * | \_| ''\---/'' | |
     * \ .-\__ `-` ___/-. /
     * ___`. .' /--.--\ `. . __
     * ."" '< `.___\_<|>_/___.' >'"".
     * | | : `- \`.;`\ _ /`;.`/ - ` : | |
     * \ \ `-. \_ __\ /__ _/ .-` / /
     * ======`-.____`-.___\_____/___.-`____.-'======
     * `=---='
     *          .............................................
     *           佛曰：bug泛滥，我已瘫痪！
     */


    /***
     *      ┌─┐       ┌─┐
     *   ┌──┘ ┴───────┘ ┴──┐
     *   │                 │
     *   │       ───       │
     *   │  ─┬┘       └┬─  │
     *   │                 │
     *   │       ─┴─       │
     *   │                 │
     *   └───┐         ┌───┘
     *       │         │
     *       │         │
     *       │         │
     *       │         └──────────────┐
     *       │                        │
     *       │                        ├─┐
     *       │                        ┌─┘
     *       │                        │
     *       └─┐  ┐  ┌───────┬──┐  ┌──┘
     *         │ ─┤ ─┤       │ ─┤ ─┤
     *         └──┴──┘       └──┴──┘
     *                神兽保佑
     *               代码无BUG!
     */


    public long a;

    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
////        calendar.setTimeInMillis(System.currentTimeMillis()-1000*60*60*24*10);
////        System.out.println(calendar.get(Calendar.YEAR));
////        System.out.println(calendar.get(Calendar.MONTH)+1);
////        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
////        Calendar cal = Calendar.getInstance();
////        //当前年
////        int year = cal.get(Calendar.YEAR);
////        //当前月
////        int month = (cal.get(Calendar.MONTH))+1;
////        //当前月的第几天：即当前日
////        int day_of_month = cal.get(Calendar.DAY_OF_MONTH);
////
////        System.out.println(year);
////        System.out.println(month);
////        System.out.println(day_of_month);

    DownloadManager downloadManager = new DownloadManager();
    downloadManager.start();
    }

    public static String timetodate(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年-MM月dd日");//这里的格式可换"yyyy年-MM月dd日-HH时mm分ss秒"等等格式
        sf.getCalendar().get(Calendar.YEAR);
        String date = sf.format(calendar.getTime());
        return date;

    }

    public static int a() {
        final CountDownLatch parserCtl = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000 + new Random().nextInt(7000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("over 1");
                        parserCtl.countDown();
                    }
                }
            }).start();
        }
        try {
            parserCtl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("over all");
        return 10;
    }

    public static void getFileNameByPackageName() {
        final CountDownLatch parserCtl = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            DefaultPoolExecutor.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        //do Something...
                    } finally {
                        parserCtl.countDown();
                    }
                }
            });
        }

        try {
            parserCtl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("全部完成");
    }

    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    private Util util = new Util();

    public synchronized void fun1() {
        //do something ...
        try {
            reentrantReadWriteLock.readLock().lock();
            if (util != null) {
                util.work();
            }
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
        //do something ...
    }

    public void fun2() {
        //do something ...
        try {
            reentrantReadWriteLock.writeLock().lock();
            util = null;
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
        //do something ...
    }

    public static class Util {
        public void work() {
        }
    }
}
