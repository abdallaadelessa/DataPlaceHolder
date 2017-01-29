# DataPlaceHolder
Android View that displays different content based on data state (Loading,Empty,Error,Success) 
# Screenshots

![alt tag](https://github.com/abdallaadelessa/DataPlaceHolder/blob/master/screenshots/screenshot2.png)
![alt tag](https://github.com/abdallaadelessa/DataPlaceHolder/blob/master/screenshots/screenshot.gif)

# Usage
You can create your own data place holder in xml like this (remeber to add ```xmlns:app="http://schemas.android.com/apk/res-auto"```):

```xml
    <com.abdallaadelessa.android.dataplaceholder.SimplePlaceHolder
        android:id="@+id/placeHolder"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:dataViewId="@+id/textView"
        app:imageHeight="200dp"
        app:imageWidth="200dp"
        app:messageTextColor="@color/colorPrimary"
        app:progressColor="@color/colorPrimary"
        app:showImage="@drawable/test"
        app:showMessage="Loading...">
          <!-- here you can put your list , recycler view or any other content -->
        <TextView
            android:id="@+id/textView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:text="My Data"
            android:textAppearance="?android:attr/textAppearanceLarge" />
    </com.abdallaadelessa.android.dataplaceholder.SimplePlaceHolder>
 ```
 
DataPlaceHolder Attrs

```xml

        <attr name="loadLayoutId" format="reference" />
        <attr name="errorLayoutId" format="reference" />
        <attr name="dataLayoutId" format="reference" />
        <attr name="loadViewId" format="reference" />
        <attr name="errorViewId" format="reference" />
        <attr name="dataViewId" format="reference" />
```
SimplePlaceHolder Attrs

```xml

        <attr name="dimModeColor" format="color" />
        <attr name="messageTextColor" format="color" />
        <attr name="progressBarColor" format="color" />
        <attr name="actionButtonBgColor" format="color" />
        <attr name="actionButtonTextColor" format="color" />
        <attr name="showMessage" format="string" />
        <attr name="stateImageWidth" format="dimension" />
        <attr name="stateImageHeight" format="dimension" />
        <attr name="progressSize" format="dimension" />
        <attr name="showStateImage" format="reference" />
        <attr name="showProgress" format="integer" />
        <attr name="showDimProgress" format="integer" />

```

or in code you can use the following methods

```Java

    /**
     * The Main Component Method
     * @param message         Text to show
     * @param progress        if -1 progress will be hidden
     *                        if 0 Indeterminate progress will be shown
     *                        if greater than 0 determinate progress will be shown using the given progress
     * @param dimProgress     if true a dim background will be shown over the content and behind the component views
     * @param stateImageResId state image resource id to be shown if -1 no image will be shown
     * @param actionText      action text for the button which is below the message text view if -1 the default text will be used
     * @param action          the runnable action which would be executed when the action button is clicked if null no action will be executed
     */
    public void showMessage(String message, int progress, boolean dimProgress, int stateImageResId, final String actionText, final Runnable action)
    public void showMessage(int messageResId, int progress, boolean dimProgress, int stateImageResId, final int actionTextResId, final Runnable action)
    public void showMessage(String message, int stateImageResId, final String actionText, final Runnable action)
    public void showMessage(String message, int stateImageResId, final Runnable action)
    public void showMessage(String message, final Runnable action) 
    public void showMessage(String message, int stateImageResId)
    public void showMessage(String message, int progress, int stateImageResId)
    public void showMessage(String message)
    public void showStateImage(int stateImageResId, final String actionText, final Runnable action)
    public void showStateImage(int stateImageResId, final Runnable action)
    public void showStateImage(int stateImageResId) 
    public void showActionButton(final String actionText, final Runnable action) 
    public void showActionButton(final Runnable action)
    public void showProgress(String message, int progress)
    public void showProgress(int progress)
    public void showProgress()
    public void showDimProgress(String message, int progress)
    public void showDimProgress(int progress)
    public void showDimProgress()
    public void dismissAll()
 
```

# Gradle 
```xml

repositories { 
    maven { url 'https://dl.bintray.com/abdalla-essa92/maven' }
}

dependencies {
    compile 'com.abdallaadelessa.android.dataplaceholder:library:1.2.0@aar'
}

```
