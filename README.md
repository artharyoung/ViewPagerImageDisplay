# ViewPagerImageDisplay
主要实现如下两个效果：
![alt_text](https://github.com/artharyoung/ViewPagerImageDisplay/blob/master/images/device-2016-05-21-165941.png "展示多个item") 
![alt_text](https://github.com/artharyoung/ViewPagerImageDisplay/blob/master/images/device-2016-05-21-170040.png "图片切换")

- tips:对于上面的两种效果，参考过网络上许多实现方法，大多数都是通过自定义控件的各项属性等来实现。
个人还是倾向于使用android本身提供的控件来实现这两种效果。

## 知识点小结：
- 通过复写ViewPagerAdapter的getPageWidth(int position)方法来实现多个item的展示：
```java
@Override
public float getPageWidth(int position) {
    return 1/3f;
}
```
- 通过获取屏幕参数来动态设置ViewPager的高度来适配phone、pad等不同分辨率的显示效果。（ViewPager无法设置高度wrap_content，只能指定固定高度）
```java
//获取屏幕像素相关信息
DisplayMetrics dm = new DisplayMetrics();
getWindowManager().getDefaultDisplay().getMetrics(dm);

//根据屏幕信息设置ViewPager容器的宽高,为了适配pad和手机
mViewPager.setLayoutParams(new LinearLayout.LayoutParams(dm.widthPixels, dm.heightPixels * 1/3));
```
tips:这里注意使用的LayoutParams为ViewPager父布局的属性，比如：父布局为LinearLayout则使用LinearLayout.LayoutParams

- 通过RadioGroup动态添加RadioButton来实现切换图片时“点”的切换。这里有两个小细节需要注意：
1、动态添加的RadioButton需要调用setId(int i)手动设置id,否则setOnCheckedChangeListener的时候找不到子View。
2、通过setButtonDrawable设置的图片不能居中，始终在View的左边，这是RadioButton源码中onDraw设定的，看到大多数的解决方案都是复写onDraw
其实是LayoutParams设置不对，应该使用RadioGroup.LayoutParams然后setMargins实现居中。[stackoverflow](http://stackoverflow.com/questions/7905885/margin-set-programmatically-on-radiobutton-not-applied?answertab=active#tab-top "stackoverflow")
```java
RadioButton tempButton = new RadioButton(this);
tempButton.setButtonDrawable(R.drawable.game_banner_dot_normal);

RadioGroup.LayoutParams childParams = new RadioGroup.LayoutParams(
            RadioGroup.LayoutParams.WRAP_CONTENT,
            RadioGroup.LayoutParams.WRAP_CONTENT);
childParams.gravity = Gravity.CENTER_HORIZONTAL;
childParams.setMargins((int)getResources().getDimension(R.dimen.ui_layout_margin),0,
                    (int)getResources().getDimension(R.dimen.ui_layout_margin),0);
tempButton.setId(i);

mRadioGroup.addView(tempButton,childParams);
```
