/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_lwjgl_opengl_NVPresentVideoUtil */

#ifndef _Included_org_lwjgl_opengl_NVPresentVideoUtil
#define _Included_org_lwjgl_opengl_NVPresentVideoUtil
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_lwjgl_opengl_NVPresentVideoUtil
 * Method:    nglEnumerateVideoDevicesNV
 * Signature: (Ljava/nio/ByteBuffer;Ljava/nio/LongBuffer;I)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_opengl_NVPresentVideoUtil_nglEnumerateVideoDevicesNV
  (JNIEnv *, jclass, jobject, jobject, jint);

/*
 * Class:     org_lwjgl_opengl_NVPresentVideoUtil
 * Method:    nglBindVideoDeviceNV
 * Signature: (Ljava/nio/ByteBuffer;IJLjava/nio/IntBuffer;I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_NVPresentVideoUtil_nglBindVideoDeviceNV
  (JNIEnv *, jclass, jobject, jint, jlong, jobject, jint);

/*
 * Class:     org_lwjgl_opengl_NVPresentVideoUtil
 * Method:    nglQueryContextNV
 * Signature: (Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;ILjava/nio/IntBuffer;I)Z
 */
JNIEXPORT jboolean JNICALL Java_org_lwjgl_opengl_NVPresentVideoUtil_nglQueryContextNV
  (JNIEnv *, jclass, jobject, jobject, jint, jobject, jint);

#ifdef __cplusplus
}
#endif
#endif
