package Design_mode.g_adapter;

/**
 * 目标，也就是用户所希望使用的接口
 * 用户想使用的功能，播放Flac
 */
interface Target {
    void playFlac(Object src);
}