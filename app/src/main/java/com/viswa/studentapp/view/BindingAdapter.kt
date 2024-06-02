package com.viswa.studentapp.view

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("android:imageUrl")
fun loadPhotoURL(v: ImageView, url:String?){
    if (!url.isNullOrEmpty()) {
        Log.d("BindingAdapter", "Loading image URL: $url")
        val picasso = Picasso.Builder(v.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(url).into(v)
    } else {
        Log.d("BindingAdapter", "Image URL is null or empty")
        //Image is being shown but goes here??? What the h#ll???
//        FATAL EXCEPTION: main
//                Process: com.viswa.studentapp, PID: 9742
//        java.lang.NullPointerException: Parameter specified as non-null is null: method com.viswa.studentapp.view.BindingAdapterKt.loadPhotoURL, parameter url
//        at com.viswa.studentapp.view.BindingAdapterKt.loadPhotoURL(Unknown Source:7)
//        at com.viswa.studentapp.databinding.FragmentStudentDetailBindingImpl.executeBindings(FragmentStudentDetailBindingImpl.java:152)
//        at androidx.databinding.ViewDataBinding.executeBindingsInternal(ViewDataBinding.java:512)
//        at androidx.databinding.ViewDataBinding.executePendingBindings(ViewDataBinding.java:484)
//        at androidx.databinding.ViewDataBinding$7.run(ViewDataBinding.java:218)
//        at androidx.databinding.ViewDataBinding$8.doFrame(ViewDataBinding.java:320)
//        at android.view.Choreographer$CallbackRecord.run(Choreographer.java:1337)
//        at android.view.Choreographer$CallbackRecord.run(Choreographer.java:1348)
//        at android.view.Choreographer.doCallbacks(Choreographer.java:952)
//        at android.view.Choreographer.doFrame(Choreographer.java:878)
//        at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:1322)
//        at android.os.Handler.handleCallback(Handler.java:958)
//        at android.os.Handler.dispatchMessage(Handler.java:99)
//        at android.os.Looper.loopOnce(Looper.java:205)
//        at android.os.Looper.loop(Looper.java:294)
//        at android.app.ActivityThread.main(ActivityThread.java:8177)
//        at java.lang.reflect.Method.invoke(Native Method)
//        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:552)
//        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:971)

    }
}

