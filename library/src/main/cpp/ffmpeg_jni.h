#include <jni.h>

JNIEXPORT jint JNICALL
Java_Java_com_ffmpeg_utils_FFmpegHelper_execCmd(JNIEnv *env, jclass type,
                                             jobjectArray commands,jobject instance);

JNIEXPORT jint JNICALL
Java_Java_com_ffmpeg_utils_FFmpegHelper_setDebugLevel(JNIEnv *env, jclass type,
                                                         jint debuglevel);