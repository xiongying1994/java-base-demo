package Design_mode.g_adapter;

/**
 * 被适配者
 */
class Adaptee {
    void playMp3(Object src) {
        System.out.println("播放MP3：" + src);
    }
}