
# CityPicker

现在使用较多的类似美团、外卖等APP的城市选择界面，**一行代码搞定**，就是这么简单粗暴！！！



#### 主要功能：

-   字母悬浮栏
-   指定热门城市
-   自定义动画效果
-   自定义主题
-   名称或拼音搜索
-   返回城市名、code等数据
-   提供定位接口，解耦定位SDK

# Preview

![gif](./img/screen.gif)


![gif](./img/screen1.gif)


![gif](./img/screen2.gif)


# Usage

`CityPicker` 基于`DialogFragment` 实现，已提供定位接口，需要APP自身实现定位。

### 基本使用：

#### Step1:

在`manifest.xml`中给使用`CityPicker` 的`activity`添加主题`android:theme="@style/DefaultCityPickerTheme"`

```xml
<activity android:name=".MainActivity" android:theme="@style/DefaultCityPickerTheme">
  ......
</activity>
```

#### Step2:

注意：热门城市使用`HotCity` ，定位城市使用`LocatedCity` 

```java
List<HotCity> hotCities = new ArrayList<>();
hotCities.add(new HotCity("北京", "北京", "101010100")); //code为城市代码
hotCities.add(new HotCity("上海", "上海", "101020100"));
hotCities.add(new HotCity("广州", "广东", "101280101"));
hotCities.add(new HotCity("深圳", "广东", "101280601"));
hotCities.add(new HotCity("杭州", "浙江", "101210101"));
......

CityPicker.from(activity) //activity或者fragment
  .enableAnimation(true)	//启用动画效果，默认无
  .setAnimationStyle(anim)	//自定义动画
  .setLocatedCity(new LocatedCity("杭州", "浙江", "101210101")))  //APP自身已定位的城市，传null会自动定位（默认）
  .setHotCities(hotCities)	//指定热门城市
  .setOnPickListener(new OnPickListener() {
    @Override
    public void onPick(int position, City data) {
      Toast.makeText(getApplicationContext(), data.getName(), Toast.LENGTH_SHORT).show();
    }
      
    @Override
    public void onCancel(){
      Toast.makeText(getApplicationContext(), "取消选择", Toast.LENGTH_SHORT).show();     
    }
      
    @Override
    public void onLocate() {
      //定位接口，需要APP自身实现，这里模拟一下定位
      new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
          //定位完成之后更新数据
          CityPicker.getInstance()
            .locateComplete(new LocatedCity("深圳", "广东", "101280601"), LocateState.SUCCESS);
        }
      }, 3000);
    }
  })
  .show();
```

### 关于自定义主题：

在`style.xml` 中自定义主题并且继承`DefaultCityPickerTheme` ，别忘了在`manifest.xml` 设置给`activity`。

```xml
<style name="CustomTheme" parent="DefaultCityPickerTheme">
        <item name="cpCancelTextColor">@color/color_green</item>
        <item name="cpSearchCursorDrawable">@color/color_green</item>
        <item name="cpIndexBarNormalTextColor">@color/color_green</item>
        <item name="cpIndexBarSelectedTextColor">@color/color_green</item>
        <item name="cpSectionHeight">@dimen/custom_section_height</item>
        <item name="cpOverlayBackground">@color/color_green</item>
  		......
</style>
```

`CityPicker` 中自定义的所有属性如下，有些属性值必须是引用类型`refrence`，使用时注意。

```xml
<resources>
    <attr name="cpCancelTextSize" format="dimension|reference" />
    <attr name="cpCancelTextColor" format="color|reference" />

    <attr name="cpClearTextIcon" format="reference" />
    <attr name="cpSearchTextSize" format="dimension|reference" />
    <attr name="cpSearchTextColor" format="color|reference" />
    <attr name="cpSearchHintText" format="string|reference" />
    <attr name="cpSearchHintTextColor" format="color|reference" />
    <attr name="cpSearchCursorDrawable" format="reference" />

    <attr name="cpListItemTextSize" format="dimension|reference" />
    <attr name="cpListItemTextColor" format="color|reference" />
    <attr name="cpListItemHeight" format="dimension|reference"/>

    <attr name="cpEmptyIcon" format="reference"/>
    <attr name="cpEmptyIconWidth" format="dimension|reference"/>
    <attr name="cpEmptyIconHeight" format="dimension|reference"/>
    <attr name="cpEmptyText" format="string|reference"/>
    <attr name="cpEmptyTextSize" format="dimension|reference"/>
    <attr name="cpEmptyTextColor" format="color|reference"/>

    <attr name="cpGridItemBackground" format="color|reference"/>
    <attr name="cpGridItemSpace" format="reference"/>
	<!--悬浮栏-->
    <attr name="cpSectionHeight" format="reference"/>
    <attr name="cpSectionTextSize" format="reference" />
    <attr name="cpSectionTextColor" format="reference" />
    <attr name="cpSectionBackground" format="reference" />

    <attr name="cpIndexBarTextSize" format="reference" />
    <attr name="cpIndexBarNormalTextColor" format="reference" />
    <attr name="cpIndexBarSelectedTextColor" format="reference" />
	<!--特写布局-->
    <attr name="cpOverlayWidth" format="dimension|reference"/>
    <attr name="cpOverlayHeight" format="dimension|reference"/>
    <attr name="cpOverlayTextSize" format="dimension|reference"/>
    <attr name="cpOverlayTextColor" format="color|reference"/>
    <attr name="cpOverlayBackground" format="color|reference"/>
</resources>
```
