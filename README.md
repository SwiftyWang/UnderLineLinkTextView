# UnderLineLinkTextView

Support Maven:
```gradle
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
dependencies {
	compile 'com.github.SwiftyWang:UnderLineLinkTextView:2.02'
}
```

Support some of the key words can be clicked with the underline TextView<br>
```xml
    <declare-styleable name="AutoLinkStyleTextView">
        <attr name="link_text_value" format="string|reference"/>
        <attr name="link_text_color" format="color|reference"/>
        <attr name="link_text_bg_color" format="color|reference"/>
        <attr name="link_text_click_bg_color" format="color|reference"/>
        <attr name="link_text_click_bg_auto_invalidate" format="boolean"/>
        <attr name="link_has_under_line" format="boolean"/>
    </declare-styleable>
```
<br>
use, for example:<br>
```xml
    <com.len.library.AutoLinkStyleTextView
        android:id="@+id/tv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/allString"
        android:textSize="16sp"
        app:link_has_under_line="true"
        app:link_text_click_bg_auto_invalidate="false"
        app:link_text_click_bg_color="@color/colorPrimaryDark"
        app:link_text_color="@color/colorAccent"
        app:link_text_value="@string/linkString"
        />
```
```java
    autoLinkStyleTextView.setOnClickCallBack(new AutoLinkStyleTextView.ClickCallBack() {
        @Override
        public void onClick(int position) {
            if (position == 0) {
                Toast.makeText(MainActivity.this, "购买须知", Toast.LENGTH_SHORT).show();
            } else if (position == 1) {
                Toast.makeText(MainActivity.this, "用户条款", Toast.LENGTH_SHORT).show();
            }
        }
     });
```
![](https://github.com/wangshaolei/UnderLineLinkTextView/blob/master/img/1.png)   ![](https://github.com/wangshaolei/UnderLineLinkTextView/blob/master/img/2.png)
