# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.6)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
add_definitions(-DNDEBUG)

set(common_sources
        ijkmedia/ijkj4a/j4a/j4a_allclasses.c
        ijkmedia/ijkj4a/j4a/j4a_base.c
        ijkmedia/ijkj4a/j4a/class/android/media/AudioTrack.c
        ijkmedia/ijkj4a/j4a/class/android/media/MediaCodec.c
        ijkmedia/ijkj4a/j4a/class/android/media/MediaFormat.c
        ijkmedia/ijkj4a/j4a/class/android/media/PlaybackParams.c
        ijkmedia/ijkj4a/j4a/class/android/os/Build.c
        ijkmedia/ijkj4a/j4a/class/android/os/Bundle.c
        ijkmedia/ijkj4a/j4a/class/java/nio/Buffer.c
        ijkmedia/ijkj4a/j4a/class/java/nio/ByteBuffer.c
        ijkmedia/ijkj4a/j4a/class/java/util/ArrayList.c
        ijkmedia/ijkj4a/j4a/class/tv/danmaku/ijk/media/player/misc/IMediaDataSource.c
        ijkmedia/ijkj4a/j4a/class/tv/danmaku/ijk/media/player/misc/IAndroidIO.c
        ijkmedia/ijkj4a/j4a/class/tv/danmaku/ijk/media/player/IjkMediaPlayer.c
        ijkmedia/ijkj4a/j4au/class/android/media/AudioTrack.util.c
        ijkmedia/ijkj4a/j4au/class/java/nio/ByteBuffer.util.c

        #ijkmedia/ijksdl/
        ijkmedia/ijksdl/ijksdl_aout.c
        ijkmedia/ijksdl/ijksdl_audio.c
        ijkmedia/ijksdl/ijksdl_egl.c
        ijkmedia/ijksdl/ijksdl_error.c
        ijkmedia/ijksdl/ijksdl_mutex.c
        ijkmedia/ijksdl/ijksdl_stdinc.c
        ijkmedia/ijksdl/ijksdl_thread.c
        ijkmedia/ijksdl/ijksdl_timer.c
        ijkmedia/ijksdl/ijksdl_vout.c
        ijkmedia/ijksdl/ijksdl_extra_log.c
        ijkmedia/ijksdl/gles2/color.c
        ijkmedia/ijksdl/gles2/common.c
        ijkmedia/ijksdl/gles2/renderer.c
        ijkmedia/ijksdl/gles2/renderer_rgb.c
        ijkmedia/ijksdl/gles2/renderer_yuv420p.c
        ijkmedia/ijksdl/gles2/renderer_yuv444p10le.c
        ijkmedia/ijksdl/gles2/shader.c
        ijkmedia/ijksdl/gles2/fsh/rgb.fsh.c
        ijkmedia/ijksdl/gles2/fsh/yuv420p.fsh.c
        ijkmedia/ijksdl/gles2/fsh/yuv444p10le.fsh.c
        ijkmedia/ijksdl/gles2/vsh/mvp.vsh.c
        ijkmedia/ijksdl/dummy/ijksdl_vout_dummy.c
        ijkmedia/ijksdl/ffmpeg/ijksdl_vout_overlay_ffmpeg.c
        ijkmedia/ijksdl/ffmpeg/abi_all/image_convert.c
        ijkmedia/ijksdl/android/android_audiotrack.c
        ijkmedia/ijksdl/android/android_nativewindow.c
        ijkmedia/ijksdl/android/ijksdl_android_jni.c
        ijkmedia/ijksdl/android/ijksdl_aout_android_audiotrack.c
        ijkmedia/ijksdl/android/ijksdl_aout_android_opensles.c
        ijkmedia/ijksdl/android/ijksdl_codec_android_mediacodec_dummy.c
        ijkmedia/ijksdl/android/ijksdl_codec_android_mediacodec_internal.c
        ijkmedia/ijksdl/android/ijksdl_codec_android_mediacodec_java.c
        ijkmedia/ijksdl/android/ijksdl_codec_android_mediacodec.c
        ijkmedia/ijksdl/android/ijksdl_codec_android_mediadef.c
        ijkmedia/ijksdl/android/ijksdl_codec_android_mediaformat_java.c
        ijkmedia/ijksdl/android/ijksdl_codec_android_mediaformat.c
        ijkmedia/ijksdl/android/ijksdl_vout_android_nativewindow.c
        ijkmedia/ijksdl/android/ijksdl_vout_android_surface.c
        ijkmedia/ijksdl/android/ijksdl_vout_overlay_android_mediacodec.c

        #ijkmedia/ijksoundtouch/
        ijkmedia/ijksoundtouch/soundtouch-jni.cpp
        ijkmedia/ijksoundtouch/source/SoundTouch/AAFilter.cpp
        ijkmedia/ijksoundtouch/source/SoundTouch/FIFOSampleBuffer.cpp
        ijkmedia/ijksoundtouch/source/SoundTouch/FIRFilter.cpp
        ijkmedia/ijksoundtouch/source/SoundTouch/cpu_detect_x86.cpp
        ijkmedia/ijksoundtouch/source/SoundTouch/sse_optimized.cpp
        ijkmedia/ijksoundtouch/source/SoundStretch/WavFile.cpp
        ijkmedia/ijksoundtouch/source/SoundTouch/RateTransposer.cpp
        ijkmedia/ijksoundtouch/source/SoundTouch/SoundTouch.cpp
        ijkmedia/ijksoundtouch/source/SoundTouch/InterpolateCubic.cpp
        ijkmedia/ijksoundtouch/source/SoundTouch/InterpolateLinear.cpp
        ijkmedia/ijksoundtouch/source/SoundTouch/InterpolateShannon.cpp
        ijkmedia/ijksoundtouch/source/SoundTouch/TDStretch.cpp
        ijkmedia/ijksoundtouch/source/SoundTouch/BPMDetect.cpp
        ijkmedia/ijksoundtouch/source/SoundTouch/PeakFinder.cpp
        ijkmedia/ijksoundtouch/source/SoundTouch/SoundTouch.cpp
        ijkmedia/ijksoundtouch/source/SoundTouch/mmx_optimized.cpp
        ijkmedia/ijksoundtouch/ijksoundtouch_wrap.cpp

        #ijkmedia/ijkyuv
        ijkmedia/ijkyuv/source/compare.cc
        ijkmedia/ijkyuv/source/compare_common.cc
        ijkmedia/ijkyuv/source/compare_posix.cc
        ijkmedia/ijkyuv/source/convert.cc
        ijkmedia/ijkyuv/source/convert_argb.cc
        ijkmedia/ijkyuv/source/convert_from.cc
        ijkmedia/ijkyuv/source/convert_from_argb.cc
        ijkmedia/ijkyuv/source/convert_to_argb.cc
        ijkmedia/ijkyuv/source/convert_to_i420.cc
        ijkmedia/ijkyuv/source/cpu_id.cc
        ijkmedia/ijkyuv/source/format_conversion.cc
        ijkmedia/ijkyuv/source/planar_functions.cc
        ijkmedia/ijkyuv/source/rotate.cc
        ijkmedia/ijkyuv/source/rotate_argb.cc
        ijkmedia/ijkyuv/source/rotate_mips.cc
        ijkmedia/ijkyuv/source/row_any.cc
        ijkmedia/ijkyuv/source/row_common.cc
        ijkmedia/ijkyuv/source/row_mips.cc
        ijkmedia/ijkyuv/source/row_posix.cc
        ijkmedia/ijkyuv/source/scale.cc
        ijkmedia/ijkyuv/source/scale_argb.cc
        ijkmedia/ijkyuv/source/scale_common.cc
        ijkmedia/ijkyuv/source/scale_mips.cc
        ijkmedia/ijkyuv/source/scale_posix.cc
        ijkmedia/ijkyuv/source/video_common.cc

        #ijkplayer
        ijkmedia/ijkplayer/ff_cmdutils.c
        ijkmedia/ijkplayer/ff_ffplay.c
        ijkmedia/ijkplayer/ff_ffpipeline.c
        ijkmedia/ijkplayer/ff_ffpipenode.c
        ijkmedia/ijkplayer/ijkmeta.c
        ijkmedia/ijkplayer/ijkplayer.c
        ijkmedia/ijkplayer/pipeline/ffpipeline_ffplay.c
        ijkmedia/ijkplayer/pipeline/ffpipenode_ffplay_vdec.c
        ijkmedia/ijkplayer/android/ffmpeg_api_jni.c
        ijkmedia/ijkplayer/android/ijkplayer_android.c
        ijkmedia/ijkplayer/android/ijkplayer_jni.c
        ijkmedia/ijkplayer/android/pipeline/ffpipeline_android.c
        ijkmedia/ijkplayer/android/pipeline/ffpipenode_android_mediacodec_vdec.c
        ijkmedia/ijkplayer/ijkavformat/allformats.c
        ijkmedia/ijkplayer/ijkavformat/ijklivehook.c
        ijkmedia/ijkplayer/ijkavformat/ijkmediadatasource.c
        ijkmedia/ijkplayer/ijkavformat/ijkio.c
        ijkmedia/ijkplayer/ijkavformat/ijkiomanager.c
        ijkmedia/ijkplayer/ijkavformat/ijkiocache.c
        ijkmedia/ijkplayer/ijkavformat/ijkioffio.c
        ijkmedia/ijkplayer/ijkavformat/ijkioandroidio.c
        ijkmedia/ijkplayer/ijkavformat/ijkioprotocol.c
        ijkmedia/ijkplayer/ijkavformat/ijkioapplication.c
        ijkmedia/ijkplayer/ijkavformat/ijkiourlhook.c
        ijkmedia/ijkplayer/ijkavformat/ijkasync.c
        ijkmedia/ijkplayer/ijkavformat/ijkurlhook.c
        ijkmedia/ijkplayer/ijkavformat/ijklongurl.c
        ijkmedia/ijkplayer/ijkavformat/ijksegment.c
        ijkmedia/ijkplayer/ijkavutil/ijkdict.c
        ijkmedia/ijkplayer/ijkavutil/ijkutils.c
        ijkmedia/ijkplayer/ijkavutil/ijkthreadpool.c
        ijkmedia/ijkplayer/ijkavutil/ijktree.c
        ijkmedia/ijkplayer/ijkavutil/ijkfifo.c
        ijkmedia/ijkplayer/ijkavutil/ijkstl.cpp

        prof.c
        native-lib.cpp
        )

set(armeabi_v7a_sources
        ijkmedia/ijkyuv/source/compare_neon.cc
        ijkmedia/ijkyuv/source/rotate_neon.cc
        ijkmedia/ijkyuv/source/row_neon.cc
        ijkmedia/ijkyuv/source/scale_neon.cc
        )

set(arm64_v8a_sources
        ijkmedia/ijkyuv/source/compare_neon64.cc
        ijkmedia/ijkyuv/source/rotate_neon64.cc
        ijkmedia/ijkyuv/source/row_neon64.cc
        ijkmedia/ijkyuv/source/scale_neon64.cc
        )

if (${ANDROID_ABI} STREQUAL armeabi-v7a)
    add_library( # Sets the name of the library.
            ijkplayer
            SHARED
            ${common_sources}
            ${armeabi_v7a_sources}
            )
else ()
    add_library( # Sets the name of the library.
            ijkplayer
            SHARED
            ${common_sources}
            ${arm64_v8a_sources}
            )
endif ()


# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.
configure_file(${Project_SOURCE_DIR}/../nativeLibs/${ANDROID_ABI}/libijkffmpeg.so ${Project_BINARY_DIR}/libijkffmpeg.so COPYONLY)


#ffmpeg
add_library(ijkffmpeg-lib
        SHARED
        IMPORTED)
set_target_properties(ijkffmpeg-lib
        PROPERTIES IMPORTED_LOCATION
        libijkffmpeg.so)
#sdl
add_library(ijksdl-lib
        SHARED
        IMPORTED)
set_target_properties(ijksdl-lib
        PROPERTIES IMPORTED_LOCATION
        libijksdl.so)

find_library(log-lib log)
find_library(m-lib m)
find_library(z-lib z)
find_library(android-lib android)
find_library(EGL-lib EGL)
find_library(jnigraphics-lib jnigraphics)
find_library(GLES-lib GLESv2)
find_library(OpenSLES-lib OpenSLES)

include_directories(
        .
        ffmpeginclude
        ffmpeginclude/${ANDROID_ABI}
        ijkmedia
        ijkmedia/ijkplayer
        ijkmedia/ijkj4a
        ijkmedia/ijksdl
        ijkmedia/ijksoundtouch
        ijkmedia/ijksoundtouch/include
        ijkmedia/ijkyuv
        ijkmedia/ijkyuv/include
)


# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        ijkplayer

        # Links the target library to the log library
        # included in the NDK.
        ijkffmpeg-lib ${log-lib} ${m-lib} ${z-lib} ${android-lib} ${EGL-lib} ${GLES-lib} ${jnigraphics-lib} ${OpenSLES-lib})